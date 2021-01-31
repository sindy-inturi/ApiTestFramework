package Tests;

import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import org.testng.*;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterClass;
import org.testng.asserts.SoftAssert;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import pojo.Comment;
import pojo.Photo;
import pojo.Post;
import pojo.User;
import utilities.BasicUtils;

import com.fasterxml.jackson.core.*;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.type.CollectionType;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.relevantcodes.extentreports.LogStatus;

import Enums.PostProperty;
import Enums.UserProperty;
import apiEngine.Endpoints;
import java.lang.reflect.Method;

public class FirstTest extends BaseTest {

	
	@BeforeClass
	public synchronized void beforeClass() {
		endPointObject = new Endpoints();
		
	}
	
	@BeforeMethod
	public synchronized void beforeMethod(Method method) {
		BaseTest.startTest(method.getName());
	}
	
	@Test(description = "ValidateEmailIdOnCommentsTest")
	public void ValidateEmailIdOnCommentsTest() {
		
		SoftAssert softAssert = new SoftAssert();

		try {
			User[] user = endPointObject.GetUser(UserProperty.USERNAME, "Delphine");
			System.out.println("userName-->" + user[0].getUsername());

			Assert.assertTrue(user[0].getUsername().equals("Delphine"));

			Post[] posts = endPointObject.GetPosts(PostProperty.USERID, user[0].getId().toString());

			for (Post p : posts) {

				Comment[] comments = endPointObject.GetCommentsOnAPost(p.getId());
				for (Comment c : comments) {
					System.out.println(c.getEmail());

					boolean validationResult = BasicUtils.ValidateEmail(c.getEmail());

					if (!validationResult) {
						System.out.println(c.getEmail() + "--> This is incorrect format");
						test.log(LogStatus.FAIL, "Email format is invalid");
					} else {
						test.log(LogStatus.PASS, "Valid Email format");

					}
					softAssert.assertTrue(validationResult);
				}
			}
			softAssert.assertAll();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			test.log(LogStatus.FAIL, "Exception has occured-->" + e.getMessage());
			e.printStackTrace();
		}
	}
	
	@Test(description = "ValidatePhotosAreNotCorrupted")
	public void ValidatePhotosAreNotCorrupted()
	{
		
		SoftAssert softAssert = new SoftAssert();
		Photo[] photos = endPointObject.GetAllPhotos("76");
		
		for(Photo p:photos)
		{
			String url=p.getUrl();
			boolean result= endPointObject.IsPhotoCorrupt(url);
			System.out.println(result+"--->"+url);

			if (!result) {
				
				test.log(LogStatus.FAIL, "Photo is corrupt ");
			} else {
				test.log(LogStatus.PASS, "Valid photo");

			}

			softAssert.assertTrue(result, "The photo is corrupt");
		}

	}

}
