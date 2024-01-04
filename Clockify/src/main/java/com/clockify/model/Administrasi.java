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

public class Administrasi extends Karyawan {
    private List<String> tugasAdm;

    public Administrasi(int id, String nama, String jenisKelamin, String email, String noHP, Departemen departemen) {
        super(id, nama, jenisKelamin, email, noHP, departemen);
        this.tugasAdm = new ArrayList<>();
    }

    public List<String> getTugas() {
        return tugasAdm;
    }

    public void tambahTugas(String tugasBaru) {
        this.tugasAdm.add(tugasBaru);
        System.out.println("Tugas berhasil ditambahkan: " + tugasBaru);
    }

    public void hapusTugas(String tugasHapus) {
        if (tugasAdm.contains(tugasHapus)) {
            this.tugasAdm.remove(tugasHapus);
            System.out.println("Tugas berhasil dihapus: " + tugasHapus);
        } else {
            System.out.println("Tugas tidak ditemukan: " + tugasHapus);
        }
    }

    @Override
    public void info() {
        System.out.println("Info Pekerja Biasa: " + getNama() + " - " + getDepartemen().getNama());
        System.out.println("Daftar Tugas:");
        if (tugasAdm.isEmpty()) {
            System.out.println("Tidak ada tugas");
        } else {
            for (String tugas : tugasAdm) {
                System.out.println("- " + tugas);
            }
        }
    }
}

