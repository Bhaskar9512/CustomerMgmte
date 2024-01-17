package com.customer.util;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.function.Function;

@Component
public class JwtUtil {

private  static final long JWT_TOKEN_VALIDITY=5*60*60;
private static final String SECRET_KEY="yYoQJN4fZRO+FoGgqBvoYksSt3OprJmdQsQXZmQeN0E1cFA/EALJa0bGPXpydE4uHpgOZZ+i0FWv5v5OBtUQIw==";


private long expirationTime=86400000;

public String generateToken(String username) {
    return Jwts.builder()
            .setSubject(username)
            .setIssuedAt(new Date(System.currentTimeMillis()))
            .setExpiration(new Date(System.currentTimeMillis() + expirationTime))
            .signWith(SignatureAlgorithm.HS256,SECRET_KEY )
            .compact();
}

public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
    final Claims claims = extractAllClaims(token);
    return claimsResolver.apply(claims);
}

private Claims extractAllClaims(String token) {
    return Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody();
}

public String extractUsername(String token) {
    return extractClaim(token, Claims::getSubject);
}

public Date extractExpiration(String token) {
    return extractClaim(token, Claims::getExpiration);
}

private Boolean isTokenExpired(String token) {
    return extractExpiration(token).before(new Date());
}

public Boolean validateToken(String token, UserDetails userDetails) {
    final String username = extractUsername(token);
    return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
}

public String generateToken(UserDetails userDetails) {
	// TODO Auto-generated method stub
	return null;
}
}
