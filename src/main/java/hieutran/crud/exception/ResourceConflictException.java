package hieutran.crud.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.time.LocalDateTime;

//! lỗi conflict nếu tài nguyên đã tồn tại
@ResponseStatus(value = HttpStatus.CONFLICT)
public class ResourceConflictException extends RuntimeException{

    private ErrorResponse errorResponse;

    public ResourceConflictException(String message) {
        super(message);
        errorResponse = new ErrorResponse();
        errorResponse.setStatus(HttpStatus.CONFLICT.value());
        errorResponse.setError(HttpStatus.CONFLICT.getReasonPhrase());
        errorResponse.setMessage(message);
        errorResponse.setTimestamp(LocalDateTime.now());
    }

    public ErrorResponse getErrorResponse() {
        return errorResponse;
    }
}
