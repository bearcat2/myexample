package com.headerits.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.security.DigestException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * 描述 MD5加密
 * 
 * @author caoxiongmin
 * 
 */
public class MD5Util {

	/**
	 * 获取字符串的MD5
	 * 
	 * @param string
	 * @return
	 * @throws NoSuchAlgorithmException
	 */
	public static String getStringMD5(String string){
		StringBuffer sb = new StringBuffer();
		try{
			byte[] byteString = string.getBytes(Charset.forName("UTF-8"));
			MessageDigest md = MessageDigest.getInstance("MD5");
			byte[] array = md.digest(byteString);
			for (int i = 0; i < array.length; ++i) {
				sb.append(Integer.toHexString((array[i] & 0xFF) | 0x100).substring(1, 3));
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		return sb.toString();
	}

	/**
	 * 获取文件MD5
	 * 
	 * @param filePath
	 *            文件路径
	 * @return
	 * @throws NoSuchAlgorithmException
	 * @throws IOException
	 * @throws DigestException
	 */
	public static String getFileMD5(String filePath) throws NoSuchAlgorithmException, IOException, DigestException {
		MessageDigest md = MessageDigest.getInstance("MD5");
		int length = 0;
		byte[] buffer = new byte[1024];
		byte[] array = null;
		InputStream is = new FileInputStream(new File(filePath));
		while ((length = is.read(buffer)) != -1) {
			md.update(buffer, 0, length);
		}
		array = md.digest();

		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < array.length; ++i) {
			sb.append(Integer.toHexString((array[i] & 0xFF) | 0x100).substring(1, 3));
		}

		return sb.toString();
	}
	
	public static void main(String[] args) {
			System.out.println(MD5Util.getStringMD5("123456"));
	}

}