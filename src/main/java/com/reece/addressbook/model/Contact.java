package com.reece.addressbook.model;

import java.io.Serializable;

public class Contact implements Serializable {

	private static final long serialVersionUID = -1734064500474979164L;

	private String name;
	private String phone;
	private AddressBook book;

	public Contact(String name){
		setName(name);
	}

	public Contact(String name, String phone){
		setName(name);
		setPhone(phone);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public AddressBook getBook() {
		return book;
	}

	public void setBook(AddressBook book) {
		this.book = book;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
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
		Contact other = (Contact) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "name=" + name + ", "
			+ "phone=" + phone + ", "
			+ (book != null ? "book=" + book : "");
	}

	public void validate() {
		if (isNullOrEmpty(this.name)) {
			throw new RuntimeException("Name is mandatory");
		}
		if (isNullOrEmpty(this.phone)) {
			throw new RuntimeException("Phone is mandatory");			
		}
	}

	private boolean isNullOrEmpty(String value) {
		return value == null || value.trim().isEmpty();
	}

}
