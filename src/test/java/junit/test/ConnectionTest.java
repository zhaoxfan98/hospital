package junit.test;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidPooledConnection;

import cn.buu.edu.bean.Doctor;
import cn.buu.edu.bean.User;
import cn.buu.edu.service.DoctorService;
import cn.buu.edu.service.UserService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class ConnectionTest {
	@Autowired
	private DruidDataSource druidDataSource;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private DoctorService doctorService;
	
	@Test
	public void test01() throws SQLException{
		DruidPooledConnection connection = druidDataSource.getConnection();
		
		System.out.println(connection);
	}
	@Test
	public void test02(){
		List<User> userList = userService.getUserList();
		for (User user : userList) {
			System.out.println(user);
		}
	}
	
	@Test
	public void test03(){
		
		for (int i = 0; i<20;i++) {
			User user = new User();
			
			user.setName("test"+i);
			user.setEmail("test"+i+"@gmail.com");
			user.setPassword("123456");
			user.setStatus(1);
			user.setModifytime(new Date());
			user.setUsername("test"+i);
			
			
			userService.regist(user);
		}
	}
	
	@Test
	public void test04(){
		
		for (int i = 0; i<20;i++) {
			Doctor doctor = new Doctor();
			
			doctor.setName("test"+i);
			doctor.setEmail("test"+i+"@gmail.com");
			doctor.setAge(20+i);
			doctor.setBirthday(new Date(1997-i, i%12+1, i));
			doctor.setCardno("0000000000000000");
			doctor.setSex(i%2);
			doctor.setDepartment(i%6+1);
			doctor.setPhone("10086"+i+i);
			doctor.setRemark("testDoctor");
			
			doctorService.save(doctor);
		}
	}
}
