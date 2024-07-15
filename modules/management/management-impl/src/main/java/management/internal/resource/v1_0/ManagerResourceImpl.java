package management.internal.resource.v1_0;

import management.resource.v1_0.ManagerResource;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.ServiceScope;

/**
 * @author Albert Cabral
 */
@Component(
	properties = "OSGI-INF/liferay/rest/v1_0/manager.properties",
	scope = ServiceScope.PROTOTYPE, service = ManagerResource.class
)
public class ManagerResourceImpl extends BaseManagerResourceImpl {
}