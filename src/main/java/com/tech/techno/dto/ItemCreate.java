package com.tech.techno.dto;

import com.tech.techno.model.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
public class ItemCreate extends AbstractSearchDetail{

//    public Category category;
    public SubCategory subCategory;
    public Double costPrice;
    public Double sellingPrice;
    public Double rate;
    public Supplier supplier;
}
