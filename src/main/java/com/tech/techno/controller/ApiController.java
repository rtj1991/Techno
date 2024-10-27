package com.tech.techno.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tech.techno.model.*;
import com.tech.techno.repository.*;
import com.tech.techno.service.category.CategoryService;
import com.tech.techno.service.item.ItemService;
import com.tech.techno.service.subcategory.SubCategoryService;
import com.tech.techno.service.supplier.SupplierService;
import com.tech.techno.service.user.UserService;
import com.tech.techno.util.Const;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class ApiController {

    @Autowired
    private SubCategoryRepository subCategoryRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private SubCategoryService subCategoryService;

    @Autowired
    private SupplierRepository supplierRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private ItemService itemService;

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private TechnoDataRepository dataRepository;

    @RequestMapping("/getcategory")
    public List<Category> getAllCategory() {

        return categoryRepository.findAllByDeleted(Const.NOT_DELETED);
    }

    @RequestMapping("/getsubcategory/{id}")
    public List<SubCategory> getSubcategoryById(@PathVariable Integer id) {
        Category category = new Category();
        category.setId(id);
        return subCategoryRepository.findByCategory(category);
    }

    @RequestMapping("/sidebar")
    public boolean toggleSidebar(HttpServletRequest request) {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        boolean menuCollapse = user.isMenuCollapse();
        user.setMenuCollapse(!menuCollapse);
        User u = userService.saveUser(user);
        return u.isMenuCollapse() != menuCollapse;
    }

    @RequestMapping("/saveCatogory/{name}")
    public Category saveCategory(@PathVariable String name, @ModelAttribute Category category) throws Exception {


        category.setDescription(name);
        category.setDeleted(Const.NOT_DELETED);
        categoryService.categorySave(category);

        return category;
    }

    @RequestMapping("/saveSubcatogory/{category}/{subcategory}")
    public SubCategory saveSubategory(@PathVariable Integer category, @PathVariable String subcategory, @ModelAttribute SubCategory subCategory) throws Exception {

        Category cat = new Category();
        cat.setId(category);
        subCategory.setCategory(cat);

        subCategory.setDescription(subcategory);
        subCategory.setDeleted(Const.NOT_DELETED);

        subCategoryService.savesubCategory(subCategory);
        return subCategory;
    }

    @RequestMapping("/getSupplier")
    public List<Supplier> getAllSupplier() {
        return supplierRepository.findAllByStatus(Const.STATUS_ACTIVE);

    }

    @RequestMapping("/saveSupplier/")
    public Supplier saveSupplier(@ModelAttribute(name = "supplier") Supplier supplier) {

        return supplierRepository.save(supplier);
    }

    @RequestMapping("/get_customers")
    public List<Customer> getAllCustomerByKeyword(@RequestParam String code) {
        if (code != null && !code.isEmpty()) {
            String[] keys = code.split(" ");
            return dataRepository.getAllCustomerByKeyword(keys);

        } else {
            return new ArrayList<>();
        }
    }
}
