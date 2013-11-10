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
						<li><small><i>min_max_source(Source, MinValue,
									MaxValue)</i></small></li>
						<li><small><i>cost &gt;= electric</i></small></li>
					</ul> where <i>Source</i> is the string for a <i>source</i> (see below)</li>
				<li><strong>Objective functions:</strong> list one or more
					objectives to optimise
					<ul>
						<li><small><i>max(Term)</i> or <i>min(Term)</i></small></li>
					</ul> where <i>Term</i> is either <i>cost</i>, <i>electric</i>, <i>thermal</i>
					or the key for a <i>receptor</i> (see below)</li>
				<li><strong>No of scenarios:</strong> tell the amount of
					desired scenarios to compare</li>
			</ol>

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