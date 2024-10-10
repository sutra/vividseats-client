package org.oxerr.vividseats.client.model;

import java.io.Serializable;

public class Ticket implements Serializable {

	private static final long serialVersionUID = 2024092601L;

	private Integer id;

	private Integer seatNumber;

	private String fileName;

	private String barCode;

	private String ticketToken;

	private Long listingId;

	private String fileData;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getSeatNumber() {
		return seatNumber;
	}

	public void setSeatNumber(Integer seatNumber) {
		this.seatNumber = seatNumber;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getBarCode() {
		return barCode;
	}

	public void setBarCode(String barCode) {
		this.barCode = barCode;
	}

	public String getTicketToken() {
		return ticketToken;
	}

	public void setTicketToken(String ticketToken) {
		this.ticketToken = ticketToken;
	}

	public Long getListingId() {
		return listingId;
	}

	public void setListingId(Long listingId) {
		this.listingId = listingId;
	}

	public String getFileData() {
		return fileData;
	}

	public void setFileData(String fileData) {
		this.fileData = fileData;
	}

}
