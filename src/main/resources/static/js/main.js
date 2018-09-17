
$(document).ready(function(){
	

	$("#CPF").mask("999.999.999-99");
	$("#CEP").mask("99999-999");
	$("#dataNascimento").datepicker();
	$("#dataExpedicao").datepicker();
	$("#contato1").mask("(99) 9999-9999");
	$("#contato2").mask("(99) 99999-9999");
	$("#contato3").mask("(99) 99999-9999");

	$("#tipoPagamento").change(function(){
		
		if($(this).val() == 'DINHEIRO'){
			$.each($("#formaPagamento"), function(i,v){
				if(v.val() == 'A_VISTA'){
					v.attr("selected", 'selected');
				}
			});
		}
		
		$("#formaPagamento").val("A_VISTA");
	});
	
	$("#CEP").blur(function(){
		$.ajax({
			type: 'GET',
			url: 'http://viacep.com.br/ws/'+$(this).val()+'/json/',
			success: function(cep){
				
				$("#endereco").val(cep.logradouro);
				$("#bairro").val(cep.bairro);
				$("#cidade").val(cep.localidade);
				$("#UF").val(cep.uf);
			}
		});
	});
	
});