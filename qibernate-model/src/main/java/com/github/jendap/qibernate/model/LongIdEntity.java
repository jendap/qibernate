package com.github.jendap.qibernate.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@MappedSuperclass
public class LongIdEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", unique = true, updatable = false, nullable = false)
    @Access(AccessType.PROPERTY) // see HHH-3718
    private Long id;

    protected LongIdEntity() {
    }

    @Transient
    public boolean isNew() {
        return this.id == null;
    }
}
