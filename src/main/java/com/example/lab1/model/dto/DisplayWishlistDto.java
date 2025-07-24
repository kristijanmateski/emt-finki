package com.example.lab1.model.dto;

import com.example.lab1.model.Enum.Category;
import com.example.lab1.model.Reservation;
import com.example.lab1.model.UserEntity;
import com.example.lab1.model.Wishlist;

import java.util.ArrayList;
import java.util.List;

public record DisplayWishlistDto(List<String> reservationNames){
    public static DisplayWishlistDto toDisplayWishlistDto(List<Reservation> reservations) {
        List<String> reservationNames = new ArrayList<>();
        for(Reservation reservation : reservations) {
            reservationNames.add(reservation.getName());
        }
        return new DisplayWishlistDto(reservationNames);
    }
}