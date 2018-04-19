package com.llf.demo.util;

import org.apache.commons.configuration2.Configuration;
import org.apache.commons.configuration2.builder.fluent.Configurations;
import org.jose4j.jws.JsonWebSignature;
import org.jose4j.jwt.JwtClaims;
import org.jose4j.jwt.consumer.InvalidJwtException;
import org.jose4j.jwt.consumer.JwtConsumer;
import org.jose4j.jwt.consumer.JwtConsumerBuilder;
import org.jose4j.lang.JoseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Assert;
import sun.misc.BASE64Decoder;

import java.io.File;
import java.security.Key;
import java.security.KeyFactory;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.*;

/**
 * @author: Oliver.li
 * @Description: Jwt工具类
 * @date: 2018/4/17 14:06
 */
public class JwtUtil {

    private static Logger logger = LoggerFactory.getLogger(JwtUtil.class);

    private static Key privateKey;
    private static Key publicKey;

    static {
        try {
            Configuration configuration = (new Configurations()).properties(new File("/", "jwt.properties"));
            byte[] keyBytes = (new BASE64Decoder()).decodeBuffer(configuration.getString("privateKey"));
            PKCS8EncodedKeySpec keySpecPrivateKey = new PKCS8EncodedKeySpec(keyBytes);
            KeyFactory keyFactoryPrivateKey = KeyFactory.getInstance("RSA");
            privateKey = keyFactoryPrivateKey.generatePrivate(keySpecPrivateKey);

            keyBytes = (new BASE64Decoder()).decodeBuffer(configuration.getString("publicKey"));
            X509EncodedKeySpec keySpecPublicKey = new X509EncodedKeySpec(keyBytes);
            KeyFactory keyFactoryPublicKey = KeyFactory.getInstance("RSA");
            publicKey = keyFactoryPublicKey.generatePublic(keySpecPublicKey);
        } catch (Exception e) {
            logger.error("", e);
            System.exit(1);
        }
    }

    private JwtUtil() {
    }

    public static String generateToken(String issUser, String audience, Float minutes, Map<String, Object> payLoadMap) throws JoseException {

        Assert.state(issUser != null && !"".equals(issUser), "issUser cannot be empty!");

        Assert.state(audience != null && !"".equals(audience), "audience cannot be empty!");

        Assert.state(minutes != null, "minutes cannot be null!");

        Assert.state(payLoadMap != null && !payLoadMap.isEmpty(), "payLoadMap cannot be empty!");

        JwtClaims claims = new JwtClaims();
        claims.setIssuer(issUser);
        claims.setAudience(issUser);
        claims.setExpirationTimeMinutesInTheFuture(minutes);
        claims.setGeneratedJwtId();
        claims.setIssuedAtToNow();
        claims.setNotBeforeMinutesInThePast(1.0F);
        claims.setSubject("subject");

        Set<Map.Entry<String, Object>> entries = payLoadMap.entrySet();
        for (Map.Entry<String, Object> entry : entries){
            String key = entry.getKey();
            String value = entry.getValue() == null ? "" : entry.getValue().toString();
            claims.setClaim(key, value);
        }

        claims.setClaim("audience", audience);
        JsonWebSignature jws = new JsonWebSignature();
        jws.setPayload(claims.toJson());
        jws.setKey(privateKey);
        jws.setKeyIdHeaderValue("k1");
        jws.setAlgorithmHeaderValue("RS256");
        return jws.getCompactSerialization();
    }

    public static Map<String, Object> checkToken(String token, String isUser, String audience) throws InvalidJwtException {

        Assert.state(token != null && !"".equals(token), "token cannot be empty!");

        Assert.state(isUser != null && !"".equals(isUser), "isUser cannot be empty!");

        Assert.state(audience != null && !"".equals(audience), "audience cannot be empty!");

        JwtConsumer jwtConsumer = (new JwtConsumerBuilder())
                .setRequireExpirationTime()
                .setAllowedClockSkewInSeconds(30)
                .setRequireSubject()
                .setExpectedIssuer(isUser)
                .setExpectedAudience(audience)
                .setVerificationKey(publicKey)
                .build();
        JwtClaims jwtClaims = jwtConsumer.processToClaims(token);
        Map<String, Object> payLoadMap = jwtClaims.getClaimsMap();
        logger.debug("token check success!" + payLoadMap);
        return payLoadMap;
    }

    public static void main(String[] args) throws InvalidJwtException, JoseException {

        Map<String, Object> map = new HashMap<>();
        map.put("aa", "AA");

        String token = generateToken("test", "test", 2.0f, map);

        System.out.println("token:" + token);

        Map<String, Object> checkToken = checkToken(token, "test", "test");

        System.out.println("checkToken:" + checkToken);


    }

}
