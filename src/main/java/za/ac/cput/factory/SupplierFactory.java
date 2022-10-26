package za.ac.cput.factory;

import za.ac.cput.domain.Supplier;
import za.ac.cput.util.StringHelper;


/*
    SupplierFactory.java
    Factory for Supplier
    Author: Nonzwakazi Mgxaji
    Student number: 213181584
    Date: 14 August 2022
*/

public class SupplierFactory {
    public static Supplier createSupplier(String suppID, String suppAddress, String suppRegNum) {
        StringHelper.checkStringParam("suppID", suppID);
        StringHelper.checkStringParam("suppAddress", suppAddress);
        StringHelper.checkStringParam("suppRegNum", suppRegNum);

        return new Supplier.Builder().setSuppID(suppID).setSuppAddress(suppAddress).setSuppRegNum(suppRegNum).build();
    }
}