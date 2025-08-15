package it.personalproject.prodotti.domain;

import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.personalproject.prodotti.converters.ProdottiEntityToProdottiModelConverter;
import it.personalproject.prodotti.converters.ProdottiModelToProdottiEntityConverter;
import it.personalproject.prodotti.entities.TisProdotti;
import it.personalproject.prodotti.repositories.ProdottiRepository;
import jakarta.persistence.EntityNotFoundException;

@Service
public class ProdottiServiceImpl implements ProdottiService {
		
	private final ProdottiRepository prodottiRepository;
		
	private final ProdottiModelToProdottiEntityConverter prodottiModelToClientiEntityConverter;
	
	private final ProdottiEntityToProdottiModelConverter prodottiEntityToClientiModelConverter;
	
	@Autowired
	public ProdottiServiceImpl(ProdottiRepository prodottiRepository, ProdottiModelToProdottiEntityConverter prodottiModelToClientiEntityConverter, ProdottiEntityToProdottiModelConverter prodottiEntityToClientiModelConverter) {
		this.prodottiRepository = prodottiRepository;
		this.prodottiModelToClientiEntityConverter = prodottiModelToClientiEntityConverter;
		this.prodottiEntityToClientiModelConverter = prodottiEntityToClientiModelConverter;
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public ProdottiModel creaProdotto(ProdottiModel prodotto) {
		
		TisProdotti prodottoEntity = prodottiModelToClientiEntityConverter.convert(prodotto);
						
		prodottoEntity = prodottiRepository.save(prodottoEntity);
		
		return prodottiEntityToClientiModelConverter.convert(prodottoEntity);
		
	}

	@Override
	@Transactional(readOnly = true)
	public ProdottiModel getProdotto(Integer id) {
		
		ProdottiModel result = null;
	
		Optional<TisProdotti> clienteEntity = prodottiRepository.findById(id);
		
		if(clienteEntity.isPresent()) {
			result = prodottiEntityToClientiModelConverter.convert(clienteEntity.get());
		}
		
		return result;
		
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void cancellaProdotto(Integer id) {
		Optional<TisProdotti> prodottoEntity = prodottiRepository.findById(id);
		if(prodottoEntity.isPresent()) {
			prodottiRepository.delete(prodottoEntity.get());
		}
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public ProdottiModel aggiornaProdotto(ProdottiModel prodotto) {
		
		if(prodotto.getId() == null) {
			throw new IllegalArgumentException("ERRORE AGGIORNA PRODOTTO " + prodotto.getId() + " - ID PRODOTTO NON VALORIZZATI");
		}
		
		Optional<TisProdotti> optionalProdotto = prodottiRepository.findById(prodotto.getId());
		
		if(optionalProdotto.isPresent()) {
			
			TisProdotti prodottoEntity = optionalProdotto.get();
			
			prodottoEntity.setCategoria(prodotto.getCategoria());
			prodottoEntity.setCodiceProdotto(prodotto.getCodiceProdotto());
			prodottoEntity.setDataAggiornamento(prodotto.getDataAggiornamento());
			prodottoEntity.setDataCreazione(prodotto.getDataCreazione());
			prodottoEntity.setDescrizione(prodotto.getDescrizione());
			prodottoEntity.setNome(prodotto.getNome());
			prodottoEntity.setPrezzo(prodotto.getPrezzo());
			prodottoEntity.setQuantitaDisponibile(prodotto.getQuantitaDisponibile());
			
			prodottoEntity = prodottiRepository.save(prodottoEntity);
			
			return prodottiEntityToClientiModelConverter.convert(prodottoEntity);
			
		}
		
		else {
			throw new EntityNotFoundException("CLIENTE NON TROVATO ASSOCIATO A ID " + prodotto.getId());
		}
		
	}

	@Override
	@Transactional(readOnly = true)
	public List<ProdottiModel> getAllProdotti() {
		
		List<ProdottiModel> result = new LinkedList<>();
		
		List<TisProdotti> prodottiList = prodottiRepository.findAll();
		
		if(!prodottiList.isEmpty()) {
			prodottiList.stream().forEach(o -> result.add(prodottiEntityToClientiModelConverter.convert(o)));
		}
		
		return result;
		
	}

}
