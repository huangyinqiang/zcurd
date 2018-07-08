//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.zcurd.common.task;

import com.jfinal.plugin.cron4j.ITask;
import com.zcurd.model.SysUser;
import java.util.List;

public class PayTask implements ITask {
    public PayTask() {
    }

    public void run() {
        System.out.println("------run-------");
    }

    public void stop() {
        System.out.println("------stop-------");
    }

    public void listPayUser() {
        List<SysUser> list = SysUser.me.findByAutoPay();
    }

    public void toSumChargeMoney(SysUser sysUser) {
        int id = (Integer)sysUser.get("id");
        String startTime = "";
        String endTime = "";
        StringBuffer sql = new StringBuffer();
        sql.append("select chargetype,sum(money)/100 as money,sum(amount)/100 as amount from charge_money_info inner join qr_match_device on charge_money_info.deviceId = qr_match_device.match_num");
        sql.append(" where 1=1");
        sql.append(" and qr_match_device.gid=" + id);
        sql.append(" and charge_money_info.createTime between '" + startTime + "' and '" + endTime + "'");
        sql.append("  group by chargetype");
    }

    public void toPayQueen() {
    }
}
