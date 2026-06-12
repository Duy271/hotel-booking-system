package com.net.hotel.utils;

import com.net.hotel.dto.UserDTO;
import com.net.hotel.entity.User;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.stream.Collectors;

public class Utils {
    private static final String ALPHANUMBERIC_STRING="QWERTYUIOPASDFGHJKLZXCVBNM";
    private static final SecureRandom SECURE_RANDOM=new SecureRandom();

    public static String genarateRandomAlphanumberic(int lenght)
    {
        StringBuilder stringBuilder=new StringBuilder();
        for (int i=0;i<lenght;i++)
        {
            int randomIndex=SECURE_RANDOM.nextInt(ALPHANUMBERIC_STRING.length());
            char randomChart=ALPHANUMBERIC_STRING.charAt(randomIndex);
            stringBuilder.append(randomChart);

        }
        return stringBuilder.toString();
    }

    public static UserDTO mapUserEntityToUserDTO(User user)
    {
        UserDTO userDTO=new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setName(user.getName());
        userDTO.setEmail(user.getEmail());
        userDTO.setRole(user.getRole());
        userDTO.setPhoneNumber(user.getPhoneNumber());
        return userDTO;
    }

    public static UserDTO mapUserEntityToUserDTOPlusUserBookingAndRoom(User user)
    {
        UserDTO userDTO=new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setName(user.getName());
        userDTO.setEmail(user.getEmail());
        userDTO.setRole(user.getRole());
        userDTO.setPhoneNumber(user.getPhoneNumber());
        if(!user.getBookings().isEmpty())
        {
            userDTO.setBookings(user.getBookings().stream().map(booking -> mapBookingEntityToBookingDTOPlusBookedRoom(booking, false)).collect(Collectors.toList()));
        }
        return userDTO;
    }

}
