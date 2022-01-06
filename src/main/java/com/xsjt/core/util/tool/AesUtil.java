package com.xsjt.core.util.tool;

import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.util.Assert;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.util.Arrays;

/**
 * 完全兼容微信所使用的AES加密方式。
 * aes的key必须是256byte长（比如32个字符），可以使用AesKit.genAesKey()来生成一组key
 *
 * @author Harriss
 */
public class AesUtil {

	private AesUtil() {
	}

	public static String genAesKey() {
		return StringUtil.random(32);
	}

	public static String encrypt(String content, String aesKey) {
//		Assert.isTrue(aesKey.getBytes(Charsets.UTF_8).length == 32, "IllegalAesKey, aesKey's length must be 32");
		try {
//			Cipher cipher = Cipher.getInstance("AES/CBC/NoPadding");
//			SecretKeySpec keySpec = new SecretKeySpec(aesKey.getBytes(Charsets.UTF_8), "AES");
//			IvParameterSpec iv = new IvParameterSpec(aesKey.getBytes(Charsets.UTF_8), 0, 16);
//			cipher.init(Cipher.ENCRYPT_MODE, keySpec, iv);
//			byte[] bytes = cipher.doFinal(Pkcs7Encoder.encode(content.getBytes(Charsets.UTF_8)));
//			return new Base64().encodeToString(bytes);

			Cipher cipher = Cipher.getInstance("AES/CBC/NoPadding");//"算法/模式/补码方式"NoPadding PkcsPadding
			int blockSize = cipher.getBlockSize();
			byte[] dataBytes = content.getBytes();
			int plaintextLength = dataBytes.length;
			if (plaintextLength % blockSize != 0) {
				plaintextLength = plaintextLength + (blockSize - (plaintextLength % blockSize));
			}
			byte[] plaintext = new byte[plaintextLength];
			System.arraycopy(dataBytes, 0, plaintext, 0, dataBytes.length);
			SecretKeySpec keyspec = new SecretKeySpec(aesKey.getBytes(Charsets.UTF_8), "AES");
			IvParameterSpec ivspec = new IvParameterSpec(aesKey.getBytes(Charsets.UTF_8), 0, 16);
			cipher.init(Cipher.ENCRYPT_MODE, keyspec, ivspec);
			byte[] encrypted = cipher.doFinal(plaintext);
			return new Base64().encodeToString(encrypted);
		} catch (Exception e) {
			throw Exceptions.unchecked(e);
		}
	}

	public static String decrypt(String encrypted, String aesKey) {
//		Assert.isTrue(aesKey.getBytes().length == 32, "IllegalAesKey, aesKey's length must be 32");
		try {
			byte[] encrypted1 = new Base64().decode(encrypted);
			Cipher cipher = Cipher.getInstance("AES/CBC/NoPadding");
			SecretKeySpec keySpec = new SecretKeySpec(aesKey.getBytes(Charsets.UTF_8), "AES");
			IvParameterSpec iv = new IvParameterSpec(Arrays.copyOfRange(aesKey.getBytes(Charsets.UTF_8), 0, 16));
			cipher.init(Cipher.DECRYPT_MODE, keySpec, iv);
			byte[] original = cipher.doFinal(encrypted1);
			return new String(original);
		} catch (Exception e) {
			throw Exceptions.unchecked(e);
		}
	}

	/**
	 * 提供基于PKCS7算法的加解密接口.
	 */
	static class Pkcs7Encoder {
		static int BLOCK_SIZE = 32;

		static byte[] encode(byte[] src) {
			int count = src.length;
			// 计算需要填充的位数
			int amountToPad = BLOCK_SIZE - (count % BLOCK_SIZE);
			if (amountToPad == 0) {
				amountToPad = BLOCK_SIZE;
			}
			// 获得补位所用的字符
			byte pad = (byte) (amountToPad & 0xFF);
			byte[] pads = new byte[amountToPad];
			for (int index = 0; index < amountToPad; index++) {
				pads[index] = pad;
			}
			int length = count + amountToPad;
			byte[] dest = new byte[length];
			System.arraycopy(src, 0, dest, 0, count);
			System.arraycopy(pads, 0, dest, count, amountToPad);
			return dest;
		}

		static byte[] decode(byte[] decrypted) {
			int pad = (int) decrypted[decrypted.length - 1];
			if (pad < 1 || pad > BLOCK_SIZE) {
				pad = 0;
			}
			if (pad > 0) {
				return Arrays.copyOfRange(decrypted, 0, decrypted.length - pad);
			}
			return decrypted;
		}
	}
}
