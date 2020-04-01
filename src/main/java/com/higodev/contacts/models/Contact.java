package com.higodev.contacts.models;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.springframework.hateoas.ResourceSupport;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name="contacts")
public class Contact extends ResourceSupport implements Serializable{
	private static final long serialVersionUID = -6827773046529365962L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long contactId;
	
	@Column(nullable = false, length = 100)
	private String name;
	
	@Column(length = 11)
	private String phone;
	
	@Column(length = 100, unique = true)
	private String email;
}
