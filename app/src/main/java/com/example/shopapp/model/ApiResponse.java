package com.example.shopapp.model;

import retrofit2.Response;

public class ApiResponse <T>{
    private String status;
    private T data;
    private String message;

    public ApiResponse(String status, T data, String message) {
        this.status = status;
        this.data = data;
        this.message = message;
    }
    // Getters và Setters
//    status: Trạng thái của yêu cầu (thành công, thất bại, lỗi,...).
//    data: Dữ liệu thực sự được trả về (nếu có).
//    message: Một thông điệp mô tả ngắn gọn về kết quả của yêu cầu.
    public ApiResponse(Response<T> response) {

        // Extract data from the response and set the fields
        if (response.isSuccessful()) {
            this.status = "success";
            this.data = response.body();
            this.message = null;
        } else {
            this.status = "error";
            this.data = null;
            this.message = "An error occurred.";
        }
    }
    public ApiResponse(Throwable throwable) {
        this.status = "error";
        this.data = null;
        this.message = throwable.getMessage();
    }
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }




}
