package com.fans.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.fans.dto.TokenDTO;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Optional;

/**
 * className: JwtToken
 *
 * @author k
 * @version 1.0
 * @description jwt令牌生成
 * @date 2020-05-31 23:55
 **/
@Component
public class JwtTokenUtils {

    private static final Integer SCOPE = 8;

    private static String jwtKey;

    public static Boolean verifyToken(TokenDTO tokenDTO) {
        Algorithm algorithm = Algorithm.HMAC256(jwtKey);
        JWTVerifier verifier = JWT.require(algorithm).build();
        try {
            verifier.verify(tokenDTO.getToken());
        } catch (JWTVerificationException e) {
            return false;
        }
        return true;
    }

    @Value(value = "${kapok.security.jwt-key}")
    public void setJwtKey(String jwtKey) {
        JwtTokenUtils.jwtKey = jwtKey;
    }

    private static Integer expiredTimeIn;

    @Value(value = "${kapok.security.token-expired-in}")
    public void setExpiredTimeIn(Integer expiredTimeIn) {
        JwtTokenUtils.expiredTimeIn = expiredTimeIn;
    }

    public static String makeToken(Long uid, Integer scope) {
        return getToken(uid, scope);
    }

    public static String makeToken(Long uid) {
        return getToken(uid, SCOPE);
    }

    public static Optional<Map<String, Claim>> getClaims(String token) {
        Algorithm algorithm = Algorithm.HMAC256(jwtKey);
        JWTVerifier jwtVerifier = JWT.require(algorithm).build();
        DecodedJWT verify;
        try {
            verify = jwtVerifier.verify(token);
        } catch (JWTVerificationException e) {
            return Optional.empty();
        }
        return Optional.of(verify.getClaims());
    }

    private static String getToken(Long uid, Integer scope) {
        DateTime now = DateTime.now();
        Algorithm algorithm = Algorithm.HMAC256(jwtKey);
        return JWT.create()
                .withClaim("uid", uid)
                .withClaim("scope", scope)
                .withExpiresAt(now.plusSeconds(expiredTimeIn).toDate())
                .withIssuedAt(now.toDate())
                .sign(algorithm);
    }
}
