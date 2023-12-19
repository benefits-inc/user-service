package com.benefits.userservice.db.entity.address;

import com.benefits.userservice.db.entity.address.enums.UserReceiveType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "address")
public class UserAddressEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 45, nullable = false)
    private String receiver;

    @Column(length = 200, nullable = false)
    private String address1;

    @Column(length = 200, nullable = false)
    private String address2;

    @Column(length = 45, nullable = false)
    private String phone;

    @Column(length = 20, nullable = false)
    @Enumerated(EnumType.STRING)
    private UserReceiveType receiveType;

    @Column(length = 45, nullable = false)
    private String receiveMessage;

}
