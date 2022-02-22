Feature: the version can be retrieved
  Scenario: client makes call to GET /version
  	Given the following customers
  	|customerId |customerName|
  	|1			|Anamay		 |
  	|2			|choudhary   |
    When the client calls 
    Then the client receives list of customers