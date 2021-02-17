package cn.testfan.myspringboot.web.advice;

import cn.testfan.myspringboot.Enum.ResponseEnum;
import cn.testfan.myspringboot.exception.DemoException;
import cn.testfan.myspringboot.model.DemoResponse;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolationException;

@ControllerAdvice
public class ExceptionAdivce {

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public DemoResponse defaultException(HttpServletRequest request, Exception e) {
        e.printStackTrace();
        return DemoResponse.builder()
                .code(ResponseEnum.EXCEPTION.getCode())
                .msg(ResponseEnum.EXCEPTION.getMsg())
                .build();
    }

    @ExceptionHandler(value = DemoException.class)
    @ResponseBody
    public DemoResponse demoException(HttpServletRequest request, DemoException e) {
        e.printStackTrace();
        Integer code = e.getCode();
        String message = e.getMessage();

        if (code == null) {
            code = ResponseEnum.EXCEPTION.getCode();
        }
        if (message == null) {
            message = ResponseEnum.EXCEPTION.getMsg();
        }
        return DemoResponse.builder()
                .code(code)
                .msg(message)
                .build();
    }

    @ExceptionHandler(value = ConstraintViolationException.class)
    @ResponseBody
    public DemoResponse constraintViolationException(HttpServletRequest request, ConstraintViolationException e) {
        e.printStackTrace();
        return DemoResponse.builder()
                .code(ResponseEnum.FAIL.getCode())
                .msg(e.getMessage())
                .build();
    }

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    @ResponseBody
    public DemoResponse methodArgumentNotValidException(HttpServletRequest request, MethodArgumentNotValidException e) {
        e.printStackTrace();
        return DemoResponse.builder()
                .code(ResponseEnum.FAIL.getCode())
                .msg(getError(e.getBindingResult()))
                .build();
    }

    @ExceptionHandler(value = BindException.class)
    @ResponseBody
    public DemoResponse bindException(HttpServletRequest request, BindException e) {
        e.printStackTrace();
        return DemoResponse.builder()
                .code(ResponseEnum.FAIL.getCode())
                .msg(getError(e.getBindingResult()))
                .build();
    }

    private String getError(BindingResult bindingResult) {
        StringBuffer sb = new StringBuffer();
        if (bindingResult.hasErrors()) {
            for (ObjectError error : bindingResult.getAllErrors()) {
                sb.append(error.getDefaultMessage()).append(",");
            }
        }
        String errMsg = sb.toString();
        errMsg = errMsg.substring(0, errMsg.lastIndexOf(","));
        return errMsg;
    }
}
