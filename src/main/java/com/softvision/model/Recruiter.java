package com.softvision.model;

import javax.persistence.GeneratedValue;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "recruiter")
public class Recruiter implements Comparable<Recruiter> {

    @Id
    @GeneratedValue
    private String id;

    @NotNull
    @Min(value = 0, message = "Recruiter Id cannot be null or empty")
    private String recruiterId;

    @NotNull(message = "Recruiter First Name cannot be null")
    @Size(min = 2, max = 100, message = "Recruiter First Name must be atleast 2 and 100 characters")
    private String firstName;

    @NotNull(message = "Recruiter Last Name cannot be null")
    @Size(min = 0, max = 100, message = "Recruiter Last Name must be atleast 2 and 100 characters")
    private String lastName;

    private String emailId;

    @Pattern(regexp = "(^$|[0-9]{10})", message = "Invalid Phone number")
    public String contactNumber;
    public boolean isDeleted;

    @Override
    public int compareTo(Recruiter o) {
        int val = 0;
        if (this.getFirstName().compareTo(o.getFirstName()) == -1) {
            val = -1;
        } else if (this.getFirstName().compareTo(o.getFirstName()) == 1) {
            val = 1;
        }
        return val;
    }


}
