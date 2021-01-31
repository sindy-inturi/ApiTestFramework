package Tests;

import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import org.testng.*;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.AfterClass;
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
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import Enums.PostProperty;
import Enums.UserProperty;
import apiEngine.Endpoints;
import groovyjarjarantlr.Utils;

public class FirstTest extends BaseTest {
	
	@BeforeTest
	public void ApiPreConditions() {
		endPointObject = new Endpoints();
		BaseTest.startTest(getClass().getSimpleName());
	}

	@Test
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

}
