/**
 * SPDX-FileCopyrightText: (c) 2024 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.management.app.service;

import com.liferay.petra.sql.dsl.query.DSLQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.PersistedModel;
import com.liferay.portal.kernel.util.OrderByComparator;

import com.management.app.model.Manager;

import java.io.Serializable;

import java.util.List;

/**
 * Provides the local service utility for Manager. This utility wraps
 * <code>com.management.app.service.impl.ManagerLocalServiceImpl</code> and
 * is an access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author Albert Cabral
 * @see ManagerLocalService
 * @generated
 */
public class ManagerLocalServiceUtil {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to <code>com.management.app.service.impl.ManagerLocalServiceImpl</code> and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	 * Adds the manager to the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect ManagerLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param manager the manager
	 * @return the manager that was added
	 */
	public static Manager addManager(Manager manager) {
		return getService().addManager(manager);
	}

	/**
	 * Creates a new manager with the primary key. Does not add the manager to the database.
	 *
	 * @param managerId the primary key for the new manager
	 * @return the new manager
	 */
	public static Manager createManager(long managerId) {
		return getService().createManager(managerId);
	}

	public static Manager createManager(
			long groupId, long companyId, long employeeIdPK, long mvccVersion)
		throws com.management.app.exception.NoSuchEmployeeException {

		return getService().createManager(
			groupId, companyId, employeeIdPK, mvccVersion);
	}

	/**
	 * @throws PortalException
	 */
	public static PersistedModel createPersistedModel(
			Serializable primaryKeyObj)
		throws PortalException {

		return getService().createPersistedModel(primaryKeyObj);
	}

	/**
	 * Deletes the manager with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect ManagerLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param managerId the primary key of the manager
	 * @return the manager that was removed
	 * @throws PortalException if a manager with the primary key could not be found
	 */
	public static Manager deleteManager(long managerId) throws PortalException {
		return getService().deleteManager(managerId);
	}

	/**
	 * Deletes the manager from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect ManagerLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param manager the manager
	 * @return the manager that was removed
	 */
	public static Manager deleteManager(Manager manager) {
		return getService().deleteManager(manager);
	}

	/**
	 * @throws PortalException
	 */
	public static PersistedModel deletePersistedModel(
			PersistedModel persistedModel)
		throws PortalException {

		return getService().deletePersistedModel(persistedModel);
	}

	public static <T> T dslQuery(DSLQuery dslQuery) {
		return getService().dslQuery(dslQuery);
	}

	public static int dslQueryCount(DSLQuery dslQuery) {
		return getService().dslQueryCount(dslQuery);
	}

	public static DynamicQuery dynamicQuery() {
		return getService().dynamicQuery();
	}

	/**
	 * Performs a dynamic query on the database and returns the matching rows.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the matching rows
	 */
	public static <T> List<T> dynamicQuery(DynamicQuery dynamicQuery) {
		return getService().dynamicQuery(dynamicQuery);
	}

