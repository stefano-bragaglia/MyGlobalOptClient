<div id="graphComparison" class="text-center"
	style="min-width: 640px; max-width: 760px; height: 480px; margin: 0 auto"></div>

<script type="text/javascript">
	$(function() {
		$('#graphComparison')
				.highcharts(
						{
							chart : {
								polar : true,
								type : 'line'
							},
							title : {
								text : 'Pareto-optimal plans for given objectives',
								x : -80
							},
							pane : {
								size : '80%'
							},
							credits : {
								enabled : false
							},
							xAxis : {
								categories : [ 'rec(9)', 'rec(2)', 'cost',
										'rec(10)', 'rec(5)' ],
								tickmarkPlacement : 'on',
								lineWidth : 0
							},
							yAxis : {
								gridLineInterpolation : 'polygon',
								lineWidth : 0,
								startOnTick : false,
								minPadding : 0.1
							},
							tooltip : {
								shared : true,
								useHTML : true,
								headerFormat : '<big><u><b>{point.key}</b></u></big><table>',
								pointFormat : '<tr>'
										+ '<td style="color:{series.color}">{series.name}: </td>'
										+ '<td style="text-align: right"><b>{point.options.ttip:,.2f} {point.options.unit}</b></td>'
										+ '</tr>',
								footerFormat : '</table>',
							// valueDecimals: 2
							},
							legend : {
								align : 'right',
								verticalAlign : 'top',
								y : 70,
								layout : 'vertical'
							},
							series : [ {
								name : 'max(rec(9))',
								data : [ {
									y : 1.0,
									ttip : 307.54340875
								}, {
									y : 0.034440171953752756,
									ttip : -1595.182889375
								}, {
									y : 0.9724749421689427,
									ttip : 5045200000.0
								}, {
									y : 0.9937086214393859,
									ttip : 831.187495625
								}, {
									y : 0.021795139861960203,
									ttip : -1664.2022475
								} ],
								pointPlacement : 'on'
							}, {
								name : 'max(rec(2))',
								data : [ {
									y : 0.0,
									ttip : -118.742185
								}, {
									y : 0.6023492392363033,
									ttip : -656.951201875
								}, {
									y : 0.35422127975216294,
									ttip : 1837700000.0
								}, {
									y : 0.2467715189985823,
									ttip : 207.163683125
								}, {
									y : 0.5963684134832621,
									ttip : -686.6911225
								} ],
								pointPlacement : 'on'
							}, {
								name : 'min(cost)',
								data : [ {
									y : 0.0,
									ttip : -118.742185
								}, {
									y : 0.6023492392363033,
									ttip : -656.951201875
								}, {
									y : 0.35422127975216294,
									ttip : 1837700000.0
								}, {
									y : 0.2467715189985823,
									ttip : 207.163683125
								}, {
									y : 0.5963684134832621,
									ttip : -686.6911225
								} ],
								pointPlacement : 'on'
							}, {
								name : 'max(rec(10))',
								data : [ {
									y : 0.9967472892109669,
									ttip : 306.156825
								}, {
									y : 0.0,
									ttip : -1652.0808375
								}, {
									y : 1.0,
									ttip : 5188000000.0
								}, {
									y : 1.0,
									ttip : 836.4435875
								}, {
									y : 0.0,
									ttip : -1701.281925
								} ],
								pointPlacement : 'on'
							}, {
								name : 'max(rec(5))',
								data : [ {
									y : 0.0,
									ttip : -118.742185
								}, {
									y : 0.6023492392363033,
									ttip : -656.951201875
								}, {
									y : 0.35422127975216294,
									ttip : 1837700000.0
								}, {
									y : 0.2467715189985823,
									ttip : 207.163683125
								}, {
									y : 0.5963684134832621,
									ttip : -686.6911225
								} ],
								pointPlacement : 'on'
							}, {
								name : 'Intp. plan 1',
								data : [ {
									y : 1.0,
									ttip : 307.54340875
								}, {
									y : 0.034440171953752756,
									ttip : -1595.182889375
								}, {
									y : 0.9724749421689427,
									ttip : 5045200000.0
								}, {
									y : 0.9937086214393859,
									ttip : 831.187495625
								}, {
									y : 0.021795139861960203,
									ttip : -1664.2022475
								} ],
								pointPlacement : 'on'
							}, {
								name : 'Intp. plan 137',
								data : [ {
									y : 0.5704992254888487,
									ttip : 124.453416071429
								}, {
									y : 0.26798972308805674,
									ttip : -1209.34015133929
								}, {
									y : 0.7113682722911735,
									ttip : 3690578596.93524
								}, {
									y : 0.6753902571249312,
									ttip : 565.250459375
								}, {
									y : 0.26181364573910354,
									ttip : -1255.86310178571
								} ],
								pointPlacement : 'on'
							}, {
								name : 'Intp. plan 274',
								data : [ {
									y : 0.4433602075147668,
									ttip : 70.2558843055556
								}, {
									y : 0.33846515201503263,
									ttip : -1092.90904569444
								}, {
									y : 0.6350606137081767,
									ttip : 3294694464.28296
								}, {
									y : 0.5808406907147001,
									ttip : 486.259630416667
								}, {
									y : 0.33373746747536265,
									ttip : -1133.50040388889
								} ],
								pointPlacement : 'on'
							}, {
								name : 'Intp. plan 410',
								data : [ {
									y : 0.3333333333333334,
									ttip : 23.3530129166667
								}, {
									y : 0.41304621680878617,
									ttip : -969.695097708334
								}, {
									y : 0.5579695157614109,
									ttip : 2894745848.21223
								}, {
									y : 0.4957505531455168,
									ttip : 415.171620625
								}, {
									y : 0.4048439889428262,
									ttip : -1012.52816416667
								} ],
								pointPlacement : 'on'
							}, {
								name : 'Intp. plan 546',
								data : [ {
									y : 0.24959341115137088,
									ttip : -12.34410953125
								}, {
									y : 0.4560669509214466,
									ttip : -898.621367265625
								}, {
									y : 0.5104730889879806,
									ttip : 2648334386.15917
								}, {
									y : 0.43429221692885994,
									ttip : 363.826647734375
								}, {
									y : 0.4500007025951916,
									ttip : -935.7038634375
								} ],
								pointPlacement : 'on'
							} ]
						});
	});
</script>
