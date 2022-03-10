package com.example.property.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.property.domain.Property;
import com.example.property.repo.PropertyRepo;

@Service
public class PropertyService {

	private PropertyRepo propertyRepo;
	
	@Autowired
	public PropertyService(PropertyRepo propertyRepo) {
		super();
		this.propertyRepo = propertyRepo;
	}
	
	public Property createProperty(Property property) {
		Property created = this.propertyRepo.save(property);
		return created;
	}

	public List<Property> getAllProperties() {
		return this.propertyRepo.findAll();
	}
	
	
	public Property getProperty(Integer id) {
		Optional<Property> found = this.propertyRepo.findById(id);
		return found.get();
	}
	
	public Property replaceProperty(Integer id, Property newProperty) {
		Property existing = this.propertyRepo.findById(id).get();
		
		existing.setBedrooms(newProperty.getBedrooms());
		existing.setPrice(newProperty.getPrice());
		existing.setType(newProperty.getType());
		
		Property updated = this.propertyRepo.save(existing);
		return updated;
	}
	
	public void removeProperty(@PathVariable Integer id) {
		this.propertyRepo.deleteById(id);
	}

	public List<Property> getPropertyByType(String type){
		List<Property> found = this.propertyRepo.findByType(type);
		return found;
	}
	
	public List<Property> getPropertyByBedrooms(Integer bedroom){
		List<Property> found = this.propertyRepo.findByBedrooms(bedroom);
		return found;
	}
}
