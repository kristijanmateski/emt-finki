package com.example.lab1.service;

import com.example.lab1.model.Host;
import com.example.lab1.model.Reservation;
import com.example.lab1.model.dto.ReservationDto;
import com.example.lab1.repository.HostRepository;
import com.example.lab1.repository.ReservationRepository;
import org.springframework.stereotype.Service;

import java.util.List;
//http://localhost:8080/swagger-ui/index.html#/
@Service
public class ReservationService {
    private final ReservationRepository reservationRepository;
    private final HostRepository hostRepository;

    public ReservationService(ReservationRepository reservationRepository, HostRepository hostRepository) {
        this.reservationRepository = reservationRepository;
        this.hostRepository = hostRepository;
    }

    public List<Reservation> findAll(){
        return this.reservationRepository.findAll();
    }
    public Reservation save(ReservationDto reservationDto) throws Exception {
        //so ova go zemame id-to na host
        Host host = hostRepository.findById(reservationDto.getHostId()).orElseThrow(Exception::new);
        Reservation reservation = new Reservation(reservationDto.getName(),reservationDto.getCategory(),host,reservationDto.getNumRooms());
        return this.reservationRepository.save(reservation);
    }
    public Reservation update(Long reservationId, ReservationDto reservationDto) throws Exception {
        Host host = hostRepository.findById(reservationDto.getHostId()).orElseThrow(Exception::new);
        Reservation reservation = this.reservationRepository.findById(reservationId).orElseThrow(Exception::new);
        reservation.setName(reservationDto.getName());
        reservation.setCategory(reservationDto.getCategory());
        reservation.setHost(host);
        reservation.setNumRooms(reservationDto.getNumRooms());
        return this.reservationRepository.save(reservation);
    }

    public void delete(Long reservationId){
        this.reservationRepository.deleteById(reservationId);
    }

    public Reservation addReservation(Long reservationId) throws Exception {
        Reservation reservation = this.reservationRepository.findById(reservationId).orElseThrow(Exception::new);

        //ako e true, znachi nema vishe mesto
        if (reservation.isReserved()){
            return reservation;
        }
        // ako e posledna soba
        if(reservation.getNumRooms() - 1 == 0){
            reservation.setNumRooms(reservation.getNumRooms()-1);
            reservation.setReserved(true);
            return this.reservationRepository.save(reservation);
        }else{
            //ako ima poveke od 1 soba
            reservation.setNumRooms(reservation.getNumRooms()-1);
            return reservationRepository.save(reservation);
        }
    }
}
