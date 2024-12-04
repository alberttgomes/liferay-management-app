package com.management.app.internal.search;

import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.search.batch.DynamicQueryBatchIndexingActionableFactory;
import com.liferay.portal.search.spi.model.index.contributor.ModelIndexerWriterContributor;
import com.liferay.portal.search.spi.model.registrar.ModelSearchConfigurator;
import com.liferay.portal.search.spi.model.result.contributor.ModelSummaryContributor;

import com.management.app.model.Employee;
import com.management.app.internal.search.index.contributor.EmployeeModelIndexerWriterContributor;
import com.management.app.service.EmployeeLocalService;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Albert Cabral
 */
@Component(service = ModelSearchConfigurator.class)
public class EmployeeModelSearchConfigurator
        implements ModelSearchConfigurator<Employee> {

    @Override
    public String getClassName() {
        return Employee.class.getName();
    }

    @Override
    public String[] getDefaultSelectedFieldNames() {
        return new String[] {Field.NAME, Field.CLASS_PK};
    }

    @Override
    public ModelIndexerWriterContributor<Employee>
        getModelIndexerWriterContributor() {

        return _modelIndexerWriterContributor;
    }

    @Activate
    protected void activate() {
        _modelIndexerWriterContributor =
                new EmployeeModelIndexerWriterContributor(
                        _dynamicQueryBatchIndexingActionableFactory,
                        _employeeLocalService);
    }

    @Override
    public ModelSummaryContributor getModelSummaryContributor() {
        return ModelSearchConfigurator.super.getModelSummaryContributor();
    }

    @Reference
    private DynamicQueryBatchIndexingActionableFactory
            _dynamicQueryBatchIndexingActionableFactory;

    @Reference
    private EmployeeLocalService _employeeLocalService;

    private ModelIndexerWriterContributor<Employee>
            _modelIndexerWriterContributor;

}
