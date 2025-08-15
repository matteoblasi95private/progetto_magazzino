package it.personalproject.clienti.domain;

import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.personalproject.clienti.converters.ClientiEntityToClientiModelConverter;
import it.personalproject.clienti.converters.ClientiModelToClientiEntityConverter;
import it.personalproject.clienti.entities.TisClienti;
import it.personalproject.clienti.repositories.ClientiRepository;
import jakarta.persistence.EntityNotFoundException;

@Service
public class ClientiServiceImpl implements ClientiService {
		
	private final ClientiRepository clientiRepository;
		
	private final ClientiModelToClientiEntityConverter clientiModelToClientiEntityConverter;
	
	private final ClientiEntityToClientiModelConverter clientiEntityToClientiModelConverter;
	
	@Autowired
	public ClientiServiceImpl(ClientiRepository clientiRepository, ClientiModelToClientiEntityConverter clientiModelToClientiEntityConverter, ClientiEntityToClientiModelConverter clientiEntityToClientiModelConverter) {
		this.clientiRepository = clientiRepository;
		this.clientiModelToClientiEntityConverter = clientiModelToClientiEntityConverter;
		this.clientiEntityToClientiModelConverter = clientiEntityToClientiModelConverter;
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public ClienteModel creaCliente(ClienteModel cliente) {
		
		TisClienti clienteEntity = clientiModelToClientiEntityConverter.convert(cliente);
						
		clienteEntity = clientiRepository.save(clienteEntity);
		
		return clientiEntityToClientiModelConverter.convert(clienteEntity);
		
	}

	@Override
	@Transactional(readOnly = true)
	public ClienteModel getCliente(Integer id) {
		
		ClienteModel result = null;
	
		Optional<TisClienti> clienteEntity = clientiRepository.findById(id);
		
		if(clienteEntity.isPresent()) {
			result = clientiEntityToClientiModelConverter.convert(clienteEntity.get());
		}
		
		return result;
		
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void cancellaCliente(Integer id) {
		Optional<TisClienti> clientEntity = clientiRepository.findById(id);
		if(clientEntity.isPresent()) {
			clientiRepository.delete(clientEntity.get());
		}
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public ClienteModel aggiornaCliente(ClienteModel cliente) {
		
		if(cliente.getId() == null) {
			throw new IllegalArgumentException("ERRORE AGGIORNA cliente " + cliente.getId() + " - ID CLIENTE O ID PRODOTTO NON VALORIZZATI");
		}
		
		Optional<TisClienti> optionalCliente = clientiRepository.findById(cliente.getId());
		
		if(optionalCliente.isPresent()) {
			
			TisClienti clienteEntity = optionalCliente.get();
			
			clienteEntity.setCodiceFiscale(cliente.getCodiceFiscale());
			clienteEntity.setNome(cliente.getNome());
			clienteEntity.setCognome(cliente.getCognome());
			clienteEntity.setEmail(cliente.getEmail());
			clienteEntity.setCap(cliente.getCap());
			clienteEntity.setTelefono(cliente.getTelefono());
			clienteEntity.setIndirizzo(cliente.getIndirizzo());
			clienteEntity.setCitta(cliente.getCitta());
			clienteEntity.setPaese(cliente.getPaese());
			clienteEntity = clientiRepository.save(clienteEntity);
			
			return clientiEntityToClientiModelConverter.convert(clienteEntity);
			
		}
		
		else {
			throw new EntityNotFoundException("CLIENTE NON TROVATO ASSOCIATO A ID " + cliente.getId());
		}
		
	}

	@Override
	@Transactional(readOnly = true)
	public List<ClienteModel> getAllClienti() {
		
		List<ClienteModel> result = new LinkedList<>();
		
		List<TisClienti> clientiList = clientiRepository.findAll();
		
		if(!clientiList.isEmpty()) {
			clientiList.stream().forEach(o -> result.add(clientiEntityToClientiModelConverter.convert(o)));
		}
		
		return result;
		
	}

}
