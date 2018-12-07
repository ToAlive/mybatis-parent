import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.dynamic.dao.EmployeeDynamicMapper;
import org.dynamic.pojo.Dept;
import org.dynamic.pojo.Employee;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TestMyBatis {
    public SqlSession getSqlSession() throws IOException {
        String resource = "mybatis-config.xml";
        InputStream is = Resources.getResourceAsStream(resource);
        SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(is);
        SqlSession openSession = sessionFactory.openSession();
        return openSession;
    }

    @Test
    public void test() throws IOException {
        SqlSession openSession = getSqlSession();
        EmployeeDynamicMapper interfaceMapper = openSession.getMapper(EmployeeDynamicMapper.class);
        Employee employeeById = interfaceMapper.getEmployeeById("1");
        System.out.println(employeeById);
    }

    @Test
    public void test2() throws IOException {
        SqlSession sqlSession = getSqlSession();
        EmployeeDynamicMapper mapper = sqlSession.getMapper(EmployeeDynamicMapper.class);
        Employee employee = new Employee(1,"%A","2","");
        List<Employee> empsByConditionIf = mapper.getEmpsByConditionIf(employee);
        for (Employee emp : empsByConditionIf) {
            System.out.println(emp);
        }
    }

    @Test
    public void test3() throws IOException {
        SqlSession sqlSession = getSqlSession();
        EmployeeDynamicMapper mapper = sqlSession.getMapper(EmployeeDynamicMapper.class);
        Employee emp = new Employee(1,"%A","","");
        List<Employee> empsByConditionIfTrim = mapper.getEmpsByConditionIfTrim(emp);
        for (Employee employee : empsByConditionIfTrim) {
            System.out.println(employee);
        }
    }

    @Test
    public void test4() throws IOException {
        SqlSession sqlSession = getSqlSession();
        EmployeeDynamicMapper mapper = sqlSession.getMapper(EmployeeDynamicMapper.class);
        Employee emp = new Employee(2,"FD","4",null);
        //int num = mapper.updateEmployee(emp);
        int num = mapper.updateEmployeeTrim(emp);
        sqlSession.commit();
        System.out.println(num);
    }

    @Test
    public void test5() throws IOException {
        SqlSession sqlSession = getSqlSession();
        EmployeeDynamicMapper mapper = sqlSession.getMapper(EmployeeDynamicMapper.class);
        List<Employee> empList = mapper.getEmpsByConditionForeach(Arrays.asList(1, 2, 3, 4));
        for (Employee emp : empList) {
            System.out.println(emp);
        }
    }

    @Test
    public void test6() throws IOException {
        SqlSession sqlSession = getSqlSession();
        EmployeeDynamicMapper mapper = sqlSession.getMapper(EmployeeDynamicMapper.class);
        List<Employee> employeeList = new ArrayList<Employee>();
        employeeList.add(new Employee(null,"QQ-OO","2","QQ@.com",new Dept("1")));
        employeeList.add(new Employee(null,"VV-OO","2","VV@.com",new Dept("2")));
        mapper.addBatchEmps(employeeList);
        sqlSession.commit();
    }


    @Test
    public void test7() throws IOException {
        SqlSession sqlSession = getSqlSession();
        EmployeeDynamicMapper mapper = sqlSession.getMapper(EmployeeDynamicMapper.class);
        Employee emp = mapper.getEmployeeByLastNameBind("A");
        System.out.println(emp);
    }

    @Test
    public void test8() throws IOException {
        SqlSession sqlSession = getSqlSession();
        EmployeeDynamicMapper mapper = sqlSession.getMapper(EmployeeDynamicMapper.class);
        List<Employee> employeeList = mapper.getEmployeeByLastNameSql("Q");
        for (Employee emp : employeeList) {
            System.out.println(emp);
        }
    }
}
