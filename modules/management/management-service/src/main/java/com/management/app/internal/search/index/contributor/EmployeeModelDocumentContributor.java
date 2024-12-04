package com.management.app.internal.search.index.contributor;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.search.Document;
import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.kernel.service.ClassNameLocalService;
import com.liferay.portal.search.spi.model.index.contributor.ModelDocumentContributor;

import com.management.app.model.Employee;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Albert Cabral
 */
@Component(
    property = "indexer.class.name=com.management.app.model.Employee",
    service = ModelDocumentContributor.class
)
public class EmployeeModelDocumentContributor
    implements ModelDocumentContributor<Employee> {

    @Override
    public void contribute(Document document, Employee employee) {
        try {
            document.addKeyword(
                    Field.CLASS_NAME_ID,
                    classNameLocalService.getClassNameId(Employee.class));
            document.addKeyword(
                    Field.CLASS_TYPE_ID, employee.getEmployeeId());
            document.addKeyword(
                    Field.STATUS, employee.getStatus());
            document.addKeyword(
                    "employeeId", employee.getEmployeeId());
            document.addKeyword(
                    "firstName", employee.getFirstName());
            document.addKeyword(
                    "department", employee.getDepartment());
        }
        catch (Exception exception) {
            if (_log.isErrorEnabled()) {
                _log.error(exception);
            }
        }
    }

    @Reference
    protected ClassNameLocalService classNameLocalService;

    private static final Log _log = LogFactoryUtil.getLog(
            EmployeeModelDocumentContributor.class);

}
