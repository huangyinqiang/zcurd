//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.zcurd.controller;

import com.jfinal.aop.Before;
import com.jfinal.aop.Duang;
import com.jfinal.plugin.activerecord.Record;
import com.jfinal.plugin.activerecord.tx.Tx;
import com.zcurd.common.DBTool;
import com.zcurd.common.ZcurdTool;
import com.zcurd.common.util.StringUtil;
import com.zcurd.ext.render.csv.CsvRender;
import com.zcurd.model.TaskBase;
import com.zcurd.service.TaskService;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Before({Tx.class})
public class TaskBaseController extends BaseController {
	public TaskBaseController() {
	}

	public void listPage() {
		this.setAttr("dictDatatarget_type", TaskBase.me.getDictDatatarget_type());
		this.setAttr("dictDatastatus", TaskBase.me.getDictDatastatus());
		this.render("list.html");
	}

	public void listData() {
		Object[] queryParams = this.getQueryParams();
		String[] properties = (String[])queryParams[0];
		String[] symbols = (String[])queryParams[1];
		Object[] values = (Object[])queryParams[2];
		String orderBy = this.getOrderBy();
		if (StringUtil.isEmpty(orderBy)) {
			orderBy = "id desc";
		}

		List<Record> list = DBTool.findByMultPropertiesDbSource("zcurd_base", "task_base", properties, symbols, values, orderBy, this.getPager());
		ZcurdTool.replaceDict(TaskBase.me.getDictDatatarget_type(), list, "target_type");
		ZcurdTool.replaceDict(TaskBase.me.getDictDatastatus(), list, "status");
		this.renderDatagrid(list, DBTool.countByMultPropertiesDbSource("zcurd_base", "task_base", properties, symbols, values));
	}

	public void addPage() {
		this.setAttr("dictDatatarget_type", TaskBase.me.getDictDatatarget_type());
		this.setAttr("dictDatastatus", TaskBase.me.getDictDatastatus());
		this.render("add.html");
	}

	public void add() {
		TaskBase model = (TaskBase)this.getModel(TaskBase.class, "model");
		TaskService taskService = (TaskService)Duang.duang(TaskService.class);
		taskService.add(model);
		this.addOpLog("[定时任务] 增加");
		this.renderSuccess();
	}

	public void updatePage() {
		this.setAttr("dictDatatarget_type", TaskBase.me.getDictDatatarget_type());
		this.setAttr("dictDatastatus", TaskBase.me.getDictDatastatus());
		this.setAttr("model", TaskBase.me.findById(this.getPara("id")));
		this.render("update.html");
	}

	public void update() {
		TaskBase model = (TaskBase)TaskBase.me.findById(this.getPara("id"));
		model.setName(this.getPara("model.name"));
		model.setTargetType(this.getParaToInt("model.target_type"));
		model.setTargetValue(this.getPara("model.target_value"));
		model.setCron(this.getPara("model.cron"));
		model.setStatus(this.getParaToInt("model.status"));
		TaskService taskService = (TaskService)Duang.duang(TaskService.class);
		taskService.update(model);
		this.addOpLog("[定时任务] 修改");
		this.renderSuccess();
	}

	public void delete() {
		Integer[] ids = this.getParaValuesToInt("id[]");
		TaskService taskService = (TaskService)Duang.duang(TaskService.class);
		Integer[] var6 = ids;
		int var5 = ids.length;

		for(int var4 = 0; var4 < var5; ++var4) {
			Integer id = var6[var4];
			taskService.delete(id);
		}

		this.addOpLog("[定时任务] 删除");
		this.renderSuccess();
	}

	public void detailPage() {
		TaskBase model = (TaskBase)TaskBase.me.findById(this.getParaToInt("id"));
		Map<String, Object> dictDatatarget_type = TaskBase.me.getDictDatatarget_type();
		if (dictDatatarget_type.get(model.get("target_type").toString()) != null) {
			model.set("target_type", dictDatatarget_type.get(model.get("target_type").toString()));
		}

		Map<String, Object> dictDatastatus = TaskBase.me.getDictDatastatus();
		if (dictDatastatus.get(model.get("status").toString()) != null) {
			model.set("status", dictDatastatus.get(model.get("status").toString()));
		}

		this.setAttr("model", model);
		this.render("detail.html");
	}

	public void startOrStop() {
		TaskService taskService = (TaskService)Duang.duang(TaskService.class);
		taskService.startOrStop(this.getParaToInt("id"), this.getParaToInt("status"));
		this.renderSuccess();
	}

	public void runAtSoon() {
		TaskService taskService = (TaskService)Duang.duang(TaskService.class);
		TaskBase taskBase = (TaskBase)TaskBase.me.findById(this.getPara("id"));
		taskService.runAtSoon(taskBase);
		this.renderSuccess();
	}

	public void exportCsv() {
		Object[] queryParams = this.getQueryParams();
		String[] properties = (String[])queryParams[0];
		String[] symbols = (String[])queryParams[1];
		Object[] values = (Object[])queryParams[2];
		String orderBy = this.getOrderBy();
		if (StringUtil.isEmpty(orderBy)) {
			orderBy = "id desc";
		}

		List<Record> list = DBTool.findByMultPropertiesDbSource("zcurd_base", "task_base", properties, symbols, values);
		List<String> headers = new ArrayList();
		List<String> clomuns = new ArrayList();
		headers.add("名称");
		clomuns.add("name");
		headers.add("目标类型");
		clomuns.add("target_type");
		headers.add("目标值");
		clomuns.add("target_value");
		headers.add("cron表达式");
		clomuns.add("cron");
		headers.add("上次执行时间");
		clomuns.add("last_run_time");
		headers.add("上次执行耗时");
		clomuns.add("last_run_time_cost");
		headers.add("状态");
		clomuns.add("status");
		CsvRender csvRender = new CsvRender(headers, list);
		csvRender.clomuns(clomuns);
		csvRender.fileName("定时任务");
		this.addOpLog("[定时任务] 导出cvs");
		this.render(csvRender);
	}
}
