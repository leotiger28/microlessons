package ru.leo.springdemo.entity;


import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "greeting")
public class Greeting {

    private static final String template = "Здравствуй, ученик, %s!";

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    //@SequenceGenerator(name = "GREETING_GEN", sequenceName = "GREETING_SEQ", allocationSize = 1)
    private Long id;

    @Basic
    @Column(name = "username")
    private String user;

    public Greeting() {
    }

    public Greeting(String user) {
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContent() {
        return String.format(template, user);
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Greeting[" +
                "id=" + id +
                ", content='" + getContent() + '\'' +
                '}';
    }

}
