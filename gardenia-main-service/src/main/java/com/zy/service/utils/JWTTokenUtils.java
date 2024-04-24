package com.zy.service.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.zy.service.security.beans.LoginUserDetails;
import com.zy.service.security.config.JWTConfig;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;
import jakarta.annotation.PostConstruct;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

/**
 * @description jwt工具类
 */
@Slf4j
@Component
public class JWTTokenUtils {
	/**
	 * 时间格式化
	 */
	private static final DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

	private static JWTTokenUtils jwtTokenUtils;

	@PostConstruct
	public void init() {
		jwtTokenUtils = this;
	}

	/**
	 * 创建 Token
	 *
	 * @param
	 */
	public static String createAccessToken(LoginUserDetails consumer) {
		String token = Jwts.builder()// 设置JWT
				.setId(consumer.getId() + "")     // 用户Id
				.setSubject(consumer.getUsername())   // 主题
				.setIssuedAt(new Date()) // 签发时间
				.setIssuer("ZY_Gardenia") // 签发者
				.setExpiration(new Date(System.currentTimeMillis() + JWTConfig.expiration)) // 过期时间
				.signWith(SignatureAlgorithm.HS256, JWTConfig.secret) // 签名算法、密钥
//				.claim("authorities", JSON.toJSONString(consumerSecurityDetails.getAuthorities()))// 自定义其他属性，如用户组织机构ID，用户所拥有的角色，用户权限信息等
				.compact();
		return JWTConfig.tokenPrefix + token;
	}

	/**
	 * 刷新Token
	 *
	 * @param oldToken 过期但未超过刷新时间的Token
	 * @return
	 */
//	public static String refreshAccessToken(String oldToken) {
//		String username = JWTTokenUtils.getUserNameByToken(oldToken);
//		LoginUserDetails consumer = (LoginUserDetails) jwtTokenUtils.userDetailService
//				.loadUserByUsername(username);
//		consumer.setIp(JWTTokenUtils.getIpByToken(oldToken));
//		return createAccessToken(consumer);
//	}

	/**
	 * 解析 Token
	 *
	 * @param token Token信息
	 * @return
	 */
	public static LoginUserDetails parseAccessToken(String token) {
		LoginUserDetails consumerDetails = null;
		if (StringUtils.isNotEmpty(token)) {
			try {
				// 去除JWT前缀  -  Bearer
				token = token.substring(JWTConfig.tokenPrefix.length());

				// 解析Token
				Claims claims = Jwts.parser().setSigningKey(JWTConfig.secret).parseClaimsJws(token).getBody();

				// 获取用户信息
				consumerDetails = new LoginUserDetails();
				consumerDetails.setId(Integer.parseInt(claims.getId()) + "");
				consumerDetails.setUsername(claims.getSubject());

				// 获取角色
				Set<GrantedAuthority> authorities = new HashSet<>();
				String authority = claims.get("authorities").toString();
				if (StringUtils.isNotEmpty(authority)) {
					List<Map<String, String>> authorityList = JSON.parseObject(authority,
							new TypeReference<List<Map<String, String>>>() {});
					for (Map<String, String> role : authorityList) {
						if (!role.isEmpty()) {
							authorities.add(new SimpleGrantedAuthority(role.get("authority")));
						}
					}
				}

				consumerDetails.setAuthorities(authorities);
			} catch (Exception e) {
				log.error("解析Token异常：" + e);
			}
		}
		return consumerDetails;
	}

	/**
	 * 保存 Token 信息到 Redis 中
	 *
	 * @param token    Token信息
	 * @param username 用户名
	 * @param ip       IP
	 */
	public static void setTokenInfo(String token, String username, String ip) {
		if (StringUtils.isNotEmpty(token)) {
			// 去除JWT前缀
			token = token.substring(JWTConfig.tokenPrefix.length());

			Integer refreshTime = JWTConfig.refreshTime;
			LocalDateTime localDateTime = LocalDateTime.now();

//			RedisUtils.hset(token, "username", username, refreshTime);
//			RedisUtils.hset(token, "ip", ip, refreshTime);
//			RedisUtils.hset(token, "refreshTime",
//					df.format(localDateTime.plus(JWTConfig.refreshTime, ChronoUnit.MILLIS)), refreshTime);
//			RedisUtils.hset(token, "expiration",
//					df.format(localDateTime.plus(JWTConfig.expiration, ChronoUnit.MILLIS)),
//					refreshTime);
		}
	}

