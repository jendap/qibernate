package com.github.jendap.qibernate.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.MappedSuperclass;
import javax.persistence.Version;

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@MappedSuperclass
public class VersionedLongIdEntity extends LongIdEntity {
    @Version
    private int version;

    protected VersionedLongIdEntity() {
    }
}
