//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.busi.controller;

import com.busi.model.PayLog;
import com.jfinal.plugin.activerecord.Record;
import com.zcurd.common.DBTool;
import com.zcurd.common.util.StringUtil;
import com.zcurd.controller.BaseController;
import com.zcurd.ext.render.csv.CsvRender;
import java.util.ArrayList;
import java.util.List;

public class PayLogController extends BaseController {
    public PayLogController() {
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

        List<Record> list = DBTool.findByMultPropertiesDbSource("zcurd_busi", "pay_log", properties, symbols, values, orderBy, this.getPager());
        this.renderDatagrid(list, DBTool.countByMultPropertiesDbSource("zcurd_busi", "pay_log", properties, symbols, values));
    }

    public void addPage() {
        this.render("add.html");
    }

    public void add() {
        ((PayLog)this.getModel(PayLog.class, "model")).save();
        this.addOpLog("[付款日志] 增加");
        this.renderSuccess();
    }

    public void updatePage() {
        this.setAttr("model", PayLog.me.findById(this.getPara("id")));
        this.render("update.html");
    }

    public void update() {
        PayLog model = (PayLog)PayLog.me.findById(this.getPara("id"));
        model.set("userid", this.getPara("model.userid"));
        model.set("username", this.getPara("model.username"));
        model.set("starttime", this.getPara("model.starttime"));
        model.set("endtime", this.getPara("model.endtime"));
        model.set("jointime", this.getPara("model.jointime"));
        model.set("status", this.getPara("model.status"));
        model.set("money", this.getPara("model.money"));
        model.set("type", this.getPara("model.type"));
        model.update();
        this.addOpLog("[付款日志] 修改");
        this.renderSuccess();
    }

    public void delete() {
        Integer[] ids = this.getParaValuesToInt("id[]");
        Integer[] var5 = ids;
        int var4 = ids.length;

        for(int var3 = 0; var3 < var4; ++var3) {
            Integer id = var5[var3];
            ((PayLog)(new PayLog()).set("id", id)).delete();
        }

        this.addOpLog("[付款日志] 删除");
        this.renderSuccess();
    }

    public void detailPage() {
        PayLog model = (PayLog)PayLog.me.findById(this.getParaToInt("id"));
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

        List<Record> list = DBTool.findByMultPropertiesDbSource("zcurd_busi", "pay_log", properties, symbols, values);
        List<String> headers = new ArrayList();
        List<String> clomuns = new ArrayList();
        headers.add("代理ID");
        clomuns.add("userid");
        headers.add("代理名称");
        clomuns.add("username");
        headers.add("查询开始日期");
        clomuns.add("starttime");
        headers.add("查询结束日期");
        clomuns.add("endtime");
        headers.add("记录日期");
        clomuns.add("jointime");
        headers.add("状态0 未成功 1 成功");
        clomuns.add("status");
        headers.add("付款金额");
        clomuns.add("money");
        headers.add("费用类型");
        clomuns.add("type");
        CsvRender csvRender = new CsvRender(headers, list);
        csvRender.clomuns(clomuns);
        csvRender.fileName("付款日志");
        this.addOpLog("[付款日志] 导出cvs");
        this.render(csvRender);
    }
}
