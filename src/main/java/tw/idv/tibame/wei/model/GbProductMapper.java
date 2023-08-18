package tw.idv.tibame.wei.model;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.data.repository.query.Param;

@Mapper
public interface GbProductMapper {
    @Select("select p_name,b.gb_c_max,b.gb_s_price,p_type,count(*) as people"
    		+ " from gb_order a,gb b,product"
    		+ " where a.gb_order_id = b.gb_id"
    		+ " and b.gb_p_id=p_id"
    		+ " and p_m_id = #{p_m_id}"
    		+ " and b.gb_id = #{gb_p_id}"
    		+ " group by p_name,b.gb_c_max,b.gb_s_price,p_type")
    List<GbProductResult> selectProductInfo(@Param("p_m_id") Integer p_m_id, @Param("gb_p_id") Integer gb_p_id);
}
