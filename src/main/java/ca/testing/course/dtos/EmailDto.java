package ca.testing.course.dtos;

import lombok.Data;

@Data
public class EmailDto {
    private String emailAddress;
    private String emailSubject;
    private String emailBody;
}
