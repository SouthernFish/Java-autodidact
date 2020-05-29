package com.blacktea.utils;

import org.springframework.security.authentication.encoding.Md5PasswordEncoder;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * MD5工具类
 * 
 * @author Jiangbo
 *
 */
public class MD5Utils {
	private static final String DEFAULT_CHARSET = "UTF-8";
	private static final String SALT = "ruiyun";
	
	/**
	 * 云客接口盐值
	 * 测试环境：salt=yuejiayunMYTEST， key=11111111
	 * 正式环境：salt=yuejiayunMY， key=kfds8f998fdsfs8
	 */
	private static final String YUNKE_SALT = "yuejiayunMY";
	private static final String YUNKE_KEY = "kfds8f998fdsfs8";

	public final static String MD5(String plainText) {
		String result = null;
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(plainText.getBytes());
			byte b[] = md.digest();
			int i;
			StringBuffer buf = new StringBuffer("");
			for (int offset = 0; offset < b.length; offset++) {
				i = b[offset];
				if (i < 0) {
					i += 256;
				}
				if (i < 16) {
					buf.append("0");
				}
				buf.append(Integer.toHexString(i));
			}
			result = buf.toString().substring(8, 24);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return result.toUpperCase();
	}

	public final static String Md5(String plainText) {
		String result = null;
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(plainText.getBytes());
			byte b[] = md.digest();
			int i;
			StringBuffer buf = new StringBuffer("");
			for (int offset = 0; offset < b.length; offset++) {
				i = b[offset];
				if (i < 0) {
					i += 256;
				}
				if (i < 16) {
					buf.append("0");
				}
				buf.append(Integer.toHexString(i));
			}
			result = buf.toString().substring(8, 24);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return result.toLowerCase();
	}

	public final static String MD532(String s) {
		return MD532(s, DEFAULT_CHARSET);
	}

	public final static String MD532(String s, String charset) {
		return Md532(s, charset).toUpperCase();
	}

	public final static String Md532(String s) {
		return Md532(s, DEFAULT_CHARSET);
	}

	public final static String Md532(String s, String charset) {
		char hexDigits[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F' };
		try {
			byte[] strTemp = s.getBytes();
			MessageDigest mdTemp = MessageDigest.getInstance("MD5");
			mdTemp.update(strTemp);
			byte[] md = mdTemp.digest();
			int j = md.length;
			char str[] = new char[j * 2];
			int k = 0;
			for (int i = 0; i < j; i++) {
				byte byte0 = md[i];
				str[k++] = hexDigits[byte0 >>> 4 & 0xf];
				str[k++] = hexDigits[byte0 & 0xf];
			}
			return new String(str).toLowerCase();
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * 专门用于登录密码加密
	 * 
	 * @param rawPass
	 * @return
	 */
	public static String MD5EncodePassword(String rawPass) {
		return new Md5PasswordEncoder().encodePassword(rawPass, SALT);
	}
	
	/** 
	* @Author LY
	* @CreateTime 2020年2月26日 下午6:19:35 
	* @Title: MD5EncodeYunKe 
	* @Description: 云客接口签名 
	*/
	public static String MD5EncodeYunKe(long timesTamp,String tel){
		StringBuffer sb = new StringBuffer(YUNKE_SALT);
		sb.append(tel).append(timesTamp).append(YUNKE_KEY);
		return MD532(sb.toString()).toLowerCase();
	}
	
	public static void main(String[] args) {
		System.out.println(MD5EncodeYunKe(1583207967908L, "18800000001"));
	}

}
