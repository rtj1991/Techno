package com.tech.techno.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Component;

import java.util.Date;

@Getter
@Setter
@Component
public class DefaltSave extends AbstractSearchDetail{

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    public Date defaltDate;
}
