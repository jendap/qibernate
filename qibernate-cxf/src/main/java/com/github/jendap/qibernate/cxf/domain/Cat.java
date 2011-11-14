package com.github.jendap.qibernate.cxf.domain;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Cat {
	@XmlElement(required = true)
	private String name;
	private int age;

	public com.github.jendap.qibernate.model.Cat toModelCat() {
		return new com.github.jendap.qibernate.model.Cat(name, null, age);
	}

	public static Cat fromModelCat(final com.github.jendap.qibernate.model.Cat cat) {
		return new Cat(cat.getName(), cat.getAge());
	}

	public static List<Cat> fromModelCats(final List<com.github.jendap.qibernate.model.Cat> cats) {
		final List<Cat> result = new ArrayList<Cat>();
		for (final com.github.jendap.qibernate.model.Cat cat : cats) {
			result.add(fromModelCat(cat));
		}
		return result;
	}
}
