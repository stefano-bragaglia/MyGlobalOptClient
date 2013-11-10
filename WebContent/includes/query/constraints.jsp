<script type="text/javascript">
	function checkConstraints() {
		var value = $('#inputConstraints').val();
		$('#groupConstraints').removeClass("error");
		$('#groupConstraints').removeClass("warning");
		$('#groupConstraints').removeClass("success");
		if (false) {
			$('#groupConstraints').addClass("error");
			$('#helpConstraints').text(
					"This constraints specification contains an error...");
			return false;
		} else if (value == "") {
			$('#groupConstraints').addClass("warning");
			$('#helpConstraints')
					.text(
							"Add additional domain constraints for more meaningful results...");
			return true;
		} else {
			// $('#groupConstraints').addClass("success");
			$('#helpConstraints').text("");
			return true;
		}
	}
</script>
<div class="control-group" id="groupConstraints">
	<label class="control-label" for="inputConstraints">Constraints</label>
	<div class="controls">
		<textarea class="input-block-level" id="inputConstraints" rows="7"
			placeholder="i.e.: min_max_source(&quot;Source&quot;,Min,Max)"
			onchange="checkConstraints()"></textarea>
		<small><span class="help-inline" id="helpConstraints"></span></small>
	</div>
	<small><span class="help-inline" id="helpConstraints"></span></small>
</div>
