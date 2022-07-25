package com.testapi.tests;

import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.path.json.JsonPath;
import org.testng.Assert;
import org.testng.annotations.*;
import io.restassured.response.Response;

import static com.testapi.services.Endpoints.*;
import static com.testapi.constants.ResponseCodes.*;
import static io.restassured.RestAssured.*;
import static com.testapi.utils.Formatter.*;

public class GetCallTest extends BaseTest {

    @Test(priority = 0, description = "Get user by ID")
    public void getUsersByIDTest() {
        int userID = 2;
        Response response = given().filter(new RequestLoggingFilter())
                .when().pathParam("id", userID).get(USER_BY_ID)
                .then().extract().response();
        logResponseInReport(response);
        JsonPath jsonPath = convertResponseToJson(response);
        Assert.assertEquals(response.statusCode(), SUCCESS_STATUS_CODE);
        Assert.assertEquals(jsonPath.getInt("data.id"), userID);
        Assert.assertEquals(jsonPath.getString("data.first_name"), "Janet");
        Assert.assertEquals(jsonPath.getString("data.last_name"), "Weaver");
        Assert.assertEquals(jsonPath.getString("data.email"), "janet.weaver@reqres.in");
    }

    @Test(priority = 1, description = "Get User List By Page Number")
    public void getUserListByPageTest() {
        int pageNumber = 2;
        Response response = given().filter(new RequestLoggingFilter())
                .queryParam("page", pageNumber).when().get(USERS)
                .then().extract().response();
        logResponseInReport(response);
        JsonPath jsonPath = convertResponseToJson(response);
        Assert.assertEquals(response.statusCode(), SUCCESS_STATUS_CODE);
        Assert.assertEquals(jsonPath.getInt("page"), pageNumber);
        Assert.assertEquals(jsonPath.getInt("per_page"), 6);
        Assert.assertEquals(jsonPath.getInt("data[0].id"), 7);
        Assert.assertEquals(jsonPath.getString("data[0].email"), "michael.lawson@reqres.in");
        Assert.assertEquals(jsonPath.getString("data[0].first_name"), "Michael");
        Assert.assertEquals(jsonPath.getString("data[0].last_name"), "Lawson");
    }

    @Test(priority = 6, description = "User not found user ID")
    public void userNotFoundTest() {
        int userID = 23;
        Response response = given().filter(new RequestLoggingFilter())
                .when().pathParam("id", userID).get(USER_BY_ID)
                .then().extract().response();
        Assert.assertEquals(response.statusCode(), NOT_FOUND_STATUS_CODE);
    }
}
