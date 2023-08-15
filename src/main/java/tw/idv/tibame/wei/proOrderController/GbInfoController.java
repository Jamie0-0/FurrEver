package tw.idv.tibame.wei.proOrderController;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import tw.idv.tibame.wei.model.GbOrderMapper;
import tw.idv.tibame.wei.model.GbOrderResult;
import tw.idv.tibame.wei.model.GbProductMapper;
import tw.idv.tibame.wei.model.GbProductResult;
import tw.idv.tibame.wei.service.GbUpdateService;

@RestController
@RequestMapping("/backEnd")
public class GbInfoController {

    @Autowired
    private GbOrderMapper gbOrderMapper;
    
    @Autowired
    private GbProductMapper gbProductMapper;

    private GbUpdateService gbUpdateService;
    
    
    
    @PostMapping("/gb-memberList")
    public List<GbOrderResult> selectGbMember(Integer p_m_id, Integer gb_p_id) {
        return gbOrderMapper.selectGbMember(p_m_id, gb_p_id);
    }
    
    
    @PostMapping("/gb-productList")
    public List<GbProductResult> selectProductInfo(Integer p_m_id, Integer gb_p_id) {
        return gbProductMapper.selectProductInfo(p_m_id, gb_p_id);
    }
    
    
    @Autowired
    public GbInfoController(GbUpdateService gbUpdateService) {
        this.gbUpdateService = gbUpdateService;
    }
    @PostMapping("/gb-update")
    public ResponseEntity<String> updateData(@RequestParam Integer p_m_id,Integer gb_p_id) {
    	gbUpdateService.updateData(p_m_id, gb_p_id);
        return ResponseEntity.ok("Data updated successfully");
    }
}

