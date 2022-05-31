<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>

<acme:form >

	<jstl:if test="${acme:anyOf(command, 'show')}">
		<acme:input-textbox  readonly="true" code="inventor.chimpum.form.label.code" path="code"/>
		<acme:input-textbox readonly="true" code="inventor.chimpum.form.label.description" path="description"/>
		<acme:input-moment  readonly="true" code="inventor.chimpum.form.label.initPeriod" path="initPeriod"/>
		<acme:input-moment  readonly="true" code="inventor.chimpum.form.label.finalPeriod" path="finalPeriod"/>
		<acme:input-moment readonly="true" code="inventor.chimpum.form.label.creationMoment" path="creationMoment"/>
		<acme:input-money  readonly="true" code="inventor.chimpum.form.label.budget" path="budget"/>
		<acme:input-money  readonly="true" code="inventor.chimpum.form.label.moneyExchange" path="moneyExchange"/>
		<acme:input-textbox  readonly="true" code="inventor.chimpum.list.label.link" path="link"/>
		<br></br>
		
		<acme:button code="inventor.chimpum.form.submit.update" action="/inventor/chimpum/update?id=${chimpumId}" />
		<acme:submit code="inventor.chimpum.form.submit.delete" action="/inventor/chimpum/delete" />
		
	</jstl:if>
</acme:form>