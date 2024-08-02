package teamPlay.model.vo;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class BorderVO {
	
	private int bo_number;// int AI PK
	private String bo_title;// varchar(30) => 필수
	private int bo_counter;// int
	private String bo_detail;// varchar(255)
	private String bo_oldtitle;// varchar(30)

	
	// toString 만들어서 카테고리 쫙 나열 해 줄 수 있도록 바꾸어 주자
}
