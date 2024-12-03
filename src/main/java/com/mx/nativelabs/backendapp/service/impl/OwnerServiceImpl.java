package com.mx.nativelabs.backendapp.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.SpringSecurityMessageSource;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.mx.nativelabs.backendapp.commons.constants.Constants;
import com.mx.nativelabs.backendapp.commons.constants.ErrorConstans;
import com.mx.nativelabs.backendapp.commons.exception.ServiceException;
import com.mx.nativelabs.backendapp.commons.mapper.OwnerMapper;
import com.mx.nativelabs.backendapp.commons.model.dto.OwnerDTO;
import com.mx.nativelabs.backendapp.commons.model.entity.OwnerDO;
import com.mx.nativelabs.backendapp.commons.model.response.MetadataDTO;
import com.mx.nativelabs.backendapp.commons.model.response.PaginationDTO;
import com.mx.nativelabs.backendapp.commons.model.response.ResponseListDTO;
import com.mx.nativelabs.backendapp.commons.model.response.SortDTO;
import com.mx.nativelabs.backendapp.repository.AdminRepository;
import com.mx.nativelabs.backendapp.repository.OwnerRepository;
import com.mx.nativelabs.backendapp.repository.custom.OwnerCustomRepository;
import com.mx.nativelabs.backendapp.service.OwnerService;

@Order(Ordered.HIGHEST_PRECEDENCE)
@Service
public class OwnerServiceImpl implements OwnerService {

	private static final Logger logger = Logger.getLogger(OwnerServiceImpl.class);
	
	@Autowired
	private OwnerRepository ownerRepository;
	
	@Autowired
	private AdminRepository adminRepository;
	
	@Autowired
	private OwnerCustomRepository ownerCustomRepository;
	
	@Autowired
	private PasswordEncoder bCryptPasswordEncoder;
	
	@Override
	public OwnerDTO getById(int id) throws ServiceException {
		OwnerDTO ownerDTO = null;
		try {
			logger.info("Consultar propietario con id: " + id);
			Optional<OwnerDO> ownerDO = ownerRepository.findById(id);
			
			if (!ownerDO.isPresent()) {
				logger.info("Obj db es null");
				throw new ServiceException("El propietario no existe");
			}

			ownerDTO = OwnerMapper.toGet(ownerDO.get());
			logger.info("Consulta de propietario: " + ownerDTO);

		} catch (ServiceException se) {
			logger.error("ServiceException: ", se);
			throw se;
		} catch (Exception e) {
			logger.error("Exception: ", e);
			throw new ServiceException("Error generico: ");
		}
		
		return ownerDTO;
		
	}

	@Override
	public ResponseListDTO getByFilter(Map<String, String> filterParams) throws ServiceException {
		ResponseListDTO responseListDTO =new ResponseListDTO();
		try {

			int page = (filterParams.get("page") != null && !filterParams.get("page").equals("")) ? Integer.valueOf(filterParams.get("page")) : 0; 
			int size = (filterParams.get("size") != null && !filterParams.get("size").equals("")) ? Integer.valueOf(filterParams.get("size")) : 10;

			Pageable pageable = PageRequest.of(page, size, Sort.by(Direction.ASC, "idOwner"));
			
			Specification<OwnerDO> specification = this.ownerCustomRepository.queryWhereConditionFilter(filterParams);
			Page<OwnerDO> ownerDOs = this.ownerRepository.findAll(specification, pageable);
			responseListDTO.setData(new ArrayList<OwnerDTO>());
			
			if (ownerDOs != null && !ownerDOs.getContent().isEmpty()) {
				SortDTO sortDTO = new SortDTO(Direction.ASC.name(), "ibsn");
				PaginationDTO paginationDTO = new PaginationDTO(page, size, ownerDOs.getTotalElements());
				responseListDTO.setData(OwnerMapper.toGet(ownerDOs.getContent()));
				responseListDTO.setMetadata(new MetadataDTO(paginationDTO, sortDTO));
			}

		} catch (Exception e) {
			logger.error("Error", e);
			throw new ServiceException("Ocurrio un error inesperado");
		}

		return responseListDTO;
	}

