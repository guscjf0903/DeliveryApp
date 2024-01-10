package com.example.deliveryapi.service;

import static com.example.deliveryapi.exception.ErrorCode.MENU_ADD_FAILED;

import com.example.core.dto.MenuDto;
import com.example.deliveryapi.entity.LoginDataEntity;
import com.example.deliveryapi.entity.MenuAddDataEntity;
import com.example.deliveryapi.entity.UserSignupDataEntity;
import com.example.deliveryapi.exception.CustomException;
import com.example.deliveryapi.model.MenuRepository;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MenuService {
    private final MenuRepository menuRepository;
    private final UserLoginService userLoginService;
    private final UserSignupService userSignupService;
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Transactional
    public void addMenu(MenuDto menuDto) {
        try {
            LoginDataEntity login = userLoginService.validateLoginId(menuDto.getLoginId());
            userSignupService.checkStorePermission(login);
            saveMenu(menuDto, login.getUser());
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw new CustomException(MENU_ADD_FAILED);
        }
    }


    private void saveMenu(MenuDto menuDto, UserSignupDataEntity user) {
        MenuAddDataEntity menu = MenuAddDataEntity.of(menuDto);
        menu.setUser(user);
        user.getMenus().add(menu);
        menuRepository.save(menu);
    }

}
