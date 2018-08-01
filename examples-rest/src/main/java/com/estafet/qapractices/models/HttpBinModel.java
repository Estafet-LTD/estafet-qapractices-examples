/**
 * Copyright (C) Estafet Ltd
 */
package com.estafet.qapractices.models;

import com.google.gson.annotations.Expose;

/**
 * Created by Pesho on 15-Sep-17.
 */

/**
 * It's a POJO. Duh...
 */
public class HttpBinModel {

    @Expose
    private String userName;

    @Expose
    private String length;

    @Expose
    private String cupSize;

    @Expose
    private String measures;

    public String getUserName() {
        return userName;
    }

    public void setUserName(final String userName) {
        this.userName = userName;
    }

    public String getLength() {
        return length;
    }

    public void setLength(final String length) {
        this.length = length;
    }

    public String getCupSize() {
        return cupSize;
    }

    public void setCupSize(final String cupSize) {
        this.cupSize = cupSize;
    }

    public String getMeasures() {
        return measures;
    }

    public void setMeasures(final String measures) {
        this.measures = measures;
    }
}
