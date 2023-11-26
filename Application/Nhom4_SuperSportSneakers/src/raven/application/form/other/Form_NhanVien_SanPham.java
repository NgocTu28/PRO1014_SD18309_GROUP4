/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package raven.application.form.other;

import Impl.giayChiTiet_Impl;
import Model.KichThuoc;
import Model.MauSac;
import Model.SanPham;
import Model.SanPhamChiTiet;
import Model.ThuongHieu;
import Repository.KichThuoc_Repository;
import Repository.MauSac_Reponsitory;
import Repository.SanPhamCT_Repository;
import Repository.SanPham_Repository;
import Repository.ThuongHieu_Repository;
import com.github.sarxos.webcam.Webcam;
import com.github.sarxos.webcam.WebcamPanel;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.LuminanceSource;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.NotFoundException;
import com.google.zxing.Result;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.HybridBinarizer;
import java.awt.Dimension;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;
import static raven.application.form.other.Form_SanPhamChiTiet.spct;
import raven.toast.Notifications;

/**
 *
 * @author vutu8
 */
public class Form_NhanVien_SanPham extends javax.swing.JPanel {

    SanPham_Repository sanPham_Repository = new SanPham_Repository();
    public static SanPhamChiTiet spct = new SanPhamChiTiet();
    MauSac_Reponsitory mauSac_Reponsitory = new MauSac_Reponsitory();
    KichThuoc_Repository kichThuoc_Repository = new KichThuoc_Repository();
    ThuongHieu_Repository hieu_Repository = new ThuongHieu_Repository();
    DefaultTableModel tblModel = new DefaultTableModel();
    List<SanPhamChiTiet> listSanPhamChiTiet = new ArrayList<>();
    private static SanPhamCT_Repository sanPhamCT_Repository = new SanPhamCT_Repository();
    private static int page = 1;
    private static int gioiHanPage = (int) ((Math.ceil(sanPhamCT_Repository.getRowCount() / 4)) + 1);
    private static giayChiTiet_Impl chiTiet_Impl = new giayChiTiet_Impl();
    private DefaultComboBoxModel model = new DefaultComboBoxModel();
    private DefaultComboBoxModel model1 = new DefaultComboBoxModel();
    private DefaultComboBoxModel model2 = new DefaultComboBoxModel();
    private DefaultComboBoxModel model3 = new DefaultComboBoxModel();
    JPanel jpnWebcam = new JPanel();
    JDialog dlScanQr = new JDialog();  // Hoặc khởi tạo theo cách phù hợp

    //Scan QR
    private Webcam webcam;
    private WebcamPanel webcamPanel;
    private Thread thread;

    public Form_NhanVien_SanPham() {
        initComponents();
        listSanPhamChiTiet = sanPhamCT_Repository.get(page, 4);
        fillToTable(listSanPhamChiTiet);
    }

    public void fillToTable(List<SanPhamChiTiet> listSanPhamChiTiet) {
        tblModel = (DefaultTableModel) tblSanPhamCT.getModel();
        tblModel.setRowCount(0);
        int n = 1;
        for (SanPhamChiTiet i : listSanPhamChiTiet) {
            Object[] row = new Object[13];
            row[0] = n++;
            row[1] = i.getMaSPCT();
            row[2] = i.getIdSanPham().getTenSanpham();
            row[3] = i.getIdThuongHieu().getTenThuongHieu();
            row[4] = i.getIdKichThuoc().getTenSize();
            row[5] = i.getIdMau().getTenMau();
            row[6] = i.getGiaBan();
            row[7] = i.getGiaNiemYet();
            row[8] = i.getTrangThai() == 0 ? "CÒN HÀNG" : (i.getTrangThai() == 1 ? "TẠM HẾT" : "DỪNG BÁN");
            row[9] = i.getMoTa();
            row[10] = i.getSoLuong();
            tblModel.addRow(row);
        }
    }

    public void fillToCboTenSP() {
        model = (DefaultComboBoxModel) this.cbo_TenSP.getModel();
        List<SanPham> list = sanPham_Repository.getToAllSanPham();
        for (SanPham sanPham : list) {
            model.addElement(sanPham);
        }
    }

    public void fillToCboMau() {
        model1 = (DefaultComboBoxModel) this.cboMauSac.getModel();
        List<MauSac> list = mauSac_Reponsitory.getToAll();
        for (MauSac ms : list) {
            model1.addElement(ms);
        }
    }

