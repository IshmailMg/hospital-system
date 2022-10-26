package za.ac.cput.domain;

import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Objects;

/*
    MedicalAid.java
    Entity for the Medical Aid
    Author: Shina Kara (219333181)
    Date: 4 August 2022
*/
@Entity
@Table(name = "tbl_medical_aid")
public class MedicalAid {

    @Id
    private String medicalNum;
    @NotNull
    private String medicalName;
    @NotNull
    private String medicAddr;


    public MedicalAid(Builder builder) {
        this.medicalNum = builder.medicalNum;
        this.medicalName = builder.medicalName;
        this.medicAddr = builder.medicalAddr;
    }
    public MedicalAid() {

    }


    public String getMedicalNum() {
        return medicalNum;
    }

    public String getMedicalName() {
        return medicalName;
    }

    public String getMedicAddr() {
        return medicAddr;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MedicalAid that = (MedicalAid) o;
        return Objects.equals(medicalNum, that.medicalNum) && Objects.equals(medicalName, that.medicalName) && Objects.equals(medicAddr, that.medicAddr);
    }

    @Override
    public int hashCode() {
        return Objects.hash(medicalNum, medicalName, medicAddr);
    }

    @Override
    public String toString() {
        return "MedicalAid{" +
                "medicalNum='" + medicalNum + '\'' +
                ", medicalName='" + medicalName + '\'' +
                ", medicAddr='" + medicAddr + '\'' +
                '}';
    }

    public static class Builder {
        private String medicalNum;
        private String medicalName;
        private String medicalAddr;

        public MedicalAid.Builder medicalNum(String medicalNum) {
            this.medicalNum = medicalNum;
            return this;
        }

        public MedicalAid.Builder medicalName(String medicalName) {
            this.medicalName = medicalName;
            return this;
        }

        public MedicalAid.Builder medicalAddr(String medicalAddr) {
            this.medicalAddr = medicalAddr;
            return this;
        }



        public MedicalAid.Builder copy(MedicalAid medicalAid) {
            this.medicalNum = medicalAid.medicalNum;
            this.medicalName=medicalAid.medicalName;
            this.medicalAddr=medicalAid.medicAddr;
            return this;
        }
        public MedicalAid build() {
            return new MedicalAid(this);

        }
    }
}



