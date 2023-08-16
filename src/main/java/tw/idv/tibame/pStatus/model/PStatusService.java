package tw.idv.tibame.pStatus.model;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
<<<<<<< HEAD

@Service
public class PStatusService {
=======

@Service
public class PStatusService {

>>>>>>> ec6b8275dcb92e957d2ce9fff8cfd21797afb32a
	@Autowired
	private PStatusDAO_interface dao;

	public PStatusService() {
		dao = new PStatusDAO();
	}

	public PStatusVO addPro(Integer ps_id, String ps_name) {
		PStatusVO pStatusVO = new PStatusVO();
		pStatusVO.setPs_id(ps_id);
		pStatusVO.setPs_name(ps_name);
		dao.insert(pStatusVO);
		return pStatusVO;
	}

	public PStatusVO getOnePro(Integer ps_id) {
		return dao.findByPrimaryKey(ps_id);
	}

	public List<PStatusVO> getAll() {
		return dao.getAll();
	}
}
