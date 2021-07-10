package com.peaqock.users.security;


import com.peaqock.users.Model.ProfileRole;
import com.peaqock.users.exception.InvalidJwtAuthenticationException;
import io.jsonwebtoken.*;
import org.bouncycastle.jcajce.BCFKSLoadStoreParameter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import java.util.Base64;
import java.util.Date;

@Component
public class JwtTokenProvider {
    @Autowired
    private Environment env;
    @Value("${token.secret}")
    private String secretKey;
    @Value("${security.jwt.token.expire-length:3600000}")
    private static final long EXPIRATIONTIME = 1000 * 60 * 60 * 24 * 10L; // 10 days
    @Value("${security.jwt.token.expire-length:86400000}")
    private static final long PASSWORD_RESET_EXPIRATION_TIME = 1000 * 60 * 60 * 24L; // 24 hours
    @Autowired
    private UserDetailsService userDetailsService;
    private static final Logger logger = LoggerFactory.getLogger(JwtTokenProvider.class);


    @PostConstruct
    protected void init() {
        secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes());
    }

    public String createToken(String username, ProfileRole roles) {
        Claims claims = Jwts.claims().setSubject(username);
        claims.put("roles", roles);
        return Jwts.builder()//
                .setClaims(claims)//
                .setIssuedAt(new Date())//
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATIONTIME))//
                .signWith(SignatureAlgorithm.HS256, secretKey)//
                .compact();
    }

    /**
     * Generate Password Reset Token by Id
     * @param userId
     * @return
     */
    public String generatePasswordResetToken(Long userId){
        return Jwts.builder()//
                .setSubject(String.valueOf(userId))//
                .setIssuedAt(new Date())//
                .setExpiration(new Date(System.currentTimeMillis() + PASSWORD_RESET_EXPIRATION_TIME))//
                .signWith(SignatureAlgorithm.HS256, secretKey.getBytes())//
                .compact();
    }

    public Authentication getAuthentication(String token) {
        UserDetails userDetails = this.userDetailsService.loadUserByUsername(getUsername(token));
        return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
    }

    public String getUsername(String token) {
        return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody().getSubject();
    }

    public String resolveToken(HttpServletRequest req) {
        String bearerToken = req.getHeader("Authorization");
        if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7, bearerToken.length());
        }
        return null;
    }

    public boolean validateToken(String token) {
        try {
            Jws<Claims> claims = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token);
            return !claims.getBody().getExpiration().before(new Date());
        } catch (JwtException | IllegalArgumentException e) {
            throw new InvalidJwtAuthenticationException();
        }
    }

    /**
     * Check token validity by Expiration date
     * @param token
     * @return
     */
    public boolean hasTokenExpired(String token){
        Claims claims = Jwts.parser()
                .setSigningKey(secretKey)
                .parseClaimsJws(token).getBody();
        Date tokenExpirationDate = claims.getExpiration();
       // System.out.println("tokenExpirationDate: "+tokenExpirationDate);
        Date DateNow = new Date();
        //System.out.println("DateNow: "+ DateNow);
        //System.out.println(tokenExpirationDate.before(DateNow));
        return tokenExpirationDate.before(DateNow);
    }
}