	/**
	 * 将Token放到黑名单中
	 *
	 * @param token Token信息
	 */
	public static void addBlackList(String token) {
		if (StringUtils.isNotEmpty(token)) {
			// 去除JWT前缀
			token = token.substring(JWTConfig.tokenPrefix.length());
//			RedisUtils.hset("blackList", token, df.format(LocalDateTime.now()));
		}
	}

	/**
	 * Redis中删除Token
	 *
	 * @param token Token信息
	 */
	public static void deleteRedisToken(String token) {
		if (StringUtils.isNotEmpty(token)) {
			// 去除JWT前缀
			token = token.substring(JWTConfig.tokenPrefix.length());
//			RedisUtils.deleteKey(token);
		}
	}

	/**
	 * 判断当前Token是否在黑名单中
	 *
	 * @param token Token信息
	 */
	public static boolean isBlackList(String token) {
		if (StringUtils.isNotEmpty(token)) {
			// 去除JWT前缀
			token = token.substring(JWTConfig.tokenPrefix.length());
//			return RedisUtils.hasKey("blackList", token);
			return true;
		}
		return false;
	}

	/**
	 * 是否过期
	 *
	 * @param expiration 过期时间，字符串
	 * @return 过期返回True，未过期返回false
	 */
	public static boolean isExpiration(String expiration) {
		LocalDateTime expirationTime = LocalDateTime.parse(expiration, df);
		LocalDateTime localDateTime = LocalDateTime.now();
		if (localDateTime.compareTo(expirationTime) > 0) {
			return true;
		}
		return false;
	}

	/**
	 * 是否有效
	 *
	 * @param refreshTime 刷新时间，字符串
	 * @return 有效返回True，无效返回false
	 */
	public static boolean isValid(String refreshTime) {
		LocalDateTime validTime = LocalDateTime.parse(refreshTime, df);
		LocalDateTime localDateTime = LocalDateTime.now();
		if (localDateTime.compareTo(validTime) > 0) {
			return false;
		}
		return true;
	}

	/**
	 * 检查Redis中是否存在Token
	 *
	 * @param token Token信息
	 * @return
	 */
	public static boolean hasToken(String token) {
		if (StringUtils.isNotEmpty(token)) {
			// 去除JWT前缀
			token = token.substring(JWTConfig.tokenPrefix.length());
//			return RedisUtils.hasKey(token);
			return true;
		}
		return false;
	}

	/**
	 * 从Redis中获取过期时间
	 *
	 * @param token Token信息
	 * @return 过期时间，字符串
	 */
	public static String getExpirationByToken(String token) {
		if (StringUtils.isNotEmpty(token)) {
			// 去除JWT前缀
			token = token.substring(JWTConfig.tokenPrefix.length());
//			return RedisUtils.hget(token, "expiration").toString();
			return "";
		}
		return null;
	}

	/**
	 * 从Redis中获取刷新时间
	 *
	 * @param token Token信息
	 * @return 刷新时间，字符串
	 */
	public static String getRefreshTimeByToken(String token) {
		if (StringUtils.isNotEmpty(token)) {
			// 去除JWT前缀
			token = token.substring(JWTConfig.tokenPrefix.length());
//			return RedisUtils.hget(token, "refreshTime").toString();
			return "";
		}
		return null;
	}

	/**
	 * 从 Redis 中获取用户名
	 *
	 * @param token Token信息
	 * @return
	 */
	public static String getUserNameByToken(String token) {
		if (StringUtils.isNotEmpty(token)) {
			// 去除JWT前缀
			token = token.substring(JWTConfig.tokenPrefix.length());
//			return RedisUtils.hget(token, "username").toString();
			return "";
		}
		return null;
	}

	/**
	 * 从Redis中获取IP
	 *
	 * @param token Token信息
	 * @return
	 */
	public static String getIpByToken(String token) {
		if (StringUtils.isNotEmpty(token)) {
			// 去除JWT前缀
			token = token.substring(JWTConfig.tokenPrefix.length());
//			return RedisUtils.hget(token, "ip").toString();
			return "";
		}
		return null;
	}

}
