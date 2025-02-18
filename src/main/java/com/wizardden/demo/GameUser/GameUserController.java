package com.wizardden.demo.GameUser;

import com.wizardden.demo.Util.UserPasswordEncoder;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GameUserController {
    private final GameUserRepository repository;

    @Autowired
    GameUserController(GameUserRepository repository) {
        this.repository = repository;
    }

    @PostMapping("/GameUser")
    String newGameUser(@RequestBody GameUser gameUser, HttpServletResponse httpServletResponse) {
        GameUser existingUser = repository.findByEmail(gameUser.getEmail());
        if (existingUser != null) {
            if (UserPasswordEncoder.matches(gameUser.getPassword(), existingUser.getPassword())) {
                Cookie cookie = new Cookie("gameUserAccessToken", "authenticated");
                httpServletResponse.addCookie(cookie);
                return gameUser.getUsername();
            } else {
                throw new GameUserWrongPasswordException("Wrong password");
            }
        }

        Cookie cookie = new Cookie("gameUserAccessToken", "authenticated");
        httpServletResponse.addCookie(cookie);
        repository.save(gameUser);
        return gameUser.getUsername();
    }
}
