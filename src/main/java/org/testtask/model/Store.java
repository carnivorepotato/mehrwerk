package org.testtask.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "STORE")
public class Store {
    @Id
    @Column(name = "id")
    private String id;
    @Column(name = "name")
    private String name;
    @Column(name = "description", length = 1023)
    private String description;
    @ManyToMany(targetEntity = org.testtask.model.Category.class,
            fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Category> categories;
}
