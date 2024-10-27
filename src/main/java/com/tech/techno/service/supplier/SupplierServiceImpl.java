package com.tech.techno.service.supplier;

import com.tech.techno.model.Supplier;
import com.tech.techno.repository.SupplierRepository;
import com.tech.techno.util.Const;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

@Service
@Transactional
public class SupplierServiceImpl implements SupplierService {

    @Autowired
    SupplierRepository supplierRepository;

    @Override
    public Page<Supplier> getAllSuppliers(Pageable pageable) {
        return supplierRepository.findAllByStatus(Const.STATUS_ACTIVE, pageable);
    }

    @Override
    public Supplier saveSupplers(Map<String, String> map, Supplier supplier) {

        double credit_limit = Double.parseDouble(map.get("credit_limit"));
        int credit_period = Integer.parseInt(map.get("credit_period"));

        supplier.setCompany(map.get("company"));
        supplier.setSuplier_name(map.get("suplier"));
        supplier.setAddress(map.get("address"));
        supplier.setStatus(1);
        supplier.setCreditLimit(credit_limit);
        supplier.setCreditPeriod(credit_period);
        supplier.setMobileNo(map.get("phone"));
        supplierRepository.save(supplier);

        return supplier;
    }

    @Override
    public List<Supplier> getAllSupplierByStatus(int status) {
        return supplierRepository.findAllByStatus(status);
    }

    @Override
    public Supplier getSupplierById(Integer id) {
        return supplierRepository.findById(id);

    }

    @Override
    public Supplier updateSupplier(Map<String, String> map) {

        String supplier = map.get("supplierId");
        int bySupplier = Integer.parseInt(supplier);
        Supplier supplier1 = supplierRepository.findById(bySupplier);

        double creditlimit = Double.parseDouble(map.get("creditLimit"));
        int creditperiod = Integer.parseInt(map.get("creditPeriod"));

        supplier1.setSuplier_name(map.get("suplier_name"));
        supplier1.setCompany(map.get("company"));
        supplier1.setAddress(map.get("address"));
        supplier1.setMobileNo(map.get("mobileNo"));
        supplier1.setEmail(map.get("email"));
        supplier1.setCreditLimit(creditlimit);
        supplier1.setCreditPeriod(creditperiod);
        supplierRepository.save(supplier1);

        return supplier1;
    }

    @Override
    public Supplier deleteSupplier(int id) {
        Supplier supplier = supplierRepository.findById(id);
        supplier.setStatus(Const.STATUS_DEACTIVE);
        return supplierRepository.save(supplier);
    }
}
