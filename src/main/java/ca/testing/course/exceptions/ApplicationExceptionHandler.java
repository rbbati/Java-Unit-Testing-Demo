package ca.testing.course.exceptions;

import ca.testing.course.dtos.StudentsResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
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

    //    @ExceptionHandler(value = {StudentInvalidException.class})
    public ResponseEntity<Object> studentInvalidExceptionHandler(StudentInvalidException e, WebRequest request) {
        return ResponseEntity.badRequest().body(e.getMessage());
    }
}
