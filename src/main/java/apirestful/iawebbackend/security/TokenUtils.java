package apirestful.iawebbackend.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;


import java.security.Key;
import java.util.*;
import java.util.function.Function;


public class TokenUtils {

    static UserDetailServiceImpl userDetailService;

    public TokenUtils(UserDetailServiceImpl userDetailService) {
        this.userDetailService = userDetailService;
    }

    private final static String ACCESS_TOKEN_SECRET = "IaWebAtmiratest1test2test3test4test5test6test7";

    public static String createToken(String login, String email) {

        Map<String, Object> extra = new HashMap<>();
        extra.put("email", email);

        return Jwts.builder()
                .setSubject(login)
                .addClaims(extra)
                .signWith(Keys.hmacShaKeyFor(ACCESS_TOKEN_SECRET.getBytes()))
                .compact();
    }

    public static UsernamePasswordAuthenticationToken getAuthentication(String token) {
        try {
            Claims claims = Jwts.parserBuilder()
                    .setSigningKey(ACCESS_TOKEN_SECRET.getBytes())
                    .build()
                    .parseClaimsJws(token)
                    .getBody();

            String login = claims.getSubject();
            return new UsernamePasswordAuthenticationToken(login, null, userDetailService.loadUserByUsername(login).getAuthorities());

        } catch (JwtException e) {
            return null;
        }
    }

    public String getUsernameFromToken(String token) {
        return getClaimFromToken(token, Claims::getSubject);
    }
    private <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = getAllClaimsFromToken(token);
        return claimsResolver.apply(claims);
    }
    private Claims getAllClaimsFromToken(String token) {
        return Jwts.parserBuilder().setSigningKey(getSecretKey()).build().parseClaimsJws(token).getBody();
    }
    private Key getSecretKey() {
        return Keys.hmacShaKeyFor(ACCESS_TOKEN_SECRET.getBytes());
    }
    public boolean validateToken(String token, UserDetails userDetails) {
        final String username = getUsernameFromToken(token);
        return (username.equals(userDetails.getUsername()));
    }



}
