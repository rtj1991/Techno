package com.tech.techno.controller;

import com.tech.techno.model.Supplier;
import com.tech.techno.service.supplier.SupplierService;
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
@RequestMapping("/supplier")
public class SupplierController {

    @Autowired
    SupplierService supplierService;

    @RequestMapping("/list")
    public String supplerList(Model model, Pageable pageable) {
        PageRequest request = new PageRequest(pageable.getPageNumber(), 10, Sort.Direction.DESC, "id");
        Page<Supplier> suppliers = supplierService.getAllSuppliers(request);
        PageWrapper<Supplier> page = new PageWrapper<>(suppliers, "/supplier/list");
        model.addAttribute("suppliers", suppliers);
        model.addAttribute("page", page);
        return "system/supplier/supplier_list";
    }

    @RequestMapping("/create")
    public String createSupplier() {

        return "system/supplier/create_supplier";
    }

    @PostMapping("/saveSupplier")
    public String saveSupplier(@RequestParam Map<String, String> map, @ModelAttribute Supplier supplier) {

        supplierService.saveSupplers(map, supplier);
        return "redirect:/supplier/" + supplier.getId();
    }

    @RequestMapping("/{id}")
    public String editSupplier(@PathVariable Integer id, Model model) {
        if (id != null) {
//            supplierService.getSupplierById(id);
            model.addAttribute("supplier", supplierService.getSupplierById(id));
            model.addAttribute("supcode", id);
        }
        return "system/supplier/supplier_edit";
    }

    @PostMapping("/updateSupplier")
    public String updateSupplier(@RequestParam Map<String, String> map) {
        if (map != null && !map.isEmpty()) {
            supplierService.updateSupplier(map);
        }
        return "redirect:/supplier/list";

    }

    @RequestMapping("deleteSupplier/{id}")
    public String deleteSupplier(@PathVariable Integer id) {
        if (id != null) {
            supplierService.deleteSupplier(id);
        }
        return "redirect:/supplier/list";
    }
}
