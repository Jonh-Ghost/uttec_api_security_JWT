package com.mx.nativelabs.backendapp.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.mx.nativelabs.backendapp.commons.Utils;
import com.mx.nativelabs.backendapp.commons.constants.Constants;
import com.mx.nativelabs.backendapp.commons.exception.ServiceException;
import com.mx.nativelabs.backendapp.commons.mapper.BranchOfficeMapper;
import com.mx.nativelabs.backendapp.commons.model.dto.BranchOfficeDTO;
import com.mx.nativelabs.backendapp.commons.model.entity.BranchOfficeDO;
import com.mx.nativelabs.backendapp.commons.model.entity.UserDO;
import com.mx.nativelabs.backendapp.commons.model.entity.HistoricoQrDO;
import com.mx.nativelabs.backendapp.commons.model.response.MetadataDTO;
import com.mx.nativelabs.backendapp.commons.model.response.PaginationDTO;
import com.mx.nativelabs.backendapp.commons.model.response.ResponseListDTO;
import com.mx.nativelabs.backendapp.commons.model.response.SortDTO;
import com.mx.nativelabs.backendapp.repository.BranchOfficeRepository;
import com.mx.nativelabs.backendapp.repository.HistoricoQrRepository;
import com.mx.nativelabs.backendapp.repository.custom.BranchOfficeCustomRepository;
import com.mx.nativelabs.backendapp.service.NewDocumentService;

@Service
public class BranchOfficeServiceImpl implements NewDocumentService {

	private static final Logger logger = Logger.getLogger(BranchOfficeServiceImpl.class);

	@Autowired
	private BranchOfficeRepository branchOfficeRepository;

	@Autowired
	private HistoricoQrRepository historicoQrRepository;

	@Autowired
	private BranchOfficeCustomRepository branchOfficeCustomRepository; 

	@Value("${app.storage.qr}")
	private String storagePathQr;

	@Override
	public BranchOfficeDTO getById(int id) throws ServiceException {
		BranchOfficeDTO branchOfficeDTO = null;
		try {
			logger.info("Consultar sucursal con id: " + id);
			Optional<BranchOfficeDO> branchOfficeDO = branchOfficeRepository.findByIdBranchOfficeAndActiveNot(id, Constants.ID_STATUS_BRANCHOFFICE_DELETED);

			if (!branchOfficeDO.isPresent()) {
				logger.info("Obj db es null");	
				throw new ServiceException("La sucursal no existe");
			}

			branchOfficeDTO = BranchOfficeMapper.toGet(branchOfficeDO.get());
			logger.info("Consulta de sucursal: " + branchOfficeDTO);

			HistoricoQrDO historicoQrDO = historicoQrRepository.findByBranchOfficeIdBranchOfficeAndStatus(id, true);

			List<HistoricoQrDO> listHistoricoQrDO = historicoQrRepository.findByBranchOfficeIdBranchOffice(id);

			int sizeHistoric = listHistoricoQrDO.size();

			branchOfficeDTO.setCountUpdatesStatusQr(sizeHistoric); 
			logger.info("SIZE DE LA LISTA DE HISTORICO " + sizeHistoric); 


			if (historicoQrDO != null) {
				logger.info("Optional contiene esto " + historicoQrDO.getUrlQr());
				branchOfficeDTO.setPublicPath(historicoQrDO.getUrlQr());

				Utils.generateQRCodeImage(historicoQrDO.getUrlQr() + ".png", storagePathQr + branchOfficeDTO.getName() + ".png");
				branchOfficeDTO.setUrlQr(storagePathQr + branchOfficeDTO.getName() + ".png");
			}

		} catch (ServiceException se) {
			logger.error("ServiceException: ", se);
			throw se;
		} catch (Exception e) {
			logger.error("Exception: ", e);
			throw new ServiceException("Error generico: ");
		}

		return branchOfficeDTO;

	}

	@Override
	public ResponseListDTO getByFilter(Map<String, String> filterParams) throws ServiceException {
		ResponseListDTO responseListDTO =new ResponseListDTO();
		try {

			int page = (filterParams.get("page") != null && !filterParams.get("page").equals("")) ? Integer.valueOf(filterParams.get("page")) : 0; 
			int size = (filterParams.get("size") != null && !filterParams.get("size").equals("")) ? Integer.valueOf(filterParams.get("size")) : 10;

			Pageable pageable = PageRequest.of(page, size, Sort.by(Direction.ASC, "idBranchOffice"));

			Specification<BranchOfficeDO> specification = this.branchOfficeCustomRepository.queryWhereConditionFilter(filterParams);
			Page<BranchOfficeDO> branchOfficeDOs = this.branchOfficeRepository.findAll(specification, pageable);
			responseListDTO.setData(new ArrayList<BranchOfficeDTO>());

			if (branchOfficeDOs != null && !branchOfficeDOs.getContent().isEmpty()) {
				SortDTO sortDTO = new SortDTO(Direction.ASC.name(), "idBranchOffice");
				PaginationDTO paginationDTO = new PaginationDTO(page, size, branchOfficeDOs.getTotalElements());
				responseListDTO.setData(BranchOfficeMapper.toGet(branchOfficeDOs.getContent()));
				responseListDTO.setMetadata(new MetadataDTO(paginationDTO, sortDTO));
			}


		} catch (Exception e) {
			logger.error("Error", e);
			throw new ServiceException("Ocurrio un error inesperado");
		}

		return responseListDTO;
	}

