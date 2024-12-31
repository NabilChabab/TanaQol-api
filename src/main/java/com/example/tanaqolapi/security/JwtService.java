package com.example.tanaqolapi.security;

import com.example.tanaqolapi.model.AppUser;
import com.example.tanaqolapi.model.enums.Role;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.*;
import java.util.function.Function;

@Service
public class JwtService {

    private static final String SECRET_KEY = "4HmrkFLzXppXXdHbJPbkTwcD9ET6DJy6rLxDZJjArDj9BYCuYNR6Zm5SBWtwUds2LYCP7zSNxyT2XkH6aBKPP6eQFrb2qUb5MGkNPdmUAhdNFTqh397ax4ADkA46Xs8cpdcUXS37PCuHKtk4zCZe9oLSHBAp3fwyezua98CAZs4U6CzFMbnLbkbuHxTrkksAFdwrA3QFMwpSuSrzmbJDKe5d8Qu7UUH5ySxfgHGYYJE8HmnSgux6zksUT44ocoFM";

    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public String generateToken(UserDetails userDetails) {
        return generateToken(new HashMap<>(), userDetails);
    }

    public String generateTokenForOAuth2User(OAuth2User oauth2User) {
        Map<String, Object> claims = new HashMap<>();

        Map<String, Object> attributes = oauth2User.getAttributes();
        String email = (String) attributes.get("email");
        String name = (String) attributes.get("name");
        String picture = (String) attributes.get("picture");

        claims.put("email", email);
        claims.put("name", name);
        claims.put("picture", picture);
        claims.put("provider", "google");

        UUID userId = UUID.nameUUIDFromBytes(email.getBytes());
        claims.put("id", userId.toString());

        claims.put("role", "ROLE_"+Role.CUSTOMER);

        return Jwts.builder()
            .setClaims(claims)
            .setSubject(email)
            .claim("username", name)
            .setIssuedAt(new Date(System.currentTimeMillis()))
            .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 24))
            .signWith(getSigningKey(), SignatureAlgorithm.HS256)
            .compact();
    }

    public boolean isTokenValid(String token, UserDetails userDetails) {
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername())) && !isTokenExpired(token);
    }

    // New method to validate token for OAuth2 users
    public boolean isTokenValid(String token, OidcUser oidcUser) {
        final String username = extractUsername(token);
        return (username.equals(oidcUser.getEmail())) && !isTokenExpired(token);
    }

    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    public String generateToken(
        Map<String, Object> claims,
        UserDetails userDetails
    ) {
        // Get the user's ID
        UUID userId = ((AppUser) userDetails).getId();

        // Extract the list of roles from authorities
        String role = userDetails.getAuthorities().stream()
            .map(Object::toString)
            .filter(authority -> authority.startsWith("ROLE_"))
            .findFirst()
            .orElse("");

        // Build the JWT
        return Jwts.builder()
            .setClaims(claims)
            .setSubject(userDetails.getUsername())
            .claim("id", userId.toString())
            .claim("role", role)
            .claim("username", ((AppUser) userDetails).getFirstName() + " " + ((AppUser) userDetails).getLastName())
            .setIssuedAt(new Date(System.currentTimeMillis()))
            .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 24))
            .signWith(getSigningKey(), SignatureAlgorithm.HS256)
            .compact();
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    public Claims extractAllClaims(String jwt) {
        return Jwts.parserBuilder()
            .setSigningKey(getSigningKey())
            .build()
            .parseClaimsJws(jwt)
            .getBody();
    }

    private Key getSigningKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
