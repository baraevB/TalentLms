package peaksoft.security.jvt;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JwtTokenUtil {
    @Value("java_moscow2")
    private String jwtSecret;
    private final long JWT_TOKEN_VALIDITY = 7*24*60*60*1000;

    private String createToken(Map<String,Object> claims,String subject){
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(subject)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis()+JWT_TOKEN_VALIDITY))
                .signWith(SignatureAlgorithm.HS512,jwtSecret)
                .compact();
    }
    public String generateToken(UserDetails userDetails){
        Map<String,Object>claims=new HashMap<>();
        return createToken(claims,userDetails.getUsername());
    }
    public Date gerExpirationDateFromToken(String token){
        return getClaimFormToken(token , Claims::getExpiration);

    }
    private <T>T getClaimFormToken(String token, Function<Claims,T> function){
        final Claims claims= getAllClaimsFromToken(token);
        return function.apply(claims);
    }
    private  Claims getAllClaimsFromToken(String token){
        return Jwts.parser()
                .setSigningKey(jwtSecret)
                .parseClaimsJws(token)
                .getBody();
    }
    private Boolean isTokenExpired(String token){
        final Date expiration = gerExpirationDateFromToken(token);
        return expiration.before(new Date());
    }
    public String getUsernameFormToken(String token){
        return getClaimFormToken(token,Claims::getSubject);
    }
    public Boolean validationToken(String token,UserDetails userDetails){
        final String username = getUsernameFormToken(token);
        return (username.equals(userDetails.getUsername() )&& !isTokenExpired(token));
    }
}