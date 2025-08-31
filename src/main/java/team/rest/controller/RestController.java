package team.rest.controller;

import io.restassured.common.mapper.TypeRef;
import io.restassured.http.ContentType;

import static io.restassured.RestAssured.given;

public class RestController {

    private static final String BASE_URL = "http://localhost:8080";

    public <B, R> R postMethod(String endpoint, B body, Class<R> responseType) {

        return given()
                .contentType(ContentType.JSON)
                .when()
                .log().all()
                .body(body)
                .baseUri(BASE_URL)
                .post(endpoint)
                .then()
                .log().all()
                .extract().as(responseType);
    }

    public <B, R> R patchMethod(String endpoint, B body, Class<R> responseType) {

        return given()
                .contentType(ContentType.JSON)
                .when()
                .log().all()
                .body(body)
                .baseUri(BASE_URL)
                .patch(endpoint)
                .then()
                .log().all()
                .extract().as(responseType);
    }

    public void deleteMethod(String endpoint, String params) {

        given()
                .contentType(ContentType.JSON)
                .when()
                .log().all()
                .baseUri(BASE_URL)
                .delete(endpoint + params)
                .then()
                .log().all();
    }

    public <T> T getMethod(String endpoint, String params, Class<T> responseType) {

        return given()
                .contentType(ContentType.JSON)
                .when()
                .log().all()
                .baseUri(BASE_URL)
                .get(endpoint + params)
                .then()
                .log().all()
                .extract().as(responseType);
    }

    public <T> T getMethod(String endpoint, TypeRef<T> responseType) {

        return given()
                .contentType(ContentType.JSON)
                .when()
                .log().all()
                .baseUri(BASE_URL)
                .get(endpoint)
                .then()
                .log().all()
                .extract().as(responseType);
    }
}
