package tests;

import io.qameta.allure.restassured.AllureRestAssured;
import models.lombok.LoginBodyLombokModel;
import models.lombok.LoginResponseLombokModel;
import models.pojo.LoginBodyPojoModel;
import models.pojo.LoginResponsePojoModel;
import org.junit.jupiter.api.Test;

import static helpers.CustomApiListener.withCustomTemplates;
import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static org.assertj.core.api.Assertions.assertThat;
import static specs.LoginSpecs.loginRequestSpec;
import static specs.LoginSpecs.loginResponseSpec;

public class ReqresInExtendedTests {

    @Test
    void loginWithPojoModelTest() {
        LoginBodyPojoModel body = new LoginBodyPojoModel();
        body.setEmail("eve.holt@reqres.in");
        body.setPassword("cityslicka");

        LoginResponsePojoModel response = given()
                .log().all()
                .contentType(JSON)
                .body(body)
                .when()
                .post("https://reqres.in/api/login")
                .then()
                .log().all()
                .statusCode(200)
                .extract().as(LoginResponsePojoModel.class);

        assertThat(response.getToken()).isEqualTo("QpwL5tke4Pnpja7X4");
    }

    @Test
    void loginWithLombokModelTest() {
        LoginBodyLombokModel body = new LoginBodyLombokModel();
        body.setEmail("eve.holt@reqres.in");
        body.setPassword("cityslicka");

        LoginResponseLombokModel response = given()
                .log().all()
                .contentType(JSON)
                .body(body)
                .when()
                .post("https://reqres.in/api/login")
                .then()
                .log().all()
                .statusCode(200)
                .extract().as(LoginResponseLombokModel.class);

        assertThat(response.getToken()).isEqualTo("QpwL5tke4Pnpja7X4");
    }

    @Test
    void loginWithAllureListenerTest() {
        LoginBodyLombokModel body = new LoginBodyLombokModel();
        body.setEmail("eve.holt@reqres.in");
        body.setPassword("cityslicka");

        LoginResponseLombokModel response = given()
                .filter(new AllureRestAssured())
                .log().all()
                .contentType(JSON)
                .body(body)
                .when()
                .post("https://reqres.in/api/login")
                .then()
                .log().all()
                .statusCode(200)
                .extract().as(LoginResponseLombokModel.class);

        assertThat(response.getToken()).isEqualTo("QpwL5tke4Pnpja7X4");
    }

    @Test
    void loginWithCustomAllureListenerTest() {
        LoginBodyLombokModel body = new LoginBodyLombokModel();
        body.setEmail("eve.holt@reqres.in");
        body.setPassword("cityslicka");

        LoginResponseLombokModel response = given()
                .filter(withCustomTemplates())
                .log().all()
                .contentType(JSON)
                .body(body)
                .when()
                .post("https://reqres.in/api/login")
                .then()
                .log().all()
                .statusCode(200)
                .extract().as(LoginResponseLombokModel.class);

        assertThat(response.getToken()).isEqualTo("QpwL5tke4Pnpja7X4");
    }

    @Test
    void loginWithSpecsTest() {
        LoginBodyLombokModel body = new LoginBodyLombokModel();
        body.setEmail("eve.holt@reqres.in");
        body.setPassword("cityslicka");

        LoginResponseLombokModel response = given()
                .spec(loginRequestSpec)
                .body(body)
                .when()
                .post()
                .then()
                .spec(loginResponseSpec)
                .extract().as(LoginResponseLombokModel.class);

        assertThat(response.getToken()).isEqualTo("QpwL5tke4Pnpja7X4");
    }

}


