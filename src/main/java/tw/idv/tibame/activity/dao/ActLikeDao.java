package tw.idv.tibame.activity.dao;

import java.util.List;

import tw.idv.tibame.activity.vo.ActLike;

public interface ActLikeDao {

    //顯示所有收藏的活動
    List<ActLike> selectAll();
}