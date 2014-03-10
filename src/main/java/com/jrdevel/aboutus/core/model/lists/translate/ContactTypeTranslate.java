package com.jrdevel.aboutus.core.model.lists.translate;

// Generated 10/Mar/2014 22:16:04 by Hibernate Tools 3.4.0.CR1

import com.jrdevel.aboutus.core.model.lists.ContactType;
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
 * ContactTypeTranslate generated by hbm2java
 */
@Entity
@Table(name = "trans_contactType")
public class ContactTypeTranslate implements java.io.Serializable {

	private Integer id;
	private ContactType contactType;
	private String langId;
	private String text;

	public ContactTypeTranslate() {
	}

	public ContactTypeTranslate(ContactType contactType, String langId,
			String text) {
		this.contactType = contactType;
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
	public ContactType getContactType() {
		return this.contactType;
	}

	public void setContactType(ContactType contactType) {
		this.contactType = contactType;
	}

	@Column(name = "langId", nullable = false, length = 100)
	public String getLangId() {
		return this.langId;
	}

	public void setLangId(String langId) {
		this.langId = langId;
	}

	@Column(name = "text", nullable = false)
	public String getText() {
		return this.text;
	}

	public void setText(String text) {
		this.text = text;
	}

}
