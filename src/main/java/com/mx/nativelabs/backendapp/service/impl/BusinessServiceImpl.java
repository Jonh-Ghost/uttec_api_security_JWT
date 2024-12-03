package com.mx.nativelabs.backendapp.service.impl;

import java.util.ArrayList;
import java.util.Map;
import java.util.Optional;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.mx.nativelabs.backendapp.commons.constants.Constants;
import com.mx.nativelabs.backendapp.commons.exception.ServiceException;
import com.mx.nativelabs.backendapp.commons.mapper.UserMapper;
import com.mx.nativelabs.backendapp.commons.mapper.CatMapper;
import com.mx.nativelabs.backendapp.commons.mapper.OwnerMapper;
import com.mx.nativelabs.backendapp.commons.model.dto.BusinessDTO;
import com.mx.nativelabs.backendapp.commons.model.entity.UserDO;
import com.mx.nativelabs.backendapp.commons.model.entity.HistoricoQrDO;
import com.mx.nativelabs.backendapp.commons.model.entity.OwnerDO;
import com.mx.nativelabs.backendapp.commons.model.entity.StatusDO;
import com.mx.nativelabs.backendapp.commons.model.response.MetadataDTO;
import com.mx.nativelabs.backendapp.commons.model.response.PaginationDTO;
import com.mx.nativelabs.backendapp.commons.model.response.ResponseListDTO;
import com.mx.nativelabs.backendapp.commons.model.response.SortDTO;
import com.mx.nativelabs.backendapp.repository.BusinessRepository;
import com.mx.nativelabs.backendapp.repository.CatStatusRepository;
import com.mx.nativelabs.backendapp.repository.HistoricoQrRepository;
import com.mx.nativelabs.backendapp.repository.custom.BusinessCustomRepository;
import com.mx.nativelabs.backendapp.service.BusinessService;

@Service
public class BusinessServiceImpl implements BusinessService {

	private static final Logger logger = Logger.getLogger(BusinessServiceImpl.class);

	@Autowired
	private BusinessRepository businessRepository;

	@Autowired
	private BusinessCustomRepository businessCustomRepository;
	
	@Autowired 
	private CatStatusRepository catStatusRepository;
	
	@Autowired
	private HistoricoQrRepository historicoQrRepository;
	
	@Override
	public BusinessDTO getById(int id) throws ServiceException {
		BusinessDTO businessDTO = null;
		try {
			logger.info("Consultar negocio con id: " + id);
			Optional<UserDO> userDO = businessRepository.findByIdBusiness(id);

			if (!userDO.isPresent()) {
				logger.info("Obj db es null");
				throw new ServiceException("El propietario no existe");
			}

			businessDTO = UserMapper.toGet(userDO.get());
			businessDTO.setIdOwner(OwnerMapper.toGet(userDO.get().getIdOwner()));
			//businessDTO.setTypeBusiness(UserMapper.toGet(businessDO.get().getType());
			logger.info("Consulta de negocio: " + businessDTO);
			

		} catch (ServiceException se) {
			logger.error("ServiceException: ", se);
			throw se;
		} catch (Exception e) {
			logger.error("Exception: ", e);
			throw new ServiceException("Error generico: ");
		}

		return businessDTO;

	}

	@Override
	public ResponseListDTO getByFilter(Map<String, String> filterParams) throws ServiceException {
		ResponseListDTO responseListDTO = new ResponseListDTO();
		try {

			int page = (filterParams.get("page") != null && !filterParams.get("page").equals("")) ? Integer.valueOf(filterParams.get("page")) : 0;
			int size = (filterParams.get("size") != null && !filterParams.get("size").equals("")) ? Integer.valueOf(filterParams.get("size")) : 10;

			Pageable pageable = PageRequest.of(page, size, Sort.by(Direction.ASC, "idBusiness"));

			Specification<UserDO> specification = this.businessCustomRepository.queryWhereConditionFilter(filterParams);
			Page<UserDO> userDOs = this.businessRepository.findAll(specification, pageable);
			responseListDTO.setData(new ArrayList<BusinessDTO>());

			if (userDOs != null && !userDOs.getContent().isEmpty()) {
				SortDTO sortDTO = new SortDTO(Direction.ASC.name(), "name");
				PaginationDTO paginationDTO = new PaginationDTO(page, size, userDOs.getTotalElements());
				responseListDTO.setData(UserMapper.toGet(userDOs.getContent()));
				responseListDTO.setMetadata(new MetadataDTO(paginationDTO, sortDTO));
			}

		} catch (Exception e) {
			logger.error("Error", e);
			throw new ServiceException("Ocurrio un error inesperado");
		}

		return responseListDTO;
	}

