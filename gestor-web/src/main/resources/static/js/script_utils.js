/* ================================================================================================== */
/**
 *	So deixar digitar um numero cuidado com keyCode e charCode
 *  keyCode � o numero da tecla que foi digitado no teclado e 
 *  charCode � o c�digo do caracter digitado
 *  dependendo do evento keyDown, keyPress, keyUp um o outro vem preenchido  
 *
 * @param anEvent
 * @return
 */
var doNumber = function (anEvent) {
 	var myEvent = window.event ? window.event : anEvent;

 	var key = myEvent.keyCode;

 	if  (	(key >= 48 && key <= 57) || 
 				(key >= 96 && key <= 105) || 
 				teclaEspecial(key) ||
 				myEvent.ctrlKey
 				){
 		//testar numero e se for ok com ultimo key informado continuar se nao impedir
 		
 		return true;
 	}
 		
 	return impedeEvento(anEvent);;

};
		 
 /**
  * Retorna true quando o caracter digitado, que vem no evento (parametro do m�todo), � 'num�rico', tab, del, right, left, enter ou ctrl.
  * Funciona no IE e Firefox.
  */
 var isNumber = function (anEvent) {
 	
	var myEvent = window.event ? window.event : anEvent;
	var key = myEvent.charCode? myEvent.charCode : myEvent.keyCode;

	//Quando '.'		
	if(window.event) {
		if(myEvent.keyCode==46) {
			return impedeEvento( anEvent );
		}
	}
	else {
		if(myEvent.charCode==46) {
			return impedeEvento( anEvent );
		}
	}			
	
 	if(!((key >= 48 && key <= 57) ||
 				teclaEspecial(key) ||
 				anEvent.ctrlKey)) {
 		return impedeEvento( anEvent );
 	}
 	
 	return true;

 };
		  
 /**
  * M�todo que informa se o caracter � 'num�rico', aceita: parte inteira e decimal, um delimitador (',' ou '.') de acordo com o locale, tab, del, right, left, enter, ctrl.
  *
  * @param anEvent evento ocorrido que contem o caracter digitado.
  * @param element objeto html que cont�m o valor atual do elemento digitado.
  *
  * Funciona no IE e Firefox.
  */
 var isNumberOrDelimiter = function (anEvent) {
	
	var origemEvento  = getSourceEvento( anEvent );
	
 	var DELIMITER_COMMA = 44;
 	var DELIMITER_DOT = 46;
 	var NEG_SIGN = 45;

	var myEvent = window.event ? window.event : anEvent;
	var key = myEvent.charCode? myEvent.charCode : myEvent.keyCode;	 			
	
 	
 	var formato = getFormatoNumeroSIP( origemEvento.className );
 	
 	
 	if(locale == 'en') {
		if(myEvent.charCode == DELIMITER_DOT && ((origemEvento.value.indexOf(".",0) > 0) || origemEvento.value.length == 0))
			return impedeEvento( anEvent );
		} else {
		if(key == DELIMITER_COMMA)
			if((origemEvento.value.search(/,/) == -1) &&  (origemEvento.value.length != 0)){
				return true;
			}else{
				return impedeEvento( anEvent );
			}
		
		if(myEvent.charCode == DELIMITER_DOT ) {
			return impedeEvento( anEvent );
		} 
	}
	
 	if(!((key >= 48 && key <= 57) ||
 				teclaEspecial(key) ||
 				anEvent.ctrlKey
 				|| (origemEvento.className.indexOf("positivo") < 0 && key == NEG_SIGN && (origemEvento.selectionStart == 0 || origemEvento.selectionEnd == 0) )) ) {
 		return impedeEvento( anEvent );
 	}
	
 	var valor = undefined;
 	if(origemEvento.selectionStart == origemEvento.selectionEnd && origemEvento.selectionEnd != 0 ){
 		valor = origemEvento.value.substring(0,origemEvento.selectionStart) + String.fromCharCode(key) + origemEvento.value.substring(origemEvento.selectionEnd, origemEvento.value.length); 
 	}else if(origemEvento.selectionStart == origemEvento.selectionEnd && origemEvento.selectionEnd == origemEvento.value.length ){
 		valor = origemEvento.value + String.fromCharCode(key);
 	}else if(origemEvento.selectionStart != origemEvento.selectionEnd){
 		valor = origemEvento.value.substring(0,origemEvento.selectionStart) + String.fromCharCode(key) + origemEvento.value.substring(origemEvento.selectionEnd, origemEvento.value.length);
 	}
 	
 	if((key >= 48 && key <= 57) && formato && !validarFormatoNumerico(formato, valor) ){
 		return impedeEvento( anEvent );
 	} 	
 	return true;
 };