package za.ac.cput.factory;

import za.ac.cput.domain.Patient;
import za.ac.cput.util.StringHelper;
/*
    PatientFactory.java
    Entity: Patient
    Author: Ishmail T Mgwena (215088417)
    Date: 06 August 2022
*/
public class PatientFactory {
    public static Patient createPatient(String patIdNum, String patFirstName, String patLastName, String patAddress, String patCellNum){
       StringHelper.checkStringParam("patIdNum",patIdNum);
       StringHelper.checkStringParam("patFirstName",patFirstName);
       StringHelper.checkStringParam("patLastName",patLastName);
       StringHelper.checkStringParam("patAddress",patAddress);
       StringHelper.checkStringParam("patCellNum",patCellNum);
        return new Patient.Builder().setPatIdNum(patIdNum).setPatFirstName(patFirstName).setPatLastName(patLastName)
                .setPatAddress(patAddress).setPatCellNum(patCellNum).build();
    }
}
