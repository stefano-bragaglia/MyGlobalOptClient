<%@page import="globalopt.ws.model.Proxy"%>
<div id="footer">
	<div class="container">
		<hr />
		<p class="muted credit text-center">
			<small>You are running the <strong><%=Proxy.getInstance().getVersion()%>
				version</strong> of the application.
			</small>
		</p>
	</div>
</div>