package tw.idv.tibame.master.service;

import tw.idv.tibame.master.vo.Master;

public interface MasterService {

	Master register(Master master);

	Integer login(String mEmail, String mPwd);

	Master findMasterName(String mEmail);
}
