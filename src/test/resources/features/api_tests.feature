Feature: API Testing with Beeceptor
  As a QA Engineer
  I want to test GET and POST API endpoints
  So that I can validate the API responses and data integrity

  Background:
    Given the API base URL is "https://echo.free.beeceptor.com"

  @get-request
  Scenario: Validate GET request response
    Given I have a GET endpoint "/sample-request"
    And I add query parameter "author" with value "beeceptor"
    When I send a GET request
    Then the response status code should be 200
    And the response should contain field "path"
    And the response should contain field "ip"
    And the response should contain all headers
    And the response time should be less than 5000 milliseconds

  @post-request
  Scenario: Validate POST request with order data
    Given I have a POST endpoint "/sample-request"
    And I add query parameter "author" with value "beeceptor"
    And I load request body from "order.json"
    When I send a POST request
    Then the response status code should be 200
    And the customer information should be validated
    And the payment details should be accurate
    And the product information should be correct
    And the order total should match the expected amount

  @post-request-validation
  Scenario: Validate specific fields in POST response
    Given I have a POST endpoint "/sample-request"
    And I add query parameter "author" with value "beeceptor"
    And I have a valid order payload
    When I send a POST request
    Then the response should contain customer name "Jane Smith"
    And the response should contain customer email "janesmith@example.com"
    And the response should contain order id "12345"
    And the response should contain payment method "credit_card"
    And the response should contain transaction id "txn_67890"
    And the response should contain total amount 111.97