package com.restful.resources;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Application;

@Path("/Zip")
@Produces("text/plain")
@ApplicationPath("/rest")
public class ZipInfo extends Application {
// private static final String[] String = null;

@GET
 @Path("{zip}")
@Produces("text/plain")
 public String getStateCity(@PathParam("zip") String zipcode) {
	 if("22030".equals(zipcode)) {
		 String str1 = "Fairfax,VA";
		 //System.out.println(str);
		 return(str1);
		 }
	else if ("22312".equals(zipcode)) {
			 String str2="Alexandria,VA";
			 return str2;
		 }
	else if("22301".equals(zipcode)) {
		String str3="Tysons Corner,MD";
		return str3;
	}
	else if("20148".equals(zipcode)) {
		String str4="Ashburn,VA";
		return str4;
	}
	else
	 return null;
 }
 
}
