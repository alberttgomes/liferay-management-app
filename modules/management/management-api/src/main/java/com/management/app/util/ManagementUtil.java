package com.management.app.util;

import java.util.Random;

/**
 * @author Albert Cabral
 */
public class ManagementUtil {

    public static String generateRandomPassword() {
        return new Random().doubles().toString();

    }

}
