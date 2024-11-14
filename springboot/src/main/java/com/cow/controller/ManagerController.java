package com.cow.controller;

import com.cow.dao.ManagerMapper;
import com.cow.entity.Manager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component

public class ManagerController {
    @Autowired
    private ManagerMapper managerMapper;

    public Manager getManagerById(Integer id) {
        Manager manager = managerMapper.selectById(id);
        if (manager != null) {
            return manager;
        } else {
            System.out.println("manager 为空！");
        }
        return null;
    }
}
