package com.savvato.tribeapp.entities;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.Table;
import java.util.Calendar;

@Entity
@IdClass(ConnectionId.class)
@Table(name = "connections")
public class Connection {
    @Id
    private Long requestingUserId;
    @Id
    private Long toBeConnectedWithUserId;
    private java.sql.Timestamp created;

    public Long getRequestingUserId() {
        return requestingUserId;
    }

    public void setRequestingUserId(Long requestingUserId) {
        this.requestingUserId = requestingUserId;
    }

    public Long getToBeConnectedWithUserId() {
        return toBeConnectedWithUserId;
    }

    public void setToBeConnectedWithUserId(Long toBeConnectedWithUserId) {
        this.toBeConnectedWithUserId = toBeConnectedWithUserId;
    }

    public java.sql.Timestamp getCreated() {
        return created;
    }

    public void setCreated() {
        this.created = java.sql.Timestamp.from(Calendar.getInstance().toInstant());
    }

    public Connection(Long requestingUserId, Long toBeConnectedWithUserId) {
        this.requestingUserId = requestingUserId;
        this.toBeConnectedWithUserId = toBeConnectedWithUserId;

        setCreated();
    }

    public Connection() {

        setCreated();

    }
}
