package com.jrdevel.aboutus.core.common.helper;

/**
 * @author Raphael Domingues
 *
 */
public enum MessageKeyEnum {
	
	GEN_ERROR ("gen.message.error"),
	
	AUDIT_OBJECT_USER("audit.object.user"),
	AUDIT_OBJECT_GROUP("audit.object.group"),
	AUDIT_OBJECT_PERSON("audit.object.person"),
	AUDIT_OBJECT_CHURCH("audit.object.church"),
	AUDIT_OBJECT_EVENT("audit.object.event"),
	AUDIT_OBJECT_FILE("audit.object.file"),
	AUDIT_OBJECT_FOLDER("audit.object.folder"),
	AUDIT_OBJECT_ARTICLE("audit.object.article"),
	AUDIT_OBJECT_CATEGORY("audit.object.category"),
	AUDIT_OBJECT_BANNER("audit.object.banner"),
	AUDIT_OBJECT_VIDEO("audit.object.video"),
	AUDIT_OBJECT_ALBUM("audit.object.album"),
	AUDIT_OBJECT_MUSIC("audit.object.music"),
	AUDIT_OBJECT_PLAYLIST("audit.object.playlist"),
	AUDIT_ACTION_ADD("audit.action.add"),
	AUDIT_ACTION_UPDATE("audit.action.update"),
	AUDIT_ACTION_DELETE("audit.action.delete"),
	
	DULICATED_EMAIL ("user.message.emailDuplicated"),
	AUTHENTICATION_FAILED ("authentication.message.error"),
	
	ARTICLE_INSERTED ("article.message.inserted"),
	ARTICLE_UPDATED ("article.message.updated"),
	ARTICLE_DELETED ("article.message.deleted"),
	
	CATEGORY_INSERTED ("category.message.inserted"),
	CATEGORY_UPDATED ("category.message.updated"),
	CATEGORY_DELETED ("category.message.deleted"),
	
	VIDEO_INSERTED ("video.message.inserted"),
	VIDEO_UPDATED ("video.message.updated"),
	VIDEO_DELETED ("video.message.deleted"),
	
	ALBUM_INSERTED ("album.message.inserted"),
	ALBUM_UPDATED ("album.message.updated"),
	ALBUM_DELETED ("album.message.deleted"),
	
	BANNER_INSERTED ("banner.message.inserted"),
	BANNER_UPDATED ("banner.message.updated"),
	BANNER_DELETED ("banner.message.deleted");
	
	private String key = null;
	
	private MessageKeyEnum(String key){
		this.key=key;
	}
	
	public String getKey(){
		return key;
	}

}
