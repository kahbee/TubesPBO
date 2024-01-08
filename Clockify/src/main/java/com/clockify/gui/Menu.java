package com.clockify.gui;

import com.clockify.clockify.Database;
import com.clockify.model.Administrasi;
import com.clockify.model.Departemen;
import com.clockify.model.Karyawan;
import com.clockify.model.Manager;
import com.clockify.model.PekerjaBiasa;
import com.clockify.model.Teknisi;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.regex.Pattern;

import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
import javax.swing.RowFilter;

import javax.swing.Timer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author khusyasy
 */
public class Menu extends javax.swing.JFrame {
    private Database db = new Database();
    private final Karyawan user;
    
    private ArrayList<Departemen> DepList = new ArrayList<>();
    private Departemen DepSel = null;
    
    private ArrayList<Karyawan> KarList = new ArrayList<>();
    private Karyawan KarSel = null;
    
    private ArrayList<Absensi> AbList = new ArrayList<>();
    private Absensi AbSel = null;
    DefaultTableModel TAbsensiModel = new DefaultTableModel(new String[] {"Tanggal", "Jam", "Nama", "Posisi", "Status", "Alasan"}, 0);


    /**
     * Creates new form Menu
     * @param user
     */
    public Menu(Karyawan user) {
        this.user = user;
        initComponents();
        setupDisplay();
        loadDepartemen();
        loadKaryawan();
        loadAbsensi();
    }

    private void setupDisplay() {
        LBNama.setText(user.getNama());
        
        Timer timer = new Timer(1000, (ActionEvent e) -> {
            LBJam.setText(new SimpleDateFormat("HH:mm:ss").format(new Date()));
        });
        LBJam.setText(new SimpleDateFormat("HH:mm:ss").format(new Date()));
        timer.start();
        
        LBTanggal.setText(new SimpleDateFormat("EEEE, dd MMMM yyyy").format(new Date()));
    }

    public void loadDepartemen() {
        DepList.clear();
        DefaultListModel<Departemen> LDepModel = new DefaultListModel<>();
        try {
            ResultSet rs = db.executeQuery("SELECT id, nama FROM departemen");
            while (rs.next()) {
                Departemen dep = new Departemen(rs.getInt("id"), rs.getString("nama"));
                DepList.add(dep);
                LDepModel.addElement(dep);
            }
            LDep.setModel(LDepModel);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public void loadKaryawan() {
        KarList.clear();
        DefaultTableModel TKarModel = new DefaultTableModel(new String[] {"Nama", "JK", "HP", "Email", "Departemen", "Posisi"}, 0);
        try {
            ResultSet rs = db.executeQuery("SELECT k.id, k.nama, k.jenisKelamin, k.noHP, k.email, d.nama, k.posisi, d.id FROM karyawan k JOIN departemen d ON k.id_departemen = d.id");
            while (rs.next()) {
                String[] row = new String[]{rs.getString("k.nama"), rs.getString("k.jenisKelamin"), rs.getString("k.noHP"), rs.getString("k.email"), rs.getString("d.nama"), rs.getString("k.posisi")};
                Karyawan k;
                Departemen d = new Departemen(rs.getInt("d.id"), rs.getString("d.nama"));
                if (row[5].equalsIgnoreCase("administrasi")) {
                    k =  new Administrasi(rs.getInt("k.id"), row[0], row[1], row[3], row[2], d);
                } else if (row[5].equalsIgnoreCase("teknisi")) {
                    k =  new Teknisi(rs.getInt("k.id"), row[0], row[1], row[3], row[2], d);
                } else if (row[5].equalsIgnoreCase("manager")) {
                    k =  new Manager(rs.getInt("k.id"), row[0], row[1], row[3], row[2], d);
                } else {
                    k =  new PekerjaBiasa(rs.getInt("k.id"), row[0], row[1], row[3], row[2], d);
                }
                KarList.add(k);
                TKarModel.addRow(row);
            }
            TKar.setModel(TKarModel);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void loadAbsensi() {
        TAbsensiModel.setRowCount(0);
        try {
            ResultSet rs = db.executeQuery("SELECT a.waktu, k.nama, k.posisi, a.status, a.alasan FROM absensi a JOIN karyawan k ON a.id_karyawan = k.id");
            while (rs.next()) {
                String[] row = new String[]{new SimpleDateFormat("dd MMMM yyyy").format(rs.getDate("a.waktu")), new SimpleDateFormat("HH:mm:ss").format(rs.getTime("a.waktu")), rs.getString("k.nama"), rs.getString("k.posisi"), rs.getString("a.status"), rs.getString("a.alasan")};
                // Costructor public Absensi(LocalDateTime waktu, Karyawan karyawan, String status, String Alasan)
                // Conatructor public Karyawan(int id, String nama, String jenisKelamin, String email, String noHP, Departemen departemen)
                
                TAbsensiModel.addRow(row);
            }
            THis.setModel(TAbsensiModel);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        labelNama = new javax.swing.JLabel();
        labelTanggal = new javax.swing.JLabel();
        BTHadir = new javax.swing.JButton();
        BTIzin = new javax.swing.JButton();
        LBNama = new javax.swing.JLabel();
        LBTanggal = new javax.swing.JLabel();
        LBJam = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        LDep = new javax.swing.JList<Departemen>();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        TFDepID = new javax.swing.JTextField();
        BTDepAdd = new javax.swing.JButton();
        TFDepNama = new javax.swing.JTextField();
        BTDepDelete = new javax.swing.JButton();
        BTDepEdit = new javax.swing.JButton();
        BTDepCancel = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        TKar = new javax.swing.JTable();
        BTKarAdd = new javax.swing.JButton();
        BTKarEdit = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        TFHis = new javax.swing.JTextField();
        BTSearch = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        THis = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        labelNama.setText("Nama");

        labelTanggal.setText("Tanggal");

        BTHadir.setText("Hadir");
        BTHadir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BTHadirActionPerformed(evt);
            }
        });

        BTIzin.setText("Izin");
        BTIzin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BTIzinActionPerformed(evt);
            }
        });

