
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;
import org.learn.mybatis.dao.EmployeeInterfaceMapper;
import org.learn.mybatis.pojo.Employee;

import java.io.IOException;
import java.io.InputStream;

public class MyBatisTest {

    @Test
    public void test1() throws IOException {
        /*1.根据xml配置文件（全局的配置文件）创建一个SqlSessionFactory对象*/
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        /*2.通过SqlSessionFactory对象的openSession获取SqlSession对象*/
        SqlSession sqlSession = sqlSessionFactory.openSession();
        /*3.通过SqlSession对象执行sql*/
        /*第一个参数：建议使用namespace+id这样可以限定到哪个xml文件，如果只是用id的话，如果id重名了可能会出错*/
        Employee employee = sqlSession.selectOne("selectEmp","1");
        System.out.println(employee.toString());

        //4.关闭sqlSession
        sqlSession.close();
    }

    @Test
    public void test2() throws IOException {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        EmployeeInterfaceMapper mapper = sqlSession.getMapper(EmployeeInterfaceMapper.class);
        Employee employee = mapper.getEmployeeById(1);
        System.out.println(employee);

        sqlSession.close();
    }

    /*mybatis允许增删改查直接定义以下返回值：Integer，Long，Boolean*/
    @Test
    public void test3() throws IOException {
        String resource = "mybatis-config.xml";
        InputStream is = Resources.getResourceAsStream(resource);
        SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(is);
        /*openSession不传入参数的意思是不会自动提交，传入一个boolean为true的参数表示开启自动提交*/
        SqlSession openSession = sessionFactory.openSession();
        EmployeeInterfaceMapper interfaceMapper = openSession.getMapper(EmployeeInterfaceMapper.class);
        /*新增测试*/
        //Employee employee = new Employee(2,"AA","2","AA@.com");
        //interfaceMapper.addEmployee(employee);

        /*修改测试*/
        //Employee employee = new Employee(1,"CC","1","CC@.com");
        //interfaceMapper.updateEmployee(employee);

        /*删除测试*/
        //interfaceMapper.delEmployee(1);

        Employee employeeById = interfaceMapper.getEmployeeById(1);
        System.out.println(employeeById);

        //openSession.commit();
    }
}
