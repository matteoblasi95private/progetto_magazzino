package it.personalproject.clienti.domain;

import java.util.Collection;

public interface ClientiService {
	
	public ClienteModel creaCliente(ClienteModel ordine);
	
	public ClienteModel getCliente(Integer id);
	
	public void cancellaCliente(Integer id);
	
	public ClienteModel aggiornaCliente(ClienteModel ordine);
	
	public Collection<ClienteModel> getAllClienti();

}
