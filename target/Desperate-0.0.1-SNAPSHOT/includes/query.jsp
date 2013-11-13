<%@ page import="model.Wrapper"%>

<div class="container-fluid">
	<div class="row-fluid">

		<div class="span6">
			<div class="well">
				<!-- p>Form section.</p -->
				<form class="form-horizontal" method="post">
					<!-- action="/Desperate/servlet"-->
					<fieldset>
						<legend>Energy Scenario Evaluation</legend>
						<%@include file="query/language.jsp"%>
						<%@include file="query/constraints.jsp"%>
						<%@include file="query/functions.jsp"%>
						<%@include file="query/scenario.jsp"%>
						<hr />
						<%@include file="query/buttons.jsp"%>
						<div id="modalProgress" class="modal hide fade" tabindex="-1"
							data-backdrop="static" data-keyboard="false">

							<div class="modal-header">
								<h3 id="myModalLabel">Progress</h3>
							</div>
							<div class="modal-body">
								<p>Please wait while the application computes the desired
									scenarios...</p>
								<div class="progress progress-striped active" id="progressBar">
									<div class="bar" style="width: 100%;"></div>
								</div>
							</div>
							<div class="modal-footer">
								<!-- button class="btn" data-dismiss="modal" aria-hidden="true">Close</button>
								<button class="btn btn-primary">Save changes</button -->
							</div>
						</div>
					</fieldset>
				</form>
			</div>
		</div>

		<div class="span6">
			<p class="lead">Instructions</p>
			<ol>
				<li><strong>Language:</strong> select the language to use in
					the query<br /> <small>(please note that only English and
						Italian are currently supported)</small></li>
				<li><strong>Constraints:</strong> provide all the appropriate
					constraints on the domain
					<ul>
						<li><small><i><span class="text-success">min_max_source(Source,
										MinValue, MaxValue)</span></i></small></li>
						<li><small>
							<i><span class="text-success" id="helpCost1">cost</span> <span class="text-success">&gt;=</span> 
							   <span class="text-success" id="helpElectric1">electric</span></i> 
							   (or similar)</small></li>
					</ul> <small>where <i>Source</i> is the key for a <span class="text-info">source</span>
						(see the references below), 
						<i><span class="text-success" id="helpCost2">cost</span></i>,
						<i><span class="text-success" id="helpElectric2">electric</span></i>
						or <i><span class="text-success" id="helpThermal2">thermal</span></i></small>
				</li>
				<li><strong>Objective functions:</strong> list one or more
					objectives to optimise
					<ul>
						<li><small><i><span class="text-success">max(Term)</span></i>
								or <i><span class="text-success">min(Term)</span></i></small></li>
					</ul> <small>where <i>Term</i> is either <i><span
							class="text-success" id="helpCost3">cost</span></i>, <i><span
							class="text-success" id="helpElectric3">electric</span></i>, <i><span
							class="text-success" id="helpThermal3">thermal</span></i> or the key
						for a <span class="text-info">receptor</span> (see the references below)</li>
				</small>
				<li><strong>No of scenarios:</strong> tell the amount of
					desired scenarios to compare</li>
			</ol>
			<div class="alert alert-warning">
				<button type="button" class="close" data-dismiss="alert">&times;</button>
				<small><strong>Warning!</strong> No syntax or semantic check
					is performed on constraints and objective functions.</small>
			</div>
			<hr />
			<p class="lead">References</p>
			<div class="tabbable">
				<ul class="nav nav-tabs nav-tabs-border">
					<li class="active"><a href="#sourceList" data-toggle="tab">Sources</a></li>
					<li><a href="#receptorList" data-toggle="tab">Receptors</a></li>
				</ul>
				<div class="tab-content">
					<div class="tab-pane tab-pane-border active" id="sourceList">
						<p>
							The following <strong>sources</strong> are available:
						</p>
						<div class="row-fluid help-inline" id="helpSources">
							<%=Wrapper.getInstance().listSources()%>
						</div>
					</div>
					<div class="tab-pane tab-pane-border" id="receptorList">
						<p>
							The following <strong>receptors</strong> are available:
						</p>
						<div class="row-fluid help-inline" id="helpReceptors">
							<%=Wrapper.getInstance().listReceptors()%>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>