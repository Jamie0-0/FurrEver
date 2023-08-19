package tw.idv.tibame.activity.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import tw.idv.tibame.activity.dao.ParticipantDao;
import tw.idv.tibame.activity.vo.Participant;

@Repository
public class ParticipantDaoImpl implements ParticipantDao {
    @Autowired
    private DataSource ds;

    @Override
    public Integer selectActJoin(Integer t_act_id, String uid_join) {
        final String sql = "select count(*) from participant\n" + " where t_act_id = ? AND uid_join = ? \n" + " group by t_act_id ";
        try (Connection conn = ds.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, 5);
            pstmt.setString(2, "0");
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    Integer count = rs.getInt(1);
                    return count;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    //參加活動
    @Override
    public Integer imInAct(Participant participant) {
        final String sql = "INSERT INTO participant (t_act_id, uid, uid_join)\n" + "VALUES (?, ?, ?);";
        try (Connection conn = ds.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, participant.getT_act_id());
            pstmt.setInt(2, participant.getUid());
            pstmt.setString(3, participant.getUid_join());

            return pstmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1;
    }
}

