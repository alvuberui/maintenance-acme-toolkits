
<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>

<acme:form>
	<acme:input-select code="inventor.artefact.form.label.type" path="type">	
		<acme:input-option code="TOOL" value="TOOL" selected="${type == 'TOOL'}"/>
		<acme:input-option code="COMPONENT" value="COMPONENT" selected="${type == 'COMPONENT'}"/>
	</acme:input-select>
	<acme:input-textbox code="inventor.artefact.form.label.name" path="name"/>
	<acme:input-textbox code="inventor.artefact.form.label.code" path="code"/>	
	<acme:input-textbox code="inventor.artefact.form.label.technology" path="technology"/>
	<acme:input-textarea code="inventor.artefact.form.label.description" path="description"/>	
	<acme:input-money code="inventor.artefact.form.label.retail-price" path="retailPrice"/>
	<jstl:choose>	
		<jstl:when test="${command == 'show'}">
			<acme:input-money readonly="${true}" code="inventor.artefact.form.label.money-exchange" path="moneyExchange"/>
		</jstl:when>	
	</jstl:choose>
	<acme:input-url code="inventor.artefact.form.label.more-info" path="moreInfo"/>	
	
	<jstl:choose>	 
		<jstl:when test="${acme:anyOf(command, 'show, update, delete, publish') && published == false}">
			<acme:submit code="inventor.artefact.form.button.update" action="/inventor/artefact/update"/>
			<acme:submit code="inventor.artefact.form.button.delete" action="/inventor/artefact/delete"/>
			<acme:submit code="inventor.artefact.form.button.publish" action="/inventor/artefact/publish"/>
		</jstl:when>
		<jstl:when test="${command == 'create'}">
			<acme:submit code="inventor.artefact.form.button.create" action="/inventor/artefact/create"/>
		</jstl:when>		
	</jstl:choose>

</acme:form>

