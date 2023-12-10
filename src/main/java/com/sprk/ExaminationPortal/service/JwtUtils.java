package com.sprk.ExaminationPortal.service;

import com.sprk.ExaminationPortal.Exception.JwtException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class JwtUtils {

    @Value("${app.jwt.secret}")
    private String jwtSecret;

    private long defaultExpirationMilis = 1800000;

    private Key getSignKey() {
        byte[] keyBytes = Decoders.BASE64.decode(jwtSecret);
        return Keys.hmacShaKeyFor(keyBytes);
    }


    private Claims getClaims(String authToken) {
        try {
            return Jwts.parserBuilder()
                    .setSigningKey(getSignKey())
                    .build()
                    .parseClaimsJws(authToken)
                    .getBody();
        } catch (Exception ex) {
            throw new JwtException(ex.getMessage());
        }
    }


    private String generateToken(String subject, Map<String, Object> claimsMap, long expirationDate) {
        return Jwts.builder()
                .setSubject(subject)
                .addClaims(claimsMap)
                .setIssuedAt(new Date())
                .setExpiration(new Date(new Date().getTime() + expirationDate))
                .signWith(getSignKey(), SignatureAlgorithm.HS256)
                .compact();
    }


    public String generateToken(String identifier, Long upt) {
        Map<String, Object> claimsMap = new HashMap<>();
        claimsMap.put("upt", upt);

        return generateToken(
                identifier,
                claimsMap,
                defaultExpirationMilis
        );
    }
}
