<nav>
	<div class="nav-wrapper">
        <#if offline==true><a href="#" class="brand-logo black"><img src="html/logo.png"></a>
        <#else><a href="#!" class="brand-logo black"><img src="${config.protocol?lower_case}://${cdnURI}${iconcommit}/commons/img/logo.png"></a>
        </#if>
		<!-- slideout menu -->
		<ul id='slide-out' class='side-nav fixed hide-on-med-and-down'>
			<li class='waves-effect active'><a href='#!' view='test-view' onclick="configureView(0);chartsView('test');"><i class='material-icons'>dashboard</i></a></li>
			<#if config.enableCategoryView==true && report.categoryCtx.hasItems()>
			<li class='waves-effect'><a href='#!' view='category-view' onclick="configureView(1)"><i class='material-icons'>label_outline</i></a></li>
			</#if>
			<#if config.enableExceptionView==true && report.exceptionInfoCtx.hasItems()>
			<li class='waves-effect'><a href='#!' view='exception-view' onclick="configureView(2)"><i class='material-icons'>bug_report</i></a></li>
			</#if>
			<#if config.enableAuthorView==true && report.authorCtx.hasItems()>
			<li class='waves-effect'><a href='#!' view='author-view' onclick="configureView(3)"><i class='material-icons'>person_outline</i></a></li>
			</#if>
			<li class='waves-effect'><a href='#!' onclick="configureView(-1);chartsView('dashboard');" view='dashboard-view'><i class='material-icons'>track_changes</i></a></li>
			<#if config.enableTestRunnerLogsView==true && report.logs?size != 0>
			<li class='waves-effect'><a href='#!' onclick="configureView(-1)" view='testrunner-logs-view'><i class='material-icons'>note</i></a></li>
			</#if>
		</ul>
		<!-- report name -->
		<span class='report-name'>${ config.reportName }</span>
		<!-- report headline -->
		<span class='report-headline'></span>
		<!-- nav-right -->
		<ul id='nav-mobile' class='right hide-on-med-and-down nav-right'>
			<a href='#!'>
			<span class='label blue darken-3 suite-start-time'>${ report.startTime?datetime?string["${timeStampFormat}"] }</span>
			</a>
		</ul>
	</div>
</nav>