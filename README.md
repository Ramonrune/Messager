# Messager
Messager app
Messager app that support functions like social networks (profile management, messages, comments, and others).


## Package Structure
|------Messager<br>
|------|-----beans<br>
|------|-----dao<br>
|------|-----exception<br>
|------|-----factory<br>
|------|-----model<br>
|------|-----resources<br>


## Comments
```java

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
```
