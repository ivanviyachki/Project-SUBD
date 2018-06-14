package healthblog.services;


import healthblog.models.Comment;

import java.util.List;

public interface CommentsService {

    List<Comment> getAllComments();

    Comment findComment(Integer id);

    void deleteComment(Comment comment);

    void saveComment(Comment comment);

    void updateComment(Comment comment);
}
