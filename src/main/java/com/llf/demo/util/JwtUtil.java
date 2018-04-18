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
import org.springframework.util.StringUtils;
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

    private JwtUtil() {
    }

    public static String generateToken(String issUser, String audience, Float minutes, Map<String, Object> payLoadMap) throws JoseException {
        if(StringUtils.isEmpty(issUser)) {
            logger.debug("令牌创建者为空");
            return null;
        } else if(StringUtils.isEmpty(audience)) {
            logger.debug("令牌使用者为空");
            return null;
        } else if(minutes == null) {
            logger.debug("令牌有效时间为空");
            return null;
        } else {
            JwtClaims claims = new JwtClaims();
            claims.setIssuer(issUser);
            claims.setAudience(issUser);
            claims.setExpirationTimeMinutesInTheFuture(minutes.floatValue());
            claims.setGeneratedJwtId();
            claims.setIssuedAtToNow();
            claims.setNotBeforeMinutesInThePast(1.0F);
            claims.setSubject("subject");
            Iterator var5 = payLoadMap.entrySet().iterator();

            while(var5.hasNext()) {
                Map.Entry<String, Object> entry = (Map.Entry)var5.next();
                String key = ((String)entry.getKey()).toString();
                String value = entry.getValue() == null?"":entry.getValue().toString();
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
    }

    public static Map<String, Object> checkToken(String token, String isUser, String audience) throws InvalidJwtException {
        JwtConsumer jwtConsumer = (new JwtConsumerBuilder()).setRequireExpirationTime().setAllowedClockSkewInSeconds(30).setRequireSubject().setExpectedIssuer(isUser).setExpectedAudience(new String[]{audience}).setVerificationKey(publicKey).build();
        JwtClaims jwtClaims = jwtConsumer.processToClaims(token);
        Map<String, Object> payLoadMap = jwtClaims.getClaimsMap();
        logger.debug("token 校验成功" + payLoadMap);
        return payLoadMap;
    }

    static {
        try {
            Configuration configuration = (new Configurations()).properties(new File("/", "jwt.properties"));
            byte[] keyBytes = (new BASE64Decoder()).decodeBuffer(configuration.getString("privateKey"));
            PKCS8EncodedKeySpec keySpec_privateKey = new PKCS8EncodedKeySpec(keyBytes);
            KeyFactory keyFactory_privateKey = KeyFactory.getInstance("RSA");
            privateKey = keyFactory_privateKey.generatePrivate(keySpec_privateKey);
            keyBytes = (new BASE64Decoder()).decodeBuffer(configuration.getString("publicKey"));
            X509EncodedKeySpec keySpec_publicKey = new X509EncodedKeySpec(keyBytes);
            KeyFactory keyFactory_publicKey = KeyFactory.getInstance("RSA");
            publicKey = keyFactory_publicKey.generatePublic(keySpec_publicKey);
        } catch (Exception var6) {
            logger.error("", var6);
            System.exit(1);
        }

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
