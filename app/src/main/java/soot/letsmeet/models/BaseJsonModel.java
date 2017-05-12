package soot.letsmeet.models;

import com.google.gson.Gson;

/**
 * Created by Soot on 03/05/2017.
 */

public abstract class BaseJsonModel {

    public String toJson(){
        return new Gson().toJson(this);
    }
}