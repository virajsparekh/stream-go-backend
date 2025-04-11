package com.streamgo.backend.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.Date;

@Document(collection = "users")
@Data
public class User {
    @Id
    private String id;

    private String name;
    private String email;
    private String password;

    @Field("last_updated")
    private Date lastUpdated;

    public void setPassword(String password) {
        this.password = password;
    }
}