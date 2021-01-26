package com.ey.core.annotations;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class NotAllowedValidator implements ConstraintValidator<NotAllowed, String> {

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {

		return value.matches("^[a-zA-Z0-9_]+$");
	}

}
