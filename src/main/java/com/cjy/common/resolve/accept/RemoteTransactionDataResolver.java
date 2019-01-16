package com.cjy.common.resolve.accept;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import com.cjy.common.data.TxAttributesContent;

@Component
public class RemoteTransactionDataResolver {
	
	private static final Logger LOG = LoggerFactory.getLogger(RemoteTransactionDataResolver.class);
	
	@Autowired
	ApplicationContext context;
	
	/**
	 * 初始化远程事务信息
	 */
	public void init() {
		//初始化当前容器
		TxAttributesContent.initContainer();
		String[] accepterNames = context.getBeanNamesForType(RemoteTransactionAccepter.class);
		if(accepterNames != null && accepterNames.length > 0) {
			for(int i = 0 ; i < accepterNames.length ; i++) {
				LOG.info("load {} " , accepterNames[i]);
				RemoteTransactionAccepter accepter = (RemoteTransactionAccepter) context.getBean(accepterNames[i]);
				accepter.acceptRemoteTransactionData();
			}
		}
	}
	
}
