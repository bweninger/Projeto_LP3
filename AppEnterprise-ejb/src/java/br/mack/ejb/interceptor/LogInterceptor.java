package br.mack.ejb.interceptor;

import javax.annotation.Resource;
import javax.ejb.SessionContext;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;
import javax.jms.Connection;
import javax.jms.Queue;
import javax.jms.QueueConnection;
import javax.jms.QueueConnectionFactory;
import javax.jms.QueueSender;
import javax.jms.QueueSession;
import javax.jms.Session;
import javax.jms.TextMessage;

@Interceptor
public class LogInterceptor {
	
	@Resource
	private SessionContext sessionContext;
	
	@Resource(name = "java:comp/DefaultJMSConnectionFactory")
	private QueueConnectionFactory qcf;

	@Resource(name = "java:/jms/queue/eventQueue")
	private Queue queue;

	@AroundInvoke
	public Object log(InvocationContext context) throws Exception {
		System.out.println("---" + context.getMethod());
		QueueConnection conn = qcf.createQueueConnection();
		conn.start();
		QueueSession session = conn.createQueueSession(true, Session.SESSION_TRANSACTED);
		TextMessage msg = session.createTextMessage();
		msg.setText(context.getMethod().getDeclaringClass().getSimpleName() + ";" + context.getMethod().getName() + ";" + 
				sessionContext.getCallerPrincipal().getName());
		QueueSender queueSender = session.createSender(queue);
		queueSender.send(msg);		
		return context.proceed();
	}
}