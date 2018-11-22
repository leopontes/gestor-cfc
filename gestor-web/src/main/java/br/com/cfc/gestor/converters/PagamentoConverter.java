package br.com.cfc.gestor.converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import br.com.cfc.gestor.model.Pagamento;

@Component
public class PagamentoConverter implements Converter<String, Pagamento>{

	@Override
	public Pagamento convert(String value) {
		return null;
	}

}
