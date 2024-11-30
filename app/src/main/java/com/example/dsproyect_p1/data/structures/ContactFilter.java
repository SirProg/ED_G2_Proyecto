package com.example.dsproyect_p1.data.structures;
import com.example.dsproyect_p1.data.model.Contact;
import com.example.dsproyect_p1.data.model.ContactType;
import com.example.dsproyect_p1.data.model.SocialMedia;

import java.util.List;
import java.util.stream.Collectors;

public class ContactFilter {
    public static List<Contact> filtrarPorCiudad(List<Contact> contacts, String ciudad){
        return contacts.stream()
                .filter(c -> c.getResidencyCountry().equalsIgnoreCase(ciudad))
                .collect(Collectors.toList());
    }
    public static List<Contact> filtrarPorTipo(List<Contact> contacts, ContactType tipo){
        return contacts.stream()
                .filter(c -> c.getContactType() == tipo)
                .collect(Collectors.toList());
    }
    public static List<Contact> filtrarPorRedSocial(List<Contact> contactos, SocialMedia redSocial) {
        return contactos.stream()
                .filter(contacto -> contacto.getSocialMediaAccounts() != null
                        && contacto.getSocialMediaAccounts().stream()
                        .anyMatch(cuenta -> cuenta.getSocialMedia() == redSocial))
                .collect(Collectors.toList());
    }

}
