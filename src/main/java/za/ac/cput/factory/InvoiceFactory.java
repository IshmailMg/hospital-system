package za.ac.cput.factory;

import za.ac.cput.domain.Invoice;
import za.ac.cput.util.StringHelper;
/*
    InvoiceFactory.java
    Factory for  Invoice
    Author: Shina Kara (219333181)
    Date: 13 August 2022
*/
public class InvoiceFactory {
    public static Invoice createInvoice(String invoiceNum, Double invoiceAmount, String invoiceDate, String invoiceType) {
      StringHelper.checkStringParam("invoiceNum", invoiceNum);
        StringHelper.checkStringParam("invoiceType", invoiceType);
        StringHelper.checkStringParam("invoiceDate", invoiceDate);
      return new Invoice.Builder().invoiceNum(invoiceNum).invoiceAmount(invoiceAmount).invoiceType(invoiceType).invoiceDate(invoiceDate).build();

    }

}
