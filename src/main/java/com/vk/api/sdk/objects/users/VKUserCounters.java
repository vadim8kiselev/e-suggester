package com.vk.api.sdk.objects.users;

import com.google.gson.annotations.SerializedName;

import java.util.Objects;

public class VKUserCounters extends UserXtrCounters {

    @SerializedName("is_closed")
    private String isClosed;

    public String isClosed() {
        return isClosed;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), isClosed);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        VKUserCounters VKUserCounters = (VKUserCounters) o;
        return Objects.equals(isClosed, VKUserCounters.isClosed);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("VKUserCounters{");
        sb.append("is_closed=").append(isClosed);
        sb.append('}');
        return sb.toString();
    }
}
