package com.github.jendap.qibernate.model;

import javax.persistence.Cacheable;
import javax.persistence.Entity;
import javax.persistence.Table;
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
@Table(name = "nest")
@Cacheable
public class Nest extends VersionedLongIdEntity {
	@NotNull
	private String name;

	private String address;
}
