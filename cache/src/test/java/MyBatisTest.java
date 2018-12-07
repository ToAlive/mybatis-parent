import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.cache.dao.EmployeeCacheMapper;
import org.cache.pojo.Employee;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;

public class MyBatisTest {
    /*这种写法获取的到sqlSession在配置二次缓存是不生效的*/
    public SqlSession getSqlSession() throws IOException {
        InputStream is = Resources.getResourceAsStream("mybatis-config.xml");
        SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(is);
        SqlSession sqlSession = sessionFactory.openSession();
        return sqlSession;
    }


    public SqlSessionFactory getSqlSessionFactory() throws IOException {
        InputStream is = Resources.getResourceAsStream("mybatis-config.xml");
        SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(is);
        return sessionFactory;
    }
    @Test
    /*一级缓存*/
    public void test1() throws IOException {
        SqlSession sqlSession = getSqlSession();
        EmployeeCacheMapper cacheMapper = sqlSession.getMapper(EmployeeCacheMapper.class);
        Employee emp = cacheMapper.getEmployeeById("2");
        System.out.println(emp);

    }

    @Test
    /*二级缓存*/
    public void test2() throws IOException {
        SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();
        SqlSession sqlSession = sqlSessionFactory.openSession();
        SqlSession sqlSession1 = sqlSessionFactory.openSession();
        EmployeeCacheMapper cacheMapper = sqlSession.getMapper(EmployeeCacheMapper.class);
        EmployeeCacheMapper cacheMapper1 = sqlSession1.getMapper(EmployeeCacheMapper.class);

        Employee employee = cacheMapper.getEmployeeById("2");
        System.out.println(employee);
        sqlSession.close();

        Employee employee1 = cacheMapper1.getEmployeeById("2");
        System.out.println(employee1);
        sqlSession1.close();
        System.out.println(employee == employee1);

    }

}
