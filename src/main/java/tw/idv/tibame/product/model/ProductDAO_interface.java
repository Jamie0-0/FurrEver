package tw.idv.tibame.product.model;

import java.util.List;

import tw.idv.tibame.weimaster.model.MasterPicVO;
import tw.idv.tibame.weimaster.model.MasterPicVO2;

public interface ProductDAO_interface {
    public void insert(ProductVO productVO);
    public void update(ProductVO productVO);
    public void delete(Integer p_id,Integer mid);
    
    
    public ProductVO findByPrimaryKey(Integer p_id,Integer mid);
    public List<ProductVO> searchLatest();
    public List<ProductVO> findByPrimaryKey2(Integer p_id,Integer p_status,Integer p_class,Integer mid);
    public List<ProductVO> getAll();
    
    
    public ProductVO indexValue(Integer p_m_id);
    public List<MasterPicVO> indexNatrix1(Integer p_m_id);
    public List<MasterPicVO2> indexNatrix2(Integer mid);
}
