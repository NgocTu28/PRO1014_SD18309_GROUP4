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
import com.github.sarxos.webcam.WebcamResolution;
import static com.github.sarxos.webcam.WebcamUtils.capture;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.LuminanceSource;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.NotFoundException;
import com.google.zxing.Result;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.HybridBinarizer;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import javax.management.Notification;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import pro1041.team_3.swing.jnafilechooser.api.JnaFileChooser;
import raven.application.Application;
import raven.toast.Notifications;
import com.github.sarxos.webcam.Webcam;
import com.github.sarxos.webcam.WebcamPanel;
import javax.swing.ImageIcon;

/**
 *
 * @author vutu8
 */
public class Form_SanPhamChiTiet extends javax.swing.JPanel {

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
    private DefaultComboBoxModel model11 = new DefaultComboBoxModel();
    private DefaultComboBoxModel model22 = new DefaultComboBoxModel();
    private DefaultComboBoxModel model33 = new DefaultComboBoxModel();
    private DefaultComboBoxModel modelSP = new DefaultComboBoxModel();

    JPanel jpnWebcam = new JPanel();
    JDialog dlScanQr = new JDialog();  // Hoặc khởi tạo theo cách phù hợp

    //Scan QR
    private Webcam webcam;
    private WebcamPanel webcamPanel;
    private Thread thread;

