//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.busi.model.mq;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.jms.Topic;
import org.apache.activemq.pool.PooledConnection;

public class JmsReceiver implements MessageListener {
    private String name;
    private Session session;
    private MessageConsumer consumer;

    public JmsReceiver(String name, PooledConnection connection, Destination type, String subject) throws JMSException {
        this.name = name;
        this.session = connection.createSession(false, 1);
        if (type.equals(Destination.Topic)) {
            Topic destination = this.session.createTopic(subject);
            this.consumer = this.session.createConsumer(destination);
        } else {
            Queue destination = this.session.createQueue(subject);
            this.consumer = this.session.createConsumer(destination);
        }

        this.consumer.setMessageListener(this);
    }

    public String getName() {
        return this.name;
    }

    public void close() {
        try {
            this.consumer.close();
        } catch (JMSException var2) {
            System.out.println(var2.getMessage());
        }

    }

    public void onMessage(Message message) {
        try {
            if (message instanceof TextMessage) {
                TextMessage msg = (TextMessage)message;
                String textStr = msg.getText();
                if (textStr == null || "".equals(textStr) || !textStr.startsWith("{")) {
                    System.out.println("Error Message Data Format...");
                    return;
                }

                System.out.println("TextMessage " + msg.getText());
            } else {
                System.out.println("other... " + message);
            }
        } catch (Exception var4) {
            var4.printStackTrace();
        }

    }
}
