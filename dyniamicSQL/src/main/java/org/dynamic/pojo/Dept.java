package org.dynamic.pojo;

public class Dept {
    private String id;
    private String deptName;

    public Dept() {
    }

    public Dept(String id) {
        this.id = id;
    }

    public Dept(String id, String deptName) {
        this.id = id;
        this.deptName = deptName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

}
