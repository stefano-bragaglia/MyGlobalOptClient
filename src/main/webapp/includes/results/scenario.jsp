<%@page import="globalopt.ws.model.Solution"%>
<%@page import="globalopt.ws.model.Wrapper"%>
<%
	int index = (Integer) request.getAttribute("scenario.index");
	Solution solution = (Solution) request.getAttribute("solution");
%>

<script type="text/javascript">
	<% out.println(solution.getVUMeter(index)); %>
	function showReceptors<%=index%>() {
		<% out.println(Wrapper.MAP_RECEPTORS); %>
        var selectedOption = $('#receptorsList<%=index%> option:selected');
        var selectedValue = parseFloat(selectedOption.val());
        var selectedText = selectedOption.text();
    	var chart = $('#graphReceptors<%=index%>').highcharts();
        chart.yAxis[0].update({
            title: {
				text: receptors[selectedText] + '<br/><span style="font-size:8px">' + selectedText + '</span>',
				y : -40
			}
        });
        chart.series[0].data[0].update(selectedValue);
        chart.setTitle({
            text: selectedText
        });
	}
</script>

<div class="tabbable">
	<ul class="nav nav-tabs nav-tabs-border">
		<li class="active"><a href="#receptors<%=index%>"
			data-toggle="tab">Receptors</a></li>
		<li><a href="#sources<%=index%>" data-toggle="tab">Energy Sources</a></li>
		<li><a href="#actions<%=index%>" data-toggle="tab">Costs per Action</a></li>
		<li><a href="#costs<%=index%>" data-toggle="tab">Detailed Costs</a></li>
		<li><a href="#emit<%=index%>" data-toggle="tab">Emissions and Pollutants</a></li>
	</ul>
	<div class="tab-content">
		<div class="tab-pane tab-pane-welled tab-pane-border active"
			id="receptors<%=index%>">
			<% out.println(solution.getStrongRecs(index)); %>
			<div class="row-fluid">
				<div class="span12 text-center">
					<div id="graphReceptors<%=index%>"
						style="width: 600px; height: 300px; margin: 0 auto"></div>
					<br/><%=solution.getSelect(index)%>
				</div>
			</div>
			<% out.println(solution.getWeakRecs(index)); %>
		</div>
		<div class="tab-pane tab-pane-welled tab-pane-border"
			id="sources<%=index%>">
			<%=solution.getTableSources(index)%>
		</div>
		<div class="tab-pane tab-pane-welled tab-pane-border"
			id="actions<%=index%>">
			<%=solution.getTableActions(index)%>
		</div>
		<div class="tab-pane tab-pane-welled tab-pane-border"
			id="costs<%=index%>">
			<%=solution.getTableCosts(index)%>
		</div>
		<div class="tab-pane tab-pane-welled tab-pane-border"
			id="emit<%=index%>">
			<%=solution.getTableEmissions(index)%>
		</div>
	</div>
</div>
