//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.zcurd.service;

import com.jfinal.plugin.cron4j.Cron4jPlugin;
import com.jfinal.plugin.cron4j.ITask;
import com.zcurd.common.ErrorMsgException;
import com.zcurd.common.task.TaskConstant;
import com.zcurd.common.task.ZcurdTask;
import com.zcurd.model.TaskBase;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class TaskService {
	public TaskService() {
	}

	public void startAll() {
		System.out.println(new Date() + "开始启动定时任务");
		List<TaskBase> taskList = TaskBase.me.find("select * from task_base");
		int count = 0;
		Iterator var4 = taskList.iterator();

		while(var4.hasNext()) {
			TaskBase taskBase = (TaskBase)var4.next();
			Cron4jPlugin cp = new Cron4jPlugin();
			cp.addTask(taskBase.getCron(), this.createTask(taskBase));
			TaskConstant.getTaskMap().put(taskBase.getId(), cp);
			if (taskBase.getStatus() == 1) {
				cp.start();
				++count;
			}
		}

		System.out.println(new Date() + "完成启动定时任务\t已启动" + count + "个任务");
	}

	public void startOrStop(int taskId, int status) {
		((TaskBase)((TaskBase)TaskBase.me.findById(taskId)).setStatus(status)).update();
		Cron4jPlugin cp = (Cron4jPlugin)TaskConstant.getTaskMap().get(taskId);
		if (status == 1) {
			cp.start();
		} else if (status == 2) {
			cp.stop();
		}

	}

	public void add(TaskBase task) {
		task.setStatus(2);
		task.save();
		Cron4jPlugin cp = new Cron4jPlugin();
		cp.addTask(task.getCron(), this.createTask(task));
		TaskConstant.getTaskMap().put(task.getId(), cp);
	}

	public void update(TaskBase taskBase) {
		Cron4jPlugin cp = (Cron4jPlugin)TaskConstant.getTaskMap().get(taskBase.getId());

		try {
			if (taskBase.getStatus() == 1) {
				cp.stop();
			}
		} catch (Exception var5) {
			var5.printStackTrace();
			throw new ErrorMsgException("任务停止失败，" + var5.getMessage());
		}

		cp = new Cron4jPlugin();
		cp.addTask(taskBase.getCron(), this.createTask(taskBase));

		try {
			if (taskBase.getStatus() == 1) {
				cp.start();
			}
		} catch (Exception var4) {
			var4.printStackTrace();
			throw new ErrorMsgException("任务启动失败，" + var4.getMessage());
		}

		taskBase.update();
		TaskConstant.getTaskMap().put(taskBase.getId(), cp);
	}

	public void delete(Integer id) {
		Cron4jPlugin cp = (Cron4jPlugin)TaskConstant.getTaskMap().get(id);
		TaskBase task = (TaskBase)TaskBase.me.findById(id);

		try {
			if (task.getStatus() == 1) {
				cp.stop();
			}

			cp = null;
			TaskConstant.getTaskMap().remove(id);
			task.delete();
		} catch (Exception var8) {
			var8.printStackTrace();
			throw new ErrorMsgException("任务停止失败，" + var8.getMessage());
		} finally {
			cp = null;
			TaskConstant.getTaskMap().remove(id);
		}

	}

	public void runAtSoon(TaskBase taskBase) {
		this.createTask(taskBase).run();
	}

	private ITask createTask(TaskBase taskBase) {
		ITask task = new ZcurdTask(taskBase.getId(), taskBase.getTargetType(), taskBase.getTargetValue());
		return task;
	}
}
