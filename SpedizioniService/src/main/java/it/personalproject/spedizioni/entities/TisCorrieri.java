package it.personalproject.spedizioni.entities;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(
    name = "TIS_CORRIERI",
    uniqueConstraints = {
        @UniqueConstraint(name = "UQ_TIS_CORRIERI_Nome", columnNames = "Nome")
    }
)
public class TisCorrieri {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private Integer id;

    @Column(name = "Nome", nullable = false)
    private String nome;
    
    @Column(name = "Sito", nullable = false)
    private String sito;
    
    public TisCorrieri() {}
    

	public TisCorrieri(Integer id, String nome, String sito) {
		this.id = id;
		this.nome = nome;
		this.sito = sito;
	}
	
	
	

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TisCorrieri other = (TisCorrieri) obj;
		return Objects.equals(id, other.id);
	}


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getSito() {
		return sito;
	}

	public void setSito(String sito) {
		this.sito = sito;
	}


	@Override
	public String toString() {
		return "TisCorrieri [id=" + id + ", nome=" + nome + ", sito=" + sito + "]";
	}
    
    
    
    

    
}