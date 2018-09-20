
$(document).ready(function(){

	var anoLimite = (new Date().getFullYear() - 18);
	
	$("#cpf").mask("999.999.999-99");
	$("#cep").mask("99999-999");
	$("#quantidade").mask("99");

	$("#aluno-matricula").blur(function(){
		var matricula = $("#aluno-matricula").val();
		
		$.ajax({
			url: '/gestao/aluno/' + matricula,
			method: 'GET',
			success: function(data){
				if(data){
					$("#aluno-nome").val(data.nome);
					$("#veiculos").removeAttr("disabled");
				}
			}
		});
	});
	
	$("#veiculos").change(function(){
		$("#periodoAgendamentos").removeAttr("disabled");
	});
	
	$("#periodoAgendamentos").datepicker({
		dateFormat: 'mm/yy',
		changeMonth: true,
	    changeYear: true,
	    showButtonPanel: true,
	    onClose: function(dateText, inst) {  
            var month = $("#ui-datepicker-div .ui-datepicker-month :selected").val(); 
            var year = $("#ui-datepicker-div .ui-datepicker-year :selected").val(); 
            $(this).datepicker('setDate', new Date(year, month, 1)); 
            carregarEscalas(month, year, $("#veiculos").val(), $("#aluno-matricula").val());
	    }
	});
	
	$("#periodoAgendamentos").focus(function () {
		$(".ui-datepicker-calendar").hide();
		$("#ui-datepicker-div").position({
			  my: "center top",
			  at: "center bottom",
			  of: $(this)
			});
		
	});
	
	$("#valorTotal").maskMoney({
		prefix:'R$ ', 
		allowNegative: true, 
		thousands:'.', 
		decimal:',', 
		affixesStay: false
	});
	
	$("#valorParcela").maskMoney({
		prefix:'R$ ', 
		allowNegative: true, 
		thousands:'.', 
		decimal:',', 
		affixesStay: false
	});
	
	$("#dataNascimento").datepicker({
		changeMonth: true,
		changeYear: true,
		yearRange: '1910:' + anoLimite
	});
	
	$("#dataEmissao").datepicker({
		changeMonth: true,
		changeYear: true,
		yearRange: '1910:+0'
	});
	
	$("#contato1").mask("(99) 9999-9999");
	$("#contato2").mask("(99) 99999-9999");
	$("#contato3").mask("(99) 99999-9999");

	$("#quantidade").val(1);
	$("#formaPagamento").val("A_VISTA");
	
	$("#tipoPagamento").change(function(){
		
		if($(this).val() == 'CARNE'){
			$("#formaPagamento").val("PARCELADO");
			return;
		}
		
		$("#formaPagamento").val("A_VISTA");
	});
	
	$("#cep").blur(function(){
		$.ajax({
			type: 'GET',
			url: 'http://viacep.com.br/ws/'+$(this).val()+'/json/',
			success: function(cep){
				
				$("#endereco").val(cep.logradouro);
				$("#bairro").val(cep.bairro);
				$("#cidade").val(cep.localidade);
				$("#uf").val(cep.uf);
			}
		});
	});
	
	$("#valorTotal, #quantidade").keyup(function(){
		
		var valorTotal = $("#valorTotal").val().replace("R$", "");
		valorTotal = valorTotal.replace(".", "");
		valorTotal = valorTotal.replace(",", ".");
		var valorParcelado = parseFloat(valorTotal)/parseFloat($("#quantidade").val());
		
		$("#valorParcela").val(valorParcelado);
	});
	
	$("#btnGerarPagamento").click(function(){
		
		var tipoPagamento    = $("#tipoPagamento :selected").text();
		var formaPagamento   = $("#formaPagamento :selected").text();
		var numeroDeParcelas = parseInt($("#quantidade").val());
		var valorParcela     = $("#valorParcela").val();
		
		$("#tb-pagamentos tbody tr").remove();
		
		for(var i=0; i<numeroDeParcelas; i++){
			var tr = $(document.createElement('tr'));
			
			var td1 = $(document.createElement('td'));
			var td2 = $(document.createElement('td'));
			var td3 = $(document.createElement('td'));
			var td4 = $(document.createElement('td'));
			var td5 = $(document.createElement('td'));
			var td6 = $(document.createElement('td'));
			
			$(td1).text(i+1);
			$(td2).text();
			$(td3).text(tipoPagamento);
			$(td4).text(formaPagamento);
			$(td5).text(valorParcela);
			$(td6).text();
			
			$(td1).css({"text-align" : "center"});
			$(td2).css({"text-align" : "center"});
			$(td3).css({"text-align" : "center"});
			$(td4).css({"text-align" : "center"});
			$(td5).css({"text-align" : "center"});
			$(td6).css({"text-align" : "center"});
			
			$(tr).append(td1);
			$(tr).append(td2);
			$(tr).append(td3);
			$(tr).append(td4);
			$(tr).append(td5);
			$(tr).append(td6);
			
			$("#tb-pagamentos tbody").append(tr);
		}
	});
});

};