/*
  @description:
		Funktionen für den lsfpageobserver definieren.
		Um eine neue Funktion hinzuzufügen, geben Sie folgenden Eintrag an:
		lsfPageObserver_parameterList.push({'name':<Funktionsname>,'params':<Parameterliste als HashMap>})
		
		Anschließend definieren Sie ihre Funktion, die sie bereits als Funktionsname definiert haben.
		
	@author: hillmer@his.de
*/
var lsfPageObserver_parameterList = new Array()
lsfPageObserver_parameterList.push({'name':'markierteTermineVormerken','params':{'state':'wplan','search':'ver','act':'add'}})
lsfPageObserver_parameterList.push({'name':'pruefeLoginWerte','params':{'state':'user','type':'0'}})
lsfPageObserver_parameterList.push({'name':'pruefeLoginWerte','params':{'state':'user','type':'1'}})
lsfPageObserver_parameterList.push({'name':'pruefeLoginWerte','params':{'state':'wlogin'}})

//Sicherheitslückenproblem, deshalb auskommentiert
//lsfPageObserver_parameterList.push({'name':'zurueckButtonBelegungsplan','params':{'state':'wplan','act':'Raum','pool':'Raum','P.subc':'plan'}})
//lsfPageObserver_parameterList.push({'name':'zurueckButtonEvent','params':{'state':'change','type':'2','isparent':'y'}})
lsfPageObserver_parameterList.push({'name':'raumanfrageEmailCheck','params':{'state':'extendedRoomSearchAnfrage','type':'1','next' : 'extendedRoomSearch.vm'}})

//Sicherheitslückenproblem, deshalb auskommentiert
//lsfPageObserver_parameterList.push({'name':'semesterSwitchSavePage','params':{}})
//lsfPageObserver_parameterList.push({'name':'semesterSwitch','params':{'state':'change','type':'6','moduleParameter' : 'semesterSelect', 'nextdir' : 'change','next' : 'SearchSelect.vm','subdir':'applications','targettype':'7','targetstate':'change','getglobal' : 'semester'}})

lsfPageObserver_parameterList.push({'name':'veranstaltungenAdditionalinfo','params':{state : 'wscheck', 'wscheck' : 'leistungen'}})

lsfPageObserver_parameterList.push({'name':'timeoutinfo','params':{}})
//Text-Diff nur für Protokollseiten
//lsfPageObserver_parameterList.push({'name':'textdiff','params':{state: 'verpublish', publishSubDir: 'veranstaltung', moduleCall: 'webProt'}})
//In der veransttermin, wird bei Rythmus woch nu Mo-Fr. in der Wochenanzeige angezeigt.
// deaktiviert
//lsfPageObserver_parameterList.push({'name':'montagBisFreitag','params':{state: 'change', moduleParameter: 'veransttermin', parentModuleParameter: 'veranstaltung'}})
lsfPageObserver_parameterList.push({'name':'sortTableByBeginTime','params':{'state':'pdfplan', 'moduleCall' : 'freePositionHeutigeVeranstaltungen'}})
lsfPageObserver_parameterList.push({'name':'semesterDropdown','params':{}})
lsfPageObserver_parameterList.push({'name':'checkAllCheckboxes','params':{state : 'change','moduleParameter' : 'veranstaltungen'}})
lsfPageObserver_parameterList.push({'name':'checkAllCheckboxes','params':{state : 'change','moduleParameter' : 'veransttermin'}})
lsfPageObserver_parameterList.push({'name':'checkAllCheckboxes','params':{state : 'change','moduleParameter' : 'studiengaenge'}})



var markierteTermineVormerken = function(lsfpageobserver){
	$( document ).ready(function() {
				if ($('select[name="week"] option:selected').val() != '-2' && $('td[class="plan2"]').size() == 0){
					var ok = confirm(lsfpageobserver.lsfObserverLanguage.getLangText('_mtv_'))
					if (ok){
						document.location.href = document.location.href + "&week=-2"
					}
				}
		})
}
/*
 * Login Werte auf keine Einträge überprüfen
 */
var pruefeLoginWerte = function(lsfpageobserver){

	var benutzerkennung = $('input[id="asdf"]')
	var passwort = $('input[id="fdsa"]')
	var form = $('form[name="loginform"]')

	if (benutzerkennung.size() > 0 && passwort.size() > 0 && form.size() > 0){
		form.submit(function( event ) {
			if (benutzerkennung.val().trim().length == 0 || passwort.val().trim().length == 0){
				alert(lsfpageobserver.lsfObserverLanguage.getLangText('_login_'))
				event.preventDefault();
			}
		})
	}
}
/*
 * Speichern der Href Position für Zurück Button
 */
