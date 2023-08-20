package com.example.powerset.error_handling;

import com.example.powerset.set.PSet;
import org.jetbrains.annotations.NotNull;

public class InvalidSetInputException extends RuntimeException{
    public InvalidSetInputException(@NotNull PSet set){
        super("Invalid input - no empty/null attributes accepted: \n" +
                "type: \t" + set.getType() + "\n" +
                "weight:\t" + set.getWeight() + "\n" +
                "reps: \t" + set.getReps() + "\n");
    }
}
