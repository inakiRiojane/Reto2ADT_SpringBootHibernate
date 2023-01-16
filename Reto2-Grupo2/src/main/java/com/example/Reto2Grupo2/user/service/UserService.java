package com.example.Reto2Grupo2.user.service;

import java.util.List;

import org.springframework.http.ResponseEntity;
import com.example.Reto2Grupo2.auth.exception.UserCantCreateException;
import com.example.Reto2Grupo2.user.modelo.ClienteUpdateRequest;
import com.example.Reto2Grupo2.user.modelo.User;
import com.example.Reto2Grupo2.user.modelo.UserExpands;
import com.example.Reto2Grupo2.user.modelo.UserPostRequest;
import com.example.Reto2Grupo2.user.modelo.UserServiceModel;

//TODO invertir nombres de las clases service
public interface UserService {

	User signupEmpleado(User empleado) throws UserCantCreateException;
	
	User signupCliente(User cliente) throws UserCantCreateException;
	
	List<UserServiceModel> getUsers();

	UserServiceModel getUserById(Integer id, List<UserExpands> expand );

	UserServiceModel updateById(Integer id, UserPostRequest userPostRequest);

	UserServiceModel updateCliente(ClienteUpdateRequest clienteUpdateRequest, Integer userId);
	
	ResponseEntity<UserServiceModel>  deleteById(Integer id);

	ResponseEntity<UserServiceModel> deleteCliente(Integer id);
	
//	UserServiceModel create( UserPostRequest trabajadorPostRequest);//lo hacen los signups
	
}
