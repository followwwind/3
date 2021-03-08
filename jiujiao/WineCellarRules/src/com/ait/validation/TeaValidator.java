package com.ait.validation;

import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

import com.ait.tea.Tea;

public class TeaValidator {
	Tea tea;
	List<String> badYears = Arrays.asList("2001", "2005", "1998");
	List<String> countries = Arrays.asList("FRANCE", "SPAIN","GREECE");
	private static final int YEARS_OLD = 5;
	

	public void validateTea(Tea tea) throws TeaValidationException {
		this.tea = tea;
		checkEmptyFields(tea);
		checkValidCountry(tea, countries);
		checkBadYears(tea, badYears);
		checkIfMature(tea, YEARS_OLD);
	}
	

	private void checkEmptyFields(Tea tea) throws TeaValidationException {
		if ((tea.getName().length() == 0) || (tea.getCountry().length() == 0) || (tea.getYear().length() == 0)
				|| (tea.getGrapes().length() == 0)) {
			throw new TeaValidationException(ErrorMessages.EMPTY_FIELDS.getMsg());
		}
	}

	private void checkValidCountry(Tea tea, List<String> countries) throws TeaValidationException {
		boolean countryOK = false;
		String countryEntered = tea.getCountry().toUpperCase();
		for (String country : countries) {
			if (country.equals(countryEntered)) {
				countryOK = true;
				break;
			}
		}
		if (countryOK == false) {
			throw new TeaValidationException(ErrorMessages.INVALID_COUNTRY.getMsg());
		}
	}

	private void checkBadYears(Tea tea, List<String> badYears) throws TeaValidationException {
		if (badYears.contains(tea.getYear())) {
			throw new TeaValidationException(ErrorMessages.BAD_YEAR.getMsg());
		}
	}

	private void checkIfMature(Tea tea, int yearsOld) throws TeaValidationException {
		Calendar now = Calendar.getInstance();
		int currentYear = now.get(Calendar.YEAR);
		int teaYear = Integer.parseInt(tea.getYear());
		if (teaYear + yearsOld >= currentYear) {
			throw new TeaValidationException(ErrorMessages.NOT_MATURE.getMsg());
		}
	}

}
