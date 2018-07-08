//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.busi.controller;

import com.busi.model.Pay;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Record;
import com.zcurd.common.DBTool;
import com.zcurd.common.ZcurdTool;
import com.zcurd.common.util.PayUtil;
import com.zcurd.controller.BaseController;
import com.zcurd.model.SysUser;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PayMoneySumController extends BaseController {
    private DecimalFormat df = new DecimalFormat("######0.00");

    public PayMoneySumController() {
    }

    public void listSumPage() {
        this.render("sum.html");
    }

    public void listSumData() {
        StringBuffer sql = new StringBuffer();
        sql.append("select chargetype,sum(money)/100 as money,sum(amount)/100 as amount from charge_money_info inner join qr_match_device on charge_money_info.deviceId = qr_match_device.match_num");
        Object[] queryParams = this.getQueryParams();
        String[] properties = (String[])queryParams[0];
        Object[] values = (Object[])queryParams[2];
        List<Record> result = new ArrayList();
        if (properties.length > 2) {
            String gid = (String)values[0];
            String startTime = (String)values[1];
            String endTime = (String)values[2];
            sql.append(" where 1=1");
            if (!gid.equals("")) {
                sql.append(" and qr_match_device.gid=" + gid);
            }

            if (!startTime.equals("") && !endTime.equals("")) {
                sql.append(" and charge_money_info.createTime between '" + startTime + "' and '" + endTime + "'");
            }

            sql.append("  group by chargetype");
            List<Object> list = Db.use(ZcurdTool.getDbSource("zcurd_busi")).query(sql.toString());
            StringBuffer sql1 = new StringBuffer();
            sql1.append("select operType,sum(charge)/100 from charge_battery_info inner join qr_match_device on charge_battery_info.deviceId=qr_match_device.match_num");
            sql1.append(" where 1=1");
            if (!gid.equals("")) {
                sql1.append(" and qr_match_device.gid=" + gid);
            }

            if (!startTime.equals("") && !endTime.equals("")) {
                sql1.append(" and charge_battery_info.startTime between '" + startTime + "' and '" + endTime + "'");
            }

            sql1.append("  group by operType");
            List<Object> list1 = Db.use(ZcurdTool.getDbSource("zcurd_busi")).query(sql1.toString());

            int i;
            Record r;
            Object[] a;
            String s1;
            for(i = 0; i < list.size(); ++i) {
                r = new Record();
                a = (Object[])list.get(i);
                s1 = (String)a[0];
                BigDecimal s2 = (BigDecimal)a[1];
                r.set("chargetype", s1);
                r.set("money", s2);
                r.set("time", startTime + "至" + endTime);
                result.add(r);
            }

            for(i = 0; i < list1.size(); ++i) {
                r = new Record();
                a = (Object[])list1.get(i);
                s1 = (String)a[0];
                Double s2 = (Double)a[1];
                r.set("chargetype", s1);
                r.set("money", s2);
                r.set("time", startTime + "至" + endTime);
                result.add(r);
            }
        }

        this.renderDatagrid(result, 2);
    }

    public void detailPage() {
        String chargeType = this.getPara("chargetype");
        String money = this.getPara("money");
        String time = this.getPara("time");
        String userId = this.getPara("userid");
        SysUser user = (SysUser)SysUser.me.findById(userId);
        this.setAttr("chargeType", this.getType(chargeType));
        this.setAttr("money", money);
        this.setAttr("rate", user.get("rate"));
        this.setAttr("time", time);
        this.setAttr("userId", userId);
        this.setAttr("openid", user.get("openid"));
        this.setAttr("user", user.get("user_name"));
        this.render("detail.html");
    }

    public void payfor() {
        Pay model = (Pay)this.getModel(Pay.class, "model");
        String temp = this.getPara("time");
        String rateStr = this.getPara("rate");
        String moneyStr = (String)model.get("money");
        double money = Double.parseDouble(moneyStr) * 100.0D;
        Pay pay = new Pay();
        pay.setOpenid((String)model.get("openid"));
        double rate = Double.parseDouble(rateStr);
        money -= money * rate;
        int moneyInt = (int)money;
        pay.setMoney("" + moneyInt);
        pay.setReUserName("");
        pay.setDesc(temp + "时间内的结算款");
        if (pay.getOpenid() != null && !"".equals(pay.getOpenid())) {
            if (PayUtil.PAY_TO_USER(pay)) {
                double a = money / 100.0D;
                model.set("realmoney", this.df.format(a));
                model.set("jointime", new Date());
                model.set("status", 1);
                model.set("rate", rateStr);
                String[] tempS = temp.split("至");
                model.set("starttime", tempS[0]);
                model.set("endtime", tempS[1]);
                if (model.save()) {
                    this.renderSuccess("支付成功,支付记录请点击付款记录查看");
                } else {
                    this.renderFailed("付款成功，数据保存失败");
                }
            } else {
                this.renderFailed("支付失败");
            }

        } else {
            this.renderFailed("支付失败,请设置代理商OPENID");
        }
    }

    private String getType(String value) {
        String temp = "";
        if (value.equals("WA")) {
            temp = "钱包充值";
        } else if (value.equals("CH")) {
            temp = "电卡充值";
        } else if (value.equals("M")) {
            temp = "临时消费";
        } else if (value.equals("W")) {
            temp = "钱包消费";
        } else if (value.equals("WA4")) {
            temp = "电卡消费";
        }

        return temp;
    }

    private List<Record> totalChongZhi(Object[] queryParams) {
        String sql = "select chargetype,sum(money)/100 as money,sum(amount)/100 as amount from charge_money_info inner join qr_match_device on charge_money_info.deviceId = qr_match_device.match_num";
        long gid = (Long)this.getSessionUser().get("id");
        String[] properties = (String[])queryParams[0];
        String[] symbols = (String[])queryParams[1];
        Object[] values = (Object[])queryParams[2];
        List<Object> list = DBTool.findDbSource3("zcurd_busi", sql, properties, symbols, values, gid);
        List<Record> result = new ArrayList();

        for(int i = 0; i < list.size(); ++i) {
            Record r = new Record();
            Object[] a = (Object[])list.get(i);
            String s1 = (String)a[0];
            BigDecimal s2 = (BigDecimal)a[1];
            BigDecimal s3 = (BigDecimal)a[2];
            r.set("chargetype", s1);
            r.set("money", s2);
            r.set("amount", s3);
            result.add(r);
        }

        return result;
    }
}
