package tw.idv.tibame.payStatus.model;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PayStatusService {
	@Autowired
	private PayStatusDAO_interface dao;

	public List<PayStatusVO> getAll() {
		return dao.getAll();
	}
}
