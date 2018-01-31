package com.ajahsma.carservice.json;

import com.fasterxml.jackson.annotation.JsonInclude;

public class Data {

    private Object items;

    @JsonInclude(value=JsonInclude.Include.NON_NULL)
    private String type;
    
    public Object getItems (){
        return items;
    }

    public void setItems (Object items){
        this.items = items;
    }

    public String getType (){
        return type;
    }

    public void setType (String type){
        this.type = type;
    }

	@Override
    public String toString(){
        return "ClassPojo [items = "+items+", type = "+type+"]";
    }
}
