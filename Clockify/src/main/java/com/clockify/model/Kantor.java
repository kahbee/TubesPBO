package com.clockify.model;

/**
 *
 * @author khusyasy
 */
public class Kantor {
    private int id;
    private String nama;
    private String alamat;
    private Departemen[] departemens;

    public Kantor(int id, String nama, String alamat, Departemen[] departemens) {
        this.id = id;
        this.nama = nama;
        this.alamat = alamat;
        this.departemens = departemens;
    }

    public Departemen[] getDepartemens() {
        return departemens;
    }

    public void setDepartemens(Departemen[] departemens) {
        this.departemens = departemens;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }
}
