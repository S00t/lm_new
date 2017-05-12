package soot.letsmeet.models;

import android.support.annotation.Nullable;

import com.j256.ormlite.field.DatabaseField;

import java.util.UUID;

public class Logger {
    @DatabaseField(generatedId = true)
    private UUID mId;
    @DatabaseField
    private String mKomunikat;
    @DatabaseField
    @Nullable
    private Long mDataZdarzenia;

    public Logger() {
    }

    public Logger(String komunikat, @Nullable Long dataZdarzenia) {
        mKomunikat = komunikat;
        mDataZdarzenia = dataZdarzenia;
    }

    public UUID getId() {
        return mId;
    }

    public void setId(UUID id) {
        mId = id;
    }

    public String getKomunikat() {
        return mKomunikat;
    }

    public void setKomunikat(String komunikat) {
        mKomunikat = komunikat;
    }

    public @Nullable Long getDataZdarzenia() {
        return mDataZdarzenia;
    }

    public void setDataZdarzenia(@Nullable Long dataZdarzenia) {
        mDataZdarzenia = dataZdarzenia;
    }


}
