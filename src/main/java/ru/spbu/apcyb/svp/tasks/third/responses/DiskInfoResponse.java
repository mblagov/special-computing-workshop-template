package ru.spbu.apcyb.svp.tasks.third.responses;

import com.google.gson.annotations.SerializedName;

/**
 * Класс для представления информации о диске.
 * Наследуется от ApiResponse и содержит данные о дисковом пространстве.
 *
 * @see <a href="https://yandex.ru/dev/disk-api/doc/ru/reference/capacity">Документация</a>
 */
public class DiskInfoResponse extends BaseResponse {
    @SerializedName("total_space")
    private long totalSpace;

    @SerializedName("used_space")
    private long usedSpace;

    @SerializedName("trash_size")
    private long trashSize;

    public long getTotalSpace() {
        return totalSpace;
    }

    public long getUsedSpace() {
        return usedSpace;
    }

    public long getTrashSize() {
        return trashSize;
    }
}
