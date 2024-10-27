package com.tech.techno.service.item;

import com.tech.techno.dto.ItemCreate;
import com.tech.techno.model.*;
import com.tech.techno.repository.ItemRepository;
import com.tech.techno.repository.PriceListRepository;
import com.tech.techno.util.Const;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;


@Service
@Transactional
public class ItemServiceImpl implements ItemService {

    @Autowired
    private PriceListRepository priceListRepository;

    @Autowired
    private ItemRepository itemRepository;


    @Override
    public Page<Item> getAllItemList(Pageable pageable) {
        return itemRepository.findAllByStatus(Const.STATUS_ACTIVE, pageable);
    }

    @Override
    public Item saveItem(Map<String, String> map, ItemCreate itemCreate, Item item, PriceList priceList) {
//        save item start
        double width = Double.parseDouble(map.get("width"));
        double height = Double.parseDouble(map.get("height"));
        double conWidth = width * 12;
        double conHeigth = height * 12;
        double area = conHeigth * conWidth;

        item.setItemName(map.get("itemName"));
        item.setGauge(map.get("gauge"));
        item.setStatus(Const.STATUS_ACTIVE);
        item.setThickness(map.get("thickness"));
        item.setWidth(width);
        item.setHeight(height);
        item.setAreaOfItem(area);
//        item.setSubcategory(itemCreate.getSubCategory());
        itemRepository.save(item);

        Integer id = item.getItemcode();
        Item itemcode = itemRepository.findByItemcode(id);
        item.setItemRefcode("techno" + id);
        itemRepository.save(itemcode);
//        save item end

//        save pricelist start
        priceList.setCostPrice(Double.parseDouble(map.get("cprice")));
        priceList.setSellingPrice(Double.parseDouble(map.get("sprice")));
        priceList.setRate(Double.parseDouble(map.get("rate")));
        priceList.setItem(itemcode);
        priceList.setStatus(1);
        priceListRepository.save(priceList);
//        save pricelist end

        Integer pitem = priceList.getId();
        item.setPriceId(pitem);

        Item getitem = priceList.getItem();
        itemRepository.save(getitem);

        return item;
    }

    @Override
    public Item getItemById(Integer itemcode) {
        return itemRepository.findByItemcode(itemcode);
    }

    @Override
    public Item updateItem(Map<String, String> map) {

        String itemcode = map.get("itemcodehide");
        int code = Integer.parseInt(itemcode);
        int supplier = Integer.parseInt(map.get("supplier"));
        int category = Integer.parseInt(map.get("category"));
        int subcategory = Integer.parseInt(map.get("subcategory"));

        Supplier suplierobj = new Supplier();
        suplierobj.setId(supplier);

        Category cat = new Category();
        cat.setId(category);

        SubCategory subcate = new SubCategory();
        subcate.setId(subcategory);

        Item codeByItem = itemRepository.findByItemcode(code);

        codeByItem.setItemName(map.get("itemName"));
        codeByItem.setGauge(map.get("gauge"));
        codeByItem.setThickness(map.get("thickness"));

        codeByItem.setSupplier(suplierobj);
        codeByItem.setCategory(cat);
        codeByItem.setSubcategory(subcate);

        PriceList price = priceListRepository.findById(codeByItem.getPriceId());

        price.setCostPrice(Double.parseDouble(map.get("costPrice")));
        price.setSellingPrice(Double.parseDouble(map.get("sellingPrice")));
        price.setRate(Double.parseDouble(map.get("rate")));

        priceListRepository.save(price);
        itemRepository.save(codeByItem);

        return codeByItem;
    }

    @Override
    public Item deleteItem(Integer id) throws Exception {
        Item item1 = itemRepository.findByItemcode(id);
        item1.setStatus(Const.STATUS_DEACTIVE);
        itemRepository.save(item1);
        return item1;
    }
}