var zurueckButtonBelegungsplan = function(lsfpageobserver){
	
	//?state=wplan&act=Raum&pool=Raum&P.subc=plan&raum.rgid=10073&idcol=raum.rgid&idval=10073&purge=n&getglobal=n&text=AC-Institutsgebäude+-+10++(Lehrlabor)
	lsfpageobserver.createCookie('zurueckButtonBelegungsplan',document.location.href);
}
/*
 * Zurück Button in  Veranstaltung und bei Klick, Sprung auf den Belegungsplan
 */
var zurueckButtonEvent = function(lsfpageobserver){
	//http://localhost:8080/qisserver/rds?state=change&type=2&moduleParameter=event&nextdir=change&next=SingleInput.vm&sqlmode=new&subdir=veranstaltung&isparent=y&raum=10073&day=Mo&begin=7:00&week=&ignorecheckfor=y&headertext=Neue+Veranstaltung+am+Mo+ab+7%3A00+Uhr+in+AC-Institutsgeb%C3%A4ude+-+10++%28Lehrlabor%29&asi=ZtxkoLd5jhqxtQJXgtDT
	var url = lsfpageobserver.readCookie('zurueckButtonBelegungsplan');
	if (url != null){
		var bread = $('div.breadcrumb > a').text()
		if (bread.indexOf("Raumbelegung") > 0 || bread.indexOf("Reservations") > 0){
			var html = '<input class="linkAsButton" type="button" onclick="javascript:document.location.href=\''+url+'\'" value="'+lsfpageobserver.lsfObserverLanguage.getLangText("Zurück")+'"/>'
			var cont = $('form > div:last-child');
				cont.prepend(html)
		}
	}	
}
/*
 * Raumanfrage: Wenn Raumverwalter keine Email haben und oder diese nicht aktiv sind, wird eine Fehlemldung ausgegeben.
 */
var raumanfrageEmailCheck = function(lsfpageobserver){
	$('input[type="checkbox"]').click(function() {
		$('div[id="emailwarning"]').remove();
		found = false
		$('input[type="checkbox"]:checked').each(function() {
			var raumverwalter = $(this).parent().parent().find('td:eq(4)')
			if (raumverwalter.html().toLowerCase().indexOf(">email") > -1){
				found = true;
				return true;
			}			
		})
		if (found){
			var text = lsfpageobserver.lsfObserverLanguage.getLangText("_raumverwalteremail_")
			$('input[name="RoomSearch_RoomRequest"]').before("<div class='redcolor' id='emailwarning'>"+text+"</div>");
		}
	})
}
/*
 * Für den Semesterwechsel(oben rechts) wird jede Seite, die zuvor geöffnet wurde gespeichert.
 */
var semesterSwitchSavePage = function(lsfpageobserver){
	var linkToBelegungsplan = lsfpageobserver.readCookie('semesterSwitch');
	if (linkToBelegungsplan == null || document.location.href.indexOf('state=change&type=6&moduleParameter=semesterSelect&nextdir=change&next=SearchSelect.vm&subdir=applications&targettype=7&targetstate=change&getglobal=semester') == -1){
		lsfpageobserver.createCookie('semesterSwitch',document.location.href);
		//alert("erstellt")
	}
}
/*
 * Änderung der a Tags mit Javascript Ajax Switch
 */
var semesterSwitch = function(lsfpageobserver){
	var linkToBelegungsplan = lsfpageobserver.readCookie('semesterSwitch');
	if (linkToBelegungsplan != null){
		$('div.content > fieldset > div > a').each(function(){
			var href = $(this).attr('href')
			var link = "javascript:semesterSwitchHandle('"+href+"')";
			$(this).attr('href',link)
		})
	}
}
/*
 * Ausführen des Ajax Requests, der durch einen Klick Event, die zuvor geänderten a Tags, ausgelöst wurde.
 */
var semesterSwitchHandle = function(link){
	var linkToBelegungsplan = readCookie('semesterSwitch');
	if (linkToBelegungsplan != null){
		var request = $.ajax( link )
			.done(function() {
				document.location.href = linkToBelegungsplan
			})
			.fail(function() {
				alert( "Ein Javascript Fehler ist aufgetreten." );
			})
	}
}

var readCookie = function(name) {
	var nameEQ = name + "=";
	var ca = document.cookie.split(';');
	for(var i=0;i < ca.length;i++) {
		var c = ca[i];
		while (c.charAt(0)==' ') c = c.substring(1,c.length);
		if (c.indexOf(nameEQ) == 0) return c.substring(nameEQ.length,c.length);
	}
	return null;
}

var eraseCookie = function(name) {
	createCookie(name,"",-1);
}


