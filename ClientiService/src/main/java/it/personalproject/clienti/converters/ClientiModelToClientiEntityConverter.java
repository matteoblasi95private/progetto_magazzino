package it.personalproject.clienti.converters;

import java.util.Optional;

import org.jspecify.annotations.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Service;

import it.personalproject.clienti.domain.ClienteModel;
import it.personalproject.clienti.entities.TisClienti;
import it.personalproject.clienti.repositories.ClientiRepository;

@Service
public class ClientiModelToClientiEntityConverter implements Converter<ClienteModel, TisClienti> {

	@Override
	public @Nullable TisClienti convert(ClienteModel source) {
		
		TisClienti result = null;
		
		if(source != null) {
			
			result = new TisClienti();
			
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