    public void fillToCboKichThuoc() {
        model2 = (DefaultComboBoxModel) this.cboKichThuoc.getModel();
        List<KichThuoc> list = kichThuoc_Repository.getToAllKichThuoc();
        for (KichThuoc kt : list) {
            model2.addElement(kt);
        }
    }

    public void fillToCboThuongHieu() {
        model3 = (DefaultComboBoxModel) this.cboHang.getModel();
        List<ThuongHieu> list = hieu_Repository.getToAll();
        for (ThuongHieu th : list) {
            model3.addElement(th);
        }
    }

    void init() {
        //page là số trang cần hiển thị
        // limt = 4 : là số bản ghi từng trang
        ///fillToTableKH(service.get(page, 4));
        fillToTable(sanPhamCT_Repository.get(page, 4));
        int gioiHanPage = (int) ((Math.ceil(sanPhamCT_Repository.getRowCount() / 4)) + 1);
        soTrang.setText(page + " / " + gioiHanPage);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel4 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblSanPhamCT = new javax.swing.JTable();
        jLabel9 = new javax.swing.JLabel();
        txtSearch = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        cboLocTrangThai = new javax.swing.JComboBox<>();
        jButton13 = new javax.swing.JButton();
        btnLui = new javax.swing.JButton();
        btnNext = new javax.swing.JButton();
        jButton16 = new javax.swing.JButton();
        soTrang = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        lb = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        cbo_TenSP = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        txt_MaSPCT = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtSoLuong = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtGiaBan = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        cboMauSac = new javax.swing.JComboBox<>();
        jLabel7 = new javax.swing.JLabel();
        cboKichThuoc = new javax.swing.JComboBox<>();
        jLabel8 = new javax.swing.JLabel();
        cboHang = new javax.swing.JComboBox<>();
        jScrollPane3 = new javax.swing.JScrollPane();
        txtMoTa = new javax.swing.JTextArea();
        jLabel11 = new javax.swing.JLabel();
        txtGiaBan1 = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        cboTrangThai = new javax.swing.JComboBox<>();

        jPanel4.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        tblSanPhamCT.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "STT", "Mã SPCT", "Tên Giày", "Hãng", "Size", "Màu Sắc", "Giá Bán", "Giá Niêm Yết", "Trạng Thái", "Mô Tả", "Số Lượng"
            }
        ));
        tblSanPhamCT.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblSanPhamCTMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tblSanPhamCT);

        jLabel9.setFont(new java.awt.Font("Segoe UI", 3, 14)); // NOI18N
        jLabel9.setText("Tìm Kiếm");

        txtSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSearchActionPerformed(evt);
            }
        });

        jLabel13.setFont(new java.awt.Font("Segoe UI", 3, 14)); // NOI18N
        jLabel13.setText("Lọc Trạng Thái");

        cboLocTrangThai.setFont(new java.awt.Font("Segoe UI", 3, 12)); // NOI18N
        cboLocTrangThai.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Còn Hàng", "Tạm Hết", "Dừng Bán" }));
        cboLocTrangThai.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboLocTrangThaiActionPerformed(evt);
            }
        });

        jButton13.setFont(new java.awt.Font("Segoe UI", 3, 18)); // NOI18N
        jButton13.setText("<<");
        jButton13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton13ActionPerformed(evt);
            }
        });

        btnLui.setFont(new java.awt.Font("Segoe UI", 3, 18)); // NOI18N
        btnLui.setText("<");
        btnLui.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLuiActionPerformed(evt);
            }
        });

        btnNext.setFont(new java.awt.Font("Segoe UI", 3, 18)); // NOI18N
        btnNext.setText(">");
        btnNext.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNextActionPerformed(evt);
            }
        });

        jButton16.setFont(new java.awt.Font("Segoe UI", 3, 18)); // NOI18N
        jButton16.setText(">>");
        jButton16.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton16ActionPerformed(evt);
            }
        });

        soTrang.setText("1");

        jLabel10.setFont(new java.awt.Font("Segoe UI", 3, 24)); // NOI18N
        jLabel10.setText("Danh sách sản phẩm chi tiết");

        jButton1.setFont(new java.awt.Font("Segoe UI", 3, 14)); // NOI18N
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/qr-code_9460284.png"))); // NOI18N
        jButton1.setText("SCAN QR");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addComponent(jScrollPane2))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(289, 289, 289)
                        .addComponent(jButton13)
                        .addGap(18, 18, 18)
                        .addComponent(btnLui, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(soTrang, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnNext)
                        .addGap(18, 18, 18)
                        .addComponent(jButton16, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addComponent(jLabel10)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(38, 38, 38)
                .addComponent(jLabel9)
                .addGap(18, 18, 18)
                .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 276, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 152, Short.MAX_VALUE)
                .addComponent(jLabel13)
                .addGap(18, 18, 18)
                .addComponent(cboLocTrangThai, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(24, 24, 24))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel10)
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13)
                    .addComponent(cboLocTrangThai, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(soTrang, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jButton13, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnLui, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnNext, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButton16, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );

        lb.setFont(new java.awt.Font("Segoe UI", 3, 24)); // NOI18N
        lb.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lb.setText("Thông Tin Giày Snaker");

        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel1.setFont(new java.awt.Font("Sitka Display", 3, 18)); // NOI18N
        jLabel1.setText("Tên Giày");

        cbo_TenSP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbo_TenSPActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Sitka Display", 3, 18)); // NOI18N
        jLabel2.setText("Mã SPCT");

        txt_MaSPCT.setEnabled(false);

        jLabel3.setFont(new java.awt.Font("Sitka Display", 3, 18)); // NOI18N
        jLabel3.setText("Số Lượng");

        jLabel4.setFont(new java.awt.Font("Sitka Display", 3, 18)); // NOI18N
        jLabel4.setText("Giá Bán");

        jLabel5.setFont(new java.awt.Font("Sitka Display", 3, 18)); // NOI18N
        jLabel5.setText("Mô Tả");

        jLabel6.setFont(new java.awt.Font("Sitka Display", 3, 18)); // NOI18N
        jLabel6.setText("Màu Sắc");

        jLabel7.setFont(new java.awt.Font("Sitka Display", 3, 18)); // NOI18N
        jLabel7.setText("Kích Thước");

        jLabel8.setFont(new java.awt.Font("Sitka Display", 3, 18)); // NOI18N
        jLabel8.setText("Hãng");

        txtMoTa.setColumns(20);
        txtMoTa.setRows(5);
        jScrollPane3.setViewportView(txtMoTa);

        jLabel11.setFont(new java.awt.Font("Sitka Display", 3, 18)); // NOI18N
        jLabel11.setText("Giá Niêm Yết");

        jLabel12.setFont(new java.awt.Font("Sitka Display", 3, 18)); // NOI18N
        jLabel12.setText("Trạng Thái");

        cboTrangThai.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Còn Hàng", "Tạm Hết", "Dừng Bán" }));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel12, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txtGiaBan1, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtGiaBan, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtSoLuong, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txt_MaSPCT, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cboTrangThai, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cbo_TenSP, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(jPanel2Layout.createSequentialGroup()
                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 257, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                            .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(cboHang, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(cboMauSac, javax.swing.GroupLayout.PREFERRED_SIZE, 363, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(cboKichThuoc, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addContainerGap())))))
        );

        jPanel2Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {cboHang, cboKichThuoc, cboMauSac, jScrollPane3});

        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(cboMauSac, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7)
                            .addComponent(cboKichThuoc, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel8)
                            .addComponent(cboHang, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cbo_TenSP, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(txt_MaSPCT, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtSoLuong, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtGiaBan, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4)
                            .addComponent(jLabel5))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel11)
                            .addComponent(txtGiaBan1, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(36, 36, 36)
                                .addComponent(jLabel12))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                .addGap(26, 26, 26)
                                .addComponent(cboTrangThai, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(19, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(lb, javax.swing.GroupLayout.PREFERRED_SIZE, 480, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(303, 303, 303)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lb)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, Short.MAX_VALUE)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void tblSanPhamCTMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblSanPhamCTMouseClicked
        int index = tblSanPhamCT.getSelectedRow();
        if (index < 0) {
            Notifications.getInstance().show(Notifications.Type.INFO, Notifications.Location.TOP_CENTER, "Vui lòng chọn dòng trên bảng.");
            return;
        } else {
            String nameShoes = (String) tblSanPhamCT.getValueAt(index, 2).toString();
            model.setSelectedItem(nameShoes);

            String mauSac = (String) tblSanPhamCT.getValueAt(index, 5);
            model1.setSelectedItem(mauSac);

            String kichThuoc = (String) tblSanPhamCT.getValueAt(index, 4).toString();
            model2.setSelectedItem(kichThuoc);

            String thuongHieu = (String) tblSanPhamCT.getValueAt(index, 3);
            model3.setSelectedItem(thuongHieu);

            String maSPCT = (String) tblSanPhamCT.getValueAt(index, 1);
            txt_MaSPCT.setText(maSPCT);

            txtSoLuong.setText((String) tblSanPhamCT.getValueAt(index, 10).toString());

            txtGiaBan.setText((String) tblSanPhamCT.getValueAt(index, 6).toString());

            txtGiaBan1.setText((String) tblSanPhamCT.getValueAt(index, 7).toString());

            txtMoTa.setText((String) tblSanPhamCT.getValueAt(index, 9).toString());

            if (tblSanPhamCT.getValueAt(index, 8).equals("CÒN HÀNG")) {
                cboTrangThai.setSelectedIndex(0);
            } else if (tblSanPhamCT.getValueAt(index, 8).equals("TẠM HẾT")) {
                cboTrangThai.setSelectedIndex(1);
            } else if (tblSanPhamCT.getValueAt(index, 8).equals("DỪNG BÁN")) {
                cboTrangThai.setSelectedIndex(2);
            }
        }
    }//GEN-LAST:event_tblSanPhamCTMouseClicked

    private void txtSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSearchActionPerformed
        List<SanPhamChiTiet> listSearch = sanPhamCT_Repository.search_SanPhamChiTiet(txtSearch.getText().trim());
        System.out.println(listSearch);
        fillToTable(listSearch);
    }//GEN-LAST:event_txtSearchActionPerformed

    private void cboLocTrangThaiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboLocTrangThaiActionPerformed
        if (cboLocTrangThai.getSelectedIndex() == 0) {
            List<SanPhamChiTiet> listSearch = sanPhamCT_Repository.searchTrangThai_SanPhamChiTiet(0);
            fillToTable(listSearch);
        } else if (cboLocTrangThai.getSelectedIndex() == 1) {
            List<SanPhamChiTiet> listSearch = sanPhamCT_Repository.searchTrangThai_SanPhamChiTiet(1);
            fillToTable(listSearch);
        } else if (cboLocTrangThai.getSelectedIndex() == 2) {
            List<SanPhamChiTiet> listSearch = sanPhamCT_Repository.searchTrangThai_SanPhamChiTiet(2);
            fillToTable(listSearch);
        }
    }//GEN-LAST:event_cboLocTrangThaiActionPerformed

    private void jButton13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton13ActionPerformed
        fillToTable(sanPhamCT_Repository.get(1, 4));
    }//GEN-LAST:event_jButton13ActionPerformed

    private void btnLuiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLuiActionPerformed
        page--;
        if (page >= 1) {
            soTrang.setText(page + " / " + gioiHanPage);
            fillToTable(sanPhamCT_Repository.get(page, 4));
        } else {
            page = 1;
        }
    }//GEN-LAST:event_btnLuiActionPerformed

    private void btnNextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNextActionPerformed
        page++;
        if (page <= gioiHanPage) {
            fillToTable(sanPhamCT_Repository.get(page, 4));
            soTrang.setText(page + " / " + gioiHanPage);
            return;
        }
        page = gioiHanPage;
    }//GEN-LAST:event_btnNextActionPerformed

    private void jButton16ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton16ActionPerformed
        fillToTable(sanPhamCT_Repository.get(gioiHanPage, 4));
    }//GEN-LAST:event_jButton16ActionPerformed
    private void initWebcam() {
        Dimension d = new Dimension(100, 100);
        webcam = Webcam.getWebcams().get(0);
        webcam.setCustomViewSizes(new Dimension[]{d});
        webcam.setViewSize(d);

        webcamPanel = new WebcamPanel(webcam);
        webcamPanel.setPreferredSize(d);
        webcamPanel.setFPSDisplayed(true);
        webcamPanel.setVisible(true);
        webcamPanel.setDisplayDebugInfo(true);
        webcamPanel.setImageSizeDisplayed(true);
        webcamPanel.setMirrored(true);
        if (jpnWebcam != null && jpnWebcam.getParent() != null) {
            SwingUtilities.invokeLater(new Runnable() {
                @Override
                public void run() {
                    jpnWebcam.getParent().revalidate();
                    jpnWebcam.getParent().repaint();
                }
            });
        }
        dlScanQr.add(webcamPanel);

    }

    private void captureThread() {
        thread = new Thread() {
            @Override
            public void run() {
                do {
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException ex) {
                        ex.printStackTrace();
                    }

                    Result result = null;
                    BufferedImage image = null;

                    if (webcam.isOpen()) {
                        if ((image = webcam.getImage()) == null) {
                            continue;
                        }
                    }
                    LuminanceSource source = new BufferedImageLuminanceSource(image);
                    BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(source));
                    try {
                        result = new MultiFormatReader().decode(bitmap);
                    } catch (NotFoundException ex) {
                        ex.printStackTrace();
                        continue;
                    }
                    if (result != null) {
                        String resultText = result.getText();
                        String[] arrResult = resultText.split("\\n");
                        txtSearch.setText(arrResult[1].substring(10));
                        dlScanQr.setVisible(false);
                        searchSanPham();
                        webcam.close();
                        thread.stop();
                    }

                } while (true);
            }
        };
        thread.setDaemon(true);
        thread.start();
    }

    private void searchSanPham() {
        String keyWord = (String) txtSearch.getText();
        if (keyWord.isEmpty() || keyWord == null) {
            return;
        }
        SanPhamChiTiet result = (SanPhamChiTiet) sanPhamCT_Repository.search_SanPhamChiTiet(keyWord);
        if (result == null) {
            JOptionPane.showMessageDialog(this, "Không tìm thấy");
            return;
        }

        cbo_TenSP.setSelectedItem(result.getIdSanPham().getTenSanpham());
        txt_MaSPCT.setText(result.getMaSPCT());
        String soLuong = String.valueOf(result.getSoLuong());
        txtSoLuong.setText(soLuong);
        String giaBan = result.getGiaBan().toString();
        txtGiaBan.setText(giaBan);
        String giaBan1 = result.getGiaNiemYet().toString();
        txtGiaBan1.setText(giaBan1);
        if (result.getTrangThai() == 0) {
            cboTrangThai.setSelectedIndex(0);
        } else if (result.getTrangThai() == 1) {
            cboTrangThai.setSelectedIndex(1);
        } else if (result.getTrangThai() == 2) {
            cboTrangThai.setSelectedIndex(2);
        }
        cboMauSac.setSelectedItem(result.getIdMau().getTenMau());
        cboKichThuoc.setSelectedItem(result.getIdKichThuoc().getTenSize());
        cboHang.setSelectedItem(result.getIdThuongHieu().getTenThuongHieu());
        txtMoTa.setText(result.getMoTa());
        spct = result;

    }

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // BTN Scan QR
        if (webcam != null) {
            if (webcam.isOpen()) {
                webcam.close();
                thread.stop();
                dlScanQr.setVisible(false);
            }
        }
        initWebcam();
        captureThread();
        dlScanQr.setVisible(true);
        dlScanQr.setSize(500, 500);
        dlScanQr.setLocationRelativeTo(null);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void cbo_TenSPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbo_TenSPActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbo_TenSPActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnLui;
    private javax.swing.JButton btnNext;
    private javax.swing.JComboBox<String> cboHang;
    private javax.swing.JComboBox<String> cboKichThuoc;
    private javax.swing.JComboBox<String> cboLocTrangThai;
    private javax.swing.JComboBox<String> cboMauSac;
    private javax.swing.JComboBox<String> cboTrangThai;
    private javax.swing.JComboBox<String> cbo_TenSP;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton13;
    private javax.swing.JButton jButton16;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JLabel lb;
    private javax.swing.JLabel soTrang;
    private javax.swing.JTable tblSanPhamCT;
    private javax.swing.JTextField txtGiaBan;
    private javax.swing.JTextField txtGiaBan1;
    private javax.swing.JTextArea txtMoTa;
    private javax.swing.JTextField txtSearch;
    private javax.swing.JTextField txtSoLuong;
    private javax.swing.JTextField txt_MaSPCT;
    // End of variables declaration//GEN-END:variables
}