var veranstaltungenAdditionalinfo = function(lsfpageobserver){
	$('div.Container_CaptionVeranstAus').each(function(){
		$(this).before('<div class="evt_more_infos" style="cursor:pointer">Klick für mehr oder weniger Informationen</div>');
	})
	// Elemente verstecken
	$('div.Container_VeranstAus').each(function(){
		$(this).hide()	
	})
	// Klick Event für 'Klick für mehr Informationen'
	$('div.evt_more_infos').click(function(){
		var el = $(this).parent().find('div.Container_VeranstAus')
		el.toggle()
	})
	
	var sem = $('a#choosesemester').text().replace(/[a-zA-Z ]/g,'').trim();
	$('div.functionnavi > h2').each(function(){
		var curSem = $(this).text().replace(/[a-zA-Z :]/g,'').trim();
		if (curSem == sem){
		var el = $(this).parent().find('div.Container_VeranstAus')
		el.show()		
		}
	});		
}
var timeout = 30
var origTimeoutText = null
var timeoutinfo = function(lsfpageobserver){
	var logoutDiv = $('logout').first()
	timeout = parseInt(logoutDiv.attr('maxtime'))
	var divloginstatus = $('div.divloginstatus a:eq(1)').text()
	if (divloginstatus) divloginstatus = divloginstatus.trim().toLowerCase()
	if ((divloginstatus && divloginstatus == 'anmelden') || logoutDiv.length == 0 || $('a.links3[accesskey="l"]').length == 0 || timeout <= 0 || logoutDiv.attr('active') != 'y'){
		logoutDiv.hide()
		return null
	}
	settimeout(logoutDiv, lsfpageobserver)
}

var tclass = ''
var tclass_before = ''
var settimeout = function(div, lsfpageobserver){
	if (timeout <= 0){
		alert(div.attr('timeoutmessage'))
		document.location.href=$('a.links3[accesskey="l"]').attr('href')
		return;
	}
	
	var classStyle=div.attr('class')
	var style=div.attr('style')
	var class5min = div.attr('class5min')
	var class10min = div.attr('class10min')
	var minutetext_plural = div.attr('minutetext_plural')
	var minutetext_singular = div.attr('minutetext_singular')
	var showOnMin = div.attr('showOnMin')
	
	if (timeout <= 5){
		tclass = class5min//'color:#d94747;background-color:white'
	}else if (timeout <= 10){ 
		tclass = class10min//'color:#ffcc00'
	}
	if (origTimeoutText == null){
		origTimeoutText = div.html() 	
	}
	
	var rep = origTimeoutText.replace(/\[min\]/,(timeout<10?'0'+timeout:timeout))
	rep = rep.replace(/\[minutetext\]/,(timeout<=1 ? minutetext_singular : minutetext_plural))
	var ttext = "<span id='timeouttext'>"+rep+"</span>"
	div.html(ttext)
	$('#timeouttext').attr('class',classStyle)
	$('#timeouttext').attr('style',style)
	if (showOnMin != undefined && showOnMin.length > 0){
		showOnMin = parseInt(showOnMin)
		if (timeout > showOnMin){
			div.children().hide()
		}else{
			div.children().show()	
		}
	}else{
			div.children().show()
	}
	div.find('logouttext > #min').removeClass(tclass_before)
	div.find('logouttext > #min').addClass(tclass)
	
	tclass_before = tclass
	timeout -= 1
	setTimeout(function(){settimeout(div, lsfpageobserver)}, 60000);		
}

var textdiff = function(lsfpageobserver){
	$( document ).ready(function() {
		
		var hasTextName = $('tbody#diff_tbody > tr:last > td:eq(1)').text().trim() == 'Text'
		var hasBeforeText = $('#diff_tbody > tr:last > td:eq(2)').text().trim().length == 0 ? false : true
		if (!hasTextName || !hasBeforeText) return
		
		$('tr#headertext').append('<td class="td_p" colspan="4">&nbsp;</td><td class="th">Differenz</td>')
		$('table.summary_table > tbody > tr:gt(0)').append('<td class="td_p" colspan="4">&nbsp;</td><td class="td" valign="top"></td>')
		var diffObj = $('table.summary_table > tbody > tr:gt(0) > td').last()
		var origObj = $('#diff_tbody > tr:eq(1) > td:eq(2)')
		var changedObj = $('tbody#diff_tbody > tr:eq(1) > td:eq(4)')
		$(origObj).prettyTextDiff({
			cleanup: true,
			originalContainer: origObj,
			changedContainer: changedObj,
			diffContainer: $(diffObj)
			
        })		
	})
}

