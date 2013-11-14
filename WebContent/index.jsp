<%@ page language="java" import="java.util.*" session="true"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

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
<link href="css/theme.bootstrap.css" rel="stylesheet">
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

.table th.text-right,.table td.text-right {
	text-align: right;
}
</style>
<link href="css/bootstrap-responsive.css" rel="stylesheet">

<!-- HTML5 shim, for IE6-8 support of HTML5 elements -->
<!--[if lt IE 9]>
<script src="js/html5shiv.js"></script>
<![endif]-->

<script type="text/javascript" src="js/jquery-2.0.3.min.js"></script>
<script type="text/javascript" src="js/jquery.tablesorter.min.js"></script>
<script type="text/javascript" src="js/jquery.tablesorter.widgets.min.js"></script>
<script type="text/javascript" src="js/bootstrap.min.js"></script>
<script type="text/javascript" src="js/highcharts.js"></script>
<script type="text/javascript" src="js/highcharts-more.js"></script>
<script type="text/javascript" src="js/modules/exporting.js"></script>
<script type="text/javascript">
	function tablesort(table) {
		$.extend($.tablesorter.themes.bootstrap, {
			// these classes are added to the table. To see other table classes available,
			// look here: http://twitter.github.com/bootstrap/base-css.html#tables
			table : 'table table-condensed table-bordered',
			header : 'bootstrap-header info', // give the header a gradient background
			footerRow : '',
			footerCells : '',
			icons : '', // add "icon-white" to make them white; this icon class is added to the <i> in the header
			sortNone : 'bootstrap-icon-unsorted',
			sortAsc : 'icon-chevron-up glyphicon glyphicon-chevron-up', // includes classes for Bootstrap v2 & v3
			sortDesc : 'icon-chevron-down glyphicon glyphicon-chevron-down', // includes classes for Bootstrap v2 & v3
			active : '', // applied when column is sorted
			hover : '', // use custom css here - bootstrap class may not override it
			filterRow : '', // filter row class
			even : '', // odd row zebra striping
			odd : '' // even row zebra striping
		});
		table.tablesorter({
			// this will apply the bootstrap theme if "uitheme" widget is included
			// the widgetOptions.uitheme is no longer required to be set
			theme : "bootstrap",
			widthFixed : true,
			headerTemplate : '{content} {icon}', // new in v2.7. Needed to add the bootstrap icon!
			// widget code contained in the jquery.tablesorter.widgets.js file
			// use the zebra stripe widget if you plan on hiding any rows (filter widget)
			widgets : [ "uitheme", "filter", "zebra" ],
			widgetOptions : {
				// using the default zebra striping class name, so it actually isn't included in the theme variable above
				// this is ONLY needed for bootstrap theming if you are using the filter widget, because rows are hidden
				zebra : [ "even", "odd" ],
				// reset filters button
				filter_reset : ".reset"
			// set the uitheme widget to use the bootstrap theme class names
			// this is no longer required, if theme is set
			// ,uitheme : "bootstrap"
			}
// 		}).tablesorterPager({
// 			// target the pager markup - see the HTML block below
// 			container : $(".ts-pager"),
// 			// target the pager page select dropdown - choose a page
// 			cssGoto : ".pagenum",
// 			// remove rows from the table to speed up the sort of large tables.
// 			// setting this to false, only hides the non-visible rows; needed if you plan to add/remove rows with the pager enabled.
// 			removeRows : false,
// 			// output string - default is '{page}/{totalPages}';
// 			// possible variables: {page}, {totalPages}, {filteredPages}, {startRow}, {endRow}, {filteredRows} and {totalRows}
// 			output : '{startRow} - {endRow} / {filteredRows} ({totalRows})'
		});
	}
</script>
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