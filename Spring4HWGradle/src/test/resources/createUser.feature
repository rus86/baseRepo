Feature: Create new User
Scenario: Create new User to users list
Given empty User list
When administrator call create new User
Then user list not empty