package it.personalproject.clienti.domain;

import java.util.Collection;
import java.util.Optional;

import it.personalproject.clienti.exceptions.ClienteNotFoundException;

public interface ClientiService {
	
	public ClienteModel creaCliente(ClienteModel ordine);
	
	public Optional<ClienteModel> getCliente(Integer id);
	
	public ClienteModel cancellaCliente(Integer id) throws ClienteNotFoundException;
	
	public ClienteModel aggiornaCliente(ClienteModel ordine) throws ClienteNotFoundException;
	
	public Collection<ClienteModel> getAllClienti();

}
