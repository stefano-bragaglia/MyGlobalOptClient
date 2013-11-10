<%@ page import="java.util.Locale" %>
<%@ page import="model.Wrapper" %>
<script type="text/javascript"> 
	function checkLanguage() {
		<% out.println(Wrapper.MAP_ENG_ITA); %>
		$('#groupLanguage').removeClass("error");
		$('#groupLanguage').removeClass("warning");
		$('#groupLanguage').removeClass("success");
		switch ($('#inputLanguage').val()) {
		case "en":
			// $('#groupLanguage').addClass("success");
			// $('#helpLanguage').text("Please fill in the form using English terms...");
			var str = $('#inputConstraints').val();
			jQuery.each(sources, function(eng, ita) {
				str = str.split(ita).join(eng);
			});
			$('#inputConstraints').val(str);
			str = $('#inputFunctions').val();
			str = str.split('ric(').join('rec(');
			$('#inputFunctions').val(str);
			$('#helpSources').html(<% out.print("\"" + Wrapper.getInstance().listSources(Wrapper.ENG) + "\""); %>);
			$('#helpReceptors').html(<% out.print("\"" + Wrapper.getInstance().listReceptors(Wrapper.ENG) + "\""); %>);
			return true;
			break;
		case "it":
			// $('#groupLanguage').addClass("success");
			// $('#helpLanguage').text("Please fill in the form using Italian terms...");
			var str = $('#inputConstraints').val();
			jQuery.each(sources, function(eng, ita) {
				str = str.split(eng).join(ita);
			});
			$('#inputConstraints').val(str);
			str = $('#inputFunctions').val();
			str = str.split('rec(').join('ric(');
			$('#inputFunctions').val(str);
			$('#helpSources').html(<% out.print("\"" + Wrapper.getInstance().listSources(Wrapper.ITA) + "\""); %>);
			$('#helpReceptors').html(<% out.print("\"" + Wrapper.getInstance().listReceptors(Wrapper.ITA) + "\""); %>);
			return true;
			break;
		default:
			$('#groupLanguage').addClass("error");
			$('#helpLanguage').text("Something wrong has happened...");
			return false;
		}
	}
</script>
<div class="control-group" id="groupLanguage">
	<label class="control-label" for="inputLanguage">Language</label>
	<div class="controls">
		<select class="input-block-level" id="inputLanguage"
			onchange="checkLanguage()">
			<option value="en" selected="selected">(en_US) English</option>
			<option value="it">(it_IT) Italiano</option>
		</select><small><span class="help-inline" id="helpLanguage"></span></small>
	</div>
</div>
