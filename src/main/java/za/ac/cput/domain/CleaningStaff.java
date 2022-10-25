package za.ac.cput.domain;

import com.sun.istack.NotNull;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Objects;

/*
   CleaningStaff.java
   Entity for hospital cleaning staff
   Author: Sinazo Mehlomakhulu(216076498)
   Date: 06 August 2022
 */
@Entity
@Table(name = "tbl_Cleaning_Staff")
public class CleaningStaff implements Serializable {

    @Id
    @NotNull
    private String employeeId;

    @NotNull
    private String employeeFirstName;
    @NotNull
    private String employeeLastName;

    public CleaningStaff(Builder builder) {
        this.employeeId = builder.employeeId;
        this.employeeFirstName = builder.employeeFirstName;
        this.employeeLastName = builder.employeeLastName;

    }

    public CleaningStaff() {
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public String getEmployeeFirstName() {
        return employeeFirstName;
    }

    public String getEmployeeLastName() {
        return employeeLastName;
    }

    @Override
    public String toString() {
        return "CleaningStaff{" +
                "employeeId='" + employeeId + '\'' +
                ", employeeFirstName='" + employeeFirstName + '\'' +
                ", employeeLastName='" + employeeLastName + '\'' +
                '}';
}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CleaningStaff that = (CleaningStaff) o;
        return Objects.equals(employeeId, that.employeeId) && Objects.equals(employeeFirstName, that.employeeFirstName) && Objects.equals(employeeLastName, that.employeeLastName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(employeeId, employeeFirstName, employeeLastName);
    }

    public static class Builder {
        private String employeeId;
        private String employeeFirstName;
        private String employeeLastName;

        public Builder employeeId (String employeeId){
            this.employeeId = employeeId;
            return this;
        }
        public Builder employeeFirstName (String employeeFirstName) {
            this.employeeFirstName = employeeFirstName;
            return this;
        }
        public Builder employeeLastName (String employeeLastName){
            this.employeeLastName = employeeLastName;
            return this;
        }
        public Builder copy(CleaningStaff cleaningStaff) {
            this.employeeId = cleaningStaff.employeeId;
            this.employeeFirstName = cleaningStaff.employeeFirstName;
            this.employeeLastName = cleaningStaff.employeeLastName;
            return this;
        }

        public CleaningStaff build() {
            return new CleaningStaff(this);
        }
    }
}
