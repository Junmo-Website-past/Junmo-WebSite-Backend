package com.junmo.junmowebsite.global.exception;


public class ApplicationException extends RuntimeException{

    private final ErrorCode errorCode;

    // ErrorCode에 등록한 message 내용이 콘솔에도 출력되도록 RuntimeException 생성자에 전달
    protected ApplicationException(ErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }

    // 직접 콘솔 출력 에러메시지 설정하고 싶을때는 해당 생성자 사용
    public ApplicationException(ErrorCode errorCode, String message) {
        super(message);
        this.errorCode = errorCode;
    }

}
