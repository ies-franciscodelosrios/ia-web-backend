package apirestful.iawebbackend.security;

import lombok.Data;

@Data
public class AuthCredentials {
    private String login;
    private String password;
}
