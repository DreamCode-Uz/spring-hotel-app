package uz.pdp.springhotelapp.service;

import org.springframework.stereotype.Service;
import uz.pdp.springhotelapp.repository.HotelRepository;
import uz.pdp.springhotelapp.repository.RoomRepository;

@Service
public class RoomService {
    private final RoomRepository roomRepository;
    private final HotelRepository hotelRepository;

    public RoomService(RoomRepository roomRepository, HotelRepository hotelRepository) {
        this.roomRepository = roomRepository;
        this.hotelRepository = hotelRepository;
    }
}
