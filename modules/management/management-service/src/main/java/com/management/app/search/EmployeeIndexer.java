package com.management.app.search;

import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.search.*;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.ListUtil;
import com.liferay.portal.kernel.util.StringBundler;

import com.management.app.model.Employee;
import com.management.app.service.EmployeeLocalService;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Locale;
import javax.portlet.PortletRequest;
import javax.portlet.PortletResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Albert Cabral
 */
@Component(service = Indexer.class)
public class EmployeeIndexer extends BaseIndexer<Employee> {

    @Override
    protected void doDelete(Employee employee) throws Exception {
        deleteDocument(
                employee.getCompanyId(),
                employee.getEmployeeId());
    }

    @Override
    protected Document doGetDocument(Employee employee) throws Exception {
        Document document =
                getBaseModelDocument(
                        Employee.class.getName(), employee);

        long[] employeeIds = _getAllEmployees();

        document.addKeyword(
                Field.STATUS, employee.getStatus());
        document.addNumber(
                "allEmployeeIds", employeeIds);
        document.addNumber(
                "employeeId", employee.getEmployeeId());
        document.addText(
                "department", employee.getDepartment());
        document.addText(
                Field.NAME, employee.getFirstName());
        document.addText(
                "position", employee.getPosition());

        if (employee.getIsManager()) {
            document.addKeyword(
                    "isManager", employee.getIsManager());
        }

        return document;
    }

    @Override
    protected Summary doGetSummary(
            Document document, Locale locale, String s,
            PortletRequest portletRequest, PortletResponse portletResponse)
        throws Exception {

        Summary summary = createSummary(
                document, Field.ENTRY_CLASS_PK, Field.NAME);

        summary.setMaxContentLength(200);

        return summary;

    }

    @Override
    protected void doReindex(String className, long classPK) throws Exception {
        doReindex(_employeeLocalService.fetchEmployee(classPK));
    }

    @Override
    protected void doReindex(String[] strings) throws Exception {
        long companyId = GetterUtil.getLong(strings[0]);

        _reindexEmployees(companyId);
    }

    @Override
    protected void doReindex(Employee employee) throws Exception {
        Collection<Document> documentCollection = new ArrayList<>();

        documentCollection.add(getDocument(employee));

        _indexWriterHelper.updateDocuments(
                employee.getEmployeeId(),
                documentCollection, isCommitImmediately());
    }

    @Override
    public String getClassName() {
        return Employee.class.getName();
    }

    private long[] _getAllEmployees() {
        return ListUtil.toLongArray(
                _employeeLocalService.getEmployees(QueryUtil.ALL_POS, 20),
                Employee::getEmployeeId);
    }

    private void _reindexEmployees(long companyId) throws PortalException {
        IndexableActionableDynamicQuery indexableActionableDynamicQuery =
                _employeeLocalService.getIndexableActionableDynamicQuery();

        indexableActionableDynamicQuery.setCompanyId(companyId);
        indexableActionableDynamicQuery.setPerformActionMethod(
                (Employee employee) -> {
                    if (_log.isDebugEnabled()) {
                        _log.debug(StringBundler.concat("Indexing employee",
                                StringPool.SPACE, employee.getFirstName()));
                    }

                    indexableActionableDynamicQuery.addDocuments(
                            getDocument(employee));
                });

        indexableActionableDynamicQuery.performActions();
    }

    private static final Log _log = LogFactoryUtil.getLog(EmployeeIndexer.class);

    @Reference
    private EmployeeLocalService _employeeLocalService;

    @Reference
    private IndexWriterHelper _indexWriterHelper;

}
