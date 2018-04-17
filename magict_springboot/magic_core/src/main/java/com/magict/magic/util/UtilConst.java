package com.magict.magic.util;

import java.nio.charset.StandardCharsets;

/**
 * 工具类常量
 * @author ZP
 * @date 2018-4-14 14:34:04
 */
public interface UtilConst {
	/**
	 * AES加密默认密钥
	 */
	String KEY = "asdfghjkl";
	
	/**
	 * 大写16进制字符
	 */
	String HEX_UP = "0123456789ABCDEF";
	
	/**
	 * 小写16进制字符
	 */
	String HEX_LOW = "0123456789abcdef";

	/**
	 * 默认编码
	 */
	String CHARSET = StandardCharsets.UTF_8.name();

	/**
	 * md5
	 */
	String MD5 = "MD5";

	/**
	 * HMACSHA256
	 */
	String HMACSHA256 = "HmacSHA256";
}
