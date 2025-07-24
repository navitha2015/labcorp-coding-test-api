package com.labcorp.utils;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.util.HashMap;
import java.util.Map;

public class ApiHelper {

    private String baseUrl;
    private Map<String, String> queryParams;
    private String requestBody;
    private RequestSpecification requestSpec;

    public ApiHelper() {
        this.queryParams = new HashMap<>();
        setupRequestSpecification();
    }

    private void setupRequestSpecification() {
        this.requestSpec = RestAssured.given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .log().all();
    }

    public void setBaseUrl(String baseUrl) {
        this.baseUrl = baseUrl;
        RestAssured.baseURI = baseUrl;
    }

    public void addQueryParameter(String key, String value) {
        this.queryParams.put(key, value);
    }

    public void setRequestBody(String body) {
        this.requestBody = body;
    }

    public Response sendGetRequest(String endpoint) {
        RequestSpecification request = requestSpec;

        if (!queryParams.isEmpty()) {
            request = request.queryParams(queryParams);
        }

        return request.when()
                .get(endpoint)
                .then()
                .log().all()
                .extract()
                .response();
    }

    public Response sendPostRequest(String endpoint) {
        RequestSpecification request = requestSpec;

        if (!queryParams.isEmpty()) {
            request = request.queryParams(queryParams);
        }

        if (requestBody != null && !requestBody.isEmpty()) {
            request = request.body(requestBody);
        }

        return request.when()
                .post(endpoint)
                .then()
                .log().all()
                .extract()
                .response();
    }

    public Response sendPutRequest(String endpoint) {
        RequestSpecification request = requestSpec;

        if (!queryParams.isEmpty()) {
            request = request.queryParams(queryParams);
        }

        if (requestBody != null && !requestBody.isEmpty()) {
            request = request.body(requestBody);
        }

        return request.when()
                .put(endpoint)
                .then()
                .log().all()
                .extract()
                .response();
    }

    public Response sendDeleteRequest(String endpoint) {
        RequestSpecification request = requestSpec;

        if (!queryParams.isEmpty()) {
            request = request.queryParams(queryParams);
        }

        return request.when()
                .delete(endpoint)
                .then()
                .log().all()
                .extract()
                .response();
    }

    public void clearQueryParams() {
        this.queryParams.clear();
    }

    public void clearRequestBody() {
        this.requestBody = null;
    }

    public void reset() {
        clearQueryParams();
        clearRequestBody();
        setupRequestSpecification();
    }
}