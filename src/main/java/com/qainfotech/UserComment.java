package com.qainfotech;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "addcomment")
public class UserComment {
	
	public UserComment () {}
	public UserComment(String comment) {
		this.comments.add(comment);
	}

	private List<String> comments = new ArrayList<String>();

	public List<String> getComment() {
		return comments;
	}

	public void setComment(String comment) {
		this.comments.add(comment);
	}

}
