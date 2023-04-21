package com.imusicstudio.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "slide")
public class Slide extends BaseEntity{

	@Column(name = "img")
	private String img;
	@Column(name = "content")
	private String content;
	@Column(name = "caption")
	private String caption;

	public Slide() {
		super();
	}

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getCaption() {
		return caption;
	}

	public void setCaption(String caption) {
		this.caption = caption;
	}

}
