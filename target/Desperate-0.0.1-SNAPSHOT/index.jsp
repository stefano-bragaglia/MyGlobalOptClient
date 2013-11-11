<%@ page language="java" import="java.util.*" session="true"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%! String times = null; %>
<% times = (String) session.getAttribute("times"); %>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<title>ePolicy Global Optimization module testing page</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="description"
	content="Energy Scenario Evaluation for ePolicy project">
<meta name="author" content="Stefano Bragaglia">

<link href="css/bootstrap.min.css" rel="stylesheet">
<style type="text/css">
body {
	padding-top: 60px;
	padding-bottom: 40px;
}

.sidebar-nav {
	padding: 9px 0;
}

.tab-pane-border {
	border-left: 1px solid #ddd;
	border-right: 1px solid #ddd;
	border-bottom: 1px solid #ddd;
	padding: 10px;
}

.nav-tabs-border {
	margin-bottom: 0;
}

.tab-pane-welled {
	background-color: #FFFFFF;
}

@media ( max-width : 980px) {
	/* Enable use of floated navbar text */
	.navbar-text.pull-right {
		float: none;
		padding-left: 5px;
		padding-right: 5px;
	}
}
</style>
<link href="css/bootstrap-responsive.css" rel="stylesheet">

<!-- HTML5 shim, for IE6-8 support of HTML5 elements -->
<!--[if lt IE 9]>
<script src="js/html5shiv.js"></script>
<![endif]-->

<script type="text/javascript" src="js/jquery-2.0.3.min.js"></script>
<script type="text/javascript" src="js/bootstrap.min.js"></script>
<script type="text/javascript" src="js/highcharts.js"></script>
<script type="text/javascript" src="js/highcharts-more.js"></script>
<script type="text/javascript" src="js/modules/exporting.js"></script>

</head>

<body>
	<!-- Fixed navbar -->
	<%@include file="includes/navbar.jsp"%>
	<!-- Page container -->
	<div class="tab-content">
		<div class="tab-pane active" id="welcome">
			<%@include file="includes/welcome.jsp"%>
		</div>
		<div class="tab-pane" id="query">
			<%@include file="includes/query.jsp"%>
		</div>
		<div class="tab-pane" id="results">
			<%@include file="includes/results.jsp"%>
		</div>
	</div>
	<!-- Footer -->
	<%@ include file="includes/footer.jsp"%>
</body>

</html>