package com.db.gestionale.mdm.be.dto;

import lombok.Data;

@Data
public class LoginRequest {
    private String username;
    private String password;
}
