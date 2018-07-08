//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.zcurd.model.base;

import com.jfinal.plugin.activerecord.IBean;
import com.jfinal.plugin.activerecord.Model;
import java.util.Date;
@SuppressWarnings({"serial", "unchecked"})
public abstract class BaseTaskBase<M extends BaseTaskBase<M>> extends Model<M> implements IBean {
	public BaseTaskBase() {
	}

	public M setId(java.lang.Integer id) {
		set("id", id);
		return (M)this;
	}

	public Integer getId() {
		return (Integer)this.get("id");
	}

	public M setName(java.lang.String name) {
		set("name", name);
		return (M)this;
	}

	public String getName() {
		return (String)this.get("name");
	}

	public M setTargetType(java.lang.Integer targetType) {
		set("target_type", targetType);
		return (M)this;
	}

	public Integer getTargetType() {
		return (Integer)this.get("target_type");
	}

	public M setTargetValue(java.lang.String targetValue) {
		set("target_value", targetValue);
		return (M)this;
	}

	public String getTargetValue() {
		return (String)this.get("target_value");
	}

	public M setCron(java.lang.String cron) {
		set("cron", cron);
		return (M)this;
	}

	public String getCron() {
		return (String)this.get("cron");
	}

	public M setCreateUserId(java.lang.Integer createUserId) {
		set("create_user_id", createUserId);
		return (M)this;
	}

	public Integer getCreateUserId() {
		return (Integer)this.get("create_user_id");
	}

	public M setLastRunResult(java.lang.String lastRunResult) {
		set("last_run_result", lastRunResult);
		return (M)this;
	}

	public String getLastRunResult() {
		return (String)this.get("last_run_result");
	}

	public M setLastRunTime(java.util.Date lastRunTime) {
		set("last_run_time", lastRunTime);
		return (M)this;
	}

	public Date getLastRunTime() {
		return (Date)this.get("last_run_time");
	}

	public M setLastRunTimeCost(java.lang.Integer lastRunTimeCost) {
		set("last_run_time_cost", lastRunTimeCost);
		return (M)this;
	}

	public Integer getLastRunTimeCost() {
		return (Integer)this.get("last_run_time_cost");
	}

	public M setStatus(java.lang.Integer status) {
		set("status", status);
		return (M)this;
	}

	public Integer getStatus() {
		return (Integer)this.get("status");
	}

	public M setCreateTime(java.util.Date createTime) {
		set("create_time", createTime);
		return (M)this;
	}
	public Date getCreateTime() {
		return (Date)this.get("create_time");
	}
}
