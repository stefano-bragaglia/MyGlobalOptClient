<%@page import="globalopt.ws.model.service.Objective"%>
<%!Objective[] objectives = null;%>
<div class="tabbable">
	<ul class="nav nav-tabs nav-tabs-border">
		<%
			objectives = (Objective[]) request.getAttribute("objectives");
			for (int o = 0; o < objectives.length; o++)
				out.println(String.format("\t\t<li%s><a href=\"#function%d\" data-toggle=\"tab\">%s</a></li>",
						(0 == o ? " class=\"active\"" : ""), o + 1, objectives[o].getLabel()));
		%>
	</ul>
	<div class="tab-content">
		<%
			for (int o = 0; o < objectives.length; o++) {
				out.println(String.format(
						"\t\t<div class=\"tab-pane tab-pane-welled tab-pane-border%s\" id=\"function%d\">",
						(0 == o ? " active" : ""), o + 1));
				// out.println(String.format("\t\t<p>This is #function%d for '%s'</p>", o + 1, objectives[o].getLabel()));

				out.println(String.format("<div id=\"graphFunction%d\" class=\"text-center\" style=\"min-width: 640px; width: 860px; height: 550px; margin: 0 auto\"></div>", o + 1));

				out.println("<script type=\"text/javascript\">");
				out.println("\t$(function() {");
				out.println(String.format("\t\tvar titleLang = \"%s%s\";", objectives[o].getLabel(),
						objectives[o].hasUnit() ? " (in " + objectives[o].getUnit() + ")": ""));
				out.println(String.format("\t\t$('#graphFunction%d').highcharts( {", o + 1));
				out.println("\t\t\t\t\t\t\tchart : { type : 'bar' },");
				out.println("\t\t\t\t\t\t\ttitle : { text : '' },");
				out.println("\t\t\t\t\t\t\tcredits : { enabled : false },");
				out.println(String.format("\t\t\t\t\t\t\txAxis : { categories : %s },",
						request.getAttribute("categories")));
				out.println("\t\t\t\t\t\t\tyAxis : { title : { text : titleLang } },");
				out.println("\t\t\t\t\t\t\ttooltip : {");
				out.println("\t\t\t\t\t\t\t\tshared : true,");
				out.println("\t\t\t\t\t\t\t\tuseHTML : true,");
				out.println("\t\t\t\t\t\t\t\theaderFormat : '<big><u><b>{point.key}</b></u></big><table>',");
				out.println("\t\t\t\t\t\t\t\tpointFormat : '<tr>'");
				out.println("\t\t\t\t\t\t\t\t\t\t+ '<td style=\"color:{series.color}\">{series.name}: </td>'");
				out.println("\t\t\t\t\t\t\t\t\t\t+ '<td style=\"text-align: right\"><b>{point.options.y:,.2f}</b></td>'");
				out.println("\t\t\t\t\t\t\t\t\t\t+ '</tr>',");
				out.println("\t\t\t\t\t\t\t\tfooterFormat : '</table>',");
				out.println("\t\t\t\t\t\t\t\tvalueDecimals : 2");
				out.println("\t\t\t\t\t\t\t},");
				out.println("\t\t\t\t\t\t\tlegend : { backgroundColor : '#FFFFFF', reversed : true },");
				out.println("\t\t\t\t\t\t\tplotOptions : { series : { stacking : 'normal' } },");
				out.println(String.format("\t\t\t\t\t\t\tseries : %s", request.getAttribute("function" + (o + 1) + ".series")));
				out.println("\t\t\t\t\t});");
				out.println("\t});");
				out.println("</script>");

				out.println("\t\t</div>");
			}
		%>
	</div>
</div>



