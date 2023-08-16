package tw.idv.tibame.weimaster.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tw.idv.tibame.weimaster.model.WeiMasterDao;

@Service
public class WeiMasterService {

	@Autowired
	private WeiMasterDao dao;

//	public MasterService() {
//		dao = new MasterDao();
//	}
	

	public MasterVO updatePro(Integer m_id,String m_name
			,String m_gui,String m_bank_id,String m_address
			,String m_man_id,String m_man_name,String m_email,String m_phone){

		MasterVO masterVO = new MasterVO.Builder()
										.setM_id(m_id)
										.setM_name(m_name)
										.setM_gui(m_gui)
										.setM_bank_id(m_bank_id)
										.setM_address(m_address)
										.setM_man_id(m_man_id)
										.setM_man_name(m_man_name)
										.setM_email(m_email)
										.setM_phone(m_phone)
										.build();

		dao.update(masterVO);
		return masterVO;
	}

	public MasterVO getOnePro(Integer m_id) {
		return dao.findByPrimaryKey(m_id);
	}
}
