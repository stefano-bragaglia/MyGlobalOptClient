<div id="graphCosts" class="text-center"
	style="min-width: 640px; width: 860px; height: 550px; margin: 0 auto"></div>

<script type="text/javascript">
	$(function() {
		var titleLang = "Costs per source (in Eur)";
		if ($('#inputLanguage').val() == "it")
			titleLang = "Costi per tipologia (in Eur)";
		$('#graphCosts').highcharts( {
							chart : { type : 'bar' },
							title : { text : '' },
							credits : { enabled : false },
							xAxis : { categories : <%=request.getAttribute("categories")%> },
							yAxis : { title : { text : titleLang } },
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
							legend : { backgroundColor : '#FFFFFF', reversed : true },
							plotOptions : { series : { stacking : 'normal' } },
							series : <%=request.getAttribute("costs.series")%>
					});
	});
</script>
