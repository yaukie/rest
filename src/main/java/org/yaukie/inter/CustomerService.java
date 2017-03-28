package org.yaukie.inter;

import java.util.List;
import java.util.Map;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.yaukie.entity.Customer;
/**
 * 
 * @author yaukie
 * CXF架构下的接口注解
 * JAX-RS注解实现
 */
@Produces({MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON})
public interface CustomerService {

	@GET
	@Path("/customers")
	@Produces(MediaType.APPLICATION_JSON)
	String  getAllCustomers();
	
	@GET
	@Path("/customer/{id}")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.APPLICATION_JSON)
	Customer getCustomerByName(@PathParam("id") int id);
	
	@POST
	@Path("/addCustomer")
	@Consumes(MediaType.APPLICATION_JSON)
	String createCustomer(Customer customer);
	
	@PUT
	@Path("/customer/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	Customer updateCustomerByName(@PathParam("id") int id,Map<String,Object> fieldMap);
	
	@DELETE
	@Path("/delete/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	Response  deleteCustomerByName(@PathParam("id") int id );
	
}