	@Override
	public BusinessDTO create(BusinessDTO businessDTO) throws ServiceException {
		logger.info("Crear nuevo registro propietario: " + businessDTO);

		try {
			logger.info("Creacion : " + businessDTO + "\n");
			UserDO userDO = UserMapper.toCreate(businessDTO);
			
			if(businessDTO.getTypeBusiness().getId() == 1) {
				Optional<StatusDO> statusDO = catStatusRepository.findById(Constants.ID_TYPE_BUSINESS_CAFETERIA);
				if (statusDO.isPresent()) {
					businessDTO.setTypeBusiness(CatMapper.toMap(statusDO.get()));
					userDO.setType(statusDO.get());
				}
			}else
			if(businessDTO.getTypeBusiness().getId() == 2) {
				Optional<StatusDO> statusDO = catStatusRepository.findById(Constants.ID_TYPE_BUSINESS_BAR);
				if (statusDO.isPresent()) {
					businessDTO.setTypeBusiness(CatMapper.toMap(statusDO.get()));
					userDO.setType(statusDO.get());
				}
			}else
			if (businessDTO.getTypeBusiness().getId() == 3) {
				Optional<StatusDO> statusDO = catStatusRepository.findById(Constants.ID_TYPE_BUSINESS_FONDA);
				if (statusDO.isPresent()) {
					businessDTO.setTypeBusiness(CatMapper.toMap(statusDO.get()));
					userDO.setType(statusDO.get());
				}
			}
			
			OwnerDO ownerDO = new OwnerDO();
			StatusDO statusDO = new StatusDO();
			
			ownerDO.setIdOwner(businessDTO.getIdOwner().getId());
			userDO.setIdOwner(ownerDO);
			businessRepository.save(userDO);
			businessDTO.setId(userDO.getIdBusiness());

		} catch (Exception e) {
			logger.error("Exception: ", e);
			throw new ServiceException("Error generico: ");
		}
		return null;
	}

	@Override
	public BusinessDTO update(int id, BusinessDTO businessDTO) throws ServiceException {
		logger.info("Actualizar usuarios: " + id + " con: " + businessDTO);
		try {
			Optional<UserDO> userDO = businessRepository.findByIdBusiness(id);
			if (!userDO.isPresent()) {
				logger.info("Obj db es null");
				throw new ServiceException("El usuario no existe");
			}
			UserMapper.toUpdate(businessDTO, userDO.get());

			businessRepository.save(userDO.get());

		} catch (ServiceException se) {
			logger.info("ServiceException:  ", se);
			throw se;
		} catch (Exception e) {
			logger.error("Exception: ", e);
			throw new ServiceException("Error generico: ");
		}
		return businessDTO;
	}

	@Override
	public void delete(int id) throws ServiceException {
		logger.info("Eliminar propietario con id: " + id);

		try {
			Optional<UserDO> userDO = businessRepository.findByIdBusiness(id);

			if (!userDO.isPresent()) {
				logger.info("Obj db es null");
				throw new ServiceException("El propietario no existe");
			}

			businessRepository.delete(userDO.get());
		} catch (ServiceException se) {
			logger.error("ServiceException: " + se);
			throw se;
		} catch (Exception e) {
			logger.error("Exception: ", e);
			throw new ServiceException("Error generico: ");
		}

	}
	
	@Override
	public BusinessDTO getByNameBusiness(String nameBusiness, String nameBranchOffice) throws ServiceException {
		BusinessDTO businessDTO = null;
		try {
			logger.info("Consultar negocio por nombre " + nameBusiness);
						
			String urlQr = nameBusiness + "/" + nameBranchOffice;
			
			HistoricoQrDO historicoQrDO = historicoQrRepository.findByUrlQr(urlQr);
			
			if(historicoQrDO != null) {
				
				Page<HistoricoQrDO> historicoQrDOs = historicoQrRepository.findTop5ByBranchOfficeIdBranchOffice(historicoQrDO.getBranchOffice().getIdBranchOffice(), PageRequest.of(0,5, Sort.by(Sort.Direction.DESC, "idHistoricQr")));
				
				
				businessDTO = new BusinessDTO();
				
				boolean urlValid = false;
				boolean pathExist = false;
				String redirectTo = "";
				if (historicoQrDOs != null) {
					historicoQrDOs.getSize();
					for (HistoricoQrDO item : historicoQrDOs) {
						if(urlQr == item.getUrlQr()) {
							pathExist = true; 
						}
						
						if(historicoQrDO.getStatus()) {
							pathExist = true;
							redirectTo = historicoQrDO.getUrlQr();
						}
					}	
					businessDTO.setUrlQr(redirectTo);
					logger.error("Url " + redirectTo);
					logger.error("Size de historicos " + historicoQrDOs.getSize());
				}
				
			}
			
			
		} catch (Exception e) {
			logger.error("Exception: ", e);
			throw new ServiceException("Error generico: ");
		}

		return businessDTO;
		
	}

}
