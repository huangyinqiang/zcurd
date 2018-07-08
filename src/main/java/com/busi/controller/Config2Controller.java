//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.busi.controller;

import com.busi.model.Config;
import com.jfinal.plugin.activerecord.Record;
import com.zcurd.common.DBTool;
import com.zcurd.common.util.StringUtil;
import com.zcurd.controller.BaseController;
import com.zcurd.ext.render.csv.CsvRender;
import java.util.ArrayList;
import java.util.List;

public class Config2Controller extends BaseController {
    public Config2Controller() {
    }

    public void listPage() {
        long gid = (Long)this.getSessionUser().get("id");
        if (gid == 1L) {
            this.render("list.html");
        } else {
            this.render("list1.html");
        }

    }

    public void listPage1() {
        this.render("list2.html");
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

        List<Record> list = DBTool.findByMultPropertiesDbSource("zcurd_busi", "config", properties, symbols, values, orderBy, this.getPager());
        this.renderDatagrid(list, DBTool.countByMultPropertiesDbSource("zcurd_busi", "config", properties, symbols, values));
    }

    public void listData1() {
        Object[] queryParams = this.getQueryParams();
        String[] properties = (String[])queryParams[0];
        String[] symbols = (String[])queryParams[1];
        Object[] values = (Object[])queryParams[2];
        String orderBy = this.getOrderBy();
        if (StringUtil.isEmpty(orderBy)) {
            orderBy = "id desc";
        }

        List<Record> list = DBTool.findByMultPropertiesDbSource("zcurd_busi", "config", properties, symbols, values, orderBy, this.getPager());
        this.renderDatagrid(list, DBTool.countByMultPropertiesDbSource("zcurd_busi", "config", properties, symbols, values));
    }

    public void addPage() {
        long gid = (Long)this.getSessionUser().get("id");
        if (gid == 1L) {
            this.render("add.html");
        } else {
            this.render("add1.html");
        }

    }

    public void add() {
        ((Config)this.getModel(Config.class, "model")).save();
        this.addOpLog("[秘钥配置] 增加");
        this.renderSuccess();
    }

    public void updatePage() {
        long gid = (Long)this.getSessionUser().get("id");
        this.setAttr("model", Config.me.findById(this.getPara("id")));
        if (gid == 1L) {
            this.render("update.html");
        } else {
            this.render("update1.html");
        }

    }

    public void update() {
        Config model = (Config)Config.me.findById(this.getPara("id"));
        long gid = (Long)this.getSessionUser().get("id");
        String str = "";
        if (gid == 1L) {
            model.set("keya1value", this.getPara("model.keya1value"));
            model.set("keya2value", this.getPara("model.keya2value"));
            model.set("remark", this.getPara("model.remark"));
            str = "keya1value:" + this.getPara("model.keya1value");
            str = str + " keya2value:" + this.getPara("model.keya2value");
        }

        model.set("keyb1value", this.getPara("model.keyb1value"));
        str = str + "keyb1value" + this.getPara("model.keyb1value");
        model.set("keyb2value", this.getPara("model.keyb2value"));
        str = str + "keyb2value" + this.getPara("model.keyb2value");
        model.update();
        this.addOpLog("[秘钥配置] 修改 :" + str);
        this.renderSuccess();
    }

    public void delete() {
        Integer[] ids = this.getParaValuesToInt("id[]");
        Integer[] var5 = ids;
        int var4 = ids.length;

        for(int var3 = 0; var3 < var4; ++var3) {
            Integer id = var5[var3];
            ((Config)(new Config()).set("id", id)).delete();
        }

        this.addOpLog("[秘钥配置] 删除");
        this.renderSuccess();
    }

    public void detailPage() {
        Config model = (Config)Config.me.findById(this.getParaToInt("id"));
        this.setAttr("model", model);
        this.render("detail.html");
    }

    public void initPage() {
        long gid = (Long)this.getSessionUser().get("id");
        List<Config> model = Config.me.find("select * from config where gid=" + gid);
        this.setAttr("model", model);
        this.render("init.html");
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

        List<Record> list = DBTool.findByMultPropertiesDbSource("zcurd_busi", "config", properties, symbols, values);
        List<String> headers = new ArrayList();
        List<String> clomuns = new ArrayList();
        headers.add("秘钥名称");
        clomuns.add("key");
        headers.add("秘钥数值");
        clomuns.add("value");
        headers.add("备注");
        clomuns.add("remark");
        CsvRender csvRender = new CsvRender(headers, list);
        csvRender.clomuns(clomuns);
        csvRender.fileName("秘钥配置");
        this.addOpLog("[秘钥配置] 导出cvs");
        this.render(csvRender);
    }
}
