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

public class Manager extends Karyawan {
    private Departemen departemenDikelola;
    private List<String> tugasDepartemen;

    public Manager(int id, String nama, String jenisKelamin, String email, String noHP, Departemen departemen) {
        super(id, nama, jenisKelamin, email, noHP, departemen);
        this.tugasDepartemen = new ArrayList<>();
    }

    public Departemen getDepartemenDikelola() {
        return departemenDikelola;
    }

    public void setDepartemenDikelola(Departemen departemenDikelola) {
        this.departemenDikelola = departemenDikelola;
    }

    public List<String> getTugasDepartemen() {
        return tugasDepartemen;
    }

    public void tambahTugasDepartemen(String tugasBaru) {
        this.tugasDepartemen.add(tugasBaru);
        System.out.println("Tugas departemen berhasil ditambahkan: " + tugasBaru);
    }

    public void hapusTugasDepartemen(String tugasHapus) {
        if (tugasDepartemen.contains(tugasHapus)) {
            this.tugasDepartemen.remove(tugasHapus);
            System.out.println("Tugas departemen berhasil dihapus: " + tugasHapus);
        } else {
            System.out.println("Tugas departemen tidak ditemukan: " + tugasHapus);
        }
    }

    @Override
    public void info() {
        System.out.println("Info Manager: " + getNama() + " - " + getDepartemen().getNama());
        System.out.println("Departemen yang dikelola: " + (departemenDikelola != null ? departemenDikelola.getNama() : "Belum ditentukan"));
        System.out.println("Daftar Tugas Departemen:");
        if (tugasDepartemen.isEmpty()) {
            System.out.println("Tidak ada tugas departemen");
        } else {
            for (String tugas : tugasDepartemen) {
                System.out.println("- " + tugas);
            }
        }
    }
}

