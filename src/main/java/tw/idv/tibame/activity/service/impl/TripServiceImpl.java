package tw.idv.tibame.activity.service.impl;

import java.util.List;
import tw.idv.tibame.activity.dao.ParticipantDao;
import tw.idv.tibame.activity.dao.TripDao;
import tw.idv.tibame.activity.dao.impl.TripDaoImpl;
import tw.idv.tibame.activity.service.TripService;
import tw.idv.tibame.activity.vo.Trip;


public class TripServiceImpl implements TripService {

    private TripDao tripdao;
    private ParticipantDao participantdao;

//	public static void main(String[] args) {
//
//		List<Trip> trips =new TripServiceImpl().showActCity(1);
//
//		for(Trip trip: trips) {
//			System.out.println(trip);
//		}
//
//	}

    public TripServiceImpl() {
        tripdao = new TripDaoImpl();
    }

    //show所有活動
    @Override
    public List<Trip> showAllAct() {
        System.out.println("showAllAct"); //執行此方法()
        return tripdao.selectAll();
    }

    //show狗或貓的活動篩選器
    @Override
    public List<Trip> showByActType(Integer t_act_type) {
        System.out.println("showByActType");
        if (t_act_type == 1) {
            System.out.println("狗");
        } else if(t_act_type == 2) {
            System.out.println("貓");
        }
        return tripdao.selectByActType(t_act_type);
    }

    //show熱門活動篩選器
    @Override
    public List<Trip> showHotAct() {
        System.out.println("showHotAct"); //執行此方法()
        return tripdao.selectHotAct();

    }

    //show最新活動篩選器
    @Override
    public List<Trip> showNewAct() {
        System.out.println("showNewAct"); //執行此方法()
        return tripdao.selectNewAct();
    }

    //show活動地區篩選器(台北市或新北市)
    @Override
    public List<Trip> showActCity(Integer t_act_city) {
//		System.out.println("showActCity");
//		if (t_act_city == 1) {
//			System.out.println("台北市");
//		} else if (t_act_city == 2) {
//			System.out.println("新北市");
//		}
        return tripdao.selectActCity(t_act_city);
    }
    //舉辦活動
    @Override
    //活動圖片、名稱、描述、地點、時間、人數、預算判斷
    public Trip hostEvent(Trip trip) {
        if (trip.getT_act_pic_one() == null) {
            trip.setMessage("圖片1未上傳");
            trip.setSuccessful(false);
            return trip;
        }

        if (trip.getT_act_name() == null) {
            trip.setMessage("活動名稱未輸入");
            trip.setSuccessful(false);
            return trip;
        }

        if (trip.getT_act_desc() == null) {
            trip.setMessage("活動描述未輸入");
            trip.setSuccessful(false);
            return trip;
        }

        if (trip.getT_act_loc() == null) {
            trip.setMessage("活動地點未輸入");
            trip.setSuccessful(false);
            return trip;
        }

        if (trip.getT_act_time() == null) {
            trip.setMessage("活動時間未輸入");
            trip.setSuccessful(false);
            return trip;
        }

        if (trip.getT_act_ppl() == null) {
            trip.setMessage("人數未輸入");
            trip.setSuccessful(false);
            return trip;
        }

        if (trip.getT_act_bdg() == null) {
            trip.setMessage("活動預算未輸入");
            trip.setSuccessful(false);
            return trip;
        }
        tripdao.insertAct(trip);
        trip.setMessage("填寫成功，前往預覽活動");
        trip.setSuccessful(true);
        return trip;
    }

    //修改活動
    @Override
    public Trip updateAct(Trip trip) {
        // 檢查圖片上傳
        if (trip.getT_act_pic_one() == null || trip.getT_act_pic_one().length == 0) {
            trip.setMessage("請上傳活動圖片1");
            trip.setSuccessful(false);
            return trip;
        }
        if (trip.getT_act_pic_two() == null || trip.getT_act_pic_two().length == 0) {
            trip.setMessage("請上傳活動圖片2");
            trip.setSuccessful(false);
            return trip;
        }


        // 檢查活動名稱
        if (trip.getT_act_name() == null || trip.getT_act_name().isEmpty()) {
            trip.setMessage("請輸入活動名稱");
            trip.setSuccessful(false);
            return trip;
        }

        // 檢查活動描述是否空白、字元是否超過300字
        if (trip.getT_act_desc() == null || trip.getT_act_desc().isEmpty() || trip.getT_act_desc().length() > 300) {
            if (trip.getT_act_desc() == null || trip.getT_act_desc().isEmpty()) {
                trip.setMessage("請輸入活動描述");
            } else {
                trip.setMessage("活動描述不能超過300字");
            }
            trip.setSuccessful(false);
            return trip;
        }

        // 檢查活動地點
        if (trip.getT_act_loc() == null || trip.getT_act_loc().isEmpty()) {
            trip.setMessage("請輸入活動地點");
            trip.setSuccessful(false);
            return trip;
        }

        // 檢查活動時間
        if (trip.getT_act_time() == null || trip.getT_act_time().getTime() == 0) {
            trip.setMessage("請輸入活動時間");
            trip.setSuccessful(false);
            return trip;
        }


        // 檢查人數限制
        if (trip.getT_act_ppl() <= 0) {
            trip.setMessage("人數限制必須大於0");
            trip.setSuccessful(false);
            return trip;
        }

        // 檢查活動預算
        if (trip.getT_act_bdg() <= 0) {
            trip.setMessage("活動預算必須大於0");
            trip.setSuccessful(false);
            return trip;
        }

        trip.setSuccessful(true);
        return trip;
    }

    //show參加者數量
    @Override
    public Integer selectActJoin(Integer t_act_id, String uid_join) {
        System.out.println("selectActJoin"); //執行此方法()
        return participantdao.selectActJoin(t_act_id, uid_join);
    }

    //show活動圖片
    @Override
    public Trip showActPic(Integer t_act_id) {
        System.out.println("showActPic");
        return tripdao.selectActPic(t_act_id);
    }

    //查詢單筆活動
    @Override
    public Trip selectActId(Integer t_act_id) {
        System.out.println("selectActId");
        return tripdao.selectById(t_act_id);
    }



}

