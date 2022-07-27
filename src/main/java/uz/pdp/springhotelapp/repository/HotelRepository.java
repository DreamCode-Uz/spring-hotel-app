package uz.pdp.springhotelapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.pdp.springhotelapp.entity.Hotel;

@Repository
public interface HotelRepository extends JpaRepository<Hotel, Long> {
}
