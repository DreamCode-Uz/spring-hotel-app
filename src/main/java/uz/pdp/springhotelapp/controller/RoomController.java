package uz.pdp.springhotelapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.springhotelapp.entity.Room;
import uz.pdp.springhotelapp.payload.RoomDTO;
import uz.pdp.springhotelapp.service.RoomService;

import java.util.List;

@RestController
@RequestMapping("/api/room")
public class RoomController {

    private final RoomService service;

    @Autowired
    public RoomController(RoomService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<Room>> rooms() {
        return service.getAll();
    }

    @GetMapping("/hotel/id={hotelId}")
    public ResponseEntity<Page<Room>> getPageableRoom(@PathVariable("hotelId") Long id, @RequestParam(value = "page", defaultValue = "1") Integer page) {
        return service.getParamPageableValue(id, page);
    }

    @PostMapping
    public ResponseEntity<Object> save(@RequestBody RoomDTO dto) {
        return service.saveRoom(dto);
    }

    @PutMapping("/id={roomId}")
    public ResponseEntity<Object> update(@PathVariable("roomId") Long id, @RequestBody RoomDTO roomDTO) {
        return service.updateRoom(id, roomDTO);
    }

    @DeleteMapping("/id={roomId}")
    public ResponseEntity<String> delete(@PathVariable("roomId") Long id) {
        return service.deleteRoom(id);
    }
}
