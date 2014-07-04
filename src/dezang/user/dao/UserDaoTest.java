package dezang.user.dao;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.sql.SQLException;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import dezang.user.domain.User;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "/applicationContext.xml")
//
public class UserDaoTest {
	@Autowired
	private ApplicationContext context;
	@Autowired
	private UserDao dao;
	private User user1;
	private User user2;
	private User user3;

	@Before
	public void setUp() throws SQLException {
		// context = new GenericXmlApplicationContext("applicationContext.xml");
		// this.dao = context.getBean("userDao", UserDao.class);

		/*
		 * 빈의 의존관계를 코드에서 강제로 DI 하는 코드 / 위험하다.
		 * @DirtiesContext 어노테이션으로 application context를 공유하지 않게 한다.
		 *******************************************************
		DataSource dataSource = new SingleConnectionDataSource(
				"jdbc:mysql://190.190.70.113:3306/test", "devtest", "dezang0819", true);
		dao.setDataSource(dataSource);
		 */

		this.user1 = new User("dezang1", "goodboy1", "dezang1");
		this.user2 = new User("dezang2", "goodboy2", "dezang2");
		this.user3 = new User("dezang3", "goodboy3", "dezang3");

	}

	@Test
	public void addAndGet() throws SQLException {
		dao.deleteAll();
		assertThat(dao.getCount(), is(0));

		dao.add(user1);
		dao.add(user2);
		assertThat(dao.getCount(), is(2));

		User userget1 = dao.get(user1.getId());
		assertThat(userget1.getName(), is(user1.getName()));
		assertThat(userget1.getPassword(), is(user1.getPassword()));

		User userget2 = dao.get(user2.getId());
		assertThat(userget2.getName(), is(user2.getName()));
		assertThat(userget2.getPassword(), is(user2.getPassword()));
	}

	@Test
	public void count() throws SQLException {
		dao.deleteAll();
		assertThat(dao.getCount(), is(0));

		dao.add(user1);
		assertThat(dao.getCount(), is(1));

		dao.add(user2);
		assertThat(dao.getCount(), is(2));

		dao.add(user3);
		assertThat(dao.getCount(), is(3));
	}

	@Test(expected = EmptyResultDataAccessException.class)
	public void getUserFailure() throws SQLException {
		dao.deleteAll();
		assertThat(dao.getCount(), is(0));

		dao.get("unknown_id");
	}
}
