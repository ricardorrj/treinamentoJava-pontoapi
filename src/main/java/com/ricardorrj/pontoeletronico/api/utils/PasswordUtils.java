package com.ricardorrj.pontoeletronico.api.utils;

import lombok.NoArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@NoArgsConstructor
public class PasswordUtils {

    //Metodo para gerar um Hash a partir da String informada no parametro 'senha'
    public static String crypt(String senha){
        if(senha == null){
            return senha;
        }

        BCryptPasswordEncoder bCryptEncoder = new BCryptPasswordEncoder();
        return bCryptEncoder.encode(senha);
    }

}
