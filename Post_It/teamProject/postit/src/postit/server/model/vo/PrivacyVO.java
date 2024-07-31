package postit.server.model.vo;

import java.sql.Date;
//sql Date 사용합니다

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PrivacyVO {
    private int pr_number; //AI, PK
    private String pr_id;// varchar(15) 
    private String pr_pw;// varchar(15) 
    private String pr_gender;// varchar(5) 
    private Date pr_birth_day; //DATETIME
    private String pr_phone;// varchar(20) 
    private String pr_email;// varchar(100) 
    private String pr_au_key;// varchar(5) 
    private int pr_failed; //로그인 실패횟수
    private String pr_st_key;// varchar(10) 
    private String pr_re_key; // varchar(50) 
    private int pr_autologinl;// tinyint 1이랑 0만 들어갈 수 있음
    private Date pr_stop;// datetime
}
