package api.test;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import api.endpoints.userEndPoint;
import api.payload.User;
import io.restassured.response.Response;

public class UserTest {
	
	Faker faker;
	User userPayload;
	
	@BeforeClass
	
	public void setupData() {
		faker=new Faker();
		userPayload=new User();
		
		//generating data using faker from pojo class
		userPayload.setId(faker.idNumber().hashCode()); //generating our own id
		userPayload.setUsername(faker.name().username());
		userPayload.setFirstName(faker.name().firstName());
		userPayload.setLastName(faker.name().lastName());
		userPayload.setEmail(faker.internet().safeEmailAddress());
		userPayload.setPassword(faker.internet().password(5,10));
		userPayload.setPhone(faker.phoneNumber().cellPhone());
	}
	
	@Test(priority=1)
	public void testPostUser(){
		Response response=userEndPoint.createUser(userPayload);
		response.then().log().all();
		
		Assert.assertEquals(response.getStatusCode(), 200);
		//add other validations  -- json response, json data validations etc
		//do manual testing in postman and add other validate other responses here
	}

	@Test(priority = 2)
	public void testGetUserByName() {
		
		Response response= userEndPoint.readUser(this.userPayload.getUsername());// this is used to get the same user name which is generated
		response.then().log().all();
		Assert.assertEquals(response.getStatusCode(), 200);
	}
	
	@Test(priority = 3)
	public void testUpdateUserByName() {
		
		//update data using payload -- regenerating the values , new first name, last name and email
		userPayload.setFirstName(faker.name().firstName());
		userPayload.setLastName(faker.name().lastName());
		userPayload.setEmail(faker.internet().safeEmailAddress());
		
		Response response=userEndPoint.updateUser(this.userPayload.getUsername(),userPayload);
		response.then().log().body();
		
		Assert.assertEquals(response.getStatusCode(), 200);
		
		//checking data after update -- extracting data using the same user name and checking status code, check response body as well
		Response responseAfterUpdate= userEndPoint.readUser(this.userPayload.getUsername());
		Assert.assertEquals(responseAfterUpdate.getStatusCode(), 200);
		
	}
	

	@Test(priority = 4)
	public void testDeleteUserByName() {
		
		Response response=userEndPoint.deteleUser(this.userPayload.getUsername());
		Assert.assertEquals(response.getStatusCode(), 200);
	}
	
}






















