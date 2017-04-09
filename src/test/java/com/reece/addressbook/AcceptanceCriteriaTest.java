package com.reece.addressbook;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Set;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.reece.addressbook.model.Contact;

/**
 * Acceptance criteria unit tests, using a simple fixture.
 */
public class AcceptanceCriteriaTest
{
	private AddressBookManager manager;

	/**
	 * A small fixture is set up before the execution of each test.
	 * @throws Exception
	 */
	@Before
	public void before() throws Exception {
		manager = new AddressBookManager();
		// Add some contacts to the default address book
		manager.addContact(new Contact("Police", "000"));
		manager.addContact(new Contact("Floods", "132 500"));

		// Add contacts to other address books
		manager.addContact(new Contact("Dad",      "0123 123123"), "family");
		manager.addContact(new Contact("Mom",      "0123 234234"), "family");

    	manager.addContact(new Contact("Archie",   "0123 111111"), "friends");
    	manager.addContact(new Contact("Betty",    "0123 222222"), "friends");
    	manager.addContact(new Contact("Veronica", "0123 333333"), "friends");

    	manager.addContact(new Contact("Fred",     "0123 456456"), "work");
	}

	/**
	 * After the execution of test, the fixture is tore down.
	 * @throws Exception
	 */
	@After
	public void teardown() throws Exception {
		manager = null;
	}

    /**
     * Acceptance criteria #1: Users should be able to add new contact entries
     */
	@Test
	public void testAddContact() {
		manager.addContact(new Contact("Ambulance", "000"));

		assertEquals(9, manager.getAllContacts().size());
		assertTrue(manager.getAllContacts().contains(new Contact("Ambulance")));
	}

    /**
     * Acceptance criteria #2: Users should be able to remove existing contact entries
     */
	@Test
	public void testRemoveContact() {
		manager.removeContactByName("Floods");

		Set<Contact> contacts = manager.getAllContacts();
		assertEquals(7, contacts.size());
		assertFalse(contacts.contains(new Contact("Floods")));
	}

    /**
     * Acceptance criteria #3: Users should be able to print all contacts in an address book
     */
	@Test
	public void testGetContactsInAdressBook() {
		Set<Contact> contacts = manager.getContacts("friends");
		assertTrue(contacts.contains(new Contact("Archie")));
		assertTrue(contacts.contains(new Contact("Betty")));
		assertTrue(contacts.contains(new Contact("Veronica")));

		contacts = manager.getContacts("family");
		assertTrue(contacts.contains(new Contact("Dad")));
		assertTrue(contacts.contains(new Contact("Mom")));

		contacts = manager.getContacts("work");
		assertTrue(contacts.contains(new Contact("Fred")));

		contacts = manager.getContacts("default");
		assertTrue(contacts.contains(new Contact("Police")));
		assertTrue(contacts.contains(new Contact("Floods")));
	}

    /**
     * Acceptance criteria #4: Users should be able to maintain multiple address books
     */
	@Test
	public void testMaintainMultipleAddressBooks() {

    	// Assert the existence of all address books
    	Set<String> books = manager.getAllAdressBooks();
		assertEquals(4, books.size());
		assertTrue(books.contains("family"));
		assertTrue(books.contains("friends"));
		assertTrue(books.contains("work"));

		// Remove the "work" address book
		manager.removeAddressBook("work");

		books = manager.getAllAdressBooks();
		assertEquals(3, books.size());
		assertFalse(books.contains("work"));

		// Remove the "friends" address book
		manager.removeAddressBook("friends");

		books = manager.getAllAdressBooks();
		assertEquals(2, books.size());
		assertFalse(books.contains("friends"));

		// Add a new address book
		manager.createAddressBook("footy");

		books = manager.getAllAdressBooks();
		assertEquals(3, books.size());
		assertTrue(books.contains("footy"));
	}

	/**
	 * Acceptance criteria #5: Users should be able to print a unique set of all
	 * contacts across multiple address books
	 */
	@Test
	public void testUniqueContactsInAllAddressBooks() {
		// let's add some duplicated entries across different address books:
    	manager.addContact(new Contact("Betty",    "0123 222222"), "family"); // exists in "friends"
    	manager.addContact(new Contact("Veronica", "0123 333333"), "work"); // exists in "friends"

		assertEquals(2, manager.getContacts(AddressBookManager.DEFAULT_BOOK).size());
		assertEquals(3, manager.getContacts("family").size());
		assertEquals(3, manager.getContacts("friends").size());
		assertEquals(2, manager.getContacts("work").size());

		Set<Contact> allContacts = manager.getAllContacts();
		assertEquals(8, allContacts.size()); // only 8 unique contacts
		assertTrue(allContacts.contains(new Contact("Archie")));
		assertTrue(allContacts.contains(new Contact("Betty")));
		assertTrue(allContacts.contains(new Contact("Veronica")));
		assertTrue(allContacts.contains(new Contact("Fred")));
		assertTrue(allContacts.contains(new Contact("Dad")));
		assertTrue(allContacts.contains(new Contact("Mom")));
		assertTrue(allContacts.contains(new Contact("Police")));
		assertTrue(allContacts.contains(new Contact("Floods")));
	}

}
