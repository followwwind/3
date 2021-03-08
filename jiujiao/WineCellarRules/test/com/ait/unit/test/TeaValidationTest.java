package com.ait.unit.test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertThrows;

import com.ait.tea.Tea;
import com.ait.validation.ErrorMessages;
import com.ait.validation.TeaException;
import com.ait.validation.TeaValidationException;
import com.ait.validation.TeaValidator;

public class TeaValidationTest {
	TeaValidator wineValidator;
	Tea wine;
	//List<Wine> wines;
	
	@BeforeEach
	public void setUp() {
		wine = new Tea();
		wineValidator = new TeaValidator();
		wine.setId(7);
		wine.setName("BODEGA LURTON");
		wine.setCountry("FRANCE");
		wine.setYear("2012");
		wine.setGrapes("Merlot");	
	}
	
	//No exception expect validation works
	@Test
	void testAllFieldsOK() throws TeaException {
		wineValidator.validateWine(wine);
	}
	
	@Test
	void testForEmptyNameField() throws TeaException {
		wine.setName("");
		Exception exception = assertThrows(TeaValidationException.class,()->{
			wineValidator.validateWine(wine);
		});
	assertEquals(ErrorMessages.EMPTY_FIELDS.getMsg(),exception.getMessage());
	}
	
	@Test
	void testForEmptyFieldCountry() throws TeaException {
		wine.setCountry("");
		Exception exception = assertThrows(TeaValidationException.class,()->{
			wineValidator.validateWine(wine);
		});
	assertEquals(ErrorMessages.EMPTY_FIELDS.getMsg(),exception.getMessage());
	}
	
	@Test
	void testForEmptyFieldGrapes() throws TeaException {
		wine.setGrapes("");
		Exception exception = assertThrows(TeaValidationException.class,()->{
			wineValidator.validateWine(wine);
		});
	assertEquals(ErrorMessages.EMPTY_FIELDS.getMsg(),exception.getMessage());
	}
	
	@Test
	void testForEmptyFieldYear() throws TeaException {
		wine.setYear("");
		Exception exception = assertThrows(TeaValidationException.class,()->{
			wineValidator.validateWine(wine);
		});
	assertEquals(ErrorMessages.EMPTY_FIELDS.getMsg(),exception.getMessage());
	}
	
	@ParameterizedTest
	@ValueSource(strings= {"SPaIN","france","GREECE"})
	void testForValidCountry(String country) throws TeaException {
		wine.setCountry(country);
		wineValidator.validateWine(wine);
	}
	
	@Test
	void testForInvalidCountry() throws TeaException {
		wine.setCountry("USA");
		Exception exception = assertThrows(TeaValidationException.class,()->{
			wineValidator.validateWine(wine);
		});
	assertEquals(ErrorMessages.INVALID_COUNTRY.getMsg(),exception.getMessage());
	}
	
	@ParameterizedTest
	@ValueSource(strings= {"2001","2005","1998"})
	void testForBadYears(String year) throws TeaException {
		wine.setYear(year);
		Exception exception = assertThrows(TeaValidationException.class,()->{
			wineValidator.validateWine(wine);
		});
	assertEquals(ErrorMessages.BAD_YEAR.getMsg(),exception.getMessage());
	}
	
	@ParameterizedTest
	@ValueSource(strings= {"2020","2016","2018"})
	void testForWineNotMature(String year) throws TeaException {
		wine.setYear(year);
		Exception exception = assertThrows(TeaValidationException.class,()->{
			wineValidator.validateWine(wine);
		});
	assertEquals(ErrorMessages.NOT_MATURE.getMsg(),exception.getMessage());
	}
}
