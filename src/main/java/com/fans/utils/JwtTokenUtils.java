package com.fans.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
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

    @Value(value = "${kapok.jwt-key}")
    public void setJwtKey(String jwtKey) {
        JwtTokenUtils.jwtKey = jwtKey;
    }

    private static long expiredTimeIn;

    @Value(value = "${kapok.expired-time-in}")
    public static void setExpiredTimeIn(long expiredTimeIn) {
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
                .withExpiresAt(now.plus(expiredTimeIn).toDate())
                .withIssuedAt(now.toDate())
                .sign(algorithm);
    }
}
