package it.personalproject.clienti.domain;

import java.util.List;

public interface ClientiService {
	
	public ClienteModel creaCliente(ClienteModel ordine);
	
	public ClienteModel getCliente(Integer id);
	
	public void cancellaCliente(Integer id);
	
	public ClienteModel aggiornaCliente(ClienteModel ordine);
	
	public List<ClienteModel> getAllClienti();

}
