package com.jsc.javageeks.exception.mapper;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import com.jsc.javageeks.application.StatusCode;
import com.jsc.javageeks.domain.Status;

@Provider
public class GenericExceptionMapper implements ExceptionMapper<Throwable> {

	public Response toResponse(Throwable exception) {
		Status status = new Status(exception.getMessage(), StatusCode.INTERNAL_SERVER_ERROR);
		return Response.status(javax.ws.rs.core.Response.Status.INTERNAL_SERVER_ERROR).entity(status).build();
	}

}
