package com.github.jendap.qibernate.model;

import com.github.jendap.qibernate.validator.ValidKitten;
import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.*;

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
