$(document).ready(function() {var formatter = new CucumberHTML.DOMFormatter($('.cucumber-report'));formatter.uri("com/ruscorporation/tests/createUser.feature");
formatter.feature({
  "line": 1,
  "name": "Create new User",
  "description": "",
  "id": "create-new-user",
  "keyword": "Feature"
});
formatter.scenario({
  "line": 2,
  "name": "Create new User to users list",
  "description": "",
  "id": "create-new-user;create-new-user-to-users-list",
  "type": "scenario",
  "keyword": "Scenario"
});
formatter.step({
  "line": 3,
  "name": "empty User list",
  "keyword": "Given "
});
formatter.step({
  "line": 4,
  "name": "administrator call create new User",
  "keyword": "When "
});
formatter.step({
  "line": 5,
  "name": "user list not empty",
  "keyword": "Then "
});
formatter.match({
  "location": "UserBDDTests.getEmptyUserList()"
});
formatter.result({
  "duration": 321428103,
  "status": "passed"
});
formatter.match({
  "location": "UserBDDTests.addUser()"
});
formatter.result({
  "duration": 43928,
  "status": "passed"
});
formatter.match({
  "location": "UserBDDTests.checkUserList()"
});
formatter.result({
  "duration": 41054,
  "status": "passed"
});
});