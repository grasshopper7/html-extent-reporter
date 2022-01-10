<#assign sizeLarge='s12 m12 l12'>
<#if childCount!=0>
    <#assign sizeLarge='s12 m6 l6'>
</#if>
<#if report.isBDD() || (childCount != 0 && grandChildCount != 0)>
    <#assign sizeLarge='s12 m4 l4'>
</#if>
<#assign chartWidth="90" chartHeight="70" chartBoxHeight="94">
<div id='test-view-charts' class='subview-full'>
    <div id='charts-row' class='row nm-v nm-h'>
        <div class='col ${ sizeLarge } np-h'>
            <div class='card-panel nm-v'>
                <div class='left panel-name'>${ parentHeading }</div>
                <div class='chart-box' style="max-height:${chartBoxHeight}px;">
                    <canvas id='parent-analysis' width='${chartWidth}' height='${chartHeight}'></canvas>
                </div>
                <div class='block text-small'>
                    <span class='tooltipped' data-position='top' data-tooltip='$${report.stats.parentPercentage?api.get(Status.PASS)}%'>
                    <span class='strong'>${report.stats.parent?api.get(Status.PASS)}</span> ${parentHeading?lower_case} passed</span>
                </div>
                <div class='block text-small'>
                    <span class='strong tooltipped' data-position='top' data-tooltip='${report.stats.parentPercentage?api.get(Status.FAIL)}%'>
                    ${ report.stats.parent?api.get(Status.FAIL) }</span> ${parentHeading?lower_case} failed, 
                    <span class='strong tooltipped' data-position='top' data-tooltip='${report.stats.parentPercentage?api.get(Status.SKIP)}%'>
                    ${ report.stats.parent?api.get(Status.SKIP) }</span> skipped
                </div>
            </div>
        </div>
        <#if childCount != 0>
        <div class='col ${ sizeLarge } np-h'>
            <div class='card-panel nm-v'>
                <div class='left panel-name'>${ childHeading }</div>
                <div class='chart-box' style="max-height:${chartBoxHeight}px;">
                    <canvas id='child-analysis' width='${chartWidth}' height='${chartHeight}'></canvas>
                </div>
                <div class='block text-small'>
                    <span class='tooltipped' data-position='top' data-tooltip='${report.stats.childPercentage?api.get(Status.PASS)}%'>
                    <span class='strong'>${ report.stats.child?api.get(Status.PASS) }</span> ${childHeading?lower_case} passed</span>
                </div>
                <div class='block text-small'>
                    <span class='strong tooltipped' data-position='top' data-tooltip='${report.stats.childPercentage?api.get(Status.FAIL)}%'>
                    ${ report.stats.child?api.get(Status.FAIL) }</span> ${childHeading?lower_case} failed, 
                    <span class='strong tooltipped' data-position='top' data-tooltip='${report.stats.childPercentage?api.get(Status.SKIP)}%'>
                    ${ report.stats.child?api.get(Status.SKIP) }</span> skipped, 
                    <span class='strong tooltipped' data-position='top' data-tooltip='${report.stats.childPercentage?api.get(Status.WARNING) + report.stats.childPercentage?api.get(Status.INFO)}%'>
                    ${ report.stats.child?api.get(Status.WARNING) + report.stats.child?api.get(Status.INFO) }</span> others
                </div>
            </div>
        </div>
        </#if>
        <#if grandChildCount != 0>
        <div class='col ${ sizeLarge } np-h'>
            <div class='card-panel nm-v'>
                <div class='left panel-name'>${ grandChildHeading }</div>
                <div class='chart-box' style="max-height:${chartBoxHeight}px;">
                    <canvas id='grandchild-analysis' width='${chartWidth}' height='${chartHeight}'></canvas>
                </div>
                <div class='block text-small'>
                    <span class='tooltipped' data-position='top' data-tooltip='${report.stats.grandchildPercentage?api.get(Status.PASS)}%'>
                    <span class='strong'>${ report.stats.grandchild?api.get(Status.PASS) }</span> ${grandChildHeading?lower_case} passed</span>
                </div>
                <div class='block text-small'>
                    <span class='strong tooltipped' data-position='top' data-tooltip='${report.stats.grandchildPercentage?api.get(Status.FAIL)}%'>
                    ${ report.stats.grandchild?api.get(Status.FAIL) }</span> ${grandChildHeading?lower_case} failed, 
                    <span class='strong tooltipped' data-position='top' data-tooltip='${report.stats.grandchildPercentage?api.get(Status.SKIP)}%'>
                    ${ report.stats.grandchild?api.get(Status.SKIP) }</span> skipped, 
                    <span class='strong tooltipped' data-position='top' data-tooltip='${report.stats.grandchildPercentage?api.get(Status.WARNING) + report.stats.grandchildPercentage?api.get(Status.INFO)}%'>
                    ${ report.stats.grandchild?api.get(Status.WARNING) + report.stats.grandchild?api.get(Status.INFO) }</span> others
                </div>
            </div>
        </div>
        </#if>
    </div>
    <#if enableTimeline=='true'>
    <div id="timeline-chart" class="row nm-v nm-h">
        <div class="col s12 m12 l12 np-h">
            <div class="card-panel">
                <div class='left panel-name'>Timeline (seconds)</div>
                <div class="chart-box" style="width:98%;max-height:145px;">
                    <canvas id="timeline" height="120"></canvas>
                </div>
            </div>
        </div>
    </div>
    </#if>
</div>