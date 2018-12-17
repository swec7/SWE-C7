/*
  @description:
		Sprachabhängige Texte definieren. Diese Funktion gehört zu lsfpageobserver.js
		addLangText(<Identifizierer>, <Sprachcode>, <sprachcodeabhängiger Text>)
	@author: hillmer@his.de
*/
var lsfPageObserverLanguageInit = function(lSFObserverLanguage){
	
		lSFObserverLanguage.addLangText("_mtv_","de","Es konnte in dieser Ansicht kein Termin gefunden werden. \nMöchten Sie deshalb zur Semesteransicht springen?")
		lSFObserverLanguage.addLangText("_mtv_","en","No appointment found. \nWould you like to change to term view?");		
		lSFObserverLanguage.addLangText("_login_","de","Bitte geben Sie Ihre Benutzerkennung und Passwort ein.");
		lSFObserverLanguage.addLangText("_login_","en","Please enter your username and password.");
		lSFObserverLanguage.addLangText("_back_","de","Zurück");
		lSFObserverLanguage.addLangText("_back_","en","Back");
		lSFObserverLanguage.addLangText("_raumverwalteremail_","de","EMailadressen von Raumverwaltern sind nicht vorhanden oder inaktiv und es kann daher keine Benachrichtigungsemail an die jeweiligen Personen gesendet werden!");
		lSFObserverLanguage.addLangText("_raumverwalteremail_","en","The EMail adresses of selected facility manager are not available or inactive!");
				
}

