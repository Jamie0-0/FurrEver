package tw.idv.tibame.friends.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import tw.idv.tibame.friends.vo.Friend;
import tw.idv.tibame.friends.vo.FriendId;

@Repository
public interface FriendsDao extends CrudRepository<Friend, FriendId> {

	// 讀取好友清單
	@Query("select f from Friend f where fUid = :fUid and fStatus = :fStatus")
	public List<Friend> getCurrentFriends(@Param("fUid") Integer fUid, @Param("fStatus") String fStatus);

	// 查詢是否為好友
	@Query("select f from Friend f where fUid = :fUid and fId = :fId and fStatus = '2'")
	public Friend isFriend(Integer fUid, Integer fId);

}
