package com.mensagens.Messager.resources;


import java.util.List;

import javax.ws.rs.BeanParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import com.mensagens.Messager.beans.MessageFilterBean;
import com.mensagens.Messager.dao.MessageDao;
import com.mensagens.Messager.model.Message;

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
