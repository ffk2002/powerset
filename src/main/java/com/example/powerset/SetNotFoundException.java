package com.example.powerset;

public class SetNotFoundException extends RuntimeException{
    SetNotFoundException(Long id){
        super("SET ID " + id + "NOT FOUND" );
    }
    SetNotFoundException(String type){
        super("SET WITH TYPE " + type + "NOT FOUND" );
    }
}
