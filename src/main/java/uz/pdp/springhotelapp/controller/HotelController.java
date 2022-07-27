package uz.pdp.springhotelapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.springhotelapp.entity.Hotel;
import uz.pdp.springhotelapp.service.HotelService;

import java.util.List;

@RestController
@RequestMapping("/api/hotel")
public class HotelController {
    private final HotelService service;

    @Autowired
    public HotelController(HotelService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<Hotel>> hotels() {
        return service.getAll();
    }

    @GetMapping("/id={hotelId}")
    public ResponseEntity<Object> oneHotel(@PathVariable("hotelId") Long id) {
        return service.getHotel(id);
    }

    @PostMapping
    public ResponseEntity<Object> save(@RequestBody Hotel hotel) {
        return service.saveHotel(hotel);
    }

    @PutMapping("/id={hotelId}")
    public ResponseEntity<Object> update(@PathVariable("hotelId") Long id, @RequestBody Hotel hotel) {
        return service.updateHotel(id, hotel);
    }

    @DeleteMapping("/id={hotelId}")
    public ResponseEntity<String> delete(@PathVariable("hotelId") Long id) {
        return service.deleteHotel(id);
    }
}
