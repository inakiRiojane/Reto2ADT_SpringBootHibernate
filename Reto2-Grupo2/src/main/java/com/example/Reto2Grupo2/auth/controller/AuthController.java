package com.example.Reto2Grupo2.auth.controller;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.file.Files;
import java.util.Base64;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.Reto2Grupo2.auth.model.AuthResponse;
import com.example.Reto2Grupo2.auth.model.LoginRequest;
import com.example.Reto2Grupo2.auth.security.JwtTokenUtil;
import com.example.Reto2Grupo2.cifrados.RSA.CifradoRSA;
import com.example.Reto2Grupo2.user.modelo.AuthRequestEmple;
import com.example.Reto2Grupo2.user.modelo.User;

@RestController
@RequestMapping("api")
public class AuthController {

	@Autowired
	private AuthenticationManager authManager;

	@Autowired
	private JwtTokenUtil jwtUtil;
	@Autowired
	private CifradoRSA cifradoRSA;
	
	@PostMapping("/auth/login")
	public ResponseEntity<?> login(@RequestBody AuthRequestEmple request) {

		try {
			Authentication authentication = authManager.authenticate(
					new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));

			User trabajadorUser = (User) authentication.getPrincipal();

			String accessToken = jwtUtil.generateAccessToken(trabajadorUser);

			AuthResponse response = new AuthResponse(trabajadorUser.getId(), trabajadorUser.getUsername(), accessToken);

			return ResponseEntity.ok().body(response);

		} catch (BadCredentialsException ex) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
		}
	}

	
	/* 
	 * 				UNICAMENTE PARA ANDROID Y PSP	
	 * 
	 */

	@PostMapping("/auth/login/android")
	public ResponseEntity<?> loginAndroid(@RequestBody LoginRequest loginRequest) {
		
		
		String pass = null;

		byte[] decodedString;

		try {

			decodedString = Base64.getDecoder().decode(loginRequest.getPassword().getBytes("UTF-8"));

			byte[] passDescifrada = cifradoRSA.descifrarTexto(decodedString);

			pass = new String(passDescifrada);

			Authentication authentication = authManager
					.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), pass));

			User trabajadorUser = (User) authentication.getPrincipal();

			String accessToken = jwtUtil.generateAccessToken(trabajadorUser);

			AuthResponse response = new AuthResponse(trabajadorUser.getId(), trabajadorUser.getUsername(), accessToken);

			return ResponseEntity.ok().body(response);

		} catch (BadCredentialsException ex) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
		} catch (UnsupportedEncodingException e) {
			return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).build();
		}
	}

	@GetMapping("/getPublicKey")
	public String getPublicKey() {

		String publicKeyPath = CifradoRSA.PUBLIC_KEY_FILE_PATH;
		byte[] clavePublica = null;

		File ficheroPublica = new File(publicKeyPath);

		try {
			clavePublica = Files.readAllBytes(ficheroPublica.toPath());

		} catch (IOException e) {
			e.printStackTrace();
		}

		String encodedBASE64 = Base64.getEncoder().encodeToString(clavePublica);

		return encodedBASE64;
	}

}