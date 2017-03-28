package org.yaukie.client;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.cxf.jaxrs.client.WebClient;
import org.yaukie.entity.Customer;

import com.fasterxml.jackson.jaxrs.json.JacksonJsonProvider;

/**
 * JAXR各个时代 调用方法
 * @author yaukie
 *
 */
public class RestClient {
public static void main(String[] args) {
	String baseAddress = "http://localhost:8080/ws/rest";
    JacksonJsonProvider jacksonProvider = new JacksonJsonProvider();
	//1.0时代写法
    List<Object> providerList = new ArrayList<Object>();
    List<Customer> cusList = null;
    providerList.add(jacksonProvider);
//    CustomerService service = JAXRSClientFactory.create(baseAddress, CustomerService.class, providerList);
//    cusList= service.getAllCustomers();
//    //2.0时代写法
//    cusList = ClientBuilder.newClient().register(providerList).target(baseAddress)
//    		.path("/customer")
//    		.request(MediaType.APPLICATION_JSON)
//    		.get(new GenericType<List<Customer>>(){});
    //通用写法
    String cus =WebClient.create(baseAddress, providerList)
    		.path("/customers")
    		.accept(MediaType.APPLICATION_JSON)
    		.type(MediaType.APPLICATION_JSON)
    		.get(new GenericType<String>(){});
    System.out.println(cus);
}
}
