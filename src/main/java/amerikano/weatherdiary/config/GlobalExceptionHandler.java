package amerikano.weatherdiary.config;

import amerikano.weatherdiary.error.ErrorResponse;
import amerikano.weatherdiary.error.InvalidDate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import static amerikano.weatherdiary.type.ErrorCode.INVALID_REQUEST;
import static amerikano.weatherdiary.type.ErrorCode.INVALID_DATE;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(InvalidDate.class)
    public ErrorResponse handleInvalidDate() {
        log.error("InvalidDate occured.");
        return new ErrorResponse(INVALID_DATE,
                "날짜가 적정 범위를 벗어났습니다.");
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    public ErrorResponse handleAllException() {
        log.error("Error occured.");
        return new ErrorResponse(INVALID_REQUEST,
                "올바른 요청 형식이 아닙니다.");
    }
}
