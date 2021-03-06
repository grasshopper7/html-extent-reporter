<#if report.exceptionInfoCtx.hasItems()>
<div id='exception-view' class='view hide'>
	<section id='controls'>
		<div class='controls grey lighten-4'>
			<!-- search -->
			<div class='chip transparent' alt='Search Tests' title='Search Tests'>
				<a href="#" class='search-div'>
				<i class='material-icons'>search</i> Search
				</a>
				<div class='input-field left hide'>
					<input id='search-tests' type='text' class='validate browser-default' placeholder='Search Tests...'>
				</div>
			</div>
			<!-- search -->
		</div>
	</section>
	<div class='subview-left left'>
		<div class='view-summary'>
			<ul id='exception-collection' class='exception-collection'>
				<#list report.exceptionInfoCtx.set as exception>
				<li class='exception displayed active'>
					<div class='exception-heading'>
						<span class='exception-name'>${ exception.attr.name }</span>
						<span class='exception-count right'><span class='label red lighten-1 white-text'>${ exception.testList?size }</span></span>
					</div>
					<div class='exception-content hide'>
						<div class='exception-tests'>
							<table class='bordered table-results'>
								<thead>
									<tr>
										<th>Timestamp</th>
										<th>TestName</th>
										<th>StackTrace</th>
									</tr>
								</thead>
								<tbody>
									<#list exception.testList as test>
									<#-- <#list test.exceptionInfoContext.all as testException>
									<#if testException.getExceptionName() == exception.exceptionInfo.getExceptionName()> -->
									<tr>
										<td>${ test.startTime?datetime?string["${timeStampFormat}"] }</td>
										<td class='linked' test-id='${ test.getId() }'>${ test.name }</td>
										<td>
											<textarea disabled class="code-block">${ exception.attr.stackTrace }</textarea>
										</td>
									</tr>
									<#--</#if>
									</#list> -->
									</#list>
								</tbody>
							</table>
						</div>
					</div>
				</li>
				</#list>
			</ul>
		</div>
	</div>
	<div class='subview-right left'>
		<div class='view-summary'>
			<h5 class='exception-name'></h5>
		</div>
	</div>
</div>
</#if>
<!-- exception view -->