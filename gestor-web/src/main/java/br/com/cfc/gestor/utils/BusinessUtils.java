package br.com.cfc.gestor.utils;

import java.util.Optional;

import org.springframework.util.StringUtils;

public class BusinessUtils {

	public static Optional<Long> toCodigo(String matricula) {
		
		Long codigo = null;

		if(!StringUtils.isEmpty(matricula)) {
			if(matricula.contains("/")) {
				String strId = matricula.substring(0, matricula.indexOf("/"));
				
				if(!StringUtils.isEmpty(strId)) {
					codigo = Long.valueOf(strId);
				}
			}else {
				codigo = Long.valueOf(matricula);
			}
		}
		
		Optional<Long> retorno = Optional.ofNullable(codigo);
		
		return retorno;
	}

}
