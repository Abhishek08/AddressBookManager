package com.reece.addressbook;

import java.util.Set;

import org.junit.Assert;
import org.junit.Test;

import com.reece.addressbook.model.Contact;

/**
 * Integration tests for the {@link AddressBookManager} class.
 */
public class AddressBookManagerIntegrationTest
{
    /**
     * Users should be able to add new contact entries
     */
	@Test
    public void should_addValidContact_toDefaultAddressBook()
    {
    	AddressBookManager manager = new AddressBookManager();
    	manager.addContact(new Contact("Archie", "0123111111"));

    	final Set<Contact> contacts = manager.getAllContacts();

    	Assert.assertEquals(1, contacts.size());
    	Assert.assertTrue(contacts.contains(new Contact("Archie")));
    }

	@Test
    public void should_addValidContact_toNamedAddressBook()
    {
    	AddressBookManager manager = new AddressBookManager();
    	manager.addContact(new Contact("Archie", "0123111111"), "friends");

    	final Set<Contact> contacts = manager.getAllContacts();

    	Assert.assertEquals(1, contacts.size());
    	Assert.assertTrue(contacts.contains(new Contact("Archie")));
    }

	@Test
    public void shouldNot_addNullContact_toDefaultAddressBook()
    {
    	AddressBookManager manager = new AddressBookManager();

    	try {
			manager.addContact(null);
			Assert.fail("Should not add a null contact.");
		} catch (RuntimeException e) {
			Assert.assertTrue(e.getMessage().equals("A contact is mandatory"));
		}

    	Assert.assertEquals(0, manager.getAllContacts().size());
    }

	@Test
    public void shouldNot_addNullContact_toNamedAddressBook()
    {
    	AddressBookManager manager = new AddressBookManager();

    	try {
			manager.addContact(null, "newBook");
			Assert.fail("Should not add a null contact.");
		} catch (RuntimeException e) {
			Assert.assertTrue(e.getMessage().equals("A contact is mandatory"));
		}

    	// the address book should have zero contacts
    	Assert.assertEquals(0, manager.getAllContacts().size());
    	// the address book should have just the default address book
    	Assert.assertEquals(1, manager.getAllAdressBooks().size());
    }

	@Test
    public void shouldNot_addInvalidNameContact_toDefaultAddressBook()
    {
    	AddressBookManager manager = new AddressBookManager();

    	try {
			manager.addContact(new Contact("", "0123111111"));
			Assert.fail("Should not add a contact with an empty name.");
		} catch (RuntimeException e) {
			Assert.assertTrue(e.getMessage().equals("Name is mandatory"));
		}

    	Assert.assertEquals(0, manager.getAllContacts().size());
    }

	@Test
    public void shouldNot_addNullNameContact_toDefaultAddressBook()
    {
    	AddressBookManager manager = new AddressBookManager();

    	try {
			manager.addContact(new Contact(null, "0123111111"));
			Assert.fail("Should not add a contact with a null name.");
		} catch (RuntimeException e) {
			Assert.assertTrue(e.getMessage().equals("Name is mandatory"));
		}

    	Assert.assertEquals(0, manager.getAllContacts().size());
    }

	@Test
    public void shouldNot_addInvalidPhoneContact_toNamedAddressBook()
    {
    	AddressBookManager manager = new AddressBookManager();

    	try {
			manager.addContact(new Contact("Archie", "   "), "friends");
			Assert.fail("Should not allow the addition of a contact with no phone.");
		} catch (RuntimeException e) {
			Assert.assertTrue(e.getMessage().equals("Phone is mandatory"));
		}

    	// the address book should have zero contacts
    	Assert.assertEquals(0, manager.getAllContacts().size());
    	// the address book should have just the default address book
    	Assert.assertEquals(1, manager.getAllAdressBooks().size());
    }

	@Test
    public void shouldNot_addNullPhoneContact_toNamedAddressBook()
    {
    	AddressBookManager manager = new AddressBookManager();

    	try {
			manager.addContact(new Contact("Archie", null), "friends");
			Assert.fail("Should not allow the addition of a contact with no phone.");
		} catch (RuntimeException e) {
			Assert.assertTrue(e.getMessage().equals("Phone is mandatory"));
		}

    	// the address book should have zero contacts
    	Assert.assertEquals(0, manager.getAllContacts().size());
    	// the address book should have just the default address book
    	Assert.assertEquals(1, manager.getAllAdressBooks().size());
    }

    /**
     * Users should be able to remove existing contact entries
     */
	@Test
    public void should_removeContactByName_fromDefaultAddressBook()
    {
		// First an entry is added>
    	AddressBookManager manager = new AddressBookManager();
    	manager.addContact(new Contact("Archie", "0123111111"));

    	Set<Contact> contacts = manager.getAllContacts();

    	Assert.assertEquals(1, contacts.size());
    	Assert.assertTrue(contacts.contains(new Contact("Archie")));

    	// Remove the existing entry:
    	manager.removeContactByName("Archie");
    	contacts = manager.getAllContacts();

    	Assert.assertEquals(0, contacts.size());
    	Assert.assertFalse(contacts.contains(new Contact("Archie")));
    }