	@Override
	public BranchOfficeDTO create(BranchOfficeDTO branchOfficeDTO) throws ServiceException {
		logger.info("Crear nuevo registro sucursal: " + branchOfficeDTO);

		try {
			logger.info("Creacion : " + branchOfficeDTO + "\n");
			BranchOfficeDO branchOfficeDO = BranchOfficeMapper.toCreate(branchOfficeDTO);
			UserDO userDO = new UserDO();
			userDO.setIdBusiness(branchOfficeDTO.getBusiness().getId());
			branchOfficeDO.setBusiness(userDO);
			branchOfficeRepository.save(branchOfficeDO);
			branchOfficeDTO.setId(branchOfficeDO.getIdBranchOffice());

			HistoricoQrDO historicoQrDO = new HistoricoQrDO();
			historicoQrDO.setUrlQr(branchOfficeDTO.getPublicPath());
			historicoQrDO.setStatus(branchOfficeDTO.getStatusQr());
			historicoQrDO.setBranchOffice(branchOfficeDO);
			historicoQrRepository.save(historicoQrDO);

		} catch (Exception e) {
			logger.error("Exception: ", e);
			throw new ServiceException("Error generico: ");
		}
		return null;
	}

	@Override
	public BranchOfficeDTO update(int id, BranchOfficeDTO branchOfficeDTO) throws ServiceException {
		logger.info("Actualizar sucursales: " + id + " con: " + branchOfficeDTO);
		try {
			Optional<BranchOfficeDO> branchOfficeDO = branchOfficeRepository.findByIdBranchOfficeAndActiveNot(id, Constants.ID_STATUS_BRANCHOFFICE_DELETED);
			if (!branchOfficeDO.isPresent()) {
				logger.info("Obj db es null");
				throw new ServiceException("La sucursal no existe");
			}

			Boolean validateChangeName = branchOfficeDO.get().getName() == branchOfficeDTO.getName() ? false: true;
			logger.info(branchOfficeDO.get().getName());
			logger.info(branchOfficeDTO.getName());
			logger.info(validateChangeName);


			BranchOfficeMapper.toUpdate(branchOfficeDTO, branchOfficeDO.get());
			branchOfficeRepository.save(branchOfficeDO.get());

			if(validateChangeName == true) {
				HistoricoQrDO historicoQrDO = historicoQrRepository.findByBranchOfficeIdBranchOfficeAndStatus(branchOfficeDTO.getId(), Constants.STATUS_HISTORYC_QR_ACTIVE);

				if(historicoQrDO!= null) {
					historicoQrDO.setStatus(Constants.STATUS_HISTORYC_QR_INACTIVE);
					historicoQrRepository.save(historicoQrDO);
				}

				HistoricoQrDO historicoQrDOCreate = new HistoricoQrDO();
				BranchOfficeDO branchOfficeDOCreate = new BranchOfficeDO();

				branchOfficeDOCreate.setIdBranchOffice(branchOfficeDTO.getId());

				historicoQrDOCreate.setBranchOffice(branchOfficeDOCreate);
				historicoQrDOCreate.setStatus(Constants.STATUS_HISTORYC_QR_ACTIVE);
				historicoQrDOCreate.setUrlQr(branchOfficeDTO.getPublicPath());
				historicoQrRepository.save(historicoQrDOCreate);

				List<HistoricoQrDO> listHistoricoQrDO = historicoQrRepository.findByBranchOfficeIdBranchOffice(id);

				int sizeHistoric = listHistoricoQrDO.size();

				branchOfficeDTO.setCountUpdatesStatusQr(sizeHistoric);



			}

		} catch (ServiceException se) {
			logger.info("ServiceException:  ", se);
			throw se;				
		} catch (Exception e) {
			logger.error("Exception: ", e);
			throw new ServiceException("Error generico: ");
		}
		return branchOfficeDTO;			
	}


	@Override
	public void delete(int id) throws ServiceException {
		logger.info("Eliminar sucursal con id: "+ id);

		try {
			Optional<BranchOfficeDO> branchOfficeDO = branchOfficeRepository.findByIdBranchOfficeAndActiveNot(id, Constants.ID_STATUS_BRANCHOFFICE_DELETED);

			if(!branchOfficeDO.isPresent()) {
				logger.info("Obj db es null");
				throw new ServiceException("La sucursal no existe");
			}
			branchOfficeDO.get().setActive(Constants.ID_STATUS_BRANCHOFFICE_DELETED);
			branchOfficeRepository.save(branchOfficeDO.get());

		} catch (ServiceException se ) {
			logger.error("ServiceException: "+ se);
			throw se;
		} catch (Exception e) {
			logger.error("Exception: ", e);
			throw new ServiceException("Error generico: ");
		} 

	}
}
