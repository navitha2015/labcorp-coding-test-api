package com.labcorp.utils;

import io.restassured.response.Response;

public class TestContext {

    private static TestContext instance;
    private Response response;
    private String testData;

    private TestContext() {}

    public static TestContext getInstance() {
        if (instance == null) {
            instance = new TestContext();
        }
        return instance;
    }

    public Response getResponse() {
        return response;
    }

    public void setResponse(Response response) {
        this.response = response;
    }

    public String getTestData() {
        return testData;
    }

    public void setTestData(String testData) {
        this.testData = testData;
    }

    public void reset() {
        this.response = null;
        this.testData = null;
    }
}