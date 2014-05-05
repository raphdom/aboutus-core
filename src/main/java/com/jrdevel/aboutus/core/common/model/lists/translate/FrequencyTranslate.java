package com.jrdevel.aboutus.core.common.model.lists.translate;

// Generated 5/mai/2014 23:56:03 by Hibernate Tools 3.4.0.CR1

import com.jrdevel.aboutus.core.common.model.lists.Frequency;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * FrequencyTranslate generated by hbm2java
 */
@Entity
@Table(name = "trans_frequency")
public class FrequencyTranslate implements java.io.Serializable {

	private Integer id;
	private Frequency frequency;
	private String langId;
	private String text;

	public FrequencyTranslate() {
	}

	public FrequencyTranslate(Frequency frequency, String langId, String text) {
		this.frequency = frequency;
		this.langId = langId;
		this.text = text;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "lstId", nullable = false)
	public Frequency getFrequency() {
		return this.frequency;
	}

	public void setFrequency(Frequency frequency) {
		this.frequency = frequency;
	}

	@Column(name = "langId", nullable = false, length = 100)
	public String getLangId() {
		return this.langId;
	}

	public void setLangId(String langId) {
		this.langId = langId;
	}

	@Column(name = "text", nullable = false, length = 100)
	public String getText() {
		return this.text;
	}

	public void setText(String text) {
		this.text = text;
	}

}
