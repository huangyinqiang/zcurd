//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.busi.controller;

import com.busi.model.Charge;
import com.busi.model.Config;
import com.busi.model.Customer;
import com.busi.model.LostCard;
import com.jfinal.plugin.activerecord.Record;
import com.zcurd.common.DBTool;
import com.zcurd.common.util.StringUtil;
import com.zcurd.controller.BaseController;
import com.zcurd.ext.render.csv.CsvRender;
import com.zcurd.model.SysUser;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Customer2Controller extends BaseController {
    public Customer2Controller() {
    }

    public void listPage() {
        this.render("list.html");
    }

    public void listPage1() {
        this.render("list1.html");
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

        List<Record> list = DBTool.findByMultPropertiesDbSource("zcurd_busi", "customer", properties, symbols, values, orderBy, this.getPager());
        this.renderDatagrid(list, DBTool.countByMultPropertiesDbSource("zcurd_busi", "customer", properties, symbols, values));
    }

    public void addPage() {
        this.render("add.html");
    }

    public void add() {
        Customer customer = (Customer)this.getModel(Customer.class, "model");
        long id = (Long)this.getSessionUser().get("id");
        SysUser user = (SysUser)SysUser.me.findById(id);
        if (user != null) {
            customer.set("gname", user.get("user_name"));
        }

        customer.set("gid", id);
        if (customer.save()) {
            this.addOpLog("[物业用户卡信息] 增加" + customer.get("name"));
            this.renderSuccess();
        } else {
            this.renderFailed();
        }

    }

    public void charge() {
        Customer customer = (Customer)Customer.me.findById(this.getPara("id"));
        Charge charge = (Charge)this.getModel(Charge.class, "model");
        String knsycs = (String)customer.get("total");
        String cardcs = (String)charge.get("cardcs");
        charge.set("cardcs", cardcs);
        String chargenum = (String)charge.get("chargenum");
        long id = (Long)this.getSessionUser().get("id");
        SysUser user = (SysUser)SysUser.me.findById(id);
        if (user != null) {
            charge.set("gname", user.get("user_name"));
        }

        charge.set("gid", id);
        int total = Integer.parseInt(cardcs) + Integer.parseInt(chargenum);
        charge.set("jointime", new Date());
        charge.set("total", total);
        double money = 0.0D;
        double start = 0.0D;
        double end = 0.0D;

        try {
            start = Double.parseDouble(customer.getStr("money"));
            end = Double.parseDouble(charge.getStr("money"));
            money = start + end;
        } catch (Exception var17) {
            money = end;
        }

        customer.set("money", money);
        customer.set("total", total);
        customer.update();
        if (charge.save()) {
            this.addOpLog("[充值成功信息] 增加");
            this.renderSuccess();
        } else {
            this.addOpLog("[充值失败信息] 增加");
            this.renderFailed();
        }

    }

    public void charge1() {
        Customer customer = (Customer)Customer.me.findById(this.getPara("id"));
        Charge charge = (Charge)this.getModel(Charge.class, "model");
        String knsycs = (String)customer.get("total");
        String cardcs = (String)charge.get("cardcs");
        charge.set("cardcs", cardcs);
        String chargenum = (String)charge.get("chargenum");
        long id = (Long)this.getSessionUser().get("id");
        SysUser user = (SysUser)SysUser.me.findById(id);
        if (user != null) {
            charge.set("gname", user.get("user_name"));
        }

        charge.set("gid", id);
        Float total = Float.parseFloat(cardcs) + Float.parseFloat(chargenum);
        charge.set("jointime", new Date());
        charge.set("total", total);
        double money = 0.0D;
        double start = 0.0D;
        double end = 0.0D;

        try {
            start = Double.parseDouble(customer.getStr("money"));
            end = Double.parseDouble(charge.getStr("money"));
            money = start + end;
        } catch (Exception var17) {
            money = end;
        }

        customer.set("money", money);
        customer.set("total", total);
        customer.update();
        if (charge.save()) {
            this.addOpLog("[充值成功信息] 增加");
            this.renderSuccess();
        } else {
            this.addOpLog("[充值失败信息] 增加");
            this.renderFailed();
        }

    }

    public void lostPage() {
        this.setAttr("model", Customer.me.findById(this.getPara("id")));
        this.render("lost.html");
    }

    public void lost() {
        try {
            long id = (Long)this.getSessionUser().get("id");
            SysUser user = (SysUser)SysUser.me.findById(id);
            Charge charge = (Charge)this.getModel(Charge.class, "model");
            Customer customer = (Customer)Customer.me.findById(this.getPara("id"));
            customer.set("status", 1);
            String total = (String)customer.get("total");
            LostCard lostCard = new LostCard();
            if (user != null) {
                lostCard.set("gname", user.get("user_name"));
            }

            lostCard.set("gid", id);
            lostCard.set("uname", charge.get("name"));
            lostCard.set("cardnum", charge.get("cardnum"));
            lostCard.set("losttime", new Date());
            lostCard.set("balance", total);
            lostCard.save();
            customer.update();
            this.renderSuccess();
            this.addOpLog("[物业用户卡挂失成功] 修改");
        } catch (Exception var8) {
            this.renderFailed("挂失失败:" + var8.getMessage());
            this.addOpLog("[物业用户卡挂失失败]" + var8.getMessage());
        }

    }

    public void updatePage() {
        this.setAttr("model", Customer.me.findById(this.getPara("id")));
        this.render("update.html");
    }

    public void updateCharge() {
        long gid = (Long)this.getSessionUser().get("id");
        this.setAttr("model", Customer.me.findById(this.getPara("id")));
        List<Config> list = Config.me.find("select keyb2value from config where gid=" + gid);
        if (list != null && list.size() > 0) {
            this.setAttr("config", ((Config)list.get(0)).get("keyb2value"));
        }

        this.render("recharge.html");
    }

    public void updateCharge1() {
        long gid = (Long)this.getSessionUser().get("id");
        this.setAttr("model", Customer.me.findById(this.getPara("id")));
        List<Config> list = Config.me.find("select keyb2value from config where gid=" + gid);
        if (list != null && list.size() > 0) {
            this.setAttr("config", ((Config)list.get(0)).get("keyb2value"));
        }

        this.render("recharge1.html");
    }

    public void update() {
        Customer model = (Customer)Customer.me.findById(this.getPara("id"));
        model.set("name", this.getPara("model.name"));
        model.set("phone", this.getPara("model.phone"));
        model.set("cardnum", this.getPara("model.cardnum"));
        model.set("address", this.getPara("model.address"));
        model.update();
        this.addOpLog("[物业用户卡信息] 修改");
        this.renderSuccess();
    }

    public void delete() {
        Integer[] ids = this.getParaValuesToInt("id[]");
        Integer[] var5 = ids;
        int var4 = ids.length;

        for(int var3 = 0; var3 < var4; ++var3) {
            Integer id = var5[var3];
            ((Customer)(new Customer()).set("id", id)).delete();
        }

        this.addOpLog("[物业用户卡信息] 删除");
        this.renderSuccess();
    }

    public void detailPage() {
        Customer model = (Customer)Customer.me.findById(this.getParaToInt("id"));
        this.setAttr("model", model);
        this.render("detail.html");
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

        List<Record> list = DBTool.findByMultPropertiesDbSource("zcurd_busi", "customer", properties, symbols, values);
        List<String> headers = new ArrayList();
        List<String> clomuns = new ArrayList();
        headers.add("用户名");
        clomuns.add("name");
        headers.add("手机号");
        clomuns.add("phone");
        headers.add("卡号");
        clomuns.add("cardnum");
        headers.add("住址");
        clomuns.add("address");
        headers.add("物业名称");
        clomuns.add("property");
        CsvRender csvRender = new CsvRender(headers, list);
        csvRender.clomuns(clomuns);
        csvRender.fileName("物业用户卡信息");
        this.addOpLog("[物业用户卡信息] 导出cvs");
        this.render(csvRender);
    }
}
