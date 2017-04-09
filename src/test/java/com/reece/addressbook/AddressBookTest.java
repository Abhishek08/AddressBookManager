package com.reece.addressbook;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.reece.addressbook.model.AddressBook;
import com.reece.addressbook.model.Contact;

/**
 * {@link AddressBook} class unit tests, using a simple fixture.
 */
public class AddressBookTest
{
	private AddressBook book;

	/**
	 * A small fixture is set up before the execution of each test.
	 * @throws Exception
	 */
	@Before
	public void before() throws Exception {
		book = new AddressBook("leads");

		// Add some contacts to the default address book
		book.addContact(new Contact("Cheryl", "0123 444444"));
		book.addContact(new Contact("Jason",  "0123 555555"));
		book.addContact(new Contact("Reggie", "0123 666666"));
	}

	/**
	 * After the execution of test, the fixture is tore down.
	 * @throws Exception
	 */
	@After
	public void teardown() throws Exception {
		book = null;
	}

    /**
     * {@link AddressBook#getContacts()} test.
     */
	@Test
	public void should_getContacts_inBookAddress() {
		assertEquals(3, book.getContacts().size());
	}

    /**
     * {@link AddressBook#addContact(Contact)} test.
     */
	@Test
	public void should_addContact_toBookAddress() {
		book.addContact(new Contact("Moose", "0123 777777"));

		assertEquals(4, book.getContacts().size());
		assertTrue(book.getContacts().contains(new Contact("Moose")));
	}

	@Test
	public void should_removeContact_fromBookAddress() {
		book.removeContact(new Contact("Jason"));

		assertEquals(2, book.getContacts().size());
		assertFalse(book.getContacts().contains(new Contact("Jason")));
	}

}
