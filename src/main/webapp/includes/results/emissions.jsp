
<div class="tabbable">
	<ul class="nav nav-tabs nav-tabs-border">
		<li class="active"><a href="#metals" data-toggle="tab">Heavy metals</a></li>
		<li><a href="#gases" data-toggle="tab">Greenhouse gases</a></li>
		<li><a href="#others" data-toggle="tab">Other pollutants</a></li>
	</ul>
	<div class="tab-content">
		<div class="tab-pane tab-pane-welled tab-pane-border active" id="metals">
			<%@include file="graphs/metals.jsp"%>
		</div>
		<div class="tab-pane tab-pane-welled tab-pane-border" id="gases">
			<%@include file="graphs/gases.jsp"%> 
		</div>
		<div class="tab-pane tab-pane-welled tab-pane-border" id="others">
			<%@include file="graphs/others.jsp"%>
		</div>
	</div>
</div>
