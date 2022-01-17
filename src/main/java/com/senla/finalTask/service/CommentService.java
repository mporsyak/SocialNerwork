package com.senla.finalTask.service;

import com.senla.finalTask.Util.WsSender;
import com.senla.finalTask.dto.EventType;
import com.senla.finalTask.dto.ObjectType;
import com.senla.finalTask.model.Comment;
import com.senla.finalTask.model.User;
import com.senla.finalTask.model.Views;
import com.senla.finalTask.repository.CommentRepository;
import org.springframework.stereotype.Service;

import java.util.function.BiConsumer;

@Service
public class CommentService {
    private final CommentRepository commentRepository;
    private final BiConsumer<EventType, Comment> wsSender;


    public CommentService(CommentRepository commentRepository, WsSender wsSender) {
        this.commentRepository = commentRepository;
        this.wsSender = wsSender.getSender(ObjectType.COMMENT, Views.FullComment.class);
    }

    public Comment create(Comment comment, User user){
        comment.setAuthor(user);
        Comment commentFromDb = commentRepository.save(comment);
        wsSender.accept(EventType.CREATE, commentFromDb);

        return comment;
    }
}
