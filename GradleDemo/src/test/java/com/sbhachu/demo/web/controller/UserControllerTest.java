package com.sbhachu.demo.web.controller;

import com.jayway.restassured.response.Response;
import com.sbhachu.demo.models.UserModel;
import com.sbhachu.demo.util.MD5EncoderUtil;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.HttpHeaders;

import static com.jayway.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by sbhachu on 23/11/2014.
 */
public class UserControllerTest {

    private UserModel userModel;

    @Before
    public void setUp() throws Exception {

    }

    @Test
    public void givenNotAuthenticated_whenUserListRequested_then401isReceived() {
        // given

        // when
        final Response response = given().header(HttpHeaders.ACCEPT, "application/json")
                .get("http://localhost:8080/api/v1/users");

        // then
        assertThat(response.getStatusCode()).isEqualTo(401);
    }

    @Test
    public void givenAuthenticated_whenUserListRequested_then200isReceived() {
        // given

        // when
        final Response response = given().auth().preemptive()
                .basic("manager", MD5EncoderUtil.encode("password"))
                .header(HttpHeaders.ACCEPT, "application/json")
                .get("http://localhost:8080/api/v1/users");

        // then
        assertThat(response.getStatusCode()).isEqualTo(200);
    }

    @Test
    public void givenNullUsername_whenRegisterUserRequested_then400isReceived() {
        // given
        UserModel userModel = new UserModel();
        userModel.setUsername(null);
        userModel.setPassword(MD5EncoderUtil.encode("password"));
        userModel.setEmail("junit_user@gradledemo.com");

        // when
        final Response response = given().contentType("application/json")
                .body(userModel)
                .header(HttpHeaders.ACCEPT, "application/json")
                .when()
                .post("http://localhost:8080/api/v1/user/create");

        // then
        assertThat(response.getStatusCode()).isEqualTo(400);
    }
}
