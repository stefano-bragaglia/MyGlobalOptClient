<%@ page import="model.Wrapper"%>
<script type="text/javascript">
	function repeat() {
		$('#navtabResults').removeClass('active');
		$('#results').removeClass('active');
		$('#navtabQuery').addClass('active');
		$('#query').addClass('active');
	}
</script>

<div class="container-fluid" id="content">
	<div class="row-fluid">
		<div class="span3">
			<div class="well sidebar-nav">
				<ul class="nav nav-list">
					<li class="nav-header">Scenarios comparison</li>
					<li class="active"><a href="#overview" data-toggle="tab">General
							overview</a></li>
					<li><a href="#objectives" data-toggle="tab">Objective
							functions</a></li>
					<li><a href="#emissions" data-toggle="tab">Emissions and
							pollutants</a></li>
					<li class="nav-header">Boundary scenarios</li>
					<li><a href="#scenario" data-toggle="tab">No scenario yet
							available</a></li>
					<li class="nav-header">Intermediate scenarios</li>
					<li><a href="#scenario" data-toggle="tab">No scenario yet
							available</a></li>
				</ul>
			</div>
			<div class="text-right">
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
						<hr />
						<p>At the moment, no query has been carried out yet.</p>
						<p>Please return to this page after you have requested at
							least one to see the results and the scenarios computed for the
							last query.</p>
					</div>
				</div>

				<div class="tab-pane" id="objectives">
					<div class="well">
						<h4>Objective functions</h4>
						<hr />
						<p>At the moment, no query has been carried out yet.</p>
						<p>Please return to this page after you have requested at
							least one to see the results and the scenarios computed for the
							last query.</p>
					</div>
				</div>

				<div class="tab-pane" id="emissions">
					<div class="well">
						<h4>Emissions and pollutants</h4>
						<hr />
						<p>At the moment, no query has been carried out yet.</p>
						<p>Please return to this page after you have requested at
							least one to see the results and the scenarios computed for the
							last query.</p>
					</div>
				</div>
				<div class="tab-pane" id="scenario">
					<div class="well well-small">
						<h4>No scenario yet available</h4>
						<hr />
						<p>At the moment, no query has been carried out yet.</p>
						<p>Please return to this page after you have requested at
							least one to see the results and the scenarios computed for the
							last query.</p>
					</div>
				</div>
			</div>
		</div>
		<!--/span-->
	</div>
	<!--/row-->
</div>