package com.tech.techno.service.supplier;

import com.tech.techno.model.Supplier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

public interface SupplierService {

    Page<Supplier> getAllSuppliers(Pageable pageable);

    public Supplier saveSupplers(Map<String,String>map,Supplier supplier);

    List<Supplier> getAllSupplierByStatus(int status);

    Supplier getSupplierById(Integer id);

    Supplier updateSupplier(Map<String,String>map);

    Supplier deleteSupplier(int id);
}
