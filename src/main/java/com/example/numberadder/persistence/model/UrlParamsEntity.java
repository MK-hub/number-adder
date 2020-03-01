package com.example.numberadder.persistence.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class UrlParamsEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private int minValue;
    private int maxValue;
    private int baseValue;
}
