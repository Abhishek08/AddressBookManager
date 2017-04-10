# AddressBook Manager
A simple yet powerful address book and contact manager (Reece.com.au coding challenge).<br>
By Hernan Rojas.<br>
Last edited: April 10th, 2017.

# Challenge statement
As a Reece Branch Manager I would like an address book application on my PC So that I can keep track of my customer contacts

## Acceptance Criteria
- Address book will hold name and phone numbers of contact entries
- Users should be able to add new contact entries
- Users should be able to remove existing contact entries
- Users should be able to print all contacts in an address book
- Users should be able to maintain multiple address books
- Users should be able to print a unique set of all contacts across multiple address books

## Deliverables
- Written in Java 8
- A working user interface is not required, nor the use of any frameworks or a database.
- We are looking for all acceptance criteria to be demonstrable through tests

# Challenge solution
My aim was to deliver a very simple and concise solution that demonstrated a good knowledge of Java 8 and Test Driven Development. Instead of creating a GUI for manual testing, I provided a set of tests. The solution is built and the tests are run using maven.

## File Listing
```
/
¦ pom.xml                                              Maven POM file, for dependency handling and launching
¦
+---src
  +---main
  ¦ +---java
  ¦   +---com
  ¦     +---reece
  ¦       +---addressbook
  ¦         ¦ AddressBookManager.java                  Logic for management of address books and contacts
  ¦         ¦
  ¦         +---model
  ¦           ¦ AddressBook.java                       Class that represents an address book, containing a set of contacts
  ¦           ¦ Contact.java                           Class that represents a contact
  ¦
  +---test
    +---java
      +---com
        +---reece
          +---shopping
            +---test
              | AcceptanceCriteriaTest.java            Tests that focus on demonstrating acceptance criteria
              | AddressBookManagerIntegrationTest.java Integration tests used to develop the solution
              | AddressBookTest.java                   Tests for the AddressBook class
```

## Test files description
### AcceptanceCriteriaTest.java
Contains tests that briefly demonstrate the implementation of the features set as Acceptance Criteria.

### AddressBookManagerIntegrationTest.java
This class contains integration tests used to develop and assure the features of the address book manager app. Includes tests for valid management operations and for failing scenarios.

### AddressBookTest.java
Unit tests for the methods that handle the contacts of an address book.

## How to build and run
Apache Maven 3.x is needed to build and run the tests. To achieve this, launch the command line and run: 
```
> mvn clean install
```

Thanks for reading this file.
