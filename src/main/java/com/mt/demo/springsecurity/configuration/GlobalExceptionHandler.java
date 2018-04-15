package com.mt.demo.springsecurity.configuration;

import com.mt.demo.springsecurity.utils.LogController;
import com.mt.demo.springsecurity.utils.ResultCode;
import com.mt.demo.springsecurity.utils.ResultModel;
import org.hibernate.service.spi.ServiceException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.validation.ConstraintViolationException;
import javax.validation.ValidationException;

/**
 * GlobalExceptionHandler
 *
 * @author MT.LUO
 * 2018/4/11 11:45
 * @Description:
 */
@ControllerAdvice
@Component
public class GlobalExceptionHandler {

    /* 参数缺失 */
    @ExceptionHandler(MissingServletRequestParameterException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResultModel handleMissingServletRequestParameterException(MissingServletRequestParameterException
                                                                                 exception) {

        return ResultModel.error(ResultCode.RESULT_CODE_PARAM_ERROR, exception.getMessage());
    }

    /* 参数解析失败 */
    @ExceptionHandler(HttpMessageNotReadableException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResultModel handleHttpMessageNotReadableException(HttpMessageNotReadableException exception) {

        return ResultModel.error(ResultCode.RESULT_CODE_PARAM_ERROR, exception.getMessage());
    }


    /**
     * 400 - Bad Request
     */
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResultModel handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        LogController.warn("GlobalExceptionHandler", e.getMessage());
        BindingResult result = e.getBindingResult();
        FieldError error = result.getFieldError();
        String field = error.getField();
        String code = error.getDefaultMessage();
        String message = String.format("%s:%s", field, code);
        return ResultModel.error(ResultCode.RESULT_CODE_PARAM_ERROR, message);
    }

    /**
     * 400 - Bad Request
     */
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(BindException.class)
    public ResultModel handleBindException(BindException e) {
        LogController.warn("GlobalExceptionHandler", e.getMessage());
        BindingResult result = e.getBindingResult();
        FieldError error = result.getFieldError();
        String field = error.getField();
        String code = error.getDefaultMessage();
        String message = String.format("%s:%s", field, code);
        return ResultModel.error(ResultCode.RESULT_CODE_PARAM_ERROR, message);
    }

    /**
     * 400 - Bad Request
     */
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ConstraintViolationException.class)
    public ResultModel handleServiceException(ConstraintViolationException e) {
        LogController.warn("GlobalExceptionHandler", e.getMessage());
        return ResultModel.error(ResultCode.RESULT_CODE_PARAM_ERROR, e.getConstraintViolations().iterator().next().getMessage());
    }

    /**
     * 400 - Bad Request
     */
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ValidationException.class)
    public ResultModel handleValidationException(ValidationException e) {
        LogController.warn("GlobalExceptionHandler", e.getMessage());
        return ResultModel.error(ResultCode.RESULT_CODE_PARAM_ERROR, e.getMessage());
    }

    /**
     * 405 - Method Not Allowed
     */
    @ResponseBody
    @ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResultModel handleHttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException e) {
        LogController.warn("GlobalExceptionHandler", e.getMessage());
        return ResultModel.error(ResultCode.RESULT_CODE_METHOD_NOT_SUPPORT, e.getMessage());
    }

    /**
     * 415 - Unsupported Media Type
     */
    @ResponseBody
    @ResponseStatus(HttpStatus.UNSUPPORTED_MEDIA_TYPE)
    @ExceptionHandler(HttpMediaTypeNotSupportedException.class)
    public ResultModel handleHttpMediaTypeNotSupportedException(Exception e) {
        LogController.warn("GlobalExceptionHandler", e.getMessage());
        return ResultModel.error(ResultCode.RESULT_CODE_MEDIA_NOT_SUPPORT, e.getMessage());
    }

    /**
     * 500 - Internal Server Error
     */
    @ResponseBody
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(ServiceException.class)
    public ResultModel handleServiceException(ServiceException e) {
        LogController.warn("GlobalExceptionHandler", e.getMessage());
        return ResultModel.error(ResultCode.RESULT_CODE_ERROR, e.getMessage());
    }

    /**
     * 500 - Internal Server Error
     */
    @ResponseBody
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    public ResultModel handleException(Exception e) {
        LogController.warn("GlobalExceptionHandler", e.getMessage());
        return ResultModel.error(ResultCode.RESULT_CODE_SERVER_ERROR, e.getMessage());
    }

    /**
     * 操作数据库出现异常:名称重复，外键关联
     */
    @ResponseBody
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResultModel handleException(DataIntegrityViolationException e) {
        LogController.warn("GlobalExceptionHandler", e.getMessage());
        return ResultModel.error(ResultCode.RESULT_CODE_DATABASE_ERROR, e.getMessage());
    }

}
