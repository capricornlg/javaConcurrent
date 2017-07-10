package com.primeton.activemq;

import java.util.concurrent.TimeUnit;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnectionFactory;

public class Sender {

	public static void main(String[] args) throws Exception {
		//第一步，建立ConnectionFactory工厂对象，需要填入用户名和密码，以及连接的，均使用默认即可，默认端口为："tcp://localhost:61616"
		ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(ActiveMQConnectionFactory.DEFAULT_USER,
				                                                            ActiveMQConnectionFactory.DEFAULT_PASSWORD, 
				                                                            "tcp://localhost:61616");
		Connection connection = connectionFactory.createConnection();
		connection.start();
		
		Session session = connection.createSession(Boolean.FALSE, Session.AUTO_ACKNOWLEDGE);
		
		Destination destination = session.createQueue("myAvtiveMq");
		
		MessageProducer producer = session.createProducer(null);
		
		for(int i = 0;i < 100;i++) {
			TextMessage msg = session.createTextMessage("生产者消息内容：" + i);
			producer.send(destination,msg);
			TimeUnit.SECONDS.sleep(1);
		}
	}

}
