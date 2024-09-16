package com.example.powerset.error_handling;

public class SetNotFoundException extends RuntimeException{
    public SetNotFoundException(Long id){
        super("SET ID " + id + "NOT FOUND" );
    }
    public SetNotFoundException(String type){
        super("SET WITH TYPE " + type + "NOT FOUND" );
    }
}
