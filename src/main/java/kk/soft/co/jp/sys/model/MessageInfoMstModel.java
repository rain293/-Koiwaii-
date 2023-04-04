package kk.soft.co.jp.sys.model;

import java.io.Serializable;

import lombok.Data;

@Data
public class MessageInfoMstModel implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * id
	 */
	private int id;

	private String name;
	private String email;
	private String password;
	private String gender;
	private String marriage;
	private String privacy;

	public MessageInfoMstModel(String name, String email, String gender, String marriage,
			String privacy) {
		this.id = id;
		this.name = name;
		this.email = email;
		this.gender = gender;
		this.marriage = marriage;
		this.privacy = privacy;
	}
}
