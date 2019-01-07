package com.ds.mall.common.exception;

import com.ds.mall.common.response.BaseResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;

/**
 * @author tb
 * @date 2019/1/7 14:11
 */
@ControllerAdvice("com.ds.mall.security")
@ResponseBody
@Slf4j
public class AuthExceptionHanlder {

    @ExceptionHandler(ClientInvalidException.class)
    public BaseResponse clientInvalidHandler(HttpServletResponse response, ClientInvalidException ex) {
        response.setStatus(403);
        log.error(ex.getMessage(),ex);
        return new BaseResponse(ex.getStatus(), ex.getMessage());
    }

    @ExceptionHandler(ClientForbiddenException.class)
    public BaseResponse clientForbiddenHandler(HttpServletResponse response, ClientForbiddenException ex) {
        response.setStatus(200);
        log.error(ex.getMessage(),ex);
        return new BaseResponse(ex.getStatus(), ex.getMessage());
    }

    @ExceptionHandler(UserInvalidException.class)
    public BaseResponse userInvalidExceptionHandler(HttpServletResponse response, UserInvalidException ex) {
        response.setStatus(200);
        log.error(ex.getMessage(),ex);
        return new BaseResponse(ex.getStatus(), ex.getMessage());
    }

    @ExceptionHandler(PasswordInvalidException.class)
    public BaseResponse passwordInvalidHandler(HttpServletResponse response, PasswordInvalidException ex) {
        response.setStatus(200);
        log.error(ex.getMessage(),ex);
        return new BaseResponse(ex.getStatus(), ex.getMessage());
    }

    @ExceptionHandler(MallBizException.class)
    public BaseResponse mallBizHandler(HttpServletResponse response, MallBizException ex) {
        log.error(ex.getMessage(),ex);
        response.setStatus(500);
        return new BaseResponse(ex.getStatus(), ex.getMessage());
    }

    @ExceptionHandler(MallSystemException.class)
    public BaseResponse mallSystemHandler(HttpServletResponse response, MallSystemException ex) {
        log.error(ex.getMessage(),ex);
        response.setStatus(500);
        return new BaseResponse(ex.getStatus(), ex.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public BaseResponse otherExceptionHandler(HttpServletResponse response, Exception ex) {
        response.setStatus(500);
        log.error(ex.getMessage(),ex);
        return new BaseResponse(ExceptionCode.OTHER_ERROR.getCode(), ex.getMessage());
    }
}
