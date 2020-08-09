package com.diplomski.registration;

import com.diplomski.model.User;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

@Entity
@Getter
@Setter
public class VerificationToken {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String confirmationToken;

    @OneToOne(targetEntity = User.class, fetch = FetchType.EAGER)
    @JoinColumn(nullable = false, name = "user_id")
    private User user;

    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;

    public VerificationToken(String token, User user) {
        this.confirmationToken = token;
        this.user = user;
    }

    public VerificationToken() {
    }

    public VerificationToken(User user) {
        this.confirmationToken =  UUID.randomUUID().toString();
        this.createdDate = new Date();
        this.user = user;
    }
}
