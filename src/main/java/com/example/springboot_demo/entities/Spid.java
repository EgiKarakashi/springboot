package com.example.springboot_demo.entities;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Optional;


@Entity
@Table(name = "Spid")
public class Spid extends Base {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @OneToOne
    @JoinColumn(name = "userId")
    private User user;
    private Status status;
    private Type type;

    public Spid() {

    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Spid(LocalDateTime createdAt, String createdBy, User user, long id, Status status, Type type) {
        super(createdAt, createdBy);
        this.id = id;
        this.status = status;
        this.type = type;
        this.user = user;
    }





    public long getUserId() {
        return id;
    }

    public User getId() {
        return user;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Status getStatus() {
        return status;
    }

    public void setId(long id) {
        this.id = id;
    }
}
