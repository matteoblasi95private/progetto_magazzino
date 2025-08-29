package it.personalproject.magazzini.domain;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.personalproject.magazzini.converters.MagazziniEntityToMagazziniModelConverter;
import it.personalproject.magazzini.converters.MagazziniModelToMagazziniEntityConverter;
import it.personalproject.magazzini.entities.TisMagazzini;
import it.personalproject.magazzini.entities.TisMagazzinoStoricoMovimenti;
import it.personalproject.magazzini.repositories.MagazziniRepository;
import it.personalproject.magazzini.repositories.StoricoMagazziniRepository;
import jakarta.persistence.EntityNotFoundException;

@Service
public class MagazzinoServiceImpl implements MagazzinoService {
	
	private final MagazziniRepository magazziniRepository;
	
	private final StoricoMagazziniRepository storicoMagazziniRepository;
	
	private final MagazziniModelToMagazziniEntityConverter magazziniModelToMagazziniEntityConverter;
	
	private final MagazziniEntityToMagazziniModelConverter magazziniEntityToMagazziniModelConverter;
	
	@Autowired
	public MagazzinoServiceImpl(MagazziniRepository magazzinoRepository, StoricoMagazziniRepository storicoMagazziniRepository, MagazziniModelToMagazziniEntityConverter magazziniModelToMagazziniEntityConverter, MagazziniEntityToMagazziniModelConverter magazziniEntityToMagazziniModelConverter) {
		this.magazziniRepository = magazzinoRepository;
		this.storicoMagazziniRepository = storicoMagazziniRepository;
		this.magazziniModelToMagazziniEntityConverter = magazziniModelToMagazziniEntityConverter;
		this.magazziniEntityToMagazziniModelConverter = magazziniEntityToMagazziniModelConverter;
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public MagazzinoModel creaMagazzino(MagazzinoModel magazzino) {
		
		TisMagazzini magazzinoEntity = magazziniModelToMagazziniEntityConverter.convert(magazzino);
		
		magazzinoEntity.setDataCreazione(LocalDateTime.now());
				
		magazzinoEntity = magazziniRepository.save(magazzinoEntity);
		
		scriviStoricoMagazzino(magazzinoEntity, "CREAZIONE");
		
		return magazziniEntityToMagazziniModelConverter.convert(magazzinoEntity);
		
	}

	@Override
	@Transactional(readOnly = true)
	public MagazzinoModel getMagazzino(Integer id) {
		
		MagazzinoModel result = null;
	
		Optional<TisMagazzini> magazziniEntity = magazziniRepository.findById(id);
		
		if(magazziniEntity.isPresent()) {
			result = magazziniEntityToMagazziniModelConverter.convert(magazziniEntity.get());
		}
		
		return result;
		
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void cancellaMagazzino(Integer id) {
		
		TisMagazzini magazzinoEntity = magazziniRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("MAGAZZINO NON TROVATO ASSOCIATO A ID " + id));
		
		magazzinoEntity.setAttivo(false);
		
		magazziniRepository.save(magazzinoEntity);
		
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public MagazzinoModel aggiornaMagazzino(MagazzinoModel magazzino) {
		
		if(magazzino.getId() == null) {
			throw new IllegalArgumentException("ERRORE AGGIORNA ORDINE " + magazzino.getId() + " - ID SPEDIZIONE NON VALORIZZATO");
		}
		
		TisMagazzini magazzinoEntity = magazziniRepository.findById(magazzino.getId()).orElseThrow(() -> new EntityNotFoundException("SPEDIZIONE NON TROVATA ASSOCIATO A ID " + magazzino.getId()));
				
		
		return magazziniEntityToMagazziniModelConverter.convert(magazziniRepository.save(magazzinoEntity));
		
	}

	@Override
	@Transactional(readOnly = true)
	public List<MagazzinoModel> getAllMagazzini() {
		
		List<MagazzinoModel> result = new LinkedList<>();
		
		List<TisMagazzini> magazziniList = magazziniRepository.findAll();
		
		if(!magazziniList.isEmpty()) {
			magazziniList.stream().forEach(m -> result.add(magazziniEntityToMagazziniModelConverter.convert(m)));
		}
		
		return result;
		
	}
	
	@Override
	public void scriviStoricoMagazzino(TisMagazzini magazzino, String note) {
		
		TisMagazzinoStoricoMovimenti storico = new TisMagazzinoStoricoMovimenti();
		storico.setDataMovimento(LocalDateTime.now());
		storicoMagazziniRepository.save(storico);
		
	}

	@Override
	public Collection<MagazzinoModel> getMagazziniCitta(String citta) {
		
		Collection<MagazzinoModel> magazziniCitta = new LinkedList<>();
		
		Collection<TisMagazzini> magazziniCittaEntities = magazziniRepository.getMagazziniCitta(citta);
		
		if(!magazziniCittaEntities.isEmpty()) {
			magazziniCittaEntities.stream().forEach(m -> magazziniCitta.add(magazziniEntityToMagazziniModelConverter.convert(m)));
		}
		
		return magazziniCitta;
	}

	@Override
	public Collection<MagazzinoModel> getMagazziniAttivi() {
		
		Collection<MagazzinoModel> magazziniAttivi = new LinkedList<>();

		Collection<TisMagazzini> magazziniAttiviEntities = magazziniRepository.findByAttivo(true);
		
		if(!magazziniAttiviEntities.isEmpty()) {
			magazziniAttiviEntities.stream().forEach(m -> magazziniAttivi.add(magazziniEntityToMagazziniModelConverter.convert(m)));
		}
		
		return magazziniAttivi;
	}

	@Override
	public Collection<MagazzinoModel> getMagazziniPaese(String paese) {
		
		Collection<MagazzinoModel> magazziniPaese = new LinkedList<>();
		
		Collection<TisMagazzini> magazziniPaeseEntities = magazziniRepository.getMagazziniCitta(paese);
		
		if(!magazziniPaeseEntities.isEmpty()) {
			magazziniPaeseEntities.stream().forEach(m -> magazziniPaese.add(magazziniEntityToMagazziniModelConverter.convert(m)));
		}
		
		return magazziniPaese;
		
	}

}
