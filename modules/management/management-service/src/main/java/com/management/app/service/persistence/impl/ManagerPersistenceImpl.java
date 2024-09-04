/**
 * SPDX-FileCopyrightText: (c) 2024 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.management.app.service.persistence.impl;

import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.configuration.Configuration;
import com.liferay.portal.kernel.dao.orm.EntityCache;
import com.liferay.portal.kernel.dao.orm.FinderCache;
import com.liferay.portal.kernel.dao.orm.FinderPath;
import com.liferay.portal.kernel.dao.orm.Query;
import com.liferay.portal.kernel.dao.orm.QueryPos;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.orm.Session;
import com.liferay.portal.kernel.dao.orm.SessionFactory;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.security.auth.CompanyThreadLocal;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.ServiceContextThreadLocal;
import com.liferay.portal.kernel.service.persistence.impl.BasePersistenceImpl;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.PropsKeys;
import com.liferay.portal.kernel.util.PropsUtil;
import com.liferay.portal.kernel.util.ProxyUtil;
import com.liferay.portal.kernel.util.SetUtil;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.uuid.PortalUUIDUtil;

import com.management.app.exception.NoSuchManagerException;
import com.management.app.model.Manager;
import com.management.app.model.ManagerTable;
import com.management.app.model.impl.ManagerImpl;
import com.management.app.model.impl.ManagerModelImpl;
import com.management.app.service.persistence.ManagerPersistence;
import com.management.app.service.persistence.ManagerUtil;
import com.management.app.service.persistence.impl.constants.ManagementPersistenceConstants;

import java.io.Serializable;

import java.lang.reflect.InvocationHandler;

import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

import javax.sql.DataSource;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Reference;

/**
 * The persistence implementation for the manager service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Albert Cabral
 * @generated
 */
