package com.paul.queue;

import java.util.Calendar;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

public class Consumer {
	private static String url = ActiveMQConnection.DEFAULT_BROKER_URL;
	private static String subject = "HELLOWORLD";

	public static void main(String[] args) {
		ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(url);
		Connection connection;
		try {
			connection = connectionFactory.createConnection();
			connection.start();
			
			Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
			
			Destination destination = session.createQueue(subject);
			
			MessageConsumer consumer = session.createConsumer(destination);
			
			Message message = consumer.receive();
			
			if(message instanceof TextMessage) {
				TextMessage textMessage = (TextMessage) message;
				System.out.println("Receive: " + textMessage.getText() + " " + Calendar.getInstance().getTime());
			}
			session.close();
			connection.close();
		} catch (JMSException e) {
			e.printStackTrace();
		}
		
		
	}

}
