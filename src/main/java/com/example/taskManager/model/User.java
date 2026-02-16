package com.example.taskManager.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "users") //Different than entity's name ya sha3bola

@Getter
@Setter

@NoArgsConstructor
@AllArgsConstructor
@Builder
//NO @Data 3ashan de entity, fel dtos ba2a w 3aleek kheer
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, length = 100)
    private String name;
    @Column(nullable = false, length = 100, unique = true)
    private String email;
    @CreationTimestamp
    @Column(name = "created_at", updatable = false ) //Nullable at my DB
    private LocalDateTime createdAt;

}
