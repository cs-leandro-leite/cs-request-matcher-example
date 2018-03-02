/*
 * Copyright (C) 2017 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package br.com.concrete.leite.csrequestmatcher.model;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;


public class ResponseData<T> {

    public enum Status {
        SUCCESS,
        ERROR
    }

    @NonNull public final Status status;
    @Nullable public final String message;
    @Nullable public final T data;

    public ResponseData(@NonNull Status status, @Nullable T data, @Nullable String message) {
        this.status = status;
        this.data = data;
        this.message = message;
    }

    public static <T> ResponseData<T> clone(ResponseData<T> responseData) {
        return new ResponseData<>(responseData.status, responseData.data, responseData.message);
    }

    public static <T> ResponseData<T> success(@Nullable T data) {
        return new ResponseData<>(Status.SUCCESS, data, null);
    }

    public static <T> ResponseData<T> error(String message, @Nullable T data) {
        return new ResponseData<>(Status.ERROR, data, message);
    }

    @Override
    public String toString() {
        return "ResponseData{" +
                "status=" + status +
                ", message='" + message + '\'' +
                ", data=" + data +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;

        if (o == null || getClass() != o.getClass())
            return false;

        ResponseData<?> resource = (ResponseData<?>) o;

        if (status != resource.status)
            return false;

        if (message != null ? !message.equals(resource.message) : resource.message != null)
            return false;

        return data != null ? data.equals(resource.data) : resource.data == null;
    }

    @Override
    public int hashCode() {
        int result = status.hashCode();
        result = 31 * result + (message != null ? message.hashCode() : 0);
        result = 31 * result + (data != null ? data.hashCode() : 0);
        return result;
    }
}
