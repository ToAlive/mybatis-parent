package org.cache.dao;

import org.cache.pojo.Employee;

public interface EmployeeCacheMapper {

    Employee getEmployeeById(String Id);
}
