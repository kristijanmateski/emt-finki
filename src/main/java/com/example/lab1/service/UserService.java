package com.example.lab1.service;

import com.example.lab1.model.UserEntity;
import com.example.lab1.model.Wishlist;
import com.example.lab1.model.dto.CreateUserDto;
import com.example.lab1.model.dto.DisplayUserDto;
import com.example.lab1.repository.UserRepository;
import com.example.lab1.repository.WishlistRepository;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final WishlistRepository wishlistRepository;

    public UserService(UserRepository userRepository, WishlistRepository wishlistRepository) {
        this.userRepository = userRepository;
        this.wishlistRepository = wishlistRepository;
    }

    public void register(CreateUserDto createUserDto) {
        //prvo go kreirame wishlistot deka e zavisno vo UserEntity modelot
        Wishlist wishlist = new Wishlist();
        wishlistRepository.save(wishlist);
        UserEntity user = createUserDto.toUser();
        user.setWishlist(wishlist);
        userRepository.save(user);
    }

    public DisplayUserDto login(String username, String password) throws UsernameNotFoundException {
        Optional<UserEntity> user = userRepository.findByUsername(username);
        if(user.isEmpty()){
            throw new UsernameNotFoundException("User not found");
        }
        if(!user.get().getPassword().equals(password)){
            throw new UsernameNotFoundException("Wrong password");
        }
        return DisplayUserDto.fromUserToDisplayUserDto(user.get());
    }
}
