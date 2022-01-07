package com.github.jendap.qibernate.model;

import com.github.jendap.qibernate.validator.ValidKitten;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true, of = {"name"})
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
