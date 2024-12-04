package com.management.app.service.helper;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.transaction.Propagation;
import com.liferay.portal.kernel.transaction.Transactional;

import java.util.Map;

/**
 * @author Albert Cabral
 */
public interface EmployeeManagementHelper {

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    Object addDependentHealthPlan(
            long employeeId, long userManagerId) throws PortalException;

    void dismissalEmployee(
            long employeeId, long userManagerId) throws PortalException;

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    Map<String, Object> requestBenefitEducation(
            String description, long employeeId, String value, String type)
         throws PortalException;

    void requestDismissalSelf(
            String description, long employeeId, String value, String type)
        throws PortalException;

}
