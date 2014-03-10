package com.jrdevel.aboutus.core.model;

// Generated 10/Mar/2014 22:16:04 by Hibernate Tools 3.4.0.CR1

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * TblGroupTblPermission generated by hbm2java
 */
@Entity
@Table(name = "tbl_group_tbl_permission")
public class TblGroupTblPermission implements java.io.Serializable {

	private TblGroupTblPermissionId id;

	public TblGroupTblPermission() {
	}

	public TblGroupTblPermission(TblGroupTblPermissionId id) {
		this.id = id;
	}

	@EmbeddedId
	@AttributeOverrides({
			@AttributeOverride(name = "permissionsId", column = @Column(name = "permissions_id", nullable = false)),
			@AttributeOverride(name = "tblGroupId", column = @Column(name = "tbl_group_id", nullable = false)) })
	public TblGroupTblPermissionId getId() {
		return this.id;
	}

	public void setId(TblGroupTblPermissionId id) {
		this.id = id;
	}

}
