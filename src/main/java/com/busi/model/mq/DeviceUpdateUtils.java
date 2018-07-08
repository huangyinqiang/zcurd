//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.busi.model.mq;

import com.alibaba.fastjson.JSON;
import java.util.Date;
import javax.jms.JMSException;
import javax.jms.TextMessage;

public class DeviceUpdateUtils {
    public DeviceUpdateUtils() {
    }

    public static void UpdateDevice(String deviceId) {
        JmsSender sq1 = ActiveMQ.getSender("WexinSender");
        Command cmd = new Command();
        cmd.setType("initdevice");
        cmd.setDeviceId(deviceId);
        cmd.setJoinTime(new Date());

        try {
            String str = JSON.toJSONString(cmd);
            TextMessage textMsg = sq1.getSession().createTextMessage(str);
            sq1.sendMessage(textMsg);
        } catch (JMSException var5) {
            var5.printStackTrace();
        }

    }

    public static void SendCmdDevice(Command cmd) {
        JmsSender sq1 = ActiveMQ.getSender("WexinSender");
        cmd.setType("syscmd");
        cmd.setJoinTime(new Date());

        try {
            String str = JSON.toJSONString(cmd);
            TextMessage textMsg = sq1.getSession().createTextMessage(str);
            sq1.sendMessage(textMsg);
        } catch (JMSException var4) {
            var4.printStackTrace();
        }

    }
}
