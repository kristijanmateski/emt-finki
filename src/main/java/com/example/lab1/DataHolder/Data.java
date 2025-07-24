package com.example.lab1.DataHolder;

import com.example.lab1.model.Country;
import com.example.lab1.model.Enum.Category;
import com.example.lab1.model.Host;
import com.example.lab1.model.Reservation;
import com.example.lab1.repository.CountryRepository;
import com.example.lab1.repository.HostRepository;
import com.example.lab1.repository.ReservationRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

@Component
public class Data {
    private final HostRepository hostRepository;
    private final CountryRepository countryRepository;
    private final ReservationRepository reservationRepository;

    public Data(HostRepository hostRepository, CountryRepository countryRepository, ReservationRepository reservationRepository) {
        this.hostRepository = hostRepository;
        this.countryRepository = countryRepository;
        this.reservationRepository = reservationRepository;
    }

    @PostConstruct
    public void init(){
        Country country = new Country("Macedonia","Europe");
        countryRepository.save(country);
        Country country2 = new Country("Germany","Europe");
        countryRepository.save(country2);

        Host host = new Host("Host1","Host1",country);
        hostRepository.save(host);
        Host host2 = new Host("Host2","Host2",country2);
        hostRepository.save(host2);

        Reservation reservation = new Reservation("Booking1", Category.APARTMENT, host, 5);
        reservationRepository.save(reservation);
    }
}
