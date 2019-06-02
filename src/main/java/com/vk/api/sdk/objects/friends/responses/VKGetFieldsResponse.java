package com.vk.api.sdk.objects.friends.responses;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;
import com.vk.api.sdk.objects.users.VKUserLists;

import java.util.List;
import java.util.Objects;

public class VKGetFieldsResponse {

    @SerializedName("count")
    private Integer count;
    @SerializedName("items")
    private List<VKUserLists> items;

    public VKGetFieldsResponse() {
    }

    public Integer getCount() {
        return this.count;
    }

    public VKGetFieldsResponse setCount(Integer count) {
        this.count = count;
        return this;
    }

    public List<VKUserLists> getItems() {
        return this.items;
    }

    public VKGetFieldsResponse setItems(List<VKUserLists> items) {
        this.items = items;
        return this;
    }

    public int hashCode() {
        return Objects.hash(this.count, this.items);
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        } else if (o != null && this.getClass() == o.getClass()) {
            VKGetFieldsResponse getFieldsResponse = (VKGetFieldsResponse) o;
            return Objects.equals(this.count, getFieldsResponse.count) && Objects.equals(this.items, getFieldsResponse.items);
        } else {
            return false;
        }
    }

    public String toString() {
        Gson gson = new Gson();
        return gson.toJson(this);
    }

    public String toPrettyString() {
        StringBuilder sb = new StringBuilder("VKGetFieldsResponse{");
        sb.append("count=").append(this.count);
        sb.append(", items=").append(this.items);
        sb.append('}');
        return sb.toString();
    }
}
