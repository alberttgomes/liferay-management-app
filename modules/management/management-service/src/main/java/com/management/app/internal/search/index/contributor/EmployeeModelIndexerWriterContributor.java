package com.management.app.internal.search.index.contributor;

import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.ProjectionFactoryUtil;
import com.liferay.portal.kernel.dao.orm.Property;
import com.liferay.portal.kernel.dao.orm.PropertyFactoryUtil;
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

        batchIndexingActionable.setAddCriteriaMethod(
                dynamicQuery -> {
                    Property empployeeIdProperty =
                            _propertyFactory("employeeId");

                    DynamicQuery employeeDynamicQuery =
                            _employeeLocalService.dynamicQuery();

                    employeeDynamicQuery.setProjection(
                            ProjectionFactoryUtil.property("employeeId"));

                    dynamicQuery.add(
                            empployeeIdProperty.in(employeeDynamicQuery));

                    Property employeeDepartmentProperty =
                            _propertyFactory("department");

                    employeeDynamicQuery.setProjection(
                            ProjectionFactoryUtil.property("department"));

                    dynamicQuery.add(
                            employeeDepartmentProperty.in(employeeDynamicQuery));

                    Property employeeFirstNameProperty =
                            _propertyFactory("firstName");

                    employeeDynamicQuery.setProjection(
                            ProjectionFactoryUtil.property("firstName"));

                    dynamicQuery.add(
                            employeeFirstNameProperty.in(employeeDynamicQuery));

                });
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

    private Property _propertyFactory(String propertyName) {
        return PropertyFactoryUtil.forName(propertyName);
    }

    private final DynamicQueryBatchIndexingActionableFactory
            _dynamicQueryBatchIndexingActionableFactory;

    private final EmployeeLocalService _employeeLocalService;

}
