<%@page import="globalopt.ws.model.Wrapper"%>
<script type="text/javascript">
	function minus() {
		var input = $('#inputScenarios');
		var value = parseInt(input.val());
		if (value > 0)
			input.val(value - 1);
		if (input.val() == "NaN")
			input.val(0);
		checkScenario();
	}
	function plus() {
		var input = $('#inputScenarios');
		input.val(parseInt(input.val()) + 1);
		if (input.val() == "NaN")
			input.val(1);
		checkScenario();
	}
	function checkScenario() {
		var input = $('#inputScenarios');
		var value = parseInt(input.val());
		$('#groupScenario').removeClass("error");
		$('#groupScenario').removeClass("warning");
		$('#groupScenario').removeClass("success");
		if (isNaN(value) || value < 0 || value != input.val()) {
			$('#groupScenario').addClass("error");
			$('#helpScenario').text("Please enter a positive integer value...");
			return false;
		} else if (value > 5) {
			$('#groupScenario').addClass("warning");
			$('#helpScenario').text("Computation may take a while...");
			return true;
		} else {
			// $('#groupScenario').addClass("success");
			$('#helpScenario').text("");
			return true;
		}
	}
</script>

<div class="control-group" id="groupScenario">
	<label class="control-label" for="inputScenarios">No of
		scenarios</label>
	<div class="controls">
		<div class="input-prepend input-append input-xlarge">
			<button class="btn" type="button" id="inputMinus" onclick="minus()">
				<i class="icon-minus"></i>
			</button>
			<input class="input-block-level" type="text" id="inputScenarios"
				onchange="checkScenario()" placeholder="i.e.: 3">
			<button class="btn" type="button" id="inputPlus" onclick="plus()">
				<i class="icon-plus"></i>
			</button>
		</div>
		<small><span class="help-inline" id="helpScenario"></span></small>
	</div>
</div>
