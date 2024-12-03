package com.mx.nativelabs.backendapp.commons.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Table(name = "HISTORICO_QR")
@Entity
public class HistoricoQrDO {


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_historico_qr")
	private Integer idHistoricQr;
	
	@Column(name = "url_qr")
	private String urlQr;
	
	@Column(name = "estatus")
	private boolean status;
	
	@OneToOne
	@JoinColumn(name = "id_sucursal")
	private BranchOfficeDO branchOffice;
	

	public Integer getId_historic_qr() {
		return idHistoricQr;
	}

	public void setId_historic_qr(Integer id_historic_qr) {
		this.idHistoricQr = id_historic_qr;
	}

	public String getUrlQr() {
		return urlQr;
	}

	public void setUrlQr(String urlQr) {
		this.urlQr = urlQr;
	}

	public boolean getStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}
	
	public BranchOfficeDO getBranchOffice() {
		return branchOffice;
	}

	public void setBranchOffice(BranchOfficeDO historicoQrDO) {
		this.branchOffice = historicoQrDO;
	}
	


	
}	