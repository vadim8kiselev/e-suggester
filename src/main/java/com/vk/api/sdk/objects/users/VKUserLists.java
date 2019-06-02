package com.vk.api.sdk.objects.users;

import com.google.gson.annotations.SerializedName;
import com.vk.api.sdk.objects.friends.UserXtrLists;

import java.util.Objects;

public class VKUserLists extends UserXtrLists {

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
        VKUserLists VKUserLists = (VKUserLists) o;
        return Objects.equals(isClosed, VKUserLists.isClosed);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("VKUserLists{");
        sb.append("is_closed=").append(isClosed);
        sb.append('}');
        return sb.toString();
    }
}
