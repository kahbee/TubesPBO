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

public class PekerjaBiasa extends Karyawan {
    private List<String> tugas;

    public PekerjaBiasa(int id, String nama, String jenisKelamin, String email, String noHP, Departemen departemen) {
        super(id, nama, jenisKelamin, email, noHP, departemen);
        this.tugas = new ArrayList<>();
    }

    public List<String> getTugas() {
        return tugas;
    }

    public void tambahTugas(String tugasBaru) {
        this.tugas.add(tugasBaru);
        System.out.println("Tugas berhasil ditambahkan: " + tugasBaru);
    }

    public void hapusTugas(String tugasHapus) {
        if (tugas.contains(tugasHapus)) {
            this.tugas.remove(tugasHapus);
            System.out.println("Tugas berhasil dihapus: " + tugasHapus);
        } else {
            System.out.println("Tugas tidak ditemukan: " + tugasHapus);
        }
    }

    @Override
    public void info() {
        System.out.println("Info Pekerja Biasa: " + getNama() + " - " + getDepartemen().getNama());
        System.out.println("Daftar Tugas:");
        if (tugas.isEmpty()) {
            System.out.println("Tidak ada tugas");
        } else {
            for (String tugas : tugas) {
                System.out.println("- " + tugas);
            }
        }
    }
}
