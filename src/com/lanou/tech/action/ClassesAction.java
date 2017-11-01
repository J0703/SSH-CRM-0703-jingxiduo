package com.lanou.tech.action;

import com.lanou.tech.domain.Classes;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

/**
 * Created by jbtms940317 on 17/10/27.
 */
public class ClassesAction extends ActionSupport implements ModelDriven<Classes>{

    private Classes classDriven;
    @Override
    public Classes getModel() {
        classDriven = new Classes();
        return classDriven;
    }
}
