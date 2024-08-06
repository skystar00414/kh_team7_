package teamPlay.model.vo;

import java.io.Serializable;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CategoryVO implements Serializable{


	private static final long serialVersionUID = 1L;
	private int ca_number;// int AI PK 
	private int ca_bo_number;//  int 
	private String ca_name ;// varchar(10)s	
	
}
