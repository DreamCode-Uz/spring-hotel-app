package uz.pdp.springhotelapp.payload;

import lombok.Data;

@Data
public class RoomDTO {
    private Long hotelId;
    private Integer number;
    private Integer size;
    private Integer floor;
}
