package it.personalproject.giacenze.domain;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record TrasferimentoProdottoDTO(@NotNull @Positive Integer idProdotto, @NotNull @Positive Integer idMagazzinoPrecedente, @NotNull @Positive Integer idMagazzinoNuovo, @NotNull @Positive Integer quantitaTrasferita) {

}
