/*jslint browser: true, plusplus: true, maxerr: 500, indent: 4 */
/*global jQuery, org_apache_myfaces_PopupCalendar, alert, confirm, top*/
(function () {
	"use strict";
	var $ = jQuery;
	if(typeof String.prototype.str_replace != "function") {
		String.prototype.str_replace = function(search, replace) {
			return this.split(search).join(replace);
		}
	}
	
	function replaceHistory() {
		var stateObj = {};
		var referrer = document.referrer;
		if (referrer != '') {
			window.history.pushState(stateObj, "Back over forward", referrer);
		}
	}

	var hisqis = {
		qfw: {
			openWindow: function (element) {
				var url, windowName, windowProp, w;
				url = element.attr("data-url");
				if (url === undefined) {
					url = element.attr("href");
				}
				windowName = element.attr("data-window-name");
				if (windowName === undefined) {
					windowName = "fenster";
				}
				windowProp = element.attr("data-window-properties");
				if (windowProp === undefined) {
					windowProp = "width=880,height=600,status=yes,scrollbars=yes,resizable=yes";
				}
				w = window.open(url, windowName, windowProp);
				w.focus();
			},

			initNavigation: function () {
				jQuery(".qisback").click(function (event) {
					window.history.back();
					event.preventDefault();
					return false;
				});
				if (document.getElementById("qis-top-main-redirect") !== null) {
					top.main.location.href = document.getElementById("qis-top-main-redirect").getAttribute("data-location");
				}
				jQuery(".qisautoopenwindow").each(function (event) {
					hisqis.qfw.openWindow(jQuery(this));
				});

				jQuery(".qisopenwindow").click(function (event) {
					hisqis.qfw.openWindow(jQuery(this));
					event.preventDefault();
					return false;
				});
				jQuery(".qisclosewindow").click(function (event) {
					window.close();
					event.preventDefault();
					return false;
				});
				jQuery(".qisprintwindow").click(function (event) {
					window.print();
					event.preventDefault();
					return false;
				});
				jQuery(".qisautosubmit").each(function (event) {
					jQuery(this).submit();
				});
				jQuery(".qischangeimage").click(function () {
					document.getElementById(jQuery(this).attr("data-img-id")).src = jQuery(this).attr("data-img-url");
				});
			},
			initCalendar: function () {
				var c, length, i, child;
				jQuery(".qiscalendar").each(function (index, e) {
					c = new org_apache_myfaces_PopupCalendar();
					c.initData.imgDir = e.getAttribute("data-initData-imgDir");
					c.initData.monthName = e.getAttribute("data-initData-monthName").split(",");
					c.initData.dayName = e.getAttribute("data-initData-dayName").split(",");
					c.initData.startAt = e.getAttribute("data-initData-startAt");
					c.initData.popupLeft = e.getAttribute("data-initData-popupLeft");
					c.initData.gotoString = e.getAttribute("data-initData-gotoString");
					c.initData.todayString = e.getAttribute("data-initData-todayString");
					c.initData.weekString = e.getAttribute("data-initData-weekString");

					c.dateFormat = e.getAttribute("data-dateFormat");
					c.dateFormatSymbols.weekdays = e.getAttribute("data-dateFormatSymbols-weekdays").split(",");
					c.dateFormatSymbols.shortWeekdays = e.getAttribute("data-dateFormatSymbols-shortWeekdays").split(",");
					c.dateFormatSymbols.shortMonths = e.getAttribute("data-dateFormatSymbols-shortMonths").split(",");
					c.dateFormatSymbols.months = e.getAttribute("data-dateFormatSymbols-months").split(",");
					c.dateFormatSymbols.eras = e.getAttribute("data-dateFormatSymbols-eras").split(",");
					c.dateFormatSymbols.ampms = e.getAttribute("data-dateFormatSymbols-ampms").split(",");

					length = e.childNodes.length;
					for (i = 0; i < length; i++) {
						child = e.childNodes[i];
						if (child.className === "qiscalendarfreeday") {
							c.addHoliday(parseInt(child.getAttribute("data-day"), 10), parseInt(child.getAttribute("data-month"), 10),
									parseInt(child.getAttribute("data-year"), 10), child.getAttribute("data-name"));
						} else if (child.className === "qiscalendarfreedays") {
							c.addHolidayRange(parseInt(child.getAttribute("data-day1"), 10), parseInt(child.getAttribute("data-month1"), 10),
									parseInt(child.getAttribute("data-year1"), 10), parseInt(child.getAttribute("data-day2"), 10),
									parseInt(child.getAttribute("data-month2"), 10), parseInt(child.getAttribute("data-year2"), 10),
									child.getAttribute("data-name"));
						}
					}

					c.init(e);

					jQuery("#" + e.getAttribute("id") + "i").click(function () {
						c._popUpCalendar(this, document.getElementById(e.getAttribute("data-element-id")), 'dd.MM.yy');
					});
				});

			},
			initOpenidSelector: function () {
				if (window.openid !== undefined) {
					window.openid.init('openid_identifier');
				}
			},
			initConfirmBox: function () {
				jQuery(".qisconfirmbox").click(function (event) {
					var answer = confirm(jQuery(this).attr("data-message"));
					if (!answer) {
						event.preventDefault();
						return false;
					}
				});

				jQuery(".newcaptcha").click(function (event) {
					var now = new Date();
					document.getElementById('j_captcha_image').src = jQuery("#j_captcha_image").attr("data-urlprefix") + now.getTime();
					event.preventDefault();
					return false;
				});

			},
			initOpenWindow: function () {
				if (jQuery(".openwindowonload").attr("data-openwindow")) {
					window.open(jQuery(".openwindowonload").attr("data-openwindow"));
				}
				jQuery(".openwindowonclick").click(function (event) {
					window.open(jQuery(this).attr("data-openwindow"));
					event.preventDefault();
				});
			}, 
			initDateTimePicker: function() {
				jQuery(".qisdatetimepicker").each(function (index, e) {
					var colId = e.getAttribute("dtPicker-columnId"),
						colId = colId.replace(".", "\\."), // dot as a literal part of a name must be escaped with with two backslashes
						lang = e.getAttribute("dtPicker-lang"),
						format = e.getAttribute("dtPicker-format"),
						formatDate = e.getAttribute("dtPicker-formatDate"),
						formatTime = e.getAttribute("dtPicker-formatTime"),
						step = Number(e.getAttribute("dtPicker-step")),
						closeOnDateSelect = e.getAttribute("dtPicker-closeOnDateSelect").toUpperCase() === "Y",
						closeOnTimeSelect = e.getAttribute("dtPicker-closeOnTimeSelect").toUpperCase() === "Y",
						closeOnWithoutClick = e.getAttribute("dtPicker-closeOnWithoutClick").toUpperCase() === "Y",
						validateOnBlur = e.getAttribute("dtPicker-validateOnBlur").toUpperCase() === "Y",
						timepicker = e.getAttribute("dtPicker-timepicker").toUpperCase() === "Y",
						datepicker = e.getAttribute("dtPicker-datepicker").toUpperCase() === "Y",
						weeks = e.getAttribute("dtPicker-weeks").toUpperCase() === "Y",
						theme = e.getAttribute("dtPicker-theme"),
						minDate = e.getAttribute("dtPicker-minDate") ? e.getAttribute("dtPicker-minDate") : false,
						maxDate = e.getAttribute("dtPicker-maxDate") ? e.getAttribute("dtPicker-maxDate") : false,
						defaultDate = e.getAttribute("dtPicker-defaultDate"),
						defaultTime = e.getAttribute("dtPicker-defaultTime"),
						minTime = e.getAttribute("dtPicker-minTime"),
						maxTime = e.getAttribute("dtPicker-maxTime"),
						allowTimes = e.getAttribute("dtPicker-allowTimes") ? e.getAttribute("dtPicker-allowTimes").split(',').map(String) : new Array(),
						highlightedDates = [],
						highlightedPeriods = [],
						opened = e.getAttribute("dtPicker-opened").toUpperCase() === "Y",
						todayButton = e.getAttribute("dtPicker-todayButton").toUpperCase() === "Y",
						prevButton = e.getAttribute("dtPicker-prevButton").toUpperCase() === "Y",
						nextButton = e.getAttribute("dtPicker-nextButton").toUpperCase() === "Y",
						defaultSelect = e.getAttribute("dtPicker-defaultSelect").toUpperCase() === "Y",
						allowBlank = e.getAttribute("dtPicker-allowBlank").toUpperCase() === "Y",
						timepickerScrollbar = e.getAttribute("dtPicker-timepickerScrollbar").toUpperCase() === "Y",
						scrollMonth = e.getAttribute("dtPicker-scrollMonth").toUpperCase() === "Y",
						yearStart = Number(e.getAttribute("dtPicker-yearStart")),
						yearEnd = Number(e.getAttribute("dtPicker-yearEnd")),
						dayOfWeekStart = Number(e.getAttribute("dtPicker-dayOfWeekStart"));
					
					var child, i;
					for (i = 0; i < e.childNodes.length; i++) {
						child = e.childNodes[i];
						if (child.className === "qiscalendarfreeday") {
							highlightedDates.push(child.getAttribute("data-day")+"."+child.getAttribute("data-month")+"."+child.getAttribute("data-year")+","+child.getAttribute("data-name"));
						} else if (child.className === "qiscalendarfreedays") {
							highlightedPeriods.push(child.getAttribute("data-day1")+"."+child.getAttribute("data-month1")+"."+child.getAttribute("data-year1")+","+child.getAttribute("data-day2")+"."+child.getAttribute("data-month2")+"."+child.getAttribute("data-year2")+","+child.getAttribute("data-name"));
						}
					}
					
					jQuery("#" + colId).datetimepicker({
						lang: lang,
						format: format,
						formatDate: formatDate,
						formatTime: formatTime,
						step: step,
						closeOnDateSelect: closeOnDateSelect,
						closeOnTimeSelect: closeOnTimeSelect,
						closeOnWithoutClick: closeOnWithoutClick,
						validateOnBlur: validateOnBlur,
						timepicker: timepicker,
						datepicker: datepicker,
						weeks: weeks,
						theme: theme,
						minDate: minDate,
						maxDate: maxDate,
						defaultDate: defaultDate,
						defaultTime: defaultTime,
						minTime: minTime,
						maxTime: maxTime,
						allowTimes: allowTimes,
						highlightedDates: highlightedDates,
						highlightedPeriods: highlightedPeriods,
						opened: opened,
						todayButton: todayButton,
						prevButton: prevButton,
						nextButton: nextButton,
						defaultSelect: defaultSelect,
						allowBlank: allowBlank,
						timepickerScrollbar: timepickerScrollbar,
						scrollMonth: scrollMonth,
						yearStart: yearStart,
						yearEnd: yearEnd,
						dayOfWeekStart: dayOfWeekStart
					});
					jQuery("#" + e.getAttribute("id") + "i").click(function () {
						jQuery("#" + colId).datetimepicker('show');
					});
				});
			}
		},

		lsf: {
			calls: false,
			initLastCall: function () {
				jQuery(".lsflastcall").click(function (event) {
					if (hisqis.lsf.calls) {
						var warten = "<span class='warnung'>Ihre Belegung wird verarbeitet ...<\/span>";
						document.getElementsByName("belegen")[0].parentNode.innerHTML = warten;
					} else {
						hisqis.lsf.calls = true;
					}
				});
			},

			testMassenBuchung: function() {
				var cache = new Object();
				
				var log = $('#d_massen_buchung_log');
				
				function getAnfrageIds(anfrageId,parent) {
					if(cache[anfrageId]) {
						return;
					}
					cache[anfrageId] = parent;
					
					var item = $('div[data-anfrage-id="' + anfrageId + '"]');
					var requestConflicts = item.attr("data-request-conflict").str_replace("<br />"," ").split(" ");
					for(var i=0;i<requestConflicts.length;++i) {
						getAnfrageIds(requestConflicts[i],anfrageId);
						cache[requestConflicts[i]] = anfrageId;
					}
				}
				
				getAnfrageIds(2022,0);
				
				for(var anfrageId in cache) {
					log.append(cache[anfrageId] + "- " + anfrageId + "<br />");
				}

				for(var anfrageId in cache) {
					log.append(anfrageId + ",");
				}
			},
			sortMassenBuchung: function() {
				var checked = new Array();
				var days = ["Mo","Di","Mi","Do","Fr","Sa","So"];
				
				$('[data-role="massenbuchung"]').each(function() {
					var elm = $(this);
					var anfrageId = elm.attr("data-anfrage-id");
					for(var i=0;i<checked.length;++i) {
						if(checked[i] == anfrageId) {
							return;
						}
					}
					
					var requestConflicts = elm.attr("data-request-conflict");
					
					if(requestConflicts == "n") {
						requestConflicts = "";
					}
					
					if(requestConflicts) {
						requestConflicts = requestConflicts.str_replace("<br />"," ").split(" ");
						for(var i=0;i<requestConflicts.length;++i) {
							checked.push(requestConflicts[i]);
							var sorting = new Array();

							var item = $('div[data-anfrage-id="' + requestConflicts[i] + '"]');
							if(item.length >= 1) {
								sorting.push(item);
							}
							
							sorting.push(elm);
							sorting.sort(function(a,b) {
								var daya = a.attr("data-sort").split(",")[0];
								var dayb = b.attr("data-sort").split(",")[0];
								
								for(var i=0;i<days.length;++i) {
									if(days[i] == daya) {
										daya = i;
									}
									if(days[i] == dayb) {
										dayb = i;
									}
								}
								
								if(daya == dayb) {
									return a.attr("data-sort") < b.attr("data-sort");
								}
								else {
									return daya < dayb;
								}
							});
							
							var parent = sorting[0].parent().parent();
							for(var j=1;j<sorting.length;++j) {
								var tmp = sorting[j].parent().parent();
								parent.after(tmp);
							}
						}
					}
				});
			},
			
			initMassenBuchung: function() {
				var blocking = new Array();
				var blockSameTermin = true;
				var blockSameTerminArray = new Array();
				
				$('[data-mrb-block="true"]').bind("click", function() {
					alert("Dieser Termin wird gerade von einem Raumverwalter bearbeitet.");
				});
				
				$('[data-role="massenbuchung"]').each(function() {
					var elm = $(this);
					var anfrageId = elm.attr("data-anfrage-id");
					var block_id = false;
					var terminId = elm.attr("data-termin-id");
					var requestConflicts = elm.attr("data-request-conflict").str_replace("<br />"," ");
					if(requestConflicts == "n") {
						requestConflicts = "";
					}
					requestConflicts = requestConflicts.split(" ");
					
					elm.children().remove();
					if(requestConflicts.length > 0) {
						for(var aid in blocking) {
							if(aid == anfrageId) {
								block_id = blocking[aid];
							}
						}

						for(var i=0;i<requestConflicts.length;++i) {
							blocking[requestConflicts[i]] = anfrageId;
						}
					}
					
					if(blockSameTermin) {
						var found = false;
						for(var tid in blockSameTerminArray) {
							if(tid == terminId) {
								found = blockSameTerminArray[tid];
							}
						}
						
						if(found) {
							if(block_id == false) {
								block_id = found[0];
							}
							for(var i=0;i<found.length;++i) {
								var imb = $("#i_massen_buchung_" + found[i]);
								var c = imb.attr("data-request-conflict");
								if(c) {
									c += " " + anfrageId;
								}
								else {
									c = anfrageId;
								}
								imb.attr("data-request-conflict", c);
								requestConflicts.push(found[i]);
							}
							found.push(anfrageId);
						}
						else {
							found = new Array(anfrageId);
						}
						blockSameTerminArray[terminId] = found;
					}
					
//					var checkbox = $('<input type="checkbox" id="i_massen_buchung_' + anfrageId + '" name="i_massen_buchung_' + anfrageId + '" data-i-termin-id="' + terminId + '" data-anfrage-id="' + anfrageId + '" data-request-conflict="' + requestConflicts.join(" ") + '" value="' + anfrageId + '"' + ((block_id == false) ? ' checked="checked"' : '')+ '>');
					var checkbox = $('<input type="checkbox" id="i_massen_buchung_' + anfrageId + '" data-i-termin-id="' + terminId + '" data-anfrage-id="' + anfrageId + '" data-request-conflict="' + requestConflicts.join(" ") + '" value="' + anfrageId + '"' + ((block_id == false) ? ' checked="checked"' : '')+ '>');
					checkbox.change(function() {
						var elm = $(this);
						elm.parent().find('[data-blocking="true"]').html("").attr('href','#');
						var anfrageId = elm.attr("data-anfrage-id");
						if(elm.is(':checked')) {
							elm.attr('checked', 'checked');
							var requestConflicts = elm.attr("data-request-conflict");
							if(requestConflicts) {
								requestConflicts = requestConflicts.split(" ");
								var i;
								for(i=0;i<requestConflicts.length;++i) {
									var cb = $('#i_massen_buchung_' + requestConflicts[i]);
									cb.each(function() {
										$(this).parent().find('[data-blocked="true"]').remove();

										var blocking = $(this).parent().find('[data-blocking="true"]');
										blocking.attr("href","#anfrage_" + anfrageId);
										blocking.html(anfrageId);
										$(this).attr('checked', false);
										this.checked = false;
									});
									
									elm.after('<a data-blocked="true" href="#anfrage_' + requestConflicts[i] + '">' + requestConflicts[i] + '</a><br data-blocked="true" />');
								}
							}
						}
					});

					elm.append(checkbox);
					elm.append('<a name="anfrage_' + anfrageId + '">');
					if(block_id) {
						elm.append('<a data-blocking="true" href="#anfrage_' + block_id + '">' + block_id + '</a>');
					}
					else {
						elm.append('<a data-blocking="true" href="#"></a>');
						for(var i=0;i<requestConflicts.length;++i) {
							elm.append('<a data-blocked="true" href="#anfrage_' + requestConflicts[i] + '">' + requestConflicts[i] + '</a><br data-blocked="true" />');
						}
					}
				});

				var cancel = false;
				
				$('#a_massen_buchung_cancel').click(function() {
					cancel = true;
				});
				
				$('#a_massen_buchung').click(function() {
					var elms = $('[id^="i_massen_buchung_"]');
					var log = $('#d_massen_buchung_log');
					var ids = new Array();
					var current = 0;
					
					if(cancel) {
						alert("Die Massenbuchung wurde abgebrochen!");
						return;
					}
					
					function sendRequest(anfrageId) {
						var conflict = $("#i_massen_buchung_" + anfrageId).attr("data-request-conflict");
						var url = location.href.substr(0,location.href.indexOf("?"));
						var asi = location.href.substr(location.href.indexOf("asi"));
						if(conflict) {
							url += "?state=openRequestsForRooms&nextdir=ressourcenManager&next=null.vm&menu_open=n&executeRequest=" + anfrageId + "&speed=y&" + asi;
						}
						else {
							url += "?state=openRequestsForRooms&nextdir=ressourcenManager&next=null.vm&menu_open=n&executeRequest=" + anfrageId + "&conflict=n&speed=y&" + asi;
						}
//						location.href = url;
						
//						url = "http://localhost:38080/qisserver/rds";
//						url = "http://localhost:38080/";
						
				        $.ajax(url, {
			                success: function(res, textStatus, jqXHR) {
			                	var div = $('[data-role="massenbuchung"][data-anfrage-id="' + anfrageId + '"]');
			                	div.children().remove();
			                	div.append('<img src="/QIS/images/Tik.gif" />');
			                	var tr = "";
			                	tr += "<tr>";
			                	tr += "<td>";
			                	tr += anfrageId;
			                	tr += "</td>";
			                	tr += "<td>";
			                	tr += "erfuellt";
			                	tr += "</td>";
			                	var childs = div.parent().parent().children();
			                	var i;
			                	childs.each(function() {
				                	tr += "<td>";
				                	tr += $(this).text();
				                	tr += "</td>";
			                	});
			                	tr += "</tr>";
			                	log.append(tr);
			                	if(!cancel) {
									doNext();
			                	}
			                },
			                error: function(jqXHR, textStatus, errorThrown) {
			                	var div = $('[data-role="massenbuchung"][data-anfrage-id="' + anfrageId + '"]');
			                	var tr = "";
			                	tr += "<tr>";
			                	tr += "<td>";
			                	tr += anfrageId;
			                	tr += "</td>";
			                	tr += "<td>";
			                	tr += "error (" + textStatus + "," + errorThrown + ")";
			                	tr += "</td>";
			                	var childs = div.parent().parent().children();
			                	var i;
			                	childs.each(function() {
				                	tr += "<td>";
				                	tr += $(this).text();
				                	tr += "</td>";
			                	});
			                	tr += "</tr>";
			                	log.append(tr);
			                }
				        });
					}
					
					function doNext() {
						if(typeof ids[current] != "undefined" && ids[current] != undefined) {
							sendRequest(ids[current++]);
						}
						else {
							alert("Alle anfragen wurden bearbeitet.");
//							location.reload(true);
						}
					}
					
					elms.each(function() {
						var elm = $(this);
						if(elm.is(':checked')) {
							ids.push(elm.attr("data-anfrage-id"));
						}
						elm.attr("disabled", true);
					});
					
					doNext();
				});
				
				$('#a_massen_buchung_drucken').click(function() {
					var w = 800;
					var h = 600;
			        var x = screen.availWidth/2-w/2;
			        var y = screen.availHeight/2-h/2;
			        
			        var log = $('#d_massen_buchung_log');
			        
			        var popupWindow = window.open('','','width='+w+',height='+h+',left='+x+',top='+y+',screenX='+x+',screenY='+y);
			        var text = log.html();
			        text = "<table border='1'>" + text + "</table>";
			        popupWindow.document.write(
			        		"<html>" +
			        		"<head>" +
			        		"</head>" +
			        		"<body>" +
			        		text +
			        		"</body>" +
							"</html>");
			        popupWindow.document.close();
			        popupWindow.focus();
			        popupWindow.print();
				});
				
				$('#a_massen_buchung_sortieren').click(function() {
					hisqis.lsf.sortMassenBuchung();
					hisqis.lsf.initMassenBuchung();
				});
				
				$('#a_massen_buchung_test').click(function() {
					hisqis.lsf.testMassenBuchung();
				});
			},
			
			initMarkAll: function() {
				var mark = $('[data-mark="true"]').add('[name="markAllButton"]');
				mark.each(function() {
					var mark = $(this);
					mark.css("visibility","visible");
					if(!mark.val()) {
						mark.val(mark.attr("data-markall"));
					}
					
					var form = mark;
					var form_name = mark.attr("data-form-name");
					if(form_name) {
						form_name = $('[name="' + form_name + '"]');
						if(form_name.length > 0) {
							form = form_name;
						}
					}
					while(form.length > 0 && form[0].tagName != "FORM") {
						form = form.parent();
					}
					
					mark.click(function(e) {
						var checked;
						if(mark.val() == mark.attr("data-markall")) {
							checked = true;
							mark.val(mark.attr("data-demarkall"));
						}
						else {
							checked = false;
							mark.val(mark.attr("data-markall"));
						}

						form.find('input:[type="checkbox"]').each(function() {
							$(this).attr('checked', checked);
							$(this).prop('checked', checked);
						});
						e.preventDefault();
						return false;
					});
				});
			}
		},

		qissos: {
			called: 0,
			initCallBescheid: function () {
				jQuery(".qiscallbescheid").click(function (event) {
					hisqis.qissos.called = hisqis.qissos.called + 1;
					if (hisqis.qissos.called === 1) {
						document.forms[0].besch.value = jQuery(this).attr("data-besch");
						document.forms[0].stammdaten.value = jQuery(this).attr("data-stammdaten");
						document.forms[0].submit();
					} else {
						alert("Bitte warten Sie, Ihre Anfrage wird bereits bearbeitet");
					}
				});
			}
		},

		qispos: {
			baumnode_collapse: function (nodeDOM_id) {
				document.getElementById('expand_' + nodeDOM_id).style.display = 'inline';
				document.getElementById('Text_' + nodeDOM_id).style.display = 'none';
				document.getElementById('Prio_' + nodeDOM_id).style.display = 'none';
			},
			baumnode_collapse_All: function (class_name) {
				var all_obj, ret_obj = [], teststr, i;
				if (document.all) {
					all_obj = document.all;
				} else if (document.getElementsByTagName && !document.all) {
					all_obj = document.getElementsByTagName("*");
				}

				for (i = 0; i < all_obj.length; i++) {
					if (all_obj[i].className.indexOf(class_name) !== -1) {
						teststr = "," + all_obj[i].className.split(" ").join(",") + ",";
						if (teststr.indexOf("," + class_name + ",") !== -1) {
							hisqis.qispos.baumnode_collapse(all_obj[i].id.substr(5));
							document.getElementById('collapse_' + all_obj[i].id.substr(5)).style.display = 'inline';
							document.getElementById('Text_' + all_obj[i].id.substr(5)).style.border = '1px solid #7FB4D8';
						}
					}
				}
			},

			initTreeOperations: function () {
				jQuery(".qisposexpand").click(function () {
					document.getElementById('expand_' + jQuery(this).attr("data-nodeid")).style.display = 'none';
					document.getElementById('Text_' + jQuery(this).attr("data-nodeid")).style.display = 'Block';
					document.getElementById('Prio_' + jQuery(this).attr("data-nodeid")).style.display = 'inline';
				});

				jQuery(".qisposcollapse").click(function () {
					hisqis.qispos.baumnode_collapse(jQuery(this).attr("data-nodeid"));
				});

				hisqis.qispos.baumnode_collapse_All('Baumnode_Text');
			}
		},

		qiszul: {
			called: 0,
			initLastCall: function () {
				jQuery(".qiszullastcall").click(function (event) {
					hisqis.qiszul.called = hisqis.qiszul.called + 1;
					if (hisqis.qiszul.called === 1) {
						document.forms[0].submit();
					} else {
						alert("Bitte warten Sie, Ihre Anfrage wird bereits bearbeitet");
					}
				});

				jQuery(".qiszullastcall2").click(function (event) {
					hisqis.qiszul.calls = hisqis.qiszul.calls + 1;
					if (hisqis.qiszul.calls === 1) {
						document.forms[1].submit();
					} else {
						alert("Bitte warten Sie, Ihre Anfrage wird bereits bearbeitet");
					}
				});
			}
		}
	};

	jQuery().ready(function () {
		hisqis.qfw.initCalendar();
		hisqis.qfw.initNavigation();
		hisqis.qfw.initConfirmBox();
		hisqis.qfw.initOpenidSelector();
		hisqis.qfw.initOpenWindow();
		hisqis.qfw.initDateTimePicker();
		hisqis.lsf.initLastCall();
		hisqis.qispos.initTreeOperations();
		hisqis.qissos.initCallBescheid();
		hisqis.qiszul.initLastCall();
		hisqis.lsf.initMassenBuchung();
		hisqis.lsf.initMarkAll();
		// if forward.vm we'll erase ourselves from the browser history
		// to allow back button to refer
		if (jQuery("body").hasClass("forwardPage")) {
			replaceHistory();
		}
	});

}());