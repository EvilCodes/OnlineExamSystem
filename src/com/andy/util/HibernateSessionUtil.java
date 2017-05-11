package com.andy.util;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

final public class HibernateSessionUtil {

	private static SessionFactory sessionFactory=null;
	//�ֲ߳̾�ģʽ
	private static ThreadLocal<Session> threadLocal=new ThreadLocal<Session>();
	private HibernateSessionUtil(){}
	static{
		sessionFactory=new Configuration().configure().buildSessionFactory();
	}
	public static Session openSession()
	{
		return sessionFactory.openSession(); 
	}
	//��ȡ���̹߳�����session
	public static Session getCurrentSession()
	{
		Session session=threadLocal.get();
		//�ж��ǲ��ǵĵõ���
		if(session==null){
			session=sessionFactory.openSession();
			//��session�������õ�threadLocal
			threadLocal.set(session);
		}
		return session;
	}
}
