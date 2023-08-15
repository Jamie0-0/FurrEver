package tw.idv.tibame.master.service.impl;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import tw.idv.tibame.master.dao.MasterDao;
import tw.idv.tibame.master.service.MasterService;
import tw.idv.tibame.master.vo.Master;

@Component
public class MasterServiceImpl implements MasterService{
	
	@Autowired
	private MasterDao masterDao;
	
	@Transactional
	@Override
	public Master register(Master master) {
		Master result = masterDao.findBymEmail(master.getMEmail());
		if (result == null) {
			return masterDao.save(master);
		}
		return null;
	}

	@Override
	public Integer login(String mEmail, String mPwd) {
		Integer result = masterDao.findBymEmailAndmPwd(mEmail, mPwd);
		return result;
	}

	@Override
	public Master findMasterName(String mEmail) {
		Master result = masterDao.findBymEmail(mEmail);
//		System.out.println(result);
		if(result != null) {
			return result;
		}
		return null;
	}

}