        LBNama.setText("Nama");

        LBTanggal.setText("Tanggal");

        LBJam.setFont(new java.awt.Font("Segoe UI", 1, 48)); // NOI18N
        LBJam.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        LBJam.setText("JAM");
        LBJam.setToolTipText("");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(LBJam, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(169, 169, 169)
                .addComponent(BTIzin)
                .addGap(18, 18, 18)
                .addComponent(BTHadir)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(157, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(labelTanggal)
                    .addComponent(labelNama, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(26, 26, 26)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(LBTanggal, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(LBNama, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(112, 112, 112))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(45, 45, 45)
                .addComponent(LBJam, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(LBNama)
                    .addComponent(labelNama))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelTanggal)
                    .addComponent(LBTanggal))
                .addGap(52, 52, 52)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(BTIzin)
                    .addComponent(BTHadir))
                .addContainerGap(95, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Absensi", jPanel1);

        LDep.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                LDepValueChanged(evt);
            }
        });
        jScrollPane2.setViewportView(LDep);

        jLabel1.setText("ID");

        jLabel2.setText("Nama");

        TFDepID.setEditable(false);
        TFDepID.setForeground(new java.awt.Color(102, 102, 102));

        BTDepAdd.setText("Add");
        BTDepAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BTDepAddActionPerformed(evt);
            }
        });

        BTDepDelete.setText("Delete");
        BTDepDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BTDepDeleteActionPerformed(evt);
            }
        });

        BTDepEdit.setText("Edit");
        BTDepEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BTDepEditActionPerformed(evt);
            }
        });

        BTDepCancel.setText("Cancel");
        BTDepCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BTDepCancelActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(BTDepAdd)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(BTDepEdit))
                    .addComponent(TFDepID, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(TFDepNama, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(BTDepCancel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(BTDepDelete)))
                .addContainerGap(73, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(49, 49, 49)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(TFDepID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(TFDepNama, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(BTDepAdd)
                    .addComponent(BTDepEdit))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(BTDepCancel)
                    .addComponent(BTDepDelete))
                .addContainerGap(173, Short.MAX_VALUE))
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2)
                .addContainerGap())
        );

        jTabbedPane1.addTab("Departemen", jPanel4);

        TKar.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Nama", "Jenis Kelamin", "no Hp", "Email", "Departemen", "Posisi"
            }
        ));
        TKar.setColumnSelectionAllowed(true);
        jScrollPane1.setViewportView(TKar);
        TKar.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);

        BTKarAdd.setText("Add");
        BTKarAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BTKarAddActionPerformed(evt);
            }
        });

        BTKarEdit.setText("Edit");
        BTKarEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BTKarEditActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 406, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(BTKarAdd)
                    .addComponent(BTKarEdit))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 342, Short.MAX_VALUE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(9, 9, 9)
                        .addComponent(BTKarAdd)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(BTKarEdit)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );

        jTabbedPane1.addTab("Karyawan", jPanel2);

        BTSearch.setText("Search");
        BTSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BTSearchActionPerformed(evt);
            }
        });

        THis.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Tanggal", "Jam", "Nama", "Posisi", "Status", "Alasan"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane3.setViewportView(THis);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(TFHis, javax.swing.GroupLayout.PREFERRED_SIZE, 362, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(BTSearch))
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 452, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(28, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(TFHis, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BTSearch))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 271, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(25, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("History", jPanel3);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 389, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void BTIzinActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTIzinActionPerformed
        String alasan = JOptionPane.showInputDialog(this, "Alasan", "Izin", JOptionPane.QUESTION_MESSAGE);
        if (alasan == null || alasan.isEmpty() || alasan.isBlank()) {
            return;
        }
        try {
            db.executeUpdate("INSERT INTO absensi (waktu, id_karyawan, status, alasan) VALUES (?, ?, ?, ?)", new Date(), user.getId(), "izin", alasan);
            JOptionPane.showMessageDialog(this, "Izin berhasil dikirim", "Absensi", JOptionPane.INFORMATION_MESSAGE);
            loadAbsensi();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
        
    }//GEN-LAST:event_BTIzinActionPerformed

    private void BTHadirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTHadirActionPerformed
        try {
            db.executeUpdate("INSERT INTO absensi (waktu, id_karyawan, status) VALUES (?, ?, ?)", new Date(), user.getId(), "hadir");
            JOptionPane.showMessageDialog(this, "Absensi berhasil dikirim", "Absensi", JOptionPane.INFORMATION_MESSAGE);
            loadAbsensi();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_BTHadirActionPerformed

    private void clearDepInput() {
        TFDepID.setText("");
        TFDepNama.setText("");
    }
    
    private void LDepValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_LDepValueChanged
        DepSel = LDep.getSelectedValue();
        if (DepSel != null) {
            TFDepID.setText(String.valueOf(DepSel.getId()));
            TFDepNama.setText(DepSel.getNama());
        } else {
            clearDepInput();
        }
    }//GEN-LAST:event_LDepValueChanged

    private void BTDepCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTDepCancelActionPerformed
        LDep.setSelectedIndex(-1);
        DepSel = null;
        clearDepInput();
    }//GEN-LAST:event_BTDepCancelActionPerformed

    private void BTDepAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTDepAddActionPerformed
        if (DepSel == null) {
            String nama = TFDepNama.getText();
            if (nama == null || nama.isEmpty() || nama.isBlank()) {
                JOptionPane.showMessageDialog(this, "Nama departemen tidak boleh kosong", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            try {
                db.executeUpdate("INSERT INTO departemen (nama) VALUES (?)", nama);
                JOptionPane.showMessageDialog(this, "Departemen berhasil ditambahkan", "Departemen", JOptionPane.INFORMATION_MESSAGE);
                loadDepartemen();
                clearDepInput();
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_BTDepAddActionPerformed

    private void BTDepEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTDepEditActionPerformed
        if (DepSel != null) {
            String nama = TFDepNama.getText();
            if (nama == null || nama.isEmpty() || nama.isBlank()) {
                JOptionPane.showMessageDialog(this, "Nama departemen tidak boleh kosong", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            try {
                db.executeUpdate("UPDATE departemen SET nama = ? WHERE id = ?", nama, DepSel.getId());
                JOptionPane.showMessageDialog(this, "Departemen berhasil diubah", "Departemen", JOptionPane.INFORMATION_MESSAGE);
                loadDepartemen();
                clearDepInput();
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_BTDepEditActionPerformed

    private void BTDepDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTDepDeleteActionPerformed
        if (DepSel != null) {
            boolean confirm = JOptionPane.showConfirmDialog(this, "Apakah anda yakin ingin menghapus departemen " + DepSel.getNama() + "?", "Hapus Departemen", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION;
            if (!confirm) {
                return;
            }
            try {
                db.executeUpdate("DELETE FROM departemen WHERE id = ?", DepSel.getId());
                JOptionPane.showMessageDialog(this, "Departemen berhasil dihapus", "Departemen", JOptionPane.INFORMATION_MESSAGE);
                loadDepartemen();
                clearDepInput();
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_BTDepDeleteActionPerformed

    private void BTKarAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTKarAddActionPerformed
        FormKaryawan form = new FormKaryawan(this, true, db, null, DepList);
        form.setLocationRelativeTo(this);
        form.setVisible(true);
    }//GEN-LAST:event_BTKarAddActionPerformed

    private void BTKarEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTKarEditActionPerformed
        int idx = TKar.getSelectedRow();
        if (idx >= 0) {
            Karyawan k = KarList.get(idx);
            FormKaryawan form = new FormKaryawan(this, true, db, k, DepList);
            form.setLocationRelativeTo(this);
            form.setVisible(true);
        }
    }//GEN-LAST:event_BTKarEditActionPerformed

    private void BTSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTSearchActionPerformed
        // Mendapatkan teks pencarian
        String searchText = TFHis.getText();

        // Konversi teks pencarian menjadi regex untuk pencarian yang lebih fleksibel
        String regex = ".*" + Pattern.quote(searchText) + ".*";

        DefaultTableModel THisModel = new DefaultTableModel(new String[]{"Tanggal", "Jam", "Nama", "Posisi", "Status", "Alasan"}, 0);

        for (int i = 0; i < TAbsensiModel.getRowCount(); i++) {
            Object[] rowData = new Object[6]; // Sesuaikan dengan jumlah kolom
            for (int j = 0; j < TAbsensiModel.getColumnCount(); j++) {
                rowData[j] = TAbsensiModel.getValueAt(i, j);
            }

            // Cek apakah data di baris saat ini sesuai dengan kriteria pencarian
            if (Pattern.matches(regex, rowData[0].toString()) || 
                Pattern.matches(regex, rowData[1].toString()) || 
                Pattern.matches(regex, rowData[2].toString()) || 
                Pattern.matches(regex, rowData[3].toString()) || 
                Pattern.matches(regex, rowData[4].toString()) || 
                rowData[5] != null && Pattern.matches(regex, rowData[5].toString())) {
                THisModel.addRow(rowData);
            }
        }

        THis.setModel(THisModel);
    }//GEN-LAST:event_BTSearchActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BTDepAdd;
    private javax.swing.JButton BTDepCancel;
    private javax.swing.JButton BTDepDelete;
    private javax.swing.JButton BTDepEdit;
    private javax.swing.JButton BTHadir;
    private javax.swing.JButton BTIzin;
    private javax.swing.JButton BTKarAdd;
    private javax.swing.JButton BTKarEdit;
    private javax.swing.JButton BTSearch;
    private javax.swing.JLabel LBJam;
    private javax.swing.JLabel LBNama;
    private javax.swing.JLabel LBTanggal;
    private javax.swing.JList<Departemen> LDep;
    private javax.swing.JTextField TFDepID;
    private javax.swing.JTextField TFDepNama;
    private javax.swing.JTextField TFHis;
    private javax.swing.JTable THis;
    private javax.swing.JTable TKar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JLabel labelNama;
    private javax.swing.JLabel labelTanggal;
    // End of variables declaration//GEN-END:variables
}
