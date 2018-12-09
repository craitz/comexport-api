package com.craitz.comexport.domains;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
public class JournalEntry {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonInclude(Include.NON_NULL)
	private Long id;

	@NotNull(message = "O parâmetro journalAccount é origatório")
	@JsonInclude(Include.NON_NULL)
	private Long journalAccount;

	@NotNull(message = "O parâmetro date é origatório")
	@JsonInclude(Include.NON_NULL)
	@JsonFormat(shape = JsonFormat.Shape.NUMBER, pattern = "yyyyMMdd", locale = "pt_BR")
	private Long date;

	@NotNull(message = "O parâmetro value é origatório")
	@JsonInclude(Include.NON_NULL)
	private Double value;
	
	public JournalEntry() {
		super();
	}
	
	public JournalEntry(Long id, Long journalAccount, Long date, Double value) {
		super();
		this.id = id;
		this.journalAccount = journalAccount;
		this.date = date;
		this.value = value;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (id ^ (id >>> 32));
		return result;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		JournalEntry other = (JournalEntry) obj;
		if (id != other.id)
			return false;
		return true;
	}

	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public Long getJournalAccount() {
		return journalAccount;
	}
	
	public void setJournalAccount(Long journalAccount) {
		this.journalAccount = journalAccount;
	}
	
	public Long getDate() {
		return date;
	}
	
	public void setDate(Long date) {
		this.date = date;
	}
		
	public Double getValue() {
		return value;
	}
	
	public void setValue(Double value) {
		this.value = value;
	}
}
