package it.personalproject.giacenze.entities;

import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public class TisGiacenzePK {
	
	@Column(name = "IdMagazzino", nullable = false)
    private Integer idMagazzino;

    @Column(name = "IdProdotto", nullable = false)
    private Integer idProdotto;

    public TisGiacenzePK() {}

    public TisGiacenzePK(Integer idMagazzino, Integer idProdotto) {
        this.idMagazzino = idMagazzino;
        this.idProdotto = idProdotto;
    }

    public Integer getIdMagazzino() {
        return idMagazzino;
    }

    public void setIdMagazzino(Integer idMagazzino) {
        this.idMagazzino = idMagazzino;
    }

    public Integer getIdProdotto() {
        return idProdotto;
    }

    public void setIdProdotto(Integer idProdotto) {
        this.idProdotto = idProdotto;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TisGiacenzePK)) return false;
        TisGiacenzePK that = (TisGiacenzePK) o;
        return Objects.equals(idMagazzino, that.idMagazzino) &&
               Objects.equals(idProdotto, that.idProdotto);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idMagazzino, idProdotto);
    }

}
