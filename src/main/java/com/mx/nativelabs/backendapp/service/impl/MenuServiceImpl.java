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
import com.mx.nativelabs.backendapp.commons.mapper.BranchOfficeMapper;
import com.mx.nativelabs.backendapp.commons.mapper.CatMapper;
import com.mx.nativelabs.backendapp.commons.mapper.MenuMapper;
import com.mx.nativelabs.backendapp.commons.model.dto.FileDTO;
import com.mx.nativelabs.backendapp.commons.model.dto.MenuDTO;
import com.mx.nativelabs.backendapp.commons.model.entity.BranchOfficeDO;
import com.mx.nativelabs.backendapp.commons.model.entity.CatTypeMenuProductDO;
import com.mx.nativelabs.backendapp.commons.model.entity.FileDO;
import com.mx.nativelabs.backendapp.commons.model.entity.MenuDO;
import com.mx.nativelabs.backendapp.commons.model.response.MetadataDTO;
import com.mx.nativelabs.backendapp.commons.model.response.PaginationDTO;
import com.mx.nativelabs.backendapp.commons.model.response.ResponseListDTO;
import com.mx.nativelabs.backendapp.commons.model.response.SortDTO;
import com.mx.nativelabs.backendapp.repository.CatTypeMenuProductRepository;
import com.mx.nativelabs.backendapp.repository.FileRepository;
import com.mx.nativelabs.backendapp.repository.MenuRepository;
import com.mx.nativelabs.backendapp.repository.custom.MenuCustomRepository;
import com.mx.nativelabs.backendapp.service.MenuProductService;
import com.mx.nativelabs.backendapp.service.MenuService;
import com.mx.nativelabs.backendapp.service.StorageService;

@Service
public class MenuServiceImpl implements MenuService {

	private static final Logger logger = Logger.getLogger(MenuServiceImpl.class);

	@Autowired
	private MenuRepository menuRepository;

	@Autowired
	private MenuCustomRepository menuCustomRepository;

	@Autowired
	private CatTypeMenuProductRepository catTypeMenuProductRepository;

	@Autowired
	private MenuProductService menuProductService;
	
	@Autowired
	private StorageService storageService;
	
	@Autowired
	private FileRepository fileRepository;
	
	@Value("${app.storage.path}")
	private String storagePath;

	@Override
	public MenuDTO getById(int id) throws ServiceException {

		MenuDTO menuDTO = null;

		try {
			logger.info("Consulta por id: " + id);
			Optional<MenuDO> menuDO = menuRepository.findByIdMenuAndStatusNot(id, Constants.ID_STATUS_USER_DELETED);

			if (!menuDO.isPresent()) {
				logger.info("db Obj es null");
				throw new ServiceException("No existe el menu");
			}
			menuDTO = MenuMapper.toGet(menuDO.get());
			menuDTO.setIdBranchOffice(BranchOfficeMapper.toGet(menuDO.get().getIdBranchOffice()));
			logger.info("Consulta de menu: " + menuDTO);

		} catch (ServiceException se) {
			logger.info("ServiceException", se);
			throw se;
		} catch (Exception e) {
			logger.error("Exception: ", e);
			throw new ServiceException("Error generico: ");
		}

		return menuDTO;
	}

	@Override
	public ResponseListDTO getByFilter(Map<String, String> filterParams) throws ServiceException {
		ResponseListDTO responseListDTO = new ResponseListDTO();

		try {
			int page = (filterParams.get("page") != null && !filterParams.get("page").equals(""))
					? Integer.valueOf(filterParams.get("page"))
					: 0;
			int size = (filterParams.get("size") != null && !filterParams.get("size").equals(""))
					? Integer.valueOf(filterParams.get("size"))
					: 10;

			Pageable pageable = PageRequest.of(page, size, Sort.by(Direction.ASC, "branchOffice"));
			if (filterParams.entrySet().isEmpty()) {
				filterParams.put("status", "1,3,4,5");
			}

			Specification<MenuDO> specification = this.menuCustomRepository.queryWhereConditionFilter(filterParams);
			Page<MenuDO> menuDOS = this.menuRepository.findAll(specification, pageable);
			responseListDTO.setData(new ArrayList<MenuDTO>());

			if (menuDOS != null && !menuDOS.getContent().isEmpty()) {
				SortDTO sortDTO = new SortDTO(Direction.ASC.name(), "IGcode");
				PaginationDTO paginationDTO = new PaginationDTO(page, size, menuDOS.getTotalElements());
				responseListDTO.setData(MenuMapper.toGet(menuDOS.getContent()));
				responseListDTO.setMetadata(new MetadataDTO(paginationDTO, sortDTO));
			}

		} catch (Exception e) {
			logger.info("Error: ", e);
			throw new ServiceException("Ocurrio un error inesperado");
		}
		return responseListDTO;
	}

