package com.example.springsocial.model.chat;

import com.example.springsocial.model.user.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class ChatMessageDB {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "message_id")
    private Long id;
    private String message;
    private LocalDateTime time;

    @ManyToOne
    @JoinColumn(name = "room_id")
    private ChatRoomDB chatRoom;

    @ManyToOne
    @JoinColumn(name = "id")
    private User writer;
}