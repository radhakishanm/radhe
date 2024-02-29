Feature: API sample Tests

Background:
* url 'https://restful-booker.herokuapp.com'


  

Scenario: GET Request
Given path '/booking/4'
And header Accept = 'application/json'
When method GET
Then status 200

Scenario: POST Request
Given path '/booking'
And header Accept = 'application/json'
And header Content-Type = 'application/json'
And request { "firstname": "radhe", "lastname": "maske", "totalprice": 100, "depositpaid": true, "bookingdates": { "checkin": "2024-02-25", "checkout": "2024-02-26" }, "additionalneeds": "Breakfast" }
When method POST
Then status 200

Scenario: PUT Request
Given path '/booking/4'
And header Authorization = 'Basic YWRtaW46cGFzc3dvcmQxMjM='
And header Accept = 'application/json'
And header Content-Type = 'application/json'
And request { "firstname": "radhe", "lastname": "maske", "totalprice": 200, "depositpaid": true, "bookingdates": { "checkin": "2024-02-26", "checkout": "2024-02-27" }, "additionalneeds": "Dinner" }
When method PUT
Then status 200

Scenario: PATCH Request
Given path '/booking/4'
And header Authorization = 'Basic YWRtaW46cGFzc3dvcmQxMjM='
And header Accept = 'application/json'
And header Content-Type = 'application/json'
And request { "firstname": "radhe" }
When method PATCH
Then status 200

Scenario: DELETE Request
Given path '/booking/4'
And header Authorization = 'Basic YWRtaW46cGFzc3dvcmQxMjM='
And header Content-Type = 'application/json'
When method DELETE
Then status 201