package com.higodev.contacts.resources;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.higodev.contacts.models.Contact;
import com.higodev.contacts.services.ContactService;

@RestController
@RequestMapping(path = "/contacts")
public class ContactResource {

	@Autowired
	private ContactService service;

	@GetMapping
	public ResponseEntity<List<Contact>> findAll() {
		List<Contact> contacts = service.findAll();

		if (contacts.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} else {
			for (Contact contact : contacts) {
				long id = contact.getContactId();
				contact.add(linkTo(methodOn(ContactResource.class).findById(id)).withSelfRel());
			}
			return new ResponseEntity<List<Contact>>(contacts, HttpStatus.OK);
		}

	}

	@GetMapping(path = "/{contactId}")
	public ResponseEntity<Contact> findById(@PathVariable(value = "contactId") long contactId) {
		Optional<Contact> contact = service.findById(contactId);
		
		if (!contact.isPresent()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} else {
			contact.get().add(linkTo(methodOn(ContactResource.class).findAll()).withRel("List of contacts"));
			return new ResponseEntity<Contact>(contact.get(), HttpStatus.OK);
		}

	}

}
