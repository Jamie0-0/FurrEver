package tw.idv.tibame.user.dao;

import java.util.Date;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import tw.idv.tibame.user.vo.User;

@Repository
public interface UserDao extends CrudRepository<User, Integer> {

	User findByuEmail(String uEmail);

	@Query(value = "select uid from USER where u_email =:uEmail and u_pwd =:uPwd", nativeQuery = true)
	Integer findByuEmailAndUPwd(@Param("uEmail") String uEmail, @Param("uPwd") String uPwd);
	
	@Modifying
	@Query(value = "update USER set u_email =?1, u_name=?2, u_pwd=?3, u_phone=?4, u_birth=?5, u_about=?6, u_pic =?7 where uid =?8", nativeQuery = true)
	Integer updateByuid(@Param("uEmail") String uEmail, @Param("uName") String uName, @Param("uPwd") String uPwd, @Param("uPhone") String uPhone, @Param("uBirth") Date uBirth, @Param("uAbout") String uAbout, @Param("uPic") byte[] uPic, @Param("uid") Integer uid);

	User findPasswordByUid(Integer uid);

}
