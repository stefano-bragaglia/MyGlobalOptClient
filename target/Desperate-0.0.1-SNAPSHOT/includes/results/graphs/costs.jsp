<div id="graphCosts" class="text-center"
	style="min-width: 640px; max-width: 640px; height: 480px; margin: 0 auto"></div>

<script type="text/javascript">
	$(function() {
		$('#graphCosts')
				.highcharts(
						{
							chart : {
								type : 'bar'
							},
							title : {
								text : "Comparing scenarios w.r.t. 'func'"
							},
							credits : {
								enabled : false
							},
							xAxis : {
								categories : [ 'rec(9)', 'rec(2)', 'cost',
										'rec(10)', 'rec(5)' ]
							},
							yAxis : {
								// min: 0,
								title : {
									text : "'Costs comparison' (in Euro)"
								}
							},
							tooltip : {
								shared : true,
								useHTML : true,
								headerFormat : '<big><u><b>{point.key}</b></u></big><table>',
								pointFormat : '<tr>'
										+ '<td style="color:{series.color}">{series.name}: </td>'
										+ '<td style="text-align: right"><b>{point.options.y:,.2f}</b></td>'
										+ '</tr>',
								footerFormat : '</table>',
								valueDecimals : 2
							},
							legend : {
								backgroundColor : '#FFFFFF',
								reversed : true
							},
							plotOptions : {
								series : {
									stacking : 'normal'
								}
							},
							//	series: stackedSeries[i]
							series : [ {
								name : 'John',
								data : [ 5, 3, 4, 7, 2 ]
							}, {
								name : 'Jane',
								data : [ 2, 2, 3, 2, 1 ]
							}, {
								name : 'Joe',
								data : [ 3, 4, 4, 2, 5 ]
							} ]
						});
	});
</script>