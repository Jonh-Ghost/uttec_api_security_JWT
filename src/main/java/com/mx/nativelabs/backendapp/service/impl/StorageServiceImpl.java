package com.mx.nativelabs.backendapp.service.impl;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;
import java.util.Optional;

import javax.xml.bind.DatatypeConverter;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.mx.nativelabs.backendapp.commons.Utils;
import com.mx.nativelabs.backendapp.commons.exception.ServiceException;
import com.mx.nativelabs.backendapp.commons.model.dto.FileDTO;
import com.mx.nativelabs.backendapp.commons.model.entity.FileDO;
import com.mx.nativelabs.backendapp.commons.model.response.ResponseListDTO;
import com.mx.nativelabs.backendapp.repository.FileRepository;
import com.mx.nativelabs.backendapp.service.StorageService;

@Service
public class StorageServiceImpl implements StorageService{
	
	private static final Logger logger = Logger.getLogger(StorageServiceImpl.class);
	
	@Value("${app.storage.path}")
	private String storagePath;
	
	@Autowired
	private FileServiceImpl fileService;
	
	@Autowired
	private FileRepository fileRepository;

	@Override
	public FileDTO getById(int id) throws ServiceException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseListDTO getByFilter(Map<String, String> filterParams) throws ServiceException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public FileDTO create(FileDTO fileDTO) throws ServiceException {
		
				
		System.out.println(storagePath);
		
		File file = new File(Utils.builderString(storagePath, fileDTO.getName()));
		//file.createNewFile();
		file.setExecutable(true, false);
		file.setReadable(true, false);
		file.setWritable(true, false);
		try(OutputStream outputStream = new BufferedOutputStream(new FileOutputStream(file))) {
			byte[] data = DatatypeConverter.parseBase64Binary(Utils.getFileToBase(fileDTO.getBase()));
			outputStream.write(data);
		} catch (FileNotFoundException e) {
			System.err.println(e);
		} catch (IOException e) {
			System.err.println(e);
		}
		
		
		return fileDTO;
	}

	@Override
	public FileDTO update(int id, FileDTO fileDTO) throws ServiceException {

		Optional<FileDO> fileDO = fileRepository.findByProduct(id);
		Path path = Paths.get(Utils.builderString(storagePath, fileDO.get().getName()));

		try {
			boolean result = Files.deleteIfExists(path);
			fileDTO.setName(fileDO.get().getName());
			fileDTO.setUrl(Utils.builderString("http://localhost:8080/", storagePath, fileDTO.getName()));
			int idFile = fileDO.get().getIdFile();
			if (result) {
				fileService.update(idFile, fileDTO);
			}
			
		} catch(IOException e) {
			
		}
		
		return fileDTO;
	}

	@Override
	public void delete(int id) throws ServiceException {
		
		Optional<FileDO> fileDO = fileRepository.findById(id);
		
		Path path = Paths.get(Utils.builderString(storagePath, fileDO.get().getName()));
		try {
			boolean result = Files.deleteIfExists(path);
			
			if (result) {
				logger.info("Se eliminó el Archivo");
				fileService.delete(id);
			}
			
		} catch (IOException e) {
			logger.error("No se pudo eliminar el archivo por que no existe en la ruta especificada");
			throw new ServiceException("Error: ", e);
		}
				 
	}

}
