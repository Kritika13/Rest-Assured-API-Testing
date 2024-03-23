package api.endpoints;

import static io.restassured.RestAssured.given;

import api.payload.User;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

//to perform CRUD requests , user API

public class userEndPoint {
	
	public static Response createUser(User payload)
	{
		Response response=given()
			.contentType(ContentType.JSON)
			.accept(ContentType.JSON)
			.body(payload)
		.when()
			.post(routes.post_url);
			
			return response;
	}

	public static Response readUser(String userName) {
		
		Response response=given ()
				.pathParam ("username", userName)
				.when ()
					.get(routes.get_url);
		return response;
		
	}
	
	public static Response updateUser(String userName, User payload) {
		Response response=given()
					.contentType(ContentType.JSON)
					.accept (ContentType.JSON)
					.pathParam("username", userName)
					.body (payload)
				.when ()
				.put(routes.update_url);
		return response;
	}
	
	
public static Response deteleUser(String userName) {
		
				Response response=given ()
				.pathParam ("username", userName)
			.when ()
					.delete(routes.delete_url);
		return response;
		
	}
	
}