var montagBisFreitag = function(lsfpageobserver, montagBisFreitagBind){
	$( document ).ready(function() {
		$('select[id$="_7"]').each(function(){
			var parent = $(this).parent().parent()
			_montagBisFreitag(parent)
			$(this).change(function(){
				_montagBisFreitag(parent)
			})		
		})
	})
}
var _montagBisFreitag = function(parent){
	
	var val = $(parent).find('select[id$="_7"] option:selected').attr('value')
	if (val && val == 8){
		var val2 = $(parent).find('select[id$="_3"] option:selected').attr('value')
		if (val2 == 1){
			$(parent).find('select[id$="_3"] option').removeAttr("selected");
			$(parent).find('select[id$="_3"] option[value="6"]').attr('selected',true);
		}
		$(parent).find('select[id$="_3"] option[value="1"]').hide()
	}else{
		$(parent).find('select[id$="_3"] option[value="1"]').show()
	}
	
}
var semesterDropdown = function(lsfpageobserver){
	var is_semester_dropdown = $('semester_dropdown[active="y"]').length > 0 ? true : false
	var aktuellessemester = $('semester_dropdown').attr('aktsem')
	if (!is_semester_dropdown || !aktuellessemester){
		return true
	}
	var semesterAjaxUrl = "rds?state=change&type=6&moduleParameter=semesterSelect&nextdir=change&next=SearchSelect.vm&subdir=applications&targettype=7&targetstate=change&getglobal=semester"
	var cookiename = 'semester_dropdown_data'
	var data = localStorage ? localStorage.getItem(cookiename) : null
	var isStartpage = lsfpageobserver.getUrlParam('state') == 'user' ? true : false
	var cssclass = $('semester_dropdown').attr('class')
	$( document ).ready(function() {
			
		if (isStartpage || !data){
			//console.log("1"+isStartpage)
			$.ajax({
			  url: semesterAjaxUrl,
			}).done(function( mydata ) {
				var html = '<select id="semester_dropdown" class="'+cssclass+'">'
				$(mydata).find('div.content > fieldset > div > a').each(function(){
						var href = $(this).attr('href')
						var sem = $(this).text().trim()
						var ar = href.split("&")
						var sel = ''
						for(var item in ar){
							item = ar[item]
							var ar2 = item.split("=")
							if (ar2.length == 2 && ar2[0].trim() == "k_semester.semid" && ar2[1].trim() == aktuellessemester){
								sel = 'selected="true"'
								break
							}
						}
						//href += "&redirect="+redirect
						html += '<option value="'+href+'" '+sel+'>'+sem+'</option>'
				})
				html += '</select>'
				if (localStorage) localStorage.setItem(cookiename, html);
				$('semester_dropdown').first().prepend(html)
				$('select#semester_dropdown').change(function(){
					document.location.href = $(this).val()
				})
			})
		}else{
			//console.log("2")
			$('semester_dropdown').first().prepend(data)
			$('select#semester_dropdown').change(function(){
				document.location.href = $(this).val()
			})			
		}
	})
}

var checkAllCheckboxes = function(lsfpageobserver){
	if ($('div.table_1 > table > tbody > tr:eq(0) > th:eq(0)').length > 0){
		_checkAllCheckboxes($('div.table_1 > table > tbody > tr:eq(0) > th:eq(0)'), $('div.table_1 > table > tbody > tr:gt(0)  input[type="checkbox"]'))
	}
}

var _checkAllCheckboxes = function(allCheckboxParent, targetCheckboxes){
	var allCheckbox = '<input type="checkbox" id="observer_allCheckboxes" />'
	$(allCheckboxParent).append(allCheckbox)
	$('#observer_allCheckboxes').click(function(){
		var status = $(this).prop("checked")
		$(targetCheckboxes).each(function(){
			$(this).prop('checked',status)
		})
	})
}


var sortTableByBeginTime = function(lsfpageobserver){
	$( document ).ready(function() {
			var checkPos = 0
			var tr_data = []
			var tbody = 'div.content > table[0] > tbody'
			var tr_items = $(tbody+' > tr')
			tr_items.each(function(){
				var item1 = $(this)
				var item1_val = item1.find('td[0]').text().trim()
				console.log(item1_val)
				tr_items.each(function(){
					var item2 = $(this)
					var item2_val = item2.find('td[0]').text().trim()
					if (item1 != item2 ){
						var comp = _compareTableStrings(item1_val, item2_val)
						if (comp >= 0){
							tr_data.push(item1)
						}else{
							tr_data.push(item2)
						}
						
					}
				})
			})
	
			// clear table
			$(tbody).remove()
			var str = ''
			tr_data.each(function(){
				str+=$(this).html()
			})
			$(tbody).html(str)
	})
}

var _compareTableStrings = function(a,b){
	return (a<b?-1:(a>b?1:0))
}



