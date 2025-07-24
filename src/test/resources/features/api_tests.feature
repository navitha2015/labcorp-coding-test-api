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
    And I have the following order payload:
      """
      {
        "order_id": "12345",
        "customer": {
          "name": "Jane Smith",
          "email": "janesmith@example.com",
          "phone": "1-987-654-3210",
          "address": {
            "street": "456 Oak Street",
            "city": "Metropolis",
            "state": "NY",
            "zipcode": "10001",
            "country": "USA"
          }
        },
        "items": [
          {
            "product_id": "A101",
            "name": "Wireless Headphones",
            "quantity": 1,
            "price": 79.99
          },
          {
            "product_id": "B202",
            "name": "Smartphone Case",
            "quantity": 2,
            "price": 15.99
          }
        ],
        "payment": {
          "method": "credit_card",
          "transaction_id": "txn_67890",
          "amount": 111.97,
          "currency": "USD"
        },
        "shipping": {
          "method": "standard",
          "cost": 5.99,
          "estimated_delivery": "2024-11-15"
        },
        "order_status": "processing",
        "created_at": "2024-11-07T12:00:00Z"
      }
      """
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