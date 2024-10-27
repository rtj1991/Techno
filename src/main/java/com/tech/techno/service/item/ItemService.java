package com.tech.techno.service.item;

import com.tech.techno.dto.ItemCreate;
import com.tech.techno.model.Item;
import com.tech.techno.model.PriceList;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Map;


public interface ItemService {

     Page<Item> getAllItemList(Pageable pageable);

     Item saveItem(Map<String,String>map, ItemCreate itemCreate, Item item, PriceList priceList);

     Item updateItem(Map<String,String>map);

     Item getItemById(Integer itemcode);

     Item deleteItem(Integer id)throws Exception;
}