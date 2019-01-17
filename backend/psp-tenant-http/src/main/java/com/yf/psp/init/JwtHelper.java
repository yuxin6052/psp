package com.yf.psp.init;

import java.security.Key;
import java.util.Date;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import com.yf.psp.common.login.UserSession;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class JwtHelper {

	private final static Logger logger = LoggerFactory.getLogger(JwtHelper.class);

	public static final String JWT_HTTP_HEADER_AUTHORIZATION = "Authorization";
	public static final String JWT_TOKEN_PREFIX = "bearer;";
	public static final String JWT_ATTR = "userSession";
	public static final String JWT_ISSUER = "YF#PSP#CH";
	public static final long JWT_EXPIRE = 3600 * 1000 * 24; // 24个小时

	public static final long JWT_VALID_FREQUENCY = 10; // access token 有效次数
	public static final String JWT_VALID_FREQUENCY_ATTR = "frequency";
	public static final long JWT_VALID_FREQUENCY_EXPIRE = 3600 * 1000 * 24 * 10; // 10天

	public static final String BASE64SECURITY = "CH=678901234567=QWEASDFZXCVBMNGHJKLPOIUYTGL=CH";

	/**
	 * 解析jwt
	 */
	public static Claims parseJWT(String jsonWebToken, String base64Security) {
		try {
			Claims claims = Jwts.parser().setSigningKey(DatatypeConverter.parseBase64Binary(base64Security))
					.parseClaimsJws(jsonWebToken).getBody();
			if (!JWT_ISSUER.equals(claims.getIssuer())
					|| System.currentTimeMillis() >= claims.getExpiration().getTime()) {
				return null;
			}
			return claims;
		} catch (Exception ex) {
			logger.error(ex.getMessage());
			return null;
		}
	}

	
	public static User getUserDetails() {
		Authentication au = SecurityContextHolder.getContext().getAuthentication();
		if(au==null) {
			return null;
		}
		User us = (User)au.getPrincipal();
		return us;
	}
	/**
	 * 构建jwt
	 */
	public static String createJWT(String audience, UserSession us) {
		SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;


		// 生成签名密钥
		byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(BASE64SECURITY);
		Key signingKey = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());

		// 添加构成JWT的参数
		JwtBuilder builder = Jwts.builder().setHeaderParam("typ", "JWT").claim(JWT_ATTR, us).setIssuer(JWT_ISSUER)
				.setAudience(audience).signWith(signatureAlgorithm, signingKey);
		// 添加Token过期时间
		long nowMillis = System.currentTimeMillis();
		long expMillis = nowMillis + JWT_EXPIRE;
		Date exp = new Date(expMillis);
		Date now = new Date();
		builder.setExpiration(exp).setNotBefore(now);

		// 生成JWT
		return builder.compact();
	}
	
	public static String createFrequencyJWT(String audience, UserSession us) {
		SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;


		// 生成签名密钥
		byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(BASE64SECURITY);
		Key signingKey = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());

		// 添加构成JWT的参数
		JwtBuilder builder = Jwts.builder().setHeaderParam("typ", "JWT").claim(JWT_ATTR, us).claim(JWT_VALID_FREQUENCY_ATTR, JWT_VALID_FREQUENCY).setIssuer(JWT_ISSUER)
				.setAudience(audience).signWith(signatureAlgorithm, signingKey);
		// 添加Token过期时间
		long nowMillis = System.currentTimeMillis();
		long expMillis = nowMillis + JWT_VALID_FREQUENCY_EXPIRE;
		Date exp = new Date(expMillis);
		Date now = new Date();
		builder.setExpiration(exp).setNotBefore(now);

		// 生成JWT
		return builder.compact();
	}
}