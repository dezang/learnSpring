package dezang.inactive;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;

import dezang.user.dao.UserDao;

@Configuration
public class DaoFactory {


	@Bean
	public UserDao userDao() {
		UserDao userDao = new UserDao();
		userDao.setDataSource(dataSource());
		return userDao;
	}

	@Bean
	public DataSource dataSource() {
		SimpleDriverDataSource dataSource = new SimpleDriverDataSource();

		dataSource.setDriverClass(com.mysql.jdbc.Driver.class);
		dataSource.setUrl("jdbc:mysql://190.190.70.113:3306/test");
		dataSource.setUsername("devtest");
		dataSource.setPassword("dezang0819");

		return dataSource;
	}
}
