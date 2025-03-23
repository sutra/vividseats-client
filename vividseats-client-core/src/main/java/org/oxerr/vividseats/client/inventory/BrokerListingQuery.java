package org.oxerr.vividseats.client.inventory;

import java.time.LocalDateTime;

public class BrokerListingQuery {

	private Long listingId;

	private String internalTicketId;

	private Integer productionId;

	private Integer headlinerId;

	private LocalDateTime fromEventDate;

	private LocalDateTime toEventDate;

	private Boolean includeFiles;

	public Long getListingId() {
		return listingId;
	}

	public void setListingId(Long listingId) {
		this.listingId = listingId;
	}

	public String getInternalTicketId() {
		return internalTicketId;
	}

	public void setInternalTicketId(String internalTicketId) {
		this.internalTicketId = internalTicketId;
	}

	public Integer getProductionId() {
		return productionId;
	}

	public void setProductionId(Integer productionId) {
		this.productionId = productionId;
	}

	public Integer getHeadlinerId() {
		return headlinerId;
	}

	public void setHeadlinerId(Integer headlinerId) {
		this.headlinerId = headlinerId;
	}

	public LocalDateTime getFromEventDate() {
		return fromEventDate;
	}

	public void setFromEventDate(LocalDateTime fromEventDate) {
		this.fromEventDate = fromEventDate;
	}

	public LocalDateTime getToEventDate() {
		return toEventDate;
	}

	public void setToEventDate(LocalDateTime toEventDate) {
		this.toEventDate = toEventDate;
	}

	public Boolean getIncludeFiles() {
		return includeFiles;
	}

	public void setIncludeFiles(Boolean includeFiles) {
		this.includeFiles = includeFiles;
	}

}
