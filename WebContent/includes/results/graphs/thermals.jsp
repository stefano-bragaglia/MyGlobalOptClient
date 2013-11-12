<div id="graphThermals" class="text-center"
	style="min-width: 640px; width: 860px; height: 550px; margin: 0 auto"></div>

<script type="text/javascript">
	$(function() {
		var titleLang = "Thermal energy per source (in ktoe)";
		if ($('#inputLanguage').val() == "it")
			titleLang = "Energia termica per tipologia (in ktoe)";
		$('#graphThermals')
				.highcharts(
						{
							chart : {
								type : 'bar'
							},
							title : {
								text : ''
							},
							credits : {
								enabled : false
							},
							xAxis : {
								categories : <%=request.getAttribute("thermals.categories")%>
							},
							yAxis : {
								// min: 0,
								title : {
									text : titleLang
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
							series : <%=request.getAttribute("thermals.series")%>
						});
	});
</script>
