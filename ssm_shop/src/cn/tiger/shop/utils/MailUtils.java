package cn.tiger.shop.utils;

import java.util.Properties;

import javax.mail.Address;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.Message.RecipientType;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 * 邮件发送工具
 * @author tiger
 *
 */
public class MailUtils {
	
	/**
	 * 发送邮件的方法
	 * @param to	接收人
	 * @param code	激活码
	 */
	public static void sendMail(String to,String code) {
		/*
		 * 1. 创建session对象
		 * 2. 创建邮件对象Message
		 * 3.发送邮件Transport
		 */
		//1. 创建session对象
		Properties props = new Properties();
		props.setProperty("mail.host", "localhost");
		Session session = Session.getInstance(props, new Authenticator(){

			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				// TODO Auto-generated method stub
				return new PasswordAuthentication("service@tg.com","111");
			}
		});
		//2. 创建邮件对象Message
		Message message = new MimeMessage(session);
		try {
			//设置发件人
			message.setFrom(new InternetAddress("service@tg.com"));
			//设置收件人
			// 抄送 CC   密送BCC
			message.setRecipient(RecipientType.TO, new InternetAddress(to));
			//设置邮件标题
			message.setSubject("来自tiger网上购物商场的一份电子激活邮件！");
			//设置邮件内容
			message.setContent("<h1>tiger网上购物商场激活邮件!点下面链接完成激活操作!</h1><h3><a href='http://localhost:8080/ssm_shop/user/active.action?code="+code+"'>http://localhost:8080/ssm_shop/user/active.action?code="+code+"</a></h3>","text/html;charset=UTF-8");
			//3.发送邮件Transport
			Transport.send(message);
		} catch (AddressException e) {
			e.printStackTrace();
		} catch (MessagingException e) {
			e.printStackTrace();
		}
	}
}
