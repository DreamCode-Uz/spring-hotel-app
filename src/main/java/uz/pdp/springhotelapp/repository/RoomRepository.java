package uz.pdp.springhotelapp.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.pdp.springhotelapp.entity.Room;

@Repository
public interface RoomRepository extends JpaRepository<Room, Long> {

    Page<Room> findAllByHotel_Id(Long hotel_id, Pageable pageable);
}
