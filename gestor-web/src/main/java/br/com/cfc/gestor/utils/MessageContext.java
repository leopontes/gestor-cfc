package br.com.cfc.gestor.utils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

@Component
@Scope(value=WebApplicationContext.SCOPE_REQUEST, proxyMode=ScopedProxyMode.TARGET_CLASS)
public class MessageContext implements Serializable{

	private static final long serialVersionUID = 7609653178578999744L;

	private String title = "Aviso";
	
	private Collection<String> messages = new ArrayList<>();
	
	public MessageContext() {
		super();
		this.title = "Aviso";
		this.messages = new ArrayList<>();
	}
	
	public Collection<String> getMessages() {
		return messages;
	}

	public void setMessages(Collection<String> messages) {
		this.messages = messages;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	
	public void add(String message) {
		this.messages.add(message);
	}
}
