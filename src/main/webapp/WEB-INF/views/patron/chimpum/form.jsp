
<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>



<acme:form >

	<jstl:if test="${acme:anyOf(command, 'show')}">
		<acme:input-textbox  readonly="true" code="patron.chimpum.form.label.code" path="code"/>
		<acme:input-textbox readonly="true" code="patron.chimpum.form.label.description" path="description"/>
		<acme:input-moment  readonly="true" code="patron.chimpum.form.label.initPeriod" path="initPeriod"/>
		<acme:input-moment  readonly="true" code="patron.chimpum.form.label.finalPeriod" path="finalPeriod"/>
		<acme:input-moment readonly="true" code="patron.chimpum.form.label.creationMoment" path="creationMoment"/>
		<acme:input-money  readonly="true" code="patron.chimpum.form.label.budget" path="budget"/>
		<acme:input-money  readonly="true" code="patron.chimpum.form.label.moneyExchange" path="moneyExchange"/>
		<acme:input-textbox  readonly="true" code="patron.chimpum.list.label.link" path="link"/>
		<br></br>
		
		<h3> <acme:message code="patron.chimpum.artefact.form.label.title"/> </h3>
		<acme:input-textbox readonly="true" code="patron.chimpum.artefact.form.label.name" path="toolName"/>
		<acme:input-textbox readonly="true" code="patron.chimpum.artefact.form.label.code" path="toolCode"/>
		<acme:input-textbox readonly="true" code="patron.chimpum.artefact.form.label.toolDescription" path="toolDescription"/>
		<acme:input-textbox readonly="true" code="patron.chimpum.artefact.form.label.toolTechonology" path="toolTechonology"/>
		<acme:input-textbox readonly="true" code="patron.chimpum.artefact.form.label.toolRetailPrice" path="toolRetailPrice"/>
		<acme:input-textbox readonly="true" code="patron.chimpum.artefact.form.label.moneyExchangeRetail" path="moneyExchangeRetail"/>
		<acme:input-textbox readonly="true" code="patron.chimpum.artefact.form.label.toolInventor" path="toolInventor"/>
		
		
		<acme:button code="patron.toolkit.form.submit.update.chimpum-artefact" action="/patron/chimpum/update-artefact?id=${chimpumId}" />
		<acme:button code="patron.toolkit.form.submit.update.chimpum" action="/patron/chimpum/update?id=${chimpumId}" />
		<acme:submit code="patron.toolkit.form.submit.delete.chimpum" action="/patron/chimpum/delete" />
		
	</jstl:if>
	<jstl:if test="${acme:anyOf(command, 'update')}">
			<acme:input-textbox code="patron.chimpum.form.label.code" path="code"/>
			<acme:input-textbox  code="patron.chimpum.form.label.description" path="description"/>
			<acme:input-moment  code="patron.chimpum.form.label.initPeriod" path="initPeriod"/>
			<acme:input-moment  code="patron.chimpum.form.label.finalPeriod" path="finalPeriod"/>
			<acme:input-money  code="patron.chimpum.form.label.budget" path="budget"/>
			<acme:input-textbox  code="patron.chimpum.list.label.link" path="link"/>
		</jstl:if>
		
		
		<jstl:if test="${acme:anyOf(command, 'create')}">
			<acme:input-textbox code="patron.chimpum.form.label.code" path="code"/>
			<acme:input-textbox  code="patron.chimpum.form.label.description" path="description"/>
			<acme:input-moment  code="patron.chimpum.form.label.initPeriod" path="initPeriod"/>
			<acme:input-moment  code="patron.chimpum.form.label.finalPeriod" path="finalPeriod"/>
			<acme:input-money  code="patron.chimpum.form.label.budget" path="budget"/>
			<acme:input-textbox  code="patron.chimpum.list.label.link" path="link"/>
			<acme:input-select code="patron.toolkit.form.label.artefact" path="artefactId" >	
			<jstl:if test="${artefacts.size() == 0}">	
					<acme:input-option code="patron.toolkit.form.empty.list" value="" selected="true"/>
			</jstl:if>
			<jstl:forEach items="${artefacts}" var="artefact">
				<acme:input-option code="${artefact.getType()}: ${artefact.getCode()} ${artefact.getName()} ${artefact.getRetailPrice()}" value="${artefact.getId()}"/>
			</jstl:forEach>

			</acme:input-select>
			
		</jstl:if>
		
		
		
		
	<jstl:if test="${command == 'update'}">
		<acme:submit code="patron.toolkit.form.submit.update.chimpum" action="/patron/chimpum/update" />
	</jstl:if>
	<jstl:if test="${command == 'create'}">
		<acme:submit code="patron.toolkit.form.submit.create.chimpum" action="/patron/chimpum/create" />
	</jstl:if>
	
	<jstl:if test="${command == 'update-artefact'}">
			<jstl:if test="${artefactName != null}">	
				<acme:input-textbox readonly="true" code="patron.chimpum.form.label.artefact.selected" path="artefactName"/>
			</jstl:if>
				<acme:input-select code="patron.toolkit.form.label.artefact" path="artefactId" >	
			<jstl:if test="${artefacts.size() == 0}">	
					<acme:input-option code="patron.toolkit.form.empty.list" value="" selected="true"/>
			</jstl:if>
			<jstl:forEach items="${artefacts}" var="artefact">
				<acme:input-option code="${artefact.getType()}: ${artefact.getCode()} ${artefact.getName()} ${artefact.getRetailPrice()}" value="${artefact.getId()}"/>
			</jstl:forEach>

			</acme:input-select>
		
		<acme:submit code="patron.toolkit.form.submit.update.chimpum-artefact" action="/patron/chimpum/update-artefact" />
	</jstl:if>
	
</acme:form>