import com.user.dao.UserDao;
import com.user.domain.QueryVo;
import com.user.domain.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.List;

public class mybatisTest {

    private InputStream inputStream;
    private  SqlSession sqlSession;
    private UserDao userDao;

    @Before
    public void inti() throws IOException {
        inputStream = Resources.getResourceAsStream("SqlMapConfig.xml");
        SqlSessionFactoryBuilder sqlSessionFactoryBuilder = new SqlSessionFactoryBuilder();
        SqlSessionFactory sqlSessionFactory = sqlSessionFactoryBuilder.build(inputStream);
        sqlSession = sqlSessionFactory.openSession();
        userDao = sqlSession.getMapper(UserDao.class);
    }
    @After
    public void distory() throws IOException {
        sqlSession.commit();
        sqlSession.close();
        inputStream.close();
    }

    @Test
    public void findAll() {
        List<User> userList=userDao.findAll();
        for(User user:userList){
            System.out.println(user);
        }
    }

    @Test
    public void saveUserTest() {
        User user=new User();
        user.setUsername("小九");
        user.setBirthday(new Date());
        user.setAddress("老街");
        user.setSex("女");
        userDao.saveUser(user);
    }

    @Test
    public void usercount() {
        int total=userDao.UserCount();
        System.out.println("一共有记录："+total);
    }

    @Test
    public void findByVo(){
        User user=new User();
        user.setUsername("%王%");
        QueryVo queryVo = new QueryVo();
        queryVo.setUser(user);
        userDao.findByVo(queryVo);
        List<User>  userList= userDao.findByVo(queryVo);
        for(User user2:userList){
            System.out.println(user2);
        }
    }
}
