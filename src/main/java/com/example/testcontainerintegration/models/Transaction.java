package com.example.testcontainerintegration.models;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@NoArgsConstructor
@Data
public class Transaction {
    @Id
    private String id;
    @Column
    private String name;
    @Column
    private String description;
}
