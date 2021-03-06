package cn.flaty.NettyPush.core;

import java.util.Date;
import java.util.concurrent.atomic.AtomicInteger;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import cn.flaty.NettyPush.model.ClientInfo;
import cn.flaty.NettyPush.server.conn.NettyConnection;

/**
 * 
 * 报文分发逻辑处理，对收到的报文进行分发，如，心跳，签到，普通报文等
 * 
 * @author flatychen
 *
 */
@Service
public class ClientDispacherService extends ConnPoolService {

	private static Logger log = LoggerFactory.getLogger(ClientDispacherService.class);
	
	private  AtomicInteger clients = new AtomicInteger(0);
	

	/**
	 * 客户端报文分发
	 * @param conn
	 * @param msg
	 */
	public void dispacher(NettyConnection conn, String msg) {
		this.validateAndSave(conn,msg);
//	//	GenericMessage m = FastJsonUtils.praseToObject(msg,
//				GenericMessage.class);
//		int commond = m.getCommond();
//		switch (commond) {
//		// 新连接
//		case ConnPoolService.CONN_NEW:
//			this.validateAndSave(conn,m.getMessage());
//			break;
//		// 心跳	
//		case ConnPoolService.CONN_KEEP_HEART:
//			this.keepAlive(conn, m.getMessage());
//			break;
//		default:
//			log.warn("----> invalid commond type ");
//			break;
//		}
		
	}

	/**
	 * 新连接保存
	 * 
	 * @param conn
	 * @param message
	 */
	private void validateAndSave(NettyConnection conn, String message) {
		int _clients = clients.incrementAndGet();
		log.info("连接总数： {}",_clients);
		ClientInfo client = new ClientInfo();
		client.setCid(new Date().getTime()+"");
		pool.set(client.getCid(), conn);
	}


	
	
}
