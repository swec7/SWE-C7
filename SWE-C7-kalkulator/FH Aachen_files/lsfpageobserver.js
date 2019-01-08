/*
	@class: LsfPageObserver
  @description:
		Der LsfPageObserver überprüft anhand von Einträgen einer Parameterliste nach Übereinstimmungen von Parametern. Wird ein Eintrag gefunden, wird die Methode, die unter Name steht, aufgerufen. 
		
		Bsp.: var parameterList = [{'name':'markierteTermineVormerken','params':{'state':'wplan','search':'ver','act':'add'}}];
		
		In der Datei lsfobersver_language_functions.js ist eine Funktion 'markierteTermineVormerken' zu finden, die nur bei den Url-Parametern state=wplan&search=ver&act=add aufgerufen wird.
		
		Die Datei lsfobersver_language_init.js kann dazu genutzt werden, sprachabhängige Texte zu erfassen.
		Bitte ändern Sie nur die Dateien lsfobersver_language_init.js und lsfobersver_language_functions.js
  
  @author: hillmer@his.de
*/

function LsfPageObserver(parameterList){
	
	this.lsfObserverLanguage = new LSFObserverLanguage();
		
	//Konstruktor
	this.constructor = function(name){
		// Sprachdatei initialisieren
		lsfPageObserverLanguageInit(this.lsfObserverLanguage);
		
		var found = false
		for(var key in parameterList){
			var parameterItem = parameterList[key]
			var c = 0
			for(var key in parameterItem['params']){
				var val = this.getUrlParam(key)
				if (val == parameterItem['params'][key]){
					c++
				}
			}
			if ((c > 0 && parameterItem != null && c == this.size(parameterItem['params'])) ||  this.size(parameterItem['params']) == 0){
				// Funktion dynamisch aufrufen
				window[parameterItem['name']](this)				
			}
		}
	}
	
	this.size = function (object) {
		var len = 0
			for (var k in object)
				len++;
		return len;
	}	
	
	this.getUrlParam = function( name ){
		name = name.replace(/[\[]/,"\\\[").replace(/[\]]/,"\\\]");
		
		var regexS = "[\\?&]"+name+"=([^&#]*)";
		var regex = new RegExp( regexS );
		var results = regex.exec( window.location.href );
		
		if ( results == null )
			return "";
		else
			return results[1];
	 }
	
	this.createCookie = function(name,value,days) {
		if (days) {
			var date = new Date();
			date.setTime(date.getTime()+(days*24*60*60*1000));
			var expires = "; expires="+date.toGMTString();
		}
		else var expires = "";
		document.cookie = name+"="+value+expires+"; path=/";
	}

	this.readCookie = function(name) {
		var nameEQ = name + "=";
		var ca = document.cookie.split(';');
		for(var i=0;i < ca.length;i++) {
			var c = ca[i];
			while (c.charAt(0)==' ') c = c.substring(1,c.length);
			if (c.indexOf(nameEQ) == 0) return c.substring(nameEQ.length,c.length);
		}
		return null;
	}

	this.eraseCookie = function(name) {
		createCookie(name,"",-1);
	}
  
}

function LSFObserverLanguage(){
	var langtext = new Array()
	var lang = $('html').attr("lang");
	
	this.addLangText = function(key,language, text){
		if (langtext[key] == undefined) langtext[key] = new Array()
		langtext[key][language] = text
	}	

	this.getLangText = function(key){
		if (lang == null){
			lang = 'de'
		}
		
		if (langtext[key] != null){
			
			if (langtext[key][lang] != null){
				return langtext[key][lang]
			}else if (langtext[key]['de'] != null){
				return langtext[key]['de']
			}
		}
		return key
	}	
	
	
}
new LsfPageObserver(lsfPageObserver_parameterList).constructor();
