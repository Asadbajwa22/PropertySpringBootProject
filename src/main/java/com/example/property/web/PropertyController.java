package com.example.property.web;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.property.domain.Property;
import com.example.property.service.PropertyService;

@RestController
public class PropertyController {

	private PropertyService service;
	
	@Autowired
	public PropertyController(PropertyService propertyService) {
		super();
		this.service = propertyService;
	}
	
	@PostMapping("/create")
	public ResponseEntity<Property> createProperty(@RequestBody Property property){
		Property created = this.service.createProperty(property);
		ResponseEntity<Property> response = new ResponseEntity<Property>(created,HttpStatus.CREATED);
		return response;
	}
	
	@GetMapping("/getAll")
	public ResponseEntity<List<Property>> getAllProperties(){
		return ResponseEntity.ok(this.service.getAllProperties());
	}
	
	@GetMapping("/get/{id}")
	public Property getProperty(@PathVariable Integer id) {
		 return this.service.getProperty(id);
	}
	
	@PutMapping("/replace/{id}")
	public ResponseEntity<Property> replaceProperty(@PathVariable Integer id,@RequestBody Property newProperty){
		Property body = this.service.replaceProperty(id, newProperty);
		ResponseEntity<Property> responseEntity = new ResponseEntity<Property>(body,HttpStatus.ACCEPTED);
		return responseEntity;
	}
	
	@DeleteMapping("/remove/{id}")
	public ResponseEntity<?> removeProperty(@PathVariable Integer id){
		this.service.removeProperty(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
	@GetMapping("/getByType/{type}")
	public ResponseEntity<List<Property>> getPropertyByType(@PathVariable String type){
		List<Property> list = this.service.getPropertyByType(type);
		return ResponseEntity.ok(list);
	}
	
	@GetMapping("/getByBedroom/{bedroom}")
	public ResponseEntity<List<Property>> getPropertyByBedroom(@PathVariable Integer bedroom){
		List<Property> list = this.service.getPropertyByBedrooms(bedroom);
		return ResponseEntity.ok(list);
	}
}
