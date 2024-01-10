package com.example.deliveryapi.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCode {
    TOTAL_FETCH_FAILED(400,"매출 총합 데이터를 가져오는 중에 실패하였습니다."),
    UNAUTHORIZED_ACCESS(400,"접근 권한이 없습니다."),
    NOT_FOUND_USERORSTORE_TOKEN(401,"토큰이 존재하지 않습니다."),
    MENU_ADD_FAILED(400,"메뉴 추가에 실패하였습니다."),
    MENU_FOUND_FAILED(400,"메뉴 조회에 실패하였습니다."),
    ORDER_ADD_FAILED(400,"주문 추가에 실패하였습니다."),
    ORDERDETAIL_ADD_FAILED(400,"주문 추가에 실패하였습니다."),
    NOT_FOUND_LOGINID(400,"정상적인 로그인이 아닙니다."),

    NOT_FOUND_STORE(400,"가게를 찾을 수 없습니다."),
    MENU_TOTAL_FETCH_FAILED(400,"메뉴 총합 데이터를 가져오는 중에 실패하였습니다."),

    PASSWORD_DISMATCH(400,"비밀번호가 일치하지 않습니다."),

    USERSIGNUP_FAILED(400,"회원가입에 실패하였습니다.");



    private final int status;
    private final String message;
}
