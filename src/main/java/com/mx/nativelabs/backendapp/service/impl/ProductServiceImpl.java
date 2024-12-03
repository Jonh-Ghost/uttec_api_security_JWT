package com.mx.nativelabs.backendapp.service.impl;

import java.util.ArrayList;
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
import com.mx.nativelabs.backendapp.commons.mapper.UserMapper;
import com.mx.nativelabs.backendapp.commons.mapper.ProductMapper;
import com.mx.nativelabs.backendapp.commons.model.dto.FileDTO;
import com.mx.nativelabs.backendapp.commons.model.dto.ProductDTO;
import com.mx.nativelabs.backendapp.commons.model.entity.UserDO;
import com.mx.nativelabs.backendapp.commons.model.entity.FileDO;
import com.mx.nativelabs.backendapp.commons.model.entity.ProductDO;
import com.mx.nativelabs.backendapp.commons.model.response.MetadataDTO;
import com.mx.nativelabs.backendapp.commons.model.response.PaginationDTO;
import com.mx.nativelabs.backendapp.commons.model.response.ResponseListDTO;
import com.mx.nativelabs.backendapp.commons.model.response.SortDTO;
import com.mx.nativelabs.backendapp.repository.FileRepository;
import com.mx.nativelabs.backendapp.repository.MenuProductRepository;
import com.mx.nativelabs.backendapp.repository.ProductRepository;
import com.mx.nativelabs.backendapp.repository.custom.ProductCustomRepository;
import com.mx.nativelabs.backendapp.service.FileService;
import com.mx.nativelabs.backendapp.service.MenuProductService;
import com.mx.nativelabs.backendapp.service.DocumentService;
import com.mx.nativelabs.backendapp.service.StorageService;

@Service
public class ProductServiceImpl implements DocumentService {

	private static final Logger logger = Logger.getLogger(ProductServiceImpl.class);
	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private ProductCustomRepository productCustomRepository;
	
	@Autowired
	private MenuProductService menuProductService;
	
	@Autowired
	private StorageService storageService;
	
	@Autowired
	private FileService fileService;
	
	@Autowired
	private FileRepository fileRepository;
	
	@Autowired
	private MenuProductRepository menuProductRepository;
	
	@Value("${app.storage.path}")
	private String storagePath;
	
	
	@Override
	public ProductDTO getById(int id) throws ServiceException {
		ProductDTO productDTO = null;
		try {
			logger.info("Consultar producto con id: " + id);
			Optional<ProductDO> productDO = productRepository.findByIdProductAndStatusNot(id, Constants.ID_STATUS_USER_DELETED);

			if (!productDO.isPresent()) {
				logger.info("DB OBJ IS NULL");
				throw new ServiceException("No existe el producto");
			}
			
			productDTO = ProductMapper.toGet(productDO.get());
			productDTO.setIdBusiness(UserMapper.toGet(productDO.get().getIdBusiness()));
			logger.info("Consulta: \n" + productDTO);
		} catch (ServiceException se) {
			logger.info("ServiceException", se);
			throw se;
		} catch (Exception e) {
			logger.error("Exception: ", e);
			throw new ServiceException("Error generico: ");
		}

		return productDTO;
	}

	@Override
	public ResponseListDTO getByFilter(Map<String, String> filterParams) throws ServiceException {

		ResponseListDTO responseListDTO = new ResponseListDTO();

		try {
			int page = (filterParams.get("page") != null && !filterParams.get("page").equals("")) ? Integer.valueOf(filterParams.get("page")) : 0;
			int size = (filterParams.get("size") != null && !filterParams.get("size").equals("")) ? Integer.valueOf(filterParams.get("size")) : 10;

			Pageable pageable = PageRequest.of(page, size, Sort.by(Direction.ASC, "idProduct"));

			if(filterParams.entrySet().isEmpty()){
				filterParams.put("status","1,3,4,5");
			}

			Specification<ProductDO> specification = this.productCustomRepository.queryWhereConditionFilter(filterParams);
			Page<ProductDO> productDOS = this.productRepository.findAll(specification, pageable);

			//fileService.getById(filterParams.get(key))
			responseListDTO.setData(new ArrayList<ProductDTO>());
			//productDOS.getContent().get("IdBussines");
			if (productDOS != null && !productDOS.getContent().isEmpty()) {
				SortDTO sortDTO = new SortDTO(Direction.ASC.name(), "idProduct");
				PaginationDTO paginationDTO = new PaginationDTO(page, size, productDOS.getTotalElements());
				responseListDTO.setData(ProductMapper.toGet(productDOS.getContent()));
				responseListDTO.setMetadata(new MetadataDTO(paginationDTO, sortDTO));
			}
		} catch (Exception e) {
			logger.info("ERROR ", e);
			throw new ServiceException("Ocurrio un error inesperado");
		}

		return responseListDTO;
	}

