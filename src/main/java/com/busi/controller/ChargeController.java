//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.busi.controller;

import com.busi.model.Charge;
import com.jfinal.plugin.activerecord.Record;
import com.zcurd.common.DBTool;
import com.zcurd.common.util.StringUtil;
import com.zcurd.controller.BaseController;
import com.zcurd.ext.render.csv.CsvRender;
import java.util.ArrayList;
import java.util.List;

public class ChargeController extends BaseController {
    public ChargeController() {
    }

    public void listPage() {
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

        List<Record> list = DBTool.findByMultPropertiesDbSource("zcurd_busi", "charge", properties, symbols, values, orderBy, this.getPager());
        this.renderDatagrid(list, DBTool.countByMultPropertiesDbSource("zcurd_busi", "charge", properties, symbols, values));
    }

    public void addPage() {
        this.render("add.html");
    }

    public void add() {
        ((Charge)this.getModel(Charge.class, "model")).save();
        this.addOpLog("[物业端充电纪录表] 增加");
        this.renderSuccess();
    }

    public void updatePage() {
        this.setAttr("model", Charge.me.findById(this.getPara("id")));
        this.render("update.html");
    }

    public void update() {
        Charge model = (Charge)Charge.me.findById(this.getPara("id"));
        model.set("name", this.getPara("model.name"));
        model.set("phone", this.getPara("model.phone"));
        model.set("cardnum", this.getPara("model.cardnum"));
        model.set("cardcs", this.getPara("model.cardcs"));
        model.set("chargenum", this.getPara("model.chargenum"));
        model.set("money", this.getPara("model.money"));
        model.set("total", this.getPara("model.total"));
        model.set("jointime", this.getPara("model.jointime"));
        model.update();
        this.addOpLog("[物业端充电纪录表] 修改");
        this.renderSuccess();
    }

    public void delete() {
        Integer[] ids = this.getParaValuesToInt("id[]");
        Integer[] var5 = ids;
        int var4 = ids.length;

        for(int var3 = 0; var3 < var4; ++var3) {
            Integer id = var5[var3];
            ((Charge)(new Charge()).set("id", id)).delete();
        }

        this.addOpLog("[物业端充电纪录表] 删除");
        this.renderSuccess();
    }

    public void detailPage() {
        Charge model = (Charge)Charge.me.findById(this.getParaToInt("id"));
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

        List<Record> list = DBTool.findByMultPropertiesDbSource("zcurd_busi", "charge", properties, symbols, values);
        List<String> headers = new ArrayList();
        List<String> clomuns = new ArrayList();
        headers.add("用户名称");
        clomuns.add("name");
        headers.add("手机号");
        clomuns.add("phone");
        headers.add("卡号");
        clomuns.add("cardnum");
        headers.add("卡次数");
        clomuns.add("cardcs");
        headers.add("充值次数");
        clomuns.add("chargenum");
        headers.add("消费金额");
        clomuns.add("money");
        headers.add("总共次数");
        clomuns.add("total");
        headers.add("充卡时间");
        clomuns.add("jointime");
        CsvRender csvRender = new CsvRender(headers, list);
        csvRender.clomuns(clomuns);
        csvRender.fileName("物业端充电纪录表");
        this.addOpLog("[物业端充电纪录表] 导出cvs");
        this.render(csvRender);
    }
}
