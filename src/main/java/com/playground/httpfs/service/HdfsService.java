package com.playground.httpfs.service;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * 
 * @author Guruprasad
 * class to wrap CRUD operations on hdfs
 *
 */
@Path("/directory")
public class HdfsService {
	
//	private static final String URL_PREFIX = "curl -i \"http://quickstart.cloudera:14000/webhdfs/v1/?user.name=";
	private static final String URL_PREFIX = "http://quickstart.cloudera:14000/webhdfs/v1/?user.name=";

	private static final String URL_SUFFIX = "&op=liststatus";
	/**
	 * 
	 * @param userId
	 * @return JSON
	 * method retunrns the user's hdfs home directory
	 */
	@GET
	@Path("/{userId}")
	@Produces(MediaType.APPLICATION_JSON)
	public String getDirectory(@PathParam("userId") String userId) {
		String restUrl = URL_PREFIX + userId + URL_SUFFIX; 
		System.out.println("URL is : " + restUrl);
		return RestClientService.getDataFromUrl(restUrl) ;
	}
}
