package com.tech.techno.service.priceList;

import com.tech.techno.model.Item;
import com.tech.techno.model.PriceList;
import com.tech.techno.repository.PriceListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class PriceListServiceImpl implements PriceListService {
    @Autowired
    private PriceListRepository priceListRepository;

    @Override
    public PriceList getPriceByItemcode(Item itemcode) {
        return priceListRepository.findByItem(itemcode);
    }
}
