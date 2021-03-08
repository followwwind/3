package com.ait.unit.test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.ait.tea.Tea;
import com.ait.validation.CheckVintageExists;
import com.ait.validation.TeaException;
import com.ait.validation.TeaValidationException;
import com.ait.validation.TeaVintageExistsException;

class CheckVintageExistsTest {
	Tea teaOne=createWine(1,"BLOCK NINE", "SPAIN","Merlot","2004");
	Tea teaTwo=createWine(2,"PINOT NOIR", "FRANCE","Grenache","2002");;
	Tea teaThree=createWine(3,"FAUSTINO", "GREECE","Shiraz","2000");
	List<Tea> teas=new ArrayList<Tea>();
	CheckVintageExists checkVintageExists;
	
	
	@BeforeEach
	void setUp() {
		teas.add(teaOne);
		teas.add(teaTwo);
		teas.add(teaThree);
		checkVintageExists = new CheckVintageExists();
	}

	@Test
	void testVintageYearExistsOK() throws TeaException {
		Tea tea = new Tea();
		tea.setId(4);
		tea.setCountry("SPAIN");
		tea.setGrapes("Merlot");
		tea.setName("SANTA ROSA");
		tea.setYear("2004");
		checkVintageExists.checkForVintage(tea,teas);
	}
	
	@Test
	void testVintageNameExistsOK() throws TeaException {
		Tea tea = new Tea();
		tea.setId(4);
		tea.setCountry("SPAIN");
		tea.setGrapes("Merlot");
		tea.setName("BLOCK NINE");
		tea.setYear("2003");
		checkVintageExists.checkForVintage(tea,teas);
	}
	
	@Test
	void testVintageExistsException() throws TeaException {
		Tea tea = new Tea();
		tea.setId(4);
		tea.setCountry("SPAIN");
		tea.setGrapes("Merlot");
		tea.setName("BLOCK NINE");
		tea.setYear("2004");
		Exception exception = assertThrows(TeaVintageExistsException.class,()->{
			checkVintageExists.checkForVintage(tea,teas);
		});
		
	}
	
	private Tea createWine(int id, String name, String country, String grapes, String year) {
		Tea tea=new Tea();
		tea.setId(id);
		tea.setCountry(country);
		tea.setName(name);
		tea.setYear(year);
		return tea;
	}

}
