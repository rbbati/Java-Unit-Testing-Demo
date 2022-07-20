package ca.testing.course.exceptions;

import ca.testing.course.dtos.StudentsResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Arrays;

@ControllerAdvice
public class ApplicationExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = {Exception.class})
    public ResponseEntity<StudentsResponse> allExceptionsHandler(Exception e, WebRequest request) {
        StudentsResponse response = new StudentsResponse();
        response.setSuccess(false);
        response.setMessages(Arrays.asList(e.getMessage()));
        return ResponseEntity.badRequest().body(response);
    }

    @ExceptionHandler(value = {StudentInvalidException.class})
    public ResponseEntity<StudentsResponse> studentInvalidExceptionHandler(StudentInvalidException e, WebRequest request) {
        StudentsResponse response = new StudentsResponse();
        response.setTruckingId(request.getAttribute("trackingId", RequestAttributes.SCOPE_REQUEST).toString());
        response.setSuccess(false);
        response.setMessages(Arrays.asList(e.getMessage()));
        return ResponseEntity.badRequest().body(response);
    }
}
