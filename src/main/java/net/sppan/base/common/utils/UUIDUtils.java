package net.sppan.base.common.utils;

import java.util.UUID;

public class UUIDUtils {
	public static String getUUID() {
		return UUID.randomUUID().toString().replace("-", "").toString();
	}
}
