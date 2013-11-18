<script type="text/javascript">
	function query() {
		$('#navtabWelcome').removeClass('active');
		$('#welcome').removeClass('active');
		$('#navtabQuery').addClass('active');
		$('#query').addClass('active');
	}
</script>
<div class="container-fluid">
	<div class="row-fluid">
		<div class="span8 offset2">
			<div class="hero-unit text-center">
				<img src="img/logo.png" alt="ePolicy" width="400px" />
				<h1>Energy Scenario Evaluation</h1>
				<p class="lead">
					This page is for <strong>testing purposes only</strong>.
				</p>
				<hr />
				<p>
					This web application is an interface to more easily query the
					Global Optimiser component that was implemented for the <strong>ePolicy</strong>
					project. Once queried, this interface presents the computed data in
					forms of graphs and interactive tables to let the user evaluate the
					possible alternative scenarios for the objective functions to
					optimise.
				</p>
				<p>
					<a href="#" onclick="query()"
						class="btn btn-primary btn-large push" data-toggle="tab">Query
						the optimiser &raquo;</a>
				</p>
			</div>
		</div>
	</div>
</div>

<!-- Example row of columns -->
<!--div class="row-fluid">
	<div class="span4 offset2">
		<h2>Heading</h2>
		<p>Donec id elit non mi porta gravida at eget metus. Fusce
			dapibus, tellus ac cursus commodo, tortor mauris condimentum nibh, ut
			fermentum massa justo sit amet risus. Etiam porta sem malesuada magna
			mollis euismod. Donec sed odio dui.</p>
		<p>
			<a class="btn" href="#">View details &raquo;</a>
		</p>
	</div>
	<div class="span4">
		<h2>Heading</h2>
		<p>Donec id elit non mi porta gravida at eget metus. Fusce
			dapibus, tellus ac cursus commodo, tortor mauris condimentum nibh, ut
			fermentum massa justo sit amet risus. Etiam porta sem malesuada magna
			mollis euismod. Donec sed odio dui.</p>
		<p>
			<a class="btn" href="#">View details &raquo;</a>
		</p>
	</div>
</div-->
