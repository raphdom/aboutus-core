package com.jrdevel.aboutus.core.common.model.lists;

import com.jrdevel.aboutus.core.common.model.lists.translate.FrequencyTranslate;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Frequency generated by hbm2java
 */
@Entity
@Table(name = "lst_frequency")
public class Frequency implements java.io.Serializable {

	private String id;
	private Set<FrequencyTranslate> frequencyTranslates = new HashSet<FrequencyTranslate>(
			0);

	public Frequency() {
	}

	public Frequency(String id) {
		this.id = id;
	}

	public Frequency(String id, Set<FrequencyTranslate> frequencyTranslates) {
		this.id = id;
		this.frequencyTranslates = frequencyTranslates;
	}

	@Id
	@Column(name = "id", unique = true, nullable = false, length = 100)
	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "frequency")
	public Set<FrequencyTranslate> getFrequencyTranslates() {
		return this.frequencyTranslates;
	}

	public void setFrequencyTranslates(
			Set<FrequencyTranslate> frequencyTranslates) {
		this.frequencyTranslates = frequencyTranslates;
	}

}
