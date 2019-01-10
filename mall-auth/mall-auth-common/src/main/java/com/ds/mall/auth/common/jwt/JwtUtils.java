package com.ds.mall.auth.common.jwt;

import com.ds.mall.auth.common.constant.AuthConstants;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.joda.time.DateTime;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * TODO 改进，将配置信息进行抽离，可以动态配置
 * @author tb
 * @date 2019/1/7 18:15
 */
public final class JwtUtils {

    private static final long expires = 7*60*60*1000;
    private static final String EXPIRES = "expires";
    private static final String RANDOM = "random";

    public static void main(String[] args) throws Exception {
        IJwtData jwtData = new JwtData("hello","1212","tom");
        System.out.println(generateToken(jwtData,"",1000));
    }

    /**
     * 密钥加密token
     */
    public static String generateToken(IJwtData jwtData, String priKeyPath, int expire) throws Exception {
        Map<String,Object> claims = new HashMap<>();
        claims.put(AuthConstants.JWT_CLIENT_ID, jwtData.getClientId());
        claims.put(AuthConstants.JWT_CLIENT_NAME, jwtData.getClientName());
        claims.put(EXPIRES,expires);
        claims.put(RANDOM, UUID.randomUUID());
        return Jwts.builder()
                .setSubject(jwtData.getUsername())
                .addClaims(claims)
                .setExpiration(DateTime.now().plusSeconds(expire).toDate())
                .signWith(SignatureAlgorithm.RS256, RsaUtils.getPrivateKey(priKeyPath))
                .compact();
    }

    /**
     * 密钥加密token
     */
    public static String generateToken(IJwtData jwtData, byte priKey[], int expire) throws Exception {
        Map<String,Object> claims = new HashMap<>();
        claims.put(AuthConstants.JWT_CLIENT_ID, jwtData.getClientId());
        claims.put(AuthConstants.JWT_CLIENT_NAME, jwtData.getClientName());
        claims.put(RANDOM, UUID.randomUUID());
        claims.put(EXPIRES,expires);
        return Jwts.builder()
                .setSubject(jwtData.getUsername())
                .setExpiration(DateTime.now().plusSeconds(expire).toDate())
                .signWith(SignatureAlgorithm.RS256, RsaUtils.getPrivateKey(priKey))
                .addClaims(claims)
                .compact();
    }

    /**
     * 公钥解析token
     */
    public static Jws<Claims> parserToken(String token, String pubKeyPath) throws Exception {
        return Jwts.parser().setSigningKey(RsaUtils.getPublicKey(pubKeyPath)).parseClaimsJws(token);
    }
    /**
     * 公钥解析token
     */
    public static Jws<Claims> parserToken(String token, byte[] pubKey) throws Exception {
        return Jwts.parser().setSigningKey(RsaUtils.getPublicKey(pubKey)).parseClaimsJws(token);
    }
    /**
     * 获取token中的用户信息
     */
    public static IJwtData getInfoFromToken(String token, String pubKeyPath) throws Exception {
        Jws<Claims> claimsJws = parserToken(token, pubKeyPath);
        Claims body = claimsJws.getBody();
        return new JwtData(body.getSubject(),
                objectToString(body.get(AuthConstants.JWT_CLIENT_ID)),
                objectToString(body.get(AuthConstants.JWT_CLIENT_NAME)));
    }
    /**
     * 获取token中的用户信息
     *
     * @param token
     * @param pubKey
     * @return
     * @throws Exception
     */
    public static IJwtData getInfoFromToken(String token, byte[] pubKey) throws Exception {
        Jws<Claims> claimsJws = parserToken(token, pubKey);
        Claims body = claimsJws.getBody();
        return new JwtData(body.getSubject(),
                objectToString(body.get(AuthConstants.JWT_CLIENT_ID)),
                objectToString(body.get(AuthConstants.JWT_CLIENT_NAME)));
    }


    private static String objectToString(Object object) {
        return null == object?"":String.valueOf(object);
    }
}