    public Form_SanPhamChiTiet() {
        listSanPhamChiTiet = sanPhamCT_Repository.get(page, 4);
        initComponents();
        fillToCboTenSP();
        fillToCboMau();
        fillToCboKichThuoc();
        fillToCboThuongHieu();
        fillToCboKichThuoc1();
        fillToCboMau1();
        fillToCboThuongHieu1();
        fillToCboSanPham1();
        fillToTable(listSanPhamChiTiet);
        ImageIcon iconDialog = new ImageIcon("E:\\Fpoly\\Snaker\\SuperSport-Sneakers\\Application\\Nhom4_SuperSportSneakers\\src\\raven\\icon\\png/logo3.png");
        ImageIcon iconDialogThem = new ImageIcon("E:\\Fpoly\\Snaker\\SuperSport-Sneakers\\Application\\Nhom4_SuperSportSneakers\\src\\raven\\icon\\pngaddUser.png");
        dlScanQr.setTitle("Scan QR");
        dlScanQr.setIconImage(iconDialog.getImage());
        initSearch();;
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

    ///////////////////
    public void fillToCboMau1() {
        model11 = (DefaultComboBoxModel) this.cboMauSac1.getModel();
        List<MauSac> list = mauSac_Reponsitory.getToAll();
        for (MauSac ms : list) {
            model11.addElement(ms);
        }
    }

    public void fillToCboKichThuoc1() {
        model22 = (DefaultComboBoxModel) this.cboKichThuoc1.getModel();
        List<KichThuoc> list = kichThuoc_Repository.getToAllKichThuoc();
        for (KichThuoc kt : list) {
            model22.addElement(kt);
        }
    }

    public void fillToCboThuongHieu1() {
        model33 = (DefaultComboBoxModel) this.cboHang1.getModel();
        List<ThuongHieu> list = hieu_Repository.getToAll();
        for (ThuongHieu th : list) {
            model33.addElement(th);
        }
    }

    public void fillToCboSanPham1() {
        modelSP = (DefaultComboBoxModel) this.cbo_TenSP1.getModel();
        List<SanPham> list = sanPham_Repository.getToAllSanPham();
        for (SanPham sanPham : list) {
            modelSP.addElement(sanPham);
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

    private SanPhamChiTiet getSanPhamChiTiet() {
        KichThuoc kt = new KichThuoc();
        if (cboKichThuoc.getSelectedItem() instanceof KichThuoc) {
            kt = (KichThuoc) cboKichThuoc.getSelectedItem();
        }
        MauSac ms = new MauSac();
        if (cboMauSac.getSelectedItem() instanceof MauSac) {
            ms = (MauSac) cboMauSac.getSelectedItem();
        }
        SanPham sp = new SanPham();
        if (cbo_TenSP.getSelectedItem() instanceof SanPham) {
            sp = (SanPham) cbo_TenSP.getSelectedItem();
        }
        ThuongHieu th = new ThuongHieu();
        if (cboHang.getSelectedItem() instanceof ThuongHieu) {
            th = (ThuongHieu) cboHang.getSelectedItem();
        }
        String maSanPhamCT;
        int index = tblSanPhamCT.getSelectedRow();
        if (txt_MaSPCT.getText().isEmpty()) {
            maSanPhamCT = sanPhamCT_Repository.MaTuDongSanPham();
        } else {
            maSanPhamCT = txt_MaSPCT.getText();
        }

        if (txtSoLuong.getText().isEmpty() || txtSoLuong.getText() == null) {
            Notifications.getInstance().show(Notifications.Type.INFO, Notifications.Location.TOP_CENTER, "Số lượng không được để trống.");
            return null;
        }

        int soLuong = 0;
        try {
            soLuong = Integer.parseInt(txtSoLuong.getText());
            if (soLuong < 0) {
                Notifications.getInstance().show(Notifications.Type.INFO, Notifications.Location.TOP_CENTER, "Số lượng phải lớn hơn hoặc bằng 0.");
                return null;
            }
        } catch (Exception e) {
            Notifications.getInstance().show(Notifications.Type.INFO, Notifications.Location.TOP_CENTER, "Số lượng phải là số tự nhiên.");
            return null;
        }

        Float giaBan, giaNiemYet;

        String txtGiaBanValue = txtGiaBan.getText();
        String txtGiaBan1Value = txtGiaBan1.getText();

        if (txtGiaBanValue == null || txtGiaBanValue.isEmpty() || txtGiaBan1Value == null || txtGiaBan1Value.isEmpty()) {
            Notifications.getInstance().show(Notifications.Type.INFO, Notifications.Location.TOP_CENTER, "Giá tiền không được để trống.");
            return null;
        }

        try {
            giaBan = Float.parseFloat(txtGiaBanValue);
            giaNiemYet = Float.parseFloat(txtGiaBan1Value);

            if (giaBan < 0 || giaNiemYet < 0) {
                Notifications.getInstance().show(Notifications.Type.INFO, Notifications.Location.TOP_CENTER, "Giá tiền phải lớn hơn hoặc bằng 0.");
                return null;
            }
        } catch (NumberFormatException e) {
            Notifications.getInstance().show(Notifications.Type.INFO, Notifications.Location.TOP_CENTER, "Giá tiền phải là số thực.");
            return null;
        }

        String moTa = txtMoTa.getText();
        int trangThai;
        if (cboTrangThai.getSelectedIndex() == 0) {
            trangThai = 0;
        } else if (cboTrangThai.getSelectedIndex() == 1) {
            trangThai = 1;
        } else {
            trangThai = 2;
        }

        return new SanPhamChiTiet(maSanPhamCT, soLuong, giaBan, giaNiemYet, trangThai, moTa, ms, kt, th, sp);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        jButton8 = new javax.swing.JButton();
        jButton9 = new javax.swing.JButton();
        jButton10 = new javax.swing.JButton();
        btnClean1 = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
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
        btnAdd_MauSac = new javax.swing.JButton();
        btnAdd_KichThuoc = new javax.swing.JButton();
        btnAdd_Hang = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        txtMoTa = new javax.swing.JTextArea();
        jLabel11 = new javax.swing.JLabel();
        txtGiaBan1 = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        cboTrangThai = new javax.swing.JComboBox<>();
        btnAdd_MauSac1 = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        btnThem = new javax.swing.JButton();
        btnSua = new javax.swing.JButton();
        btnExport = new javax.swing.JButton();
        btnTemplate = new javax.swing.JButton();
        btnImport = new javax.swing.JButton();
        btnQr = new javax.swing.JButton();
        btnClean = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblSanPhamCT = new javax.swing.JTable();
        jButton13 = new javax.swing.JButton();
        btnLui = new javax.swing.JButton();
        btnNext = new javax.swing.JButton();
        jButton16 = new javax.swing.JButton();
        soTrang = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        cboLocTrangThai = new javax.swing.JComboBox<>();
        cboMauSac1 = new javax.swing.JComboBox<>();
        cboKichThuoc1 = new javax.swing.JComboBox<>();
        cboHang1 = new javax.swing.JComboBox<>();
        jLabel17 = new javax.swing.JLabel();
        txtSearch = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        cbo_TenSP1 = new javax.swing.JComboBox<>();
        jLabel10 = new javax.swing.JLabel();

        jButton5.setBackground(new java.awt.Color(0, 0, 0));
        jButton5.setFont(new java.awt.Font("Segoe UI", 2, 14)); // NOI18N
        jButton5.setText("Thêm Sản Phẩm");

        jButton6.setBackground(new java.awt.Color(0, 102, 102));
        jButton6.setFont(new java.awt.Font("Segoe UI", 2, 12)); // NOI18N
        jButton6.setText("Cập Nhật Sản Phẩm");

        jButton7.setBackground(new java.awt.Color(0, 102, 102));
        jButton7.setFont(new java.awt.Font("Segoe UI", 2, 14)); // NOI18N
        jButton7.setText("Làm Mới");

        jButton8.setBackground(new java.awt.Color(0, 102, 102));
        jButton8.setFont(new java.awt.Font("Segoe UI", 2, 14)); // NOI18N
        jButton8.setText("Xuất File Excel");

        jButton9.setBackground(new java.awt.Color(0, 102, 102));
        jButton9.setFont(new java.awt.Font("Segoe UI", 2, 14)); // NOI18N
        jButton9.setText("Tải Mẫu Excel");

        jButton10.setBackground(new java.awt.Color(0, 102, 102));
        jButton10.setFont(new java.awt.Font("Segoe UI", 2, 14)); // NOI18N
        jButton10.setText("Import File Excel");

        btnClean1.setFont(new java.awt.Font("Segoe UI", 3, 14)); // NOI18N
        btnClean1.setText("Làm Mới");
        btnClean1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnClean1ActionPerformed(evt);
            }
        });

        lb.setFont(new java.awt.Font("Segoe UI", 3, 24)); // NOI18N
        lb.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lb.setText("THÔNG TIN GIÀY CHI TIẾT");

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

        btnAdd_MauSac.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/add (2).png"))); // NOI18N
        btnAdd_MauSac.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAdd_MauSacActionPerformed(evt);
            }
        });

        btnAdd_KichThuoc.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/add (2).png"))); // NOI18N
        btnAdd_KichThuoc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAdd_KichThuocActionPerformed(evt);
            }
        });

        btnAdd_Hang.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/add (2).png"))); // NOI18N
        btnAdd_Hang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAdd_HangActionPerformed(evt);
            }
        });

        txtMoTa.setColumns(20);
        txtMoTa.setRows(5);
        jScrollPane3.setViewportView(txtMoTa);

        jLabel11.setFont(new java.awt.Font("Sitka Display", 3, 18)); // NOI18N
        jLabel11.setText("Giá Niêm Yết");

        jLabel12.setFont(new java.awt.Font("Sitka Display", 3, 18)); // NOI18N
        jLabel12.setText("Trạng Thái");

        cboTrangThai.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Còn Hàng", "Tạm Hết", "Dừng Bán" }));

        btnAdd_MauSac1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/add (2).png"))); // NOI18N
        btnAdd_MauSac1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAdd_MauSac1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addContainerGap(12, Short.MAX_VALUE)
                        .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)))
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(txtGiaBan1, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtGiaBan, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtSoLuong, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cbo_TenSP, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txt_MaSPCT, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cboTrangThai, 0, 239, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(btnAdd_MauSac1, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(26, 26, 26)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(35, 35, 35))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)))
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 271, Short.MAX_VALUE)
                    .addComponent(cboKichThuoc, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cboMauSac, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cboHang, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnAdd_Hang, javax.swing.GroupLayout.DEFAULT_SIZE, 49, Short.MAX_VALUE)
                    .addComponent(btnAdd_MauSac, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnAdd_KichThuoc, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(46, Short.MAX_VALUE))
        );

        jPanel2Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {btnAdd_Hang, btnAdd_KichThuoc, btnAdd_MauSac});

        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addGap(10, 10, 10)
                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(cboMauSac, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel6)))
                                    .addComponent(btnAdd_MauSac))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel7)
                                    .addComponent(cboKichThuoc)))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(btnAdd_KichThuoc)))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(cboHang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel8))
                            .addComponent(btnAdd_Hang, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(50, 50, 50))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(4, 4, 4)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(cbo_TenSP, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel1))
                            .addComponent(btnAdd_MauSac1))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(txt_MaSPCT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtSoLuong, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtGiaBan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4))
                        .addGap(19, 19, 19)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel11)
                            .addComponent(txtGiaBan1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cboTrangThai, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel12))
                        .addGap(39, 39, 39))))
        );

        jPanel2Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {btnAdd_Hang, btnAdd_KichThuoc, btnAdd_MauSac});

        jPanel3.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        btnThem.setFont(new java.awt.Font("Segoe UI", 2, 12)); // NOI18N
        btnThem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/raven/icon/png/add.png"))); // NOI18N
        btnThem.setText("ADD");
        btnThem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemActionPerformed(evt);
            }
        });

        btnSua.setFont(new java.awt.Font("Segoe UI", 2, 12)); // NOI18N
        btnSua.setIcon(new javax.swing.ImageIcon(getClass().getResource("/raven/icon/png/update.png"))); // NOI18N
        btnSua.setText("UPDATE");
        btnSua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaActionPerformed(evt);
            }
        });

        btnExport.setFont(new java.awt.Font("Segoe UI", 2, 12)); // NOI18N
        btnExport.setIcon(new javax.swing.ImageIcon(getClass().getResource("/raven/icon/png/xls.png"))); // NOI18N
        btnExport.setText("EXPORT");
        btnExport.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExportActionPerformed(evt);
            }
        });

        btnTemplate.setFont(new java.awt.Font("Segoe UI", 2, 12)); // NOI18N
        btnTemplate.setIcon(new javax.swing.ImageIcon(getClass().getResource("/raven/icon/png/template.png"))); // NOI18N
        btnTemplate.setText("TEAMPLATE");
        btnTemplate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTemplateActionPerformed(evt);
            }
        });

        btnImport.setFont(new java.awt.Font("Segoe UI", 2, 12)); // NOI18N
        btnImport.setIcon(new javax.swing.ImageIcon(getClass().getResource("/raven/icon/png/file-import.png"))); // NOI18N
        btnImport.setText("IMPORT");
        btnImport.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnImportActionPerformed(evt);
            }
        });

        btnQr.setFont(new java.awt.Font("Segoe UI", 2, 12)); // NOI18N
        btnQr.setIcon(new javax.swing.ImageIcon(getClass().getResource("/raven/icon/png/qr-code.png"))); // NOI18N
        btnQr.setText("EXPORT QR");
        btnQr.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnQrActionPerformed(evt);
            }
        });

        btnClean.setFont(new java.awt.Font("Segoe UI", 2, 12)); // NOI18N
        btnClean.setIcon(new javax.swing.ImageIcon(getClass().getResource("/raven/icon/png/clean.png"))); // NOI18N
        btnClean.setText("RESET");
        btnClean.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCleanActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnImport, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btnClean, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnThem, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnExport, javax.swing.GroupLayout.DEFAULT_SIZE, 110, Short.MAX_VALUE)
                            .addComponent(btnSua, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnTemplate, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnQr, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );

        jPanel3Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {btnExport, btnSua, btnTemplate, btnThem});

        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnThem)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnSua)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnTemplate, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnQr)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnExport)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnImport)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnClean)
                .addContainerGap())
        );

        jPanel3Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {btnQr, btnSua, btnTemplate, btnThem});

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

        jPanel5.setBorder(new javax.swing.border.MatteBorder(null));

        cboLocTrangThai.setFont(new java.awt.Font("Segoe UI", 3, 12)); // NOI18N
        cboLocTrangThai.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Còn Hàng", "Tạm Hết", "Dừng Bán" }));
        cboLocTrangThai.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboLocTrangThaiActionPerformed(evt);
            }
        });

        cboMauSac1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "-- Màu Sắc --" }));
        cboMauSac1.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cboMauSac1ItemStateChanged(evt);
            }
        });
        cboMauSac1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboMauSac1ActionPerformed(evt);
            }
        });

        cboKichThuoc1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "-- Kích Thước --" }));
        cboKichThuoc1.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cboKichThuoc1ItemStateChanged(evt);
            }
        });
        cboKichThuoc1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboKichThuoc1ActionPerformed(evt);
            }
        });

        cboHang1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "-- Thương Hiệu --" }));
        cboHang1.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cboHang1ItemStateChanged(evt);
            }
        });

        jLabel17.setFont(new java.awt.Font("Arial", 3, 18)); // NOI18N
        jLabel17.setText("Trạng Thái");

        txtSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSearchActionPerformed(evt);
            }
        });
        txtSearch.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtSearchKeyReleased(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("Segoe UI", 3, 18)); // NOI18N
        jLabel9.setText("Tìm Kiếm");

        jButton1.setFont(new java.awt.Font("Segoe UI", 2, 12)); // NOI18N
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/qr-code_9460284.png"))); // NOI18N
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        cbo_TenSP1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "-- Tên Giày --" }));
        cbo_TenSP1.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbo_TenSP1ItemStateChanged(evt);
            }
        });
        cbo_TenSP1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbo_TenSP1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(61, 61, 61)
                        .addComponent(jLabel9)
                        .addGap(18, 18, 18)
                        .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 262, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(84, 84, 84)
                        .addComponent(cboMauSac1, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(100, 100, 100)
                        .addComponent(cboKichThuoc1, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGap(177, 177, 177)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cboHang1, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(48, 48, 48)
                        .addComponent(cboLocTrangThai, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 115, Short.MAX_VALUE)
                        .addComponent(cbo_TenSP1, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(67, 67, 67))))
        );

        jPanel5Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {cboHang1, cboKichThuoc1, cboLocTrangThai, cboMauSac1, cbo_TenSP1});

        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cboHang1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbo_TenSP1, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cboKichThuoc1, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cboMauSac1, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 23, Short.MAX_VALUE)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel9)
                            .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(10, 10, 10))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jButton1)
                            .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel17)
                                .addComponent(cboLocTrangThai, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap())))
        );

        jPanel5Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {cboHang1, cboKichThuoc1, cboLocTrangThai, cboMauSac1, cbo_TenSP1});

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(330, 330, 330)
                        .addComponent(jButton13)
                        .addGap(18, 18, 18)
                        .addComponent(btnLui, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(soTrang, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnNext, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(28, 28, 28)
                        .addComponent(jButton16, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addComponent(jScrollPane2))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );

        jPanel4Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {btnLui, btnNext, jButton13, jButton16, soTrang});

        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jButton13)
                        .addComponent(btnLui))
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnNext)
                        .addComponent(jButton16))
                    .addComponent(soTrang, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(12, 12, 12))
        );

        jPanel4Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {btnLui, btnNext, jButton13, jButton16});

        jLabel10.setFont(new java.awt.Font("Segoe UI", 3, 24)); // NOI18N
        jLabel10.setText("Danh sách sản phẩm chi tiết");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(23, 23, 23)
                                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                        .addComponent(lb, javax.swing.GroupLayout.PREFERRED_SIZE, 480, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(263, 263, 263))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel10)
                                        .addGap(326, 326, 326)))))
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(171, 171, 171))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(lb)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 14, Short.MAX_VALUE)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(4, 4, 4)
                        .addComponent(jLabel10))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(26, 26, 26)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 1182, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void cbo_TenSPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbo_TenSPActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbo_TenSPActionPerformed

    private void btnNextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNextActionPerformed
        page++;
        if (page <= gioiHanPage) {
            fillToTable(sanPhamCT_Repository.get(page, 4));
            soTrang.setText(page + " / " + gioiHanPage);
            return;
        }
        page = gioiHanPage;
    }//GEN-LAST:event_btnNextActionPerformed

    private void btnLuiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLuiActionPerformed
        page--;
        if (page >= 1) {
            soTrang.setText(page + " / " + gioiHanPage);
            fillToTable(sanPhamCT_Repository.get(page, 4));
        } else {
            page = 1;
        }
    }//GEN-LAST:event_btnLuiActionPerformed

    private void jButton13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton13ActionPerformed
        fillToTable(sanPhamCT_Repository.get(1, 4));
    }//GEN-LAST:event_jButton13ActionPerformed

    private void jButton16ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton16ActionPerformed
        fillToTable(sanPhamCT_Repository.get(gioiHanPage, 4));
    }//GEN-LAST:event_jButton16ActionPerformed

    private void btnCleanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCleanActionPerformed
        txt_MaSPCT.setText("");
        txtGiaBan.setText("");
        txtGiaBan1.setText("");
        txtMoTa.setText("");
        txtSearch.setText("");
        txtSoLuong.setText("");
        cboHang.setSelectedIndex(0);
        cboKichThuoc.setSelectedIndex(0);
        cboMauSac.setSelectedIndex(0);
        cbo_TenSP.setSelectedIndex(0);
        cboLocTrangThai.setSelectedIndex(0);
        fillToTable(listSanPhamChiTiet);
    }//GEN-LAST:event_btnCleanActionPerformed

    public void getSearch() {

    }
    private void btnClean1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClean1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnClean1ActionPerformed

    private void btnThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemActionPerformed
        SanPhamChiTiet spct = getSanPhamChiTiet();
        if (spct == null) {
            Notifications.getInstance().show(Notifications.Type.INFO, Notifications.Location.TOP_CENTER, "Vui lòng thực hiện lại.");
            return;
        } else {
            int check = JOptionPane.showConfirmDialog(this, "Bạn có muốn thêm sản phẩm chi tiết không ?", "Xác nhận", JOptionPane.YES_NO_OPTION);

            if (check == JOptionPane.YES_OPTION) {
                // Người dùng chọn "Có"
                sanPhamCT_Repository.insertSPCT(spct);
                listSanPhamChiTiet = sanPhamCT_Repository.getToAll();
                fillToTable(listSanPhamChiTiet);
                Notifications.getInstance().show(Notifications.Type.INFO, Notifications.Location.TOP_CENTER, "Đã thêm thành công sản phẩm chi tiết.");
            } else if (check == JOptionPane.NO_OPTION) {
                // Người dùng chọn "Không"
                Notifications.getInstance().show(Notifications.Type.INFO, Notifications.Location.TOP_CENTER, "Bạn đã thoát thêm sản phẩm chi tiết.");
                return;
            } else {
                // Người dùng đóng hoặc đặt lại hộp thoại
                System.out.println("Hộp thoại đã đóng hoặc đặt lại.");
            }

        }
    }//GEN-LAST:event_btnThemActionPerformed

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

    private void btnSuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaActionPerformed
        SanPhamChiTiet spct = getSanPhamChiTiet();
        if (spct == null) {
            Notifications.getInstance().show(Notifications.Type.INFO, Notifications.Location.TOP_CENTER, "Vui lòng thực hiện lại.");
            return;
        } else {
            // Hiển thị hộp thoại xác nhận
            int check = JOptionPane.showConfirmDialog(this, "Bạn có muốn sửa sản phẩm chi tiết không ?", "Xác nhận", JOptionPane.YES_NO_OPTION);

            // Xử lý dựa trên lựa chọn của người dùng
            if (check == JOptionPane.YES_OPTION) {
                // Người dùng chọn "Có"
                String maSPCT = txt_MaSPCT.getText();
                sanPhamCT_Repository.updateSPCT(spct, maSPCT);
                listSanPhamChiTiet = sanPhamCT_Repository.getToAll();
                fillToTable(listSanPhamChiTiet);
                Notifications.getInstance().show(Notifications.Type.INFO, Notifications.Location.TOP_CENTER, "Đã sửa thành công sản phẩm chi tiết.");
            } else if (check == JOptionPane.NO_OPTION) {
                // Người dùng chọn "Không"
                Notifications.getInstance().show(Notifications.Type.INFO, Notifications.Location.TOP_CENTER, "Bạn đã thoát sửa sản phẩm chi tiết.");
                return;
            } else {
                // Người dùng đóng hoặc đặt lại hộp thoại
                System.out.println("Hộp thoại đã đóng hoặc đặt lại.");
            }

        }
    }//GEN-LAST:event_btnSuaActionPerformed


    private void btnQrActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnQrActionPerformed
        //BTN Tạo QR
        int row = tblSanPhamCT.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(this, "Mời chọn một sản phẩm để tải mã QR");
            return;
        }
        pro1041.team_3.swing.jnafilechooser.api.JnaFileChooser jfc = new pro1041.team_3.swing.jnafilechooser.api.JnaFileChooser();
        jfc.setMode(pro1041.team_3.swing.jnafilechooser.api.JnaFileChooser.Mode.Directories);
        if (!jfc.showOpenDialog((JFrame) SwingUtilities.getAncestorOfClass(JFrame.class, this))) {
            return;
        }
        SanPhamChiTiet spct = listSanPhamChiTiet.get(row);
        String mess = null;
        if (chiTiet_Impl != null) {
            mess = chiTiet_Impl.exportQr(jfc.getSelectedFile().getAbsolutePath(), spct.getMaSPCT());
            if ("Tải thành công".equals(mess)) {
                Notifications.getInstance().show(Notifications.Type.INFO, Notifications.Location.TOP_CENTER, "Đã thêm thành công.");
            } else {
                JOptionPane.showMessageDialog(this, mess);
            }
//            String qrFileName = chiTiet_Impl.exportQr(path, spct.getMaSPCT());
//            spct.setQr(qrFileName);
//            sanPhamCT_Repository.updateSPCT(spct, txt_MaSPCT.getText());
        } else {
            // Xử lý trường hợp chiTiet_Impl là null, có thể hiển thị một thông báo hoặc xử lý khác
            System.out.println("Lỗi: chiTiet_Impl không được khởi tạo.");
        }
        if (mess.equals("Tải thành công")) {
            Notifications.getInstance().show(Notifications.Type.INFO, Notifications.Location.TOP_CENTER, "Đã thêm thành công mã QR.");
        } else {
            JOptionPane.showMessageDialog(this, mess);
        }
    }//GEN-LAST:event_btnQrActionPerformed

    private void btnAdd_MauSacActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAdd_MauSacActionPerformed
        Application.showForm(new Form_MauSac());
    }//GEN-LAST:event_btnAdd_MauSacActionPerformed

    private void btnAdd_KichThuocActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAdd_KichThuocActionPerformed
        Application.showForm(new Form_KichThuoc());
    }//GEN-LAST:event_btnAdd_KichThuocActionPerformed

    private void btnAdd_HangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAdd_HangActionPerformed
        Application.showForm(new Form_ThuongHieu());
    }//GEN-LAST:event_btnAdd_HangActionPerformed

    private void btnAdd_MauSac1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAdd_MauSac1ActionPerformed
        Application.showForm(new Form_SanPham());
    }//GEN-LAST:event_btnAdd_MauSac1ActionPerformed

    private void btnExportActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExportActionPerformed
        JnaFileChooser jfc = new JnaFileChooser();
        jfc.setMode(JnaFileChooser.Mode.Directories);
        if (!jfc.showOpenDialog((JFrame) SwingUtilities.getAncestorOfClass(JFrame.class, this))) {
            return;
        }
        String path = jfc.getSelectedFile().getAbsolutePath();
        LocalDateTime local = LocalDateTime.now();
        File file = new File(path + "\\DanhSachGiayChiTiet_" + local.getDayOfMonth() + "_" + local.getMonthValue() + "_" + local.getYear() + ".xlsx");

        if (chiTiet_Impl.export(file)) {
            JOptionPane.showMessageDialog(this, "Export thành công", "Export", JOptionPane.INFORMATION_MESSAGE);
            if (Desktop.isDesktopSupported()) {
                Desktop desktop = Desktop.getDesktop();
                if (file.exists()) {
                    try {
                        desktop.open(file);
                    } catch (IOException ex) {
                        ex.printStackTrace();
                        JOptionPane.showMessageDialog(this, "Mở thất bại", "Export", JOptionPane.INFORMATION_MESSAGE);
                    }
                }
            }
        } else {
            JOptionPane.showMessageDialog(this, "Thất bại", "Export", JOptionPane.INFORMATION_MESSAGE);
        }
    }//GEN-LAST:event_btnExportActionPerformed

    private void btnTemplateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTemplateActionPerformed
        // BTN export mẫu
        JnaFileChooser jfc = new JnaFileChooser();
        jfc.setMode(JnaFileChooser.Mode.Directories);
        if (!jfc.showOpenDialog((JFrame) SwingUtilities.getAncestorOfClass(JFrame.class, this))) {
            return;
        }
        String path = jfc.getSelectedFile().getAbsolutePath();
        File file = new File(path + "\\MauImport.xlsx");

        if (chiTiet_Impl.exportMau(file)) {
            JOptionPane.showMessageDialog(this, "Tải mẫu thành công", "Export", JOptionPane.INFORMATION_MESSAGE);
            if (Desktop.isDesktopSupported()) {
                Desktop desktop = Desktop.getDesktop();
                if (file.exists()) {
                    try {
                        desktop.open(file);
                    } catch (IOException ex) {
                        ex.printStackTrace();
                        JOptionPane.showMessageDialog(this, "Mở thất bại", "Export", JOptionPane.INFORMATION_MESSAGE);
                    }
                }
            }
        } else {
            JOptionPane.showMessageDialog(this, "Thất bại", "Export", JOptionPane.INFORMATION_MESSAGE);
        }
    }//GEN-LAST:event_btnTemplateActionPerformed

    private void btnImportActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnImportActionPerformed
        //BTN Import Excel
        JFileChooser avatarChooser = new JFileChooser("D:\\");
        FileNameExtensionFilter avatarFilter = new FileNameExtensionFilter("Exel File", "xlsx");
        avatarChooser.setFileFilter(avatarFilter);
        avatarChooser.setAcceptAllFileFilterUsed(false);
        int selectFileCheck = avatarChooser.showOpenDialog(this);
        File selectedFile = avatarChooser.getSelectedFile();
        if (!(selectFileCheck == JFileChooser.APPROVE_OPTION)) {
            return;
        }
        String ketQua = chiTiet_Impl.importFile(selectedFile);
        if (ketQua.equals("Import thành công")) {
            listSanPhamChiTiet = sanPhamCT_Repository.get(page, 5);

            fillToTable(listSanPhamChiTiet);
        }
        JOptionPane.showMessageDialog(this, ketQua);
    }//GEN-LAST:event_btnImportActionPerformed

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

    private void txtSearchKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSearchKeyReleased
        List<SanPhamChiTiet> listSearch = sanPhamCT_Repository.search_SanPhamChiTiet(txtSearch.getText().trim());
        System.out.println(listSearch);
        fillToTable(listSearch);
    }//GEN-LAST:event_txtSearchKeyReleased

    private void cboKichThuoc1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboKichThuoc1ActionPerformed

    }//GEN-LAST:event_cboKichThuoc1ActionPerformed

    private void cbo_TenSP1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbo_TenSP1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbo_TenSP1ActionPerformed

    private void cboMauSac1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboMauSac1ActionPerformed

    }//GEN-LAST:event_cboMauSac1ActionPerformed

    private void cboMauSac1ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cboMauSac1ItemStateChanged
        initSearch();
    }//GEN-LAST:event_cboMauSac1ItemStateChanged

    private void cboKichThuoc1ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cboKichThuoc1ItemStateChanged
        initSearch();
    }//GEN-LAST:event_cboKichThuoc1ItemStateChanged

    private void cboHang1ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cboHang1ItemStateChanged
        initSearch();
    }//GEN-LAST:event_cboHang1ItemStateChanged

    private void cbo_TenSP1ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbo_TenSP1ItemStateChanged
        initSearch();
    }//GEN-LAST:event_cbo_TenSP1ItemStateChanged

    private void initSearch() {

        KichThuoc kt = new KichThuoc();
        if (cboKichThuoc1.getSelectedItem() instanceof KichThuoc) {
            kt = (KichThuoc) cboKichThuoc1.getSelectedItem();
        }
        Long idKichThuoc = null;
        idKichThuoc = kt.getIdSize();

        MauSac ms = new MauSac();
        if (cboMauSac1.getSelectedItem() instanceof MauSac) {
            ms = (MauSac) cboMauSac1.getSelectedItem();
        }
        Long idMau = null;
        idMau = ms.getIdMau();

        SanPham sp = new SanPham();
        if (cbo_TenSP1.getSelectedItem() instanceof SanPham) {
            sp = (SanPham) cbo_TenSP1.getSelectedItem();
        }
        Long idSanPham = null;
        idSanPham = sp.getIdSanPham();

        ThuongHieu th = new ThuongHieu();
        if (cboHang1.getSelectedItem() instanceof ThuongHieu) {
            th = (ThuongHieu) cboHang1.getSelectedItem();
        }
        Long idThuongHieu = null;
        idThuongHieu = th.getIdThuongHieu();
        
        List<SanPhamChiTiet> list = sanPhamCT_Repository.searchItem(idMau, idKichThuoc, idThuongHieu, idSanPham);
        fillToTable(list);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdd_Hang;
    private javax.swing.JButton btnAdd_KichThuoc;
    private javax.swing.JButton btnAdd_MauSac;
    private javax.swing.JButton btnAdd_MauSac1;
    private javax.swing.JButton btnClean;
    private javax.swing.JButton btnClean1;
    private javax.swing.JButton btnExport;
    private javax.swing.JButton btnImport;
    private javax.swing.JButton btnLui;
    private javax.swing.JButton btnNext;
    private javax.swing.JButton btnQr;
    private javax.swing.JButton btnSua;
    private javax.swing.JButton btnTemplate;
    private javax.swing.JButton btnThem;
    private javax.swing.JComboBox<String> cboHang;
    private javax.swing.JComboBox<String> cboHang1;
    private javax.swing.JComboBox<String> cboKichThuoc;
    private javax.swing.JComboBox<String> cboKichThuoc1;
    private javax.swing.JComboBox<String> cboLocTrangThai;
    private javax.swing.JComboBox<String> cboMauSac;
    private javax.swing.JComboBox<String> cboMauSac1;
    private javax.swing.JComboBox<String> cboTrangThai;
    private javax.swing.JComboBox<String> cbo_TenSP;
    private javax.swing.JComboBox<String> cbo_TenSP1;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton13;
    private javax.swing.JButton jButton16;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
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

}
