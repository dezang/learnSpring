package dezang.inactive;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import dezang.user.dao.UserDao;

@Configuration
public class CountingDaoFactory {
	@Bean
	public UserDao userDao() {
		UserDao userDao = new UserDao();
		return userDao;
	}

	@Bean
	public ConnectionMaker connectionMaker() {
		return new CountingConnectionMaker(realConnectionMaker());
	}

	@Bean
	public ConnectionMaker realConnectionMaker() {
		return new DConnectionMaker();
	}
}
