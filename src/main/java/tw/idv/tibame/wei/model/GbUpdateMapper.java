package tw.idv.tibame.wei.model;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface GbUpdateMapper {
	@Update("update gb_order a"
			+ " set gb_s = '0'"
			+ " where a.gb_order_id in "
			+ " ("
			+ " select b.gb_id"
			+ " from gb b,product"
			+ " where p_m_id = #{p_m_id}"
			+ " and b.gb_p_id=p_id "
			+ " )"
			+ " and a.gb_order_id = #{gb_p_id}"
			+ " and gb_s = '1'")
	void updateData(@Param("p_m_id") Integer p_m_id, @Param("gb_p_id") Integer gb_p_id);
}
