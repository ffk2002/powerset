
package com.example.powerset.error_handling;

import org.jetbrains.annotations.NotNull;

public class PowersetUserExistsException extends RuntimeException{
    public PowersetUserExistsException(@NotNull String username){
        super("User " + username + " already exists");
    }
}
