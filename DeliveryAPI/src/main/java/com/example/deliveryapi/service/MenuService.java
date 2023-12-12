package com.example.deliveryapi.service;

import com.example.core.dto.MenuDto;
import com.example.deliveryapi.entity.LoginData;
import com.example.deliveryapi.entity.MenuAddData;
import com.example.deliveryapi.entity.UserSignupData;
import com.example.deliveryapi.exception.InvalidTokenException;
import com.example.deliveryapi.exception.MenuAddFailedException;
import com.example.deliveryapi.model.LoginRepository;
import com.example.deliveryapi.model.MenuRepository;

import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MenuService {
    private final MenuRepository menuRepository;
    private final UserLoginService userLoginService;
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Transactional
    public void addMenu(MenuDto menuDto) {
        try {
            LoginData login = userLoginService.validateLoginId(menuDto.getLoginId());
            checkUserPermission(login);
            saveMenu(menuDto, login.getUser());
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw new MenuAddFailedException("menu add failed", e);
        }
    }

    private void checkUserPermission(LoginData login) {
        if (!login.getUser().isStore()) {
            throw new InvalidTokenException("no permission");
        }
    }

    private void saveMenu(MenuDto menuDto, UserSignupData user) {
        MenuAddData menu = MenuAddData.toEntity(menuDto);
        menu.setUser(user);
        user.getMenus().add(menu);
        menuRepository.save(menu);
    }

}
