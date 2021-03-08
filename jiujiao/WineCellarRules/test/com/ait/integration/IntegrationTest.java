package com.ait.integration;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.core.Response;

import org.apache.commons.httpclient.HttpStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.ait.tea.Tea;
import com.ait.tea.TeaResource;
import com.ait.utils.UtilsDAO;
import com.ait.validation.ErrorMessage;

class IntegrationTest {
	
	TeaResource teaResource;
	
	@BeforeEach
	public void setUp() throws Exception{
		teaResource = new TeaResource();
		UtilsDAO utilsDAO = new UtilsDAO();
		List<Tea> teas= new ArrayList<Tea>();
		Tea tea= new Tea();
		tea.setCountry("SPAIN");
		tea.setGrapes("Merlot");
		tea.setYear("2012");
		tea.setName("BLOCK NINE");
		teas.add(tea);
		tea = new Tea();
		tea.setCountry("FRANCE");
		tea.setGrapes("Chardonay");
		tea.setYear("2010");
		tea.setName("PINOT NOIR");
		teas.add(tea);
		utilsDAO.resetTable(teas);
		
	}

	@Test
	void testGetAllTeas() {
		Response response=teaResource.findAll();
		List<Tea> teaList = (List<Tea>) response.getEntity();
		assertEquals(HttpStatus.SC_OK, response.getStatus());
		assertEquals(teaList.size(), 2);
		Tea tea=teaList.get(0);
		assertEquals("SPAIN",tea.getCountry());
		tea=teaList.get(1);
		assertEquals("FRANCE",tea.getCountry());
		assertEquals("Chardonay",tea.getGrapes());
		assertEquals("2010",tea.getYear());
		assertEquals("PINOT NOIR",tea.getName());
	}
	
	@Test
	public void testAddTea() {
		Tea tea = new Tea();
		tea.setCountry("GREECE");
		tea.setGrapes("green");
		tea.setName("NEW WINE");
		tea.setYear("2009");
		Response response=teaResource.create(tea);
		assertEquals(HttpStatus.SC_OK, response.getStatus());
		tea = (Tea) response.getEntity();
		//assertEquals(tea.getId(), 3);
		assertEquals(tea.getName(), "NEW WINE");
		response=teaResource.findAll();
		List<Tea> teaList = (List<Tea>) response.getEntity();
		assertEquals(teaList.size(), 3);
	}
	
	@Test
	public void testAddTeaWithValidationException() {
		Tea tea = new Tea();
		tea.setCountry("GREECE");
		tea.setGrapes("green");
		tea.setName("NEW WINE");
		tea.setYear("2019");
		Response response=teaResource.create(tea);
		assertEquals(HttpStatus.SC_FORBIDDEN, response.getStatus());
		ErrorMessage message = (ErrorMessage) response.getEntity();
		assertEquals(message.getErrorMessage(), "Tea is not mature enough");
		response=teaResource.findAll();
		List<Tea> teaList = (List<Tea>) response.getEntity();
		assertEquals(teaList.size(), 2);
	}
	
	@Test
	public void testAddTeaWithVintageExistsException() {
		Tea tea = new Tea();
		tea.setCountry("SPAIN");
		tea.setGrapes("green");
		tea.setName("BLOCK NINE");
		tea.setYear("2012");
		Response response=teaResource.create(tea);
		assertEquals(HttpStatus.SC_FORBIDDEN, response.getStatus());
		ErrorMessage message = (ErrorMessage) response.getEntity();
		assertEquals(message.getErrorMessage(), "Tea with given name and year already exists");
		response=teaResource.findAll();
		List<Tea> teaList = (List<Tea>) response.getEntity();
		assertEquals(teaList.size(), 2);
	}
}
