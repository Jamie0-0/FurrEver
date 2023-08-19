package tw.idv.tibame.wei.model;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.data.repository.query.Param;

@Mapper
public interface GbOrderMapper {
	@Select("select c.uid,c.u_name,a.gb_p_num,gb_t*gb_p_num as gb_t,u_phone,u_address"
			+ " from gb_order a,gb b,product,user c" + " where a.gb_order_id = b.gb_id" + " and b.gb_p_id=p_id"
			+ " and p_m_id = #{p_m_id}" + " and b.gb_p_id = #{gb_p_id}" + " and a.uid = c.uid")
	List<GbOrderResult> selectGbMember(@Param("p_m_id") Integer p_m_id, @Param("gb_p_id") Integer gb_p_id);
}