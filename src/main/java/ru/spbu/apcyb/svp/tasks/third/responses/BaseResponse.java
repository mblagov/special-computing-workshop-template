package ru.spbu.apcyb.svp.tasks.third.responses;

/**
 * Базовый класс для всех ответов API.
 */
public class BaseResponse {
    private boolean success;
    private String errorMessage;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
