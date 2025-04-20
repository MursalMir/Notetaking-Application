[<h1 align="center">Week 8 - Unit Testing & Test Driven Development (TDD)</h1>

<h1 align="center">Address Book Application</h1>

![Image of Test Driven Development Cycle](/images/AdobeStock_656534510.jpeg)

> [!NOTE]
AIM: Learn the steps required to exercise TDD as the application development process
> TDD is a requirement for the project. It is a very difficult skill to implement consistently so it definitely requires practice and discipline

## Activity 1 - Preparation - Setting up the Project

Clone the repository and switch to the "sqlite" branch. You will need to install the sqlite JDBC driver as well as JUnit 5 (JUnit 5 should already be installed - Check pom file).

![Clone repo instructions command](/images/clonerepo8.png)

This is the simple address book application with CRUD functionality.


## Activity 2 - Writing Tests for Existing Code
Follow the notes on Canvas for writing the unit tests for existing code. There will be a number of classes where you will not be able to do any unit testing.

## Activity 3 - Writing Tests for New Functionality
This section will follow the Red, Green, Refactor process of TDD.

## Bonus Activity - Add search functionality to the GUI
The final step is to implement search functionality into the GUI.

## Additional Challenges
+ Ability to sort contacts by first name, last name, email, or phone number.
+ Validation for the email and phone number fields in the **Contact** class (i.e., ensure that the email is a valid email address and the phone number is a valid Australian phone number).
