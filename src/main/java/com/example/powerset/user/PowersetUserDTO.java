package com.example.powerset.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PowersetUserDTO {
    private String username;
    private String password;
    private String name;
}
