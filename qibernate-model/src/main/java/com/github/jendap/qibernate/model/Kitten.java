package com.github.jendap.qibernate.model;

import javax.persistence.Cacheable;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.PreRemove;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import com.github.jendap.qibernate.validator.ValidKitten;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true, of = { "name" })
@Entity
@Table(name = "kitten")
@ValidKitten
@Cacheable
public class Kitten extends VersionedLongIdEntity {
	@NotNull
	@ManyToOne
	private Cat cat;

	@NotNull
	private String name;

	@Min(0)
	@Max(1000000)
	private int price;

	@PreRemove
	public void preRemove() {
		this.setCat(null);
	}
}
