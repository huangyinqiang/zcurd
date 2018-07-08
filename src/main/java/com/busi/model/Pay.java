//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.busi.model;

import com.jfinal.plugin.activerecord.Model;

public class Pay extends Model<Pay> {
    private static final long serialVersionUID = 1L;
    public static final Pay me = new Pay();
    private int gid;
    private String gname;
    private String openid;
    private String nickname;
    private String money;
    private String reUserName;
    private int status;
    private String rate;
    private String realmoney;
    private String desc;

    public Pay() {
    }

    public String getRealmoney() {
        return this.realmoney;
    }

    public void setRealmoney(String realmoney) {
        this.realmoney = realmoney;
    }

    public String getRate() {
        return this.rate;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }

    public String getOpenid() {
        return this.openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public int getGid() {
        return this.gid;
    }

    public void setGid(int gid) {
        this.gid = gid;
    }

    public String getGname() {
        return this.gname;
    }

    public void setGname(String gname) {
        this.gname = gname;
    }

    public String getNickname() {
        return this.nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getMoney() {
        return this.money;
    }

    public void setMoney(String money) {
        this.money = money;
    }

    public String getReUserName() {
        return this.reUserName;
    }

    public void setReUserName(String reUserName) {
        this.reUserName = reUserName;
    }

    public int getStatus() {
        return this.status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getDesc() {
        return this.desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
