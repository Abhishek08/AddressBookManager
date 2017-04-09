package com.reece.addressbook.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class AddressBook implements Serializable {

	private static final long serialVersionUID = -6798809642346047903L;

	private String name;
	private Set<Contact> contacts;

	public AddressBook(String name) {
		this.setName(name);
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<Contact> getContacts() {
		if (contacts == null)
			contacts = new HashSet<Contact>();
		return contacts;
	}

	public void addContact(Contact contact) {
		getContacts().add(contact);
	}
	
	public void removeContact(Contact contact) {
		getContacts().remove(contact);
	}

	@Override
	public int hashCode() {
		final int prime = 37;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AddressBook other = (AddressBook) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return getName();
	}

	public void validate() {
		if (isNullOrEmpty(this.name)) {
			throw new RuntimeException("Name is mandatory");
		}
	}

	private boolean isNullOrEmpty(String value) {
		return value == null || value.trim().isEmpty();
	}
}
