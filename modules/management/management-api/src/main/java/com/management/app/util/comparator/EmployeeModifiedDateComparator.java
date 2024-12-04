package com.management.app.util.comparator;

import com.liferay.exportimport.kernel.lar.StagedModelModifiedDateComparator;
import com.management.app.model.Employee;

/**
 * @author Albert Cabral
 */
public class EmployeeModifiedDateComparator extends
        StagedModelModifiedDateComparator<Employee> {

    public EmployeeModifiedDateComparator() {
        this(false);
    }

    public EmployeeModifiedDateComparator(boolean ascending) {
        super(ascending);
    }

    @Override
    public String getOrderBy() {
        return "Employee." + super.getOrderBy();
    }

}