@Component(service = ManagerPersistence.class)
public class ManagerPersistenceImpl
	extends BasePersistenceImpl<Manager> implements ManagerPersistence {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use <code>ManagerUtil</code> to access the manager persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY =
		ManagerImpl.class.getName();

	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List1";

	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List2";

	private FinderPath _finderPathWithPaginationFindAll;
	private FinderPath _finderPathWithoutPaginationFindAll;
	private FinderPath _finderPathCountAll;
	private FinderPath _finderPathWithPaginationFindByUuid;
	private FinderPath _finderPathWithoutPaginationFindByUuid;
	private FinderPath _finderPathCountByUuid;

	/**
	 * Returns all the managers where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the matching managers
	 */
	@Override
	public List<Manager> findByUuid(String uuid) {
		return findByUuid(uuid, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
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
	@Override
	public List<Manager> findByUuid(String uuid, int start, int end) {
		return findByUuid(uuid, start, end, null);
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
	@Override
	public List<Manager> findByUuid(
		String uuid, int start, int end,
		OrderByComparator<Manager> orderByComparator) {

		return findByUuid(uuid, start, end, orderByComparator, true);
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
	@Override
	public List<Manager> findByUuid(
		String uuid, int start, int end,
		OrderByComparator<Manager> orderByComparator, boolean useFinderCache) {

		uuid = Objects.toString(uuid, "");

		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
			(orderByComparator == null)) {

			if (useFinderCache) {
				finderPath = _finderPathWithoutPaginationFindByUuid;
				finderArgs = new Object[] {uuid};
			}
		}
		else if (useFinderCache) {
			finderPath = _finderPathWithPaginationFindByUuid;
			finderArgs = new Object[] {uuid, start, end, orderByComparator};
		}

		List<Manager> list = null;

		if (useFinderCache) {
			list = (List<Manager>)finderCache.getResult(
				finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (Manager manager : list) {
					if (!uuid.equals(manager.getUuid())) {
						list = null;

						break;
					}
				}
			}
		}

		if (list == null) {
			StringBundler sb = null;

			if (orderByComparator != null) {
				sb = new StringBundler(
					3 + (orderByComparator.getOrderByFields().length * 2));
			}
			else {
				sb = new StringBundler(3);
			}

			sb.append(_SQL_SELECT_MANAGER_WHERE);

			boolean bindUuid = false;

			if (uuid.isEmpty()) {
				sb.append(_FINDER_COLUMN_UUID_UUID_3);
			}
			else {
				bindUuid = true;

				sb.append(_FINDER_COLUMN_UUID_UUID_2);
			}

			if (orderByComparator != null) {
				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}
			else {
				sb.append(ManagerModelImpl.ORDER_BY_JPQL);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				if (bindUuid) {
					queryPos.add(uuid);
				}

				list = (List<Manager>)QueryUtil.list(
					query, getDialect(), start, end);

				cacheResult(list);

				if (useFinderCache) {
					finderCache.putResult(finderPath, finderArgs, list);
				}
			}
			catch (Exception exception) {
				throw processException(exception);
			}
			finally {
				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Returns the first manager in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching manager
	 * @throws NoSuchManagerException if a matching manager could not be found
	 */
	@Override
	public Manager findByUuid_First(
			String uuid, OrderByComparator<Manager> orderByComparator)
		throws NoSuchManagerException {

		Manager manager = fetchByUuid_First(uuid, orderByComparator);

		if (manager != null) {
			return manager;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("uuid=");
		sb.append(uuid);

		sb.append("}");

		throw new NoSuchManagerException(sb.toString());
	}

	/**
	 * Returns the first manager in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching manager, or <code>null</code> if a matching manager could not be found
	 */
	@Override
	public Manager fetchByUuid_First(
		String uuid, OrderByComparator<Manager> orderByComparator) {

		List<Manager> list = findByUuid(uuid, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last manager in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching manager
	 * @throws NoSuchManagerException if a matching manager could not be found
	 */
	@Override
	public Manager findByUuid_Last(
			String uuid, OrderByComparator<Manager> orderByComparator)
		throws NoSuchManagerException {

		Manager manager = fetchByUuid_Last(uuid, orderByComparator);

		if (manager != null) {
			return manager;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("uuid=");
		sb.append(uuid);

		sb.append("}");

		throw new NoSuchManagerException(sb.toString());
	}

	/**
	 * Returns the last manager in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching manager, or <code>null</code> if a matching manager could not be found
	 */
	@Override
	public Manager fetchByUuid_Last(
		String uuid, OrderByComparator<Manager> orderByComparator) {

		int count = countByUuid(uuid);

		if (count == 0) {
			return null;
		}

		List<Manager> list = findByUuid(
			uuid, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
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
	@Override
	public Manager[] findByUuid_PrevAndNext(
			long managerId, String uuid,
			OrderByComparator<Manager> orderByComparator)
		throws NoSuchManagerException {

		uuid = Objects.toString(uuid, "");

		Manager manager = findByPrimaryKey(managerId);

		Session session = null;

		try {
			session = openSession();

			Manager[] array = new ManagerImpl[3];

			array[0] = getByUuid_PrevAndNext(
				session, manager, uuid, orderByComparator, true);

			array[1] = manager;

			array[2] = getByUuid_PrevAndNext(
				session, manager, uuid, orderByComparator, false);

			return array;
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}
	}

	protected Manager getByUuid_PrevAndNext(
		Session session, Manager manager, String uuid,
		OrderByComparator<Manager> orderByComparator, boolean previous) {

		StringBundler sb = null;

		if (orderByComparator != null) {
			sb = new StringBundler(
				4 + (orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			sb = new StringBundler(3);
		}

		sb.append(_SQL_SELECT_MANAGER_WHERE);

		boolean bindUuid = false;

		if (uuid.isEmpty()) {
			sb.append(_FINDER_COLUMN_UUID_UUID_3);
		}
		else {
			bindUuid = true;

			sb.append(_FINDER_COLUMN_UUID_UUID_2);
		}

		if (orderByComparator != null) {
			String[] orderByConditionFields =
				orderByComparator.getOrderByConditionFields();

			if (orderByConditionFields.length > 0) {
				sb.append(WHERE_AND);
			}

			for (int i = 0; i < orderByConditionFields.length; i++) {
				sb.append(_ORDER_BY_ENTITY_ALIAS);
				sb.append(orderByConditionFields[i]);

				if ((i + 1) < orderByConditionFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						sb.append(WHERE_GREATER_THAN_HAS_NEXT);
					}
					else {
						sb.append(WHERE_LESSER_THAN_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						sb.append(WHERE_GREATER_THAN);
					}
					else {
						sb.append(WHERE_LESSER_THAN);
					}
				}
			}

			sb.append(ORDER_BY_CLAUSE);

			String[] orderByFields = orderByComparator.getOrderByFields();

			for (int i = 0; i < orderByFields.length; i++) {
				sb.append(_ORDER_BY_ENTITY_ALIAS);
				sb.append(orderByFields[i]);

				if ((i + 1) < orderByFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						sb.append(ORDER_BY_ASC_HAS_NEXT);
					}
					else {
						sb.append(ORDER_BY_DESC_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						sb.append(ORDER_BY_ASC);
					}
					else {
						sb.append(ORDER_BY_DESC);
					}
				}
			}
		}
		else {
			sb.append(ManagerModelImpl.ORDER_BY_JPQL);
		}

		String sql = sb.toString();

		Query query = session.createQuery(sql);

		query.setFirstResult(0);
		query.setMaxResults(2);

		QueryPos queryPos = QueryPos.getInstance(query);

		if (bindUuid) {
			queryPos.add(uuid);
		}

		if (orderByComparator != null) {
			for (Object orderByConditionValue :
					orderByComparator.getOrderByConditionValues(manager)) {

				queryPos.add(orderByConditionValue);
			}
		}

		List<Manager> list = query.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the managers where uuid = &#63; from the database.
	 *
	 * @param uuid the uuid
	 */
	@Override
	public void removeByUuid(String uuid) {
		for (Manager manager :
				findByUuid(uuid, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {

			remove(manager);
		}
	}

	/**
	 * Returns the number of managers where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the number of matching managers
	 */
	@Override
	public int countByUuid(String uuid) {
		uuid = Objects.toString(uuid, "");

		FinderPath finderPath = _finderPathCountByUuid;

		Object[] finderArgs = new Object[] {uuid};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler sb = new StringBundler(2);

			sb.append(_SQL_COUNT_MANAGER_WHERE);

			boolean bindUuid = false;

			if (uuid.isEmpty()) {
				sb.append(_FINDER_COLUMN_UUID_UUID_3);
			}
			else {
				bindUuid = true;

				sb.append(_FINDER_COLUMN_UUID_UUID_2);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				if (bindUuid) {
					queryPos.add(uuid);
				}

				count = (Long)query.uniqueResult();

				finderCache.putResult(finderPath, finderArgs, count);
			}
			catch (Exception exception) {
				throw processException(exception);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	private static final String _FINDER_COLUMN_UUID_UUID_2 = "manager.uuid = ?";

	private static final String _FINDER_COLUMN_UUID_UUID_3 =
		"(manager.uuid IS NULL OR manager.uuid = '')";

	private FinderPath _finderPathFetchByUUID_G;
	private FinderPath _finderPathCountByUUID_G;

	/**
	 * Returns the manager where uuid = &#63; and groupId = &#63; or throws a <code>NoSuchManagerException</code> if it could not be found.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the matching manager
	 * @throws NoSuchManagerException if a matching manager could not be found
	 */
	@Override
	public Manager findByUUID_G(String uuid, long groupId)
		throws NoSuchManagerException {

		Manager manager = fetchByUUID_G(uuid, groupId);

		if (manager == null) {
			StringBundler sb = new StringBundler(6);

			sb.append(_NO_SUCH_ENTITY_WITH_KEY);

			sb.append("uuid=");
			sb.append(uuid);

			sb.append(", groupId=");
			sb.append(groupId);

			sb.append("}");

			if (_log.isDebugEnabled()) {
				_log.debug(sb.toString());
			}

			throw new NoSuchManagerException(sb.toString());
		}

		return manager;
	}

	/**
	 * Returns the manager where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the matching manager, or <code>null</code> if a matching manager could not be found
	 */
	@Override
	public Manager fetchByUUID_G(String uuid, long groupId) {
		return fetchByUUID_G(uuid, groupId, true);
	}

	/**
	 * Returns the manager where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @param useFinderCache whether to use the finder cache
	 * @return the matching manager, or <code>null</code> if a matching manager could not be found
	 */
	@Override
	public Manager fetchByUUID_G(
		String uuid, long groupId, boolean useFinderCache) {

		uuid = Objects.toString(uuid, "");

		Object[] finderArgs = null;

		if (useFinderCache) {
			finderArgs = new Object[] {uuid, groupId};
		}

		Object result = null;

		if (useFinderCache) {
			result = finderCache.getResult(
				_finderPathFetchByUUID_G, finderArgs, this);
		}

		if (result instanceof Manager) {
			Manager manager = (Manager)result;

			if (!Objects.equals(uuid, manager.getUuid()) ||
				(groupId != manager.getGroupId())) {

				result = null;
			}
		}

		if (result == null) {
			StringBundler sb = new StringBundler(4);

			sb.append(_SQL_SELECT_MANAGER_WHERE);

			boolean bindUuid = false;

			if (uuid.isEmpty()) {
				sb.append(_FINDER_COLUMN_UUID_G_UUID_3);
			}
			else {
				bindUuid = true;

				sb.append(_FINDER_COLUMN_UUID_G_UUID_2);
			}

			sb.append(_FINDER_COLUMN_UUID_G_GROUPID_2);

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				if (bindUuid) {
					queryPos.add(uuid);
				}

				queryPos.add(groupId);

				List<Manager> list = query.list();

				if (list.isEmpty()) {
					if (useFinderCache) {
						finderCache.putResult(
							_finderPathFetchByUUID_G, finderArgs, list);
					}
				}
				else {
					Manager manager = list.get(0);

					result = manager;

					cacheResult(manager);
				}
			}
			catch (Exception exception) {
				throw processException(exception);
			}
			finally {
				closeSession(session);
			}
		}

		if (result instanceof List<?>) {
			return null;
		}
		else {
			return (Manager)result;
		}
	}

	/**
	 * Removes the manager where uuid = &#63; and groupId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the manager that was removed
	 */
	@Override
	public Manager removeByUUID_G(String uuid, long groupId)
		throws NoSuchManagerException {

		Manager manager = findByUUID_G(uuid, groupId);

		return remove(manager);
	}

	/**
	 * Returns the number of managers where uuid = &#63; and groupId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the number of matching managers
	 */
	@Override
	public int countByUUID_G(String uuid, long groupId) {
		uuid = Objects.toString(uuid, "");

		FinderPath finderPath = _finderPathCountByUUID_G;

		Object[] finderArgs = new Object[] {uuid, groupId};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler sb = new StringBundler(3);

			sb.append(_SQL_COUNT_MANAGER_WHERE);

			boolean bindUuid = false;

			if (uuid.isEmpty()) {
				sb.append(_FINDER_COLUMN_UUID_G_UUID_3);
			}
			else {
				bindUuid = true;

				sb.append(_FINDER_COLUMN_UUID_G_UUID_2);
			}

			sb.append(_FINDER_COLUMN_UUID_G_GROUPID_2);

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				if (bindUuid) {
					queryPos.add(uuid);
				}

				queryPos.add(groupId);

				count = (Long)query.uniqueResult();

				finderCache.putResult(finderPath, finderArgs, count);
			}
			catch (Exception exception) {
				throw processException(exception);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	private static final String _FINDER_COLUMN_UUID_G_UUID_2 =
		"manager.uuid = ? AND ";

	private static final String _FINDER_COLUMN_UUID_G_UUID_3 =
		"(manager.uuid IS NULL OR manager.uuid = '') AND ";

	private static final String _FINDER_COLUMN_UUID_G_GROUPID_2 =
		"manager.groupId = ?";

	private FinderPath _finderPathWithPaginationFindByUuid_C;
	private FinderPath _finderPathWithoutPaginationFindByUuid_C;
	private FinderPath _finderPathCountByUuid_C;

	/**
	 * Returns all the managers where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the matching managers
	 */
	@Override
	public List<Manager> findByUuid_C(String uuid, long companyId) {
		return findByUuid_C(
			uuid, companyId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
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
	@Override
	public List<Manager> findByUuid_C(
		String uuid, long companyId, int start, int end) {

		return findByUuid_C(uuid, companyId, start, end, null);
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
	@Override
	public List<Manager> findByUuid_C(
		String uuid, long companyId, int start, int end,
		OrderByComparator<Manager> orderByComparator) {

		return findByUuid_C(
			uuid, companyId, start, end, orderByComparator, true);
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
	@Override
	public List<Manager> findByUuid_C(
		String uuid, long companyId, int start, int end,
		OrderByComparator<Manager> orderByComparator, boolean useFinderCache) {

		uuid = Objects.toString(uuid, "");

		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
			(orderByComparator == null)) {

			if (useFinderCache) {
				finderPath = _finderPathWithoutPaginationFindByUuid_C;
				finderArgs = new Object[] {uuid, companyId};
			}
		}
		else if (useFinderCache) {
			finderPath = _finderPathWithPaginationFindByUuid_C;
			finderArgs = new Object[] {
				uuid, companyId, start, end, orderByComparator
			};
		}

		List<Manager> list = null;

		if (useFinderCache) {
			list = (List<Manager>)finderCache.getResult(
				finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (Manager manager : list) {
					if (!uuid.equals(manager.getUuid()) ||
						(companyId != manager.getCompanyId())) {

						list = null;

						break;
					}
				}
			}
		}

		if (list == null) {
			StringBundler sb = null;

			if (orderByComparator != null) {
				sb = new StringBundler(
					4 + (orderByComparator.getOrderByFields().length * 2));
			}
			else {
				sb = new StringBundler(4);
			}

			sb.append(_SQL_SELECT_MANAGER_WHERE);

			boolean bindUuid = false;

			if (uuid.isEmpty()) {
				sb.append(_FINDER_COLUMN_UUID_C_UUID_3);
			}
			else {
				bindUuid = true;

				sb.append(_FINDER_COLUMN_UUID_C_UUID_2);
			}

			sb.append(_FINDER_COLUMN_UUID_C_COMPANYID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}
			else {
				sb.append(ManagerModelImpl.ORDER_BY_JPQL);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				if (bindUuid) {
					queryPos.add(uuid);
				}

				queryPos.add(companyId);

				list = (List<Manager>)QueryUtil.list(
					query, getDialect(), start, end);

				cacheResult(list);

				if (useFinderCache) {
					finderCache.putResult(finderPath, finderArgs, list);
				}
			}
			catch (Exception exception) {
				throw processException(exception);
			}
			finally {
				closeSession(session);
			}
		}

		return list;
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
	@Override
	public Manager findByUuid_C_First(
			String uuid, long companyId,
			OrderByComparator<Manager> orderByComparator)
		throws NoSuchManagerException {

		Manager manager = fetchByUuid_C_First(
			uuid, companyId, orderByComparator);

		if (manager != null) {
			return manager;
		}

		StringBundler sb = new StringBundler(6);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("uuid=");
		sb.append(uuid);

		sb.append(", companyId=");
		sb.append(companyId);

		sb.append("}");

		throw new NoSuchManagerException(sb.toString());
	}

	/**
	 * Returns the first manager in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching manager, or <code>null</code> if a matching manager could not be found
	 */
	@Override
	public Manager fetchByUuid_C_First(
		String uuid, long companyId,
		OrderByComparator<Manager> orderByComparator) {

		List<Manager> list = findByUuid_C(
			uuid, companyId, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
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
	@Override
	public Manager findByUuid_C_Last(
			String uuid, long companyId,
			OrderByComparator<Manager> orderByComparator)
		throws NoSuchManagerException {

		Manager manager = fetchByUuid_C_Last(
			uuid, companyId, orderByComparator);

		if (manager != null) {
			return manager;
		}

		StringBundler sb = new StringBundler(6);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("uuid=");
		sb.append(uuid);

		sb.append(", companyId=");
		sb.append(companyId);

		sb.append("}");

		throw new NoSuchManagerException(sb.toString());
	}

	/**
	 * Returns the last manager in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching manager, or <code>null</code> if a matching manager could not be found
	 */
	@Override
	public Manager fetchByUuid_C_Last(
		String uuid, long companyId,
		OrderByComparator<Manager> orderByComparator) {

		int count = countByUuid_C(uuid, companyId);

		if (count == 0) {
			return null;
		}

		List<Manager> list = findByUuid_C(
			uuid, companyId, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
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
	@Override
	public Manager[] findByUuid_C_PrevAndNext(
			long managerId, String uuid, long companyId,
			OrderByComparator<Manager> orderByComparator)
		throws NoSuchManagerException {

		uuid = Objects.toString(uuid, "");

		Manager manager = findByPrimaryKey(managerId);

		Session session = null;

		try {
			session = openSession();

			Manager[] array = new ManagerImpl[3];

			array[0] = getByUuid_C_PrevAndNext(
				session, manager, uuid, companyId, orderByComparator, true);

			array[1] = manager;

			array[2] = getByUuid_C_PrevAndNext(
				session, manager, uuid, companyId, orderByComparator, false);

			return array;
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}
	}

	protected Manager getByUuid_C_PrevAndNext(
		Session session, Manager manager, String uuid, long companyId,
		OrderByComparator<Manager> orderByComparator, boolean previous) {

		StringBundler sb = null;

		if (orderByComparator != null) {
			sb = new StringBundler(
				5 + (orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			sb = new StringBundler(4);
		}

		sb.append(_SQL_SELECT_MANAGER_WHERE);

		boolean bindUuid = false;

		if (uuid.isEmpty()) {
			sb.append(_FINDER_COLUMN_UUID_C_UUID_3);
		}
		else {
			bindUuid = true;

			sb.append(_FINDER_COLUMN_UUID_C_UUID_2);
		}

		sb.append(_FINDER_COLUMN_UUID_C_COMPANYID_2);

		if (orderByComparator != null) {
			String[] orderByConditionFields =
				orderByComparator.getOrderByConditionFields();

			if (orderByConditionFields.length > 0) {
				sb.append(WHERE_AND);
			}

			for (int i = 0; i < orderByConditionFields.length; i++) {
				sb.append(_ORDER_BY_ENTITY_ALIAS);
				sb.append(orderByConditionFields[i]);

				if ((i + 1) < orderByConditionFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						sb.append(WHERE_GREATER_THAN_HAS_NEXT);
					}
					else {
						sb.append(WHERE_LESSER_THAN_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						sb.append(WHERE_GREATER_THAN);
					}
					else {
						sb.append(WHERE_LESSER_THAN);
					}
				}
			}

			sb.append(ORDER_BY_CLAUSE);

			String[] orderByFields = orderByComparator.getOrderByFields();

			for (int i = 0; i < orderByFields.length; i++) {
				sb.append(_ORDER_BY_ENTITY_ALIAS);
				sb.append(orderByFields[i]);

				if ((i + 1) < orderByFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						sb.append(ORDER_BY_ASC_HAS_NEXT);
					}
					else {
						sb.append(ORDER_BY_DESC_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						sb.append(ORDER_BY_ASC);
					}
					else {
						sb.append(ORDER_BY_DESC);
					}
				}
			}
		}
		else {
			sb.append(ManagerModelImpl.ORDER_BY_JPQL);
		}

		String sql = sb.toString();

		Query query = session.createQuery(sql);

		query.setFirstResult(0);
		query.setMaxResults(2);

		QueryPos queryPos = QueryPos.getInstance(query);

		if (bindUuid) {
			queryPos.add(uuid);
		}

		queryPos.add(companyId);

		if (orderByComparator != null) {
			for (Object orderByConditionValue :
					orderByComparator.getOrderByConditionValues(manager)) {

				queryPos.add(orderByConditionValue);
			}
		}

		List<Manager> list = query.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the managers where uuid = &#63; and companyId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 */
	@Override
	public void removeByUuid_C(String uuid, long companyId) {
		for (Manager manager :
				findByUuid_C(
					uuid, companyId, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
					null)) {

			remove(manager);
		}
	}

	/**
	 * Returns the number of managers where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the number of matching managers
	 */
	@Override
	public int countByUuid_C(String uuid, long companyId) {
		uuid = Objects.toString(uuid, "");

		FinderPath finderPath = _finderPathCountByUuid_C;

		Object[] finderArgs = new Object[] {uuid, companyId};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler sb = new StringBundler(3);

			sb.append(_SQL_COUNT_MANAGER_WHERE);

			boolean bindUuid = false;

			if (uuid.isEmpty()) {
				sb.append(_FINDER_COLUMN_UUID_C_UUID_3);
			}
			else {
				bindUuid = true;

				sb.append(_FINDER_COLUMN_UUID_C_UUID_2);
			}

			sb.append(_FINDER_COLUMN_UUID_C_COMPANYID_2);

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				if (bindUuid) {
					queryPos.add(uuid);
				}

				queryPos.add(companyId);

				count = (Long)query.uniqueResult();

				finderCache.putResult(finderPath, finderArgs, count);
			}
			catch (Exception exception) {
				throw processException(exception);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	private static final String _FINDER_COLUMN_UUID_C_UUID_2 =
		"manager.uuid = ? AND ";

	private static final String _FINDER_COLUMN_UUID_C_UUID_3 =
		"(manager.uuid IS NULL OR manager.uuid = '') AND ";

	private static final String _FINDER_COLUMN_UUID_C_COMPANYID_2 =
		"manager.companyId = ?";

	private FinderPath _finderPathWithPaginationFindByCompanyId;
	private FinderPath _finderPathWithoutPaginationFindByCompanyId;
	private FinderPath _finderPathCountByCompanyId;

	/**
	 * Returns all the managers where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @return the matching managers
	 */
	@Override
	public List<Manager> findByCompanyId(long companyId) {
		return findByCompanyId(
			companyId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
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
	@Override
	public List<Manager> findByCompanyId(long companyId, int start, int end) {
		return findByCompanyId(companyId, start, end, null);
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
	@Override
	public List<Manager> findByCompanyId(
		long companyId, int start, int end,
		OrderByComparator<Manager> orderByComparator) {

		return findByCompanyId(companyId, start, end, orderByComparator, true);
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
	@Override
	public List<Manager> findByCompanyId(
		long companyId, int start, int end,
		OrderByComparator<Manager> orderByComparator, boolean useFinderCache) {

		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
			(orderByComparator == null)) {

			if (useFinderCache) {
				finderPath = _finderPathWithoutPaginationFindByCompanyId;
				finderArgs = new Object[] {companyId};
			}
		}
		else if (useFinderCache) {
			finderPath = _finderPathWithPaginationFindByCompanyId;
			finderArgs = new Object[] {
				companyId, start, end, orderByComparator
			};
		}

		List<Manager> list = null;

		if (useFinderCache) {
			list = (List<Manager>)finderCache.getResult(
				finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (Manager manager : list) {
					if (companyId != manager.getCompanyId()) {
						list = null;

						break;
					}
				}
			}
		}

		if (list == null) {
			StringBundler sb = null;

			if (orderByComparator != null) {
				sb = new StringBundler(
					3 + (orderByComparator.getOrderByFields().length * 2));
			}
			else {
				sb = new StringBundler(3);
			}

			sb.append(_SQL_SELECT_MANAGER_WHERE);

			sb.append(_FINDER_COLUMN_COMPANYID_COMPANYID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}
			else {
				sb.append(ManagerModelImpl.ORDER_BY_JPQL);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(companyId);

				list = (List<Manager>)QueryUtil.list(
					query, getDialect(), start, end);

				cacheResult(list);

				if (useFinderCache) {
					finderCache.putResult(finderPath, finderArgs, list);
				}
			}
			catch (Exception exception) {
				throw processException(exception);
			}
			finally {
				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Returns the first manager in the ordered set where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching manager
	 * @throws NoSuchManagerException if a matching manager could not be found
	 */
	@Override
	public Manager findByCompanyId_First(
			long companyId, OrderByComparator<Manager> orderByComparator)
		throws NoSuchManagerException {

		Manager manager = fetchByCompanyId_First(companyId, orderByComparator);

		if (manager != null) {
			return manager;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("companyId=");
		sb.append(companyId);

		sb.append("}");

		throw new NoSuchManagerException(sb.toString());
	}

	/**
	 * Returns the first manager in the ordered set where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching manager, or <code>null</code> if a matching manager could not be found
	 */
	@Override
	public Manager fetchByCompanyId_First(
		long companyId, OrderByComparator<Manager> orderByComparator) {

		List<Manager> list = findByCompanyId(
			companyId, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last manager in the ordered set where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching manager
	 * @throws NoSuchManagerException if a matching manager could not be found
	 */
	@Override
	public Manager findByCompanyId_Last(
			long companyId, OrderByComparator<Manager> orderByComparator)
		throws NoSuchManagerException {

		Manager manager = fetchByCompanyId_Last(companyId, orderByComparator);

		if (manager != null) {
			return manager;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("companyId=");
		sb.append(companyId);

		sb.append("}");

		throw new NoSuchManagerException(sb.toString());
	}

	/**
	 * Returns the last manager in the ordered set where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching manager, or <code>null</code> if a matching manager could not be found
	 */
	@Override
	public Manager fetchByCompanyId_Last(
		long companyId, OrderByComparator<Manager> orderByComparator) {

		int count = countByCompanyId(companyId);

		if (count == 0) {
			return null;
		}

		List<Manager> list = findByCompanyId(
			companyId, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
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
	@Override
	public Manager[] findByCompanyId_PrevAndNext(
			long managerId, long companyId,
			OrderByComparator<Manager> orderByComparator)
		throws NoSuchManagerException {

		Manager manager = findByPrimaryKey(managerId);

		Session session = null;

		try {
			session = openSession();

			Manager[] array = new ManagerImpl[3];

			array[0] = getByCompanyId_PrevAndNext(
				session, manager, companyId, orderByComparator, true);

			array[1] = manager;

			array[2] = getByCompanyId_PrevAndNext(
				session, manager, companyId, orderByComparator, false);

			return array;
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}
	}

	protected Manager getByCompanyId_PrevAndNext(
		Session session, Manager manager, long companyId,
		OrderByComparator<Manager> orderByComparator, boolean previous) {

		StringBundler sb = null;

		if (orderByComparator != null) {
			sb = new StringBundler(
				4 + (orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			sb = new StringBundler(3);
		}

		sb.append(_SQL_SELECT_MANAGER_WHERE);

		sb.append(_FINDER_COLUMN_COMPANYID_COMPANYID_2);

		if (orderByComparator != null) {
			String[] orderByConditionFields =
				orderByComparator.getOrderByConditionFields();

			if (orderByConditionFields.length > 0) {
				sb.append(WHERE_AND);
			}

			for (int i = 0; i < orderByConditionFields.length; i++) {
				sb.append(_ORDER_BY_ENTITY_ALIAS);
				sb.append(orderByConditionFields[i]);

				if ((i + 1) < orderByConditionFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						sb.append(WHERE_GREATER_THAN_HAS_NEXT);
					}
					else {
						sb.append(WHERE_LESSER_THAN_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						sb.append(WHERE_GREATER_THAN);
					}
					else {
						sb.append(WHERE_LESSER_THAN);
					}
				}
			}

			sb.append(ORDER_BY_CLAUSE);

			String[] orderByFields = orderByComparator.getOrderByFields();

			for (int i = 0; i < orderByFields.length; i++) {
				sb.append(_ORDER_BY_ENTITY_ALIAS);
				sb.append(orderByFields[i]);

				if ((i + 1) < orderByFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						sb.append(ORDER_BY_ASC_HAS_NEXT);
					}
					else {
						sb.append(ORDER_BY_DESC_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						sb.append(ORDER_BY_ASC);
					}
					else {
						sb.append(ORDER_BY_DESC);
					}
				}
			}
		}
		else {
			sb.append(ManagerModelImpl.ORDER_BY_JPQL);
		}

		String sql = sb.toString();

		Query query = session.createQuery(sql);

		query.setFirstResult(0);
		query.setMaxResults(2);

		QueryPos queryPos = QueryPos.getInstance(query);

		queryPos.add(companyId);

		if (orderByComparator != null) {
			for (Object orderByConditionValue :
					orderByComparator.getOrderByConditionValues(manager)) {

				queryPos.add(orderByConditionValue);
			}
		}

		List<Manager> list = query.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the managers where companyId = &#63; from the database.
	 *
	 * @param companyId the company ID
	 */
	@Override
	public void removeByCompanyId(long companyId) {
		for (Manager manager :
				findByCompanyId(
					companyId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {

			remove(manager);
		}
	}

	/**
	 * Returns the number of managers where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @return the number of matching managers
	 */
	@Override
	public int countByCompanyId(long companyId) {
		FinderPath finderPath = _finderPathCountByCompanyId;

		Object[] finderArgs = new Object[] {companyId};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler sb = new StringBundler(2);

			sb.append(_SQL_COUNT_MANAGER_WHERE);

			sb.append(_FINDER_COLUMN_COMPANYID_COMPANYID_2);

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(companyId);

				count = (Long)query.uniqueResult();

				finderCache.putResult(finderPath, finderArgs, count);
			}
			catch (Exception exception) {
				throw processException(exception);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	private static final String _FINDER_COLUMN_COMPANYID_COMPANYID_2 =
		"manager.companyId = ?";

	private FinderPath _finderPathWithPaginationFindByManagerId;
	private FinderPath _finderPathWithoutPaginationFindByManagerId;
	private FinderPath _finderPathCountByManagerId;

	/**
	 * Returns all the managers where managerId = &#63;.
	 *
	 * @param managerId the manager ID
	 * @return the matching managers
	 */
	@Override
	public List<Manager> findByManagerId(long managerId) {
		return findByManagerId(
			managerId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
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
	@Override
	public List<Manager> findByManagerId(long managerId, int start, int end) {
		return findByManagerId(managerId, start, end, null);
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
	@Override
	public List<Manager> findByManagerId(
		long managerId, int start, int end,
		OrderByComparator<Manager> orderByComparator) {

		return findByManagerId(managerId, start, end, orderByComparator, true);
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
	@Override
	public List<Manager> findByManagerId(
		long managerId, int start, int end,
		OrderByComparator<Manager> orderByComparator, boolean useFinderCache) {

		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
			(orderByComparator == null)) {

			if (useFinderCache) {
				finderPath = _finderPathWithoutPaginationFindByManagerId;
				finderArgs = new Object[] {managerId};
			}
		}
		else if (useFinderCache) {
			finderPath = _finderPathWithPaginationFindByManagerId;
			finderArgs = new Object[] {
				managerId, start, end, orderByComparator
			};
		}

		List<Manager> list = null;

		if (useFinderCache) {
			list = (List<Manager>)finderCache.getResult(
				finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (Manager manager : list) {
					if (managerId != manager.getManagerId()) {
						list = null;

						break;
					}
				}
			}
		}

		if (list == null) {
			StringBundler sb = null;

			if (orderByComparator != null) {
				sb = new StringBundler(
					3 + (orderByComparator.getOrderByFields().length * 2));
			}
			else {
				sb = new StringBundler(3);
			}

			sb.append(_SQL_SELECT_MANAGER_WHERE);

			sb.append(_FINDER_COLUMN_MANAGERID_MANAGERID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}
			else {
				sb.append(ManagerModelImpl.ORDER_BY_JPQL);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(managerId);

				list = (List<Manager>)QueryUtil.list(
					query, getDialect(), start, end);

				cacheResult(list);

				if (useFinderCache) {
					finderCache.putResult(finderPath, finderArgs, list);
				}
			}
			catch (Exception exception) {
				throw processException(exception);
			}
			finally {
				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Returns the first manager in the ordered set where managerId = &#63;.
	 *
	 * @param managerId the manager ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching manager
	 * @throws NoSuchManagerException if a matching manager could not be found
	 */
	@Override
	public Manager findByManagerId_First(
			long managerId, OrderByComparator<Manager> orderByComparator)
		throws NoSuchManagerException {

		Manager manager = fetchByManagerId_First(managerId, orderByComparator);

		if (manager != null) {
			return manager;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("managerId=");
		sb.append(managerId);

		sb.append("}");

		throw new NoSuchManagerException(sb.toString());
	}

	/**
	 * Returns the first manager in the ordered set where managerId = &#63;.
	 *
	 * @param managerId the manager ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching manager, or <code>null</code> if a matching manager could not be found
	 */
	@Override
	public Manager fetchByManagerId_First(
		long managerId, OrderByComparator<Manager> orderByComparator) {

		List<Manager> list = findByManagerId(
			managerId, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last manager in the ordered set where managerId = &#63;.
	 *
	 * @param managerId the manager ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching manager
	 * @throws NoSuchManagerException if a matching manager could not be found
	 */
	@Override
	public Manager findByManagerId_Last(
			long managerId, OrderByComparator<Manager> orderByComparator)
		throws NoSuchManagerException {

		Manager manager = fetchByManagerId_Last(managerId, orderByComparator);

		if (manager != null) {
			return manager;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("managerId=");
		sb.append(managerId);

		sb.append("}");

		throw new NoSuchManagerException(sb.toString());
	}

	/**
	 * Returns the last manager in the ordered set where managerId = &#63;.
	 *
	 * @param managerId the manager ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching manager, or <code>null</code> if a matching manager could not be found
	 */
	@Override
	public Manager fetchByManagerId_Last(
		long managerId, OrderByComparator<Manager> orderByComparator) {

		int count = countByManagerId(managerId);

		if (count == 0) {
			return null;
		}

		List<Manager> list = findByManagerId(
			managerId, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Removes all the managers where managerId = &#63; from the database.
	 *
	 * @param managerId the manager ID
	 */
	@Override
	public void removeByManagerId(long managerId) {
		for (Manager manager :
				findByManagerId(
					managerId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {

			remove(manager);
		}
	}

	/**
	 * Returns the number of managers where managerId = &#63;.
	 *
	 * @param managerId the manager ID
	 * @return the number of matching managers
	 */
	@Override
	public int countByManagerId(long managerId) {
		FinderPath finderPath = _finderPathCountByManagerId;

		Object[] finderArgs = new Object[] {managerId};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler sb = new StringBundler(2);

			sb.append(_SQL_COUNT_MANAGER_WHERE);

			sb.append(_FINDER_COLUMN_MANAGERID_MANAGERID_2);

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(managerId);

				count = (Long)query.uniqueResult();

				finderCache.putResult(finderPath, finderArgs, count);
			}
			catch (Exception exception) {
				throw processException(exception);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	private static final String _FINDER_COLUMN_MANAGERID_MANAGERID_2 =
		"manager.managerId = ?";

	private FinderPath _finderPathFetchByE_M;
	private FinderPath _finderPathCountByE_M;

	/**
	 * Returns the manager where employeeIdPK = &#63; and managerId = &#63; or throws a <code>NoSuchManagerException</code> if it could not be found.
	 *
	 * @param employeeIdPK the employee ID pk
	 * @param managerId the manager ID
	 * @return the matching manager
	 * @throws NoSuchManagerException if a matching manager could not be found
	 */
	@Override
	public Manager findByE_M(long employeeIdPK, long managerId)
		throws NoSuchManagerException {

		Manager manager = fetchByE_M(employeeIdPK, managerId);

		if (manager == null) {
			StringBundler sb = new StringBundler(6);

			sb.append(_NO_SUCH_ENTITY_WITH_KEY);

			sb.append("employeeIdPK=");
			sb.append(employeeIdPK);

			sb.append(", managerId=");
			sb.append(managerId);

			sb.append("}");

			if (_log.isDebugEnabled()) {
				_log.debug(sb.toString());
			}

			throw new NoSuchManagerException(sb.toString());
		}

		return manager;
	}

	/**
	 * Returns the manager where employeeIdPK = &#63; and managerId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param employeeIdPK the employee ID pk
	 * @param managerId the manager ID
	 * @return the matching manager, or <code>null</code> if a matching manager could not be found
	 */
	@Override
	public Manager fetchByE_M(long employeeIdPK, long managerId) {
		return fetchByE_M(employeeIdPK, managerId, true);
	}

	/**
	 * Returns the manager where employeeIdPK = &#63; and managerId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param employeeIdPK the employee ID pk
	 * @param managerId the manager ID
	 * @param useFinderCache whether to use the finder cache
	 * @return the matching manager, or <code>null</code> if a matching manager could not be found
	 */
	@Override
	public Manager fetchByE_M(
		long employeeIdPK, long managerId, boolean useFinderCache) {

		Object[] finderArgs = null;

		if (useFinderCache) {
			finderArgs = new Object[] {employeeIdPK, managerId};
		}

		Object result = null;

		if (useFinderCache) {
			result = finderCache.getResult(
				_finderPathFetchByE_M, finderArgs, this);
		}

		if (result instanceof Manager) {
			Manager manager = (Manager)result;

			if ((employeeIdPK != manager.getEmployeeIdPK()) ||
				(managerId != manager.getManagerId())) {

				result = null;
			}
		}

		if (result == null) {
			StringBundler sb = new StringBundler(4);

			sb.append(_SQL_SELECT_MANAGER_WHERE);

			sb.append(_FINDER_COLUMN_E_M_EMPLOYEEIDPK_2);

			sb.append(_FINDER_COLUMN_E_M_MANAGERID_2);

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(employeeIdPK);

				queryPos.add(managerId);

				List<Manager> list = query.list();

				if (list.isEmpty()) {
					if (useFinderCache) {
						finderCache.putResult(
							_finderPathFetchByE_M, finderArgs, list);
					}
				}
				else {
					if (list.size() > 1) {
						Collections.sort(list, Collections.reverseOrder());

						if (_log.isWarnEnabled()) {
							if (!useFinderCache) {
								finderArgs = new Object[] {
									employeeIdPK, managerId
								};
							}

							_log.warn(
								"ManagerPersistenceImpl.fetchByE_M(long, long, boolean) with parameters (" +
									StringUtil.merge(finderArgs) +
										") yields a result set with more than 1 result. This violates the logical unique restriction. There is no order guarantee on which result is returned by this finder.");
						}
					}

					Manager manager = list.get(0);

					result = manager;

					cacheResult(manager);
				}
			}
			catch (Exception exception) {
				throw processException(exception);
			}
			finally {
				closeSession(session);
			}
		}

		if (result instanceof List<?>) {
			return null;
		}
		else {
			return (Manager)result;
		}
	}

	/**
	 * Removes the manager where employeeIdPK = &#63; and managerId = &#63; from the database.
	 *
	 * @param employeeIdPK the employee ID pk
	 * @param managerId the manager ID
	 * @return the manager that was removed
	 */
	@Override
	public Manager removeByE_M(long employeeIdPK, long managerId)
		throws NoSuchManagerException {

		Manager manager = findByE_M(employeeIdPK, managerId);

		return remove(manager);
	}

	/**
	 * Returns the number of managers where employeeIdPK = &#63; and managerId = &#63;.
	 *
	 * @param employeeIdPK the employee ID pk
	 * @param managerId the manager ID
	 * @return the number of matching managers
	 */
	@Override
	public int countByE_M(long employeeIdPK, long managerId) {
		FinderPath finderPath = _finderPathCountByE_M;

		Object[] finderArgs = new Object[] {employeeIdPK, managerId};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler sb = new StringBundler(3);

			sb.append(_SQL_COUNT_MANAGER_WHERE);

			sb.append(_FINDER_COLUMN_E_M_EMPLOYEEIDPK_2);

			sb.append(_FINDER_COLUMN_E_M_MANAGERID_2);

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(employeeIdPK);

				queryPos.add(managerId);

				count = (Long)query.uniqueResult();

				finderCache.putResult(finderPath, finderArgs, count);
			}
			catch (Exception exception) {
				throw processException(exception);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	private static final String _FINDER_COLUMN_E_M_EMPLOYEEIDPK_2 =
		"manager.employeeIdPK = ? AND ";

	private static final String _FINDER_COLUMN_E_M_MANAGERID_2 =
		"manager.managerId = ?";

	public ManagerPersistenceImpl() {
		Map<String, String> dbColumnNames = new HashMap<String, String>();

		dbColumnNames.put("uuid", "uuid_");

		setDBColumnNames(dbColumnNames);

		setModelClass(Manager.class);

		setModelImplClass(ManagerImpl.class);
		setModelPKClass(long.class);

		setTable(ManagerTable.INSTANCE);
	}

	/**
	 * Caches the manager in the entity cache if it is enabled.
	 *
	 * @param manager the manager
	 */
	@Override
	public void cacheResult(Manager manager) {
		entityCache.putResult(
			ManagerImpl.class, manager.getPrimaryKey(), manager);

		finderCache.putResult(
			_finderPathFetchByUUID_G,
			new Object[] {manager.getUuid(), manager.getGroupId()}, manager);

		finderCache.putResult(
			_finderPathFetchByE_M,
			new Object[] {manager.getEmployeeIdPK(), manager.getManagerId()},
			manager);
	}

	private int _valueObjectFinderCacheListThreshold;

	/**
	 * Caches the managers in the entity cache if it is enabled.
	 *
	 * @param managers the managers
	 */
	@Override
	public void cacheResult(List<Manager> managers) {
		if ((_valueObjectFinderCacheListThreshold == 0) ||
			((_valueObjectFinderCacheListThreshold > 0) &&
			 (managers.size() > _valueObjectFinderCacheListThreshold))) {

			return;
		}

		for (Manager manager : managers) {
			if (entityCache.getResult(
					ManagerImpl.class, manager.getPrimaryKey()) == null) {

				cacheResult(manager);
			}
		}
	}

	/**
	 * Clears the cache for all managers.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(ManagerImpl.class);

		finderCache.clearCache(ManagerImpl.class);
	}

	/**
	 * Clears the cache for the manager.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(Manager manager) {
		entityCache.removeResult(ManagerImpl.class, manager);
	}

	@Override
	public void clearCache(List<Manager> managers) {
		for (Manager manager : managers) {
			entityCache.removeResult(ManagerImpl.class, manager);
		}
	}

	@Override
	public void clearCache(Set<Serializable> primaryKeys) {
		finderCache.clearCache(ManagerImpl.class);

		for (Serializable primaryKey : primaryKeys) {
			entityCache.removeResult(ManagerImpl.class, primaryKey);
		}
	}

	protected void cacheUniqueFindersCache(ManagerModelImpl managerModelImpl) {
		Object[] args = new Object[] {
			managerModelImpl.getUuid(), managerModelImpl.getGroupId()
		};

		finderCache.putResult(_finderPathCountByUUID_G, args, Long.valueOf(1));
		finderCache.putResult(_finderPathFetchByUUID_G, args, managerModelImpl);

		args = new Object[] {
			managerModelImpl.getEmployeeIdPK(), managerModelImpl.getManagerId()
		};

		finderCache.putResult(_finderPathCountByE_M, args, Long.valueOf(1));
		finderCache.putResult(_finderPathFetchByE_M, args, managerModelImpl);
	}

	/**
	 * Creates a new manager with the primary key. Does not add the manager to the database.
	 *
	 * @param managerId the primary key for the new manager
	 * @return the new manager
	 */
	@Override
	public Manager create(long managerId) {
		Manager manager = new ManagerImpl();

		manager.setNew(true);
		manager.setPrimaryKey(managerId);

		String uuid = PortalUUIDUtil.generate();

		manager.setUuid(uuid);

		manager.setCompanyId(CompanyThreadLocal.getCompanyId());

		return manager;
	}

	/**
	 * Removes the manager with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param managerId the primary key of the manager
	 * @return the manager that was removed
	 * @throws NoSuchManagerException if a manager with the primary key could not be found
	 */
	@Override
	public Manager remove(long managerId) throws NoSuchManagerException {
		return remove((Serializable)managerId);
	}

	/**
	 * Removes the manager with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the manager
	 * @return the manager that was removed
	 * @throws NoSuchManagerException if a manager with the primary key could not be found
	 */
	@Override
	public Manager remove(Serializable primaryKey)
		throws NoSuchManagerException {

		Session session = null;

		try {
			session = openSession();

			Manager manager = (Manager)session.get(
				ManagerImpl.class, primaryKey);

			if (manager == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchManagerException(
					_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			return remove(manager);
		}
		catch (NoSuchManagerException noSuchEntityException) {
			throw noSuchEntityException;
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}
	}

	@Override
	protected Manager removeImpl(Manager manager) {
		Session session = null;

		try {
			session = openSession();

			if (!session.contains(manager)) {
				manager = (Manager)session.get(
					ManagerImpl.class, manager.getPrimaryKeyObj());
			}

			if (manager != null) {
				session.delete(manager);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		if (manager != null) {
			clearCache(manager);
		}

		return manager;
	}

	@Override
	public Manager updateImpl(Manager manager) {
		boolean isNew = manager.isNew();

		if (!(manager instanceof ManagerModelImpl)) {
			InvocationHandler invocationHandler = null;

			if (ProxyUtil.isProxyClass(manager.getClass())) {
				invocationHandler = ProxyUtil.getInvocationHandler(manager);

				throw new IllegalArgumentException(
					"Implement ModelWrapper in manager proxy " +
						invocationHandler.getClass());
			}

			throw new IllegalArgumentException(
				"Implement ModelWrapper in custom Manager implementation " +
					manager.getClass());
		}

		ManagerModelImpl managerModelImpl = (ManagerModelImpl)manager;

		if (Validator.isNull(manager.getUuid())) {
			String uuid = PortalUUIDUtil.generate();

			manager.setUuid(uuid);
		}

		ServiceContext serviceContext =
			ServiceContextThreadLocal.getServiceContext();

		Date date = new Date();

		if (isNew && (manager.getCreateDate() == null)) {
			if (serviceContext == null) {
				manager.setCreateDate(date);
			}
			else {
				manager.setCreateDate(serviceContext.getCreateDate(date));
			}
		}

		if (!managerModelImpl.hasSetModifiedDate()) {
			if (serviceContext == null) {
				manager.setModifiedDate(date);
			}
			else {
				manager.setModifiedDate(serviceContext.getModifiedDate(date));
			}
		}

		Session session = null;

		try {
			session = openSession();

			if (isNew) {
				session.save(manager);
			}
			else {
				manager = (Manager)session.merge(manager);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		entityCache.putResult(ManagerImpl.class, managerModelImpl, false, true);

		cacheUniqueFindersCache(managerModelImpl);

		if (isNew) {
			manager.setNew(false);
		}

		manager.resetOriginalValues();

		return manager;
	}

	/**
	 * Returns the manager with the primary key or throws a <code>com.liferay.portal.kernel.exception.NoSuchModelException</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the manager
	 * @return the manager
	 * @throws NoSuchManagerException if a manager with the primary key could not be found
	 */
	@Override
	public Manager findByPrimaryKey(Serializable primaryKey)
		throws NoSuchManagerException {

		Manager manager = fetchByPrimaryKey(primaryKey);

		if (manager == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchManagerException(
				_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
		}

		return manager;
	}

	/**
	 * Returns the manager with the primary key or throws a <code>NoSuchManagerException</code> if it could not be found.
	 *
	 * @param managerId the primary key of the manager
	 * @return the manager
	 * @throws NoSuchManagerException if a manager with the primary key could not be found
	 */
	@Override
	public Manager findByPrimaryKey(long managerId)
		throws NoSuchManagerException {

		return findByPrimaryKey((Serializable)managerId);
	}

	/**
	 * Returns the manager with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param managerId the primary key of the manager
	 * @return the manager, or <code>null</code> if a manager with the primary key could not be found
	 */
	@Override
	public Manager fetchByPrimaryKey(long managerId) {
		return fetchByPrimaryKey((Serializable)managerId);
	}

	/**
	 * Returns all the managers.
	 *
	 * @return the managers
	 */
	@Override
	public List<Manager> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
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
	@Override
	public List<Manager> findAll(int start, int end) {
		return findAll(start, end, null);
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
	@Override
	public List<Manager> findAll(
		int start, int end, OrderByComparator<Manager> orderByComparator) {

		return findAll(start, end, orderByComparator, true);
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
	@Override
	public List<Manager> findAll(
		int start, int end, OrderByComparator<Manager> orderByComparator,
		boolean useFinderCache) {

		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
			(orderByComparator == null)) {

			if (useFinderCache) {
				finderPath = _finderPathWithoutPaginationFindAll;
				finderArgs = FINDER_ARGS_EMPTY;
			}
		}
		else if (useFinderCache) {
			finderPath = _finderPathWithPaginationFindAll;
			finderArgs = new Object[] {start, end, orderByComparator};
		}

		List<Manager> list = null;

		if (useFinderCache) {
			list = (List<Manager>)finderCache.getResult(
				finderPath, finderArgs, this);
		}

		if (list == null) {
			StringBundler sb = null;
			String sql = null;

			if (orderByComparator != null) {
				sb = new StringBundler(
					2 + (orderByComparator.getOrderByFields().length * 2));

				sb.append(_SQL_SELECT_MANAGER);

				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);

				sql = sb.toString();
			}
			else {
				sql = _SQL_SELECT_MANAGER;

				sql = sql.concat(ManagerModelImpl.ORDER_BY_JPQL);
			}

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				list = (List<Manager>)QueryUtil.list(
					query, getDialect(), start, end);

				cacheResult(list);

				if (useFinderCache) {
					finderCache.putResult(finderPath, finderArgs, list);
				}
			}
			catch (Exception exception) {
				throw processException(exception);
			}
			finally {
				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Removes all the managers from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (Manager manager : findAll()) {
			remove(manager);
		}
	}

	/**
	 * Returns the number of managers.
	 *
	 * @return the number of managers
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(
			_finderPathCountAll, FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(_SQL_COUNT_MANAGER);

				count = (Long)query.uniqueResult();

				finderCache.putResult(
					_finderPathCountAll, FINDER_ARGS_EMPTY, count);
			}
			catch (Exception exception) {
				throw processException(exception);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	@Override
	public Set<String> getBadColumnNames() {
		return _badColumnNames;
	}

	@Override
	protected EntityCache getEntityCache() {
		return entityCache;
	}

	@Override
	protected String getPKDBName() {
		return "managerId";
	}

	@Override
	protected String getSelectSQL() {
		return _SQL_SELECT_MANAGER;
	}

	@Override
	protected Map<String, Integer> getTableColumnsMap() {
		return ManagerModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the manager persistence.
	 */
	@Activate
	public void activate() {
		_valueObjectFinderCacheListThreshold = GetterUtil.getInteger(
			PropsUtil.get(PropsKeys.VALUE_OBJECT_FINDER_CACHE_LIST_THRESHOLD));

		_finderPathWithPaginationFindAll = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0],
			new String[0], true);

		_finderPathWithoutPaginationFindAll = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0],
			new String[0], true);

		_finderPathCountAll = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll",
			new String[0], new String[0], false);

		_finderPathWithPaginationFindByUuid = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByUuid",
			new String[] {
				String.class.getName(), Integer.class.getName(),
				Integer.class.getName(), OrderByComparator.class.getName()
			},
			new String[] {"uuid_"}, true);

		_finderPathWithoutPaginationFindByUuid = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByUuid",
			new String[] {String.class.getName()}, new String[] {"uuid_"},
			true);

		_finderPathCountByUuid = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUuid",
			new String[] {String.class.getName()}, new String[] {"uuid_"},
			false);

		_finderPathFetchByUUID_G = new FinderPath(
			FINDER_CLASS_NAME_ENTITY, "fetchByUUID_G",
			new String[] {String.class.getName(), Long.class.getName()},
			new String[] {"uuid_", "groupId"}, true);

		_finderPathCountByUUID_G = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUUID_G",
			new String[] {String.class.getName(), Long.class.getName()},
			new String[] {"uuid_", "groupId"}, false);

		_finderPathWithPaginationFindByUuid_C = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByUuid_C",
			new String[] {
				String.class.getName(), Long.class.getName(),
				Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			},
			new String[] {"uuid_", "companyId"}, true);

		_finderPathWithoutPaginationFindByUuid_C = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByUuid_C",
			new String[] {String.class.getName(), Long.class.getName()},
			new String[] {"uuid_", "companyId"}, true);

		_finderPathCountByUuid_C = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUuid_C",
			new String[] {String.class.getName(), Long.class.getName()},
			new String[] {"uuid_", "companyId"}, false);

		_finderPathWithPaginationFindByCompanyId = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByCompanyId",
			new String[] {
				Long.class.getName(), Integer.class.getName(),
				Integer.class.getName(), OrderByComparator.class.getName()
			},
			new String[] {"companyId"}, true);

		_finderPathWithoutPaginationFindByCompanyId = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByCompanyId",
			new String[] {Long.class.getName()}, new String[] {"companyId"},
			true);

		_finderPathCountByCompanyId = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByCompanyId",
			new String[] {Long.class.getName()}, new String[] {"companyId"},
			false);

		_finderPathWithPaginationFindByManagerId = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByManagerId",
			new String[] {
				Long.class.getName(), Integer.class.getName(),
				Integer.class.getName(), OrderByComparator.class.getName()
			},
			new String[] {"managerId"}, true);

		_finderPathWithoutPaginationFindByManagerId = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByManagerId",
			new String[] {Long.class.getName()}, new String[] {"managerId"},
			true);

		_finderPathCountByManagerId = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByManagerId",
			new String[] {Long.class.getName()}, new String[] {"managerId"},
			false);

		_finderPathFetchByE_M = new FinderPath(
			FINDER_CLASS_NAME_ENTITY, "fetchByE_M",
			new String[] {Long.class.getName(), Long.class.getName()},
			new String[] {"employeeIdPK", "managerId"}, true);

		_finderPathCountByE_M = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByE_M",
			new String[] {Long.class.getName(), Long.class.getName()},
			new String[] {"employeeIdPK", "managerId"}, false);

		ManagerUtil.setPersistence(this);
	}

	@Deactivate
	public void deactivate() {
		ManagerUtil.setPersistence(null);

		entityCache.removeCache(ManagerImpl.class.getName());
	}

	@Override
	@Reference(
		target = ManagementPersistenceConstants.SERVICE_CONFIGURATION_FILTER,
		unbind = "-"
	)
	public void setConfiguration(Configuration configuration) {
	}

	@Override
	@Reference(
		target = ManagementPersistenceConstants.ORIGIN_BUNDLE_SYMBOLIC_NAME_FILTER,
		unbind = "-"
	)
	public void setDataSource(DataSource dataSource) {
		super.setDataSource(dataSource);
	}

	@Override
	@Reference(
		target = ManagementPersistenceConstants.ORIGIN_BUNDLE_SYMBOLIC_NAME_FILTER,
		unbind = "-"
	)
	public void setSessionFactory(SessionFactory sessionFactory) {
		super.setSessionFactory(sessionFactory);
	}

	@Reference
	protected EntityCache entityCache;

	@Reference
	protected FinderCache finderCache;

	private static final String _SQL_SELECT_MANAGER =
		"SELECT manager FROM Manager manager";

	private static final String _SQL_SELECT_MANAGER_WHERE =
		"SELECT manager FROM Manager manager WHERE ";

	private static final String _SQL_COUNT_MANAGER =
		"SELECT COUNT(manager) FROM Manager manager";

	private static final String _SQL_COUNT_MANAGER_WHERE =
		"SELECT COUNT(manager) FROM Manager manager WHERE ";

	private static final String _ORDER_BY_ENTITY_ALIAS = "manager.";

	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY =
		"No Manager exists with the primary key ";

	private static final String _NO_SUCH_ENTITY_WITH_KEY =
		"No Manager exists with the key {";

	private static final Log _log = LogFactoryUtil.getLog(
		ManagerPersistenceImpl.class);

	private static final Set<String> _badColumnNames = SetUtil.fromArray(
		new String[] {"uuid"});

	@Override
	protected FinderCache getFinderCache() {
		return finderCache;
	}

}