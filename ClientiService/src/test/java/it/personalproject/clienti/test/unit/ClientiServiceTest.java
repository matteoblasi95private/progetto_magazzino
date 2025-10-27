package it.personalproject.clienti.test.unit;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import it.personalproject.clienti.converters.ClientiEntityToClientiModelConverter;
import it.personalproject.clienti.converters.ClientiModelToClientiEntityConverter;
import it.personalproject.clienti.domain.ClienteModel;
import it.personalproject.clienti.domain.ClientiServiceImpl;
import it.personalproject.clienti.entities.TisClienti;
import it.personalproject.clienti.exceptions.ClienteNotFoundException;
import it.personalproject.clienti.repositories.ClientiRepository;

@ExtendWith(MockitoExtension.class)
public class ClientiServiceTest {
	
	@Mock
	private ClientiRepository clientiRepository;
	
	@Spy
	private ClientiModelToClientiEntityConverter clientiModelToClientiEntityConverter = new ClientiModelToClientiEntityConverter();
	
	@Spy
	private ClientiEntityToClientiModelConverter clientiEntityToClientiModelConverter = new ClientiEntityToClientiModelConverter();
	
	@InjectMocks
	private ClientiServiceImpl clientiService;
	
	@Test
	void createCliente_success() {
		
		when(clientiRepository.save(any(TisClienti.class))).thenAnswer(i -> i.getArgument(0));
		
		var clienteModel = new ClienteModel(null, "RSSMRA84A01H501U", "Mario", "Rossi", "aa@bb", "1234", "Via Garibaldi 3", "Roma", "00042", "Italia");
		
		var clienteCreato = clientiService.creaCliente(clienteModel);
		
		var captor = ArgumentCaptor.forClass(TisClienti.class);
	    verify(clientiRepository).save(captor.capture());
	    var salvata = captor.getValue();
				
	    assertThat(salvata.getCodiceFiscale()).isEqualTo("RSSMRA84A01H501U");
		assertThat(clienteCreato).isNotNull();
		assertThat(clienteCreato.getCodiceFiscale()).isEqualTo(clienteModel.getCodiceFiscale());
		
	}
	
	@Test
	void getClientePresent() {
		
		var clienteEntity = new TisClienti("RSSMRA84A01H501U", "Mario", "Rossi", "aa@bb", "1234", "Via Garibaldi 3", "Roma", "00042", "Italia");
		clienteEntity.setId(1);

		var clienteModel = new ClienteModel(1, "RSSMRA84A01H501U", "Mario", "Rossi", "aa@bb", "1234", "Via Garibaldi 3", "Roma", "00042", "Italia");
				
		when(clientiRepository.findById(1)).thenReturn(Optional.of(clienteEntity));
		
		var cliente = clientiService.getCliente(1);
		
		assertThat(cliente).isPresent();
		assertThat(cliente.get().getCodiceFiscale()).isEqualTo(clienteModel.getCodiceFiscale());
				
	}
	
	
	@Test
	void getClienteNotPresent() {
		
		when(clientiRepository.findById(2)).thenReturn(Optional.empty());
				
		var cliente = clientiService.getCliente(2);

		assertThat(cliente).isNotPresent();
		
		verify(clientiEntityToClientiModelConverter, never()).convert(any());
	}
	
	@Test
	void cancellaClientePresent() throws ClienteNotFoundException {
		
		var clienteEntity = new TisClienti("RSSMRA84A01H501U", "Mario", "Rossi", "aa@bb", "1234", "Via Garibaldi 3", "Roma", "00042", "Italia");
		clienteEntity.setId(1);

		
		when(clientiRepository.findById(1)).thenReturn(Optional.of(clienteEntity));
		
		clientiService.cancellaCliente(1);
		
		assertThat(clienteEntity.getAttivo()).isEqualTo(false);
		
	}
	
	@Test
	void cancellaClienteNotPresent() throws ClienteNotFoundException {
				
		when(clientiRepository.findById(1)).thenReturn(Optional.empty());
				
		assertThatThrownBy(() -> clientiService.cancellaCliente(1)).isInstanceOf(ClienteNotFoundException.class);
		
	}
	
	@Test
	void aggiornaClientiIdNonValorizzato() {
		
		var clienteModel = new ClienteModel(null, "RSSMRA84A01H501U", "Mario", "Rossi", "aa@bb", "1234", "Via Garibaldi 3", "Roma", "00042", "Italia");
		
		assertThatThrownBy(() -> clientiService.aggiornaCliente(clienteModel)).isInstanceOf(IllegalArgumentException.class);
		
	}
	
	@Test
	void aggiornaClientiNotPresent() {
		
		var clienteModel = new ClienteModel(1, "RSSMRA84A01H501U", "Mario", "Rossi", "aa@bb", "1234", "Via Garibaldi 3", "Roma", "00042", "Italia");

		when(clientiRepository.findById(1)).thenReturn(Optional.empty());
		
		assertThatThrownBy(() -> clientiService.aggiornaCliente(clienteModel)).isInstanceOf(ClienteNotFoundException.class);
		
	}
	
	@Test
	void aggiornaClientiPresent() throws ClienteNotFoundException {
		
		when(clientiRepository.save(any(TisClienti.class))).thenAnswer(i -> i.getArgument(0));
		
		var clienteEntity = new TisClienti("RSSMRA84A01H501U", "Mario", "Rossi", "aa@bb", "1234", "Via Garibaldi 3", "Roma", "00042", "Italia");
		clienteEntity.setId(1);
		
		var clienteAggiornato = new ClienteModel(1, "RSSMRA85A01H501U", "Luca", "Rossi", "aa@bb", "1234", "Via Garibaldi 3", "Roma", "00042", "Italia");
		
		when(clientiRepository.findById(1)).thenReturn(Optional.of(clienteEntity));
		
		var aggiornato = clientiService.aggiornaCliente(clienteAggiornato);
		
		assertThat(aggiornato).isNotNull();
		assertThat(clienteEntity.getNome()).isEqualTo(clienteAggiornato.getNome());
		assertThat(aggiornato.getNome()).isEqualTo(clienteAggiornato.getNome());

		
	}
	
	@Test
	void getAllClienti() {
		
		var clienteEntity1 = new TisClienti("RSSMRA84A01H501U", "Mario", "Rossi", "aa@bb", "1234", "Via Garibaldi 3", "Roma", "00042", "Italia");

		var clienteEntity2 = new TisClienti("RSSMRA84A01H501U", "Luca", "Rossi", "bb@cc", "3456", "Via Roma 3", "Roma", "00042", "Italia");

		var entityMockList = List.of(clienteEntity1, clienteEntity2);
		
		when(clientiRepository.findAll()).thenReturn(entityMockList);
		
		var listAllClienti = clientiService.getAllClienti();
		
		assertThat(listAllClienti).hasSize(2);
		
		Iterator<ClienteModel> it = listAllClienti.iterator();
		
		var clienteEffettivo1 = it.next();
		
		var clienteEffettivo2 = it.next();
		
		assertThat(clienteEffettivo1.getCodiceFiscale()).isEqualTo(clienteEntity1.getCodiceFiscale());
		
		assertThat(clienteEffettivo2.getCodiceFiscale()).isEqualTo(clienteEntity2.getCodiceFiscale());

	}

}
