package br.com.cfc.gestor.controller.form;

import java.io.Serializable;

public abstract class Form<T> implements Serializable {

	private static final long serialVersionUID = -8422624033009449376L;
	
	public abstract T toBean();
	
	public abstract void toForm(T bean);

}
