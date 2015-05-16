package com.playground.httpfs.service;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/directory")
public class HdfsService {

	@GET
	@Path("/{userId}")
	@Produces(MediaType.APPLICATION_JSON)
	public String getDirectory(@PathParam("userId") String userId) {
		return "you sent " + userId ;
	}
}
