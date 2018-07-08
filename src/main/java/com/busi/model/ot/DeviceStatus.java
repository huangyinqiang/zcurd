//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.busi.model.ot;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class DeviceStatus {
    private String deviceId;
    private String name;
    private boolean isOnLine;
    private Date joinTime;
    private Date endTime;
    private Map<String, Boolean> portStatus = new HashMap();

    public DeviceStatus() {
    }

    public DeviceStatus(String type) {
        String tempStr = "";
        int i;
        if (type.equals("L")) {
            for(i = 1; i <= 6; ++i) {
                if (i < 10) {
                    tempStr = "0" + i;
                } else {
                    tempStr = "" + i;
                }

                this.portStatus.put(tempStr, false);
            }
        } else {
            for(i = 1; i <= 12; ++i) {
                if (i < 10) {
                    tempStr = "0" + i;
                } else {
                    tempStr = "" + i;
                }

                this.portStatus.put(tempStr, false);
            }
        }

    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Map<String, Boolean> getPortStatus() {
        return this.portStatus;
    }

    public void setPortStatus(Map<String, Boolean> portStatus) {
        this.portStatus = portStatus;
    }

    public void setPort(String key, Boolean value) {
        if (this.portStatus.containsKey(key)) {
            this.portStatus.remove(key);
            this.portStatus.put(key, value);
        } else {
            this.portStatus.put(key, value);
        }

    }

    public boolean getPort(String key) {
        return this.portStatus.containsKey(key) ? (Boolean)this.portStatus.get(key) : false;
    }

    public String getDeviceId() {
        return this.deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public boolean isOnLine() {
        return this.isOnLine;
    }

    public void setOnLine(boolean isOnLine) {
        this.isOnLine = isOnLine;
    }

    public Date getJoinTime() {
        return this.joinTime;
    }

    public void setJoinTime(Date joinTime) {
        this.joinTime = joinTime;
    }

    public Date getEndTime() {
        return this.endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }
}
