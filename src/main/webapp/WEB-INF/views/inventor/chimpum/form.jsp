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
	


	<jstl:if test="${acme:anyOf(command, 'update')}">
			<acme:input-textbox code="inventor.chimpum.form.label.code" path="code"/>
			<acme:input-textbox  code="inventor.chimpum.form.label.description" path="description"/>
			<acme:input-moment  code="inventor.chimpum.form.label.initPeriod" path="initPeriod"/>
			<acme:input-moment  code="inventor.chimpum.form.label.finalPeriod" path="finalPeriod"/>
			<acme:input-money  code="inventor.chimpum.form.label.budget" path="budget"/>
			<acme:input-textbox  code="inventor.chimpum.list.label.link" path="link"/>
			
			<acme:submit code="inventor.chimpum.form.submit.update.chimpum" action="/inventor/chimpum/update" />
	
	</jstl:if>
		<jstl:if test="${acme:anyOf(command,'create')}">
			<acme:input-textbox code="inventor.chimpum.form.label.code" path="code"/>
			<acme:input-textbox  code="inventor.chimpum.form.label.description" path="description"/>
			<acme:input-moment  code="inventor.chimpum.form.label.initPeriod" path="initPeriod"/>
			<acme:input-moment  code="inventor.chimpum.form.label.finalPeriod" path="finalPeriod"/>
			<acme:input-money  code="inventor.chimpum.form.label.budget" path="budget"/>
			<acme:input-textbox  code="inventor.chimpum.list.label.link" path="link"/>	
	</jstl:if>
		
	<jstl:if test="${command == 'create'}">
	
		<acme:submit code="inventor.chimpum.form.submit.create" action="/inventor/chimpum/create?id=${artefactId}" />	

	</jstl:if>
</acme:form>