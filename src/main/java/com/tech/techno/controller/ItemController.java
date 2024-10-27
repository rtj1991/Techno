package com.tech.techno.controller;

import com.tech.techno.dto.ItemCreate;
import com.tech.techno.model.*;
import com.tech.techno.service.item.ItemService;
import com.tech.techno.service.priceList.PriceListService;
import com.tech.techno.service.supplier.SupplierService;
import com.tech.techno.util.Const;
import com.tech.techno.util.PageWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Map;


@Controller
@RequestMapping("/item")
public class ItemController {

    @Autowired
    private SupplierService supplierService;

    @Autowired
    private PriceListService priceListService;

    @Autowired
    private ItemService itemService;

    @RequestMapping("/list")
    public String itemList(Model model, Pageable pageable) {

        PageRequest request = new PageRequest(pageable.getPageNumber(), 10, Sort.Direction.DESC, "itemcode");
        Page<Item> items = itemService.getAllItemList(request);
        PageWrapper<Item> page = new PageWrapper<>(items, "/item/list");
        model.addAttribute("item", items);
        model.addAttribute("page", page);

        return "system/item/item_list";
    }

    @RequestMapping("/create")
    public String createItem(Model model, ItemCreate itemCreate) {

        model.addAttribute("category", new Category());
        model.addAttribute("itemCreate", itemCreate);
        model.addAttribute("item", new Item());

        model.addAttribute("supplier", supplierService.getAllSupplierByStatus(Const.STATUS_ACTIVE));
        return "system/item/create_item";
    }

    @PostMapping("/saveItem")
    public String saveItem(@RequestParam Map<String, String> map, ItemCreate itemCreate, @ModelAttribute Item item, @ModelAttribute PriceList priceList) {
        if (!map.isEmpty() && map != null) {
            itemService.saveItem(map, itemCreate, item, priceList);
        }
        return "redirect:/item/" + item.getItemcode();
    }

    @RequestMapping("/{itemcode}")
    public String editItem(Model model, @PathVariable Integer itemcode) {

        if (itemcode != null) {
            Item item = new Item();
            item.setItemcode(itemcode);

            Item items = itemService.getItemById(itemcode);

            model.addAttribute("item", itemService.getItemById(itemcode));
            model.addAttribute("price", priceListService.getPriceByItemcode(item));
            model.addAttribute("category", items.getCategory().getId());
            model.addAttribute("categories", new Category());
            model.addAttribute("subcategory", items.getSubcategory().getId());
            model.addAttribute("supplier", items.getSupplier().getId());
            model.addAttribute("itemcodehide", itemcode);
        }
        return "system/item/item_edit";
    }

    @PostMapping("/updateItem")
    public String updateItem(@RequestParam Map<String, String> map) {
        if (map != null && !map.isEmpty()) {
            itemService.updateItem(map);
        }
        return "redirect:/item/list";
    }


    @RequestMapping("/deleteItem/{id}")
    public String deleteItem(@PathVariable Integer id) throws Exception {
        if (id != null) {
            itemService.deleteItem(id);
        }
        return "redirect:/item/list";
    }

}
