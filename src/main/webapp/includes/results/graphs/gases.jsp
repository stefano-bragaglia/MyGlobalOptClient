<div id="graphGases" class="text-center"
	style="min-width: 640px; width: 860px; height: 550px; margin: 0 auto"></div>

<script type="text/javascript">
	$(function() {
		var titleLang = "Pollutants";
		if ($('#inputLanguage').val() == "it")
			titleLang = "Inquinanti";
		$('#graphGases').highcharts( {
            chart: {
                type: 'column',
                backgroundColor: '#FFFFFF'
            },
            credits: { enabled: false },
            title: { text: '' },
            xAxis: { categories: <%=request.getAttribute("categories")%> },
            yAxis: {
                min: 0,
                title: { text: titleLang }
            },
            tooltip: {
				headerFormat : '<big><u><b>{point.key}</b></u></big><table>',
                pointFormat: '<tr><td style="color:{series.color}">{series.name}:&nbsp;</td>' +
                    '<td style="text-align: right"><b>{point.options.y:,.2f}</b></td>' +
                    '<td style="text-align: left">&nbsp;<b>{point.options.unit}</b></td></tr>',
    			footerFormat : '</table>',
                shared: true,
                useHTML: true
            },
            plotOptions: {
                column: { pointPadding: 0.2, borderWidth: 0 }
            },
            series: <%=request.getAttribute("gases.series")%>
        });
    });
</script>
