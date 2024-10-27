package com.tech.techno.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Getter
@Setter
public abstract class AbstractSearchDetail {
    public Integer id;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    public Date fromDate;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    public Date toDate;

}
