package org.dynamic.dao;

import org.apache.ibatis.annotations.Param;
import org.dynamic.pojo.Employee;

import java.util.List;

public interface EmployeeDynamicMapper {

    Employee getEmployeeById(String id);

    List<Employee> getEmpsByConditionIf(Employee employee);

    List<Employee> getEmpsByConditionIfTrim(Employee employee);

    int updateEmployee(Employee employee);

    int updateEmployeeTrim(Employee employee);

    List<Employee> getEmpsByConditionForeach(@Param("empId") List<Integer> empIds);


    int addBatchEmps(List<Employee> employees);

    Employee getEmployeeByLastNameBind(String lastName);

    List<Employee> getEmployeeByLastNameSql(String lastName);
}
