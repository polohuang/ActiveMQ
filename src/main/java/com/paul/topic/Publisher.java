package com.paul.topic;

import java.util.Calendar;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.jms.Topic;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

public class Publisher {
	public static void main(String[] args) {
	
		ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(ActiveMQConnection.DEFAULT_BROKER_URL);
		try {
			Connection connection = connectionFactory.createConnection();
			connection.setClientID("Paul");
			Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
			Topic topic = session.createTopic("HelloWorld");
			MessageProducer producer = session.createProducer(topic);
			TextMessage message = session.createTextMessage("Hello World!");
			producer.send(message);
			System.out.println("Sent: " + message.getText() + " " + Calendar.getInstance().getTime());
			session.close();
			connection.close();
		} catch (JMSException e) {
			e.printStackTrace();
		}
	
	}
}
