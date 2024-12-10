package ru.spbu.apcyb.svp.tasks.third.responses;

import com.google.gson.annotations.SerializedName;

public class UploadLinkResponse extends BaseResponse {
    @SerializedName("href")
    private String href;

    @SerializedName("method")
    private String method;

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }
}