	@Override
	public OwnerDTO create(OwnerDTO ownerDTO) throws ServiceException {
		logger.info("Crear nuevo registro propietario: " + ownerDTO);
		
		try {
			OwnerDO ownerDO = OwnerMapper.toCreate(ownerDTO);
			ownerDO.setPassword(bCryptPasswordEncoder.encode(ownerDTO.getPassword()));
		
		ownerRepository.save(ownerDO);
		ownerDTO.setId(ownerDO.getIdOwner());

		} catch (Exception e) {
		logger.error("Exception: ", e);
		throw new ServiceException("Error generico: ");
	}
		return ownerDTO;
	}

	@Override
	public OwnerDTO update(int id, OwnerDTO ownerDTO) throws ServiceException {
		logger.info("Actualizar usuarios: " + id + " con: " + ownerDTO);
		try {
			Optional<OwnerDO> ownerDO = ownerRepository.findByIdOwner(id);
			if (!ownerDO.isPresent()) {
				logger.info("Obj db es null");
				throw new ServiceException("El usuario no existe");
			}
			OwnerMapper.toUpdate(ownerDTO, ownerDO.get());
			ownerRepository.save(ownerDO.get()); 
			
			} catch (ServiceException se) {
				logger.info("ServiceException:  ", se);
				throw se;				
			} catch (Exception e) {
				logger.error("Exception: ", e);
				throw new ServiceException("Error generico: ");
			}
			return ownerDTO;			
		}
	

	@Override
	public void delete(int id) throws ServiceException {
		logger.info("Eliminar propietario con id: "+ id);
		
		try {
			Optional<OwnerDO> ownerDO = ownerRepository.findByIdOwner(id);
			
			if(!ownerDO.isPresent()) {
				logger.info("Obj db es null");
				throw new ServiceException("El propietario no existe");
			}
			
			ownerRepository.delete(ownerDO.get());
		} catch (ServiceException se ) {
			logger.error("ServiceException: "+ se);
			throw se;
		} catch (Exception e) {
			logger.error("Exception: ", e);
			throw new ServiceException("Error generico: ");
		} 
		
	}
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		logger.info("email:::"+email);
		User user = null;
		Optional<OwnerDO> ownerDO = ownerRepository.findByEmail(email);
		if(!ownerDO.isPresent()){
			throw new UsernameNotFoundException(SpringSecurityMessageSource.getAccessor().getMessage(ErrorConstans.PREFIJO + ErrorConstans.CUENTA_NO_EXISTE, new Object[] {email}, "Owner no Registrado"));
		}
		List<GrantedAuthority> autorityList = new ArrayList<GrantedAuthority>();
		autorityList.add(new SimpleGrantedAuthority(Constants.USUARIO_CLIENTE));
		user = new User(ownerDO.get().getEmail(), ownerDO.get().getPassword(), true, true, true, true, autorityList);

		return user;
	}

	@Override 
	public OwnerDTO obtenerSesionOwner(OwnerDTO ownerDTO) throws ServiceException {

		logger.info("Dentro del servicio");
		OwnerDTO inicioSesionDTO = null;
		try {
			Optional<OwnerDO> ownerDO= ownerRepository.findByEmail(ownerDTO.getEmail());
			logger.info("Owner DO cargando data ::::{"+ownerDO);
			if (ownerDO != null) {
				inicioSesionDTO = OwnerMapper.mapper(ownerDO.get());
			}	
		} catch (Exception e) {
			logger.info("Ha ocurrido un error: " + e.getCause());
			logger.error("e : "+e,e);
			e.printStackTrace();
			//			throw new ServiceException("Usuario no registrado");
		}
		return inicioSesionDTO;
	}

	@Override
	public OwnerDTO consultOwnerEmail(String email) throws ServiceException {
		OwnerDTO ownerDTO = null;
		try{			
			Optional<OwnerDO> ownerDO = ownerRepository.findByEmail(email);
			logger.info("correo owner ::: "+ownerDO);
			logger.info("email:::"+email);
			if(ownerDO.isPresent()){
				logger.info("entro al if consultOwnerEmail");
				logger.info(ownerDO.get().getName() +" / " + ownerDO.get().getLastName());
				ownerDTO = OwnerMapper.mapper(ownerDO.get());
			}			
		}catch (Exception e){
			logger.error("Error al consultar al cliente por correo");
			e.printStackTrace();
		}
		return ownerDTO;
	}

}
