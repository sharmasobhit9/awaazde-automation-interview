Feature: Sunscreens
  @Smoke
  Scenario: Shopping
    Given user navigates to application
    Then verify the task
    And Pay With Card
    Then Enter email "sharmasobhit9@gmail.com"
    And Enter your Card Number "4242 4242 4242 4242"
    And Enter Expire Date and Year "21/21"
    And Enter card cvv "222"
    And Enter the zipcode "42344"
    Then pay the bill