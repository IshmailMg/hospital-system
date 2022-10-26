package za.ac.cput.domain;

import com.sun.istack.NotNull;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "tbl_Appointment")
public class Appointment implements Serializable {
    @Id
    @NotNull
    private String appointmentId;

    @NotNull
    private String appointmentDate;

    @NotNull
    private String appointmentDuration;

    @NotNull
    private String appointmentTime;

    public Appointment(Builder builder) {
        this.appointmentId = builder.appointmentId;
        this.appointmentDate = builder.appointmentDate;
        this.appointmentDuration = builder.appointmentDuration;
        this.appointmentTime = builder.appointmentTime;
    }
    public Appointment() {
    }

    public String getAppointmentId() {
        return appointmentId;
    }

    public String getAppointmentDate() {
        return appointmentDate;
    }

    public String getAppointmentDuration() {
        return appointmentDuration;
    }

    public String getAppointmentTime() {
        return appointmentTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Appointment that = (Appointment) o;
        return Objects.equals(appointmentId, that.appointmentId) && Objects.equals(appointmentDate, that.appointmentDate) && Objects.equals(appointmentDuration, that.appointmentDuration) && Objects.equals(appointmentTime, that.appointmentTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(appointmentId, appointmentDate, appointmentDuration, appointmentTime);
    }

    @Override
    public String toString() {
        return "Appointment{" +
                "appointmentId='" + appointmentId + '\'' +
                ", appointmentDate='" + appointmentDate + '\'' +
                ", appointmentDuration='" + appointmentDuration + '\'' +
                ", appointmentTime='" + appointmentTime + '\'' +
                '}';
    }

    public static class Builder {
        private String appointmentId;
        private String appointmentDate;
        private String appointmentDuration;
        private String appointmentTime;

        public Builder appointmentId(String appointmentId) {
            this.appointmentId = appointmentId;
            return this;
        }

        public Builder appointmentDate(String appointmentDate) {
            this.appointmentDate = appointmentDate;
            return this;
        }

        public Builder appointmentDuration(String appointmentDuration) {
            this.appointmentDuration = appointmentDuration;
            return this;
        }

        public Builder appointmentTime(String appointmentTime) {
            this.appointmentTime = appointmentTime;
            return this;
        }

        public Builder copy(Appointment appointment) {
            this.appointmentId = appointment.appointmentId;
            this.appointmentDate = appointment.appointmentDate;
            this.appointmentDuration = appointment.appointmentDuration;
            this.appointmentTime = appointment.appointmentTime;
            return this;
        }

        public Appointment build() {
            return new Appointment(this);
        }
    }
}
