package com.example.reader.viewmodel;

import org.hibernate.exception.DataException;

import java.io.Serializable;
import java.util.HashMap;

public class ResponseResult<T> implements Serializable {

    private String msg = "";

    public ResponseResult() {
    }

    private HashMap<String, Object> responseResult;
    private DataException exception;

    public void put(String key, T t) {
        responseResult.put(key, t);
    }

    public void putException(DataException e) {
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public HashMap<String, Object> getResponseResult() {
        return responseResult;
    }

    public void setResponseResult(HashMap<String, Object> responseResult) {
        this.responseResult = responseResult;
    }

    public DataException getException() {
        return exception;
    }

    public void setException(DataException exception) {
        this.exception = exception;
    }
}
