//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.busi.controller;

import com.busi.model.StockHistoryLog;
import com.jfinal.plugin.activerecord.Record;
import com.zcurd.common.DBTool;
import com.zcurd.common.util.StringUtil;
import com.zcurd.controller.BaseController;
import com.zcurd.ext.render.csv.CsvRender;
import java.util.ArrayList;
import java.util.List;

public class StockHistoryLogController extends BaseController {
    public StockHistoryLogController() {
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

        this.renderDatagrid(DBTool.findByMultPropertiesDbSource("zcurd_busi", "stock_history_log", properties, symbols, values, orderBy, this.getPager()), DBTool.countByMultPropertiesDbSource("zcurd_busi", "stock_history_log", properties, symbols, values));
    }

    public void addPage() {
        this.render("add.html");
    }

    public void add() {
        ((StockHistoryLog)this.getModel(StockHistoryLog.class, "model")).save();
        this.renderSuccess();
    }

    public void updatePage() {
        this.setAttr("model", StockHistoryLog.me.findById(this.getPara("id")));
        this.render("update.html");
    }

    public void update() {
        StockHistoryLog model = (StockHistoryLog)StockHistoryLog.me.findById(this.getPara("id"));
        model.set("dt", this.getPara("model.dt"));
        model.set("code", this.getPara("model.code"));
        model.set("name", this.getPara("model.name"));
        model.set("closing_price", this.getPara("model.closing_price"));
        model.set("top_price", this.getPara("model.top_price"));
        model.set("minimum_price", this.getPara("model.minimum_price"));
        model.set("opening_price", this.getPara("model.opening_price"));
        model.set("pre", this.getPara("model.pre"));
        model.set("change_amount", this.getPara("model.change_amount"));
        model.set("change_ratio", this.getPara("model.change_ratio"));
        model.set("turnover_volume", this.getPara("model.turnover_volume"));
        model.set("turnover_money", this.getPara("model.turnover_money"));
        model.set("create_time", this.getPara("model.create_time"));
        model.update();
        this.renderSuccess();
    }

    public void delete() {
        Integer[] ids = this.getParaValuesToInt("id[]");
        Integer[] var5 = ids;
        int var4 = ids.length;

        for(int var3 = 0; var3 < var4; ++var3) {
            Integer id = var5[var3];
            ((StockHistoryLog)(new StockHistoryLog()).set("id", id)).delete();
        }

        this.renderSuccess();
    }

    public void detailPage() {
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

        List<Record> list = DBTool.findByMultPropertiesDbSource("zcurd_busi", "stock_history_log", properties, symbols, values);
        List<String> headers = new ArrayList();
        List<String> clomuns = new ArrayList();
        headers.add("日期");
        clomuns.add("dt");
        headers.add("股票代码");
        clomuns.add("code");
        headers.add("名称");
        clomuns.add("name");
        headers.add("收盘价");
        clomuns.add("closing_price");
        headers.add("最高价");
        clomuns.add("top_price");
        headers.add("最低价");
        clomuns.add("minimum_price");
        headers.add("开盘价");
        clomuns.add("opening_price");
        headers.add("前收盘");
        clomuns.add("pre");
        headers.add("涨跌额");
        clomuns.add("change_amount");
        headers.add("涨跌幅");
        clomuns.add("change_ratio");
        headers.add("成交量");
        clomuns.add("turnover_volume");
        headers.add("成交金额");
        clomuns.add("turnover_money");
        headers.add("创建时间");
        clomuns.add("create_time");
        CsvRender csvRender = new CsvRender(headers, list);
        csvRender.clomuns(clomuns);
        csvRender.fileName("股票历史数据");
        this.render(csvRender);
    }
}
