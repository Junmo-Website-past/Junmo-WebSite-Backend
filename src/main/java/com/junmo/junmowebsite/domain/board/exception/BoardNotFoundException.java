package com.junmo.junmowebsite.domain.board.exception;

import com.junmo.junmowebsite.global.exception.ApplicationException;
import com.junmo.junmowebsite.global.exception.ErrorCode;

public class BoardNotFoundException extends ApplicationException {

    private static final ErrorCode errorcode = ErrorCode.BOARD_NOT_FOUND;

    public BoardNotFoundException() {
        super(errorcode);
    }

}
