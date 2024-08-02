package teamPlay.model.vo;

import java.sql.Date;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PostitVO {//poster //다필수

	private int po_number; // int AI PK
	private int po_bo_number; // int
	private int po_ca_number; // int
	private int po_pr_number; // int
	private Date po_date; // date
	private String po_title; // varchar(50)
	private String po_content; // longtext
	private int po_views; // int
	private int po_reco; // int
	private int po_unreco; // int
		
}

