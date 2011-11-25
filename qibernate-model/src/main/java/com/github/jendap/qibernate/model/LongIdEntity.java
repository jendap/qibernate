package com.github.jendap.qibernate.model;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Transient;

import lombok.Data;

@Data
@MappedSuperclass
public class LongIdEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", unique = true, updatable = false, nullable = false)
	private Long id;

	protected LongIdEntity() {
	}

	@Transient
	public boolean isNew() {
		return this.id == null;
	}
}
