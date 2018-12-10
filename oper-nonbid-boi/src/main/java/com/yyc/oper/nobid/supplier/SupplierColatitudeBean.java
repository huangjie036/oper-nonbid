package com.yyc.oper.nobid.supplier;

public class SupplierColatitudeBean {
    private String id;

    private String supplierId;

    private String colatitudeId;

    private String del;//删除标记

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(String supplierId) {
        this.supplierId = supplierId == null ? null : supplierId.trim();
    }

    public String getColatitudeId() {
        return colatitudeId;
    }

    public void setColatitudeId(String colatitudeId) {
        this.colatitudeId = colatitudeId == null ? null : colatitudeId.trim();
    }

    public String getDel() {
        return del;
    }

    public void setDel(String del) {
        this.del = del == null ? null : del.trim();
    }
}