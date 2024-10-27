package com.tech.techno.service.priceList;

import com.tech.techno.model.Item;
import com.tech.techno.model.PriceList;

import java.util.List;

public interface PriceListService{
    PriceList getPriceByItemcode(Item itemcode);
}
