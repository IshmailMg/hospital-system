package za.ac.cput.domain;

import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Objects;

/*
    Medicine.java
    Entity: Medicine
    Author: Ishmail T Mgwena (215088417)
    Date: 05 August 2022
*/
@Entity
@Table(name = "tbl_medicine")
public class Medicine implements Serializable {

    @Id
    private String medicineId;
    @NotNull
    private String medicineAmount;
    private String medicineType;

    public Medicine(Builder builder) {
        this.medicineId = builder.medicineId;
        this.medicineAmount = builder.medicineAmount;
        this.medicineType = builder.medicineType;
    }
    public Medicine(){}

    public String getMedicineId(){return medicineId;}

    @Override
    public String toString() {
        return "Medicine{" +
                "medicineId='" + medicineId + '\'' +
                ", medicineAmount='" + medicineAmount + '\'' +
                ", medicineType='" + medicineType + '\'' +
                '}';
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Medicine medicine = (Medicine) o;
        return medicineId.equals(medicine.medicineId) && medicineAmount.equals(medicine.medicineAmount)
                && medicineType.equals(medicine.medicineType);
    }
    public int hashCode(){
        return Objects.hash(medicineId, medicineAmount,medicineType);
    }
    public static class Builder {
        private String medicineId,medicineAmount,medicineType;

    public Builder medicineId(String medicineId) {
        this.medicineId = medicineId;
        return this;
    }
    public Builder medicineAmount(String medicineAmount){
        this.medicineAmount = medicineAmount;
        return this;
    }
    public Builder medicineType(String medicineType) {
        this.medicineType = medicineType;
        return this;
    }
    public Builder copy(Medicine medicine){
        this.medicineId = medicine.medicineId;
        this.medicineAmount = medicine.medicineAmount;
        this.medicineType = medicine.medicineType;
        return this;
    }
    public Medicine build() {return new Medicine(this);}
      }
    }


