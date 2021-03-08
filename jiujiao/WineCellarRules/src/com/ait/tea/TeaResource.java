
package com.ait.tea;

import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.ait.validation.CheckVintageExists;
import com.ait.validation.ErrorMessage;
import com.ait.validation.TeaValidationException;
import com.ait.validation.TeaValidator;
import com.ait.validation.TeaVintageExistsException;

@Path("/teas")
public class TeaResource {

	
	TeaValidator teaValidator = new TeaValidator();
	ConnectionHelper conHelper = new ConnectionHelper();
	public TeaDAO teaDao = new TeaDAO(conHelper);
	CheckVintageExists checkVintageExists = new CheckVintageExists();

	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	public Response findAll(Tea tea) {
		System.out.println("invoking GET");
		// return dao.findAll();
		String name = tea.getName();
		List<Tea> teas = new ArrayList<Tea>();
		if(name != null && !"".equals(name)){
			teas.addAll(teaDao.findByName(name));
		}else{
			teas.addAll(teaDao.findAll());
		}
		return Response.status(200).entity(teas).build();
	}

	@POST
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON })
	public Response create(Tea tea) {
		Response response;
		try {
			List<Tea> teas = teaDao.findAll();
			teaValidator.validateTea(tea);
			checkVintageExists.checkForVintage(tea, teas);
			teaDao.create(tea);
			response = Response.status(200).entity(tea).build();
		} catch (TeaValidationException e) {
			ErrorMessage errorMessage = new ErrorMessage(e.getMessage());
			response = Response.status(403).entity(errorMessage).build();
		}
	    catch (TeaVintageExistsException e) {
		ErrorMessage errorMessage = new ErrorMessage(e.getMessage());
		response = Response.status(403).entity(errorMessage).build();
	}
		return response;
	}
	
	@PUT
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON })
	public Response update(Tea tea) {
		Response response;
		teaDao.update(tea);
		response = Response.status(200).entity(tea).build();
		return response;
	}
	
	@DELETE
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON })
	public Response delete(Tea tea) {
		Response response;
		teaDao.delete(tea.getId());
		response = Response.status(200).entity(tea).build();
		return response;
	}

}
