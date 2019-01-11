package com.corey.vidlib.entity;

import javax.persistence.*;

@javax.persistence.Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name="entity")
public abstract class Entity {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="entity_id")
    private int id;

    public Entity() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
