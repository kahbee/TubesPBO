/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.clockify.model;

/**
 *
 * @author WINDOWS 10
 */
import java.util.ArrayList;
import java.util.List;

public class Teknisi extends Karyawan {
    private List<String> peralatan;

    public Teknisi(int id, String nama, String jenisKelamin, String email, String noHP, Departemen departemen) {
        super(id, nama, jenisKelamin, email, noHP, departemen);
        this.peralatan = new ArrayList<>();
    }

    public List<String> getPeralatan() {
        return peralatan;
    }

    public void tambahPeralatan(String peralatanBaru) {
        // Menambahkan peralatan baru ke list
        this.peralatan.add(peralatanBaru);
        System.out.println("Peralatan berhasil ditambahkan: " + peralatanBaru);
    }

    public void hapusPeralatan(String peralatanHapus) {
        if (peralatan.contains(peralatanHapus)) {
            this.peralatan.remove(peralatanHapus);
            System.out.println("Peralatan berhasil dihapus: " + peralatanHapus);
        } else {
            System.out.println("Peralatan tidak ditemukan: " + peralatanHapus);
        }
    }

    @Override
    public void info() {
        System.out.println("Info Teknisi: " + getNama() + " - " + getDepartemen().getNama());
        System.out.println("Daftar Peralatan:");
        if (peralatan.isEmpty()) {
            System.out.println("Tidak ada peralatan");
        } else {
            for (String peralatan : peralatan) {
                System.out.println("- " + peralatan);
            }
        }
    }
}

