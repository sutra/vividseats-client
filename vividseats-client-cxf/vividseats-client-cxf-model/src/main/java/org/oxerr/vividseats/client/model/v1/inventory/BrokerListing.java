package org.oxerr.vividseats.client.model.v1.inventory;

import java.math.BigDecimal;
import java.util.List;

import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "brokerListing")
public class BrokerListing {
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
	private String inHandDate;
	private String listDate;
	private String splitType;
	private String splitValue;
	private Boolean spec;
	private Boolean instantDownload;
	private String passThrough;
	private String stockType;
	private List<Integer> seatNumbers;
	private BigDecimal faceValue;
	private BigDecimal unitTaxedCost;
	private Boolean instantTransfer;

	@XmlElement
	public Long getId() { return id; }
	public void setId(Long id) { this.id = id; }

	@XmlElement
	public Integer getProductionId() { return productionId; }
	public void setProductionId(Integer productionId) { this.productionId = productionId; }

	@XmlElement
	public Integer getQuantity() { return quantity; }
	public void setQuantity(Integer quantity) { this.quantity = quantity; }

	@XmlElement
	public String getSection() { return section; }
	public void setSection(String section) { this.section = section; }

	@XmlElement
	public String getRow() { return row; }
	public void setRow(String row) { this.row = row; }

	@XmlElement
	public String getSeatFrom() { return seatFrom; }
	public void setSeatFrom(String seatFrom) { this.seatFrom = seatFrom; }

	@XmlElement
	public String getSeatThru() { return seatThru; }
	public void setSeatThru(String seatThru) { this.seatThru = seatThru; }

	@XmlElement
	public String getNotes() { return notes; }
	public void setNotes(String notes) { this.notes = notes; }

	@XmlElement
	public BigDecimal getPrice() { return price; }
	public void setPrice(BigDecimal price) { this.price = price; }

	@XmlElement
	public String getTicketId() { return ticketId; }
	public void setTicketId(String ticketId) { this.ticketId = ticketId; }

	@XmlElement
	public Boolean getElectronic() { return electronic; }
	public void setElectronic(Boolean electronic) { this.electronic = electronic; }

	@XmlElement
	public Boolean getElectronicTransfer() { return electronicTransfer; }
	public void setElectronicTransfer(Boolean electronicTransfer) { this.electronicTransfer = electronicTransfer; }

	@XmlElement
	public String getInHandDate() { return inHandDate; }
	public void setInHandDate(String inHandDate) { this.inHandDate = inHandDate; }

	@XmlElement
	public String getListDate() { return listDate; }
	public void setListDate(String listDate) { this.listDate = listDate; }

	@XmlElement
	public String getSplitType() { return splitType; }
	public void setSplitType(String splitType) { this.splitType = splitType; }

	@XmlElement
	public String getSplitValue() { return splitValue; }
	public void setSplitValue(String splitValue) { this.splitValue = splitValue; }

	@XmlElement
	public Boolean getSpec() { return spec; }
	public void setSpec(Boolean spec) { this.spec = spec; }

	@XmlElement
	public Boolean getInstantDownload() { return instantDownload; }
	public void setInstantDownload(Boolean instantDownload) { this.instantDownload = instantDownload; }

	@XmlElement
	public String getPassThrough() { return passThrough; }
	public void setPassThrough(String passThrough) { this.passThrough = passThrough; }

	@XmlElement
	public String getStockType() { return stockType; }
	public void setStockType(String stockType) { this.stockType = stockType; }

	@XmlElement
	public List<Integer> getSeatNumbers() { return seatNumbers; }
	public void setSeatNumbers(List<Integer> seatNumbers) { this.seatNumbers = seatNumbers; }

	@XmlElement
	public BigDecimal getFaceValue() { return faceValue; }
	public void setFaceValue(BigDecimal faceValue) { this.faceValue = faceValue; }

	@XmlElement
	public BigDecimal getUnitTaxedCost() { return unitTaxedCost; }
	public void setUnitTaxedCost(BigDecimal unitTaxedCost) { this.unitTaxedCost = unitTaxedCost; }

	@XmlElement
	public Boolean getInstantTransfer() { return instantTransfer; }
	public void setInstantTransfer(Boolean instantTransfer) { this.instantTransfer = instantTransfer; }
}
