package za.ac.cput.domain;

import com.sun.istack.NotNull;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "tbl_invoice")
public class Invoice implements Serializable {

    @Id
    private String invoiceNum;

    @NotNull
    private double invoiceAmount;

    @NotNull
    private String invoiceType;

    @NotNull
    private String invoiceDate;

    public Invoice(Builder builder) {
        this.invoiceNum = builder.invoiceNum;
        this.invoiceAmount = builder.invoiceAmount;
        this.invoiceType = builder.invoiceType;
        this.invoiceDate = builder.invoiceDate;
    }

    public Invoice() {
    }

    public String getInvoiceNum() {
        return invoiceNum;
    }

    public double getInvoiceAmount() {
        return invoiceAmount;
    }

    public String getInvoiceType() {
        return invoiceType;
    }

    public String getInvoiceDate() {
        return invoiceDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Invoice invoice = (Invoice) o;
        return Double.compare(invoice.invoiceAmount, invoiceAmount) == 0 && Objects.equals(invoiceNum, invoice.invoiceNum) && Objects.equals(invoiceType, invoice.invoiceType) && Objects.equals(invoiceDate, invoice.invoiceDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(invoiceNum, invoiceAmount, invoiceType, invoiceDate);
    }

    @Override
    public String toString() {
        return "Invoice{" + "invoiceNum='" + invoiceNum + '\'' + ", invoiceAmount=" + invoiceAmount + ", invoiceType='" + invoiceType + '\'' + ", invoiceDate='" + invoiceDate + '\'' + '}';
    }

    public static class Builder {
        private String invoiceNum;
        private Double invoiceAmount;
        private String invoiceType;
        private String invoiceDate;

        public Builder invoiceNum(String invoiceNum) {
            this.invoiceNum = invoiceNum;
            return this;
        }

        public Builder invoiceAmount(double invoiceAmount) {
            this.invoiceAmount = invoiceAmount;
            return this;
        }

        public Builder invoiceType(String invoiceType) {
            this.invoiceType = invoiceType;
            return this;
        }

        public Builder invoiceDate(String invoiceDate) {
            this.invoiceDate = invoiceDate;
            return this;
        }

        public Builder copy(Invoice invoice) {
            this.invoiceNum = invoice.invoiceNum;
            this.invoiceAmount = invoice.invoiceAmount;
            this.invoiceType = invoice.invoiceType;
            this.invoiceDate = invoice.invoiceDate;
            return this;
        }

        public Invoice build() {
            return new Invoice(this);
        }
    }
}
