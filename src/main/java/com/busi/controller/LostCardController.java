//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.busi.controller;

import com.busi.model.LostCard;
import com.jfinal.plugin.activerecord.Record;
import com.zcurd.common.DBTool;
import com.zcurd.common.util.StringUtil;
import com.zcurd.controller.BaseController;
import com.zcurd.ext.render.csv.CsvRender;
import com.zcurd.model.SysUser;
import java.util.ArrayList;
import java.util.List;

public class LostCardController extends BaseController {
    public LostCardController() {
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

        List<Record> list = DBTool.findByMultPropertiesDbSource("zcurd_busi", "lostcard", properties, symbols, values, orderBy, this.getPager());
        this.renderDatagrid(list, DBTool.countByMultPropertiesDbSource("zcurd_busi", "lostcard", properties, symbols, values));
    }

    public void addPage() {
        this.render("add.html");
    }

    public void add() {
        LostCard lostCard = (LostCard)this.getModel(LostCard.class, "model");
        long id = (Long)this.getSessionUser().get("id");
        SysUser user = (SysUser)SysUser.me.findById(id);
        if (user != null) {
            lostCard.set("gname", user.get("user_name"));
        }

        lostCard.set("gid", id);
        if (lostCard.save()) {
            this.addOpLog("[挂失记录] 增加");
            this.renderSuccess();
        } else {
            this.renderFailed();
        }

    }

    public void updatePage() {
        this.setAttr("model", LostCard.me.findById(this.getPara("id")));
        this.render("update.html");
    }

    public void update() {
        LostCard model = (LostCard)LostCard.me.findById(this.getPara("id"));
        model.set("uid", this.getPara("model.uid"));
        model.set("uname", this.getPara("model.uname"));
        model.set("cardnum", this.getPara("model.cardnum"));
        model.set("losttime", this.getPara("model.losttime"));
        model.set("balance", this.getPara("model.balance"));
        model.update();
        this.addOpLog("[挂失记录] 修改");
        this.renderSuccess();
    }

    public void delete() {
        Integer[] ids = this.getParaValuesToInt("id[]");
        Integer[] var5 = ids;
        int var4 = ids.length;

        for(int var3 = 0; var3 < var4; ++var3) {
            Integer id = var5[var3];
            ((LostCard)(new LostCard()).set("id", id)).delete();
        }

        this.addOpLog("[挂失记录] 删除");
        this.renderSuccess();
    }

    public void detailPage() {
        LostCard model = (LostCard)LostCard.me.findById(this.getParaToInt("id"));
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

        List<Record> list = DBTool.findByMultPropertiesDbSource("zcurd_busi", "lostcard", properties, symbols, values);
        List<String> headers = new ArrayList();
        List<String> clomuns = new ArrayList();
        headers.add("用户编号");
        clomuns.add("uid");
        headers.add("用户姓名");
        clomuns.add("name");
        headers.add("用户卡号");
        clomuns.add("cardnum");
        headers.add("挂失时间");
        clomuns.add("losttime");
        headers.add("剩余次数");
        clomuns.add("balance");
        CsvRender csvRender = new CsvRender(headers, list);
        csvRender.clomuns(clomuns);
        csvRender.fileName("挂失记录");
        this.addOpLog("[挂失记录] 导出cvs");
        this.render(csvRender);
    }
}
