package com.ait.mocks;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.core.Response;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.ait.tea.Tea;
import com.ait.tea.TeaDAO;
import com.ait.tea.TeaResource;

import org.apache.http.HttpStatus;


class TeaResourceTest {
	
	 List<Tea> teas;
	 TeaResource teaResource;
	@Mock 
	private TeaDAO teaDAO;

	@BeforeEach
	void test() {
		MockitoAnnotations.initMocks(this);
		teaResource = new TeaResource();
		teaResource.teaDao=teaDAO;
		Tea wine=new Tea();
	    wine.setCountry("GREECE");
	    wine.setGrapes("Grenache");
	    wine.setCountry("1984");
	    wine.setName("NEW");
	    teas = new ArrayList<Tea>();
	    teas.add(wine);
	    Tea wine1=new Tea();
	    wine1.setCountry("FRANCE");
	    wine1.setGrapes("Merlot");
	    wine1.setCountry("1989");
	    wine1.setName("NEWTWO");
	    teas.add(wine1);
	}
	
	@Test
	public void findAllWines() {
		Mockito.when(teaDAO.findAll()).thenReturn(teas);
		Response response=teaResource.findAll();
		List<Tea> wineList = (List<Tea>) response.getEntity();
		assertEquals(HttpStatus.SC_OK, response.getStatus());
		assertEquals(wineList.size(), 2);
		Tea wine=wineList.get(0);
		assertEquals("NEW",wine.getName());
		wine=wineList.get(1);
		assertEquals("NEWTWO",wine.getName());

	}

}
