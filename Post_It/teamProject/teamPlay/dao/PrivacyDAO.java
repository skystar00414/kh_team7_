package teamPlay.dao;

import org.apache.ibatis.annotations.Param;

import teamPlay.model.vo.PrivacyVO;

public interface PrivacyDAO {


	boolean selectCheckPrivacyID(@Param("pr_id")String idCh);

	PrivacyVO selectLoginUser(@Param("pr_id")String idtmp, @Param("pr_pw")String pwtmp);

	boolean insertJoinUser(@Param("pr")PrivacyVO user);

	void updateFailedId(@Param("pr_id")String id);

	void updateSucceedId(@Param("pr_id")String pr_id);

}
