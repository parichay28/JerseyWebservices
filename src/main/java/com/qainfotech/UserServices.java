package com.qainfotech;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import org.json.*;

@Path("/userservices")
@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
@Produces(MediaType.APPLICATION_JSON)
public class UserServices {

	private static Map<String, UserReg> registeredUsers = new HashMap<String, UserReg>();
	private static List<String> loggedUsers = new ArrayList<String>();
	private static Map<String, UserComment> userComments = new HashMap<String, UserComment>();
	
//	{
//		registeredUsers.put("p@g.c", new UserReg("p", "p@g.c", "p"));
//		registeredUsers.put("q@g.c", new UserReg("q", "q@g.c", "q"));
//		registeredUsers.put("r@g.c", new UserReg("r", "r@g.c", "r"));
//		registeredUsers.put("s@g.c", new UserReg("s", "s@g.c", "s"));
//		registeredUsers.put("t@g.c", new UserReg("t", "t@g.c", "t"));
//		
//		loggedUsers.add("p@g.c");
//		loggedUsers.add("q@g.c");
//
//		
//		userComments.put("p@g.c", new UserComment("It's P1"));
//		userComments.get("p@g.c").setComment("It's P2");
//		userComments.get("p@g.c").setComment("It's P3");
//		
//		userComments.put("q@g.c", new UserComment("It's Q1"));
//		userComments.get("q@g.c").setComment("It's Q2");
//		userComments.get("q@g.c").setComment("It's Q3");
//		
//		userComments.put("r@g.c", new UserComment("It's R1"));
//		userComments.get("r@g.c").setComment("It's R2");
//		userComments.get("r@g.c").setComment("It's R3");
//		
//		userComments.put("s@g.c", new UserComment("It's S1"));
//		
//		userComments.put("t@g.c", new UserComment("It's T1"));
//	}
	


	@POST
	@Path("/register")
	public Response userRegistration(@FormParam("name") String name, @FormParam("email") String email,
			@FormParam("password") String password) {

		UserReg user = new UserReg(name, email, password);
		JSONObject jsonmsg = new JSONObject();
		if (registeredUsers.get(email) != null) {
			jsonmsg.append("Message", "User already registered");
			return Response.status(409).entity(jsonmsg.toString()).build();

		}

		registeredUsers.put(email, user);
		jsonmsg.append("Message", "Registration Successful");
		return Response.status(201).entity(jsonmsg.toString()).build();
	}

	@POST
	@Path("/login")
	public Response userLogin(@FormParam("email") String email, @FormParam("password") String password) {

		JSONObject jsonmsg = new JSONObject();
		if (registeredUsers.get(email) != null && 
				registeredUsers.get(email).getPassword().equals(password)) {
			loggedUsers.add(email);
			jsonmsg.append("message", "Login successful");
			return Response.status(200).entity(jsonmsg.toString()).build();
		}
		jsonmsg.append("message", "Wrong username or password");
		return Response.status(401).entity(jsonmsg.toString()).build();

	}

	@POST

	@Path("/addcomment")
	public Response addComment(@FormParam("email") String email, @FormParam("comment") String comment) {

		JSONObject jsonmsg = new JSONObject();

		if (loggedUsers.contains(email) == true) {
			
			if(userComments.get(email)!=null) {
				userComments.get(email).setComment(comment);
				jsonmsg.append("message", "User comment added successfully");
				return Response.status(200).entity(jsonmsg.toString()).build();
			}
			
			else {
				
				userComments.put(email, new UserComment(comment));
				jsonmsg.append("message", "User comment added successfully");
				return Response.status(200).entity(jsonmsg.toString()).build();
			}

		}
		jsonmsg.append("message", "Please make sure that you are logged and the Email is correct");
		return Response.status(400).entity(jsonmsg.toString()).build();

	}

	@GET
	@Path("/showusercomments")
	public Response showUserComments(@QueryParam("email") String email) {

		JSONObject jsonmsg = new JSONObject();

		if (loggedUsers.contains(email) == true) {

			UserComment comments = userComments.get(email);
			jsonmsg.put(email, comments.getComment());
			return Response.status(200).entity(jsonmsg.toString()).build();
		}
		jsonmsg.append("message", "Please make sure that you are logged and the Email is correct");
		return Response.status(401).entity(jsonmsg.toString()).build();
	}

	@GET
	@Path("/showallcomments")
	public Response showAllComments() {
		JSONObject jsonmsg = new JSONObject();
		for (Map.Entry<String, UserComment> entry : userComments.entrySet()) {
		    jsonmsg.put(entry.getKey(), entry.getValue().getComment());
		}
		return Response.status(200).entity(jsonmsg.toString()).build();
	}

}
