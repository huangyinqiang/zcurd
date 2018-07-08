//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.busi.model;

import com.jfinal.plugin.activerecord.Model;
import java.util.List;

public class QrMatchDevice extends Model<QrMatchDevice> {
    private static final long serialVersionUID = 1L;
    public static final QrMatchDevice me = new QrMatchDevice();

    public QrMatchDevice() {
    }

    public List<QrMatchDevice> findAll() {
        return this.find("select * from qr_match_device order by createtime asc");
    }

    public List<QrMatchDevice> findByGid(long gid) {
        return this.find("select * from qr_match_device where gid=" + gid + " order by createtime asc");
    }

    public List<QrMatchDevice> findInGid(String gid) {
        return this.find("select * from qr_match_device where gid in (" + gid + ") order by createtime asc");
    }
}
