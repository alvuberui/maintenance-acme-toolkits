<%--
- form.jsp
-
- Copyright (C) 2012-2022 Rafael Corchuelo.
-
- In keeping with the traditional purpose of furthering education and research, it is
- the policy of the copyright owner to permit non-commercial use and redistribution of
- this software. It has been tested carefully, but it is not guaranteed for any particular
- purposes.  The copyright owner does not offer any warranties or representations, nor do
- they accept any liabilities with respect to them.
--%>

<%@page language="java"%>
<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>
<%@taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>
<acme:form>
	<br>	
	
	<acme:message code="inventor.system-dashboard.form.label.absoluteTitle"/>		
	<table class="table table-sm">
		<caption></caption>
		<caption></caption>
			<tr>	
				<th id="">
					<acme:message code="inventor.system-dashboard.form.label.ratio"/>		
				</th>
				<td style= "text-align:right;">
					<acme:print value="${ratioToolWithChimpum}"/>
				</td>		
			</tr>
	</table>
	
	<table class="table table-sm" aria-describedby="numberPatronages">
    <acme:message code="inventor.system-dashboard.form.label.avg"/>
    <jstl:forEach items="${avgBudgetByCurrency}" var="patronages">
        <tr>
            <th scope="row" style="width:10%">
                <acme:print value="${patronages.key}"/>
            </th>
            <td>
                <acme:print value="${patronages.value}"/>
            </td>
        </tr>
    </jstl:forEach>
</table>

	<table class="table table-sm" aria-describedby="numberPatronages">
    <acme:message code="inventor.system-dashboard.form.label.dev"/>
    <jstl:forEach items="${deviationBudgetByCurrency}" var="patronages">
        <tr>
            <th scope="row" style="width:10%">
                <acme:print value="${patronages.key}"/>
            </th>
            <td>
                <acme:print value="${patronages.value}"/>
            </td>
        </tr>
    </jstl:forEach>
</table>

	<table class="table table-sm" aria-describedby="numberPatronages">
    <acme:message code="inventor.system-dashboard.form.label.max"/>
    <jstl:forEach items="${maxBudgetByCurrency}" var="patronages">
        <tr>
            <th scope="row" style="width:10%">
                <acme:print value="${patronages.key}"/>
            </th>
            <td>
                <acme:print value="${patronages.value}"/>
            </td>
        </tr>
    </jstl:forEach>
</table>

	<table class="table table-sm" aria-describedby="numberPatronages">
    <acme:message code="inventor.system-dashboard.form.label.min"/>
    <jstl:forEach items="${minBudgetByCurrency}" var="patronages">
        <tr>
            <th scope="row" style="width:10%">
                <acme:print value="${patronages.key}"/>
            </th>
            <td>
                <acme:print value="${patronages.value}"/>
            </td>
        </tr>
    </jstl:forEach>
</table>

</acme:form>
