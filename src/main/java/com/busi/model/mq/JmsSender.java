//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.busi.model.mq;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageProducer;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.Topic;
import org.apache.activemq.pool.PooledConnection;

public class JmsSender {
    private String name;
    private Session session;
    private MessageProducer producer;

    public JmsSender(String name, PooledConnection connection, Destination type, String subject) throws JMSException {
        this.name = name;
        this.session = connection.createSession(false, 1);
        if (type.equals(Destination.Topic)) {
            Topic destination = this.session.createTopic(subject);
            this.producer = this.session.createProducer(destination);
        } else {
            Queue destination = this.session.createQueue(subject);
            this.producer = this.session.createProducer(destination);
        }

        this.producer.setDeliveryMode(1);
    }

    public void close() {
        try {
            this.producer.close();
        } catch (JMSException var2) {
            System.out.println(var2.getMessage());
        }

    }

    public String getName() {
        return this.name;
    }

    public Session getSession() {
        return this.session;
    }

    public void sendMessage(Message message) throws JMSException {
        this.producer.send(message);
    }
}
