Feature: Sample API Tests

Background:
* url 'https://restful-booker.herokuapp.com'

Scenario: GET Request
Given path '/booking/1'
When method GET
Then status 200

Scenario: POST Request
Given path '/booking'
And request { "firstname": "John", "lastname": "Doe", "totalprice": 100, "depositpaid": true, "bookingdates": { "checkin": "2024-02-22", "checkout": "2024-02-23" }, "additionalneeds": "Breakfast" }
When method POST
Then status 200

Scenario: PUT Request
Given path '/booking/1'
And request { "firstname": "Jane", "lastname": "Doe", "totalprice": 200, "depositpaid": true, "bookingdates": { "checkin": "2024-02-23", "checkout": "2024-02-24" }, "additionalneeds": "Dinner" }
When method PUT
Then status 200

Scenario: PATCH Request
Given path '/booking/1'
And request { "firstname": "Jane" }
When method PATCH
Then status 200

Scenario: DELETE Request
Given path '/booking/1'
When method DELETE
Then status 200