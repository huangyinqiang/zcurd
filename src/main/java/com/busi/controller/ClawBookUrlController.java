//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.busi.controller;

import com.busi.model.ClawBookUrl;
import com.jfinal.plugin.activerecord.Record;
import com.zcurd.common.DBTool;
import com.zcurd.common.util.StringUtil;
import com.zcurd.controller.BaseController;
import com.zcurd.ext.render.csv.CsvRender;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class ClawBookUrlController extends BaseController {
    public ClawBookUrlController() {
    }

    public void listPage() {
        this.setAttr("dictDatastatus", ClawBookUrl.me.getDictDatastatus());
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

        List<Record> list = DBTool.findByMultPropertiesDbSource("zcurd_busi", "claw_book_url", properties, symbols, values, orderBy, this.getPager());
        Map<String, Object> dictDatastatus = ClawBookUrl.me.getDictDatastatus();
        Iterator var9 = list.iterator();

        while(var9.hasNext()) {
            Record record = (Record)var9.next();
            String fieldName = "status";
            if (dictDatastatus.get(record.get(fieldName).toString()) != null) {
                record.set(fieldName, dictDatastatus.get(record.get(fieldName).toString()));
            }
        }

        this.renderDatagrid(list, DBTool.countByMultPropertiesDbSource("zcurd_busi", "claw_book_url", properties, symbols, values));
    }

    public void addPage() {
        this.setAttr("dictDatastatus", ClawBookUrl.me.getDictDatastatus());
        this.render("add.html");
    }

    public void add() {
        ((ClawBookUrl)this.getModel(ClawBookUrl.class, "model")).save();
        this.renderSuccess();
    }

    public void updatePage() {
        this.setAttr("dictDatastatus", ClawBookUrl.me.getDictDatastatus());
        this.setAttr("model", ClawBookUrl.me.findById(this.getPara("id")));
        this.render("update.html");
    }

    public void update() {
        ClawBookUrl model = (ClawBookUrl)ClawBookUrl.me.findById(this.getPara("id"));
        model.set("source", this.getPara("model.source"));
        model.set("book_name", this.getPara("model.book_name"));
        model.set("url", this.getPara("model.url"));
        model.set("status", this.getPara("model.status"));
        model.set("create_time", this.getPara("model.create_time"));
        model.set("last_update_time", this.getPara("model.last_update_time"));
        model.update();
        this.renderSuccess();
    }

    public void delete() {
        Integer[] ids = this.getParaValuesToInt("id[]");
        Integer[] var5 = ids;
        int var4 = ids.length;

        for(int var3 = 0; var3 < var4; ++var3) {
            Integer id = var5[var3];
            ((ClawBookUrl)(new ClawBookUrl()).set("id", id)).delete();
        }

        this.renderSuccess();
    }

    public void detailPage() {
        ClawBookUrl model = (ClawBookUrl)ClawBookUrl.me.findById(this.getParaToInt("id"));
        String fieldName = "status";
        Map<String, Object> dictDatastatus = ClawBookUrl.me.getDictDatastatus();
        if (dictDatastatus.get(model.get(fieldName).toString()) != null) {
            model.set(fieldName, dictDatastatus.get(model.get(fieldName).toString()));
        }

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

        List<Record> list = DBTool.findByMultPropertiesDbSource("zcurd_busi", "claw_book_url", properties, symbols, values);
        Map<String, Object> dictDatastatus = ClawBookUrl.me.getDictDatastatus();
        Iterator var9 = list.iterator();

        while(var9.hasNext()) {
            Record record = (Record)var9.next();
            String fieldName = "status";
            if (dictDatastatus.get(record.get(fieldName).toString()) != null) {
                record.set(fieldName, dictDatastatus.get(record.get(fieldName).toString()));
            }
        }

        List<String> headers = new ArrayList();
        List<String> clomuns = new ArrayList();
        headers.add("来源");
        clomuns.add("source");
        headers.add("书名");
        clomuns.add("book_name");
        headers.add("阅读地址");
        clomuns.add("url");
        headers.add("状态");
        clomuns.add("status");
        headers.add("创建时间");
        clomuns.add("create_time");
        headers.add("最后更新时间");
        clomuns.add("last_update_time");
        CsvRender csvRender = new CsvRender(headers, list);
        csvRender.clomuns(clomuns);
        csvRender.fileName("小说管理");
        this.render(csvRender);
    }
}
