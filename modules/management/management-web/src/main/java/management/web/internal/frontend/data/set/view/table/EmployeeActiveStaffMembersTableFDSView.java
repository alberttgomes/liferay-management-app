package management.web.internal.frontend.data.set.view.table;

import com.liferay.frontend.data.set.view.FDSView;
import com.liferay.frontend.data.set.view.table.BaseTableFDSView;
import com.liferay.frontend.data.set.view.table.FDSTableSchema;
import com.liferay.frontend.data.set.view.table.FDSTableSchemaBuilder;
import com.liferay.frontend.data.set.view.table.FDSTableSchemaBuilderFactory;

import java.util.Locale;

import management.web.constants.EmployeeDetailsFDSNames;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Albert Cabral
 */
@Component(
    property = "frontend.data.set.name=" + EmployeeDetailsFDSNames.EMPLOYEE_PROMOTION,
    service = FDSView.class
)
public class EmployeeActiveStaffMembersTableFDSView extends BaseTableFDSView {

    @Override
    public FDSTableSchema getFDSTableSchema(Locale locale) {
        FDSTableSchemaBuilder fdsTableSchemaBuilder =
                _fdsTableSchemaBuilderFactory.create();

        return fdsTableSchemaBuilder.add(
            "department", "Department",
            fdsTableSchemaField -> fdsTableSchemaField.setSortable(true)
        ).add(
            "firstName", "First Name",
            fdsTableSchemaField -> fdsTableSchemaField.setSortable(true)
        ).add(
            "lastName", "Last Name",
            fdsTableSchemaField -> fdsTableSchemaField.setSortable(true)
        ).add(
            "position", "Position",
            fdsTableSchemaField -> fdsTableSchemaField.setSortable(true)
        ).build();
    }

    @Reference
    private FDSTableSchemaBuilderFactory _fdsTableSchemaBuilderFactory;

}
