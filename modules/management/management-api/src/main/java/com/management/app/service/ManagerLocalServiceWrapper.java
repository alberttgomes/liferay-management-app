/**
 * SPDX-FileCopyrightText: (c) 2024 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.management.app.service;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.service.ServiceWrapper;
import com.liferay.portal.kernel.service.persistence.BasePersistence;
import com.management.app.exception.NoSuchManagerException;
import com.management.app.model.Manager;

/**
 * Provides a wrapper for {@link ManagerLocalService}.
 *
 * @author Albert Cabral
 * @see ManagerLocalService
 * @generated
 */
public class ManagerLocalServiceWrapper
	implements ManagerLocalService, ServiceWrapper<ManagerLocalService> {

	public ManagerLocalServiceWrapper() {
		this(null);
	}

	public ManagerLocalServiceWrapper(ManagerLocalService managerLocalService) {
		_managerLocalService = managerLocalService;
	}

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
	@Override
	public com.management.app.model.Manager addManager(
		com.management.app.model.Manager manager) {

		return _managerLocalService.addManager(manager);
	}

	/**
	 * Creates a new manager with the primary key. Does not add the manager to the database.
	 *
	 * @param managerId the primary key for the new manager
	 * @return the new manager
	 */
	@Override
	public com.management.app.model.Manager createManager(long managerId) {
		return _managerLocalService.createManager(managerId);
	}

	@Override
	public com.management.app.model.Manager createManager(
			long groupId, long companyId, long employeeIdPK, long mvccVersion)
		throws com.management.app.exception.NoSuchEmployeeException {

		return _managerLocalService.createManager(
			groupId, companyId, employeeIdPK, mvccVersion);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel createPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _managerLocalService.createPersistedModel(primaryKeyObj);
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
	@Override
	public com.management.app.model.Manager deleteManager(long managerId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _managerLocalService.deleteManager(managerId);
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
	@Override
	public com.management.app.model.Manager deleteManager(
		com.management.app.model.Manager manager) {

		return _managerLocalService.deleteManager(manager);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
			com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _managerLocalService.deletePersistedModel(persistedModel);
	}

	@Override
	public <T> T dslQuery(com.liferay.petra.sql.dsl.query.DSLQuery dslQuery) {
		return _managerLocalService.dslQuery(dslQuery);
	}

	@Override
	public int dslQueryCount(
		com.liferay.petra.sql.dsl.query.DSLQuery dslQuery) {

		return _managerLocalService.dslQueryCount(dslQuery);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _managerLocalService.dynamicQuery();
	}

	/**
	 * Performs a dynamic query on the database and returns the matching rows.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the matching rows
	 */
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery) {

		return _managerLocalService.dynamicQuery(dynamicQuery);
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
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) {

		return _managerLocalService.dynamicQuery(dynamicQuery, start, end);
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
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<T> orderByComparator) {

		return _managerLocalService.dynamicQuery(
			dynamicQuery, start, end, orderByComparator);
	}

	/**
	 * Returns the number of rows matching the dynamic query.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the number of rows matching the dynamic query
	 */
	@Override
	public long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery) {

		return _managerLocalService.dynamicQueryCount(dynamicQuery);
	}

	/**
	 * Returns the number of rows matching the dynamic query.
	 *
	 * @param dynamicQuery the dynamic query
	 * @param projection the projection to apply to the query
	 * @return the number of rows matching the dynamic query
	 */
	@Override
	public long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery,
		com.liferay.portal.kernel.dao.orm.Projection projection) {

		return _managerLocalService.dynamicQueryCount(dynamicQuery, projection);
	}

	@Override
	public com.management.app.model.Manager fetchManager(long managerId) {
		return _managerLocalService.fetchManager(managerId);
	}

	@Override
	public com.management.app.model.Manager fetchManagerByFirstNameAndLastName(
			String firstName, String lastName)
		throws Exception {

		return _managerLocalService.fetchManagerByFirstNameAndLastName(
			firstName, lastName);
	}

	@Override
	public Manager findByCompanyIdAndEmployeeId(long companyId, long employeeId)
		throws NoSuchManagerException {

		return _managerLocalService.findByCompanyIdAndEmployeeId(companyId, employeeId);
	}

	/**
	 * Returns the manager matching the UUID and group.
	 *
	 * @param uuid the manager's UUID
	 * @param groupId the primary key of the group
	 * @return the matching manager, or <code>null</code> if a matching manager could not be found
	 */
	@Override
	public com.management.app.model.Manager fetchManagerByUuidAndGroupId(
		String uuid, long groupId) {

		return _managerLocalService.fetchManagerByUuidAndGroupId(uuid, groupId);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery
		getActionableDynamicQuery() {

		return _managerLocalService.getActionableDynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ExportActionableDynamicQuery
		getExportActionableDynamicQuery(
			com.liferay.exportimport.kernel.lar.PortletDataContext
				portletDataContext) {

		return _managerLocalService.getExportActionableDynamicQuery(
			portletDataContext);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery
		getIndexableActionableDynamicQuery() {

		return _managerLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	 * Returns the manager with the primary key.
	 *
	 * @param managerId the primary key of the manager
	 * @return the manager
	 * @throws PortalException if a manager with the primary key could not be found
	 */
	@Override
	public com.management.app.model.Manager getManager(long managerId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _managerLocalService.getManager(managerId);
	}

	/**
	 * Returns the manager matching the UUID and group.
	 *
	 * @param uuid the manager's UUID
	 * @param groupId the primary key of the group
	 * @return the matching manager
	 * @throws PortalException if a matching manager could not be found
	 */
	@Override
	public com.management.app.model.Manager getManagerByUuidAndGroupId(
			String uuid, long groupId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _managerLocalService.getManagerByUuidAndGroupId(uuid, groupId);
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
	@Override
	public java.util.List<com.management.app.model.Manager> getManagers(
		int start, int end) {

		return _managerLocalService.getManagers(start, end);
	}

	/**
	 * Returns all the managers matching the UUID and company.
	 *
	 * @param uuid the UUID of the managers
	 * @param companyId the primary key of the company
	 * @return the matching managers, or an empty list if no matches were found
	 */
	@Override
	public java.util.List<com.management.app.model.Manager>
		getManagersByUuidAndCompanyId(String uuid, long companyId) {

		return _managerLocalService.getManagersByUuidAndCompanyId(
			uuid, companyId);
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
	@Override
	public java.util.List<com.management.app.model.Manager>
		getManagersByUuidAndCompanyId(
			String uuid, long companyId, int start, int end,
			com.liferay.portal.kernel.util.OrderByComparator
				<com.management.app.model.Manager> orderByComparator) {

		return _managerLocalService.getManagersByUuidAndCompanyId(
			uuid, companyId, start, end, orderByComparator);
	}

	/**
	 * Returns the number of managers.
	 *
	 * @return the number of managers
	 */
	@Override
	public int getManagersCount() {
		return _managerLocalService.getManagersCount();
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _managerLocalService.getOSGiServiceIdentifier();
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _managerLocalService.getPersistedModel(primaryKeyObj);
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
	@Override
	public com.management.app.model.Manager updateManager(
		com.management.app.model.Manager manager) {

		return _managerLocalService.updateManager(manager);
	}

	@Override
	public BasePersistence<?> getBasePersistence() {
		return _managerLocalService.getBasePersistence();
	}

	@Override
	public ManagerLocalService getWrappedService() {
		return _managerLocalService;
	}

	@Override
	public void setWrappedService(ManagerLocalService managerLocalService) {
		_managerLocalService = managerLocalService;
	}

	private ManagerLocalService _managerLocalService;

}