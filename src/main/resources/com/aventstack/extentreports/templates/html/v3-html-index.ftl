<#include "../commons/commons-variables.ftl">

<#assign parentCount=report.stats.sumStat(report.stats.parent)>
<#assign childCount=report.stats.sumStat(report.stats.child)>
<#assign grandChildCount=report.stats.sumStat(report.stats.grandchild)>
<#assign testViewChartLocation='top'>
<#assign chartVisibleOnOpen='true'>
<#assign enableTimeline='true'>
<#assign offline='false'>

<#--<#assign theme=config.containsConfig('theme')?then(config.getConfig('theme')?lower_case, 'standard')>
<#assign testViewChartLocation=config.containsConfig('chartLocation')?then(config.getConfig('chartLocation')?lower_case, 'top')>
<#assign chartVisibleOnOpen=config.containsConfig('chartVisibilityOnOpen')?then(config.getConfig('chartVisibilityOnOpen'), 'false')>
<#assign klovUrl=config.containsConfig('klov-url')?then(config.getConfig('klov-url'), '')>
<#assign disableToggleActionForPassedNode=config.containsConfig('disableToggleActionForPassedNode')?then(config.getConfig('disableToggleActionForPassedNode'), '')>
<#assign enableTimeline=config.containsConfig('enableTimeline')?then(config.getConfig('enableTimeline'), 'true')>
<#assign bddReport=false>
<#assign bddClass=''>
<#if report.testList?? && report.testList?size != 0>
<#assign firstTest=report.testList[0]>
<#assign bddReport = (TestService.testHasChildren(firstTest) && firstTest.nodeContext.get(0).isBehaviorDrivenType())?then(true, false)>
</#if>
<#assign parentViewChartsHeading='Classes' childViewChartsHeading='Tests' grandChildViewChartsHeading='Steps'>
<#assign parentLabel='class(es)' childLabel='test(s)' grandChildLabel='log(s)'>
<#if bddReport>
<#assign parentViewChartsHeading='Features' childViewChartsHeading='Scenarios' grandChildViewChartsHeading='Steps'>
<#assign parentLabel='feature(s)' childLabel='scenario(s)' grandChildLabel='step(s)'>
<#assign bddClass='bdd-report'>
<#else>
<#if (childCount == 0 || grandChildCount == 0)>
<#assign parentViewChartsHeading='Tests' childViewChartsHeading='Steps' grandChildViewChartsHeading=''>
<#assign parentLabel='test(s)' childLabel='step(s)' grandChildLabel=''>
</#if>
<#if report.analysisStrategy?string == 'SUITE'>
<#assign parentViewChartsHeading='Suites' childViewChartsHeading='Tests' grandChildViewChartsHeading='Test Methods'>
<#assign parentLabel='suite(s)' childLabel='test(s)' grandChildLabel='method(s)'>
</#if>
</#if>

<#assign timeStampFormat = config.getConfig('timeStampFormat')>-->
<#assign resourceCDN=config.resourceCDN cdnURI="cdn.jsdelivr.net/gh/extent-framework/extent-github-cdn@" csscommit="ff53917fbbdb5ef820abbbe4d199a6942dc771ff" jscommit="ff53917fbbdb5ef820abbbe4d199a6942dc771ff">
<#if resourceCDN=="extentreports">
    <#assign cdnURI="extentreports.com/resx" csscommit="" jscommit="">
</#if>

<#-- Why twice called -->
<#--<#include "../commons/commons-variables.ftl">-->
<#assign bddClass=''>
<#if report.isBDD()>
	<#assign bddClass='bdd-report'>
</#if>

