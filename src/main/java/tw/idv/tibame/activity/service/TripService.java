package tw.idv.tibame.activity.service;

import java.util.List;

import tw.idv.tibame.activity.vo.Participant;
import tw.idv.tibame.activity.vo.Trip;

public interface TripService {

    //寫商業邏輯

    //show所有活動
    public List<Trip> showAllAct();

    //show狗或貓的活動篩選器
    public List<Trip> showByActType(Integer t_act_type);

    //show熱門活動篩選器
    public List<Trip> showHotAct();

    //show最新活動篩選器
    public List<Trip> showNewAct();

    //show活動地區篩選器(台北市或新北市)
    public List<Trip> showActCity(Integer t_act_city);

    //show活動圖片
    public Trip showActPic(Integer t_act_id);

    //舉辦活動
    public Trip hostEvent(Trip trip);

    //修改活動
    public Trip updateAct(Trip trip);

    //show參加者數量
    public Integer selectActJoin(Integer t_act_id, String uid_join);

    //查詢單筆活動
    public Trip selectActId(Integer t_act_id);





}
