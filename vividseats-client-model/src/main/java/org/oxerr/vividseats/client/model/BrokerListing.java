package org.oxerr.vividseats.client.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

/**
 * A listing from a broker.
 *
 * <a href="https://vividseats.stoplight.io/docs/broker-portal/decf5dae8afe5-broker-listing">Broker Listing</a>.
 */
public class BrokerListing implements Serializable {

	private static final long serialVersionUID = 2024092601L;

	private Long id;

	private Integer productionId;

	private Integer quantity;

	private String section;

	private String row;

	private String seatFrom;

	private String seatThru;

	private String notes;

	private BigDecimal price;

	private String ticketId;

	private Boolean electronic;

	private Boolean electronicTransfer;

	private LocalDateTime inHandDate;

	private LocalDateTime listDate;

	private SplitType splitType;

	private String splitValue;

	private Boolean spec;

	private Boolean instantDownload;

	private String passThrough;

	private StockType stockType;

	private List<Integer> seatNumbers;

	private BigDecimal faceValue;

	private BigDecimal unitTaxedCost;

	private Boolean instantTransfer;

	private List<String> attributes;

	private List<Ticket> tickets;

	private String internalNotes;

	private String eventName;

	private String venue;

	private String city;

	private String state;

	private LocalDateTime eventDate;

	private String shipDate;

	private BigDecimal cost;

	private Boolean hasFiles;

	private Boolean hasBarcodes;

	private LocalDateTime lastUpdate;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getProductionId() {
		return productionId;
	}

	public void setProductionId(Integer productionId) {
		this.productionId = productionId;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public String getSection() {
		return section;
	}

	public void setSection(String section) {
		this.section = section;
	}

	public String getRow() {
		return row;
	}

	public void setRow(String row) {
		this.row = row;
	}

	public String getSeatFrom() {
		return seatFrom;
	}

	public void setSeatFrom(String seatFrom) {
		this.seatFrom = seatFrom;
	}

	public String getSeatThru() {
		return seatThru;
	}

	public void setSeatThru(String seatThru) {
		this.seatThru = seatThru;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public String getTicketId() {
		return ticketId;
	}

	public void setTicketId(String ticketId) {
		this.ticketId = ticketId;
	}

	public Boolean getElectronic() {
		return electronic;
	}

	public void setElectronic(Boolean electronic) {
		this.electronic = electronic;
	}

	public Boolean getElectronicTransfer() {
		return electronicTransfer;
	}

	public void setElectronicTransfer(Boolean electronicTransfer) {
		this.electronicTransfer = electronicTransfer;
	}

	public LocalDateTime getInHandDate() {
		return inHandDate;
	}

	public void setInHandDate(LocalDateTime inHandDate) {
		this.inHandDate = inHandDate;
	}

	public LocalDateTime getListDate() {
		return listDate;
	}

	public void setListDate(LocalDateTime listDate) {
		this.listDate = listDate;
	}

	public SplitType getSplitType() {
		return splitType;
	}

	public void setSplitType(SplitType splitType) {
		this.splitType = splitType;
	}

	public String getSplitValue() {
		return splitValue;
	}

	public void setSplitValue(String splitValue) {
		this.splitValue = splitValue;
	}

	public Boolean getSpec() {
		return spec;
	}

	public void setSpec(Boolean spec) {
		this.spec = spec;
	}

	public Boolean getInstantDownload() {
		return instantDownload;
	}

	public void setInstantDownload(Boolean instantDownload) {
		this.instantDownload = instantDownload;
	}

	public String getPassThrough() {
		return passThrough;
	}

	public void setPassThrough(String passThrough) {
		this.passThrough = passThrough;
	}

	public StockType getStockType() {
		return stockType;
	}

	public void setStockType(StockType stockType) {
		this.stockType = stockType;
	}

	public List<Integer> getSeatNumbers() {
		return seatNumbers;
	}

	public void setSeatNumbers(List<Integer> seatNumbers) {
		this.seatNumbers = seatNumbers;
	}

	public BigDecimal getFaceValue() {
		return faceValue;
	}

	public void setFaceValue(BigDecimal faceValue) {
		this.faceValue = faceValue;
	}

	public BigDecimal getUnitTaxedCost() {
		return unitTaxedCost;
	}

	public void setUnitTaxedCost(BigDecimal unitTaxedCost) {
		this.unitTaxedCost = unitTaxedCost;
	}

	public Boolean getInstantTransfer() {
		return instantTransfer;
	}

	public void setInstantTransfer(Boolean instantTransfer) {
		this.instantTransfer = instantTransfer;
	}

	public List<String> getAttributes() {
		return attributes;
	}

	public void setAttributes(List<String> attributes) {
		this.attributes = attributes;
	}

	public List<Ticket> getTickets() {
		return tickets;
	}

	public void setTickets(List<Ticket> tickets) {
		this.tickets = tickets;
	}

	public String getInternalNotes() {
		return internalNotes;
	}

	public void setInternalNotes(String internalNotes) {
		this.internalNotes = internalNotes;
	}

	public String getEventName() {
		return eventName;
	}

	public void setEventName(String eventName) {
		this.eventName = eventName;
	}

	public String getVenue() {
		return venue;
	}

	public void setVenue(String venue) {
		this.venue = venue;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public LocalDateTime getEventDate() {
		return eventDate;
	}

	public void setEventDate(LocalDateTime eventDate) {
		this.eventDate = eventDate;
	}

	public String getShipDate() {
		return shipDate;
	}

	public void setShipDate(String shipDate) {
		this.shipDate = shipDate;
	}

	public BigDecimal getCost() {
		return cost;
	}

	public void setCost(BigDecimal cost) {
		this.cost = cost;
	}

	public Boolean getHasFiles() {
		return hasFiles;
	}

	public void setHasFiles(Boolean hasFiles) {
		this.hasFiles = hasFiles;
	}

	public Boolean getHasBarcodes() {
		return hasBarcodes;
	}

	public void setHasBarcodes(Boolean hasBarcodes) {
		this.hasBarcodes = hasBarcodes;
	}

	public LocalDateTime getLastUpdate() {
		return lastUpdate;
	}

	public void setLastUpdate(LocalDateTime lastUpdate) {
		this.lastUpdate = lastUpdate;
	}

}
