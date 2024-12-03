package com.mx.nativelabs.backendapp.service.impl;

import java.util.Map;
import java.util.Optional;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mx.nativelabs.backendapp.commons.constants.Constants;
import com.mx.nativelabs.backendapp.commons.exception.ServiceException;
import com.mx.nativelabs.backendapp.commons.mapper.FileMapper;
import com.mx.nativelabs.backendapp.commons.mapper.ProductMapper;
import com.mx.nativelabs.backendapp.commons.model.dto.FileDTO;
import com.mx.nativelabs.backendapp.commons.model.entity.FileDO;
import com.mx.nativelabs.backendapp.commons.model.entity.ProductDO;
import com.mx.nativelabs.backendapp.commons.model.response.ResponseListDTO;
import com.mx.nativelabs.backendapp.repository.FileRepository;
import com.mx.nativelabs.backendapp.service.FileService;

@Service
public class FileServiceImpl implements FileService {
		
	private static final Logger logger = Logger.getLogger(FileServiceImpl.class);
	
	@Autowired
	private FileRepository fileRepository;
	

	@Override
	public FileDTO getById(int id) throws ServiceException {
		logger.info("Buscar el archivo con ID: " + id);
		FileDTO fileDTO = null;
		try {
			Optional<FileDO> fileDO = fileRepository.findById(id);
			if (!fileDO.isPresent()) {
				logger.info("OBJ IS NULL");
				throw new ServiceException("El archivo no existe");
			}
			fileDTO = FileMapper.toGet(fileDO.get());


		} catch (ServiceException se) {
			logger.info("ServiceException: ", se);
			throw se;
		} catch (Exception e) {
			logger.error("EXCEPTION: ", e);
			throw new ServiceException("ERROR GENERICO: ");
		}
		
		return fileDTO;
	}

	@Override
	public ResponseListDTO getByFilter(Map<String, String> filterParams) throws ServiceException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public FileDTO create(FileDTO fileDTO) throws ServiceException {
		
		logger.info("Crear nuevo archivo"+fileDTO);
		try {
			
			FileDO fileDO = FileMapper.toCreate(fileDTO);
			fileRepository.save(fileDO);
			
		}catch(Exception e) {
			logger.error("EXCEPTION: ",e);
			throw new ServiceException("Error Generico");
		}
		
		return fileDTO;
	}

	@Override
	public FileDTO update(int id, FileDTO fileDTO) throws ServiceException {
		logger.info("Actualizar archivo: " + id + " con: " + fileDTO);

		try {
			Optional<FileDO> fileDO = fileRepository.findById(id);
			if (!fileDO.isPresent()) {
				logger.info("OBJ IS NULL");
				throw new ServiceException("El archivo no existe");
			}
			FileMapper.toUpdate(fileDTO, fileDO.get());

			fileRepository.save(fileDO.get());

		} catch (ServiceException se) {
			logger.info("ServiceException: ", se);
			throw se;
		} catch (Exception e) {
			logger.error("EXCEPTION: ", e);
			throw new ServiceException("ERROR GENERICO: ");
		}
		
		return fileDTO;
	}

	@Override
	public void delete(int id) throws ServiceException {
		
		logger.info("Eliminar archivo con ID:" +id);
		try {
			
			Optional<FileDO> fileDO = fileRepository.findById(id);
			if(!fileDO.isPresent()) {
				logger.info("Obj db es null");
				throw new ServiceException("El archivo no existe");
			}
			fileRepository.delete(fileDO.get());
			
		} catch (ServiceException se) {
			logger.error("Exception: ", se);
			throw se;
		} catch (Exception e) {
			logger.error("Exception: ", e);
			throw new ServiceException("Error generico: ");
		}
		
	}

}
