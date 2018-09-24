/**
 * Nesse arquivo tem todos as funcoes para o Calendario funcionar corretamente
 * 
 */

/*==================================================================================================*/
var cumulativeOffset = function (element) {
	var valueT = 0, valueL = 0;
	do {
		valueT += element.offsetTop || 0;
		valueL += element.offsetLeft || 0;
		element = element.offsetParent;
	} while (element);

	return [valueL, (valueT )];
};
			
/*==================================================================================================*/	
/**
 * <p>
 * Formata o conteudo de um campo texto em data dd/MM/yyyy (por exemplo 15/12/2009) ao digitar.
 * N�o deixa digitar numeros.
 * </p>
 * @param anEvent
 * @param campo
 * @return
 */
var formataData = function  ( anEvent, campo ) {
	var myEvent 		= window.event ? window.event : anEvent;
	var key 			= (myEvent.charCode && myEvent.charCode != 0) ? myEvent.charCode : myEvent.keyCode ;

	if ( !campo ){
		campo = getSourceEvento( anEvent );
	}
	
	if ( teclaEspecial( key ) ) return;

	var posEnd 		= getSelectionEnd( 		campo );

	var tempArr = campo.value.split("/");
	var temp 	= tempArr.join("");

	if ( campo.maxLength == 10 ){
		if ( temp.length == 2 ){
			campo.value = temp + "/";
		}else if (temp.length > 2 && temp.length <= 4 ){
			campo.value = temp.substr(0,2) + "/" + temp.substr(2);
		}else if( temp.length > 4 ){
			campo.value = temp.substr(0,2) + "/" + temp.substr(2,2) + "/" + temp.substr(4);
		}
	}
	else if ( campo.maxLength == 7 ){
		if ( temp.length > 2 ){
			campo.value = temp.substr(0,2) + "/" + temp.substr(2);
		}
	}

	if ( posEnd == 3 || posEnd == 6 ) posEnd++;
	
	if ( campo.selectionEnd ){
		if ( posEnd < campo.value.length - 1 ){
			campo.selectionEnd 	= posEnd;
		}
	}else{
		if ( posEnd < campo.value.length - 1 ){
			var range = campo.createTextRange();
			range.move(	'character', posEnd );
			range.select();
		}
	}


};
/**
 * <p>
 * Coloca o texto de um campo no formato MM/yyyy ao digitar dentro de uma caixa 
 * de texto que s� pode receber uma data como entrarda
 * </p>
 * @param event
 * @return
 */
/*==================================================================================================*/
var	formatarMes = function ( event ){

	var target = getSourceEvento ( event );

	var dtTipo = target.datatipo ? target.datatipo : target.getAttribute("datatipo");

	if (dtTipo && dtTipo.indexOf("m") >= 0 ){
		target.value = "01" + target.value.substr(2,target.value.length);
	}
	
};


/*==================================================================================================*/
/**
 * <p>
 * Coloca o texto de um campo no formato hh:mm ao digitar dentro de uma caixa 
 * de texto que s� pode receber uma data como entrarda
 * </p>
 * @param anEvent
 * @return
 */
var formataHora = function ( anEvent ){

	var myEvent 		= window.event ? window.event : anEvent;
	var campo			= getSourceEvento( myEvent );
	var key 			= myEvent.keyCode;

	if ( !campo ){
		campo = getSourceEvento( anEvent );
	}
	
	if( campo.value == "" ){
		return;
	}
	
	if ( teclaEspecial( key ) ) return;

	var posEnd 		= getSelectionEnd( 		campo );

	var tempArr = campo.value.split(":");
	var temp 	= tempArr.join("");

	if ( campo.maxLength == 5 ){
		if ( temp.length > 0  && temp.length<=1 ){
			campo.value =  temp + ":";
		}else if ( temp.length > 1 && temp.length <= 3){
			campo.value = temp.substr(0,1) + ":" + temp.substr(1);
		}else if ( temp.length <= 4 ){
			campo.value = temp.substr(0,2) + ":" + temp.substr(2);
		}
	}
	

	if ( posEnd == 3 ) posEnd++;
	
	if ( campo.selectionEnd ){
		if ( posEnd < campo.value.length - 1 ){
			campo.selectionEnd 	= posEnd;
		}
	}else{
		if ( posEnd < campo.value.length - 1 ){
			var range = campo.createTextRange();
			range.move(	'character', posEnd );
			range.select();
		}
	}
	
};



