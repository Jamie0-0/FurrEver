package tw.idv.tibame.wei.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tw.idv.tibame.wei.model.GbUpdateMapper;

@Service
public class GbUpdateService {
	private final GbUpdateMapper gbUpdateMapper;
	
    @Autowired
    public GbUpdateService(GbUpdateMapper gbUpdateMapper) {
        this.gbUpdateMapper = gbUpdateMapper;
    }

    public void updateData(Integer p_m_id,Integer gb_p_id) {
    	gbUpdateMapper.updateData(p_m_id, gb_p_id);
    }
}
