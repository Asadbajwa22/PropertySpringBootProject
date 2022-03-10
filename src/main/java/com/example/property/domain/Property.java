package com.example.property.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


@Entity
public class Property {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	private String type;
	private Integer bedrooms;
	private Integer price;
	
	public Property() {
		super();
	}
	
	public Property(Integer id, String type, Integer bedrooms, Integer price) {
		super();
		this.id = id;
		this.type = type;
		this.bedrooms = bedrooms;
		this.price = price;
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public Integer getBedrooms() {
		return bedrooms;
	}
	public void setBedrooms(Integer bedrooms) {
		this.bedrooms = bedrooms;
	}
	public Integer getPrice() {
		return price;
	}
	public void setPrice(Integer price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return "Property [id=" + id + ", type=" + type + ", bedrooms=" + bedrooms + ", price=" + price + "]";
	}
}
