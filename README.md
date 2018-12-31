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

## Messages
```java
@Path("/messages")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON) 
public class MessageResource {

	private MessageDao messageDao = new MessageDao();
	
	@GET
	@Produces(MediaType.APPLICATION_JSON) 
	public List<Message> getMessages(@BeanParam MessageFilterBean filterBean){
		if(filterBean.getYear() > 0){
			return messageDao.getMessages(filterBean.getYear());
		}
		if(filterBean.getOffset() > 0 && filterBean.getRange() > 0){
			return messageDao.getMessages(filterBean.getOffset(), filterBean.getRange());
		}
	
		return messageDao.getMessages();
	}
	

	/*@GET
	@Produces(MediaType.TEXT_XML) 
	public List<Message> getXmlMessages(@BeanParam MessageFilterBean filterBean){
		if(filterBean.getYear() > 0){
			return messageDao.getMessages(filterBean.getYear());
		}
		if(filterBean.getOffset() > 0 && filterBean.getRange() > 0){
			return messageDao.getMessages(filterBean.getOffset(), filterBean.getRange());
		}
	
		return messageDao.getMessages();
	}*/
	
	
	@POST
	public Response addMessage(Message message, @Context UriInfo uriInfo){
		Message newMessage = messageDao.adiciona(message);
		String newId = String.valueOf(newMessage.getId());
		
		return  Response.created(uriInfo.getAbsolutePathBuilder().path(newId).build())
			     //.status(Status.CREATED)
				 .entity(newMessage)
				 .build();		
    }
	
	@PUT
	@Path("/{messageId}")
	public Message updateMessage(@PathParam("messageId") int messageId, Message message){
		message.setId(messageId);
		return messageDao.altera(message);
	}
	
	
	
	@DELETE
	@Path("/{messageId}")
	public void deleteMessage(@PathParam("messageId") int messageId){
		messageDao.remove(messageId);
	}
	
	@GET
	@Path("/{messageId}")
	public Message getMessage(@Context UriInfo uriInfo, @PathParam("messageId") int messageId){
		Message message = messageDao.getMessage(messageId);
		message.addLink(getUriForSelf(uriInfo, message), "self");
		message.addLink(getUriForProfile(uriInfo, message), "profile");
		message.addLink(getUriForComments(uriInfo, message), "comments");

		return message;
	}
	

	@Path("/{messageId}/comments")
	public CommentResource getCommentResource(){
		return new CommentResource();
	}
	
	
	private String getUriForComments(UriInfo uriInfo, Message message) {
		String uri = uriInfo.getBaseUriBuilder()  
									.path(MessageResource.class) 
									.path(MessageResource.class, "getCommentResource")
									.path(CommentResource.class)
									.resolveTemplate("messageId",  message.getId())
									.build()
									.toString();
		return uri;
	}
	
	
	private String getUriForProfile(UriInfo uriInfo, Message message) {
		String uri = uriInfo.getBaseUriBuilder()  //http://localhost:8080/messager/webapi/
									.path(ProfileResource.class) // /profile
									.path(Long.toString(message.getAuthor())) //{profileId}
									.build()
									.toString();
		return uri;
	}
	
	
	private String getUriForSelf(UriInfo uriInfo, Message message) {
		String uri = uriInfo.getBaseUriBuilder()  //http://localhost:8080/messager/webapi/
									.path(MessageResource.class) // /messages
									.path(Long.toString(message.getId())) //{messageId}
									.build()
									.toString();
		return uri;
	}
	
}
```
## Profile
```java

@Path("/profiles")
@Produces(MediaType.APPLICATION_JSON) 
@Consumes(MediaType.APPLICATION_JSON)
public class ProfileResource {

	private ProfileDao profileDao = new ProfileDao();
	
	
	@GET
	public List<Profile> getProfiles(){
		return profileDao.getProfiles();
	}
	
	@GET
	@Path("/{idProfile}")
	public Profile getProfile(@PathParam("idProfile") int idProfile ){
		return profileDao.getProfile(idProfile);
	}
	
	@POST
	public Profile addProfile(Profile profile){
		return profileDao.adiciona(profile);
	}
	
	@PUT
	@Path("/{idProfile}")
	public Profile updateProfile(@PathParam("idProfile") int idProfile, Profile profile){
		profile.setId(idProfile);
		return profileDao.altera(profile);
	}
	
	@DELETE
	@Path("/{idProfile}")
	public void deleteProfile(@PathParam("idProfile") int idProfile){
		profileDao.remove(idProfile);		
	}
}
```

