package com.lst.wxproject.common;




public class Result<T> {
    private int code;
    private String msg;
    private T data;


    public Result(T data) {
        this.data= data;
    }

    public Result(){

    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public static Result success(){
        Result result = new Result<>( );
        result.setCode(20000);
        result.setMsg("成功");
        return result;
    }

    public static <T> Result<T> success(T data){
        Result<T> result = new Result<>(data);
        result.setMsg("成功");
        result.setCode(20000);
        return result;
    }

    public static Result error(int code,String msg){
        Result result = new Result();
        result.setCode(code);
        result.setMsg(msg);
        return result;
    }


}
