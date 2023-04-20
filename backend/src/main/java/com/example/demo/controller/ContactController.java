package com.example.demo.controller;

import java.util.List;

import com.example.demo.dto.contact.CreateContactDTO;
import com.example.demo.models.Contact;
import com.example.demo.service.ContactService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.CrossOrigin;


import jakarta.validation.Valid;

@RestController()
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping(value = "/contacts")
public class ContactController {

    @Autowired
    private ContactService contactService;

    @Autowired
    private ModelMapper modelMapper;

    // create
    @PostMapping()
    public ResponseEntity<Void> create(@Valid @RequestBody CreateContactDTO createContactDTO) {
        System.out.println(createContactDTO);
        this.contactService.createContact(modelMapper.map(createContactDTO, Contact.class));
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    // real all
    @GetMapping()
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<List<Contact>> getAllContact() {
        var contact = this.contactService.readAllContacts();
        return ResponseEntity.ok(contact);
    }

}
