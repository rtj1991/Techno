package com.tech.techno.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/invoice")
public class InvoiceController {

    @RequestMapping("/create")
    public String createInvoice() {

        return "system/invoice/create_invoice";
    }

    @RequestMapping("/list")
    public String invoiceList() {

        return "system/invoice/invoice_list";
    }
}
