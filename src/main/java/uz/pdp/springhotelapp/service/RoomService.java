package uz.pdp.springhotelapp.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import uz.pdp.springhotelapp.entity.Hotel;
import uz.pdp.springhotelapp.entity.Room;
import uz.pdp.springhotelapp.payload.RoomDTO;
import uz.pdp.springhotelapp.repository.HotelRepository;
import uz.pdp.springhotelapp.repository.RoomRepository;

import java.util.List;
import java.util.Optional;

@Service
public class RoomService {
    private final RoomRepository roomRepository;
    private final HotelRepository hotelRepository;

    public RoomService(RoomRepository roomRepository, HotelRepository hotelRepository) {
        this.roomRepository = roomRepository;
        this.hotelRepository = hotelRepository;
    }

    public ResponseEntity<List<Room>> getAll() {
        return new ResponseEntity<>(roomRepository.findAll(), HttpStatus.OK);
    }

    public ResponseEntity<Object> getOne(Long id) {
        Optional<Room> optionalRoom = roomRepository.findById(id);
        if (optionalRoom.isPresent()) {
            return new ResponseEntity<>(optionalRoom.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(String.format("Room id:[%s] not found", id), HttpStatus.NOT_FOUND);
    }

    public ResponseEntity<Page<Room>> getParamPageableValue(Long id, Integer page) {
        Pageable pageable = PageRequest.of(page, (page - 1) * 10);
        return new ResponseEntity<>(roomRepository.findAllByHotel_Id(id, pageable), HttpStatus.OK);
    }

    public ResponseEntity<Object> saveRoom(RoomDTO dto) {
        Optional<Hotel> optionalHotel = hotelRepository.findById(dto.getHotelId());
        if (optionalHotel.isPresent()) {
            Room room = new Room(dto.getHotelId(), dto.getNumber(), dto.getFloor(), dto.getSize(), optionalHotel.get());
            return new ResponseEntity<>(room, HttpStatus.CREATED);
        }
        return new ResponseEntity<>(String.format("Hotel id:[%s] not found", dto.getHotelId()), HttpStatus.NOT_FOUND);
    }

    public ResponseEntity<Object> updateRoom(Long id, RoomDTO dto) {
        Optional<Room> optionalRoom = roomRepository.findById(id);
        if (optionalRoom.isPresent()) {
            Room room = new Room(id, dto.getNumber(), dto.getFloor(), dto.getSize(), optionalRoom.get().getHotel());
            Optional<Hotel> optionalHotel = hotelRepository.findById(dto.getHotelId());
            if (optionalHotel.isPresent()) {
                room.setHotel(optionalHotel.get());
            }
            return new ResponseEntity<>(roomRepository.save(room), HttpStatus.OK);
        }
        return new ResponseEntity<>(String.format("Room id:[%s] not found", id), HttpStatus.NOT_FOUND);
    }

    public ResponseEntity<String> deleteRoom(Long id) {
        Optional<Room> optionalRoom = roomRepository.findById(id);
        if (optionalRoom.isPresent()) {
            try {
                return new ResponseEntity<>("Room successfully deleted", HttpStatus.OK);
            } catch (Exception e) {
                return new ResponseEntity<>("BAD REQUEST", HttpStatus.BAD_REQUEST);
            }
        }
        return new ResponseEntity<>(String.format("Room id:[%s] not found", id), HttpStatus.NOT_FOUND);
    }
}
