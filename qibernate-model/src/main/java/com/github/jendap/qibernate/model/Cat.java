package com.github.jendap.qibernate.model;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.Cacheable;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Version;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;


@Data
@NoArgsConstructor
@Entity
@Cacheable
@ToString(of = "id")
public class Cat implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", updatable = false, nullable = false)
	private Long id;

	@Version
	private int version;

	@ElementCollection
	@OneToMany(mappedBy = "cat", cascade = CascadeType.ALL)
	private Collection<Kitten> kittens;

	@NotNull
	private String name;

	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Nest nest;

	private int age;

	@Min(0) @Max(100)
	private int hunger;

	public Cat(final String name, final Nest nest, final int age, final int hunger) {
		this.name = name;
		this.nest = nest;
		this.age = age;
		this.hunger = hunger;
	}
}
