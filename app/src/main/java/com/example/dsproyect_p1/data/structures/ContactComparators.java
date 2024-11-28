package com.example.dsproyect_p1.data.structures;

import com.example.dsproyect_p1.data.model.Contact;

import java.util.Comparator;

public class ContactComparators {
    public static final Comparator<Contact> BY_NAME = (c1, c2) -> c1.getName().compareTo(c2.getName());;

    public static final Comparator<Contact> BY_RESIDENCY_COUNTRY = (c1, c2) -> c1.getResidencyCountry().compareTo(c2.getResidencyCountry());

    public static final Comparator<Contact> BY_CONTACT_TYPE = (c1, c2) -> c1.getContactType().compareTo(c2.getContactType());
}
