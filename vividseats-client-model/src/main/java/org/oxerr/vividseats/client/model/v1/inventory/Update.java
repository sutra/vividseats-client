package org.oxerr.vividseats.client.model.v1.inventory;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.oxerr.vividseats.client.model.inventory.SplitType;

/**
 * Request to update a listing.
 * 
 * <a href="https://vividseats.stoplight.io/docs/broker-portal/ebf6bb237e97b-update-listing">
 * /listings/v1/updateListing
 * </a>
 */
public class Update implements Serializable {

	private static final long serialVersionUID = 2024101501L;

	private String apiToken;

	private String ticketId;

	private Integer quantity;

	private String section;

	private String row;

	private String seatFrom;

	private String seatThru;

	private String notes;

	private BigDecimal price;

	private Boolean electronic;

	private String inHandDate;

	private SplitType splitType;

	private String splitValue;

	private List<String> barcode;

	private BigDecimal faceValue;

	private BigDecimal unitTaxedCost;

	public String getApiToken() {
		return apiToken;
	}

	public void setApiToken(String apiToken) {
		this.apiToken = apiToken;
	}

	public String getTicketId() {
		return ticketId;
	}

	public void setTicketId(String ticketId) {
		this.ticketId = ticketId;
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

	public Boolean getElectronic() {
		return electronic;
	}

	public void setElectronic(Boolean electronic) {
		this.electronic = electronic;
	}

	public String getInHandDate() {
		return inHandDate;
	}

	public void setInHandDate(String inHandDate) {
		this.inHandDate = inHandDate;
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

	public List<String> getBarcode() {
		return barcode;
	}

	public void setBarcode(List<String> barcode) {
		this.barcode = barcode;
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

	@Override
	public int hashCode() {
		return HashCodeBuilder.reflectionHashCode(this);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof Update)) {
			return false;
		}
		Update rhs = (Update) obj;
		return EqualsBuilder.reflectionEquals(this, rhs);
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

}
