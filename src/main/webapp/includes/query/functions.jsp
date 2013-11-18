<script type="text/javascript">
	function checkFunctions() {
		var value = $('#inputFunctions').val();
		$('#groupFunctions').removeClass("error");
		$('#groupFunctions').removeClass("warning");
		$('#groupFunctions').removeClass("success");
		if (value == null || value == "") {
			$('#groupFunctions').addClass("error");
			$('#helpFunctions').text("At least an objective function is required...");
			return false;
		} else if (false) {
			$('#groupFunctions').addClass("warning");
			$('#helpFunctions').text("Add any additional objective function...");
			return true;
		} else {
			// $('#groupFunctions').addClass("success");
			$('#helpFunctions').text("");
			return true;
		}
	}
</script>
<div class="control-group" id="groupFunctions">
	<label class="control-label" for="inputFunctions">Objective
		functions</label>
	<div class="controls">
		<textarea class="input-block-level" id="inputFunctions" rows="5"
			placeholder="i.e.: max(electric)" onchange="checkFunctions()"></textarea>
		<small><span class="help-inline" id="helpFunctions"></span></small>
	</div>
	<small><span class="help-inline" id="helpFunctions"></span></small>
</div>
