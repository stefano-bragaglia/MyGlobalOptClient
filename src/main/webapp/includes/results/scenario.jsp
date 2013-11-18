<%@page import="globalopt.ws.model.Solution"%>
<%
	int index = (Integer) request.getAttribute("scenario.index");
	Solution solution = (Solution) request.getAttribute("solution");
%>

<div class="tabbable">
	<ul class="nav nav-tabs nav-tabs-border">
		<li class="active"><a href="#receptors<%=index%>" data-toggle="tab">Receptors</a></li>
		<li><a href="#sources<%=index%>" data-toggle="tab">Energy Sources</a></li>
		<li><a href="#actions<%=index%>" data-toggle="tab">Costs per Action</a></li>
		<li><a href="#costs<%=index%>" data-toggle="tab">Detailed Costs</a></li>
		<li><a href="#emit<%=index%>" data-toggle="tab">Emissions and Pollutants</a></li>
	</ul>
	<div class="tab-content">
		<div class="tab-pane tab-pane-welled tab-pane-border active" id="receptors<%=index%>">
			<p>This is a placeholder for a graph. Either the content and the tab label are temporary.</p> 
		</div>
		<div class="tab-pane tab-pane-welled tab-pane-border" id="sources<%=index%>">		
			<%=solution.getTableSources(index)%>
		</div>
		<div class="tab-pane tab-pane-welled tab-pane-border" id="actions<%=index%>">
			<%=solution.getTableActions(index)%>
		</div>
		<div class="tab-pane tab-pane-welled tab-pane-border" id="costs<%=index%>">
			<%=solution.getTableCosts(index)%>
		</div>
		<div class="tab-pane tab-pane-welled tab-pane-border" id="emit<%=index%>">
			<%=solution.getTableEmissions(index)%>
		</div>
	</div>
</div>