	@Override
	public MenuDTO create(MenuDTO menuDTO) throws ServiceException {

		logger.info("Crear nuevo registro menu: " + menuDTO);

		try {

			logger.info("Creacion : " + menuDTO + "\n");
			MenuDO menuDO = MenuMapper.toCreate(menuDTO);

			if (menuDTO.getTypeMenu().getId() == 1) {
				Optional<CatTypeMenuProductDO> catTypeMenuProductDO = catTypeMenuProductRepository
						.findById(Constants.ID_TYPE_MENU_PRODUCT_ALIMENTO);
				if (catTypeMenuProductDO.isPresent()) {
					menuDTO.setTypeMenu(CatMapper.toMap(catTypeMenuProductDO.get()));
					menuDO.setTypeMenu(catTypeMenuProductDO.get());
				}
			} else if (menuDTO.getTypeMenu().getId() == 2) {
				Optional<CatTypeMenuProductDO> catTypeMenuProductDO = catTypeMenuProductRepository
						.findById(Constants.ID_TYPE_MENU_PRODUCT_BEBIDA);
				if (catTypeMenuProductDO.isPresent()) {
					menuDTO.setTypeMenu(CatMapper.toMap(catTypeMenuProductDO.get()));
					menuDO.setTypeMenu(catTypeMenuProductDO.get());
				}
			} else if (menuDTO.getTypeMenu().getId() == 3) {
				Optional<CatTypeMenuProductDO> catTypeMenuProductDO = catTypeMenuProductRepository
						.findById(Constants.ID_TYPE_MENU_PRODUCT_COCTEL);
				if (catTypeMenuProductDO.isPresent()) {
					menuDTO.setTypeMenu(CatMapper.toMap(catTypeMenuProductDO.get()));
					menuDO.setTypeMenu(catTypeMenuProductDO.get());
				}
			} else if (menuDTO.getTypeMenu().getId() == 4) {
				Optional<CatTypeMenuProductDO> catTypeMenuProductDO = catTypeMenuProductRepository
						.findById(Constants.ID_TYPE_MENU_PRODUCT_POSTRE);
				if (catTypeMenuProductDO.isPresent()) {
					menuDTO.setTypeMenu(CatMapper.toMap(catTypeMenuProductDO.get()));
					menuDO.setTypeMenu(catTypeMenuProductDO.get());
				}
			}

			BranchOfficeDO branchOfficeDO = new BranchOfficeDO();
			branchOfficeDO.setIdBranchOffice(menuDTO.getIdBranchOffice().getId());
			menuDO.setIdBranchOffice(branchOfficeDO);
			
			menuRepository.save(menuDO);
			menuDTO.setId(menuDO.getIdMenu());

			/*
			 * DocumentDTO menuProductDTO = new DocumentDTO();
			 * menuProductDTO.setIdMenu(menuDTO); menuProductService.create(menuProductDTO);
			 */
								
			int count = 1;

			if (menuDTO.getFiles() != null && menuDTO.getFiles().size() <= 1) {

				for (FileDTO fileDTO : menuDTO.getFiles()) {
					fileDTO.setIdMenu(menuDTO.getId());
					fileDTO.setName(Utils.builderString(menuDTO.getId(), "_menu_", count, Utils.getFileExtensionFromMimeType(fileDTO.getMimeType())));
					
					storageService.create(fileDTO);

					FileDO fileDo = new FileDO();

					fileDo.setName(fileDTO.getName());
					fileDo.setUrl("http://localhost:8080" + storagePath + fileDTO.getName());
					
					MenuDO menuDo = new MenuDO();
					menuDo.setIdMenu(fileDTO.getIdMenu());
					
					fileDo.setMenu(menuDo);
					
					fileRepository.save(fileDo);
					
					count++;
				}
			}

		} catch (Exception e) {
			logger.error("Exception: ", e);
			throw new ServiceException("Error generico: ");
		}

		return null;
	}

