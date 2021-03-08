package com.ait.validation;

import java.util.List;

import com.ait.tea.Tea;

public class CheckVintageExists {
	Tea wine;
	List<Tea> wines;

	public void checkForVintage(Tea wine, List<Tea> wines) throws TeaVintageExistsException {
		this.wine = wine;
		this.wines = wines;

		for (Tea w : wines) {
			if ((w.getName()).equals(wine.getName())) {
				if ((w.getYear()).equals(wine.getYear())) {
					throw new TeaVintageExistsException(ErrorMessages.ALREADY_EXISTS.getMsg());
				}
			}

		}
	}
}
