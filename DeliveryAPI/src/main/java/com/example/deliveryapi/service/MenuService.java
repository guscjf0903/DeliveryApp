package com.example.deliveryapi.service;

import com.example.core.dto.MenuDto;
import com.example.deliveryapi.entity.LoginData;
import com.example.deliveryapi.entity.MenuAddData;
import com.example.deliveryapi.entity.UserSignupData;
import com.example.deliveryapi.model.LoginRepository;
import com.example.deliveryapi.model.MenuRepository;

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
    private final LoginRepository loginRepository;
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private static final String INVALID_LOGIN_ID_MESSAGE = "Invalid login id";
    private static final String NO_PERMISSION_MESSAGE = "권한이 존재하지 않습니다.";


    @Transactional
    public ResponseEntity addMenu(MenuDto menuDto) {
        try {
            LoginData login = getLogin(menuDto.getLoginId());
            checkUserPermission(login);
            saveMenu(menuDto, login.getUser());

            return ResponseEntity.ok().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            logger.error("Failed to add menu", e);
            return ResponseEntity.badRequest().body("메뉴 추가에 실패했습니다.");
        }
    }

    private LoginData getLogin(int loginId) {
        return loginRepository.findById(loginId)
                .orElseThrow(() -> new IllegalArgumentException(INVALID_LOGIN_ID_MESSAGE));
    }

    private void checkUserPermission(LoginData login) {
        if (!login.getUser().isStore()) {
            throw new IllegalArgumentException(NO_PERMISSION_MESSAGE);
        }
    }

    private void saveMenu(MenuDto menuDto, UserSignupData user) {
        MenuAddData menu = MenuAddData.toEntity(menuDto);
        menu.setUser(user);
        user.getMenus().add(menu);
        menuRepository.save(menu);
    }

}
