package com.net.hotel.repo;

import com.net.hotel.entity.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BookingRepository extends JpaRepository<Booking,Long> {
    //@Query("SELECT b FROM Booking b WHERE b.id=:roomId")
    List<Booking> findByRoomId(Long roomId);
    List<Booking> findByBookingConfirmationCode(String conformationCode);
    List<Booking> findByUserId(Long userId);
}
