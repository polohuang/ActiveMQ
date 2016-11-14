package com.paul.topic;

import java.util.Calendar;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.jms.Topic;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

public class DurableSubscriber {

	public static void main(String[] args) {
		ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(ActiveMQConnection.DEFAULT_BROKER_URL);
		Connection connection;
		try {
			connection = connectionFactory.createConnection();
			connection.setClientID("Paul");
			Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
			Topic topic = session.createTopic("HelloWorld");
			MessageConsumer consumer = session.createDurableSubscriber(topic, "Subcriber1");
			connection.start();
			Message message = consumer.receive();
			if(message instanceof TextMessage) {
				TextMessage textMessage = (TextMessage) message;
				System.out.println("Receive: " + textMessage.getText() + " " + Calendar.getInstance().getTime());
			}
//			session.close();
//			connection.close();
		} catch (JMSException e) {
			e.printStackTrace();
		}
		
	}

}