	/**
	 * Performs a dynamic query on the database and returns a range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.management.app.model.impl.ManagerModelImpl</code>.
	 * </p>
	 *
	 * @param dynamicQuery the dynamic query
	 * @param start the lower bound of the range of model instances
	 * @param end the upper bound of the range of model instances (not inclusive)
	 * @return the range of matching rows
	 */
	public static <T> List<T> dynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {

		return getService().dynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.management.app.model.impl.ManagerModelImpl</code>.
	 * </p>
	 *
	 * @param dynamicQuery the dynamic query
	 * @param start the lower bound of the range of model instances
	 * @param end the upper bound of the range of model instances (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching rows
	 */
	public static <T> List<T> dynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<T> orderByComparator) {

		return getService().dynamicQuery(
			dynamicQuery, start, end, orderByComparator);
	}

	/**
	 * Returns the number of rows matching the dynamic query.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the number of rows matching the dynamic query
	 */
	public static long dynamicQueryCount(DynamicQuery dynamicQuery) {
		return getService().dynamicQueryCount(dynamicQuery);
	}

	/**
	 * Returns the number of rows matching the dynamic query.
	 *
	 * @param dynamicQuery the dynamic query
	 * @param projection the projection to apply to the query
	 * @return the number of rows matching the dynamic query
	 */
	public static long dynamicQueryCount(
		DynamicQuery dynamicQuery,
		com.liferay.portal.kernel.dao.orm.Projection projection) {

		return getService().dynamicQueryCount(dynamicQuery, projection);
	}

	public static Manager fetchManager(long managerId) {
		return getService().fetchManager(managerId);
	}

	public static Manager fetchManagerByFirstNameAndLastName(
			String firstName, String lastName)
		throws Exception {

		return getService().fetchManagerByFirstNameAndLastName(
			firstName, lastName);
	}

	/**
	 * Returns the manager matching the UUID and group.
	 *
	 * @param uuid the manager's UUID
	 * @param groupId the primary key of the group
	 * @return the matching manager, or <code>null</code> if a matching manager could not be found
	 */
	public static Manager fetchManagerByUuidAndGroupId(
		String uuid, long groupId) {

		return getService().fetchManagerByUuidAndGroupId(uuid, groupId);
	}

	public static com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery
		getActionableDynamicQuery() {

		return getService().getActionableDynamicQuery();
	}

	public static com.liferay.portal.kernel.dao.orm.ExportActionableDynamicQuery
		getExportActionableDynamicQuery(
			com.liferay.exportimport.kernel.lar.PortletDataContext
				portletDataContext) {

		return getService().getExportActionableDynamicQuery(portletDataContext);
	}

	public static
		com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery
			getIndexableActionableDynamicQuery() {

		return getService().getIndexableActionableDynamicQuery();
	}

	/**
	 * Returns the manager with the primary key.
	 *
	 * @param managerId the primary key of the manager
	 * @return the manager
	 * @throws PortalException if a manager with the primary key could not be found
	 */
	public static Manager getManager(long managerId) throws PortalException {
		return getService().getManager(managerId);
	}

	/**
	 * Returns the manager matching the UUID and group.
	 *
	 * @param uuid the manager's UUID
	 * @param groupId the primary key of the group
	 * @return the matching manager
	 * @throws PortalException if a matching manager could not be found
	 */
	public static Manager getManagerByUuidAndGroupId(String uuid, long groupId)
		throws PortalException {

		return getService().getManagerByUuidAndGroupId(uuid, groupId);
	}

	/**
	 * Returns a range of all the managers.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.management.app.model.impl.ManagerModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of managers
	 * @param end the upper bound of the range of managers (not inclusive)
	 * @return the range of managers
	 */
	public static List<Manager> getManagers(int start, int end) {
		return getService().getManagers(start, end);
	}

	/**
	 * Returns all the managers matching the UUID and company.
	 *
	 * @param uuid the UUID of the managers
	 * @param companyId the primary key of the company
	 * @return the matching managers, or an empty list if no matches were found
	 */
	public static List<Manager> getManagersByUuidAndCompanyId(
		String uuid, long companyId) {

		return getService().getManagersByUuidAndCompanyId(uuid, companyId);
	}

	/**
	 * Returns a range of managers matching the UUID and company.
	 *
	 * @param uuid the UUID of the managers
	 * @param companyId the primary key of the company
	 * @param start the lower bound of the range of managers
	 * @param end the upper bound of the range of managers (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the range of matching managers, or an empty list if no matches were found
	 */
	public static List<Manager> getManagersByUuidAndCompanyId(
		String uuid, long companyId, int start, int end,
		OrderByComparator<Manager> orderByComparator) {

		return getService().getManagersByUuidAndCompanyId(
			uuid, companyId, start, end, orderByComparator);
	}

	/**
	 * Returns the number of managers.
	 *
	 * @return the number of managers
	 */
	public static int getManagersCount() {
		return getService().getManagersCount();
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	public static String getOSGiServiceIdentifier() {
		return getService().getOSGiServiceIdentifier();
	}

	/**
	 * @throws PortalException
	 */
	public static PersistedModel getPersistedModel(Serializable primaryKeyObj)
		throws PortalException {

		return getService().getPersistedModel(primaryKeyObj);
	}

	/**
	 * Updates the manager in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect ManagerLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param manager the manager
	 * @return the manager that was updated
	 */
	public static Manager updateManager(Manager manager) {
		return getService().updateManager(manager);
	}

	public static ManagerLocalService getService() {
		return _service;
	}

	public static void setService(ManagerLocalService service) {
		_service = service;
	}

	private static volatile ManagerLocalService _service;

}