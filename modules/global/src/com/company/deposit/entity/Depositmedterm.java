package com.company.deposit.entity;

import com.haulmont.chile.core.annotations.NamePattern;
import com.haulmont.cuba.core.entity.FileDescriptor;
import com.haulmont.cuba.core.entity.StandardEntity;
import com.haulmont.cuba.core.entity.annotation.OnDelete;
import com.haulmont.cuba.core.global.DeletePolicy;

import javax.persistence.*;

@Table(name = "DEPOSIT_DEPOSITMEDTERM")
@Entity(name = "deposit_Depositmedterm")
@NamePattern("%s|accenumb")
public class Depositmedterm extends StandardEntity {
    private static final long serialVersionUID = 5160070962815079633L;

    @Column(name = "ACCENUMB", nullable = false, length = 10)
    private String accenumb;

    @Column(name = "DEPOSIT", nullable = false, unique = true, length = 30)
    private String deposit;

    @Column(name = "YEARSTORE", length = 8)
    private String yearstore;

    @Column(name = "YEARMULTI", length = 4)
    private String yearmulti;

    @Column(name = "MULTIPLY", length = 2)
    private String multiply;

    @Column(name = "YEARGERM", length = 4)
    private String yeargerm;

    @Column(name = "PERCENTAGE")
    private Integer percentage;

    @Column(name = "STOCK")
    private Integer stock;

    @Column(name = "SUMSTOCK")
    private Integer sumstock;

    @OnDelete(DeletePolicy.CASCADE)
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "QR_CODE_ID")
    protected FileDescriptor qrcode;

    public String getAccenumb() {
        return accenumb;
    }

    public void setAccenumb(String accenumb) {
        this.accenumb = accenumb;
    }

    public String getDeposit() {
        return deposit;
    }

    public void setDeposit(String deposit) {
        this.deposit = deposit;
    }

    public String getYearstore() {
        return yearstore;
    }

    public void setYearstore(String yearstore) {
        this.yearstore = yearstore;
    }

    public String getYearmulti() {
        return yearmulti;
    }

    public void setYearmulti(String yearmulti) {
        this.yearmulti = yearmulti;
    }

    public String getMultiply() {
        return multiply;
    }

    public void setMultiply(String multiply) {
        this.multiply = multiply;
    }

    public String getYeargerm() {
        return yeargerm;
    }

    public void setYeargerm(String yeargerm) {
        this.yeargerm = yeargerm;
    }

    public Integer getPercentage() {
        return percentage;
    }

    public void setPercentage(Integer percentage) {
        this.percentage = percentage;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public Integer getSumstock() {
        return sumstock;
    }

    public void setSumstock(Integer sumstock) {
        this.sumstock = sumstock;
    }

    public FileDescriptor getQrcode() {
        return qrcode;
    }

    public void setQrcode(FileDescriptor qrcode) {
        this.qrcode = qrcode;
    }

}