package com.management.app.service.util;

/**
 * @author Albert Cabral
 */
public class EmployeeStatusConstant {

    public static int[] getStatus () {
        return _STATUS;
    }

    public static final int ACTIVE = 1;

    public static final int INACTIVE = -1;

    public static final int DELETED = 0;

    public static final int PAUSED = 3;

    private static final int[] _STATUS = {ACTIVE, INACTIVE, DELETED, PAUSED};

}
