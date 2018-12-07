package org.learn.mybatis.dao;

import org.learn.mybatis.pojo.Employee;

public interface EmployeeInterfaceMapper {

    Employee getEmployeeById(Integer id);

    void addEmployee(Employee employee);

    void updateEmployee(Employee employee);

    void delEmployee(Integer id);
}