    /**
     * Users should be able to remove existing contact entries
     */
	@Test
    public void should_removeValidContact_fromNamedAddressBook()
    {
		// First an entry is added to a new address book
    	AddressBookManager manager = new AddressBookManager();
    	manager.addContact(new Contact("Archie", "0123111111"), "friends");

    	Set<Contact> contacts = manager.getAllContacts();

    	Assert.assertEquals(1, contacts.size());
    	Assert.assertTrue(contacts.contains(new Contact("Archie")));

    	// Remove the existing entry from the address book:
    	manager.removeContactByName("Archie", "friends");
    	contacts = manager.getAllContacts();

    	Assert.assertEquals(0, contacts.size());
    	Assert.assertFalse(contacts.contains(new Contact("Archie")));
    }

	@Test
    public void should_allowCreation_ofValidAddressBook()
    {
		AddressBookManager manager = new AddressBookManager();
		Assert.assertEquals(1, manager.getAllAdressBooks().size());
		Assert.assertTrue(manager.getAllAdressBooks().contains(AddressBookManager.DEFAULT_BOOK));

		manager.createAddressBook("1");
		manager.createAddressBook("Family");
		manager.createAddressBook("!@#$$%^");

		Assert.assertEquals(4, manager.getAllAdressBooks().size());
		Assert.assertTrue(manager.getAllAdressBooks().contains("1"));
		Assert.assertTrue(manager.getAllAdressBooks().contains("Family"));
		Assert.assertTrue(manager.getAllAdressBooks().contains("!@#$$%^"));
    }

	@Test
    public void shouldNot_allowCreation_ofInvalidAddressBook()
    {
		AddressBookManager manager = new AddressBookManager();

		try {
			manager.createAddressBook("   ");
			Assert.fail("Should not add a contact with an empty name.");
		} catch (RuntimeException e) {
			Assert.assertTrue(e.getMessage().equals("Name is mandatory"));
		}
    }

	@Test
    public void shouldNot_allowCreation_ofNullAddressBook()
    {
		AddressBookManager manager = new AddressBookManager();

		try {
			manager.createAddressBook(null);
			Assert.fail("Should not add a contact with a null name.");
		} catch (RuntimeException e) {
			Assert.assertTrue(e.getMessage().equals("Name is mandatory"));
		}
    }

	@Test
    public void should_allowAddContacts_toMultipleAddressBooks()
    {
		// A new manager is created, with contacts in 2 address books:
		AddressBookManager manager = new AddressBookManager();
		manager.addContact(new Contact("Dad",      "0123 123123"), "family");
		manager.addContact(new Contact("Mom",      "0123 234234"), "family");
    	manager.addContact(new Contact("Archie",   "0123 111111"), "friends");
    	manager.addContact(new Contact("Betty",    "0123 222222"), "friends");
    	manager.addContact(new Contact("Veronica", "0123 333333"), "friends");

    	// Assert number of address books (default, family, friends)
    	Assert.assertEquals(3, manager.getAllAdressBooks().size());

    	// Assert contacts per address book
    	Assert.assertEquals(0, manager.getContacts("default").size());
    	Assert.assertEquals(2, manager.getContacts("family").size());
    	Assert.assertEquals(3, manager.getContacts("friends").size());
    	Assert.assertEquals(5, manager.getAllContacts().size());
    }

	@Test
    public void should_allowRemoval_ofCompleteAddressBooks()
    {
		// A new manager is created, with contacts in 2 address books:
		AddressBookManager manager = new AddressBookManager();
		manager.addContact(new Contact("Dad",      "0123 123123"), "family");
		manager.addContact(new Contact("Mom",      "0123 234234"), "family");
    	manager.addContact(new Contact("Archie",   "0123 111111"), "friends");
    	manager.addContact(new Contact("Betty",    "0123 222222"), "friends");
    	manager.addContact(new Contact("Veronica", "0123 333333"), "friends");

    	// Remove an address book
    	manager.removeAddressBook("family");

    	Assert.assertEquals(2, manager.getAllAdressBooks().size());
    	Assert.assertEquals(3, manager.getAllContacts().size());
    }

	@Test
    public void should_shouldKeep_UniqueContacts()
    {
		// A new manager is created, with contacts in 2 address books:
		AddressBookManager manager = new AddressBookManager();
		manager.addContact(new Contact("Dad",      "0123 123123"), "family");
		manager.addContact(new Contact("Mom",      "0123 234234"), "family");
    	manager.addContact(new Contact("Archie",   "0123 111111"), "friends");
    	manager.addContact(new Contact("Betty",    "0123 222222"), "friends");
    	manager.addContact(new Contact("Veronica", "0123 333333"), "friends");

		// Add a contact to all address books, to check contact uniqueness
		// across address books
    	manager.addContact(new Contact("Fred", "0123 456456")); // added to the default address book
    	manager.addContact(new Contact("Fred", "0123 456456"), "friends");
    	manager.addContact(new Contact("Fred", "0123 456456"), "family");
    	manager.addContact(new Contact("Fred", "0123 456456"), "work"); // contact added to new address book

    	// Assert contacts per address book
    	Assert.assertEquals(1, manager.getContacts("default").size());
    	Assert.assertEquals(3, manager.getContacts("family").size());
    	Assert.assertEquals(4, manager.getContacts("friends").size());
    	Assert.assertEquals(1, manager.getContacts("work").size());
    	Assert.assertEquals(6, manager.getAllContacts().size());
    }

}
