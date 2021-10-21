package br.edu.ifsp.listatarefas.security.credentials;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserCredentials {
    private String username;
    private String password;
}
