package com.example.lab1.web;

import com.example.lab1.model.Wishlist;
import com.example.lab1.model.dto.AddReservationToWishlistDto;
import com.example.lab1.model.dto.DisplayUserDto;
import com.example.lab1.model.dto.DisplayWishlistDto;
import com.example.lab1.service.WishlistService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/wishlist")
public class WishlistController {
    private final WishlistService wishlistService;

    public WishlistController(WishlistService wishlistService) {
        this.wishlistService = wishlistService;
    }

    @Operation(summary = "Add to wishlist", description = "Add your favorite bookings into wishlist")
    @PostMapping("/add")
    public ResponseEntity<DisplayWishlistDto> addToWishlist(@RequestBody AddReservationToWishlistDto addReservationToWishlistDto, HttpServletRequest httpServletRequest) throws Exception {
        DisplayUserDto user = (DisplayUserDto) httpServletRequest.getSession().getAttribute("user");
        return ResponseEntity.ok(wishlistService.addReservationToWishlist(user.username(), addReservationToWishlistDto.reservatoinId()));
    }

    @Operation(summary = "Clear the wishlist", description = "Make them reality")
    @GetMapping("/clear")
    public ResponseEntity<DisplayWishlistDto> clearWishlist(HttpServletRequest httpServletRequest) throws Exception {
        DisplayUserDto user = (DisplayUserDto) httpServletRequest.getSession().getAttribute("user");
        return ResponseEntity.ok(wishlistService.clearWishlist(user.username()));
    }
}
