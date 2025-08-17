package it.personalproject.clienti.converters;

import java.util.Optional;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Service;

import it.personalproject.clienti.domain.ClienteModel;
import it.personalproject.clienti.entities.TisClienti;

@Service
public class ClientiEntityToClientiModelConverter implements Converter<TisClienti, ClienteModel>{

	@Override
	public ClienteModel convert(TisClienti source) {
		
		ClienteModel result = null;
		
		if(source != null) {
			
			result = new ClienteModel();
			
			result.setId(source.getId());
			result.setCodiceFiscale(source.getCodiceFiscale());
			result.setNome(source.getNome());
			result.setCognome(source.getCognome());
			result.setEmail(source.getEmail());
			result.setIndirizzo(source.getIndirizzo());
			result.setTelefono(source.getTelefono());
			result.setCitta(source.getCitta());
			result.setPaese(source.getPaese());
			result.setDataRegistrazione(source.getDataRegistrazione());
		}
		
		return result;
		
	}

}
