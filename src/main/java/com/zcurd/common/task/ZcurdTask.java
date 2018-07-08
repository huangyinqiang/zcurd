//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.zcurd.common.task;

import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.ICallback;
import com.jfinal.plugin.cron4j.ITask;
import com.zcurd.common.util.StringUtil;
import com.zcurd.common.util.UrlUtil;
import com.zcurd.model.TaskBase;
import com.zcurd.model.TaskLog;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;

public class ZcurdTask implements ITask {
	private int id;
	private int taskTargetType;
	private String taskTargetValue;

	public ZcurdTask(int taskTargetType, String taskTargetValue) {
		this.taskTargetType = taskTargetType;
		this.taskTargetValue = taskTargetValue;
	}

	public ZcurdTask(int id, int taskTargetType, String taskTargetValue) {
		this.id = id;
		this.taskTargetType = taskTargetType;
		this.taskTargetValue = taskTargetValue;
	}

	public void run() {
		TaskLog log = new TaskLog();
		Date startDate = new Date();
		((TaskLog)((TaskLog)log.setTaskId(this.id)).setStartTime(startDate)).save();
		String result = "成功";

		int costTime;
		try {
			String[] var7;
			int var6 = (var7 = this.taskTargetValue.trim().split(";")).length;

			for(costTime = 0; costTime < var6; ++costTime) {
				final String value = var7[costTime];
				if (!StringUtil.isEmpty(value)) {
					if (this.taskTargetType == 1) {
						String content = UrlUtil.getAsText(value);
						System.out.println(content);
					} else if (this.taskTargetType == 2) {
						Db.execute(new ICallback() {
							public Object call(Connection conn) throws SQLException {
								conn.createStatement().execute(value);
								return true;
							}
						});
					} else if (this.taskTargetType == 3) {
						ITask task = (ITask)Class.forName(value).newInstance();
						task.run();
					}
				}
			}
		} catch (Exception var9) {
			var9.printStackTrace();
			result = "失败";
			log.setRemark(var9.getMessage());
		}

		Date endDate = new Date();
		costTime = (int)(endDate.getTime() - startDate.getTime());
		log.setResult(result);
		log.setEndTime(endDate);
		log.setCostTime(costTime);
		log.update();
		if (this.id > 0) {
			((TaskBase)((TaskBase)((TaskBase)((TaskBase)TaskBase.me.findById(this.id)).setLastRunResult(result)).setLastRunTime(startDate)).setLastRunTimeCost(costTime)).update();
		}

		System.out.println(new Date() + "定时任务执行完成");
	}

	public void stop() {
		System.out.println("top");
	}
}
