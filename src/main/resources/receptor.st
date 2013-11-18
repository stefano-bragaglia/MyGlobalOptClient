<div id="graphReceptor?tag?" style="width: 300px; height: 200px; margin: 0 auto"></div>

<script type="text/javascript">
$(function () {
	$('#graphReceptor?tag?').highcharts({
	    chart: {
	        type: 'gauge',
	        plotBorderWidth: 1,
	        plotBackgroundColor: {
	        	linearGradient: { x1: 0, y1: 0, x2: 0, y2: 1 },
	        	stops: [[0, '#E8E8E8'], [0.3, '#FFFFFF'], [1, '#E8E8E8']]
	        },
	        plotBackgroundImage: null,
	        height: 200
	    },
        credits: { enabled : false },
	    pane: [{
	        startAngle: -45, endAngle: 45,
	        background: null,
	        center: ['50%', '145%'],
	        size: 300
	    }],	    		        
	    plotOptions: {
	    	gauge: {
	    		dataLabels: { enabled: false },
	    		dial: { radius: '100%' }
	    	}
	    },

	    title: { text: '?title?' },
	    yAxis: [{
	        min: ?min?, max: ?max?,
	        minorTickPosition: 'outside', tickPosition: 'outside',
	        labels: { rotation: 'auto', distance: 20 },
	        plotBands: [{
	        	from: ?min?, to: ?left?,
	        	color: '#C02316', innerRadius: '100%', outerRadius: '105%'
	        }, {
	        	from: ?right?, to: ?max?,
	        	color: '#16C023', innerRadius: '100%', outerRadius: '105%'
	        }],
	        pane: 0,
	        title: {
	        	text: '<br/><span style="font-size:8px">?title?</span>',
	        	y: -40
	        }
	    }],
	    series: [{ data: [?value?], yAxis: 0 }]
	});
});
</script>