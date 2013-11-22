<div id="graphComparison" class="text-center"
	style="min-width: 640px; width: 860px; height: 550px; margin: 0 auto"></div>

<script type="text/javascript">
	$(function() {
		$('#graphComparison')
				.highcharts(
						{
							chart : {
								polar : true,
								backgroundColor : '#FFFFFF',
								type : 'line'
							},
							title : {
								text : '',
								x : -80
							},
							pane : {
								size : '80%'
							},
							credits : {
								enabled : false
							},
							xAxis : {
								categories : <%=request.getAttribute("comparison.categories")%>,
								tickmarkPlacement : 'on',
								lineWidth : 0
							},
							yAxis : {
								gridLineInterpolation : 'polygon',
								lineWidth : 0,
								startOnTick : false,
								minPadding : 0.1
							},
							tooltip : {
								shared : true,
								useHTML : true,
								headerFormat : '<big><u><b>{point.key}</b></u></big><table>',
								pointFormat : '<tr>'
										+ '<td style="color:{series.color}">{series.name}: </td>'
										+ '<td style="text-align: right"><b>{point.options.ttip:,.2f} {point.options.unit}</b></td>'
										+ '</tr>',
								footerFormat : '</table>',
							// valueDecimals: 2
							},
							legend : {
								align : 'right',
								verticalAlign : 'top',
								y : 70,
								layout : 'vertical'
							},
							series : <%=request.getAttribute("comparison.series")%>
						});
	});
</script>
