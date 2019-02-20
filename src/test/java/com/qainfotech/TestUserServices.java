package com.qainfotech;
import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import java.util.ArrayList;

import static io.restassured.RestAssured.*;

public class TestUserServices {
	
	private static final String baseURI = "http://localhost:8080/rest/userservices";
	
	@Test
	public void testUserRegistration() {
		given().formParam("name", "u").formParam("email", "u@g.c").formParam("password", "u").when().post(baseURI+"/register").then().statusCode(201);
	}
	
	@Test
	public void testUserLogin() {
		given().formParam("email", "p@g.c").formParam("password", "p").when().post(baseURI+"/login").then().statusCode(200);
	}
	
	@Test
	public void testAddComment() {
		given().formParam("email", "p@g.c").formParam("comment", "hello").when().post(baseURI+"/addcomment").then().statusCode(200);
	}
	
	@Test
	public void testShowUserComments() {
		given().queryParam("email", "p@g.c").when().get(baseURI+"/showusercomments").then().statusCode(200);
	}
	
	@Test
	public void testShowAllComments() {
		given().when().get(baseURI+"/showallcomments").then().statusCode(200);
	}

}
