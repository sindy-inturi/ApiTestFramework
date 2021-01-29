package Tests;

import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import org.testng.*;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import pojo.Comment;
import pojo.Post;
import pojo.User;
import utilities.BasicUtils;

import com.fasterxml.jackson.core.*;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.type.CollectionType;
import com.fasterxml.jackson.databind.type.TypeFactory;

import Enums.PostProperty;
import Enums.UserProperty;
import apiEngine.Endpoints;
import groovyjarjarantlr.Utils;

public class FirstTest {

	private Endpoints endPointObject;
	
	@BeforeTest
	public void ApiPreConditions() {
		endPointObject=new Endpoints();
	}
	
	@Test
	public void ValidateEmailIdOnCommentsTest() throws JsonMappingException, JsonProcessingException {
		
		
		SoftAssert softAssert = new SoftAssert();

		User[] user= endPointObject.GetUser(UserProperty.USERNAME, "Delphine");
		System.out.println("userName-->" + user[0].getUsername());

		Assert.assertTrue(user[0].getUsername().equals("Delphine"));

		Post[] posts =endPointObject.GetPosts(PostProperty.USERID, user[0].getId().toString());

		for (Post p : posts) {

			Comment[] comments = endPointObject.GetCommentsOnAPost(p.getId());
			for (Comment c : comments) {
				System.out.println(c.getEmail());
				
				boolean validationResult = BasicUtils.ValidateEmail(c.getEmail());
				
				if (!validationResult) {
					System.out.println(c.getEmail() + "--> This is incorrect format");
				}				
				softAssert.assertTrue(validationResult);
			}
		}
		softAssert.assertAll();
	}

	@Test
	public void GetAllPostsByAUser() throws JsonMappingException, JsonProcessingException {

		String baseUrl = "https://jsonplaceholder.typicode.com";
		RestAssured.baseURI = baseUrl;

		// getting user
		RequestSpecification request = RestAssured.given();
		Response response = request.queryParam("username", "Delphine").get("/users");

		String jsonString = response.asString();
		ObjectMapper mapper = new ObjectMapper();
		User[] user = mapper.readValue(jsonString, User[].class);

		// getting posts
		request = RestAssured.given();
		response = request.queryParam("userId", user[0].getId()).get("/posts");
		jsonString = response.asString();
		ObjectMapper mapper2 = new ObjectMapper();
		Post[] posts = mapper2.readValue(jsonString, Post[].class);

		for (Post p : posts) {
			System.out.println(p.getId() + "***" + p.getTitle());
		}

	}

	@Test
	public void ValidateEmail() throws JsonMappingException, JsonProcessingException {

		String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\." + "[a-zA-Z0-9_+&*-]+)*@" + "(?:[a-zA-Z0-9-]+\\.)+[a-z"
				+ "A-Z]{2,7}$";

		String baseUrl = "https://jsonplaceholder.typicode.com";
		RestAssured.baseURI = baseUrl;

		// getting user
		RequestSpecification request = RestAssured.given();
		Response response = request.queryParam("username", "Delphine").get("/users");

		String jsonString = response.asString();
		ObjectMapper mapper = new ObjectMapper();
		User[] user = mapper.readValue(jsonString, User[].class);

		// getting posts
		request = RestAssured.given();
		response = request.queryParam("userId", user[0].getId()).get("/posts");
		jsonString = response.asString();
		ObjectMapper mapper2 = new ObjectMapper();
		Post[] posts = mapper2.readValue(jsonString, Post[].class);

		for (Post p : posts) {
			System.out.println("*****************" + p.getTitle() + "**********************");
			request = RestAssured.given();
			response = request.get("/posts/" + p.getId() + "/comments");
			jsonString = response.asString();
			ObjectMapper mapper3 = new ObjectMapper();
			Comment[] comments = mapper3.readValue(jsonString, Comment[].class);

			for (Comment c : comments) {
				System.out.println(c.getEmail());
				String email = c.getEmail();
				Pattern pat = Pattern.compile(emailRegex);

				if (pat.matcher(email).matches()) {
					System.out.println("Email Matches the pattern");
				} else {
					System.out.println("Invalid EMail Pattern");
				}
			}
		}
	}

}
