# AddressBook Manager
A simple yet powerful address book and contact manager. Reece.com.au coding challenge.
By Hernan Rojas
Last edited: April 9th, 2017

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
My aim was to deliver a very simple and concise solution that demonstrated a good knowledge of Java and Test Driven Development. Instead of creating a GUI for manual testing, I provided a set of tests. The solution is built and the tests run using maven.

## File Listing
```
/
¦ pom.xml                                          Maven POM file, for dependency handling and launching
¦
+---src
  +---main
  ¦ +---java
  ¦   +---com
  ¦     +---reece
  ¦       +---addressbook
  ¦         ¦ AddressBookManager.java                  Logic for address books and contacts management
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

## File description
### AddressBookManager.java
This class represents a manager for several address books, with a set of contacts for each.
- On instantiaton, a default address book is created, 

## How to build and run
Apache Maven 3.x is needed to build and run the tests. To achieve this, launch the command line and run: 
```
> mvn clean install
```

Thanks for reading this file.
