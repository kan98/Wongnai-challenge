package com.wongnai.challenge.Entities;

import javax.persistence.*;

@Entity
@Table(name = "FoodDictionary", schema = "wongnai")
public class FoodDictionary {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private long id;
    private String keyword;
}
