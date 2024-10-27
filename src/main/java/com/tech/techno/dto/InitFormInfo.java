package com.tech.techno.dto;


import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Data
public class InitFormInfo implements Serializable {

    @Size(min = 3, max = 100, message = "Full name must include more than 3 character")
    private String fullName;

    @Size(min = 3, max = 10, message = "Last name must include more than 3 character")
    private String lastName;

    @Pattern(flags = Pattern.Flag.CASE_INSENSITIVE, regexp = ".+@.+\\..+", message = "Please provide a valid email address")
    private String email;

    //    @NotNull(message = "Select your business model")
    private Integer businessModel;


    @Size(min = 3, max = 100, message = "Business name must include more than 3 character")
    private String companyName;


    @Size(min = 3, max = 100, message = "Reg. no must include more than 3 character")
    private String regNo;
    private String website;

    @NotNull(message = "Required email")
    @Pattern(flags = Pattern.Flag.CASE_INSENSITIVE, regexp = ".+@.+\\..+", message = "Please provide a valid email address")
    private String companyEmail;

    @Size(min = 3, max = 255, message = "Address must include more than 3 character")
    private String address;
    private String telephone;
    private String fax;

    @Size(min = 8, max = 255, message = "Password must include more than 8 character")
    private String password;

}
