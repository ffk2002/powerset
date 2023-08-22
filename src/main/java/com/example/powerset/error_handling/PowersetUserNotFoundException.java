package com.example.powerset.error_handling;

import org.jetbrains.annotations.NotNull;

public class PowersetUserNotFoundException extends RuntimeException{
    public PowersetUserNotFoundException(@NotNull String username){
        super("User " + username + " not found");
    }

}
