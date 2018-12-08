package com.craitz.comexport.services;

import java.util.regex.Pattern;

import org.springframework.stereotype.Service;

@Service
public class GregorianDateMatcherService implements DateMatcher {
	// monta o regex para validar a data
	private static Pattern DATE_PATTERN = Pattern.compile(
		"^((2000|2400|2800|(19|2[0-9](0[48]|[2468][048]|[13579][26])))02-29)$"
		+ "|^(((19|2[0-9])[0-9]{2})02(0[1-9]|1[0-9]|2[0-8]))$"
		+ "|^(((19|2[0-9])[0-9]{2})(0[13578]|10|12)(0[1-9]|[12][0-9]|3[01]))$"
		+ "|^(((19|2[0-9])[0-9]{2})(0[469]|11)(0[1-9]|[12][0-9]|30))$");

	@Override
	public boolean matches(String date) {
		// valida a data
		return DATE_PATTERN.matcher(date).matches();
	}
}