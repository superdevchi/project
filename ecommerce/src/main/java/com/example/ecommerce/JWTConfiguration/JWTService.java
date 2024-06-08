package com.example.ecommerce.JWTConfiguration;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JWTService {
//    write functions to decode jjwt from authenticaton filter, get usernamr, and claims

    public String getUsernameEmailFromJWT(String token){
        return getSingleClaim(token, Claims::getSubject);
    }

    public Boolean isTokenValid(String token, UserDetails userDetails){
        final String username = getUsernameEmailFromJWT(token);

        return (username.equals(userDetails.getUsername())) && !isTokenExpired(token);
    }

    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    private Date extractExpiration(String token) {

        return getSingleClaim(token,  Claims::getExpiration);
    }

//    public String generateTokenNoClaims(UserDetails userDetails){
//        return Jwts
//                .builder()
//                .setSubject(userDetails.getUsername())
//                .setIssuedAt(new Date(System.currentTimeMillis()))
//                .signWith(getSIGNINkey(), SignatureAlgorithm.HS256)
//                .compact();
//
//
//    }
//    generate token with no claims
    public String generateToken(UserDetails userDetails){

        return generateToken(new HashMap<>(), userDetails);
    }
    public String generateToken(
            Map<String, Object> extraclaims,
            UserDetails userDetails
    ){
        return Jwts
                .builder()
                .claims(extraclaims)
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .signWith(getSIGNINkey(), SignatureAlgorithm.HS256)
                .compact();
    }
    public <T> T getSingleClaim(String token, Function<Claims, T> claimsResolver){
        final Claims claims = getAllClaims(token);

        return  claimsResolver.apply(claims);
    }

    public Claims getAllClaims(String token){

        return Jwts
                .parser()
                .setSigningKey(getSIGNINkey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    private Key getSIGNINkey() {
        byte[] keyBytes = Decoders.BASE64.decode("6536b8f01d4095fbb5e2fa7c385913d54fd83249cb2477c9ba3b05f1c9f37342");

        return Keys.hmacShaKeyFor(keyBytes);
    }
}
