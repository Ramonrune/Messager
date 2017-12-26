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

import com.mensagens.Messager.dao.ProfileDao;
import com.mensagens.Messager.model.Profile;

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
