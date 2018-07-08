//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.zcurd.model.base;

import com.jfinal.plugin.activerecord.IBean;
import com.jfinal.plugin.activerecord.Model;
import java.util.Date;
@SuppressWarnings({"serial", "unchecked"})
public abstract class BaseTaskLog<M extends BaseTaskLog<M>> extends Model<M> implements IBean {
	public BaseTaskLog() {
	}

	public M setId(java.lang.Integer id) {
		set("id", id);
		return (M)this;
	}

	public Integer getId() {
		return (Integer)this.get("id");
	}

	public M setTaskId(java.lang.Integer taskId) {
		set("task_id", taskId);
		return (M)this;
	}

	public Integer getTaskId() {
		return (Integer)this.get("task_id");
	}

	public M setResult(java.lang.String result) {
		set("result", result);
		return (M)this;
	}
	public String getResult() {
		return (String)this.get("result");
	}

	public M setStartTime(java.util.Date startTime) {
		set("start_time", startTime);
		return (M)this;
	}


	public Date getStartTime() {
		return (Date)this.get("start_time");
	}

	public M setEndTime(java.util.Date endTime) {
		set("end_time", endTime);
		return (M)this;
	}

	public Date getEndTime() {
		return (Date)this.get("end_time");
	}

	public M setCostTime(java.lang.Integer costTime) {
		set("cost_time", costTime);
		return (M)this;
	}

	public Integer getCostTime() {
		return (Integer)this.get("cost_time");
	}

	public M setRemark(java.lang.String remark) {
		set("remark", remark);
		return (M)this;
	}

	public String getRemark() {
		return (String)this.get("remark");
	}
}
