package com.github.jendap.qibernate.model;

import java.util.Collection;

import javax.persistence.Cacheable;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true, of = { "name" })
@Entity
@Table(name = "cat")
@Cacheable
public class Cat extends VersionedLongIdEntity {
//	@ElementCollection
	@OneToMany(mappedBy = "cat", fetch = FetchType.LAZY)
	private Collection<Kitten> kittens;

	@NotNull
	private String name;

	@OneToOne(fetch = FetchType.LAZY)
	private Nest nest;

	@Min(0)
	private int age;

	@Min(0)
	@Max(100)
	private int hunger;
}
