package com.management.app.util;

import com.liferay.portal.kernel.security.SecureRandomUtil;

import java.util.UUID;

/**
 * @author Albert Cabral
 */
public class ManagementUtil {

    public static String generateRandomPassword() {
        UUID uuid = new UUID(
                SecureRandomUtil.nextLong(), SecureRandomUtil.nextLong());

        return uuid.toString();
    }

}
