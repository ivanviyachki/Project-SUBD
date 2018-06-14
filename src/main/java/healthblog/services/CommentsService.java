package healthblog.services;


import healthblog.models.Comment;

import java.sql.SQLException;
import java.util.List;

public interface CommentsService {

    List<Comment> getAllComments() throws SQLException;

    Comment findComment(Integer id) throws SQLException;

    void deleteComment(Comment comment) throws SQLException;

    void saveComment(Comment comment) throws SQLException;

    void updateComment(Comment comment) throws SQLException;
}
