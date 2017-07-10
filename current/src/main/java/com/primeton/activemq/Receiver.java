package com.primeton.activemq;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.MessageConsumer;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnectionFactory;

public class Receiver {

	public static void main(String[] args) throws Exception {
		// 第一步，建立ConnectionFactory工厂对象，需要填入用户名和密码，以及连接的，均使用默认即可，默认端口为："tcp://localhost:61616"
		ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(ActiveMQConnectionFactory.DEFAULT_USER,
				ActiveMQConnectionFactory.DEFAULT_PASSWORD, "tcp://localhost:61616");
		Connection connection = connectionFactory.createConnection();
		connection.start();

		Session session = connection.createSession(Boolean.FALSE, Session.AUTO_ACKNOWLEDGE);

		Destination destination = session.createQueue("myAvtiveMq");

		MessageConsumer consumer = session.createConsumer(destination);
		
		while(true) {
			TextMessage msg = (TextMessage) consumer.receive();
			System.out.println("消费数据:-->" + msg.getText());
		}
	}

}
