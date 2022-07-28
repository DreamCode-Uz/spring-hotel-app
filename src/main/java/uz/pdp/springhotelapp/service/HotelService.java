package uz.pdp.springhotelapp.service;

import ch.qos.logback.core.encoder.EchoEncoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import uz.pdp.springhotelapp.entity.Hotel;
import uz.pdp.springhotelapp.repository.HotelRepository;

import java.util.List;
import java.util.Optional;

@Service
public class HotelService {
    private final HotelRepository repository;

    @Autowired
    public HotelService(HotelRepository repository) {
        this.repository = repository;
    }

    public ResponseEntity<List<Hotel>> getAll() {
        return new ResponseEntity<>(repository.findAll(), HttpStatus.OK);
    }

    public ResponseEntity<Object> getHotel(Long hotelId) {
        Optional<Hotel> optionalHotel = repository.findById(hotelId);
        if (optionalHotel.isPresent())
            return new ResponseEntity<>(optionalHotel.get(), HttpStatus.OK);
        return new ResponseEntity<>(String.format("Hotel not found id:[%s]", hotelId), HttpStatus.NOT_FOUND);
    }

    public ResponseEntity<Object> saveHotel(Hotel hotel) {
        try {
            return new ResponseEntity<>(repository.save(hotel), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Hotel data not saved", HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<Object> updateHotel(Long hotelId, Hotel hotel) {
        Optional<Hotel> optionalHotel = repository.findById(hotelId);
        if (optionalHotel.isPresent()) {
            return new ResponseEntity<>(repository.save(new Hotel(optionalHotel.get().getId(), hotel.getName())), HttpStatus.OK);
        }
        return new ResponseEntity<>(String.format("Hotel not found hotelId:[%s]", hotelId), HttpStatus.NOT_FOUND);
    }

    public ResponseEntity<String> deleteHotel(Long id) {
        Optional<Hotel> optionalHotel = repository.findById(id);
        if (optionalHotel.isPresent()) {
            repository.delete(optionalHotel.get());
            try {
                return new ResponseEntity<>("Hotel successfully deleted", HttpStatus.OK);
            } catch (Exception e) {
                return new ResponseEntity<>("BAD REQUEST", HttpStatus.BAD_REQUEST);
            }
        }
        return new ResponseEntity<>(String.format("Hotel id:[%s] not found", id), HttpStatus.NOT_FOUND);
    }
}
