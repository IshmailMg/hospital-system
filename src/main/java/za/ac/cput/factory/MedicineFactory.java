package za.ac.cput.factory;

import za.ac.cput.domain.Medicine;
import za.ac.cput.util.StringHelper;
/*
    MedicineFactory.java
    Entity: Patient
    Author: Ishmail T Mgwena (215088417)
    Date: 07 August 2022
*/
public class MedicineFactory {
    public static Medicine createMedicine(String medicineId, String medicineAmount, String medicineType) {
        StringHelper.checkStringParam("medicineId", medicineId);
        StringHelper.checkStringParam("medicineAmount", medicineAmount);
        StringHelper.checkStringParam("medicineType", medicineType);
        return new Medicine.Builder().setMedicineId(medicineId).setMedicineAmount(medicineAmount).setMedicineType(medicineType).build();
    }
}
