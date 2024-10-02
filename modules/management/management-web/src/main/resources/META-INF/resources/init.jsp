<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet" %><%@
taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %><%@
taglib uri="http://liferay.com/tld/aui" prefix="aui" %><%@
taglib uri="http://liferay.com/tld/portlet" prefix="liferay-portlet" %><%@
taglib uri="http://liferay.com/tld/theme" prefix="liferay-theme" %><%@
taglib uri="http://liferay.com/tld/ui" prefix="liferay-ui" %><%@
taglib uri="http://liferay.com/tld/clay" prefix="clay" %><%@
taglib uri="http://liferay.com/tld/frontend-data-set" prefix="frontend-data-set" %><%@
taglib prefix="liferay-ui" uri="http://liferay.com/tld/ui" %><%@
taglib prefix="liferay-frontend" uri="http://liferay.com/tld/aui" %>

<%@ page import="com.liferay.portal.kernel.util.Constants" %><%@
page import="management.web.constants.ManagementPortletKeys" %><%@
page import="com.liferay.portal.kernel.util.ParamUtil" %><%@
page import="management.web.display.EmployeeDisplay" %><%@
page import="com.liferay.portal.kernel.dao.search.SearchContainer" %><%@
page import="management.web.internal.dao.search.ManagementDisplaySearchContainerFactory" %><%@
page import="management.web.display.context.ViewEmployeesManagementToolbarDisplayContext" %><%@
page import="com.liferay.portal.kernel.util.WebKeys" %><%@
page import="com.management.app.exception.NoSuchEmployeeException" %><%@
page import="management.web.display.context.ManagementDisplayContext" %><%@
page import="management.web.constants.EmployeeDetailsFDSNames" %><%@
page import="management.web.display.context.ManagementDisplayContext" %><%@
page import="management.web.display.EmployeeDisplay" %><%@
page import="management.web.display.EmployeeDisplay" %><%@
page import="management.web.constants.ManagementPortletKeys" %><%@
page import="com.management.app.model.Employee" %><%@
page import="com.management.app.service.EmployeeLocalServiceUtil" %><%@
page import="java.util.ArrayList" %><%@
page import="java.util.List" %><%@
page import="com.liferay.portal.kernel.util.StringUtil" %><%@
page import="management.web.constants.EmployeeRequestConstant" %>

<liferay-theme:defineObjects />

<portlet:defineObjects />
