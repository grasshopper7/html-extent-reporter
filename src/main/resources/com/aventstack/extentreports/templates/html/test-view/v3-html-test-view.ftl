<div id='test-view' class='view'>
	<section id='controls'>
		<div class='controls grey lighten-4'>
			<!-- test toggle -->
			<div class='chip transparent'>
				<a class='dropdown-button tests-toggle' data-activates='tests-toggle' data-constrainwidth='true' data-beloworigin='true' data-hover='true' href='#'>
				<i class='material-icons'>warning</i> Status
				</a>
				<ul id='tests-toggle' class='dropdown-content'>
					<#if report.anyTestHasStatus(Status.PASS)>
					<li status='pass'><a href='#!'>Pass <i class='material-icons green-text'>check_circle</i></a></li>
					</#if>
					<#if report.anyTestHasStatus(Status.FAIL)>
					<li status='fail'><a href='#!'>Fail <i class='material-icons red-text'>cancel</i></a></li>
					</#if>
					<#if report.anyTestHasStatus(Status.WARNING)>
					<li status='warning'><a href='#!'>Warning <i class='material-icons orange-text'>warning</i></a></li>
					</#if>
					<#if report.anyTestHasStatus(Status.SKIP)>
					<li status='skip'><a href='#!'>Skip <i class='material-icons cyan-text'>redo</i></a></li>
					</#if>
					<li class='divider'></li>
					<li status='clear' clear='true'><a href='#!'>Clear Filters <i class='material-icons'>clear</i></a></li>
				</ul>
			</div>
			<!-- test toggle -->
			<!-- category toggle -->
			<#if report.categoryCtx.hasItems()>
			<div class='chip transparent'>
				<a class='dropdown-button category-toggle' data-activates='category-toggle' data-constrainwidth='false' data-beloworigin='true' data-hover='true' href='#'>
				<i class='material-icons'>local_offer</i> Category
				</a>
				<ul id='category-toggle' class='dropdown-content'>
					<#list report.categoryCtx.set as category>
					<li><a href='#'>${ category.attr.name }</a></li>
					</#list>
					<li class='divider'></li>
					<li class='clear'><a href='#!' clear='true'>Clear Filters</a></li>
				</ul>
			</div>
			</#if>
			<!-- category toggle -->
			<!-- clear filters -->
			<div class='chip transparent hide'>
				<a class='' id='clear-filters' alt='Clear Filters' title='Clear Filters'>
				<i class='material-icons'>close</i> Clear
				</a>
			</div>
			<!-- clear filters -->
			<!-- enable dashboard -->
			<div id='toggle-test-view-charts' class='chip transparent'>
				<#assign btnEnabledClass=(chartVisibleOnOpen=='true')?then('pink-text','')>
				<a class='${ btnEnabledClass }' id='enable-dashboard' alt='Enable Dashboard' title='Enable Dashboard'>
				<i class='material-icons'>track_changes</i> Dashboard
				</a>
			</div>
			<!-- enable dashboard -->
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

	<#if testViewChartLocation=='top'>
		<#include 'v3-html-test-view-charts.ftl'>
	</#if>
	
	<div class='subview-left left'>
		<div class='view-summary'>
			<ul id='test-collection' class='test-collection'>
				<#list report.testList as test>
				<#-- <#assign isBdd = (TestService.testHasChildren(test) && test.nodeContext.get(0).isBehaviorDrivenType())> -->
				<#assign hasChildrenClass = ''>
				<#if test.hasChildren()==true>
				<#assign hasChildrenClass = 'has-leaf'>
				</#if>
				<li class='test displayed active ${ hasChildrenClass } ${ test.status }' status='${ test.status }' bdd='${ report.isBDD()?string }' test-id='${ test.getId() }'>
					<div class='test-heading'>
						<span class='test-name'>${ test.name }</span>
						<span class='test-time'>${ test.startTime?datetime?string["${timeStampFormat}"] }</span>
						<span class='test-status right ${ test.status }'>${ test.status }</span>
					</div>
					<div class='test-content hide'>
					<#if report.isBDD()==true>
						<#include 'v3-html-bdd.ftl'>							
					<#else>
						<#include 'v3-html-standard.ftl'>
					</#if>
					</div>
				</li>
				</#list>
			</ul>
		</div>
	</div>
	
	
	<!-- subview left -->
	<div class='subview-right left'>
		<div class='view-summary'>
			<div id='step-filters' class="right sr-filters">
				<a class="btn-floating waves-effect waves-light green" status="pass" alt="pass" title="pass"><i class="material-icons">check_circle</i></a>
				<a class="btn-floating waves-effect waves-light red" status="fail" alt="fail" title="fail"><i class="material-icons">cancel</i></a>
				<a class="btn-floating waves-effect waves-light red darken-4" status="fatal" alt="fatal" title="fatal"><i class="material-icons">cancel</i></a>
				<a class="btn-floating waves-effect waves-light pink text-lighten-1" status="error" alt="error" title="error"><i class="material-icons">error</i></a>
				<a class="btn-floating waves-effect waves-light orange" alt="warning" status="warning" title="warning"><i class="material-icons">warning</i></a>
				<a class="btn-floating waves-effect waves-light teal" status="skip" alt="skip" title="skip"><i class="material-icons">redo</i></a>
				<a class="btn-floating waves-effect waves-light grey" status="clear" alt="Clear filters" title="Clear filters"><i class="material-icons">clear</i></a>
			</div>
			<h5 class='test-name'></h5>
		</div>
	</div>
	<!-- subview right -->
	<#if testViewChartLocation=='bottom'>
		<#include 'v3-html-test-view-charts.ftl'>
	</#if>
</div>
<!-- test view -->