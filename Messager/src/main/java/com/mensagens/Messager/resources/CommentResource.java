package com.mensagens.Messager.resources;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.mensagens.Messager.dao.CommentDao;
import com.mensagens.Messager.model.Comment;

@Path("/")
@Produces(MediaType.APPLICATION_JSON) 
@Consumes(MediaType.APPLICATION_JSON)
public class CommentResource {

	private CommentDao commentDao = new CommentDao();
	
	
	@GET
	public List<Comment> getAllComments(@PathParam("messageId") int messageId){
		return commentDao.getComments(messageId);
	}
	
	@GET
	@Path("/{commentId}")
	public Comment getComment(@PathParam("commentId") int commentId){
		return commentDao.getComment(commentId);
	}
	
	

	
	@POST
	public Comment addComment(@PathParam("messageId") int messageId, Comment comment){
		comment.setMessageId(messageId);
		return commentDao.adiciona(comment);
	}
	
	@PUT
	@Path("/{commentId}")
	public Comment alterComment(@PathParam("commentId") int commentId, Comment comment){
		comment.setId(commentId);
		return commentDao.altera(comment);
	}
	
	
	@DELETE
	@Path("/{commentId}")
	public void deleteComment(@PathParam("commentId") int commentId){
		commentDao.remove(commentId);
	}
}
