package za.ac.cput.domain;

import com.sun.istack.NotNull;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Objects;


@Entity
@Table(name = "tbl_Supplier")
public class Supplier implements Serializable {

    @Id
    private String suppID;

    @NotNull
    private String suppAddress;

    @NotNull
    private String suppRegNum;

    public Supplier(Builder builder) {
        this.suppID = builder.suppID;
        this.suppAddress = builder.suppAddress;
        this.suppRegNum = builder.suppRegNum;
    }

    public Supplier() {
    }

    public String getSuppID() {
        return suppID;
    }

    public String getSuppAddress() {
        return suppAddress;
    }

    public String getSuppRegNum() {
        return suppRegNum;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Supplier supplier = (Supplier) o;
        return Objects.equals(suppID, supplier.suppID) && Objects.equals(suppAddress, supplier.suppAddress) && Objects.equals(suppRegNum, supplier.suppRegNum);
    }

    @Override
    public int hashCode() {
        return Objects.hash(suppID, suppAddress, suppRegNum);
    }

    @Override
    public String toString() {
        return "Supplier{" + "suppID='" + suppID + '\'' + ", suppAddress='" + suppAddress + '\'' + ", suppRegNum='" + suppRegNum + '\'' + '}';
    }

    public static class Builder {
        private String suppID;
        private String suppAddress;
        private String suppRegNum;

        public Builder setSuppID(String suppID) {
            this.suppID = suppID;
            return this;
        }

        public Builder setSuppAddress(String suppAddress) {
            this.suppAddress = suppAddress;
            return this;
        }

        public Builder setSuppRegNum(String suppRegNum) {
            this.suppRegNum = suppRegNum;
            return this;
        }


        public Builder copy(Supplier supplier) {
            this.suppID = supplier.suppID;
            this.suppAddress = supplier.suppAddress;
            this.suppRegNum = supplier.suppRegNum;
            return this;
        }

        public Supplier build() {
            return new Supplier(this);
        }
    }
}
