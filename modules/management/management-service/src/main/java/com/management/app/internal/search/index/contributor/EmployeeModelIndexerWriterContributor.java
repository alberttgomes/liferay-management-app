package com.management.app.internal.search.index.contributor;

import com.liferay.portal.search.batch.BatchIndexingActionable;
import com.liferay.portal.search.batch.DynamicQueryBatchIndexingActionableFactory;
import com.liferay.portal.search.spi.model.index.contributor.ModelIndexerWriterContributor;
import com.liferay.portal.search.spi.model.index.contributor.helper.ModelIndexerWriterDocumentHelper;

import com.management.app.model.Employee;
import com.management.app.service.EmployeeLocalService;

/**
 * @author Albert Cabral
 */
public class EmployeeModelIndexerWriterContributor
        implements ModelIndexerWriterContributor<Employee> {

    public EmployeeModelIndexerWriterContributor(
            DynamicQueryBatchIndexingActionableFactory
                    dynamicQueryBatchIndexingActionableFactory,
            EmployeeLocalService employeeLocalService) {

        this._dynamicQueryBatchIndexingActionableFactory =
                dynamicQueryBatchIndexingActionableFactory;
        this._employeeLocalService = employeeLocalService;
    }

    @Override
    public void customize(
            BatchIndexingActionable batchIndexingActionable,
            ModelIndexerWriterDocumentHelper modelIndexerWriterDocumentHelper) {

        batchIndexingActionable.setPerformActionMethod(
                (Employee employee) -> batchIndexingActionable.addDocuments(
                        modelIndexerWriterDocumentHelper.getDocument(employee)));
    }

    @Override
    public BatchIndexingActionable getBatchIndexingActionable() {
        return _dynamicQueryBatchIndexingActionableFactory.
                getBatchIndexingActionable(
                        _employeeLocalService.
                                getIndexableActionableDynamicQuery());
    }

    @Override
    public long getCompanyId(Employee employee) {
        return employee.getCompanyId();
    }

    private final DynamicQueryBatchIndexingActionableFactory
            _dynamicQueryBatchIndexingActionableFactory;

    private final EmployeeLocalService _employeeLocalService;

}
