package com.project.bloggingapis.exceptions;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResourceNotFoundException extends RuntimeException{
    String resourceName;
    String resourceField;
    int intFieldValue;
    String stringFieldValue;
    public ResourceNotFoundException(String resourceName,String resourceField,int fieldValue){
        super(resourceName + " not found with "+resourceField+fieldValue);
        this.resourceName=resourceName;
        this.resourceField=resourceField;
        this.intFieldValue=fieldValue;
    }
    public ResourceNotFoundException(String resourceName,String resourceField,String fieldValue){
        super(resourceName + " not found with "+resourceField+fieldValue);
        this.resourceName=resourceName;
        this.resourceField=resourceField;
        this.stringFieldValue=fieldValue;
    }
}
