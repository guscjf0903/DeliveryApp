package com.example.deliveryapi.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor

public enum ErrorCode {
    TIME_FAILED(400,"잘못된 시간 데이터 입니다."),
    UNAUTHORIZED_ACCESS(401,"접근 권한이 없습니다."),
    NOT_FOUND_USERORSTORE_TOKEN(400,"토큰이 존재하지 않습니다."),
    MENU_FOUND_FAILED(404,"메뉴 조회에 실패하였습니다."),
    ORDER_ADD_FAILED(404,"주문 추가에 실패하였습니다."),
    ORDERDETAIL_ADD_FAILED(400,"주문 Detail 추가에 실패하였습니다."),
    NOT_FOUND_LOGINID(401,"정상적인 로그인이 아닙니다."),

    NOT_FOUND_STORE(400,"가게를 찾을 수 없습니다."),
    MENU_TOTAL_FETCH_FAILED(404,"메뉴 총합 데이터를 가져오는 중에 실패하였습니다."),

    PASSWORD_DISMATCH(400,"비밀번호가 일치하지 않습니다."),

    USERSIGNUP_FAILED(400,"회원가입에 실패하였습니다."),
    MENU_LIST_FETCH_FAILED(404,"메뉴 리스트 데이터를 가져오는 중에 실패하였습니다."),
    MENU_DELETE_FAILED(400,"메뉴 삭제에 실패하였습니다."),
    MENU_ALLLIST_FETCH_FAILED(404,"메뉴 전체 리스트 데이터를 가져오는 중에 실패하였습니다."),

    UNKNOWN_VALUE(404,"알 수 없는 값입니다.");




    private final int status;
    private final String detail;
}
