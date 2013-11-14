<%!String[] scenarios = null;%>
<div class="row-fluid">
	<div class="span3">
		<div class="well sidebar-nav">
			<ul class="nav nav-list">
				<li class="nav-header">Scenarios comparison</li>
				<li class="active"><a href="#overview" data-toggle="tab">General overview</a></li>
				<li><a href="#objectives" data-toggle="tab">Objective functions</a></li>
				<li><a href="#emissions" data-toggle="tab">Emissions and pollutants</a></li>
				<%
					scenarios = (String[]) request.getAttribute("scenarios");
					int ol = ((Objective[]) request.getAttribute("objectives")).length;
					for (int s = 0; s < scenarios.length; s++) {
						if (0 == s)
							out.println("\t\t\t\t<li class=\"nav-header\">Boundary scenarios</li>");
						if (ol == s)
							out.println("\t\t\t\t<li class=\"nav-header\">Intermediate scenarios</li>");
						out.println(String.format("\t\t\t\t<li><a href=\"#scenario%d\" data-toggle=\"tab\">%s</a></li>", s + 1,
								scenarios[s]));
					}
				%>
			</ul>
		</div>
		<div class="text-right">
			<p>
				<small><span class="muted">Computing the scenarios
						took <strong class="text-info">${timestamp}</strong>.<br /> <strong
						class="text-info">${computations} query/ies</strong> answered during
						this session.
				</span></small>
			</p>
			<a href="#" onclick="repeat()" class="btn btn-info push"
				data-toggle="tab">New query <i class="icon-repeat"></i></a>
		</div>
		<!--/.well -->
	</div>
	<!--/span-->
	<div class="span9">
		<div class="tab-content">
			<div class="tab-pane active" id="overview">
				<div class="well">
					<h4>General overview</h4>
					<%@include file="results/overview.jsp"%>
				</div>
			</div>
			<div class="tab-pane" id="objectives">
				<div class="well">
					<h4>Objective functions</h4>
					<%@include file="results/functions.jsp"%>
				</div>
			</div>
			<div class="tab-pane" id="emissions">
				<div class="well">
					<h4>Emissions and pollutants</h4>
					<%@include file="results/emissions.jsp"%>
				</div>
			</div>
			<%
				for (int s = 0; s < scenarios.length; s++) {
					out.println(String.format("\t\t\t<div class=\"tab-pane\" id=\"scenario%d\">", s + 1));
					out.println("\t\t\t\t<div class=\"well\">");
					out.println(String.format("\t\t\t\t\t<h4>%s</h4>", scenarios[s]));
					request.setAttribute("scenario.index", 1 + s);
			%>		
					<%@include file="results/scenario.jsp"%>
			<%		
					out.println("\t\t\t\t</div>");
					out.println("\t\t\t</div>");
				}
			%>
		</div>
	</div>
	<!--/span-->
</div>
<!--/row-->