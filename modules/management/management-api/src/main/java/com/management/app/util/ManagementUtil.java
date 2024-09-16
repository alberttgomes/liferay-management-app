package com.management.app.util;

import org.apache.commons.lang3.RandomStringUtils;

/**
 * @author Albert Cabral
 */
public class ManagementUtil {

    public static String generateRandomPassword() {
        int length = 32;
        boolean useLetters = true;
        boolean useNumbers = true;

        return RandomStringUtils.random(
                length, useLetters, useNumbers);

    }

}
