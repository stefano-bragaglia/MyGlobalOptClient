<%@ page import="model.Wrapper"%>
<script type="text/javascript">
	function check() {
		var res1 = checkLanguage();
		var res2 = checkConstraints();
		var res3 = checkFunctions();
		var res4 = checkScenario();
		return res1 && res2 && res3 && res4;
	}
	function load() {
<%out.println(Wrapper.MAP_ENG_ITA);%>
	var constrs = 'min_max_source("Thermoelectric Biomass Plants",735,2940)\n'
				+ 'min_max_source("Photovoltaic Plants",885,4540)\n'
				+ 'min_max_source("Methane-based Thermoelectric Plants",0,0)\n'
				+ 'min_max_source("Oil-based Thermoelectric Plants",0,0)\n'
				+ 'min_max_source("Carbon-based Thermoelectric Plants",0,0)\n'
				+ 'min_max_source("Big Hydroelectric Plants",0,0)\n'
				+ 'min_max_source("Small Hydroelectric Plants",15,60)\n'
				+ 'min_max_source("Windmill Electrical Generators",115,560)\n'
				+ 'min_max_source("Thermodynamic Solar Plants",15,60)\n'
				+ 'min_max_source("Superficial Geothermal Plants",0,0)\n'
				+ 'min_max_source("Thermoelectric Biomass Plants",0,0)\n'
				+ 'min_max_source("Solar Thermal Panels",0,0)';
		var functs = 'max(electric)\n' + 'min(cost)\n' + 'max(rec(9))';
		if ($('#inputLanguage').val() == 'it') {
			jQuery.each(sources, function(eng, ita) {
				constrs = constrs.split(eng).join(ita);
			});
			functs = functs.split('cost').join('costo');
			functs = functs.split('electric').join('qwerty');
			functs = functs.split('rec').join('ric');
			functs = functs.split('qwerty').join('elettrica');
			functs = functs.split('thermic').join('termica');
		}
		$('#inputConstraints').val(constrs);
		$('#inputFunctions').val(functs);
		$('#inputScenarios').val("5");
		check();
	}
	function compute() {
		// alert('Message: ' + $("#inputLanguage").val());
		if (check()) {
			$('#modalProgress').modal('show');
			$.post("/Desperate/servlet", {
				locale : $("#inputLanguage").val(),
				constraints : $("#inputConstraints").val(),
				functions : $("#inputFunctions").val(),
				scenarios : $("#inputScenarios").val()
			}, function(result) {
				$('#content').html(result);
				$('#navtabQuery').removeClass('active');
				$('#query').removeClass('active');
				$('#navtabResults').addClass('active');
				$('#results').addClass('active');
				$('#modalProgress').modal('hide');
			}, "html");
		}
	}
</script>
<div class="control-group">
	<div class="controls pull-right">
		<button type="button" class="btn btn-info" id="inputLoad"
			onclick="load()">
			Load 'Emilia Romagna'&nbsp;<i class="icon-globe"></i>
		</button>
		<button type="button" class="btn btn-success" id="inputCompute"
			onclick="compute()">
			Compute scenarios&nbsp;<i class="icon-play"></i>
		</button>
	</div>
</div>
