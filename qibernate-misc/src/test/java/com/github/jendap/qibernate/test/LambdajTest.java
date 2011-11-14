package com.github.jendap.qibernate.test;

import static ch.lambdaj.Lambda.*;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;
import static java.util.Arrays.*;

import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import org.junit.Test;

import ch.lambdaj.function.matcher.Predicate;


public class LambdajTest {
	@Test
	public void testLambdaj() {
		final List<ObjectO> objectsOs = new ArrayList<ObjectO>();
		for (int i = 1; i < 10; i++) {
			objectsOs.add(new ObjectO(i, Integer.toString(2 * (i / 2) + 1)));
		}

		assertEquals(1, ((ObjectO) selectMin(objectsOs, on(ObjectO.class).getIntI())).getIntI());
		assertEquals(5, selectDistinct(objectsOs, "stringS").size());
		assertEquals(2, select(objectsOs, having(on(ObjectO.class).getStringS(), equalTo("5"))).size());
		assertEquals(9, sum(asList(1, 3, 5)));
		assertEquals("foo_bar_baz", join(asList("foo", "bar", "baz"), "_"));
		assertEquals(2, group(objectsOs, by(on(ObjectO.class).getStringS())).findGroup("9").getSize());
		assertEquals(7, filter(new Predicate<ObjectO>() {
			@Override
			public boolean apply(final ObjectO item) {
				return !item.getStringS().equals("3");
			}
		}, objectsOs).size());
	}

	@Data
	@NoArgsConstructor
	@AllArgsConstructor
	public static class ObjectO {
		private int intI;
		private String stringS;
	}
}
