//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.busi.controller;

import com.busi.model.Tuser;
import com.busi.model.TuserCharge;
import com.jfinal.plugin.activerecord.Record;
import com.zcurd.common.DBTool;
import com.zcurd.common.util.StringUtil;
import com.zcurd.controller.BaseController;
import com.zcurd.ext.render.csv.CsvRender;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TuserController extends BaseController {
    public TuserController() {
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

        List<Record> list = DBTool.findByMultPropertiesDbSource("zcurd_busi", "tuser", properties, symbols, values, orderBy, this.getPager());
        this.renderDatagrid(list, DBTool.countByMultPropertiesDbSource("zcurd_busi", "tuser", properties, symbols, values));
    }

    public void addPage() {
        this.render("add.html");
    }

    public void updatePage() {
        this.setAttr("model", Tuser.me.findById(this.getPara("id")));
        this.render("update.html");
    }

    public void update() {
        Tuser model = (Tuser)Tuser.me.findById(this.getPara("id"));
        int id = (Integer)model.get("id");
        int last = model.getInt("walletAccount");
        String name = (String)model.get("nickName");
        model.set("nickName", this.getPara("model.nickName"));
        String chargeWall = this.getPara("model.walletAccount");
        float chargeTmp = Float.parseFloat(chargeWall);
        model.set("walletAccount", chargeTmp * 100.0F);
        TuserCharge tCharge = new TuserCharge();
        tCharge.set("tuserid", id);
        tCharge.set("name", name);
        tCharge.set("charge", chargeTmp);
        tCharge.set("last", last);
        tCharge.set("jointime", new Date());
        tCharge.save();
        model.update();
        this.addOpLog("[在线用户] 修改");
        this.renderSuccess();
    }

    public void detailPage() {
        Tuser model = (Tuser)Tuser.me.findById(this.getParaToInt("id"));
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

        List<Record> list = DBTool.findByMultPropertiesDbSource("zcurd_busi", "tuser", properties, symbols, values);
        List<String> headers = new ArrayList();
        List<String> clomuns = new ArrayList();
        headers.add("昵称");
        clomuns.add("nickName");
        headers.add("头像");
        clomuns.add("headimgurl");
        headers.add("城市");
        clomuns.add("city");
        headers.add("省会");
        clomuns.add("province");
        headers.add("手机号码");
        clomuns.add("tel");
        headers.add("注册时间");
        clomuns.add("registerDate");
        headers.add("最后登录时间");
        clomuns.add("lastLoginDate");
        headers.add("卡号");
        clomuns.add("cardNumber");
        headers.add("是否绑定手机(Y:绑定 N:未绑定)");
        clomuns.add("band");
        headers.add("钱包");
        clomuns.add("walletAccount");
        CsvRender csvRender = new CsvRender(headers, list);
        csvRender.clomuns(clomuns);
        csvRender.fileName("在线用户");
        this.addOpLog("[在线用户] 导出cvs");
        this.render(csvRender);
    }
}
