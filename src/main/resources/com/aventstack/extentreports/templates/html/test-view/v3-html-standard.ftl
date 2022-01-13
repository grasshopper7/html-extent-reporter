<div class='test-time-info'>
	<span class='label start-time'>${ test.startTime?datetime?string["${timeStampFormat}"] }</span>
	<span class='label end-time'>${ test.endTime?datetime?string["${timeStampFormat}"] }</span>
	<span class='label time-taken grey lighten-1 white-text'>${ ReportUtils.timeTakenPretty(test.timeTaken()) }</span>
</div>
<#if test.description?? && test.description?has_content>
<div class='test-desc'>${ test.description} </div>
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
<#if test.hasLog()>
<div class='test-steps'>
	<table class='bordered table-results'>
		<thead>
			<tr>
				<th>Status</th>
				<th>Timestamp</th>
				<th>Details</th>
			</tr>
		</thead>
		<tbody>
			<#list test.logs as log>
			<tr class='log' status='${ log.status?lower_case }'>			
				<td class='status ${ log.status?lower_case }' title='${ log.status }' alt='${ log.status }'><i class='material-icons'> ${ MaterialIcon.getIcon(log.status) } </i></td>
				<td class='timestamp'>${ log.timestamp?time?string }</td>
				<td class='step-details'>
					<#if log.exception??>
						<textarea disabled class="code-block">${log.exception.stackTrace}</textarea>
					<#else>
						${log.details}
					</#if>
					<#if log.hasMedia()==true>
						${ ReportUtils.getMediaSource(log.media)}
					</#if>
				</td>
			</tr>
			</#list>
		</tbody>
	</table>
</div>

<#if test.hasChildren()>
<ul class='collapsible node-list' data-collapsible='accordion'>
	<#macro recurse_nodes nodeList>
	<#list nodeList as node>
	<#assign leaf=(node.hasChildren())?then('','leaf')>
	<li class='node level-${ node.level } ${ leaf } ${ node.status?lower_case }' status='${ node.status }' test-id='${ node.getId() }'>
		<div class='collapsible-header'>
			<div class='node-name'>${ node.name }</div>
			<span class='node-time label start-time'>${ node.startTime?datetime?string["${timeStampFormat}"] }</span>
			<span class='node-time label end-time'>${ node.endTime?datetime?string["${timeStampFormat}"] }</span>
			<span class='node-duration label time-taken grey lighten-1 white-text'>${ ReportUtils.timeTakenPretty(node.timeTaken()) }</span>
			<span class='test-status right ${ node.status?lower_case }'>${ node.status }</span>
			<#if node.hasCategory()>
			<div class='category-list'>
				<#list node.categorySet as category>
				<span class='category label'>${ category.name }</span>
				</#list>
			</div>
			</#if>						
		</div>
		<#assign displayContent=true>
		<#if node.getStatus()== Status.PASS && disableToggleActionForPassedNode==true>
			<#assign displayContent=false>
		</#if>
		<#if node.hasLog() && displayContent>
		<div class='collapsible-body'>
			<#if node.hasLog()>
			<#if node.description?? && node.description?has_content>
			<div class='node-desc'>${ node.description}</div>
			</#if>
			<#if node.hasAuthor()>
			<div class='author-list right'>
				<#list node.authorSet as author>
				<span class='author label white-text'>${ author.name }</span>
				</#list>
			</div>
			</#if>
			<div class='node-steps'>
				<table class='bordered table-results'>
					<thead>
						<tr>
							<th>Status</th>
							<th>Timestamp</th>
							<th>Details</th>
						</tr>
					</thead>
					<tbody>
						<#list node.logs as log>
						<tr class='log' status='${ log.status }'>
							<td class='status ${ log.status?lower_case }' title='${ log.status }' alt='${ log.status }'><i class='material-icons'>${ MaterialIcon.getIcon(log.status) }</i></td>
							<td class='timestamp'>${ log.timestamp?time?string }</td>
							<td class='step-details'>
								<#if log.exception??>
									<textarea disabled class="code-block">${log.exception.stackTrace}</textarea>
								<#else>
									${log.details}
								</#if>
								<#if log.hasMedia()==true>
									${ ReportUtils.getMediaSource(log.media)}
								</#if>
							</td>
						</tr>
						</#list>
					</tbody>
				</table>
				<#if node.hasScreenCapture()==true>
					<ul class='screenshots'>
						<#list node.media as sc>
							<li>${ ReportUtils.getMediaSource(sc)}</li>
						</#list>
					</ul>
				</#if>
			</div>
			</#if>
		</div>
		</#if>
		<#if node.hasChildren()>
		<ul class='collapsible node-list' data-collapsible='accordion'>
			<@recurse_nodes nodeList=node.children />
		</ul>
		</#if>
	</li>
	</#list>
	</#macro>
	<@recurse_nodes nodeList=test.children />
</ul>
</#if>
</#if>