package za.ac.cput.domain;

import com.sun.istack.NotNull;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Objects;

/*
    Patient.java
    Entity: Patient
    Author: Ishmail T Mgwena (215088417)
    Date: 05 August 2022
*/
@Entity
@Table(name = "tbl_patient")
public class Patient implements Serializable {

    @Id
    private String patIdNum;
    @NotNull
    private String patFirstName;
    private String patLastName;
    private String patAddress;
    private String patCellNum;

    public Patient(Builder builder) {
        this.patIdNum = builder.patIdNum;
        this.patFirstName = builder.patFirstName;
        this.patLastName = builder.patLastName;
        this.patAddress = builder.patAddress;
        this.patCellNum = builder.patCellNum;
    }
    public Patient(){}

    public String getPatIdNum() {return patIdNum;}

    @Override
    public String toString() {
        return "Patient{" +
                "patIdNum='" + patIdNum + '\'' +
                ", patFirstName='" + patFirstName + '\'' +
                ", patLastName='" + patLastName + '\'' +
                ", patAddress='" + patAddress + '\'' +
                ", patCellNum='" + patCellNum + '\'' +
                '}';
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Patient patient = (Patient) o;
        return patIdNum.equals(patient.patIdNum) && patFirstName.equals(patient.patFirstName) && patLastName.equals(patient.patLastName)
                && patAddress.equals(patient.patAddress) && patCellNum.equals(patient.patCellNum);}

    @Override
    public int hashCode() {
        return Objects.hash(patIdNum,patFirstName,patLastName,patAddress,patCellNum);}

    public static class Builder {
        private String patIdNum, patFirstName, patLastName, patAddress, patCellNum;

        public Builder patIdNum(String patIdNum) {
            this.patIdNum = patIdNum;
            return this;
        }

        public Builder patFirstName(String patFirstName) {
            this.patFirstName = patFirstName;
            return this;
        }

        public Builder patLastName(String patLastName) {
            this.patLastName = patLastName;
            return this;
        }

        public Builder patAddress(String patAddress) {
            this.patAddress = patAddress;
            return this;
        }

        public Builder patCellNum(String patCellNum) {
            this.patCellNum = patCellNum;
            return this;
        }
        public Builder copy(Patient patient){
            this.patIdNum = patient.patIdNum;
            this.patFirstName = patient.patFirstName;
            this.patLastName = patient.patLastName;
            this.patAddress = patient.patAddress;
            this.patCellNum = patient.patCellNum;
            return this;
        }
        public Patient build() {return new Patient(this);}
    }
}



