package org.yaukie;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;


import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.yaukie.entity.Customer;
import org.yaukie.inter.CustomerService;
import org.yaukie.util.JsonUtil;
/**
 * 
 * @author yaukie
 * 接口实现 
 */
public class CustomerServiceImpl implements CustomerService {
	private static Log log = LogFactory.getLog(CustomerServiceImpl.class);
	private static List<Customer> list = new ArrayList<Customer>();
	
	static
	{
		list.add(new Customer(1,"xxx1"));
		list.add(new Customer(2,"xxx2"));
	}
	
	public String getAllCustomers() {
		Collections.sort(list ,new Comparator(){
			public int compare(Object o1, Object o2) {
				Customer cust1 =(Customer)o1;
				Customer cust2 =(Customer)o2;
				return (cust1.getId()>cust2.getId())?1:-1;
			}
		});
		return JsonUtil.object2json(list);
	}

	public Customer getCustomerByName(int id) {
		Customer cus = null;
		for(Customer customer : list )
		{
			if(id==customer.getId())
			{
				cus = customer;
				break;
			}
		}
		return cus;
	}

	public String createCustomer(Customer customer) {
		customer.setId(new Random().nextInt()* 10 +1);
		list.add(customer);
		return JsonUtil.object2json(list);
	}

	public Customer updateCustomerByName(int id , Map<String, Object> fieldMap) {
		Customer target = this.getCustomerByName(id);
		if(target !=null )
		{
			for(Map.Entry<String, Object> iter : fieldMap.entrySet())
			{
				try {
					Field field = Customer.class.getDeclaredField(iter.getKey());
					field.setAccessible(true);
					field.set(target,iter.getValue());
				} catch (SecurityException e) {
					e.printStackTrace();
				} catch (NoSuchFieldException e) {
					e.printStackTrace();
				} catch (IllegalArgumentException e) {
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				}
			}
		}
		return target;
		
	}

	public Response  deleteCustomerByName(int id) {
		Customer cus = this.getCustomerByName(id);
		if(cus == null )
	 {
			return Response.status(Status.BAD_REQUEST).build();
	 }
	 else
	 {
		 list.remove(cus);
		 log.debug("删除成功！"+list.size());
		 return Response.ok().build();
	 }
	}
 
}
