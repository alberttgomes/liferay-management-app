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
            document.addText(
                    Field.NAME, employee.getFirstName());
            document.addNumber(
                    "employeeId", employee.getEmployeeId());
            document.addKeyword(
                    "department", employee.getDepartment());
            document.addText(
                    "firstName", employee.getFirstName());
            document.addText(
                    "lastName", employee.getFirstName());
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
