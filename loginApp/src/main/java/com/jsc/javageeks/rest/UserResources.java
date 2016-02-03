package com.jsc.javageeks.rest;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.jsc.javageeks.application.Constant;
import com.jsc.javageeks.application.StatusCode;
import com.jsc.javageeks.domain.StatusUserCombined;
import com.jsc.javageeks.domain.User;
import com.jsc.javageeks.exception.GenericException;
import com.jsc.javageeks.service.DatabaseService;
import com.jsc.javageeks.service.impl.DatabaseServiceImpl;

import jersey.repackaged.com.google.common.collect.Lists;

@Path("user")
public class UserResources {
	DatabaseService databaseService = new DatabaseServiceImpl();

	@GET
	@Path("testApi")
	@Produces(MediaType.TEXT_PLAIN)
	public String testApi() {
		return "Api is working fine";
	}

	@POST
	@Path("authenticate")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response authenticateUser(User user) throws GenericException {
		StatusUserCombined statusUserCombined = databaseService.authenticateUser(user);
		if (statusUserCombined.getUser() == null) {
			return Response.status(Status.OK).entity(statusUserCombined.getStatus()).build();
		}
		return Response.status(Status.OK).entity(user).build();
	}

	@POST
	@Path("addUser")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response addUser(User user) throws GenericException {
		com.jsc.javageeks.domain.Status status = databaseService.addUser(user);
		return Response.status(Status.CREATED).entity(status).build();
	}

	@GET
	@Path("users")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAllUsers() throws GenericException {
		List<User> users = databaseService.getAllUsers();
		if (users.size() == 0) {
			com.jsc.javageeks.domain.Status status = new com.jsc.javageeks.domain.Status(Constant.NO_RECORD_FOUND_MSG,
					StatusCode.NO_RECORD_FOUND);
			return Response.status(Status.OK).entity(status).build();
		}
		GenericEntity<List<User>> entity = new GenericEntity<List<User>>(Lists.newArrayList(users)) {
		};
		return Response.status(Status.OK).entity(entity).build();
	}

	@GET
	@Path("users/{userId}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getUser(@PathParam("userId") int userId) throws GenericException {
		User user = databaseService.getUser(userId);
		if (user == null) {
			String message = Constant.NO_RECORD_FOUND_FOR_ID_MSG + " " + userId;
			com.jsc.javageeks.domain.Status status = new com.jsc.javageeks.domain.Status(message,
					StatusCode.NO_RECORD_FOUND);
			return Response.status(Status.OK).entity(status).build();
		}
		return Response.status(Status.OK).entity(user).build();
	}

}
