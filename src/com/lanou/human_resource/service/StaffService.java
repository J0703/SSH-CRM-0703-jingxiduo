package com.lanou.human_resource.service;

import com.lanou.human_resource.domain.Staff;

import java.util.List;

/**
 * Created by jbtms940317 on 17/10/25.
 */
public interface StaffService {
    List<Staff> findAll(String hql);
}
