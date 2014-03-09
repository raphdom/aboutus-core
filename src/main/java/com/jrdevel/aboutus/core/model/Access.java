package com.jrdevel.aboutus.core.model;

// Generated 9/Mar/2014 21:10:27 by Hibernate Tools 3.4.0.CR1

import com.jrdevel.aboutus.core.model.lists.Country;
import java.util.Date;

/**
 * Access generated by hbm2java
 */
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

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Country getCountry() {
		return this.country;
	}

	public void setCountry(Country country) {
		this.country = country;
	}

	public int getYearMonth() {
		return this.yearMonth;
	}

	public void setYearMonth(int yearMonth) {
		this.yearMonth = yearMonth;
	}

	public String getIp() {
		return this.ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getCity() {
		return this.city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public Date getDateAccess() {
		return this.dateAccess;
	}

	public void setDateAccess(Date dateAccess) {
		this.dateAccess = dateAccess;
	}

	public String getUrl() {
		return this.url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getUrlParams() {
		return this.urlParams;
	}

	public void setUrlParams(String urlParams) {
		this.urlParams = urlParams;
	}

	public Long getTimeTake() {
		return this.timeTake;
	}

	public void setTimeTake(Long timeTake) {
		this.timeTake = timeTake;
	}

	public String getBrowser() {
		return this.browser;
	}

	public void setBrowser(String browser) {
		this.browser = browser;
	}

	public String getBrowserVersion() {
		return this.browserVersion;
	}

	public void setBrowserVersion(String browserVersion) {
		this.browserVersion = browserVersion;
	}

	public String getOpSystem() {
		return this.opSystem;
	}

	public void setOpSystem(String opSystem) {
		this.opSystem = opSystem;
	}

	public Long getSizeBytes() {
		return this.sizeBytes;
	}

	public void setSizeBytes(Long sizeBytes) {
		this.sizeBytes = sizeBytes;
	}

	public String getReferer() {
		return this.referer;
	}

	public void setReferer(String referer) {
		this.referer = referer;
	}

}
