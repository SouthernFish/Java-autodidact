package com.blacktea.utils;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class EncryptionUtil {
	/*
	 * 加密用的偏移量 可以用26个字母和数字组成 此处使用AES-128-CBC加密模式，偏移量需要为16位。
	 */
	private static String ivParameter = "RUIYUNYUEJIAYUN2";

	/*
	 * 加密用的key 可以用26个字母和数字组成 此处使用AES-128-CBC加密模式，key需要为16位。
	 */
	private static String AES_KEY_PASSWORD = "RUIYUNYUEJIAYUN1";

	// 加密
	public static String encrypt2Str(String sSrc) throws Exception {
		Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
		byte[] raw = AES_KEY_PASSWORD.getBytes();
		SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
		IvParameterSpec iv = new IvParameterSpec(ivParameter.getBytes());// 使用CBC模式，需要一个向量iv，可增加加密算法的强度
		cipher.init(Cipher.ENCRYPT_MODE, skeySpec, iv);
		byte[] encrypted = cipher.doFinal(sSrc.getBytes("utf-8"));

		StringBuilder stringBuilder = new StringBuilder("");
		for (int i = 0; i < encrypted.length; i++) {
			int v = encrypted[i] & 0xFF;
			String hv = Integer.toHexString(v);
			if (hv.length() < 2) {
				stringBuilder.append(0);
			}
			stringBuilder.append(hv);
		}
		return stringBuilder.toString();
	}

	// 解密
	public static String decrypt2Str(String sSrc) throws Exception {
		if (sSrc == null || sSrc.equals("")) {
			return null;
		}
		sSrc = sSrc.toUpperCase();
		int length = sSrc.length() / 2;
		char[] hexChars = sSrc.toCharArray();
		byte[] encrypted1 = new byte[length];
		for (int i = 0; i < length; i++) {
			int pos = i * 2;
			encrypted1[i] = (byte) (charToByte(hexChars[pos]) << 4 | charToByte(hexChars[pos + 1]));
		}
		try {
			byte[] raw = AES_KEY_PASSWORD.getBytes("ASCII");
			SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
			Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
			IvParameterSpec iv = new IvParameterSpec(ivParameter.getBytes());
			cipher.init(Cipher.DECRYPT_MODE, skeySpec, iv);
			byte[] original = cipher.doFinal(encrypted1);
			String originalString = new String(original, "utf-8");
			return originalString;
		} catch (Exception ex) {
			return null;
		}
	}

	private static byte charToByte(char c) {
		return (byte) "0123456789ABCDEF".indexOf(c);
	}


	public static void main(String[] args) throws Exception {

		String content = "{'operatorAccount':'admin','operatorPwd':'123456'}";

		// 加密
		System.out.println("加密前：" + content);

		String tt4 = encrypt2Str(content);
		System.out.println(new String(tt4));

		// 解密
		String d = decrypt2Str("50bebd7f108d7e51436309e4597e8430db807fea7d178508f52a1c45da8124e7557f85ee0934a59b29d51f01cb8514ce140a4219c163f5968c0cefb7890a0cba07c609cb8e03fe9f2d690aa03f170807a4e6a8a3efd4ebcf502c5009f86d99e9d0ee71532e823487fca50841e8a48925a7a6465ddf380e3da476c73a1f8da99e1cacf42c5461802d57d435e5d900cb257f0ec6e8ebf93f05860878bba9a7eeb65260313106040ad7966b63ebe39fd23ad4ba1914e4a600b4626b046b0c34f2635e3f328f36764caf0d3a78e4b7fce539e7ea58a1e629598cf962f3097edc5080268d12e5bbb660b8d1cd49ffcae03472af63c94fe5320e83e7a6eabe1c6a6ef1ddf1ac10ef2b4c656acdb1cbfb3c13e1825eafb455c0c1a158cb5ed4014e8fcea623fde644b9796ad8d67aa3f77d9dc9d47abad130161176df3048d3fa1349edbb4827b68ee19664cb41b0c74bb07fd19e71cdb79f43a6f9134d4525ab95bffc268678727e151c82d5ff7e2d935598460f6e25115587f93653c3f235d0920118c4df42f1e3596e4ee49d2b73071bfaa3497c4e908a30705b108486a185e2bae186896560b3c462dd0ace395448a069bb59db2fb1a2142011a352d6fae56cac5de7d16c29fc38bf7ef542e81bafa592610b02435e12daa04ed16e32a9465aa424056564ae715d48dd49a717c3672f5132f86aa31b32b063da7f42e7d0f504168affff68a3c3f624428c9d254ce54ce10e5fe2ec610b968ed565b7d6a9fe1d8f35ae5d450b12e7fbc3afa0171ac937723187869f4ec64d421b261b36b4f2ed1071b5d7a4c8e9a9a9a5d5ee21ad79fe6924d206cbc50e329339c3638b29cd5be21e000780d643fc65e253095ab4af4ca9aefba2dfcce373b864b1592e880f6b5a6cce4ac45296499ecc655d5f99234712c271e3e24a1a8801b568b05134838e4b0f3fa18ab4902685550ad344a2786a2fe34e79808bc997008b22ca3f9274d3dd771092cee67f3e74988a3b41f8d332df5d6580b25848778ce65c37d1c1b43ad62a6d057928fef97acda3d9a41e852edd49fafa13844da9d5f4219990e2a24cea166fd3c5532b6c4985451f0b4049dcdcc3d7712e31acb979e241a6ee105f9687b356ea77db08b0788c54b947c8b09a145a6c19e156e4010edd13d50946b782b275e5a25acf1d68ed401438d3b4e0a6c16cb298e98ad431b85299c94015912bf3e15d299521984e884c3bd72cc1f484b28e34aeb5a29d5a057ddf02c86f8c8ccf75a196291728a2ecf2b44bed145189e3f72729540e52a043ebe128f36e962f7c20fd642687668532fec83895f5f9b9d18c");

		System.out.println("解密后：" + d);

		System.out.println(MD5Utils.MD5EncodePassword("123456"));


	}
}
