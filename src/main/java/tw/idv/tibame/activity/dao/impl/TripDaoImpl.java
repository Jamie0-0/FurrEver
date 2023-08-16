package tw.idv.tibame.activity.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import tw.idv.tibame.activity.dao.TripDao;
import tw.idv.tibame.activity.vo.Trip;

@Repository
public class TripDaoImpl implements TripDao {
    //連線持目前有問題(20行)
    @Autowired
    private DataSource ds;

    @Override
    public List<Trip> selectAll() {
        final String sql = "select * from FurrEver.trip";
        try (Connection conn = ds.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql); ResultSet rs = pstmt.executeQuery()) {
            List<Trip> list = new ArrayList<>();
            while (rs.next()) {
                Trip trip = new Trip();
                trip.setT_act_id(rs.getInt("t_act_id"));
                trip.setUid(rs.getInt("uid"));
                trip.setT_act_type(rs.getInt("t_act_type"));
                trip.setT_act_name(rs.getString("t_act_name"));
                trip.setT_act_desc(rs.getString("t_act_desc"));
                trip.setT_act_city(rs.getInt("t_act_city"));
                trip.setT_act_loc(rs.getString("t_act_loc"));
                trip.setT_act_time(rs.getTimestamp("t_act_time"));
//					trip.setT_act_time(rs.getString("t_act_time"));
                trip.setT_act_ppl(rs.getInt("t_act_ppl"));
                trip.setT_act_pic_one(rs.getBytes("t_act_pic_one"));
                trip.setT_act_pic_two(rs.getBytes("t_act_pic_two"));
                trip.setT_act_bdg(rs.getInt("t_act_bdg"));
                trip.setT_act_status(rs.getString("t_act_status"));

                list.add(trip);
            }
            System.out.println("selectAll");
            return list;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    //查詢單筆活動
    @Override
    public Trip selectById(Integer t_act_id) {
        final String sql = "select * from FurrEver.trip where t_act_id = ?";
        try (Connection conn = ds.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, t_act_id);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    Trip trip = new Trip();
                    trip.setT_act_id(rs.getInt("t_act_id"));
                    trip.setUid(rs.getInt("uid"));
                    trip.setT_act_type(rs.getInt("t_act_type"));
                    trip.setT_act_name(rs.getString("t_act_name"));
                    trip.setT_act_desc(rs.getString("t_act_desc"));
                    trip.setT_act_city(rs.getInt("t_act_city"));
                    trip.setT_act_loc(rs.getString("t_act_loc"));
                    trip.setT_act_time(rs.getTimestamp("t_act_time"));
//						trip.setT_act_time(rs.getString("t_act_time"));
                    trip.setT_act_ppl(rs.getInt("t_act_ppl"));
                    trip.setT_act_pic_one(rs.getBytes("t_act_pic_one"));
                    trip.setT_act_pic_two(rs.getBytes("t_act_pic_two"));
                    trip.setT_act_bdg(rs.getInt("t_act_bdg"));
                    trip.setT_act_status(rs.getString("t_act_status"));
                    return trip;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    //查詢最熱門活動
    @Override
    public List<Trip> selectHotAct() {
        final String sql = "select tr.*,count(*) from trip tr  \n" + "join participant pt on tr.t_act_id = pt.t_act_id\n" + "group by tr.t_act_id order by count(*) desc ";
        try (Connection conn = ds.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql); ResultSet rs = pstmt.executeQuery()) {
            List<Trip> list = new ArrayList<>();
            while (rs.next()) {
                Trip trip = new Trip();
                trip.setT_act_id(rs.getInt("t_act_id"));
                trip.setUid(rs.getInt("uid"));
                trip.setT_act_type(rs.getInt("t_act_type"));
                trip.setT_act_name(rs.getString("t_act_name"));
                trip.setT_act_desc(rs.getString("t_act_desc"));
                trip.setT_act_city(rs.getInt("t_act_city"));
                trip.setT_act_loc(rs.getString("t_act_loc"));
                trip.setT_act_time(rs.getTimestamp("t_act_time"));
//					trip.setT_act_time(rs.getString("t_act_time"));
                trip.setT_act_ppl(rs.getInt("t_act_ppl"));
                trip.setT_act_pic_one(rs.getBytes("t_act_pic_one"));
                trip.setT_act_pic_two(rs.getBytes("t_act_pic_two"));
                trip.setT_act_bdg(rs.getInt("t_act_bdg"));
                trip.setT_act_status(rs.getString("t_act_status"));

                list.add(trip);
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    //查詢最新活動
    @Override
    public List<Trip> selectNewAct() {
        final String sql = "select * from trip tr  \n" + "order by tr.t_act_time desc";
        try (Connection conn = ds.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql); ResultSet rs = pstmt.executeQuery()) {
            List<Trip> list = new ArrayList<>();
            while (rs.next()) {
                Trip trip = new Trip();
                trip.setT_act_id(rs.getInt("t_act_id"));
                trip.setUid(rs.getInt("uid"));
                trip.setT_act_type(rs.getInt("t_act_type"));
                trip.setT_act_name(rs.getString("t_act_name"));
                trip.setT_act_desc(rs.getString("t_act_desc"));
                trip.setT_act_city(rs.getInt("t_act_city"));
                trip.setT_act_loc(rs.getString("t_act_loc"));
                trip.setT_act_time(rs.getTimestamp("t_act_time"));
//					trip.setT_act_time(rs.getString("t_act_time"));
                trip.setT_act_ppl(rs.getInt("t_act_ppl"));
                trip.setT_act_pic_one(rs.getBytes("t_act_pic_one"));
                trip.setT_act_pic_two(rs.getBytes("t_act_pic_two"));
                trip.setT_act_bdg(rs.getInt("t_act_bdg"));
                trip.setT_act_status(rs.getString("t_act_status"));

                list.add(trip);
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    //新增活動內容
    @Override
    public Integer insertAct(Trip trip) {
        final String sql = "INSERT INTO trip (uid, t_act_type, t_act_name, t_act_desc, t_act_city, t_act_loc, t_act_time, t_act_ppl, t_act_pic_one, t_act_bdg, t_act_status)" + "VALUES ( ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = ds.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, 1);
            pstmt.setInt(2, trip.getT_act_type());
            pstmt.setString(3, trip.getT_act_name());
            pstmt.setString(4, trip.getT_act_desc());
            pstmt.setInt(5, trip.getT_act_city());
            pstmt.setString(6, trip.getT_act_loc());
            pstmt.setTimestamp(7, trip.getT_act_time());
//			pstmt.setString(7, trip.getT_act_time());
            pstmt.setInt(8, trip.getT_act_ppl());
            pstmt.setBytes(9, trip.getT_act_pic_one());
            //	pstmt.setBytes(10, trip.getT_act_pic_two());
            pstmt.setInt(10, trip.getT_act_bdg());
            pstmt.setString(11, "2");
            return pstmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1;
    }

    //修改活動內容
    @Override
    public Integer updateAct(Trip trip) {
        int rowCount = 0;
        String sql = "update trip " + "set t_act_type = ?, t_act_name = ?, t_act_desc = ?, t_act_city = ?, t_act_loc = ?, t_act_time = ?, t_act_ppl = ?, t_act_pic_one = ?, t_act_pic_two = ?, t_act_bdg = ?" + "where t_act_id = ?;";

        try (Connection conn = ds.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, trip.getT_act_type());
            pstmt.setString(2, trip.getT_act_name());
            pstmt.setString(3, trip.getT_act_desc());
            pstmt.setInt(4, trip.getT_act_city());
            pstmt.setString(5, trip.getT_act_loc());
            pstmt.setTimestamp(6, trip.getT_act_time());
//			pstmt.setString(6, trip.getT_act_time());
            pstmt.setInt(7, trip.getT_act_ppl());
            pstmt.setBytes(8, trip.getT_act_pic_one());
            pstmt.setBytes(9, trip.getT_act_pic_two());
            pstmt.setInt(10, trip.getT_act_bdg());
            pstmt.setInt(11, trip.getT_act_id());
            rowCount = pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rowCount = -1;
    }

    //活動到期下架
    @Override
    public Integer updateActDeadLine(Trip trip) {
        int rowCount = 0;
        String sql = "update trip " + "set t_act_status = ? " + "where t_act_id = ?;";
        try (Connection conn = ds.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, trip.getT_act_status());
            pstmt.setInt(2, trip.getT_act_id());
            rowCount = pstmt.executeUpdate();
            return rowCount;//記得要return rowCount，不然會直接到return到399行的return rowCount = -1;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rowCount = -1;
    }

    //查詢活動圖片
    @Override
    public Trip selectActPic(Integer t_act_id) {
        final String sql = "select * from FurrEver.trip where t_act_id = ?";
        try (Connection conn = ds.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, t_act_id);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    Trip trip = new Trip();
                    trip.setT_act_id(rs.getInt("t_act_id"));
                    trip.setT_act_pic_one(rs.getBytes("t_act_pic_one"));
                    return trip;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    //查詢活動種類(狗或貓)
    @Override
    public List<Trip> selectByActType(Integer t_act_type) {
        final String sql = "select * from FurrEver.trip where t_act_type = ?";
        try (Connection conn = ds.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql);) {

            pstmt.setInt(1, t_act_type);
            ResultSet rs = pstmt.executeQuery();
            List<Trip> list = new ArrayList<>();
            while (rs.next()) {
                Trip trip = new Trip();
                trip.setT_act_id(rs.getInt("t_act_id"));
                trip.setUid(rs.getInt("uid"));
                trip.setT_act_type(rs.getInt("t_act_type"));
                trip.setT_act_name(rs.getString("t_act_name"));
                trip.setT_act_desc(rs.getString("t_act_desc"));
                trip.setT_act_city(rs.getInt("t_act_city"));
                trip.setT_act_loc(rs.getString("t_act_loc"));
                trip.setT_act_time(rs.getTimestamp("t_act_time"));
//					trip.setT_act_time(rs.getString("t_act_time"));
                trip.setT_act_ppl(rs.getInt("t_act_ppl"));
                trip.setT_act_pic_one(rs.getBytes("t_act_pic_one"));
                trip.setT_act_pic_two(rs.getBytes("t_act_pic_two"));
                trip.setT_act_bdg(rs.getInt("t_act_bdg"));
                trip.setT_act_status(rs.getString("t_act_status"));

                list.add(trip);
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    //查詢活動地區下拉式選單(台北、新北市)
    @Override
    public List<Trip> selectActCity(Integer t_act_city) {
        final String sql = "select * from FurrEver.trip where t_act_city = ?";
        try (Connection conn = ds.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql);) {

            pstmt.setInt(1, t_act_city);
            ResultSet rs = pstmt.executeQuery();
            List<Trip> list = new ArrayList<>();
            while (rs.next()) {
                Trip trip = new Trip();
                trip.setT_act_id(rs.getInt("t_act_id"));
                trip.setUid(rs.getInt("uid"));
                trip.setT_act_type(rs.getInt("t_act_type"));
                trip.setT_act_name(rs.getString("t_act_name"));
                trip.setT_act_desc(rs.getString("t_act_desc"));
                trip.setT_act_city(rs.getInt("t_act_city"));
                trip.setT_act_loc(rs.getString("t_act_loc"));
                trip.setT_act_time(rs.getTimestamp("t_act_time"));
//					trip.setT_act_time(rs.getString("t_act_time"));
                trip.setT_act_ppl(rs.getInt("t_act_ppl"));
                trip.setT_act_pic_one(rs.getBytes("t_act_pic_one"));
                trip.setT_act_pic_two(rs.getBytes("t_act_pic_two"));
                trip.setT_act_bdg(rs.getInt("t_act_bdg"));
                trip.setT_act_status(rs.getString("t_act_status"));

                list.add(trip);
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}

