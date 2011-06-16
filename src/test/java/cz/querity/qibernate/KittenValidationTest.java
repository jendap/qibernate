package cz.querity.qibernate;

import static org.junit.Assert.assertEquals;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import cz.querity.qibernate.model.Cat;
import cz.querity.qibernate.model.Kitten;
import cz.querity.qibernate.model.Nest;

public class KittenValidationTest {
	private static ValidatorFactory validatorFactory;
	private static Validator validator;

	private Cat cat0;
	private Cat cat1;

	@BeforeClass
	public static void setUpClass() {
		validatorFactory = Validation.buildDefaultValidatorFactory();
		validator = validatorFactory.getValidator();
	}

	@Before
	public void setUp() {
		final Nest nest0 = new Nest("foo", "bar");
		this.cat0 = new Cat("roztleskavacka", null, 5);
		this.cat1 = new Cat("foo", nest0, 50);
	}

	@Test
	public void testKittenValidation() {
		final Kitten validKitten = new Kitten();
		validKitten.setCat(this.cat0);
		final Set<ConstraintViolation<Kitten>> validKittenViolation = validator.validate(validKitten);
		assertEquals(0, validKittenViolation.size());

		final Kitten invalidKitten = new Kitten();
		invalidKitten.setPrice(-100);
		invalidKitten.setCat(this.cat1);
		final Set<ConstraintViolation<Kitten>> invalidKittenViolation = validator.validate(invalidKitten);
		assertEquals(2, invalidKittenViolation.size());
	}
}
