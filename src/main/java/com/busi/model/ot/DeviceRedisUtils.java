//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.busi.model.ot;

import com.alibaba.fastjson.JSON;

public class DeviceRedisUtils {
    private static final String deviceStartPrex = "device:";
    private static final String configStartPrex = "config:";

    public DeviceRedisUtils() {
    }

    public static DeviceStatus getDeviceStatus(String deviceId) {
        String keyValue = "device:" + deviceId;
        String tempStr = RedisUtils.GetValue(keyValue);
        DeviceStatus deviceStatus = (DeviceStatus)JSON.parseObject(tempStr, DeviceStatus.class);
        return deviceStatus;
    }

    public static DeviceStatus initDeviceStatus(String deviceId) {
        String keyValue = "config:" + deviceId;
        String tempStr = RedisUtils.GetValue(keyValue);
        DeviceStatus deviceStatus = (DeviceStatus)JSON.parseObject(tempStr, DeviceStatus.class);
        return deviceStatus;
    }
}
