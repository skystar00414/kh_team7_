package teamPlay.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import teamPlay.model.vo.BorderVO;
import teamPlay.model.vo.CategoryVO;
import teamPlay.model.vo.PostitVO;
import teamPlay.model.vo.PrivacyVO;

public interface PostitDAO {

	boolean insertNewBorder(@Param("bo_title")String newBorder);

	boolean deleteBorder(@Param("bo_title")String deleteBorder);

	BorderVO selectBorder(@Param("bo_title")String border);

	boolean updateBorder(@Param("bo_oldtitle")String oldTitle, @Param("bo_title")String title, @Param("bo_detail")String detail);

	List<BorderVO> selectBorderListOutput();

	List<CategoryVO> selectCategoryListOutput(@Param("name")String input);

	List<PostitVO> selectPostListOutput(@Param("category")String input);

	PostitVO selectPostOutput(@Param("po_number")int postN);

	CategoryVO setectCategory(@Param("ca_name")String input);

	void insetPostInput(@Param("po_bo_number")int border, @Param("po_ca_number")int category, @Param("po_pr_number")int id, @Param("po_title")String title, @Param("po_content")String content);

	PrivacyVO selectId(@Param("pr_id")String input);


}
