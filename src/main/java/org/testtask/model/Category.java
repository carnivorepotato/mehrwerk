package org.testtask.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "CATEGORY")
public class Category {
    @Id
    @Column(name = "id")
    private String id;
    @Column(name = "name")
    private String name;
}


