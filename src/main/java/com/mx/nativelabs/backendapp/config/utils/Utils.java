package com.mx.nativelabs.backendapp.config.utils;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Base64;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.Random;
import java.util.TimeZone;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import javax.imageio.IIOImage;
import javax.imageio.ImageIO;
import javax.imageio.ImageWriteParam;
import javax.imageio.ImageWriter;
import javax.imageio.stream.ImageOutputStream;
import javax.xml.bind.DatatypeConverter;

import org.apache.log4j.Logger;

import com.mx.nativelabs.backendapp.commons.constants.Constants;

/**
 * Utils.class
 * @author Jontahan Benjamin Castilla Ñonthge
 * @date 04/11/2024
 * @company UTTEC 2020
 */
public class Utils {

	public static final Logger logger = Logger.getLogger(Utils.class);

	/**
	 * Consulta de hora actual en M&eacute;xico
	 * @return
	 */
	public static Date getDayHourCurrentMexico() {

		Date date = new Date();
		String TOME_ZONE_MX = "America/Mexico_City";

		TimeZone fromTimezone = TimeZone.getTimeZone(TimeZone.getDefault().getID());
		TimeZone toTimezone = TimeZone.getTimeZone(TOME_ZONE_MX);

		long fromOffset = fromTimezone.getOffset(date.getTime());
		long toOffset = toTimezone.getOffset(date.getTime());

		long convertedTime = date.getTime() - (fromOffset - toOffset);
		Date d2 = new Date(convertedTime);

		logger.info("Fecha sin zona horaria definida: " + date);
		logger.info("Fecha con zona horaria 'America/Mexico_City': " + d2);

		return d2;
	}

	/**
	 * Generar contraseña
	 * @return
	 */
	public static String generarContrasena(){
		String contrasena = generarLetraMayuscula()+generarNumero();
		return contrasena;
	}

	/**
	 * Generar palabra
	 * @return
	 */
	public static String generarPalabra(){

		String palabra = ""; 

		int caracteres = (int)(Math.random()*10)+2; 
		for (int i=0; i<caracteres; i++){ 

			int codigoAscii = (int)Math.floor(Math.random()*(122 -
					97)+97); 
			palabra = palabra + (char)codigoAscii; 
		} 
		return palabra; 
	} 

	/**
	 * Generar n&uacute;mero
	 * @return
	 */
	public static int generarNumero(){
		int valorDado = (int) Math.floor(Math.random()*6+1);

		return valorDado;

	}

	/**
	 * Generar palabra con la primer letra may&uacute;scula
	 * @return
	 */
	public static String generarLetraMayuscula(){

		String primeraLetra = generarPalabra().substring(0, 1).toUpperCase();

		String restoDeLaCadena = generarPalabra().substring(1).toLowerCase();

		String primeraMinuscula = primeraLetra + restoDeLaCadena;

		System.out.println("Primera letra mayúscula: " + primeraMinuscula);
		return primeraMinuscula;
	}

	/**
	 * Consultar folio por idActividad
	 * @param idActividad
	 * @param idServicio
	 * @return
	 */
	public static String obtenerFolio(Integer idActividad, Integer idServicio){
		return new StringBuilder().append("WA")
				.append(idActividad)
				.append("-S")
				.append(idServicio)
				.append("-")
				.append(randomCodigo())
				.toString();
	}

