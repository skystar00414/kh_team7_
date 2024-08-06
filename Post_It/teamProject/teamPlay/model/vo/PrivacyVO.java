package teamPlay.model.vo;

import java.io.Serializable;
import java.sql.Date;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PrivacyVO  implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private int pr_number; // AI PK 
	private String pr_id; //varchar(15) 필수
	private String pr_pw; //varchar(15) 필수
	private String pr_gender; //varchar(5) 필수
	private String pr_birth_day; //datetime 필수
	private String pr_phone;// varchar(20) 필수
	private String pr_email; //varchar(100) 필수
	private String pr_au_key; //varchar(5) 필수
	private int pr_failed;// int 
	private String pr_st_key; //varchar(10) 
	private String pr_re_key;// varchar(50) 
	private String pr_autologin; //tinyint 
	private Date pr_stop; //datetime
	
//	insert into privacy(pr_id, pr_pw, pr_gender, pr_birth_day, pr_phone, pr_email, pr_au_key)
//	VALUES ("cocopam123", "cocopam123", "남", "1999-01-18", "010-2332-4771","cocopam123@naver.com", "user");
	

	public PrivacyVO(String id, String pw, String gender, String birthday, String phone, String email,
			String authority) {
		
		this.pr_id = id;
		this.pr_pw = pw;
		this.pr_gender = gender;
		this.pr_birth_day = birthday;
		this.pr_phone = phone;
		this.pr_email = email;
		this.pr_au_key = authority;
		
	}
}
