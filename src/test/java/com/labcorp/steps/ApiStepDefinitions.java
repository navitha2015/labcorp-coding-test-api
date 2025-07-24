package com.labcorp.steps;

import com.labcorp.utils.ApiHelper;
import com.labcorp.utils.TestContext;
import com.labcorp.models.OrderPayload;
import io.cucumber.java.en.*;
import io.restassured.response.Response;
import org.testng.Assert;

import static org.hamcrest.Matchers.*;

public class ApiStepDefinitions {

    private TestContext testContext;
    private ApiHelper apiHelper;
    private String baseUrl;
    private String endpoint;
    private String requestBody;
    private Response response;

    public ApiStepDefinitions() {
        this.testContext = TestContext.getInstance();
        this.apiHelper = new ApiHelper();
    }

    @Given("the API base URL is {string}")
    public void setBaseUrl(String url) {
        this.baseUrl = url;
        apiHelper.setBaseUrl(url);
    }

    @Given("I have a GET endpoint {string}")
    public void setGetEndpoint(String endpoint) {
        this.endpoint = endpoint;
    }

    @Given("I have a POST endpoint {string}")
    public void setPostEndpoint(String endpoint) {
        this.endpoint = endpoint;
    }

    @Given("I add query parameter {string} with value {string}")
    public void addQueryParameter(String paramName, String paramValue) {
        apiHelper.addQueryParameter(paramName, paramValue);
    }

    @Given("I have the following order payload:")
    public void setOrderPayload(String payload) {
        this.requestBody = payload;
        apiHelper.setRequestBody(payload);
    }

    @Given("I have a valid order payload")
    public void setValidOrderPayload() {
        OrderPayload orderPayload = new OrderPayload();
        this.requestBody = orderPayload.getValidOrderJson();
        apiHelper.setRequestBody(this.requestBody);
    }

    @When("I send a GET request")
    public void sendGetRequest() {
        response = apiHelper.sendGetRequest(endpoint);
        testContext.setResponse(response);
    }

    @When("I send a POST request")
    public void sendPostRequest() {
        response = apiHelper.sendPostRequest(endpoint);
        testContext.setResponse(response);
    }

    @Then("the response status code should be {int}")
    public void validateStatusCode(int expectedStatusCode) {
        Assert.assertEquals(response.getStatusCode(), expectedStatusCode,
                "Status code mismatch");
    }

    @Then("the response should contain field {string}")
    public void validateResponseContainsField(String fieldName) {
        response.then().body(fieldName, notNullValue());
    }

    @Then("the response should contain all headers")
    public void validateResponseContainsHeaders() {
        response.then()
                .body("headers", notNullValue())
                .body("headers.size()", greaterThan(0));
    }

    @Then("the response time should be less than {long} milliseconds")
    public void validateResponseTime(long maxTime) {
        long responseTime = response.getTime();
        Assert.assertTrue(responseTime < maxTime,
                "Response time " + responseTime + "ms exceeded maximum " + maxTime + "ms");
    }

    @Then("the customer information should be validated")
    public void validateCustomerInformation() {
        response.then()
                .body("parsedBody.customer.name", equalTo("Jane Smith"))
                .body("parsedBody.customer.email", equalTo("janesmith@example.com"))
                .body("parsedBody.customer.phone", equalTo("1-987-654-3210"))
                .body("parsedBody.customer.address.street", equalTo("456 Oak Street"))
                .body("parsedBody.customer.address.city", equalTo("Metropolis"))
                .body("parsedBody.customer.address.state", equalTo("NY"))
                .body("parsedBody.customer.address.zipcode", equalTo("10001"))
                .body("parsedBody.customer.address.country", equalTo("USA"));
    }

    @Then("the payment details should be accurate")
    public void validatePaymentDetails() {
        response.then()
                .body("parsedBody.payment.method", equalTo("credit_card"))
                .body("parsedBody.payment.transaction_id", equalTo("txn_67890"))
                .body("parsedBody.payment.amount", equalTo(111.97f))
                .body("parsedBody.payment.currency", equalTo("USD"));
    }

    @Then("the product information should be correct")
    public void validateProductInformation() {
        response.then()
                .body("parsedBody.items.size()", equalTo(2))
                .body("parsedBody.items[0].product_id", equalTo("A101"))
                .body("parsedBody.items[0].name", equalTo("Wireless Headphones"))
                .body("parsedBody.items[0].quantity", equalTo(1))
                .body("parsedBody.items[0].price", equalTo(79.99f))
                .body("parsedBody.items[1].product_id", equalTo("B202"))
                .body("parsedBody.items[1].name", equalTo("Smartphone Case"))
                .body("parsedBody.items[1].quantity", equalTo(2))
                .body("parsedBody.items[1].price", equalTo(15.99f));
    }

    @Then("the order total should match the expected amount")
    public void validateOrderTotal() {
        response.then()
                .body("parsedBody.payment.amount", equalTo(111.97f));
    }

    @Then("the response should contain customer name {string}")
    public void validateCustomerName(String expectedName) {
        response.then()
                .body("parsedBody.customer.name", equalTo(expectedName));
    }

    @Then("the response should contain customer email {string}")
    public void validateCustomerEmail(String expectedEmail) {
        response.then()
                .body("parsedBody.customer.email", equalTo(expectedEmail));
    }

    @Then("the response should contain order id {string}")
    public void validateOrderId(String expectedOrderId) {
        response.then()
                .body("parsedBody.order_id", equalTo(expectedOrderId));
    }

    @Then("the response should contain payment method {string}")
    public void validatePaymentMethod(String expectedMethod) {
        response.then()
                .body("parsedBody.payment.method", equalTo(expectedMethod));
    }

    @Then("the response should contain transaction id {string}")
    public void validateTransactionId(String expectedTransactionId) {
        response.then()
                .body("parsedBody.payment.transaction_id", equalTo(expectedTransactionId));
    }

    @Then("the response should contain total amount {double}")
    public void validateTotalAmount(double expectedAmount) {
        response.then()
                .body("parsedBody.payment.amount", equalTo((float) expectedAmount));
    }
}