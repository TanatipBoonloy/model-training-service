package kmitl.sp.smp.handler;

import kmitl.sp.smp.exception.ResourceNotFoundException;
import kmitl.sp.smp.model.server.response.base.ApiBaseErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.TypeMismatchException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
 * Created by Jo on 3/27/2017.
 */
@Slf4j
@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<Object> handleResourceNotFoundException(ResourceNotFoundException rnfe, HttpServletRequest request) {
        log.warn("", rnfe);
        HttpStatus status = HttpStatus.NOT_FOUND;
        ApiBaseErrorResponse errorResponse = createErrorResponse(rnfe, status);
        return new ResponseEntity<>(errorResponse, status);
    }

    @Override
    protected ResponseEntity<Object> handleTypeMismatch(TypeMismatchException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        log.warn("", ex);
        ApiBaseErrorResponse errorResponse = createErrorResponse(ex, status);
        return new ResponseEntity<>(errorResponse, new HttpHeaders(), status);
    }

    private ApiBaseErrorResponse createErrorResponse(Exception ex, HttpStatus status) {
        ApiBaseErrorResponse errorResponse = new ApiBaseErrorResponse();
        errorResponse.setCode(status.value());
        errorResponse.setStatus(status.getReasonPhrase());
        errorResponse.setMessage(ex.getLocalizedMessage());
        errorResponse.setDeveloperMessage(ex.toString());
        errorResponse.setTimestamp(new Date());
        return errorResponse;
    }
}
