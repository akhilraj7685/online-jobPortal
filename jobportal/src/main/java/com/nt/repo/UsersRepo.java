package com.nt.repo;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.nt.entity.Users;
import com.nt.entity.Users.UserType;

@Repository
public interface UsersRepo extends JpaRepository<Users, Long>{
	//get users by user name
		 public List<Users> findAllByName(String name);
		 
	//get all users by email
		 public List<Users> findAllByEmail(String email);
		 
	//get all users based on email and passwrd 	and usertype
		 @Query("SELECT u  FROM Users u WHERE u.email=:mail AND u.password=:pwd AND u.userType=:type")
		 public List<Users> findAllByEmailPaswdType(
				 @Param(value="mail")String email,@Param(value="pwd")String password,@Param(value="type")UserType userType);
	
		
		 
	//update password of user
	     @Modifying
		 @Query("UPDATE Users u set u.password=:pswd WHERE u.email=:mail")
		 public int updatePasswordByEmail(@Param(value="mail")String mail,@Param(value="pswd")String pswd);
	 
}
