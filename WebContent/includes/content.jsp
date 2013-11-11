
<div class="row-fluid">
	<div class="span3">
		<div class="well sidebar-nav">
			<ul class="nav nav-list">
				<li class="nav-header">Scenarios comparison</li>
				<li class="active"><a href="#overview" data-toggle="tab">General
						overview</a></li>
				<li><a href="#objectives" data-toggle="tab">Objectives
						comparison</a></li>
				<li><a href="#emissions" data-toggle="tab">Emissions and
						pollutants</a></li>
				<!--li><a href="#costs" data-toggle="tab">Costs summary</a></li-->
				<li class="nav-header">Boundary scenarios</li>
				<li><a href="#scenario1" data-toggle="tab">1. max(electric)</a></li>
				<li><a href="#scenario2" data-toggle="tab">2. min(cost)</a></li>
				<li><a href="#scenario3" data-toggle="tab">3. max(rec(9))</a></li>
				<li class="nav-header">Transitional scenarios</li>
				<li><a href="#scenario4" data-toggle="tab">4. Scenario</a></li>
				<li><a href="#scenario5" data-toggle="tab">5. Scenario</a></li>
				<li><a href="#scenario6" data-toggle="tab">6. Scenario</a></li>
				<li><a href="#scenario7" data-toggle="tab">7. Scenario</a></li>
				<li><a href="#scenario8" data-toggle="tab">8. Scenario</a></li>
			</ul>
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
					<h4>Objectives comparison</h4>
					<%@include file="results/overview.jsp"%>
				</div>
			</div>

			<div class="tab-pane" id="emissions">
				<div class="well">
					<h4>Emissions and pollutants</h4>
				</div>
			</div>
			<!--div class="tab-pane" id="thermal">
					<div class="well">
						<h4>Thermal output</h4>
					</div>
				</div>
				<div class="tab-pane" id="costs">
					<div class="well">
						<h4>Costs summary</h4>
					</div>
				</div-->
			<div class="tab-pane" id="scenario1">
				<div class="well well-small">
					<h4>Scenario n.</h4>
					<%@include file="results/scenario.jsp"%>
				</div>
			</div>
			<div class="tab-pane" id="scenario2">
				<div class="well well-small">
					<h4>Scenario n.</h4>
					<%@include file="results/scenario.jsp"%>
				</div>
			</div>
			<div class="tab-pane" id="scenario3">
				<div class="well well-small">
					<h4>Scenario n.</h4>
					<%@include file="results/scenario.jsp"%>
				</div>
			</div>
			<div class="tab-pane" id="scenario4">
				<div class="well well-small">
					<h4>Scenario n.</h4>
					<%@include file="results/scenario.jsp"%>
				</div>
			</div>
			<div class="tab-pane" id="scenario5">
				<div class="well well-small">
					<h4>Scenario n.</h4>
					<%@include file="results/scenario.jsp"%>
				</div>
			</div>
			<div class="tab-pane" id="scenario6">
				<div class="well well-small">
					<h4>Scenario n.</h4>
					<%@include file="results/scenario.jsp"%>
				</div>
			</div>
			<div class="tab-pane" id="scenario7">
				<div class="well well-small">
					<h4>Scenario n.</h4>
					<%@include file="results/scenario.jsp"%>
				</div>
			</div>
			<div class="tab-pane" id="scenario8">
				<div class="well well-small">
					<h4>Scenario n.</h4>
					<%@include file="results/scenario.jsp"%>
				</div>
			</div>
		</div>
		<div class="row-fluid">
			<div class="span10">
				<p class="muted text-right">
					<small>The service has been queried <strong
						class="text-info">${computations} time(s)</strong> during this
						working session.<br /> The computations of the above scenarios
						took <strong class="text-info">${timestamp}</strong>.
					</small>
				</p>
			</div>
			<div class="span2 text-right">
				<a href="#" onclick="repeat()" class="btn btn-info push"
					data-toggle="tab">New query <i class="icon-repeat"></i></a>
			</div>
		</div>
	</div>
	<!--/span-->
</div>
<!--/row-->