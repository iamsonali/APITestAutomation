package com.testapi.tests;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import static com.testapi.constants.ResponseCodes.*;
import static com.testapi.resources.Payload.*;
import static com.testapi.services.Endpoints.*;
import static com.testapi.utils.Formatter.*;
import static io.restassured.RestAssured.given;

public class PutCallTest extends BaseTest {

    @Test(priority = 3, description = "Update user by ID PUT")
    public void updateUserPutTest() {
        int userID = 2;

        String user = "Verma Sonali";
        String job = "SDET";

        Response response = given()
                .pathParam("id", userID)
                .body(createUpdateUserPayload(user, job))
                .contentType(ContentType.JSON)
                .when().put(USER_BY_ID)
                .then().extract().response();
        logResponseInReport(response);
        JsonPath jsonPath = convertResponseToJson(response);
        Assert.assertEquals(response.statusCode(), SUCCESS_STATUS_CODE);
        Assert.assertEquals(jsonPath.getString("name"), user);
        Assert.assertEquals(jsonPath.getString("job"), job);
        Assert.assertNotNull(jsonPath.getString("updatedAt"));
    }
}