	@Override
	public MenuDTO update(int id, MenuDTO menuDTO) throws ServiceException {
		logger.info("Actulizar menu: " + id + " con: " + menuDTO);
		try {
			Optional<MenuDO> menuDO = menuRepository.findByIdMenuAndStatusNot(id, Constants.ID_STATUS_USER_DELETED);
			if (!menuDO.isPresent()) {
				logger.info("Obj db es null");
				throw new ServiceException("El menu no existe");
			}

			MenuMapper.toUpdate(menuDTO, menuDO.get());
			if (!menuDO.isPresent()) {
				logger.info("Obj db es null");
				throw new ServiceException("El menu no existe");
			}

			if (menuDTO.getTypeMenu().getId() == 1) {
				Optional<CatTypeMenuProductDO> catTypeMenuProductDO = catTypeMenuProductRepository
						.findById(Constants.ID_TYPE_MENU_PRODUCT_ALIMENTO);
				if (catTypeMenuProductDO.isPresent()) {
					menuDTO.setTypeMenu(CatMapper.toMap(catTypeMenuProductDO.get()));
					menuDO.get().setTypeMenu(catTypeMenuProductDO.get());
				}
			} else if (menuDTO.getTypeMenu().getId() == 2) {
				Optional<CatTypeMenuProductDO> catTypeMenuProductDO = catTypeMenuProductRepository
						.findById(Constants.ID_TYPE_MENU_PRODUCT_BEBIDA);
				if (catTypeMenuProductDO.isPresent()) {
					menuDTO.setTypeMenu(CatMapper.toMap(catTypeMenuProductDO.get()));
					menuDO.get().setTypeMenu(catTypeMenuProductDO.get());
				}
			} else if (menuDTO.getTypeMenu().getId() == 3) {
				Optional<CatTypeMenuProductDO> catTypeMenuProductDO = catTypeMenuProductRepository
						.findById(Constants.ID_TYPE_MENU_PRODUCT_COCTEL);
				if (catTypeMenuProductDO.isPresent()) {
					menuDTO.setTypeMenu(CatMapper.toMap(catTypeMenuProductDO.get()));
					menuDO.get().setTypeMenu(catTypeMenuProductDO.get());
				}
			} else if (menuDTO.getTypeMenu().getId() == 4) {
				Optional<CatTypeMenuProductDO> catTypeMenuProductDO = catTypeMenuProductRepository
						.findById(Constants.ID_TYPE_MENU_PRODUCT_POSTRE);
				if (catTypeMenuProductDO.isPresent()) {
					menuDTO.setTypeMenu(CatMapper.toMap(catTypeMenuProductDO.get()));
					menuDO.get().setTypeMenu(catTypeMenuProductDO.get());
				}
			}

			menuRepository.save(menuDO.get());

		} catch (ServiceException se) {
			logger.info("ServiceException: ", se);
			throw se;
		} catch (Exception e) {
			logger.error("Exception: ", e);
			throw new ServiceException("Error generico: ");
		}
		return menuDTO;
	}

	@Override
	public void delete(int id) throws ServiceException {
		logger.info("Eliminar menú con id: " + id);
		try {
			Optional<MenuDO> menuDO = menuRepository.findByIdMenuAndStatusNot(id, Constants.ID_STATUS_USER_DELETED);
			if (!menuDO.isPresent()) {
				logger.info("Obj db es null");
				throw new ServiceException("El menú no existe");
			}
			menuDO.get().setStatus(Constants.ID_STATUS_USER_DELETED);
			menuRepository.save(menuDO.get());
		} catch (ServiceException se) {
			logger.error("Exception: ", se);
			throw se;
		} catch (Exception e) {
			logger.error("Exception: ", e);
			throw new ServiceException("Error generico: ");
		}

	}
}
