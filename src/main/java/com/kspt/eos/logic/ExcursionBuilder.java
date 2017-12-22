package com.kspt.eos.logic;

import com.kspt.eos.entity.Excursion;
import com.kspt.eos.entity.ExcursionObject;
import com.kspt.eos.entity.Organizator;

import java.util.ArrayList;

public class ExcursionBuilder {

    static public Excursion createExcursion(Organizator org, String name){
        if( org != null && org.getExcursion() != null
                && org.getExcursion().getDriver() != null){
            org.getExcursion().getDriver().setFree(true);
        }
        Excursion newExcursion = new Excursion();
        newExcursion.setOrganizator(org);
        newExcursion.setName(name);
        return newExcursion;
    }


    static public ExcursionObject createExcursionObject(int uid){
        return new ExcursionObject();
    }

    static public ExcursionObject createExcursionObject(ArrayList<String> descs, int uid){
        LExcursionObject excursionObject = (LExcursionObject) new ExcursionObject();
        for (String d : descs){
            excursionObject.addToDescriprion(d);
        }
        return excursionObject;
    }


    static public ExcursionObject createExcursionObject(){
        return new ExcursionObject();
    }

}