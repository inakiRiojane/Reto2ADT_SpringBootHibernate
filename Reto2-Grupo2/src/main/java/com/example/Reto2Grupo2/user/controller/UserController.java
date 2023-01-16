package com.example.Reto2Grupo2.user.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.example.Reto2Grupo2.auth.exception.UserCantCreateException;
import com.example.Reto2Grupo2.user.modelo.AuthRequestCliente;
import com.example.Reto2Grupo2.user.modelo.AuthRequestEmple;
import com.example.Reto2Grupo2.user.modelo.ClienteUpdateRequest;
import com.example.Reto2Grupo2.user.modelo.User;
import com.example.Reto2Grupo2.user.modelo.UserExpands;
import com.example.Reto2Grupo2.user.modelo.UserPostRequest;
import com.example.Reto2Grupo2.user.modelo.UserServiceModel;
import com.example.Reto2Grupo2.user.service.UserService;

@RestController
@RequestMapping("api")
public class UserController {

	@Autowired
	private UserService userService;

	@GetMapping("/users")
	public ResponseEntity<List<UserServiceModel>> getTrabajadores() {
		List<UserServiceModel> response = userService.getUsers();
		return new ResponseEntity<List<UserServiceModel>>(response, HttpStatus.OK);
	}

	@GetMapping("/users/{id}")
	public ResponseEntity<UserServiceModel> getTrabajadorById(@PathVariable("id") Integer id,
			@RequestParam(required = false) List<UserExpands> expand) {

		UserServiceModel response = userService.getUserById(id, expand);
		return new ResponseEntity<UserServiceModel>(response, HttpStatus.OK);
	}

	@PostMapping("/auth/signup/empleados")
	public ResponseEntity<?> signupEmpleado(@RequestBody AuthRequestEmple request) {

		User empleado = new User(request.getUsername(), request.getPassword(), request.getEmail(), request.getZooId());

		try {
			userService.signupEmpleado(empleado);
		} catch (UserCantCreateException e) {
			return new ResponseEntity<>(HttpStatus.CONFLICT);
		}
		// TODO que devuelva los datos del usuario creado ???
		return new ResponseEntity<>(HttpStatus.CREATED);
	}

	@PostMapping("/auth/signup/clientes")
	public ResponseEntity<?> signupCliente(@RequestBody AuthRequestCliente request) {

		User cliente = new User(request.getUsername(), request.getPassword(), request.getEmail());

		try {
			userService.signupCliente(cliente);
		} catch (UserCantCreateException e) {
			return new ResponseEntity<>(HttpStatus.CONFLICT);
		}
		// TODO que devuelva los datos del usuario creado ???
		return new ResponseEntity<>(HttpStatus.CREATED);
	}

	@PutMapping("/users/{id}")
	public ResponseEntity<UserServiceModel> updateUser(@PathVariable("id") Integer id,
			@RequestBody UserPostRequest userPostRequest) {

		UserServiceModel trabajadorResponse = userService.updateById(id, userPostRequest);
		return new ResponseEntity<UserServiceModel>(trabajadorResponse, HttpStatus.OK);
	}

	@PutMapping("/users/cliente")
	public ResponseEntity<UserServiceModel> updateCliente(@RequestBody ClienteUpdateRequest clienteUpdateRequest,
			Authentication authentication) {

		User userDetails = (User) authentication.getPrincipal();

		UserServiceModel userResponse = userService.updateCliente(clienteUpdateRequest, userDetails.getId());
		return new ResponseEntity<UserServiceModel>(userResponse, HttpStatus.OK);
	}

	@DeleteMapping("/users/{id}")
	public void deleteUserById(@PathVariable("id") Integer id) {
		userService.deleteById(id);

	}

	@DeleteMapping("/users/cliente")
	public void deleteCliente(Authentication authentication) {

		User userDetails = (User) authentication.getPrincipal();
		userService.deleteCliente(userDetails.getId());

	}

	// YA LO HACE EL SIGNUP
//	@PostMapping("/users")
//	public ResponseEntity<UserServiceModel> createTrabajador(@RequestBody UserPostRequest trabajadorPostRequest) {
//
//		UserServiceModel trabajadorResponse = trabajadorService.create(trabajadorPostRequest);
//		return new ResponseEntity<UserServiceModel>(trabajadorResponse, HttpStatus.CREATED);
//	}

}
