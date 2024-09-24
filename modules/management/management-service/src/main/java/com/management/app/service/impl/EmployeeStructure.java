package com.management.app.service.impl;

/**
 * @author Albert Cabral
 */
public class EmployeeStructure {

    // Departments

    public static final String DEPARTMENT_ENGINEER = "engineer";

    public static final String DEPARTMENT_GENERAL = "general";

    // Levels by Position

    public static int LEVEL_ONE = 1;
    public static int LEVEL_TWO = 2;
    public static int LEVEL_THREE = 3;
    public static int LEVEL_FOUR = 4;
    public static int LEVEL_FIVE = 5;

    public static int[] LEVELS_ASSOC_SOFTWARE_ENGINEER = {
            LEVEL_ONE, LEVEL_TWO, LEVEL_THREE, LEVEL_FOUR};

    public static int[] LEVELS_DIRECTOR_OF_ENGINEER = {
            LEVEL_ONE, LEVEL_TWO, LEVEL_THREE};

    public static int[] LEVELS_HUMAN_RESOURCES = {
            LEVEL_ONE, LEVEL_TWO, LEVEL_THREE};

    public static int[] LEVELS_INCIDENT_MANAGEMENT_ENGINEER = {
            LEVEL_ONE, LEVEL_TWO, LEVEL_THREE, LEVEL_FOUR, LEVEL_FIVE};

    public static int[] LEVELS_PRODUCT_DESIGN = {
            LEVEL_ONE, LEVEL_TWO, LEVEL_THREE, LEVEL_FOUR};

    public static int[] LEVELS_PRODUCT_MANAGER = {
            LEVEL_ONE, LEVEL_TWO, LEVEL_THREE, LEVEL_FOUR, LEVEL_FIVE};

    public static int[] LEVELS_SENIOR_SOFTWARE_ENGINEER = {
            LEVEL_ONE, LEVEL_TWO, LEVEL_THREE};

    public static int[] LEVELS_SOFTWARE_ENGINEER = {
            LEVEL_ONE, LEVEL_TWO, LEVEL_THREE, LEVEL_FOUR, LEVEL_FIVE};

    public static int[] LEVELS_TEAM_LEADER_SOFTWARE_ENGINEER = {
            LEVEL_ONE, LEVEL_TWO, LEVEL_THREE, LEVEL_FOUR, LEVEL_FIVE};

    // Positions

    public static final String TITLE_ASSOC_SOFTWARE_ENGINEER =
            "Assoc Software Engineer";

    public static final String TITLE_CHIEF_TECHNOLOGY_OFFICER =
            "Chief Technology Officer";

    public static final String TITLE_DIRECTOR_OF_ENGINEER =
            "Director Of Engineer";

    public static final String TITLE_HUMAN_RESOURCES = "Human Resources";

    public static final String TITLE_INCIDENT_MANAGEMENT_ENGINEER =
            "Incident Management Engineer";

    public static final String TITLE_MARKETING = "Marketing";

    public static final String TITLE_OPERATION_IT = "Operation IT";

    public static final String TITLE_MID_SOFTWARE_ENGINEER =
            "Mid Software Engineer";

    public static final String TITLE_PRODUCT_DESIGN = "Product Design";

    public static final String TITLE_PRODUCT_MANAGER =
            "Product Manager";

    public static final String TITLE_SALES_PRODUCT = "Sales Product";

    public static final String TITLE_SENIOR_SOFTWARE_ENGINEER =
            "Senior Software Engineer";

    public static final String TITLE_TEAM_LEADER_SOFTWARE_ENGINEER =
            "Team Leader Software Engineer";

}
