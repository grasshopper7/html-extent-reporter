<div class="sr-filters bdd-filters">
	<a class="btn-floating waves-effect waves-light pass green" title="pass"><i class='material-icons'>${MaterialIcon.getIcon(Status.PASS)}</i></a>
	<a class="btn-floating waves-effect waves-light fail red" title="fail"><i class='material-icons'>${MaterialIcon.getIcon(Status.FAIL)}</i></a>
	<a class="btn-floating waves-effect waves-light skip blue" title="skip"><i class='material-icons'>${MaterialIcon.getIcon(Status.SKIP)}</i></a>
	<a class="btn-floating waves-effect waves-light clear grey" title="clear"><i class='material-icons'>clear</i></a>
</div>
<#if test.description?? && test.description?has_content>
	<div class='test-desc'>${test.description}</div>
</#if>
<#if test.hasAttributes()>
	<div class='test-attributes'>
		<#if test.hasCategory()>
			<div class='category-list'>
				<#list test.categorySet as category>
					<span class='category label'>${ category.name }</span>
				</#list>
			</div>
		</#if>
		<#if test.hasAuthor()>
			<div class='author-list'>
				<#list test.authorSet as author>
				<span class='author label'>${ author.name }</span>
				</#list>
			</div>
		</#if>
		<#if test.hasDevice()>
		    <div class='device-list'>
		        <#list test.deviceSet as device>
		        <span class='device label'>${ device.name }</span>
		        </#list>
		    </div>
		</#if>
	</div>
</#if>

<#list test.children as node>
<div class='${ReportUtils.getBehaviorDrivenTypeName(node)?lower_case} node' test-id='${node.getId()}' status='${node.status}'>

	<span class='duration right label'>${ReportUtils.timeTakenPretty(node.timeTaken())}</span>
	<div class="bdd-test">
		<div class="scenario-name"><span class='status ${node.status?lower_case}' title='${node.status}'><i class='material-icons'>${MaterialIcon.getIcon(node.status)}</i></span> ${ReportUtils.getBehaviorDrivenTypeName(node)}: ${node.name}</div>
	</div>
	
	<#if node.description?? && node.description?has_content>
		<div class='node-desc'>${node.description}</div>
	</#if>
	
	<#if node.hasCategory()>
		<div class='category-list'>
			<#list node.categorySet as category>
				<span class='category label'>${category.name}</span>
			</#list>
		</div>
	</#if>
	
	<#if node.hasChildren()>
	<ul class='steps'>
		<#list node.children as child>
		<li test-id='${child.getId()}' class='node ${ReportUtils.getBehaviorDrivenTypeName(child)?lower_case} ${child.status}' status='${child.status}'>
			<div class="step-name" title="(${child.description!''})"><span class='status ${child.status?lower_case}' title='${child.status}'><i class='material-icons'>${MaterialIcon.getIcon(child.status)}</i></span>${child.name}</div>

			<#if child.hasCategory()>
				<div class='category-list'>
					<#list child.categorySet as category>
						<span class='category label'>${ category.name }</span>
					</#list>
				</div>
			</#if>

			<#list child.logs as log>
				<#if log.exception??>
					<textarea disabled class="code-block">${log.exception.stackTrace}</textarea>
				</#if>
				<#if log.details?? && log.details?has_content>
					<div class="node-step">${log.details}</div>
				</#if>
				<#if log.hasMedia()==true>
					<div class='screenshots'>
						${ ReportUtils.getMediaSource(log.media)}
					</div>
				</#if>
			</#list>
			<#if child.hasChildren()>
			<ul class='gc steps'>
				<#list child.children as gc>
				<li test-id='${gc.getId()}' class='gc ${ReportUtils.getBehaviorDrivenTypeName(gc)?lower_case} ${gc.status}' status='${gc.status}'>
					<h6 class="step-name" title="${gc.description!''}"><span class='status ${gc.status?lower_case}' title='${gc.status}'><i class='material-icons'>${MaterialIcon.getIcon(gc.status)}</i></span>${gc.name}</h6>

					<#list gc.logs as log>
						<#if log.exception??>
							<textarea disabled class="code-block">${log.exception.stackTrace}</textarea>
						</#if>
						<#if log.details?? && log.details?has_content>
							<div class="node-step">${log.details}</div>
						</#if>
						<#if log.hasMedia()==true>
							<div class='screenshots'>
								${ ReportUtils.getMediaSource(log.media)}
							</div>
						</#if>
					</#list>
				</li>
				</#list>
			</ul>
			</#if>
		</li>
		</#list>
	</ul>
	</#if>
</div>
</#list>