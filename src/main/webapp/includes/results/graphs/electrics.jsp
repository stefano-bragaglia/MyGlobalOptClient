<div id="graphElectrics" class="text-center"
	style="min-width: 640px; width: 860px; height: 550px; margin: 0 auto"></div>

<script type="text/javascript">
	$(function() {
		var titleLang = "Electric energy per source (in kTOE)";
		if ($('#inputLanguage').val() == "it")
			titleLang = "Energia elettrica per tipologia (in kTOE)";
		$('#graphElectrics').highcharts( {
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
							series : <%=request.getAttribute("electrics.series")%>
						});
	});
</script>
