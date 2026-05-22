package com.net.hotel.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.function.Function;

@Service
public class JWTUtils {

    // token sống 7 ngày
    private static final long EXPIRATION_TIME =
            1000 * 60 * 60 * 24 * 7;

    // khóa bí mật dùng tạo JWT
    private final SecretKey key;

    public JWTUtils()
    {
        String secretString = "nguyenkhanhduy2701nguyenkhanhduy2701";
        this.key = Keys.hmacShaKeyFor(secretString.getBytes(StandardCharsets.UTF_8));
    }

    // tạo token
    public String generateToken(UserDetails userDetails)
    {
        return Jwts.builder()
                .subject(userDetails.getUsername())
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(key)
                .compact();
    }

    // đọc toàn bộ payload
    public Claims extractAllClaims(String token)
    {
        return Jwts.parser()
                .verifyWith(key)
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    // lấy dữ liệu bất kỳ
    public <T> T extractClaims(String token, Function<Claims,T> resolver)
    {
        Claims claims = extractAllClaims(token);
        return resolver.apply(claims);
    }

    // lấy username
    public String extractUsername(String token)
    {
        return extractClaims(token, Claims::getSubject);
    }

    // lấy ngày hết hạn
    public Date extractExpiration(String token)
    {
        return extractClaims(token, Claims::getExpiration);
    }

    // kiểm tra token hết hạn
    public boolean isTokenExpired(String token)
    {
        return extractExpiration(token).before(new Date());
    }

    // xác thực token
    public boolean validateToken(String token, UserDetails userDetails)
    {
        String username = extractUsername(token);
        return username.equals(userDetails.getUsername()) && !isTokenExpired(token);
    }

}