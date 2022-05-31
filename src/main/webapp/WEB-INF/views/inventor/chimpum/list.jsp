<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>

<acme:list>
	<acme:list-column code="patron.chimpum.list.label.code" path="code"/>
	<acme:list-column code="patron.chimpum.list.label.description" path="description"/>	
	<acme:list-column code="patron.chimpum.list.label.initPeriod"  path="initPeriod"/>
	<acme:list-column code="patron.chimpum.list.label.finalPeriod" path="finalPeriod"/>	
	<acme:list-column code="patron.chimpum.list.label.budget"  path="budget"/>
</acme:list>
<acme:button code="patron.toolkit.form.button.create.chimpum" action="/patron/chimpum/create" />