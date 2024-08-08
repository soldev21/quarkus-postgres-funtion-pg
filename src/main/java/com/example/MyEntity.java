package com.example;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.EqualsAndHashCode;

@Entity
@EqualsAndHashCode
public class MyEntity {
    @Id
    Long id;
}
