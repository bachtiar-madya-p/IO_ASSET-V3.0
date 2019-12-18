/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.io.asset.model;

import java.io.Serializable;
import java.math.BigInteger;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.Size;

/**
 *
 * @author permadi
 */
@Entity
@Table(name = "serial_data_results")
@NamedQueries({
    @NamedQuery(name = "SerialDataResults.findAll", query = "SELECT s FROM SerialDataResults s")})
public class SerialDataResults implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Long id;
    @Column(name = "uploaded")
    private Short uploaded;
    @Size(max = 50)
    @Column(name = "site_id")
    private String siteId;
    @Column(name = "ID_start")
    private BigInteger iDstart;
    @Column(name = "ID_end")
    private BigInteger iDend;
    @Size(max = 50)
    @Column(name = "data_state")
    private String dataState;
    @Size(max = 50)
    @Column(name = "ticket_no")
    private String ticketNo;
    @Size(max = 50)
    @Column(name = "start")
    private String start;
    @Size(max = 50)
    @Column(name = "finish")
    private String finish;
    @Size(max = 50)
    @Column(name = "start_count")
    private String startCount;
    @Size(max = 50)
    @Column(name = "start_count_uom")
    private String startCountUom;
    @Size(max = 50)
    @Column(name = "end_count")
    private String endCount;
    @Size(max = 50)
    @Column(name = "end_count_uom")
    private String endCountUom;
    @Size(max = 50)
    @Column(name = "gross_deliver")
    private String grossDeliver;
    @Size(max = 50)
    @Column(name = "gross_deliver_uom")
    private String grossDeliverUom;
    @Size(max = 50)
    @Column(name = "avg_flow_rate")
    private String avgFlowRate;
    @Size(max = 50)
    @Column(name = "avg_flow_rate_uom")
    private String avgFlowRateUom;
    @Size(max = 50)
    @Column(name = "after_avg_flow_rate")
    private String afterAvgFlowRate;
    @Size(max = 50)
    @Column(name = "sale_number")
    private String saleNumber;
    @Size(max = 50)
    @Column(name = "meter_number")
    private String meterNumber;
    @Size(max = 50)
    @Column(name = "unit_id")
    private String unitId;
    @Size(max = 50)
    @Column(name = "duplicate")
    private String duplicate;
    @Size(max = 50)
    @Column(name = "other_one")
    private String otherOne;
    @Size(max = 50)
    @Column(name = "other_two")
    private String otherTwo;
    @Size(max = 50)
    @Column(name = "other_three")
    private String otherThree;
    @Size(max = 50)
    @Column(name = "other_four")
    private String otherFour;
    @Size(max = 50)
    @Column(name = "other_five")
    private String otherFive;

    public SerialDataResults() {
    }

    public SerialDataResults(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Short getUploaded() {
        return uploaded;
    }

    public void setUploaded(Short uploaded) {
        this.uploaded = uploaded;
    }

    public String getSiteId() {
        return siteId;
    }

    public void setSiteId(String siteId) {
        this.siteId = siteId;
    }

    public BigInteger getIDstart() {
        return iDstart;
    }

    public void setIDstart(BigInteger iDstart) {
        this.iDstart = iDstart;
    }

    public BigInteger getIDend() {
        return iDend;
    }

    public void setIDend(BigInteger iDend) {
        this.iDend = iDend;
    }

    public String getDataState() {
        return dataState;
    }

    public void setDataState(String dataState) {
        this.dataState = dataState;
    }

    public String getTicketNo() {
        return ticketNo;
    }

    public void setTicketNo(String ticketNo) {
        this.ticketNo = ticketNo;
    }

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public String getFinish() {
        return finish;
    }

    public void setFinish(String finish) {
        this.finish = finish;
    }

    public String getStartCount() {
        return startCount;
    }

    public void setStartCount(String startCount) {
        this.startCount = startCount;
    }

    public String getStartCountUom() {
        return startCountUom;
    }

    public void setStartCountUom(String startCountUom) {
        this.startCountUom = startCountUom;
    }

    public String getEndCount() {
        return endCount;
    }

    public void setEndCount(String endCount) {
        this.endCount = endCount;
    }

    public String getEndCountUom() {
        return endCountUom;
    }

    public void setEndCountUom(String endCountUom) {
        this.endCountUom = endCountUom;
    }

    public String getGrossDeliver() {
        return grossDeliver;
    }

    public void setGrossDeliver(String grossDeliver) {
        this.grossDeliver = grossDeliver;
    }

    public String getGrossDeliverUom() {
        return grossDeliverUom;
    }

    public void setGrossDeliverUom(String grossDeliverUom) {
        this.grossDeliverUom = grossDeliverUom;
    }

    public String getAvgFlowRate() {
        return avgFlowRate;
    }

    public void setAvgFlowRate(String avgFlowRate) {
        this.avgFlowRate = avgFlowRate;
    }

    public String getAvgFlowRateUom() {
        return avgFlowRateUom;
    }

    public void setAvgFlowRateUom(String avgFlowRateUom) {
        this.avgFlowRateUom = avgFlowRateUom;
    }

    public String getAfterAvgFlowRate() {
        return afterAvgFlowRate;
    }

    public void setAfterAvgFlowRate(String afterAvgFlowRate) {
        this.afterAvgFlowRate = afterAvgFlowRate;
    }

    public String getSaleNumber() {
        return saleNumber;
    }

    public void setSaleNumber(String saleNumber) {
        this.saleNumber = saleNumber;
    }

    public String getMeterNumber() {
        return meterNumber;
    }

    public void setMeterNumber(String meterNumber) {
        this.meterNumber = meterNumber;
    }

    public String getUnitId() {
        return unitId;
    }

    public void setUnitId(String unitId) {
        this.unitId = unitId;
    }

    public String getDuplicate() {
        return duplicate;
    }

    public void setDuplicate(String duplicate) {
        this.duplicate = duplicate;
    }

    public String getOtherOne() {
        return otherOne;
    }

    public void setOtherOne(String otherOne) {
        this.otherOne = otherOne;
    }

    public String getOtherTwo() {
        return otherTwo;
    }

    public void setOtherTwo(String otherTwo) {
        this.otherTwo = otherTwo;
    }

    public String getOtherThree() {
        return otherThree;
    }

    public void setOtherThree(String otherThree) {
        this.otherThree = otherThree;
    }

    public String getOtherFour() {
        return otherFour;
    }

    public void setOtherFour(String otherFour) {
        this.otherFour = otherFour;
    }

    public String getOtherFive() {
        return otherFive;
    }

    public void setOtherFive(String otherFive) {
        this.otherFive = otherFive;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SerialDataResults)) {
            return false;
        }
        SerialDataResults other = (SerialDataResults) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "id.io.dfm.model.SerialDataResults[ id=" + id + " ]";
    }
    
}
