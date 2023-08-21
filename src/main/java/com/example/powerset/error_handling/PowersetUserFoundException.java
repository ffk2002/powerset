
package com.example.powerset.error_handling;

import com.example.powerset.user.PowersetUser;
import org.jetbrains.annotations.NotNull;

public class PowersetUserFoundException extends RuntimeException{
    public PowersetUserFoundException(@NotNull String username){
        super("User " + username + " already exists");
    }
}
