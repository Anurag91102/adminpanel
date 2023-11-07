package com.adminpanel.basic.payloads;

import java.util.List;

public class TypeData<T> 
{
    private String type;
    private List<T> result;

    public TypeData(String type, List<T> result) 
    {
        this.type = type;
        this.result = result;
    }

    public String getType() 
    {
        return type;
    }

    public List<T> getResult() 
    {
        return result;
    }
}
