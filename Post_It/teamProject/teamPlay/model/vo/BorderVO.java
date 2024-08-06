package teamPlay.model.vo;

import java.io.Serializable;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class BorderVO implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private int bo_number;// int AI PK
	private String bo_title;// varchar(30) => 필수
	private int bo_counter;// int
	private String bo_detail;// varchar(255)
	private String bo_oldtitle;// varchar(30)
	
	
	
//	@Override
//	public String toString() {
//		return bo_number+"@@"+bo_title;
//	}

	
	
}
