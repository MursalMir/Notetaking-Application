package com.example.addressbook.model;

import java.util.ArrayList;
import java.util.List;

public class ContactManager {

    enum SearchType {
        EMAIL,
        PHONE,
        NAME
    }

    IContactDAO connection;

    public ContactManager() {
        connection = new SqliteContactDAO();
    }

    public List<Contact> searchContacts(String query) {

        List<Contact> allContacts = new ArrayList<>();
        allContacts = connection.getAllContacts();
        String correctQueryFormat = "";

        if (nullOrBlank(query)) {
            return allContacts;
        } else if (query.contains("@") || query.contains(".")) {
            return getContactsByQuery(allContacts,  query, SearchType.EMAIL);
         // phone
        } else if (query.matches(".*\\d.*")) {
            return getContactsByQuery(allContacts,  query, SearchType.PHONE);
        } else {
            return getContactsByQuery(allContacts, query, SearchType.NAME);

        }
    }


    public void addContact(Contact contact) {
        connection.addContact(contact);
    }

    private boolean nullOrBlank(String value) {
        return (value == null || value.isBlank());
    }

    private List<Contact> getContactsByQuery( List<Contact> allContacts, String query, SearchType value) {
        List<Contact> results = new ArrayList<>();
        for (Contact contact : allContacts) {
            if (value==SearchType.EMAIL) {
                if (contact.getEmail().contains(query)) {
                    results.add(contact);
                }
            } else if (value==SearchType.PHONE) {
                if (contact.getPhone().contains(query)) {
                    results.add(contact);
                }
            } else {
                if (contact.getFullName().contains(" ")) {
                    String[] names = contact.getFullName().split(" ");
                    if ((names[0] + names[1]).toLowerCase().contains(query.replaceAll("\\s+", "").toLowerCase())) {
                        results.add(contact);
                    }
                }
            }
        }
        return results;
    }

}
