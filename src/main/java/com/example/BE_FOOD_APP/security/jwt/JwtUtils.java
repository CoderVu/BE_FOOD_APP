package com.example.BE_FOOD_APP.security.jwt;

import com.example.BE_FOOD_APP.security.user.FoodUserDetails;
import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class JwtUtils {
    private static final Logger logger = LoggerFactory.getLogger(JwtUtils.class);

    @Value("4545454434324326737432864237498327493274983274893274893793773298")
    private String jwtSecret;

    @Value("3600000")
    private int jwtExpirationMs;

    public String generateJwtTokenForUser(Authentication authentication){
        FoodUserDetails userPrincipal = (FoodUserDetails) authentication.getPrincipal();
        List<String> roles = userPrincipal.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());

        return Jwts.builder()
                .setSubject(userPrincipal.getUsername())
                .claim("roles", roles)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + jwtExpirationMs))
                .signWith(key(), SignatureAlgorithm.HS256)
                .compact();
    }
    private SecretKey key() {
        byte[] keyBytes = Decoders.BASE64.decode(jwtSecret);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    public String getUserNameFromToken(String token){
        return Jwts.parserBuilder()
                .setSigningKey(key())
                .build()
                .parseClaimsJws(token).getBody().getSubject();
    }

    public boolean validateToken(String token){
        try{
            Jwts.parserBuilder().setSigningKey(key()).build().parse(token);
            return true;
        } catch(MalformedJwtException e){
            logger.error("Invalid jwt token: {}", e.getMessage());
        } catch (ExpiredJwtException e){
            logger.error("Expired token: {}", e.getMessage());
        } catch (UnsupportedJwtException e){
            logger.error("This token is not supported: {}", e.getMessage());
        } catch (IllegalArgumentException e){
            logger.error("No claims found: {}", e.getMessage());
        }
        return false;
    }
}