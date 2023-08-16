package tw.idv.tibame.pType.model;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
<<<<<<< HEAD

@Service
public class PTypeService {
=======

@Service
public class PTypeService {

>>>>>>> ec6b8275dcb92e957d2ce9fff8cfd21797afb32a
	@Autowired
	private PTypeDAO_interface dao;

	public PTypeService() {
		dao = new PTypeDAO();
	}

	public PTypeVO addPro(Integer pt_id, String pt_name) {
		PTypeVO pTypeVO = new PTypeVO();
		pTypeVO.setPt_id(pt_id);
		pTypeVO.setPt_name(pt_name);
		dao.insert(pTypeVO);
		return pTypeVO;
	}

	public PTypeVO getOnePro(Integer pt_id) {
		return dao.findByPrimaryKey(pt_id);
	}

	public List<PTypeVO> getAll() {
		return dao.getAll();
	}
}
