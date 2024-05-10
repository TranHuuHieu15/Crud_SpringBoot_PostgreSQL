package hieutran.crud.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.time.LocalDateTime;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException {
    private ErrorResponse errorResponse;
    //! tạo một constructor với tham số là message để truyền vào thông báo lỗi
    public ResourceNotFoundException(String message) {
        super(message);
        errorResponse = new ErrorResponse();
        errorResponse.setStatus(HttpStatus.NOT_FOUND.value());
        errorResponse.setError(HttpStatus.NOT_FOUND.getReasonPhrase());
        errorResponse.setMessage(message);
        errorResponse.setTimestamp(LocalDateTime.now());
    }

    public ErrorResponse getErrorResponse() {
        return errorResponse;
    }
}
