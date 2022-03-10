package com.example.property.web;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.parallel.ExecutionMode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultMatcher;

import com.example.property.domain.Property;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@Sql(scripts = {"classpath:property-schema.sql","classpath:property-data.sql"},executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
@ActiveProfiles("test")
public class PropertyControllerIntegrationTest {

	@Autowired
	private MockMvc mvc;
	
	@Autowired
	private ObjectMapper mapper;
	
	@Test
	void testCreate() throws Exception{
		Property property = new Property(null, "House", 4, 800);
		String testPropertyAsJson = this.mapper.writeValueAsString(property);
		RequestBuilder requestBuilder = post("/create").contentType(MediaType.APPLICATION_JSON).content(testPropertyAsJson);
		
		Property testCreatedProperty = new Property(3, "House", 4, 800);
		String testCreatedPropertyAsJson = this.mapper.writeValueAsString(testCreatedProperty);
		ResultMatcher checkStatus = status().isCreated();
		ResultMatcher checkBody = content().json(testCreatedPropertyAsJson);
		
		this.mvc.perform(requestBuilder).andExpect(checkStatus).andExpect(checkBody);
	}
	
	@Test
	void getAllTest() throws Exception {
		RequestBuilder requestBuilder = get("/getAll");
		List<Property> testProperty = List.of(new Property(1, "House", 3, 1000),new Property(2, "Flat", 4, 1200));
		String testPropertyJson = this.mapper.writeValueAsString(testProperty);
		ResultMatcher checkStatus = status().isOk();
		ResultMatcher checkBody = content().json(testPropertyJson);
		this.mvc.perform(requestBuilder).andExpect(checkStatus).andExpect(checkBody);
		
	}
	
	@Test
	void getTest() throws Exception {
		RequestBuilder requestBuilder = get("/get/1");
		String propertyAsJson = this.mapper.writeValueAsString(new Property(1, "House", 3, 1000));
		ResultMatcher checkStatus = status().isOk();
		ResultMatcher checkBody = content().json(propertyAsJson);
		this.mvc.perform(requestBuilder).andExpect(checkStatus).andExpect(checkBody);
	}
	
	@Test
	void getByTypeTest() throws Exception {
		RequestBuilder requestBuilder = get("/getByType/House");
		List<Property> testProperty = List.of(new Property(1, "House", 3, 1000));
		String propertyAsJson = this.mapper.writeValueAsString(testProperty);
		ResultMatcher checkStatus = status().isOk();
		ResultMatcher checkBody = content().json(propertyAsJson);
		this.mvc.perform(requestBuilder).andExpect(checkStatus).andExpect(checkBody);
	}
	
	@Test
	void testReplace() throws Exception {
		Property testProperty = new Property(null, "House", 2, 300);
		String testPropertyAsJson = this.mapper.writeValueAsString(testProperty);
		RequestBuilder requestBuilder = put("/replace/1").contentType(MediaType.APPLICATION_JSON).content(testPropertyAsJson);
		Property testCreatedProperty = new Property(1, "House", 2, 300);
		String testCreatedPropertyAsJSON = this.mapper.writeValueAsString(testCreatedProperty);
		ResultMatcher checkStatus = status().isAccepted();
		ResultMatcher checkBody = content().json(testCreatedPropertyAsJSON);
		this.mvc.perform(requestBuilder).andExpect(checkStatus).andExpect(checkBody);
			
	}
	
	@Test
	void testRemove() throws Exception {
		this.mvc.perform(delete("/remove/1")).andExpect(status().isNoContent());
	}
	
}
