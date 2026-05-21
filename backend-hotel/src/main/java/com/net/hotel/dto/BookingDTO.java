package com.net.hotel.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.net.hotel.entity.Room;
import com.net.hotel.entity.User;
import lombok.Data;
import java.time.LocalDate;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BookingDTO {
    private Long id;
    private LocalDate checkInDate;
    private LocalDate checkOutDate;
    private Integer numOfAdults;
    private Integer numOfChildren;
    private Integer totalNumOfGuest;
    private String bookingConfirmationCode;
    private User user;
    private Room room;
}
