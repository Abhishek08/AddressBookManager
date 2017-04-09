package com.reece.addressbook;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import com.reece.addressbook.model.AddressBook;
import com.reece.addressbook.model.Contact;

/**
 * This class represents an address book manager. It allows the creation and
 * removal of address books and the addition or removal of contacts to address
 * books. Methods to retrieve and print all contacts or the contacts within a
 * specific address book are also provided.
 * 
 * When an AddressBookManager instance is created, it contains a default address
 * book. This can be used to manage contacts without the setup of address books.
 */
public class AddressBookManager 
{
	/**
	 * Name of the default address book.
	 */
	public static final String DEFAULT_BOOK = "default";

	/**
	 * All address books managed.
	 */
	private Map<String, AddressBook> books;

	/* **************
	 * PUBLIC METHODS
	 * ************** */

	/**
	 * @return All address books currently being managed
	 */
	public Set<String> getAllAdressBooks() {
		return getBooks().keySet();
	}

	/**
	 * Creates a new address book with the name received as parameter
	 * 
	 * @param name
	 *            name for the new address book
	 * @return the newly created address book
	 */
	public AddressBook createAddressBook(String name) {
		AddressBook book = new AddressBook(name);
		book.validate();
		getBooks().put(name, book);
		return book;
	}

	/**
	 * Removes an existing address book, along with all its contacts
	 * 
	 * @param name
	 *            name of the address book to be removed
	 */
	public void removeAddressBook(String name) {
		getBooks().remove(name);
	}

	/**
	 * Adds a new contact to the default address book
	 * 
	 * @param contact
	 *            contact object with valid name and phone
	 * @return the newly created contact
	 */
	public Contact addContact(Contact contact) {
		return addContact(contact, DEFAULT_BOOK);
	}

	/**
	 * Adds a new contact to the address book specified. If addressBook is null,
	 * adds it the default address book
	 * 
	 * @param contact
	 *            contact object with valid name and phone
	 * @param addressBook
	 *            address book to which the contact will be added
	 * @return the newly created contact
	 */
	public Contact addContact(Contact contact, String addressBook) {
		if (contact == null)
			throw new RuntimeException("A contact is mandatory");

		// validates contact properties
		contact.validate();

		// gets address book
		AddressBook book = findOrCreateAddressBook(addressBook);

		contact.setBook(book);
		book.addContact(contact);

		return contact;
	}

	/**
	 * Removes a contact from the default address book given its name.
	 * 
	 * @param name
	 *            name of contact to be removed
	 */
	public void removeContactByName(String name) {
		removeContactByName(name, DEFAULT_BOOK);
	}

	/**
	 * Removes a contact from the default address book given its name
	 * 
	 * @param name
	 *            name of contact to be removed
	 * @param addressBook
	 *            name of address book from which the contact will be removed
	 */
	public void removeContactByName(String name, String addressBook) {
		final AddressBook book = getAddressBook(addressBook);

		if (book == null)
			throw new RuntimeException("Address book not found: " + book);

		Contact contact = new Contact(name);
		book.removeContact(contact);
	}

	/**
	 * @param addressBook
	 * @return All contacts in the address book with the received name.
	 */
	public Set<Contact> getContacts(String addressBook) {
		AddressBook book = getAddressBook(addressBook);
		if (book == null)
			throw new RuntimeException("Address book not found: " + addressBook);

		return book.getContacts();
	}

	/**
	 * @return Contacts across all address books.
	 */
	public Set<Contact> getAllContacts() {
		final Set<Contact> allContacts = new HashSet<Contact>();
		for (String bookName : getBooks().keySet()) {
			allContacts.addAll(getContacts(bookName));
		}

		return allContacts;
	}

	/**
	 * Sends to system out all contacts in the specified address book
	 * @param addressBook name of the address book
	 */
	public void printContacts(String addressBook) {
		System.out.println("Contacts list in " + addressBook + ":");
		printContacts(getContacts(addressBook));
	}

	/**
	 * Sends to system out contacts in all address books
	 */
	public void printAllContacts() {
		System.out.println("All contacts list:");
		printContacts(getAllContacts());
	}

	/* ***************
	 * PRIVATE METHODS
	 * *************** */

	/**
	 * Returns the address books map, or initializes it if null.
	 */
	private Map<String, AddressBook> getBooks() {
		if (books == null) {
			books = new HashMap<String, AddressBook>();
			getBooks().put(DEFAULT_BOOK, new AddressBook(DEFAULT_BOOK));
		}
		
		return books;
	}

	/**
	 * Returns the default {@link AddressBook}.
	 */
	private AddressBook getDefaultAddressBook() {
		return getBooks().get(DEFAULT_BOOK);
	}

	/**
	 * Returns the {@link AddressBook} with the name specified.
	 */
	private AddressBook getAddressBook(String name) {
		if (name == null) return getDefaultAddressBook();
		else return getBooks().get(name);
	}

	private AddressBook findOrCreateAddressBook(String addressBook) {
		AddressBook book = getAddressBook(addressBook);
		return book == null? createAddressBook(addressBook) : book;
	}

	/**
	 * Sends to the standard system out the list of contacts received.
	 */
	private void printContacts(Set<Contact> contacts) {
		for (Contact contact : contacts) {
    		System.out.println(contact);
    	}
	}

}
