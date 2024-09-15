/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dto.response;

import java.io.Serializable;
import java.util.List;

/**
 *
 * @author yasithsandesh
 */
public class ResponseDTO<T> implements Serializable {

    private boolean status;
    private String message;
    private String url;
    private List<T> dataList;
    private T data;
    private int code;

    public ResponseDTO() {
    }

    public ResponseDTO(boolean status, String message, String url, List<T> dataList, T data, int code) {
        this.status = status;
        this.message = message;
        this.url = url;
        this.dataList = dataList;
        this.data = data;
        this.code = code;
    }

 

   

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public List<T> getDataList() {
        return dataList;
    }

    public void setDataList(List<T> dataList) {
        this.dataList = dataList;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }
    
    
}
