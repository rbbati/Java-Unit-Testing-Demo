package ca.testing.course.dtos;

import lombok.Data;

import java.util.List;

@Data
public class StudentsResponse {
    private boolean success;
    private String truckingId;
    private List<String> messages;
    private Object data;
}
