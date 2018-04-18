package com.llf.demo.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.Verification;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.UnsupportedEncodingException;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author: Oliver.li
 * @Description: Jwt工具类
 * @date: 2018/4/17 14:06
 */
public class JwtUtil {

    private static Logger logger = LoggerFactory.getLogger(JwtUtil.class);

    public static String createToken(long exp) throws UnsupportedEncodingException {

        Algorithm algorithm = Algorithm.HMAC256("secret");

        long expireMillis = System.currentTimeMillis() + exp;

        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(expireMillis);
        Date expireTime = calendar.getTime();

        String token = JWT.create()
                .withClaim("userId", "1")
                .withClaim("role", "ADMIN")
                .withExpiresAt(expireTime).sign(algorithm);

        return token;
    }

    public static String checkToken(String token) throws UnsupportedEncodingException {

        return null;
    }

    public static void main(String[] args) throws UnsupportedEncodingException {
        String token = createToken(60 * 10);
        logger.info(token);

        String s = checkToken(token);
        logger.info(s);
    }

}
