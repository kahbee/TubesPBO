/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.clockify.model;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author krf23
 */
public class DaftarAbsensi {
    private List<Absensi> listAbsensi = new ArrayList<>();

    // Constructor
    public DaftarAbsensi(Absensi[] absensiArray) {
        for (Absensi absensi : absensiArray) {
            tambahAbsensi(absensi);
        }
    }

    // Getter untuk mendapatkan daftar absensi
    public Absensi[] getAbsensi() {
        return listAbsensi.toArray(new Absensi[0]);
    }

    // Method untuk menambahkan absensi ke daftar
    public void tambahAbsensi(Absensi absensi) {
        listAbsensi.add(absensi);
    }

    // Method untuk mencari absensi berdasarkan status
    public Absensi[] searchAbsensi(String status) {
        List<Absensi> hasilPencarian = new ArrayList<>();
        for (Absensi absensi : listAbsensi) {
            if (absensi.getStatus().equalsIgnoreCase(status)) {
                hasilPencarian.add(absensi);
            }
        }
        return hasilPencarian.toArray(new Absensi[0]);
    }
}
