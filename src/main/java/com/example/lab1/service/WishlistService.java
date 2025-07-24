package com.example.lab1.service;

import com.example.lab1.model.Reservation;
import com.example.lab1.model.UserEntity;
import com.example.lab1.model.Wishlist;
import com.example.lab1.model.dto.DisplayWishlistDto;
import com.example.lab1.repository.ReservationRepository;
import com.example.lab1.repository.UserRepository;
import com.example.lab1.repository.WishlistRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class WishlistService {
    private final WishlistRepository wishlistRepository;
    private final UserRepository userRepository;
    private final ReservationRepository reservationRepository;
    private final ReservationService reservationService;

    public WishlistService(WishlistRepository wishlistRepository, UserRepository userRepository, ReservationRepository reservationRepository, ReservationService reservationService) {
        this.wishlistRepository = wishlistRepository;
        this.userRepository = userRepository;
        this.reservationRepository = reservationRepository;
        this.reservationService = reservationService;
    }

    public List<Wishlist> findAll(){
        return this.wishlistRepository.findAll();
    }

    public DisplayWishlistDto addReservationToWishlist(String username, Long reservationId) throws Exception {
        UserEntity user = userRepository.findByUsername(username).orElseThrow(Exception::new);
        Reservation reservation = reservationRepository.findById(reservationId).orElseThrow(Exception::new);

        if(!reservation.isReserved()){
            Wishlist wishlist = new Wishlist();
            wishlist.getReservations().add(reservation);
            wishlistRepository.save(wishlist);
            return DisplayWishlistDto.toDisplayWishlistDto(wishlist.getReservations());
        }else {
            throw new Exception("Wishlist is already reserved");
        }
    }

    public DisplayWishlistDto clearWishlist(String username) throws Exception {
        UserEntity user = userRepository.findByUsername(username).orElseThrow(Exception::new);
        Wishlist wishlist = user.getWishlist();
        List<Reservation> reservations = wishlist.getReservations();

        for (Reservation reservation : reservations) {
            reservationService.addReservation(reservation.getId());
        }

        wishlist.getReservations().clear();
        wishlistRepository.save(wishlist);

        return DisplayWishlistDto.toDisplayWishlistDto(reservations);
    }
}
