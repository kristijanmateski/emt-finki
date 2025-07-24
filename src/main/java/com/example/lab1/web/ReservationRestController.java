package com.example.lab1.web;

import com.example.lab1.model.Reservation;
import com.example.lab1.model.dto.ReservationDto;
import com.example.lab1.service.ReservationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ReservationRestController {
    private final ReservationService reservationService;

    public ReservationRestController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @GetMapping
    public ResponseEntity<List<Reservation>> findAll(){
        return ResponseEntity.ok(reservationService.findAll());
    }

    @PostMapping
    public ResponseEntity<Reservation> save(@RequestBody ReservationDto reservationDto) throws Exception {
        return ResponseEntity.ok(reservationService.save(reservationDto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Reservation> update(@PathVariable Long id, @RequestBody ReservationDto reservationDto) throws Exception {
        return ResponseEntity.ok(reservationService.update(id, reservationDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        reservationService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{id}")
    public ResponseEntity<Reservation> addReservation(@PathVariable Long id) throws Exception {
        return ResponseEntity.ok(reservationService.addReservation(id));
    }
}
