package com.github.jendap.qibernate.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.validation.Payload;

import com.github.jendap.qibernate.model.Kitten;


public class ValidKittenValidator implements
		ConstraintValidator<ValidKitten, Kitten> {
	public static class Severity {
		public static class Info implements Payload {
		};

		public static class Error implements Payload {
		};
	}

	@Override
	public void initialize(final ValidKitten constraintAnnotation) {
		// we can save the annotation for later use
	}

	@Override
	public boolean isValid(final Kitten value,
			final ConstraintValidatorContext context) {
		if (value == null) {
			return true;
		}

		if (value.getCat().getNest() == null) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(
					"{com.github.jendap.qibernate.constraints.ValidKitten.HomelessKitten}")
					.addConstraintViolation();
			return false;
		} else {
			return true;
		}
	}
}
