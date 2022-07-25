package com.testapi.tests;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import static com.testapi.constants.ResponseCodes.*;
import static com.testapi.services.Endpoints.*;
import static io.restassured.RestAssured.*;
import static com.testapi.resources.Payload.*;
import static com.testapi.utils.Formatter.*;

public class PostCallTest extends BaseTest {

    @Test(priority = 2, description = "Create User")
    public void createUserTest() {
        String user = "Verma Sonali";
        String job = "Tester";

        Response response = given()
                .body(createUpdateUserPayload(user, job))
                .contentType(ContentType.JSON)
                .when().post(USERS)
                .then().extract().response();
        logResponseInReport(response);
        JsonPath jsonPath = convertResponseToJson(response);
        Assert.assertEquals(response.statusCode(), CREATED_STATUS_CODE);
        Assert.assertEquals(jsonPath.getString("name"), user);
        Assert.assertEquals(jsonPath.getString("job"), job);
        Assert.assertNotNull(jsonPath.getString("id"));
        Assert.assertNotNull(jsonPath.getString("createdAt"));
    }

    @Test(priority = 2, description = "Create Invalid User")
    public void createInvalidUserTest() {
        String user = "kdjvakbsdjgashcvjabkjsgdjkasgasbxdijkasgdxhuasfaxfdgss";

        Response response = given()
                .body(user)
                .contentType(ContentType.JSON)
                .when().post(USERS)
                .then().extract().response();
        logResponseInReport(response);
        JsonPath jsonPath = convertResponseToJson(response);
        Assert.assertEquals(response.statusCode(), BAD_STATUS_CODE);
    }
}