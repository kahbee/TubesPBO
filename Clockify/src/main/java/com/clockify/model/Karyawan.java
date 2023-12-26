/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.clockify.model;

import java.time.LocalDateTime;

/**
 *
 * @author krf23
 */
public abstract class Karyawan implements BisaAbsen {
    private int id;
    private String nama;
    private String jenisKelamin;
    private String noHP;
    private String email;
    private Departemen departemen;

    public Karyawan(int id, String nama, String jenisKelamin, String email, String noHP, Departemen departemen) {
        this.id = id;
        this.nama = nama;
        this.jenisKelamin = jenisKelamin;
        this.email = email;
        this.noHP = noHP;
        this.departemen = departemen;
    }

    public int getId() {
        return id;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getJenisKelamin() {
        return jenisKelamin;
    }

    public void setJenisKelamin(String jenisKelamin) {
        this.jenisKelamin = jenisKelamin;
    }

    public String getNoHP() {
        return noHP;
    }

    public void setNoHP(String noHP) {
        this.noHP = noHP;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Departemen getDepartemen() {
        return departemen;
    }

    public abstract void info();

    @Override
    public void absen(DaftarAbsensi daftarAbsensi) {
        Absensi absensi;
        absensi = new Absensi(LocalDateTime.now(), this, "Hadir");
        daftarAbsensi.tambahAbsensi(absensi);
    }

    @Override
    public void izin(DaftarAbsensi daftarAbsensi, String alasan) {
        Absensi absensi;
        absensi = new Absensi(LocalDateTime.now(), this, "Izin");
        absensi.setAlasan(alasan);
        daftarAbsensi.tambahAbsensi(absensi);
    }
}