<!DOCTYPE html>
<html>
	<#include 'v3-html-head.ftl'>
	<body class='extent ${ theme } default hide-overflow ${ bddClass }'>
		<div id='theme-selector' alt='Click to toggle theme. To enable by default, use theme configuration.' title='Click to toggle theme. To enable by default, use theme configuration.'>
			<span><i class='material-icons'>desktop_windows</i></span>
		</div>
		<#include 'v3-html-nav.ftl'>
		<!-- container -->
		<div class='container'>
			<#include 'test-view/v3-html-test-view.ftl'>
			<#if report.categoryCtx.hasItems()>
				<#include 'category-view/v3-html-category-view.ftl'>
			</#if>
			<#if report.exceptionInfoCtx.hasItems()>
				<#include 'exception-view/v3-html-exception-view.ftl'>
			</#if>
			<#if report.authorCtx.hasItems()>
				<#include 'author-view/v3-html-author-view.ftl'>			
			</#if>
			<#include 'dashboard-view/v3-html-dashboard-view.ftl'>
			<#if report.logs?size != 0>
				<#include 'logs-view/v3-html-testrunner-logs-view.ftl'>
			</#if>		
		</div>
		<!-- container -->
		<script>
			var statusGroup = {
                parentCount: ${ report.stats.sumStat(report.stats.parent)?c },
				passParent: ${ report.stats.parent?api.get(Status.PASS)?c },
				failParent: ${ report.stats.parent?api.get(Status.FAIL)?c },
				fatalParent: 0,
				errorParent: 0,
				warningParent: ${ report.stats.parent?api.get(Status.WARNING)?c  },
				skipParent: ${ report.stats.parent?api.get(Status.SKIP)?c  },
				<#--exceptionsParent: ${ report.reportStatusStats.parentCountExceptions?c },-->
				childCount: ${ report.stats.sumStat(report.stats.child)?c },
				passChild: ${ report.stats.child?api.get(Status.PASS)?c },
				failChild: ${ report.stats.child?api.get(Status.FAIL)?c },
				fatalChild: 0,
				errorChild: 0,
				warningChild: ${ report.stats.child?api.get(Status.WARNING)?c },
				skipChild: ${ report.stats.child?api.get(Status.SKIP)?c },
				infoChild: ${ report.stats.child?api.get(Status.INFO)?c },
				<#--debugChild: ${ report.reportStatusStats.childCountDebug?c },
				exceptionsChild: ${ report.reportStatusStats.childCountExceptions?c },-->
				grandChildCount: ${ report.stats.sumStat(report.stats.grandchild)?c },
				passGrandChild: ${ report.stats.grandchild?api.get(Status.PASS)?c },
				failGrandChild: ${ report.stats.grandchild?api.get(Status.FAIL)?c },
				fatalGrandChild: 0,
				errorGrandChild: 0,
				warningGrandChild: ${ report.stats.grandchild?api.get(Status.WARNING)?c },
				skipGrandChild: ${ report.stats.grandchild?api.get(Status.SKIP)?c },
				infoGrandChild: ${ report.stats.grandchild?api.get(Status.INFO)?c },
				<#--debugGrandChild: ${ report.reportStatusStats.grandChildCountDebug?c },
				exceptionsGrandChild: ${ report.reportStatusStats.grandChildCountExceptions?c },-->
			};
			</script>
		
		<#if enableTimeline=='true'>
		<script>
			<#macro listTestNameDuration testList>
			   <#if report.testList??>
			        <#list report.testList as t>"${t.name}":${(t.timeTaken()/1000)?c?replace(",","")}<#if t_has_next>,</#if></#list>
			   </#if>
			</#macro>
			var timeline = {
			    <@listTestNameDuration testList=report.testList />
			};
		</script>
		</#if>
		
		 <#if offline=="true">
		  <script src='${config.offlineDirectory}v3html-script.js' type='text/javascript'></script>
		<#else>
		  <script src='${config.protocol}://${cdnURI}${jscommit}/v3html/js/extent.js' type='text/javascript'></script>
		</#if>
		
		<#assign hide=(chartVisibleOnOpen=='true')?then(false, true)>
		<#if hide>
		<script type='text/javascript'>
			$(document).ready(function() {
				$('#test-view-charts').addClass('hide');
			});
		</script>
		</#if>
		
		<script type='text/javascript'>
			<#if config.js??>
				${ config.js }
			</#if>
			<#if config.scripts??>
			${ config.scripts }
			</#if>
		</script> 

	</body>
</html>