package com.kspt.eos.logic;

import com.kspt.eos.entity.ExcursionObject;

public class LExcursionObject extends ExcursionObject {

    public void addToDescriprion(String text) {
        setDescription(getDescription() + text);
    }

}
