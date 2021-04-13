package org.example.app.services;

import org.apache.log4j.Logger;
import org.example.web.dto.LoginForm;
import org.example.web.dto.RegisterForm;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class LoginService {
    //FIXME final
    private Logger logger = Logger.getLogger(LoginService.class);
    private ArrayList<RegisterForm> users = new ArrayList<RegisterForm>();

    public void addUser(RegisterForm regForm) {
        users.add(regForm);
        logger.info("users: " + users.size());
    }

    public boolean authenticate(final LoginForm loginForm){
        logger.info("try auth with user-form: " + loginForm);
        return users.stream().anyMatch(r->r.getUsername().equals(loginForm.getUsername()) &&
                r.getPassword().equals(loginForm.getPassword()));
    }
}
