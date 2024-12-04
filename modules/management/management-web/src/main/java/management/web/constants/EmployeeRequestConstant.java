package management.web.constants;

/**
 * @author Albert Cabral
 */
public class EmployeeRequestConstant {

    public static final long BENEFIT_EDUCATION = 0;

    public static final long COURSE = 3;

    public static final long COURSE_TO_EMPLOYEE = 4;

    public static final long DEPENDENT_HEALTH_PLAN = 1;

    public static final long DISMISSAL = -1;

    public static final long DISMISSAL_EMPLOYEE = -2;

    public static final long PROMOTION = 2;

    public static long[] getEmployeeRequestIds() {
        return new long[] {
                BENEFIT_EDUCATION, DEPENDENT_HEALTH_PLAN,
                DISMISSAL, PROMOTION
        };
    }

    public static long[] getManagerRequestIds() {
        return new long[] {
                PROMOTION, DISMISSAL_EMPLOYEE,
                COURSE, COURSE_TO_EMPLOYEE
        };
    }

}
