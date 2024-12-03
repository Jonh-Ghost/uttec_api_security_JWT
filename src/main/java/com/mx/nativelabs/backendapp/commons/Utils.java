package com.mx.nativelabs.backendapp.commons;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.mx.nativelabs.backendapp.commons.constants.Constants;

/**
 * Metodos de utilidad
 * @author Nativelabs21
 *
 */
public class Utils {

	private static final Logger logger = Logger.getLogger(Utils.class);
	
	private Utils(){};
	 
	/**
	 * Convierte un objeto de tipo Pojo en un String con formato json
	 * @param obj
	 * @return
	 */
	public static String objectToString(Object obj) {
		try {
			return new ObjectMapper().writeValueAsString(obj);
		} catch (JsonProcessingException e) {
			logger.error("Error parse objeto to string json", e);
		}
		return null;
	}
	
	/**
	 * 
	 * @param object
	 * @param valueType
	 * @return
	 */
	public static  <T> T stringJsonToObject(String object, Class<T> valueType) {
		
		try {
			return new ObjectMapper().readValue(object, valueType);
		} catch (JsonProcessingException e) {
			logger.error("Error parse json to POJO class", e);
		}

		return null;
	}
	/**
	 * 
	 * @param object
	 * @param valueType
	 * @return
	 */
	public static  <T> T mapToObject(Map<String, Object> object, Class<T> valueType) {
		
		try {
			return new ObjectMapper().readValue(objectToString(object), valueType);
		} catch (JsonProcessingException e) {
			logger.error("Error parse json to POJO class", e);
		}

		return null;
	}
	
	
	/**
	 * En base la accion retorna un id estatus del usuario
	 * @param action Accion para actualizar el estado del usuario
	 * @return Identificador del estado del usuario
	 */
	public static int getIdStatusFromAction(String action) {
		int idStatus = 0;
		switch (action) {
			case "certificate":
				idStatus = Constants.ID_STATUS_USER_CETIFICATE;
				break;
			case "active":
				idStatus = Constants.ID_STATUS_USER_ACTIVE;
				break;
			case "inactive":
				idStatus = Constants.ID_STATUS_USER_INACTIVE;
				break;
			
		}
		logger.info("Id estado del usuario para actualizar: " + idStatus);
		return idStatus;
	}
	
	/**
	 * Extrae numeros de una cadena para convertirlos en una lista de enteros
	 * @param val
	 * @return
	 */
	public static List<Integer> stringIds(String val) {
		String[] values = val.split(",");
		List<Integer> ids = new ArrayList<Integer>();
		for (String value : values) {
			ids.add(Integer.valueOf(value));
		}
		return ids;
	}

	public static List<Float> stringPrices(String val){
		String[] values = val.split(",");
		List<Float> floats = new ArrayList<>();
		for (String value : values) {
			floats.add(Float.valueOf(value));
		}
		return floats;
	}
	
	public static String builderString(Object ... values) {
		if (values == null || values.length == 0) {
			return null;
		}
		
		StringBuilder sb = new StringBuilder();
		for (Object value : values) {
			sb.append(value);
		}
		return sb.toString();
	}
	
	public static Date getDate() {
		
		SimpleDateFormat dtf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar calendar = Calendar.getInstance();

        Date dateObj = calendar.getTime();
        String formattedDate = dtf.format(dateObj);
        Date dateObjDate = null;
		try {
			dateObjDate = dtf.parse(formattedDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return dateObjDate;
	}
	
	public static String getFileExtensionFromMimeType(String mimeType) {
		
		String[] parts = mimeType.split("/");
		
		String extension = parts[parts.length-1];
		
		return "."+extension;
		
	}
	
	public static String getFileToBase(String base) {
		String[] toBase = base.split(",");
		
		String imageBase = toBase[toBase.length-1];
		
		return imageBase;
	}
	
	//Generar QR
	public static void generateQRCodeImage(String content, String filePath) throws WriterException, IOException {

		QRCodeWriter qrCodeWriter = new QRCodeWriter();
		BitMatrix bitMatrix = qrCodeWriter.encode(content, BarcodeFormat.QR_CODE, 350, 350);

		Path path = FileSystems.getDefault().getPath(filePath);
		MatrixToImageWriter.writeToPath(bitMatrix, "png", path);

	}
}
