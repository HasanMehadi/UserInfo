package com.org.userinfo.Configurations;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class JwtTokenUtil {

    static final String CLAM_KEY_USERNAME = "sub";
    static final String CLAM_KEY_AUDIENCE = "audience";
    static final String CLAM_KEY_CREATED = "created";

    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.expiration}")
    private Long expiration;

    public String getUsernameFromToken(String token){

        String username = null;

        try{
            final Claims claims = getClaimsFromToken(token);
            username = claims.getSubject();

            String stringToSearch = claims.getSubject();
            String exclusion = "username=";
            username = (stringToSearch.substring(stringToSearch.indexOf(exclusion)).substring(exclusion.length(), stringToSearch.substring(stringToSearch.indexOf(exclusion)).indexOf(",")));


        }catch (Exception ex){

            username = null;
        }
        return username;
    }

    private Claims getClaimsFromToken(String token){

        Claims claims = null;

        try{

            claims = Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();

        }catch (Exception ex){

            claims = null;
        }

        return claims;
    }

    public boolean validateToken(String token , UserDetails userDetails){


        JwtUser user = (JwtUser) userDetails;

        final String username = getUsernameFromToken(token);

        return (username.equals(user.getUsername()) && !isTokenExpire(token));
    }

    private boolean isTokenExpire(String token){

        final Date expiration = getExpirationFromToken(token);

        return expiration.before(new Date());
    }

    private Date getExpirationFromToken(String token){

        Date expiration = null;

        try{

            final Claims claims = getClaimsFromToken(token);
            if(claims != null){
                System.out.println("claims not null");

                expiration = claims.getExpiration();

                System.out.println(expiration);
            }else {
                System.out.println("claims is null");
                expiration = null;
            }
        }catch (Exception ex){
            System.out.println("expiration error");
            expiration = null;
        }

        return expiration;
    }

    public String generateToken(JwtUser userDetails){


        Map<String ,Object> claims = new HashMap<>();
        claims.put(CLAM_KEY_USERNAME, userDetails.getUser());
        claims.put(CLAM_KEY_CREATED, new Date());
        
        return TokenGenerate(claims);
    }

    private String TokenGenerate(Map<String, Object> claims) {


        return Jwts.builder().setClaims(claims).setExpiration(generateExpirationDate()).signWith(SignatureAlgorithm.HS512,secret).compact();
    }

    private Date generateExpirationDate() {


        return new Date(System.currentTimeMillis()+ expiration * 1000);
    }
}
