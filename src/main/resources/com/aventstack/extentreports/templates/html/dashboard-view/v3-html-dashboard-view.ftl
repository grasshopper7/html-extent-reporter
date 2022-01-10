<div id='dashboard-view' class='view hide'>
	<div class='card-panel transparent np-v'>
		<h5>Dashboard</h5>

		<div class='row'>
			<div class='col s2'>
				<div class='card-panel r'>
					${ parentHeading }
					<div class='panel-lead'>${ parentCount }</div>
				</div>
			</div>
			<div class='col s2'>
				<div class='card-panel r'>
					${ childHeading }
					<div class='panel-lead'>${ childCount  }</div>
				</div>
			</div>
			<#if grandChildHeading != ''> 
			<div class='col s2'>
				<div class='card-panel r'>
					${ grandChildHeading }
					<div class='panel-lead'>${ grandChildCount }</div>
				</div>
			</div>
			</#if> 
			<div class='col s2'>
				<div class='card-panel r'>
					Start
					<div class='panel-lead'>${ report.startTime?datetime?string["${timeStampFormat}"] }</div>
				</div>
			</div>
			<div class='col s2'>
				<div class='card-panel r'>
			 		End
			 		<div class='panel-lead'>${ report.endTime?datetime?string["${timeStampFormat}"] }</div>
				</div>
			</div>
			<div class='col s2'>
				<div class='card-panel r'>
					Time Taken
					<div class='panel-lead'>${ HtmlReportUtils.timeTakenPretty(report.timeTaken()) }</div>
				</div>
			</div>
			 <#if report.systemEnvInfo?size != 0>
			<div class='col s4'>
				<div class='card-panel dashboard-environment'>
					<span class='right label cyan white-text'>Environment</span><p>&nbsp;</p>
					
					<table>
						<tr>
							<th>Name</th>
							<th>Value</th>
						</tr>
						<#list report.systemEnvInfo as info>
							<#if info?? && info.name?? && info.value??>
							<tr>
								<td>${ info.name }</td>
								<td>${ info.value }</td>
							</tr>
							</#if>
						</#list>
					</table>
				</div>
			</div>
			</#if>
			<#if report.categoryCtx.set?size != 0>
			<div class='col s6'>
				<div class='card-panel dashboard-categories'>
					<span class='right label cyan white-text'>Categories</span><p>&nbsp;</p>
					
					<table>
						<tr>
							<th>Name</th>
							<th>Passed</th>
							<th>Failed</th>
							<th>Skipped</th>
							<th>Passed %</th>
						</tr>
						<#list report.categoryCtx.set as category>
						<tr>
							<td>${category.attr.name}</td>
							<td>${category.passed}</td>
							<td>${category.failed}</td>
							<td>${category.skipped}</td>
							<td>
								<#if category.size()!=0>
									${(category.passed/category.size())*100}%
								<#else>
									0%
								</#if>
							</td>
						</tr>
						</#list>
					</table>
				</div>
			</div>
			</#if> 
		</div>
	</div>
</div>
<!-- dashboard view -->
