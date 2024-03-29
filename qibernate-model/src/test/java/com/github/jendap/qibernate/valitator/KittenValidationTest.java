package com.github.jendap.qibernate.valitator;

import com.github.jendap.qibernate.model.Cat;
import com.github.jendap.qibernate.model.Kitten;
import com.github.jendap.qibernate.model.Nest;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;

public class KittenValidationTest {
    private static Validator validator;

    private Cat cat0;
    private Cat cat1;

    @BeforeAll
    public static void setUpClass() {
        try (ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory()) {
            validator = validatorFactory.getValidator();
        }
    }

    @BeforeEach
    public void setUp() {
        final Nest nest0 = new Nest("nest0.name", "nest0.address");
        this.cat0 = new Cat(null, "cat0.name", nest0, 5, 10);
        this.cat1 = new Cat(null, "cat1.name", null, 10, 10);
    }

    @Test
    public void testKittenValidation() {
        final Kitten validKitten = new Kitten(this.cat0, "kitten0.name", 1);
        final Set<ConstraintViolation<Kitten>> validKittenViolation = validator.validate(validKitten);
        Assertions.assertEquals(0, validKittenViolation.size());

        final Kitten invalidKitten = new Kitten(this.cat1, "kitten1.name", -100);
        final Set<ConstraintViolation<Kitten>> invalidKittenViolation = validator.validate(invalidKitten);
        Assertions.assertEquals(2, invalidKittenViolation.size());
    }
}