	@Override
	public ProductDTO create(ProductDTO productDTO) throws ServiceException {

		logger.info("Crear nuevo registro producto: " + productDTO);
		try {
			ProductDO productDO = ProductMapper.toCreate(productDTO);
			
			productDTO.setId(productDO.getIdProduct());
			
			logger.info("Tamaño: "+productDTO.getFiles().size());
			
			int count = 1;
			
			
			 if(productDTO.getFiles().size() <= 3) {
				
				for (FileDTO fileDTO : productDTO.getFiles()) {
					fileDTO.setIdProduct(productDTO.getIdBusiness().getId());
					fileDTO.setName(Utils.builderString(productDTO.getId(), "_", productDTO.getName(), "_", count, Utils.getFileExtensionFromMimeType(fileDTO.getMimeType())));
					storageService.create(fileDTO);
					
					FileDO fileDo = new FileDO();
					
					fileDo.setName(fileDTO.getName());
					fileDo.setUrl("C:\\Users\\user\\Documents\\CV_UTTEC\\"  + fileDTO.getName());
					
					ProductDO productDo = new ProductDO();
					productDO.setUserCreatedAt(fileDo.getUrl());
					
					logger.info("El objeto del producto contiene " + fileDTO.getIdProduct());
					productDo.setIdProduct(fileDTO.getIdProduct());
					
					fileDo.setProduct(productDo);
					
					fileRepository.save(fileDo);
					
					count++;
				}
				
			}
			
			
//			logger.info("El do del producto contiene " + productDO.toString());
			UserDO userDO =new UserDO();
			userDO.setIdBusiness(productDTO.getIdBusiness().getId());
			logger.info("ID:  "+productDO.getIdBusiness());
			productDO.setIdBusiness(userDO);
			
			
			
			productRepository.save(productDO);	
			
			
		} catch (Exception e) {
			logger.error("EXCEPTION: \n", e);
			throw new ServiceException("ERROR GENERICO: \n");
		}
		return productDTO;
	}

	@Override
	public ProductDTO update(int id, ProductDTO productDTO) throws ServiceException {
		logger.info("Actualizar producto: " + id + " con: " + productDTO);
		try {
			Optional<ProductDO> productDO = productRepository.findByIdProductAndStatusNot(id, Constants.ID_STATUS_USER_DELETED);
			if (!productDO.isPresent()) {
				logger.info("OBJ IS NULL");
				throw new ServiceException("El producto no existe");
			}
			ProductMapper.toUpdate(productDTO, productDO.get());
			
			productRepository.save(productDO.get());
			int count = 1;

			if (productDTO.getFiles() != null && productDTO.getFiles().size() <= 3) {

				for (FileDTO fileDTO : productDTO.getFiles()) {
					fileDTO.setIdProduct(productDTO.getId());
					fileDTO.setName(Utils.builderString(productDTO.getId(), "_", productDTO.getName(), "_", count, Utils.getFileExtensionFromMimeType(fileDTO.getMimeType())));
					storageService.update(productDTO.getId(),fileDTO);

					FileDO fileDo = new FileDO();

					fileDo.setName(fileDTO.getName());
					fileDo.setUrl( Utils.builderString("http://localhost:8080", storagePath, fileDTO.getName()));

					ProductDO productDo = new ProductDO();

					logger.info("El objeto del producto contiene " + fileDTO.getIdProduct());
					productDo.setIdProduct(fileDTO.getIdProduct());

					fileDo.setProduct(productDo);

					fileRepository.save(fileDo);

					count++;
				}

			}
			

		} catch (ServiceException se) {
			logger.info("ServiceException: ", se);
			throw se;
		} catch (Exception e) {
			logger.error("EXCEPTION: ", e);
			throw new ServiceException("ERROR GENERICO: ");
		}
		return productDTO;
	}

	@Override
	public void delete(int id) throws ServiceException {
		logger.info("Eliminar producto con id: " + id);
		try {
			Optional<ProductDO> productDO = productRepository.findByIdProductAndStatusNot(id, Constants.ID_STATUS_USER_DELETED);

			if (!productDO.isPresent()) {
				logger.info("OBJ IS NULL");
				throw new ServiceException("El producto no existe");
			}
			productDO.get().setStatus(Constants.ID_STATUS_USER_DELETED);
			productRepository.save(productDO.get());
			
		} catch (ServiceException se) {
			logger.error("ServiceException" + se);
			throw se;
		} catch (Exception e) {
			logger.error("EXCEPTION: ", e);
			throw new ServiceException("ERROR GENERICO: ");
		}
	}
}
