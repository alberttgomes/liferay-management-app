/**
 * SPDX-FileCopyrightText: (c) 2024 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.management.app.service.persistence;

import com.liferay.portal.kernel.service.persistence.BasePersistence;

import com.management.app.exception.NoSuchManagerException;
import com.management.app.model.Manager;

import org.osgi.annotation.versioning.ProviderType;

/**
 * The persistence interface for the manager service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Albert Cabral
 * @see ManagerUtil
 * @generated
 */
@ProviderType
public interface ManagerPersistence extends BasePersistence<Manager> {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link ManagerUtil} to access the manager persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	 * Returns all the managers where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the matching managers
	 */
	public java.util.List<Manager> findByUuid(String uuid);

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
	public java.util.List<Manager> findByUuid(String uuid, int start, int end);

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
	public java.util.List<Manager> findByUuid(
		String uuid, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Manager>
			orderByComparator);

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
	public java.util.List<Manager> findByUuid(
		String uuid, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Manager>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Returns the first manager in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching manager
	 * @throws NoSuchManagerException if a matching manager could not be found
	 */
	public Manager findByUuid_First(
			String uuid,
			com.liferay.portal.kernel.util.OrderByComparator<Manager>
				orderByComparator)
		throws NoSuchManagerException;

	/**
	 * Returns the first manager in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching manager, or <code>null</code> if a matching manager could not be found
	 */
	public Manager fetchByUuid_First(
		String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<Manager>
			orderByComparator);

	/**
	 * Returns the last manager in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching manager
	 * @throws NoSuchManagerException if a matching manager could not be found
	 */
	public Manager findByUuid_Last(
			String uuid,
			com.liferay.portal.kernel.util.OrderByComparator<Manager>
				orderByComparator)
		throws NoSuchManagerException;

	/**
	 * Returns the last manager in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching manager, or <code>null</code> if a matching manager could not be found
	 */
	public Manager fetchByUuid_Last(
		String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<Manager>
			orderByComparator);

	/**
	 * Returns the managers before and after the current manager in the ordered set where uuid = &#63;.
	 *
	 * @param managerId the primary key of the current manager
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next manager
	 * @throws NoSuchManagerException if a manager with the primary key could not be found
	 */
	public Manager[] findByUuid_PrevAndNext(
			long managerId, String uuid,
			com.liferay.portal.kernel.util.OrderByComparator<Manager>
				orderByComparator)
		throws NoSuchManagerException;

	/**
	 * Removes all the managers where uuid = &#63; from the database.
	 *
	 * @param uuid the uuid
	 */
	public void removeByUuid(String uuid);

	/**
	 * Returns the number of managers where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the number of matching managers
	 */
	public int countByUuid(String uuid);

	/**
	 * Returns the manager where uuid = &#63; and groupId = &#63; or throws a <code>NoSuchManagerException</code> if it could not be found.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the matching manager
	 * @throws NoSuchManagerException if a matching manager could not be found
	 */
	public Manager findByUUID_G(String uuid, long groupId)
		throws NoSuchManagerException;

	/**
	 * Returns the manager where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the matching manager, or <code>null</code> if a matching manager could not be found
	 */
	public Manager fetchByUUID_G(String uuid, long groupId);

	/**
	 * Returns the manager where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @param useFinderCache whether to use the finder cache
	 * @return the matching manager, or <code>null</code> if a matching manager could not be found
	 */
	public Manager fetchByUUID_G(
		String uuid, long groupId, boolean useFinderCache);

	/**
	 * Removes the manager where uuid = &#63; and groupId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the manager that was removed
	 */
	public Manager removeByUUID_G(String uuid, long groupId)
		throws NoSuchManagerException;

	/**
	 * Returns the number of managers where uuid = &#63; and groupId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the number of matching managers
	 */
	public int countByUUID_G(String uuid, long groupId);

	/**
	 * Returns all the managers where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the matching managers
	 */
	public java.util.List<Manager> findByUuid_C(String uuid, long companyId);

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
	public java.util.List<Manager> findByUuid_C(
		String uuid, long companyId, int start, int end);

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
	public java.util.List<Manager> findByUuid_C(
		String uuid, long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Manager>
			orderByComparator);

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
	public java.util.List<Manager> findByUuid_C(
		String uuid, long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Manager>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Returns the first manager in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching manager
	 * @throws NoSuchManagerException if a matching manager could not be found
	 */
	public Manager findByUuid_C_First(
			String uuid, long companyId,
			com.liferay.portal.kernel.util.OrderByComparator<Manager>
				orderByComparator)
		throws NoSuchManagerException;

	/**
	 * Returns the first manager in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching manager, or <code>null</code> if a matching manager could not be found
	 */
	public Manager fetchByUuid_C_First(
		String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<Manager>
			orderByComparator);

	/**
	 * Returns the last manager in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching manager
	 * @throws NoSuchManagerException if a matching manager could not be found
	 */
	public Manager findByUuid_C_Last(
			String uuid, long companyId,
			com.liferay.portal.kernel.util.OrderByComparator<Manager>
				orderByComparator)
		throws NoSuchManagerException;

	/**
	 * Returns the last manager in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching manager, or <code>null</code> if a matching manager could not be found
	 */
	public Manager fetchByUuid_C_Last(
		String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<Manager>
			orderByComparator);

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
	public Manager[] findByUuid_C_PrevAndNext(
			long managerId, String uuid, long companyId,
			com.liferay.portal.kernel.util.OrderByComparator<Manager>
				orderByComparator)
		throws NoSuchManagerException;

	/**
	 * Removes all the managers where uuid = &#63; and companyId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 */
	public void removeByUuid_C(String uuid, long companyId);

	/**
	 * Returns the number of managers where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the number of matching managers
	 */
	public int countByUuid_C(String uuid, long companyId);

	/**
	 * Returns all the managers where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @return the matching managers
	 */
	public java.util.List<Manager> findByCompanyId(long companyId);

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
	public java.util.List<Manager> findByCompanyId(
		long companyId, int start, int end);

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
	public java.util.List<Manager> findByCompanyId(
		long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Manager>
			orderByComparator);

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
	public java.util.List<Manager> findByCompanyId(
		long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Manager>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Returns the first manager in the ordered set where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching manager
	 * @throws NoSuchManagerException if a matching manager could not be found
	 */
	public Manager findByCompanyId_First(
			long companyId,
			com.liferay.portal.kernel.util.OrderByComparator<Manager>
				orderByComparator)
		throws NoSuchManagerException;

	/**
	 * Returns the first manager in the ordered set where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching manager, or <code>null</code> if a matching manager could not be found
	 */
	public Manager fetchByCompanyId_First(
		long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<Manager>
			orderByComparator);

	/**
	 * Returns the last manager in the ordered set where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching manager
	 * @throws NoSuchManagerException if a matching manager could not be found
	 */
	public Manager findByCompanyId_Last(
			long companyId,
			com.liferay.portal.kernel.util.OrderByComparator<Manager>
				orderByComparator)
		throws NoSuchManagerException;

	/**
	 * Returns the last manager in the ordered set where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching manager, or <code>null</code> if a matching manager could not be found
	 */
	public Manager fetchByCompanyId_Last(
		long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<Manager>
			orderByComparator);

	/**
	 * Returns the managers before and after the current manager in the ordered set where companyId = &#63;.
	 *
	 * @param managerId the primary key of the current manager
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next manager
	 * @throws NoSuchManagerException if a manager with the primary key could not be found
	 */
	public Manager[] findByCompanyId_PrevAndNext(
			long managerId, long companyId,
			com.liferay.portal.kernel.util.OrderByComparator<Manager>
				orderByComparator)
		throws NoSuchManagerException;

	/**
	 * Removes all the managers where companyId = &#63; from the database.
	 *
	 * @param companyId the company ID
	 */
	public void removeByCompanyId(long companyId);

	/**
	 * Returns the number of managers where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @return the number of matching managers
	 */
	public int countByCompanyId(long companyId);

	/**
	 * Returns all the managers where managerId = &#63;.
	 *
	 * @param managerId the manager ID
	 * @return the matching managers
	 */
	public java.util.List<Manager> findByManagerId(long managerId);

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
	public java.util.List<Manager> findByManagerId(
		long managerId, int start, int end);

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
	public java.util.List<Manager> findByManagerId(
		long managerId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Manager>
			orderByComparator);

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
	public java.util.List<Manager> findByManagerId(
		long managerId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Manager>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Returns the first manager in the ordered set where managerId = &#63;.
	 *
	 * @param managerId the manager ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching manager
	 * @throws NoSuchManagerException if a matching manager could not be found
	 */
	public Manager findByManagerId_First(
			long managerId,
			com.liferay.portal.kernel.util.OrderByComparator<Manager>
				orderByComparator)
		throws NoSuchManagerException;

	/**
	 * Returns the first manager in the ordered set where managerId = &#63;.
	 *
	 * @param managerId the manager ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching manager, or <code>null</code> if a matching manager could not be found
	 */
	public Manager fetchByManagerId_First(
		long managerId,
		com.liferay.portal.kernel.util.OrderByComparator<Manager>
			orderByComparator);

	/**
	 * Returns the last manager in the ordered set where managerId = &#63;.
	 *
	 * @param managerId the manager ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching manager
	 * @throws NoSuchManagerException if a matching manager could not be found
	 */
	public Manager findByManagerId_Last(
			long managerId,
			com.liferay.portal.kernel.util.OrderByComparator<Manager>
				orderByComparator)
		throws NoSuchManagerException;

	/**
	 * Returns the last manager in the ordered set where managerId = &#63;.
	 *
	 * @param managerId the manager ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching manager, or <code>null</code> if a matching manager could not be found
	 */
	public Manager fetchByManagerId_Last(
		long managerId,
		com.liferay.portal.kernel.util.OrderByComparator<Manager>
			orderByComparator);

	/**
	 * Removes all the managers where managerId = &#63; from the database.
	 *
	 * @param managerId the manager ID
	 */
	public void removeByManagerId(long managerId);

	/**
	 * Returns the number of managers where managerId = &#63;.
	 *
	 * @param managerId the manager ID
	 * @return the number of matching managers
	 */
	public int countByManagerId(long managerId);

	/**
	 * Returns the manager where employeeIdPK = &#63; and managerId = &#63; or throws a <code>NoSuchManagerException</code> if it could not be found.
	 *
	 * @param employeeIdPK the employee ID pk
	 * @param managerId the manager ID
	 * @return the matching manager
	 * @throws NoSuchManagerException if a matching manager could not be found
	 */
	public Manager findByE_M(long employeeIdPK, long managerId)
		throws NoSuchManagerException;

	/**
	 * Returns the manager where employeeIdPK = &#63; and managerId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param employeeIdPK the employee ID pk
	 * @param managerId the manager ID
	 * @return the matching manager, or <code>null</code> if a matching manager could not be found
	 */
	public Manager fetchByE_M(long employeeIdPK, long managerId);

	/**
	 * Returns the manager where employeeIdPK = &#63; and managerId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param employeeIdPK the employee ID pk
	 * @param managerId the manager ID
	 * @param useFinderCache whether to use the finder cache
	 * @return the matching manager, or <code>null</code> if a matching manager could not be found
	 */
	public Manager fetchByE_M(
		long employeeIdPK, long managerId, boolean useFinderCache);

	/**
	 * Removes the manager where employeeIdPK = &#63; and managerId = &#63; from the database.
	 *
	 * @param employeeIdPK the employee ID pk
	 * @param managerId the manager ID
	 * @return the manager that was removed
	 */
	public Manager removeByE_M(long employeeIdPK, long managerId)
		throws NoSuchManagerException;

	/**
	 * Returns the number of managers where employeeIdPK = &#63; and managerId = &#63;.
	 *
	 * @param employeeIdPK the employee ID pk
	 * @param managerId the manager ID
	 * @return the number of matching managers
	 */
	public int countByE_M(long employeeIdPK, long managerId);

	/**
	 * Caches the manager in the entity cache if it is enabled.
	 *
	 * @param manager the manager
	 */
	public void cacheResult(Manager manager);

	/**
	 * Caches the managers in the entity cache if it is enabled.
	 *
	 * @param managers the managers
	 */
	public void cacheResult(java.util.List<Manager> managers);

	/**
	 * Creates a new manager with the primary key. Does not add the manager to the database.
	 *
	 * @param managerId the primary key for the new manager
	 * @return the new manager
	 */
	public Manager create(long managerId);

	/**
	 * Removes the manager with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param managerId the primary key of the manager
	 * @return the manager that was removed
	 * @throws NoSuchManagerException if a manager with the primary key could not be found
	 */
	public Manager remove(long managerId) throws NoSuchManagerException;

	public Manager updateImpl(Manager manager);

	/**
	 * Returns the manager with the primary key or throws a <code>NoSuchManagerException</code> if it could not be found.
	 *
	 * @param managerId the primary key of the manager
	 * @return the manager
	 * @throws NoSuchManagerException if a manager with the primary key could not be found
	 */
	public Manager findByPrimaryKey(long managerId)
		throws NoSuchManagerException;

	/**
	 * Returns the manager with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param managerId the primary key of the manager
	 * @return the manager, or <code>null</code> if a manager with the primary key could not be found
	 */
	public Manager fetchByPrimaryKey(long managerId);

	/**
	 * Returns all the managers.
	 *
	 * @return the managers
	 */
	public java.util.List<Manager> findAll();

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
	public java.util.List<Manager> findAll(int start, int end);

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
	public java.util.List<Manager> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Manager>
			orderByComparator);

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
	public java.util.List<Manager> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Manager>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Removes all the managers from the database.
	 */
	public void removeAll();

	/**
	 * Returns the number of managers.
	 *
	 * @return the number of managers
	 */
	public int countAll();

}