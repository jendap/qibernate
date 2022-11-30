package com.github.jendap.qibernate.validator;

import com.github.jendap.qibernate.model.Kitten;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class ValidKittenValidator implements ConstraintValidator<ValidKitten, Kitten> {
//	public static class Severity {
//		public static class Info implements Payload {
//		};
//
//		public static class Error implements Payload {
//		};
//	}

    private static String DEFAULT_HOMELESS_KITTEN_MESSAGE_TEMPLATE = "{ValidKitten.HomelessKitten}";

    private String message;

    @Override
    public void initialize(final ValidKitten constraintAnnotation) {
        this.message = constraintAnnotation.message();
    }

    @Override
    public boolean isValid(final Kitten value, final ConstraintValidatorContext context) {
        if (value == null) {
            return true;
        }

        if (value.getCat().getNest() == null) {
            context.disableDefaultConstraintViolation();
            final String messageTemplate;
            if (this.message != null) {
                messageTemplate = message;
            } else {
                messageTemplate = DEFAULT_HOMELESS_KITTEN_MESSAGE_TEMPLATE;
            }
            context.buildConstraintViolationWithTemplate(messageTemplate).addConstraintViolation();
            return false;
        } else {
            return true;
        }
    }
}
