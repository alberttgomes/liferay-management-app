/**
 * SPDX-FileCopyrightText: (c) 2024 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.management.app.service.persistence;

import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.OrderByComparator;

import com.management.app.model.Manager;

import java.io.Serializable;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * The persistence utility for the manager service. This utility wraps <code>com.management.app.service.persistence.impl.ManagerPersistenceImpl</code> and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Albert Cabral
 * @see ManagerPersistence
 * @generated
 */
public class ManagerUtil {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#clearCache()
	 */
	public static void clearCache() {
		getPersistence().clearCache();
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#clearCache(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static void clearCache(Manager manager) {
		getPersistence().clearCache(manager);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#countWithDynamicQuery(DynamicQuery)
	 */
	public static long countWithDynamicQuery(DynamicQuery dynamicQuery) {
		return getPersistence().countWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#fetchByPrimaryKeys(Set)
	 */
	public static Map<Serializable, Manager> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {

		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery)
	 */
	public static List<Manager> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {

		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<Manager> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {

		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<Manager> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<Manager> orderByComparator) {

		return getPersistence().findWithDynamicQuery(
			dynamicQuery, start, end, orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static Manager update(Manager manager) {
		return getPersistence().update(manager);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static Manager update(
		Manager manager, ServiceContext serviceContext) {

		return getPersistence().update(manager, serviceContext);
	}

	/**
	 * Returns all the managers where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the matching managers
	 */
	public static List<Manager> findByUuid(String uuid) {
		return getPersistence().findByUuid(uuid);
	}

	/**
	 * Returns a range of all the managers where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ManagerModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of managers
	 * @param end the upper bound of the range of managers (not inclusive)
	 * @return the range of matching managers
	 */
	public static List<Manager> findByUuid(String uuid, int start, int end) {
		return getPersistence().findByUuid(uuid, start, end);
	}

	/**
	 * Returns an ordered range of all the managers where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ManagerModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of managers
	 * @param end the upper bound of the range of managers (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching managers
	 */
	public static List<Manager> findByUuid(
		String uuid, int start, int end,
		OrderByComparator<Manager> orderByComparator) {

		return getPersistence().findByUuid(uuid, start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the managers where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ManagerModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of managers
	 * @param end the upper bound of the range of managers (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching managers
	 */
	public static List<Manager> findByUuid(
		String uuid, int start, int end,
		OrderByComparator<Manager> orderByComparator, boolean useFinderCache) {

		return getPersistence().findByUuid(
			uuid, start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Returns the first manager in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching manager
	 * @throws NoSuchManagerException if a matching manager could not be found
	 */
	public static Manager findByUuid_First(
			String uuid, OrderByComparator<Manager> orderByComparator)
		throws com.management.app.exception.NoSuchManagerException {

		return getPersistence().findByUuid_First(uuid, orderByComparator);
	}

	/**
	 * Returns the first manager in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching manager, or <code>null</code> if a matching manager could not be found
	 */
	public static Manager fetchByUuid_First(
		String uuid, OrderByComparator<Manager> orderByComparator) {

		return getPersistence().fetchByUuid_First(uuid, orderByComparator);
	}

	/**
	 * Returns the last manager in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching manager
	 * @throws NoSuchManagerException if a matching manager could not be found
	 */
	public static Manager findByUuid_Last(
			String uuid, OrderByComparator<Manager> orderByComparator)
		throws com.management.app.exception.NoSuchManagerException {

		return getPersistence().findByUuid_Last(uuid, orderByComparator);
	}

	/**
	 * Returns the last manager in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching manager, or <code>null</code> if a matching manager could not be found
	 */
	public static Manager fetchByUuid_Last(
		String uuid, OrderByComparator<Manager> orderByComparator) {

		return getPersistence().fetchByUuid_Last(uuid, orderByComparator);
	}

	/**
	 * Returns the managers before and after the current manager in the ordered set where uuid = &#63;.
	 *
	 * @param managerId the primary key of the current manager
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next manager
	 * @throws NoSuchManagerException if a manager with the primary key could not be found
	 */
	public static Manager[] findByUuid_PrevAndNext(
			long managerId, String uuid,
			OrderByComparator<Manager> orderByComparator)
		throws com.management.app.exception.NoSuchManagerException {

		return getPersistence().findByUuid_PrevAndNext(
			managerId, uuid, orderByComparator);
	}

	/**
	 * Removes all the managers where uuid = &#63; from the database.
	 *
	 * @param uuid the uuid
	 */
	public static void removeByUuid(String uuid) {
		getPersistence().removeByUuid(uuid);
	}

	/**
	 * Returns the number of managers where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the number of matching managers
	 */
	public static int countByUuid(String uuid) {
		return getPersistence().countByUuid(uuid);
	}

	/**
	 * Returns the manager where uuid = &#63; and groupId = &#63; or throws a <code>NoSuchManagerException</code> if it could not be found.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the matching manager
	 * @throws NoSuchManagerException if a matching manager could not be found
	 */
	public static Manager findByUUID_G(String uuid, long groupId)
		throws com.management.app.exception.NoSuchManagerException {

		return getPersistence().findByUUID_G(uuid, groupId);
	}

	/**
	 * Returns the manager where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the matching manager, or <code>null</code> if a matching manager could not be found
	 */
	public static Manager fetchByUUID_G(String uuid, long groupId) {
		return getPersistence().fetchByUUID_G(uuid, groupId);
	}

	/**
	 * Returns the manager where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @param useFinderCache whether to use the finder cache
	 * @return the matching manager, or <code>null</code> if a matching manager could not be found
	 */
	public static Manager fetchByUUID_G(
		String uuid, long groupId, boolean useFinderCache) {

		return getPersistence().fetchByUUID_G(uuid, groupId, useFinderCache);
	}

	/**
	 * Removes the manager where uuid = &#63; and groupId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the manager that was removed
	 */
	public static Manager removeByUUID_G(String uuid, long groupId)
		throws com.management.app.exception.NoSuchManagerException {

		return getPersistence().removeByUUID_G(uuid, groupId);
	}

	/**
	 * Returns the number of managers where uuid = &#63; and groupId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the number of matching managers
	 */
	public static int countByUUID_G(String uuid, long groupId) {
		return getPersistence().countByUUID_G(uuid, groupId);
	}

	/**
	 * Returns all the managers where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the matching managers
	 */
	public static List<Manager> findByUuid_C(String uuid, long companyId) {
		return getPersistence().findByUuid_C(uuid, companyId);
	}

	/**
	 * Returns a range of all the managers where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ManagerModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of managers
	 * @param end the upper bound of the range of managers (not inclusive)
	 * @return the range of matching managers
	 */
	public static List<Manager> findByUuid_C(
		String uuid, long companyId, int start, int end) {

		return getPersistence().findByUuid_C(uuid, companyId, start, end);
	}

	/**
	 * Returns an ordered range of all the managers where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ManagerModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of managers
	 * @param end the upper bound of the range of managers (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching managers
	 */
	public static List<Manager> findByUuid_C(
		String uuid, long companyId, int start, int end,
		OrderByComparator<Manager> orderByComparator) {

		return getPersistence().findByUuid_C(
			uuid, companyId, start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the managers where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ManagerModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of managers
	 * @param end the upper bound of the range of managers (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching managers
	 */
	public static List<Manager> findByUuid_C(
		String uuid, long companyId, int start, int end,
		OrderByComparator<Manager> orderByComparator, boolean useFinderCache) {

		return getPersistence().findByUuid_C(
			uuid, companyId, start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Returns the first manager in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching manager
	 * @throws NoSuchManagerException if a matching manager could not be found
	 */
	public static Manager findByUuid_C_First(
			String uuid, long companyId,
			OrderByComparator<Manager> orderByComparator)
		throws com.management.app.exception.NoSuchManagerException {

		return getPersistence().findByUuid_C_First(
			uuid, companyId, orderByComparator);
	}

	/**
	 * Returns the first manager in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching manager, or <code>null</code> if a matching manager could not be found
	 */
	public static Manager fetchByUuid_C_First(
		String uuid, long companyId,
		OrderByComparator<Manager> orderByComparator) {

		return getPersistence().fetchByUuid_C_First(
			uuid, companyId, orderByComparator);
	}

	/**
	 * Returns the last manager in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching manager
	 * @throws NoSuchManagerException if a matching manager could not be found
	 */
	public static Manager findByUuid_C_Last(
			String uuid, long companyId,
			OrderByComparator<Manager> orderByComparator)
		throws com.management.app.exception.NoSuchManagerException {

		return getPersistence().findByUuid_C_Last(
			uuid, companyId, orderByComparator);
	}

	/**
	 * Returns the last manager in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching manager, or <code>null</code> if a matching manager could not be found
	 */
	public static Manager fetchByUuid_C_Last(
		String uuid, long companyId,
		OrderByComparator<Manager> orderByComparator) {

		return getPersistence().fetchByUuid_C_Last(
			uuid, companyId, orderByComparator);
	}

	/**
	 * Returns the managers before and after the current manager in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param managerId the primary key of the current manager
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next manager
	 * @throws NoSuchManagerException if a manager with the primary key could not be found
	 */
	public static Manager[] findByUuid_C_PrevAndNext(
			long managerId, String uuid, long companyId,
			OrderByComparator<Manager> orderByComparator)
		throws com.management.app.exception.NoSuchManagerException {

		return getPersistence().findByUuid_C_PrevAndNext(
			managerId, uuid, companyId, orderByComparator);
	}

	/**
	 * Removes all the managers where uuid = &#63; and companyId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 */
	public static void removeByUuid_C(String uuid, long companyId) {
		getPersistence().removeByUuid_C(uuid, companyId);
	}

	/**
	 * Returns the number of managers where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the number of matching managers
	 */
	public static int countByUuid_C(String uuid, long companyId) {
		return getPersistence().countByUuid_C(uuid, companyId);
	}

	/**
	 * Returns all the managers where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @return the matching managers
	 */
	public static List<Manager> findByCompanyId(long companyId) {
		return getPersistence().findByCompanyId(companyId);
	}

	/**
	 * Returns a range of all the managers where companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ManagerModelImpl</code>.
	 * </p>
	 *
	 * @param companyId the company ID
	 * @param start the lower bound of the range of managers
	 * @param end the upper bound of the range of managers (not inclusive)
	 * @return the range of matching managers
	 */
	public static List<Manager> findByCompanyId(
		long companyId, int start, int end) {

		return getPersistence().findByCompanyId(companyId, start, end);
	}

	/**
	 * Returns an ordered range of all the managers where companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ManagerModelImpl</code>.
	 * </p>
	 *
	 * @param companyId the company ID
	 * @param start the lower bound of the range of managers
	 * @param end the upper bound of the range of managers (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching managers
	 */
	public static List<Manager> findByCompanyId(
		long companyId, int start, int end,
		OrderByComparator<Manager> orderByComparator) {

		return getPersistence().findByCompanyId(
			companyId, start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the managers where companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ManagerModelImpl</code>.
	 * </p>
	 *
	 * @param companyId the company ID
	 * @param start the lower bound of the range of managers
	 * @param end the upper bound of the range of managers (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching managers
	 */
	public static List<Manager> findByCompanyId(
		long companyId, int start, int end,
		OrderByComparator<Manager> orderByComparator, boolean useFinderCache) {

		return getPersistence().findByCompanyId(
			companyId, start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Returns the first manager in the ordered set where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching manager
	 * @throws NoSuchManagerException if a matching manager could not be found
	 */
	public static Manager findByCompanyId_First(
			long companyId, OrderByComparator<Manager> orderByComparator)
		throws com.management.app.exception.NoSuchManagerException {

		return getPersistence().findByCompanyId_First(
			companyId, orderByComparator);
	}

	/**
	 * Returns the first manager in the ordered set where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching manager, or <code>null</code> if a matching manager could not be found
	 */
	public static Manager fetchByCompanyId_First(
		long companyId, OrderByComparator<Manager> orderByComparator) {

		return getPersistence().fetchByCompanyId_First(
			companyId, orderByComparator);
	}

	/**
	 * Returns the last manager in the ordered set where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching manager
	 * @throws NoSuchManagerException if a matching manager could not be found
	 */
	public static Manager findByCompanyId_Last(
			long companyId, OrderByComparator<Manager> orderByComparator)
		throws com.management.app.exception.NoSuchManagerException {

		return getPersistence().findByCompanyId_Last(
			companyId, orderByComparator);
	}

	/**
	 * Returns the last manager in the ordered set where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching manager, or <code>null</code> if a matching manager could not be found
	 */
	public static Manager fetchByCompanyId_Last(
		long companyId, OrderByComparator<Manager> orderByComparator) {

		return getPersistence().fetchByCompanyId_Last(
			companyId, orderByComparator);
	}

	/**
	 * Returns the managers before and after the current manager in the ordered set where companyId = &#63;.
	 *
	 * @param managerId the primary key of the current manager
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next manager
	 * @throws NoSuchManagerException if a manager with the primary key could not be found
	 */
	public static Manager[] findByCompanyId_PrevAndNext(
			long managerId, long companyId,
			OrderByComparator<Manager> orderByComparator)
		throws com.management.app.exception.NoSuchManagerException {

		return getPersistence().findByCompanyId_PrevAndNext(
			managerId, companyId, orderByComparator);
	}

	/**
	 * Removes all the managers where companyId = &#63; from the database.
	 *
	 * @param companyId the company ID
	 */
	public static void removeByCompanyId(long companyId) {
		getPersistence().removeByCompanyId(companyId);
	}

	/**
	 * Returns the number of managers where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @return the number of matching managers
	 */
	public static int countByCompanyId(long companyId) {
		return getPersistence().countByCompanyId(companyId);
	}

	/**
	 * Returns all the managers where managerId = &#63;.
	 *
	 * @param managerId the manager ID
	 * @return the matching managers
	 */
	public static List<Manager> findByManagerId(long managerId) {
		return getPersistence().findByManagerId(managerId);
	}

	/**
	 * Returns a range of all the managers where managerId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ManagerModelImpl</code>.
	 * </p>
	 *
	 * @param managerId the manager ID
	 * @param start the lower bound of the range of managers
	 * @param end the upper bound of the range of managers (not inclusive)
	 * @return the range of matching managers
	 */
	public static List<Manager> findByManagerId(
		long managerId, int start, int end) {

		return getPersistence().findByManagerId(managerId, start, end);
	}

	/**
	 * Returns an ordered range of all the managers where managerId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ManagerModelImpl</code>.
	 * </p>
	 *
	 * @param managerId the manager ID
	 * @param start the lower bound of the range of managers
	 * @param end the upper bound of the range of managers (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching managers
	 */
	public static List<Manager> findByManagerId(
		long managerId, int start, int end,
		OrderByComparator<Manager> orderByComparator) {

		return getPersistence().findByManagerId(
			managerId, start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the managers where managerId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ManagerModelImpl</code>.
	 * </p>
	 *
	 * @param managerId the manager ID
	 * @param start the lower bound of the range of managers
	 * @param end the upper bound of the range of managers (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching managers
	 */
	public static List<Manager> findByManagerId(
		long managerId, int start, int end,
		OrderByComparator<Manager> orderByComparator, boolean useFinderCache) {

		return getPersistence().findByManagerId(
			managerId, start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Returns the first manager in the ordered set where managerId = &#63;.
	 *
	 * @param managerId the manager ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching manager
	 * @throws NoSuchManagerException if a matching manager could not be found
	 */
	public static Manager findByManagerId_First(
			long managerId, OrderByComparator<Manager> orderByComparator)
		throws com.management.app.exception.NoSuchManagerException {

		return getPersistence().findByManagerId_First(
			managerId, orderByComparator);
	}

	/**
	 * Returns the first manager in the ordered set where managerId = &#63;.
	 *
	 * @param managerId the manager ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching manager, or <code>null</code> if a matching manager could not be found
	 */
	public static Manager fetchByManagerId_First(
		long managerId, OrderByComparator<Manager> orderByComparator) {

		return getPersistence().fetchByManagerId_First(
			managerId, orderByComparator);
	}

	/**
	 * Returns the last manager in the ordered set where managerId = &#63;.
	 *
	 * @param managerId the manager ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching manager
	 * @throws NoSuchManagerException if a matching manager could not be found
	 */
	public static Manager findByManagerId_Last(
			long managerId, OrderByComparator<Manager> orderByComparator)
		throws com.management.app.exception.NoSuchManagerException {

		return getPersistence().findByManagerId_Last(
			managerId, orderByComparator);
	}

	/**
	 * Returns the last manager in the ordered set where managerId = &#63;.
	 *
	 * @param managerId the manager ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching manager, or <code>null</code> if a matching manager could not be found
	 */
	public static Manager fetchByManagerId_Last(
		long managerId, OrderByComparator<Manager> orderByComparator) {

		return getPersistence().fetchByManagerId_Last(
			managerId, orderByComparator);
	}

	/**
	 * Removes all the managers where managerId = &#63; from the database.
	 *
	 * @param managerId the manager ID
	 */
	public static void removeByManagerId(long managerId) {
		getPersistence().removeByManagerId(managerId);
	}

	/**
	 * Returns the number of managers where managerId = &#63;.
	 *
	 * @param managerId the manager ID
	 * @return the number of matching managers
	 */
	public static int countByManagerId(long managerId) {
		return getPersistence().countByManagerId(managerId);
	}

	/**
	 * Returns the manager where employeeIdPK = &#63; and managerId = &#63; or throws a <code>NoSuchManagerException</code> if it could not be found.
	 *
	 * @param employeeIdPK the employee ID pk
	 * @param managerId the manager ID
	 * @return the matching manager
	 * @throws NoSuchManagerException if a matching manager could not be found
	 */
	public static Manager findByE_M(long employeeIdPK, long managerId)
		throws com.management.app.exception.NoSuchManagerException {

		return getPersistence().findByE_M(employeeIdPK, managerId);
	}

	/**
	 * Returns the manager where employeeIdPK = &#63; and managerId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param employeeIdPK the employee ID pk
	 * @param managerId the manager ID
	 * @return the matching manager, or <code>null</code> if a matching manager could not be found
	 */
	public static Manager fetchByE_M(long employeeIdPK, long managerId) {
		return getPersistence().fetchByE_M(employeeIdPK, managerId);
	}

	/**
	 * Returns the manager where employeeIdPK = &#63; and managerId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param employeeIdPK the employee ID pk
	 * @param managerId the manager ID
	 * @param useFinderCache whether to use the finder cache
	 * @return the matching manager, or <code>null</code> if a matching manager could not be found
	 */
	public static Manager fetchByE_M(
		long employeeIdPK, long managerId, boolean useFinderCache) {

		return getPersistence().fetchByE_M(
			employeeIdPK, managerId, useFinderCache);
	}

	/**
	 * Removes the manager where employeeIdPK = &#63; and managerId = &#63; from the database.
	 *
	 * @param employeeIdPK the employee ID pk
	 * @param managerId the manager ID
	 * @return the manager that was removed
	 */
	public static Manager removeByE_M(long employeeIdPK, long managerId)
		throws com.management.app.exception.NoSuchManagerException {

		return getPersistence().removeByE_M(employeeIdPK, managerId);
	}

	/**
	 * Returns the number of managers where employeeIdPK = &#63; and managerId = &#63;.
	 *
	 * @param employeeIdPK the employee ID pk
	 * @param managerId the manager ID
	 * @return the number of matching managers
	 */
	public static int countByE_M(long employeeIdPK, long managerId) {
		return getPersistence().countByE_M(employeeIdPK, managerId);
	}

	/**
	 * Caches the manager in the entity cache if it is enabled.
	 *
	 * @param manager the manager
	 */
	public static void cacheResult(Manager manager) {
		getPersistence().cacheResult(manager);
	}

	/**
	 * Caches the managers in the entity cache if it is enabled.
	 *
	 * @param managers the managers
	 */
	public static void cacheResult(List<Manager> managers) {
		getPersistence().cacheResult(managers);
	}

	/**
	 * Creates a new manager with the primary key. Does not add the manager to the database.
	 *
	 * @param managerId the primary key for the new manager
	 * @return the new manager
	 */
	public static Manager create(long managerId) {
		return getPersistence().create(managerId);
	}

	/**
	 * Removes the manager with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param managerId the primary key of the manager
	 * @return the manager that was removed
	 * @throws NoSuchManagerException if a manager with the primary key could not be found
	 */
	public static Manager remove(long managerId)
		throws com.management.app.exception.NoSuchManagerException {

		return getPersistence().remove(managerId);
	}

	public static Manager updateImpl(Manager manager) {
		return getPersistence().updateImpl(manager);
	}

	/**
	 * Returns the manager with the primary key or throws a <code>NoSuchManagerException</code> if it could not be found.
	 *
	 * @param managerId the primary key of the manager
	 * @return the manager
	 * @throws NoSuchManagerException if a manager with the primary key could not be found
	 */
	public static Manager findByPrimaryKey(long managerId)
		throws com.management.app.exception.NoSuchManagerException {

		return getPersistence().findByPrimaryKey(managerId);
	}

	/**
	 * Returns the manager with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param managerId the primary key of the manager
	 * @return the manager, or <code>null</code> if a manager with the primary key could not be found
	 */
	public static Manager fetchByPrimaryKey(long managerId) {
		return getPersistence().fetchByPrimaryKey(managerId);
	}

	/**
	 * Returns all the managers.
	 *
	 * @return the managers
	 */
	public static List<Manager> findAll() {
		return getPersistence().findAll();
	}

	/**
	 * Returns a range of all the managers.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ManagerModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of managers
	 * @param end the upper bound of the range of managers (not inclusive)
	 * @return the range of managers
	 */
	public static List<Manager> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	 * Returns an ordered range of all the managers.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ManagerModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of managers
	 * @param end the upper bound of the range of managers (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of managers
	 */
	public static List<Manager> findAll(
		int start, int end, OrderByComparator<Manager> orderByComparator) {

		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the managers.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ManagerModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of managers
	 * @param end the upper bound of the range of managers (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of managers
	 */
	public static List<Manager> findAll(
		int start, int end, OrderByComparator<Manager> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findAll(
			start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Removes all the managers from the database.
	 */
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	 * Returns the number of managers.
	 *
	 * @return the number of managers
	 */
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static ManagerPersistence getPersistence() {
		return _persistence;
	}

	public static void setPersistence(ManagerPersistence persistence) {
		_persistence = persistence;
	}

	private static volatile ManagerPersistence _persistence;

}