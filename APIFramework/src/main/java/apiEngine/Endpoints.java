package apiEngine;


import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import pojo.Comment;
import pojo.Photo;
import pojo.Post;
import pojo.User;

import org.apache.logging.log4j.*;

import com.fasterxml.jackson.core.*;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.type.CollectionType;
import com.fasterxml.jackson.databind.type.TypeFactory;

import enums.PostProperty;
import enums.UserProperty;

public class Endpoints implements IEndpoints{

	private final RequestSpecification request;
	
	static final Logger logger = LogManager.getLogger(Endpoints.class);

	public Endpoints() {
		
		RestAssured.baseURI = BASE_URL;
		request = RestAssured.given();
	}
	
	
	public User[] GetUser(UserProperty property,String value)
	{
	
        Response response= request.queryParam(property.toString().toLowerCase(), value).get("/users");
 
        System.out.println(response.asString());
        logger.info(response.getStatusCode());
		return response.getBody().as(User[].class);
       
		
	}
	
	public Post[] GetPosts(PostProperty property,String value)
	{
	
        Response response= request.queryParam(property.toString(), value).get("/posts");
        System.out.println(response.asString());
        logger.info(response.getStatusCode());
        return response.getBody().as(Post[].class);
        
	}
	
	public Comment[] GetCommentsOnAPost(String postId)
	{
		
		Response response= request.get("/posts/"+postId+"/comments");
	    logger.info(response.getStatusCode());
		return response.getBody().as(Comment[].class);
		
	}
	
	public Photo[] GetAllPhotos(String albumId) {
		Response response= request.get("albums/"+albumId+"/photos");
	    logger.info(response.getStatusCode());
		return response.getBody().as(Photo[].class);
	}
	
	public boolean IsPhotoCorrupt(String url)
	
	{
		Response response= request.get(url);
	    logger.info(response.getStatusCode());
		return isSuccessful(response);
		
	}
	
	public boolean isSuccessful(Response response) {
		int code = response.getStatusCode();
		if( code == 200 || code == 201 || code == 202 || code == 203 || code == 204 || code == 205) return true;
		return false;
	}
}
