package com.example.property.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.property.domain.Property;

@Repository
public interface PropertyRepo extends JpaRepository<Property, Integer> {

	List<Property> findByType(String type);
	
	List<Property> findByBedrooms(Integer bedrooms);
}
