package com.example.lab1.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table
public class Wishlist {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToMany
    private List<Reservation> reservations = new ArrayList<>();

    public Wishlist() {
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Reservation> getReservations() {
        return reservations;
    }

    public void setReservations(List<Reservation> reservations) {
        this.reservations = reservations;
    }
}
