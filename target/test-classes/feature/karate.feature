Feature: API sample Tests

Background:
* url 'https://restful-booker.herokuapp.com'

Scenario: Token Generation
  Given url 'https://restful-booker.herokuapp.com/auth'
  And request { username: 'admin', password: 'password123' }
  When method POST
  Then status 200

  
  * def token = response.token
  

Scenario: GET Request
Given path '/booking/{bookingid}'
And def bookingid = 1
And header Authorization = 'Bearer ' + token
When method GET
Then status 200

Scenario: POST Request
Given path '/booking'
And request { "firstname": "radhe", "lastname": "maske", "totalprice": 100, "depositpaid": true, "bookingdates": { "checkin": "2024-02-25", "checkout": "2024-02-26" }, "additionalneeds": "Breakfast" }
And header Authorization = 'Bearer ' + token
When method POST
Then status 200

Scenario: PUT Request
Given path '/booking/1'
And request { "firstname": "radhe", "lastname": "maske", "totalprice": 200, "depositpaid": true, "bookingdates": { "checkin": "2024-02-26", "checkout": "2024-02-27" }, "additionalneeds": "Dinner" }
And header Authorization = 'Bearer ' + token
When method PUT
Then status 200

Scenario: PATCH Request
Given path '/booking/1'
And request { "firstname": "radhe" }
And header Authorization = 'Bearer ' + token
When method PATCH
Then status 200

Scenario: DELETE Request
Given path '/booking/1'
And header Authorization = 'Bearer ' + token
When method DELETE
Then status 200