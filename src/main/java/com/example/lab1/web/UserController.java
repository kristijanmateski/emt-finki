package com.example.lab1.web;

import com.example.lab1.model.dto.CreateUserDto;
import com.example.lab1.model.dto.DisplayUserDto;
import com.example.lab1.model.dto.UserLoginDto;
import com.example.lab1.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @Operation(summary = "Register", description = "Register user")
    @PostMapping("/register")
    public ResponseEntity<Void> register(@RequestBody CreateUserDto createUserDto){
        userService.register(createUserDto);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "User login", description = "Authenticates a user and starts a session")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "User authenticated successfully"
            ), @ApiResponse(
                    responseCode = "404",
                    description = "User not found"
            )
    })
    @PostMapping("/login")
    public ResponseEntity<DisplayUserDto> login(@RequestBody UserLoginDto userLoginDto, HttpServletRequest request){
        try{
            DisplayUserDto user = userService.login(userLoginDto.username(), userLoginDto.password());
            request.getSession().setAttribute("user", user);
            return ResponseEntity.ok(user);
        }catch (Exception e){
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(summary = "Logout", description = "Bye, see you again!")
    @GetMapping("/logout")
    public void logout(HttpServletRequest request){
        request.getSession().invalidate();
    }
}
