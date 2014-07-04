package dezang.inactive;

import java.sql.SQLException;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

import dezang.user.dao.UserDao;
import dezang.user.domain.User;

public class UserDaoConnectionCountingTest {
	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		// AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(
		//		CountingDaoFactory.class);

		ApplicationContext context = new GenericXmlApplicationContext("applictionContext.xml");

		UserDao dao = context.getBean("userDao", UserDao.class);

		User user = new User();
		user.setId("dezang");
		user.setName("goodboy");
		user.setPassword("dezang0819");

		dao.add(user);

		System.out.println(user.getId() + "등록 성공");

		User user2 = dao.get(user.getId());
		user2 = dao.get(user.getId());
		user2 = dao.get(user.getId());
		user2 = dao.get(user.getId());

		System.out.println(user2.getName());
		System.out.println(user2.getPassword());
		System.out.println(user2.getId() + "조회 성공");


		CountingConnectionMaker ccm = context.getBean("connectionMaker",
				CountingConnectionMaker.class);
		System.out.println("counter : " + ccm.getCounter());
	}
}
