package it.personalproject.auth.entities;

import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "TIS_ROLE")
public class TisRole {
	
	@Id
	@Column(name = "Id", nullable = false, updatable = false)
	private Integer id;
	
	@Column(name = "Name", nullable = false, updatable = false)
	private String name;
	
	@Column(name = "Descr", updatable = false)
	private String descr;

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
		TisRole other = (TisRole) obj;
		return Objects.equals(id, other.id);
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescr() {
		return descr;
	}

	public void setDescr(String descr) {
		this.descr = descr;
	}

	@Override
	public String toString() {
		return "TisRole [descr=" + descr + "]";
	}
	
	
	

}
