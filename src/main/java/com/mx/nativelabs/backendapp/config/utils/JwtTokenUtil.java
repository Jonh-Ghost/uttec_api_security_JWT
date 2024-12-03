package com.mx.nativelabs.backendapp.config.utils;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.apache.log4j.Logger;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.mx.nativelabs.backendapp.commons.constants.Constants;
import com.mx.nativelabs.backendapp.commons.model.dto.AdminDTO;
import com.mx.nativelabs.backendapp.commons.model.dto.OwnerDTO;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;


@Component
public class JwtTokenUtil implements Serializable {

	private static final Logger logger = Logger.getLogger(JwtTokenUtil.class);

	private static final long serialVersionUID = 1L;

	/**
	 * Obtiene el usuario desde desde el token
	 * 
	 * @param token
	 * @return
	 */
	public String getUsernameFromToken(String token) {
		return getClaimFromToken(token, Claims::getSubject);
	}

	public String getRoleAuthorityFromToken(String token) {
		return getAllClaimsFromToken(token).get(ConstantsSecurityConfig.AUTHORITIES_ROLE_KEY, String.class);
	}

	/**
	 * Obtiene la fecha de expiracion del token
	 * 
	 * @param token
	 * @return
	 */
	public Date getExpirationDateFromToken(String token) {
		return getClaimFromToken(token, Claims::getExpiration);
	}

	/**
	 * Obtiene la informacion de un token
	 * 
	 * @param token
	 * @param claimsResolver
	 * @return
	 */
	public <T> T getClaimFromToken(String token,
			Function<Claims, T> claimsResolver) {
		final Claims claims = getAllClaimsFromToken(token);
		return claimsResolver.apply(claims);
	}

	/**
	 * Recupera la informacion del token utilizando la contrasenia del signature
	 * 
	 * @param token
	 * @return
	 */
	private Claims getAllClaimsFromToken(String token) {
		return Jwts.parser()
				.setSigningKey(ConstantsSecurityConfig.PASS_SIGNATURE)
				.parseClaimsJws(token).getBody();
	}

	/**
	 * Valida si un token sigue vigente
	 * 
	 * @param token
	 * @return
	 */
	private Boolean isTokenExpired(String token) {
		final Date expiration = getExpirationDateFromToken(token);
		return expiration.before(new Date());
	}

	/**
	 * Creacion del token y se agrega el rol en la informacion del token
	 * 
	 * @param userDetails
	 * @return
	 */
	public String generateToken(UserDetails userDetails) {
		Map<String, Object> claims = new HashMap<>();

		String authorities = userDetails.getAuthorities().stream()
				.map(GrantedAuthority::getAuthority)
				.collect(Collectors.joining(","));

		claims.put(ConstantsSecurityConfig.AUTHORITIES_ROLE_KEY, authorities);

		logger.info("Roles - authorities: " + authorities);
		logger.info("Current claims: " + claims);
		return doGenerateToken(claims, userDetails.getUsername());
	}

	/**
	 * Creacion y forma del token
	 * 
	 * @param claims
	 * @param subject
	 * @return
	 */
	private String doGenerateToken(Map<String, Object> claims, String subject) {
		Date currentDate = Utils.getDayHourCurrentMexico();
		return Jwts
				.builder()
				.setClaims(claims)
				.setSubject(subject)
				.setIssuedAt(currentDate)
				.setExpiration(Utils.additionOrSubtractionDaysOfDate(currentDate, Constants.TIME_OF_LIFE_TOKEN_SECURITY_IN_DAY))
//				.setExpiration(Utils.sumMinutesToDate(Constantes.TIME_OF_LIFE_TOKEN_SECURITY_IN_MIN))
				.signWith(SignatureAlgorithm.HS512,ConstantsSecurityConfig.PASS_SIGNATURE)
				.compact();
	}

	/**
	 * Valida la informacion del token con la informacion de Spring Security
	 * @param token
	 * @param userDetails
	 * @return
	 */
	public Boolean validateToken(String token, UserDetails userDetails) {
		final String username = getUsernameFromToken(token);
		return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
	}
	
	/**
	 * Creacion del token y se agrega el rol en la informacion del token
	 * 
	 * @param userDetails
	 * @return
	 */
	public String generarToken(UserDetails userDetails, AdminDTO adminDTO) {
		Map<String, Object> claims = new HashMap<>();

		String authorities = userDetails.getAuthorities().stream()
				.map(GrantedAuthority::getAuthority)
				.collect(Collectors.joining(","));

		claims.put(ConstantsSecurityConfig.AUTHORITIES_ROLE_KEY, authorities);
		claims.put("nombre", adminDTO.getName());
		claims.put("idAdmin", adminDTO.getId());
		claims.put("correo", adminDTO.getEmail());
		
		logger.info("Roles - authorities: " + authorities);
		logger.info("Current claims: " + claims);
		return doGenerateToken(claims, userDetails.getUsername());
	}
	
	public String generarTokenOwner(UserDetails userDetails, OwnerDTO ownerDTO) {
		Map<String, Object> claims = new HashMap<>();

		String authorities = userDetails.getAuthorities().stream()
				.map(GrantedAuthority::getAuthority)
				.collect(Collectors.joining(","));

		claims.put(ConstantsSecurityConfig.AUTHORITIES_ROLE_KEY, authorities);
		claims.put("nombre", ownerDTO.getName());
		claims.put("Apellido", ownerDTO.getLastName());
		claims.put("idOwner", ownerDTO.getId());
		claims.put("correo", ownerDTO.getEmail());
		
		logger.info("Roles - authorities: " + authorities);
		logger.info("Current claims: " + claims);
		return doGenerateToken(claims, userDetails.getUsername());
	}

}
