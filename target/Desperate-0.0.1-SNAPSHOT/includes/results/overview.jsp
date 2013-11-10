
<div class="tabbable">
	<ul class="nav nav-tabs nav-tabs-border">
		<li class="active"><a href="#comparison" data-toggle="tab">Scenario comparison</a></li>
		<li><a href="#costs" data-toggle="tab">Costs summary</a></li>
		<li><a href="#electric" data-toggle="tab">Electric outcome</a></li>
		<li><a href="#thermal" data-toggle="tab">Thermal outcome</a></li>
	</ul>
	<div class="tab-content">
		<div class="tab-pane tab-pane-welled tab-pane-border active" id="comparison">
			<%@include file="graphs/comparison.jsp"%>
		</div>
		<div class="tab-pane tab-pane-welled tab-pane-border" id="costs">
			<%@include file="graphs/costs.jsp"%>
		</div>
		<div class="tab-pane tab-pane-welled tab-pane-border" id="electric">
			<p>Howdy, I'm in Section 3.</p>
		</div>
		<div class="tab-pane tab-pane-welled tab-pane-border" id="thermal">
			<p>Howdy, I'm in Section 4.</p>
		</div>
	</div>
</div>
