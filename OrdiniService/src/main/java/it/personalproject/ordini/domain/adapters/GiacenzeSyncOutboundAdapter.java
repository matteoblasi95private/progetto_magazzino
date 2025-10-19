package it.personalproject.ordini.domain.adapters;

import java.util.Collection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpStatusCode;
import org.springframework.security.oauth2.client.OAuth2AuthorizeRequest;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientManager;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestClientException;

import it.personalproject.ordini.domain.MagazzinoModel;
import it.personalproject.ordini.domain.ports.GiacenzeOutboundPort;

@Component
public class GiacenzeSyncOutboundAdapter implements GiacenzeOutboundPort {
	
    private static final Logger logger = LoggerFactory.getLogger(GiacenzeSyncOutboundAdapter.class);
	
	private final RestClient restClient;
	
	@Autowired
	public GiacenzeSyncOutboundAdapter(@Qualifier("gatewayRestClient") RestClient restClient) {
		this.restClient = restClient;
	}

	@Override
	public Collection<MagazzinoModel> getMagazziniConDisponibilitaProdotto(Integer idProdotto, Integer quantita) {
		
		try {
			
			return restClient.get()
					.uri(u -> u
							.path("/api/giacenze/disponibilita")
							.queryParam("idprodotto", idProdotto)
							.queryParam("quantita", quantita)
							.build())
					.retrieve()
					.onStatus(HttpStatusCode::isError, (req, res) -> {
                        throw new RestClientException("Errore chiamata giacenze service da ordini service: HTTP " + res.getStatusCode());
                    })
					.body(new ParameterizedTypeReference<Collection<MagazzinoModel>>() {});
		}
		
		catch(Exception e) {
            logger.error("ERRORE REST [ID-PRODOTTO: {}] [QUANTITA: {}]", idProdotto, quantita, e);
			throw e;
		}
		
	}

}
