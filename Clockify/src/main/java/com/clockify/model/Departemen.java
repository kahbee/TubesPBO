package com.clockify.model;

/**
 *
 * @author khusyasy
 */
public class Departemen {
    private int id;
    private String nama;

    public Departemen(int id, String nama) {
        this.id = id;
        this.nama = nama;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    @Override
    public String toString() {
        // buat tampilan JList
        return getNama();
    }
}
