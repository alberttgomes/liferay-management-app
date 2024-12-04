package com.management.app.internal.constant;

import com.liferay.portal.kernel.util.HashMapBuilder;

import java.util.Map;

/**
 * @author Albert Cabral
 */
public class EmployeeStructureConstants {

    // Departments

    public static final String DEPARTMENT_ENGINEER = "engineer";

    public static final String DEPARTMENT_GENERAL = "general";

    public static final String[] DEPARTMENTS = {
            DEPARTMENT_ENGINEER, DEPARTMENT_GENERAL};

    // Levels by Position

    public static int LEVEL_ONE = 1;
    public static int LEVEL_TWO = 2;
    public static int LEVEL_THREE = 3;
    public static int LEVEL_FOUR = 4;
    public static int LEVEL_FIVE = 5;
    public static int LEVEL_UNIQUE = 6;

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

    public static int[] LEVELS_MID_SOFTWARE_ENGINEER = {
            LEVEL_ONE, LEVEL_TWO, LEVEL_THREE, LEVEL_FOUR, LEVEL_FIVE};

    public static int[] LEVELS_TEAM_LEADER_SOFTWARE_ENGINEER = {
            LEVEL_ONE, LEVEL_TWO, LEVEL_THREE, LEVEL_FOUR, LEVEL_FIVE};

    // Positions

    public static final long HIERARCHY_ASSOC_SOFTWARE_ENGINEER = 1;
    public static final long HIERARCHY_CHIEF_TECHNOLOGY_OFFICER = 10;
    public static final long HIERARCHY_DIRECTOR_SOFTWARE_ENGINEER_LONG = 7;
    public static final long HIERARCHY_HUMAN_SOFTWARE_ENGINEER = 1;
    public static final long HIERARCHY_MID_SOFTWARE_ENGINEER = 2;
    public static final long HIERARCHY_INCIDENT_MANAGEMENT_ENGINEER = 3;
    public static final long HIERARCHY_PRODUCT_DESIGN = 1;
    public static final long HIERARCHY_SENIOR_SOFTWARE_ENGINEER = 3;
    public static final long HIERARCHY_TEAM_LEADER_SOFTWARE_ENGINEER = 4;

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

    public static Map<String, Long> getHierarchyPositionEngineerMap() {
        return HashMapBuilder.put(
                TITLE_ASSOC_SOFTWARE_ENGINEER, HIERARCHY_ASSOC_SOFTWARE_ENGINEER
        ).put(
                TITLE_MID_SOFTWARE_ENGINEER, HIERARCHY_MID_SOFTWARE_ENGINEER
        ).put(
                TITLE_SENIOR_SOFTWARE_ENGINEER, HIERARCHY_SENIOR_SOFTWARE_ENGINEER
        ).put(
                TITLE_TEAM_LEADER_SOFTWARE_ENGINEER, HIERARCHY_TEAM_LEADER_SOFTWARE_ENGINEER
        ).put(
                TITLE_INCIDENT_MANAGEMENT_ENGINEER, HIERARCHY_INCIDENT_MANAGEMENT_ENGINEER
        ).build();
    }

    public static final Map<String, Long>
            HIERARCHY_PRODUCT_DESIGN_POSITIONS_HASH_MAP =
                HashMapBuilder.put(
                        TITLE_PRODUCT_DESIGN,
                        HIERARCHY_PRODUCT_DESIGN
                ).build();

    public static String[] POSITIONS_BY_GENERAL_DEPARTMENT = {
            TITLE_HUMAN_RESOURCES, TITLE_MARKETING, TITLE_OPERATION_IT,
            TITLE_PRODUCT_DESIGN, TITLE_PRODUCT_MANAGER, TITLE_SALES_PRODUCT};

    public static String[] POSITIONS_BY_ENGINEER_DEPARTMENT = {
            TITLE_ASSOC_SOFTWARE_ENGINEER, TITLE_CHIEF_TECHNOLOGY_OFFICER,
            TITLE_DIRECTOR_OF_ENGINEER, TITLE_INCIDENT_MANAGEMENT_ENGINEER,
            TITLE_MID_SOFTWARE_ENGINEER, TITLE_SENIOR_SOFTWARE_ENGINEER,
            TITLE_TEAM_LEADER_SOFTWARE_ENGINEER};

    public static Map<String, int[]> getAllPositionAndLevelsMap() {
        return HashMapBuilder.put(
                TITLE_ASSOC_SOFTWARE_ENGINEER, LEVELS_ASSOC_SOFTWARE_ENGINEER
        ).put(
                TITLE_CHIEF_TECHNOLOGY_OFFICER, new int[LEVEL_UNIQUE]
        ).put(
                TITLE_DIRECTOR_OF_ENGINEER, new int[LEVEL_UNIQUE]
        ).put(
                TITLE_HUMAN_RESOURCES, LEVELS_HUMAN_RESOURCES
        ).put(
                TITLE_INCIDENT_MANAGEMENT_ENGINEER, LEVELS_INCIDENT_MANAGEMENT_ENGINEER
        ).put(
                TITLE_MARKETING,  new int[LEVEL_UNIQUE]
        ).put(
                TITLE_MID_SOFTWARE_ENGINEER, LEVELS_MID_SOFTWARE_ENGINEER
        ).put(
                TITLE_OPERATION_IT, new int[LEVEL_UNIQUE]
        ).put(
                TITLE_PRODUCT_DESIGN, LEVELS_PRODUCT_DESIGN
        ).put(
                TITLE_PRODUCT_MANAGER, LEVELS_PRODUCT_MANAGER
        ).put(
                TITLE_SALES_PRODUCT, new int[LEVEL_UNIQUE]
        ).put(
                TITLE_SENIOR_SOFTWARE_ENGINEER, LEVELS_SENIOR_SOFTWARE_ENGINEER
        ).put(
                TITLE_TEAM_LEADER_SOFTWARE_ENGINEER, LEVELS_TEAM_LEADER_SOFTWARE_ENGINEER
        ).build();
    }

}
