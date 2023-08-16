package tw.idv.tibame.pMapping.model;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PMappingService {

	@Autowired
	private PMappingDAO_interface dao;

	public PMappingService() {
		dao = new PMappingDAO();
	}

	public PMappingVO addPro(Integer pm_id, String pm_name) {
		PMappingVO pMappingVO = new PMappingVO();
		pMappingVO.setPm_id(pm_id);
		pMappingVO.setPm_name(pm_name);
		dao.insert(pMappingVO);
		return pMappingVO;
	}

	public PMappingVO getOnePro(Integer pm_id) {
		return dao.findByPrimaryKey(pm_id);
	}

	public List<PMappingVO> getAll() {
		return dao.getAll();
	}
}
