package com.github.jendap.qibernate.cxf.domain;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

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
        return new com.github.jendap.qibernate.model.Cat(null, name, null, age, 0);
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
