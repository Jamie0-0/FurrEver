package tw.idv.tibame.master.service.impl;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;

import javax.transaction.Transactional;
import javax.xml.bind.DatatypeConverter;

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
			String mPwd = "";
			try {
				MessageDigest md = MessageDigest.getInstance("SHA-256");
				md.update(master.getMPwd().getBytes(StandardCharsets.UTF_8));
				byte[] digest = md.digest();
				mPwd = DatatypeConverter.printHexBinary(digest).toUpperCase();
			} catch (Exception e) {
				e.printStackTrace();
			}
			master.setMPwd(mPwd);
			return masterDao.save(master);
		}
		return null;
	}

	@Override
	public Integer login(String mEmail, String mPwd) {
		try {
			MessageDigest md = MessageDigest.getInstance("SHA-256");
			md.update(mPwd.getBytes(StandardCharsets.UTF_8));
			byte[] digest = md.digest();
			mPwd = DatatypeConverter.printHexBinary(digest).toUpperCase();
		} catch (Exception e) {
			e.printStackTrace();
		}
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
