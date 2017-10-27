package com.lanou.tech.domain;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by jbtms940317 on 17/10/26.
 */
public class CourseType {
    private String courseTypeId;  //课程类别Id
    private Double courseCost;  //费用
    private Integer total;  //总学时
    private String courseName;  //课程类别名称
    private String remark;  //描述

    //课程类别和班级关系：一对多，一个课程类别可以有多个班级
    private Set<Classes> classesSet = new HashSet<Classes>();

    public String getCourseTypeId() {
        return courseTypeId;
    }

    public void setCourseTypeId(String courseTypeId) {
        this.courseTypeId = courseTypeId;
    }

    public Double getCourseCost() {
        return courseCost;
    }

    public void setCourseCost(Double courseCost) {
        this.courseCost = courseCost;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Set<Classes> getClassesSet() {
        return classesSet;
    }

    public void setClassesSet(Set<Classes> classesSet) {
        this.classesSet = classesSet;
    }
}
