package org.zerock.mallapi.presentation;

public record ApiResponse<T>(
        T body,
        String message
) {
    public static <T> ApiResponse<T> ok(T body) {
        return new ApiResponse<>(body, null);
    }

    public static ApiResponse<Void> error(String message) {
        return new ApiResponse<>(null, message);
    }
}
