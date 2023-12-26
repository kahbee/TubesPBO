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
public class Absensi {
    private int id;
    private LocalDateTime waktu;
    private Karyawan karyawan;
    private String status;
    private String alasan;

    public Absensi(LocalDateTime waktu, Karyawan karyawan, String status) {
        this.waktu = waktu;
        this.karyawan = karyawan;
        this.status = status;
    }
    
    public String getStatus() {
        return status;
    }

    public String getAlasan() {
        return alasan;
    }

    public void setAlasan(String alasan) {
        this.alasan = alasan;
    }    

    public LocalDateTime getWaktu() {
        return waktu;
    }

     public void cekWaktu() {
        // Implementasi logika untuk mengecek waktu absensi, bisa ditambahkan aturan bisnis sesuai kebutuhan
        System.out.println("Waktu absensi: " + waktu);
    }

    
    
}
