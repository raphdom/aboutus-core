package com.jrdevel.aboutus.core.common.model;

// Generated 2/jun/2014 21:02:16 by Hibernate Tools 3.4.0.CR1

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Batch generated by hbm2java
 */
@Entity
@Table(name = "tbl_batches")
public class Batch implements java.io.Serializable {

	private Integer id;
	private Date dateStart;
	private Date dateEnd;
	private int quantityProcessed;
	private String finalStatus;
	private String notes;

	public Batch() {
	}

	public Batch(Date dateStart, Date dateEnd, int quantityProcessed,
			String finalStatus) {
		this.dateStart = dateStart;
		this.dateEnd = dateEnd;
		this.quantityProcessed = quantityProcessed;
		this.finalStatus = finalStatus;
	}

	public Batch(Date dateStart, Date dateEnd, int quantityProcessed,
			String finalStatus, String notes) {
		this.dateStart = dateStart;
		this.dateEnd = dateEnd;
		this.quantityProcessed = quantityProcessed;
		this.finalStatus = finalStatus;
		this.notes = notes;
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

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "dateStart", nullable = false, length = 19)
	public Date getDateStart() {
		return this.dateStart;
	}

	public void setDateStart(Date dateStart) {
		this.dateStart = dateStart;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "dateEnd", nullable = false, length = 19)
	public Date getDateEnd() {
		return this.dateEnd;
	}

	public void setDateEnd(Date dateEnd) {
		this.dateEnd = dateEnd;
	}

	@Column(name = "quantityProcessed", nullable = false)
	public int getQuantityProcessed() {
		return this.quantityProcessed;
	}

	public void setQuantityProcessed(int quantityProcessed) {
		this.quantityProcessed = quantityProcessed;
	}

	@Column(name = "finalStatus", nullable = false, length = 45)
	public String getFinalStatus() {
		return this.finalStatus;
	}

	public void setFinalStatus(String finalStatus) {
		this.finalStatus = finalStatus;
	}

	@Column(name = "notes", length = 65535)
	public String getNotes() {
		return this.notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

}
