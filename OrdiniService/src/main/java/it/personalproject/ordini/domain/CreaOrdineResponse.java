package it.personalproject.ordini.domain;

public class CreaOrdineResponse {
	
	private boolean ordineCreato;
	private OrdineModel ordine;
	
	public boolean isOrdineCreato() {
		return ordineCreato;
	}
	public void setOrdineCreato(boolean ordineCreato) {
		this.ordineCreato = ordineCreato;
	}
	public OrdineModel getOrdine() {
		return ordine;
	}
	public void setOrdine(OrdineModel ordine) {
		this.ordine = ordine;
	}

	
	

}
