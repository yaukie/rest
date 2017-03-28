package org.yaukie.server;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.cxf.jaxrs.JAXRSServerFactoryBean;
import org.apache.cxf.jaxrs.lifecycle.ResourceProvider;
import org.apache.cxf.jaxrs.lifecycle.SingletonResourceProvider;
import org.yaukie.CustomerServiceImpl;

import com.fasterxml.jackson.jaxrs.json.JacksonJsonProvider;

/**
 * 
 * @author yaukie
 *	 CXF 服务端
 */
public class RestServer {
	
	private static void publish(){
		System.out.println("REST服务开始发布！"+new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date().getTime()));
		//添加ResourceClass
		List<Class<?>> resourceClass = new ArrayList<Class<?>>();
		resourceClass.add(CustomerServiceImpl.class);
		//to-do
		
		//添加Resourceprovider
		List<ResourceProvider> resourceProviderList = new ArrayList<ResourceProvider>();
		resourceProviderList.add(new SingletonResourceProvider(new CustomerServiceImpl()));
		//to-do
		
		//添加Provider
		List<Object> providerList = new ArrayList<Object>();
		providerList.add(new JacksonJsonProvider());
		
		//发布rest服务
		JAXRSServerFactoryBean jaxr = new JAXRSServerFactoryBean();
		jaxr.setAddress("http://localhost:8080/ws/rest");
		jaxr.setResourceClasses(resourceClass);
		jaxr.setResourceProviders(resourceProviderList);
		jaxr.setProviders(providerList);
		jaxr.create();
		System.out.println("REST服务发布成功！"+new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date().getTime()));
	}
	
	
	public static void main(String[] args) {
		publish();
	}
}
