package com.jrdevel.aboutus.core.common.model;

// Generated 6/jun/2014 23:30:59 by Hibernate Tools 3.4.0.CR1

import com.jrdevel.aboutus.core.common.model.lists.Country;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Access generated by hbm2java
 */
@Entity
@Table(name = "tbl_access")
public class Access implements java.io.Serializable {

	private Integer id;
	private Country country;
	private int yearMonth;
	private String ip;
	private String city;
	private Date dateAccess;
	private String url;
	private String urlParams;
	private Long timeTake;
	private String browser;
	private String browserVersion;
	private String opSystem;
	private Long sizeBytes;
	private String referer;

	public Access() {
	}

	public Access(int yearMonth, String ip, Date dateAccess) {
		this.yearMonth = yearMonth;
		this.ip = ip;
		this.dateAccess = dateAccess;
	}

	public Access(Country country, int yearMonth, String ip, String city,
			Date dateAccess, String url, String urlParams, Long timeTake,
			String browser, String browserVersion, String opSystem,
			Long sizeBytes, String referer) {
		this.country = country;
		this.yearMonth = yearMonth;
		this.ip = ip;
		this.city = city;
		this.dateAccess = dateAccess;
		this.url = url;
		this.urlParams = urlParams;
		this.timeTake = timeTake;
		this.browser = browser;
		this.browserVersion = browserVersion;
		this.opSystem = opSystem;
		this.sizeBytes = sizeBytes;
		this.referer = referer;
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
	@JoinColumn(name = "country")
	public Country getCountry() {
		return this.country;
	}

	public void setCountry(Country country) {
		this.country = country;
	}

	@Column(name = "yearMonth", nullable = false)
	public int getYearMonth() {
		return this.yearMonth;
	}

	public void setYearMonth(int yearMonth) {
		this.yearMonth = yearMonth;
	}

	@Column(name = "ip", nullable = false, length = 100)
	public String getIp() {
		return this.ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	@Column(name = "city", length = 200)
	public String getCity() {
		return this.city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "dateAccess", nullable = false, length = 19)
	public Date getDateAccess() {
		return this.dateAccess;
	}

	public void setDateAccess(Date dateAccess) {
		this.dateAccess = dateAccess;
	}

	@Column(name = "url", length = 65535)
	public String getUrl() {
		return this.url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	@Column(name = "urlParams", length = 65535)
	public String getUrlParams() {
		return this.urlParams;
	}

	public void setUrlParams(String urlParams) {
		this.urlParams = urlParams;
	}

	@Column(name = "timeTake", precision = 10, scale = 0)
	public Long getTimeTake() {
		return this.timeTake;
	}

	public void setTimeTake(Long timeTake) {
		this.timeTake = timeTake;
	}

	@Column(name = "browser", length = 200)
	public String getBrowser() {
		return this.browser;
	}

	public void setBrowser(String browser) {
		this.browser = browser;
	}

	@Column(name = "browserVersion", length = 50)
	public String getBrowserVersion() {
		return this.browserVersion;
	}

	public void setBrowserVersion(String browserVersion) {
		this.browserVersion = browserVersion;
	}

	@Column(name = "opSystem", length = 200)
	public String getOpSystem() {
		return this.opSystem;
	}

	public void setOpSystem(String opSystem) {
		this.opSystem = opSystem;
	}

	@Column(name = "sizeBytes", precision = 10, scale = 0)
	public Long getSizeBytes() {
		return this.sizeBytes;
	}

	public void setSizeBytes(Long sizeBytes) {
		this.sizeBytes = sizeBytes;
	}

	@Column(name = "referer", length = 65535)
	public String getReferer() {
		return this.referer;
	}

	public void setReferer(String referer) {
		this.referer = referer;
	}

}