	/**
	 * @description: A partir de una fecha este m&eacute;todo calcula otra fecha sumandole o
	 * restandole dias Para restar dias y obtener una fecha pasada ingresar un
	 * numero negativo
	 * 
	 * @param date
	 * @param days
	 * @return el tiempo
	 */
	public static Date additionOrSubtractionDaysOfDate(Date date, int days) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.DAY_OF_YEAR, days);
		logger.info("Dias: " + calendar.getTime());
		return calendar.getTime();
	}
	
	/**
	 * Formato de fecha de string a Date con el formato yyyy/MM/dd
	 * @param fecha
	 * @return
	 */
	public static Date parseFecha(String fecha) {
		Date fechaDate = null;
		try {
			SimpleDateFormat formato = new SimpleDateFormat("yyyy/MM/dd");
			fechaDate = formato.parse(fecha);
		} catch (ParseException ex) {
			logger.error("Error parse date: ", ex);
		}
		return fechaDate;
	}

	/**
	 * Toma la fecha y hora actual del servidor y le suma minutos
	 * @param minutes
	 * @return
	 */
	public static Date sumMinutesToDate(int minutes){
		return new Date(Calendar.getInstance().getTimeInMillis() + (minutes * Constants.MINUTO_EN_MILISEGUNDOS));
	}

	/**
	 * Cifrado AES
	 * @param strToEncrypt
	 * @param key
	 * @return
	 * @throws Exception
	 */
	public static String encrypt(String strToEncrypt, String key) throws Exception {
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		try {

			byte[] raw = DatatypeConverter.parseHexBinary(key);
			SecretKeySpec skeySpec = new SecretKeySpec(raw, Constants.KEY_SPECK_AES);

			Cipher cipher = Cipher.getInstance(Constants.ALGORITMO_ENCRIPTACION);
			cipher.init(Cipher.ENCRYPT_MODE, skeySpec);

			byte[] iv = cipher.getIV();
			byte[] cipherText = cipher.doFinal(strToEncrypt.getBytes(Constants.CODIFICACION_UTF8));

			outputStream = new ByteArrayOutputStream();
			outputStream.write(iv);
			outputStream.write(cipherText);
		} catch (Exception e) {
			logger.error("Error al cifrar: ", e);
			throw e;
		}
		return Base64.getEncoder().encodeToString(outputStream.toByteArray());
	}

	/**
	 * Decodificador AES
	 * @param strToDecrypt
	 * @param key
	 * @return
	 * @throws Exception
	 */
	public static String decrypt(String strToDecrypt, String key) throws Exception {
		String plainText = null;
		try {

			byte[] encryptedData = DatatypeConverter.parseBase64Binary(strToDecrypt);
			byte[] raw = DatatypeConverter.parseHexBinary(key);

			SecretKeySpec skeySpec = new SecretKeySpec(raw, Constants.KEY_SPECK_AES);
			Cipher cipher = Cipher.getInstance(Constants.ALGORITMO_ENCRIPTACION);

			byte[] iv = Arrays.copyOfRange(encryptedData, 0, 16);
			byte[] cipherText = Arrays.copyOfRange(encryptedData, 16, encryptedData.length);

			IvParameterSpec iv_specs = new IvParameterSpec(iv);

			cipher.init(Cipher.DECRYPT_MODE, skeySpec, iv_specs);

			byte[] plainTextBytes = cipher.doFinal(cipherText);
			plainText = new String(plainTextBytes);

		} catch (Exception e) {
			logger.error("Error al descifrar: ", e);
			throw e;
		}
		return plainText;
	}
	
	/**
	 * Convierte una Date en un String con el formato dd/MM/yyyy
	 * @param date
	 * @return
	 */
	public static String dateToStringSinHoras(Date date) {
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");  
		return formatter.format(date);
	}
	/**
	 * Toma la fecha actual y le suma d&iacute;as
	 * @param fecha
	 * @param dias
	 * @return
	 */
	public static Date sumarDiasAFecha(Date fecha, int dias){
		if (dias==0) return fecha;
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(fecha); 
		calendar.add(Calendar.DAY_OF_YEAR, dias);  
		return calendar.getTime(); 
	}
	/**
	 * Crea un c&oacute;digo aleatorio
	 * @return
	 */
	public static String randomCodigo() {
		return String.format(Constants.STRING_FORMAT_SEIS_DIGITOS, new Random().nextInt(Constants.MAX_RANDOM));
	}
	/**
	 * Consulta si esta registrado o no
	 * @param estados
	 * @return
	 
	public static boolean tieneEstadoRegistrato(Integer[] estados) {

		for (int index = 0; index < estados.length; index++) {
			if (estados[index] == Constants.CAT_ESTADO_USUARIO_REGISTRADO) {
				return Boolean.TRUE;
			}
		}

		return Boolean.FALSE;
	}
	*/

	/**
	 * Calcular porcentaje
	 * @param total
	 * @param porcentaje
	 * @return
	 */
	public static double calcularPorcentaje(double total, double porcentaje) {
		return ((total * porcentaje) / 100);
	}

	/**
	 * calcular los ultimos d&iacute;gitos de la tarjeta
	 * @param numeroTarjeta
	 * @return
	 */
	public static String ultimosDigitos(String numeroTarjeta) {

		String ultimosDigitos = numeroTarjeta.substring((numeroTarjeta.length()-4),numeroTarjeta.length());

		return ultimosDigitos;
	}

	/**
	 * Redondea un bigdecimal a dos decimales
	 * @param value
	 * @return
	 */
	public static BigDecimal roundBigDecimal(String value) {
		BigDecimal bigDecimal = new BigDecimal(value);
		bigDecimal.setScale(2, RoundingMode.DOWN);
		return bigDecimal;
	}

	/**
	 * Comprimir la im&aacute;gen en caso de que exceda el tamaño maximo
	 * @param imagenBytes
	 * @param ruta
	 */
	public static void guardarImagen(byte[] imagenBytes, String ruta) {

		try {

			ByteArrayInputStream bais = new ByteArrayInputStream(imagenBytes);
			BufferedImage image = ImageIO.read(bais);

			File compressedImageFile = new File(ruta);
			OutputStream os = new FileOutputStream(compressedImageFile);

			Iterator<ImageWriter> writers = ImageIO.getImageWritersByFormatName("jpg");
			ImageWriter writer = (ImageWriter) writers.next();

			ImageOutputStream ios = ImageIO.createImageOutputStream(os);
			writer.setOutput(ios);

			ImageWriteParam param = writer.getDefaultWriteParam();

			param.setCompressionMode(ImageWriteParam.MODE_EXPLICIT);
			param.setCompressionQuality(0.05f);  // Change the quality value you prefer
			writer.write(null, new IIOImage(image, null, null), param);

			os.close();
			ios.close();
			writer.dispose();

		} catch (IOException e) {
			logger.error("Error al bajar calidad: ", e);
		} catch (Exception e) {
			logger.error("[Exception] Error al bajar calidad: ", e);
		}
	}
	
}