var formataHoraPadrao24h = function ( anEvent ){

	var myEvent 		= window.event ? window.event : anEvent;
	var campo			= getSourceEvento( myEvent );
	var key 			= myEvent.keyCode;
	var isContemDoiPontos = campo.value.indexOf(":") != -1;
	
	if (campo.value.length > 4 && !isContemDoiPontos) {
		if (parseInt(campo.value) > 22222) {			
			campo.value = "";
		}
	}

	if ( !campo ){
		campo = getSourceEvento( anEvent );
	}
	
	if( campo.value == "" ){
		return;
	}
	
	if ( teclaEspecial( key ) ) return;

	var posEnd 		= getSelectionEnd( 		campo );

	var tempArr = campo.value.split(":");
	var temp 	= tempArr.join("");

	if ( campo.maxLength == 5 ){
		if ( temp.length > 0  && temp.length<=1 ){
			campo.value =  temp + ":";
		}
		else if ( temp.length > 1 && temp.length <= 3){
			var primeiroDigito = temp.substr(0,1);
			var segundoDigito = temp.substr(1,1);
			
			campo.value = parseInt(primeiroDigito) <= 2? primeiroDigito+":" : ":";  
			
			
			if ( (parseInt(primeiroDigito)+parseInt(segundoDigito)) <= 23) {
				campo.value += temp.substr(1);
			}
			
			//campo.value = temp.substr(0,1) + ":" + temp.substr(1);
			
		}
		else if ( temp.length <= 4 ){
			var valorHora = temp.substr(0,2);
			var valorMinuto = temp.substr(2);
			
			campo.value = parseInt(valorHora) <= 23? valorHora+":" : ":"; 
			
			if (parseInt(valorMinuto) <= 59) {
				campo.value += valorMinuto;
			}
		}
	}
	

	if ( posEnd == 3 ) posEnd++;
	
	if ( campo.selectionEnd ){
		if ( posEnd < campo.value.length - 1 ){
			campo.selectionEnd 	= posEnd;
		}
	}else{
		if ( posEnd < campo.value.length - 1 ){
			var range = campo.createTextRange();
			range.move(	'character', posEnd );
			range.select();
		}
	}
	
	
	if (!isContemDoiPontos) {
		String.prototype.replaceAt=function(index, character) {
			return this.substr(0, index) + character + this.substr(index+character.length);
		};
		
		campo.value = campo.value.replaceAt(2, ":");
	}
};












/*==================================================================================================*/
/**
 * retorna um boolean indicando se uma tecla especial foi apertado
 * @param key
 * @return
 */
var teclaEspecial = function ( key ){
	var DEL_KEY 		= 46;
	var BACKSPACE_KEY 	= 8;
	var TAB_KEY	 		= 9;
	var LEFT_KEY 		= 37;
	var RIGHT_KEY 		= 39;
	var TOP_KEY 		= 38;
	var BOTTOM_KEY 		= 40;	
 	var HOME_KEY		= 36;
 	var END_KEY			= 35;
 	
	return key == DEL_KEY || key == BACKSPACE_KEY || key == LEFT_KEY || 
				key == RIGHT_KEY || key == TOP_KEY || key == BOTTOM_KEY || key == TAB_KEY
				|| key == HOME_KEY || key == END_KEY ;
};

/* ================================================================================================== */
/**
 * 
 * @param event
 * @return
 */
var validaData = function( event ){
	var target = getSourceEvento( event );
	var formato = "DD/MM/YYYY"; 
	if(target.maxLength && target.maxLength == 7 ){
		formato = "MM/YYYY";	
	}
	if (target.value != "" && !global.isValidDate(target.value,formato ) ){
		alert( errors_date.replace("{0}",target.value)  );
		impedeEvento( event );
		window.setTimeout("$(\"#" + target.id + "\").focus()", 10);
		window.setTimeout("$(\"#" + target.id + "\").select()", 15);
	
		return impedeEvento( event );
	}
	return true;
};

(function(global, undefined){
	
	global.validaData = { 
		id : "",
		
		blur : function( event ){
			var target = getSourceEvento( event );
			var formato = "DD/MM/YYYY"; 
			if(target.maxLength && target.maxLength == 7 ){
				formato = "MM/YYYY";	
			}
			if (target.value != "" && !global.isValidDate(target.value,formato ) ){
				alert( errors_date.replace("{0}",target.value)  );
				impedeEvento( event );
				
				global.validaData.id = ID(target.id);
				
				window.setTimeout(function(){ $( global.validaData.id  ).focus(); $( global.validaData.id  ).select();}, 10);
		
				return impedeEvento( event );
			}
			return true;
		}
	};
	
}(window.global = window.global || {}));