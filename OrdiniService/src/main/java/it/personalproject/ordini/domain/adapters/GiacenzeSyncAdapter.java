package it.personalproject.ordini.domain.adapters;

import java.util.Collection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import it.personalproject.ordini.domain.MagazzinoModel;
import it.personalproject.ordini.domain.ports.GiacenzePort;

@Component
public class GiacenzeSyncAdapter implements GiacenzePort {
	
    private static final Logger logger = LoggerFactory.getLogger(GiacenzeSyncAdapter.class);
	
	private final String giacenzeUrl;
	
	private final WebClient webClient;
	
	public GiacenzeSyncAdapter(@Value("${giacenze.rest.url}") String giacenzeUrl, WebClient.Builder webClientBuilder) {
		this.giacenzeUrl = giacenzeUrl;
		this.webClient = webClientBuilder.baseUrl(this.giacenzeUrl).build();
	}

	@Override
	public Collection<MagazzinoModel> getMagazziniConDisponibilitaProdotto(Integer idProdotto, Integer quantita) {
		
		try {
			
			Collection<MagazzinoModel> response = webClient
					.get()
					.uri(uriBuilder -> uriBuilder
							.path("/giacenze/disponibilita")
							.queryParam("idprodotto", idProdotto)
							.queryParam("quantita", quantita)
							.build())
					.retrieve()
					.bodyToMono(new ParameterizedTypeReference<Collection<MagazzinoModel>>() {
					})
					.block();
					return response;
			
		}
		
		catch(Exception e) {
            logger.error("ERRORE REST [ID-PRODOTTO: {}] [QUANTITA: {}]", idProdotto, quantita, e);
			throw e;
		}
		
	}

}
