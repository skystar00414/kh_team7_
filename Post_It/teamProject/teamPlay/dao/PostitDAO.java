package teamPlay.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import teamPlay.model.vo.BorderVO;

public interface PostitDAO {

	boolean insertNewBorder(@Param("bo_title")String newBorder);

	boolean deleteBorder(@Param("bo_title")String deleteBorder);

	BorderVO selectBorder(@Param("bo_title")String border);

	boolean updateBorder(@Param("bo_oldtitle")String oldTitle, @Param("bo_title")String title, @Param("bo_detail")String detail);

	List<BorderVO> selectBorderListOutput();


}
