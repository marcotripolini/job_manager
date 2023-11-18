package it.improvity.utils;

import java.security.MessageDigest;
import java.text.SimpleDateFormat;
import java.util.Date;

public final class IPGIntegrationUtil {

	private static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy:MM:dd-HH:mm:ss");
	private String fmtDate = dateFormat.format(new Date(System.currentTimeMillis()));
	private String storeId;
	private String sharedSecret;
	private String charge;
	private String currency;

	public IPGIntegrationUtil(String storeId, String sharedSecret, String charge, String currency) {
		super();
		this.storeId = storeId;
		this.sharedSecret = sharedSecret;
		this.charge = charge;
		this.currency = currency;
	}

	public String createHash() {
		String stringToHash = storeId + fmtDate + charge + currency + sharedSecret;
		return calculateHashFromHex(new StringBuffer(stringToHash));
	}

	private String calculateHashFromHex(StringBuffer buffer) {
		String algorithm = "SHA1";
		MessageDigest messageDigest = null;
		try {
			messageDigest = MessageDigest.getInstance(algorithm);
		} catch (Exception e) {
			throw new IllegalArgumentException("Algorithm '" + algorithm + "' not supported");
		}
		StringBuffer result = new StringBuffer();
		StringBuffer sb = new StringBuffer();
		byte[] bytes = buffer.toString().getBytes();
		int byteLen = bytes.length;
		for (int i = 0; i < byteLen; i++) {
			byte b = bytes[i];
			sb.append(Character.forDigit((b & 240) >> 4, 16));
			sb.append(Character.forDigit((b & 15), 16));
		}
		buffer = new StringBuffer(sb.toString());
		messageDigest.update(buffer.toString().getBytes());
		byte[] message = messageDigest.digest();
		int messageLen = message.length;
		for (int j = 0; j < messageLen; j++) {
			byte b = message[j];
			String apps = Integer.toHexString(b & 0xff);
			if (apps.length() == 1) {
				apps = "0" + apps;
			}
			result.append(apps);
		}
		return result.toString();
	}

	public String getCharge() {
		return charge;
	}

	public String getSharedSecret() {
		return sharedSecret;
	}

	public String getStoreId() {
		return storeId;
	}

	public String getFormattedSysDate() {
		return fmtDate;
	}

}
