package com.github.jendap.qibernate.model;

import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.Version;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

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
