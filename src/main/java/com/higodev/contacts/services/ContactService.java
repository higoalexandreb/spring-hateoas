package com.higodev.contacts.services;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.higodev.contacts.models.Contact;
import com.higodev.contacts.repositories.ContactRepository;

@Service
public class ContactService {
	
	@Autowired
	private ContactRepository repository;
	
	@Transactional(readOnly=true)
	public List<Contact> findAll(){
		return repository.findAll();
	}
	
	public Optional<Contact> findOne(UUID contactId){
		return repository.findById(contactId);
	}
	
	public Contact save(Contact contact) {
		return repository.save(contact);
	}
	
	public Contact update(Contact contact, UUID contactId) {
		return repository.findById(contactId).map(c -> {
			c.setEmail(contact.getEmail());
			c.setName(contact.getName());
			c.setPhone(contact.getPhone());
			return repository.save(contact);
		}).orElse(null);
	}
	
	public void delete(UUID contactId) {
		Optional<Contact> contact = repository.findById(contactId);
		
		if(contact.isPresent())
			repository.deleteById(contactId);
	}

}
