package raven.application.form.other;

import Model.SanPham;
import Repository.SanPham_Repository;
import com.formdev.flatlaf.FlatClientProperties;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import raven.application.Application;
import raven.toast.Notifications;

/**
 *
 * @author Raven
 */
public class Form_SanPham extends javax.swing.JPanel {

    DefaultTableModel tblModel;
    private static SanPham_Repository sanPham_Repository = new SanPham_Repository();
    private static List<SanPham> lts = new ArrayList<>();
    private static int page = 1;
    private static int gioiHanPage = (int) ((Math.ceil(sanPham_Repository.getRowCount() / 4)) + 1);

    public Form_SanPham() {
        initComponents();
        tblModel = (DefaultTableModel) tblSanPham.getModel();
        lts = sanPham_Repository.get(page, 5);
        fillToTable(lts);
    }

    public void fillToTable(List<SanPham> lst) {
        tblModel.setRowCount(0);
        int n = 1;
        for (SanPham i : lst) {
            Object[] rows = new Object[4];
            rows[0] = n++;
            rows[1] = i.getMaSanPham();
            rows[2] = i.getTenSanpham();
            rows[3] = (i.getTrangThai() == 0) ? "Đang Kinh Doanh" : ((i.getTrangThai() == 1) ? "Ngừng Kinh Doanh" : "Đã Hết Hàng");
            tblModel.addRow(rows);
        }
    }

    public SanPham getSanPham() {
        String maSanPham;
        int index = tblSanPham.getSelectedRow();
        if (txtMaSP.getText().isEmpty()) {
            maSanPham = sanPham_Repository.MaTuDongSanPham();
        } else {
            maSanPham = txtMaSP.getText();
        }
        String tenSanPham = txtTenSP.getText();
        if (tenSanPham.isEmpty()) {
            Notifications.getInstance().show(Notifications.Type.INFO, Notifications.Location.TOP_CENTER, "Vui lòng không để trống.");
            return null;
        }

        int trangThai = 0;
        if (rboDangKinhDoanh.isSelected()) {
            trangThai = 0;
        } else if (rboNgungKinhDoanh.isSelected()) {
            trangThai = 1;
        } else if (rboHetHang.isSelected()) {
            trangThai = 2;
        }
        return new SanPham(maSanPham, tenSanPham, trangThai);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        buttonGroup2 = new javax.swing.ButtonGroup();
        buttonGroup3 = new javax.swing.ButtonGroup();
        btnLui = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        btnThem2 = new javax.swing.JButton();
        btnSua = new javax.swing.JButton();
        btnClean2 = new javax.swing.JButton();
        btnClean3 = new javax.swing.JButton();
        btnNext = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        btnDauCuoi = new javax.swing.JButton();
        soTrang = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblSanPham = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtMaSP = new javax.swing.JTextField();
        txtTenSP = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        rboDangKinhDoanh = new javax.swing.JRadioButton();
        rboNgungKinhDoanh = new javax.swing.JRadioButton();
        rboHetHang = new javax.swing.JRadioButton();
        jPanel3 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txtSearch = new javax.swing.JTextField();
        rbo1 = new javax.swing.JRadioButton();
        rbo2 = new javax.swing.JRadioButton();
        rbo3 = new javax.swing.JRadioButton();
        btnSauCuoi = new javax.swing.JButton();

        btnLui.setFont(new java.awt.Font("Segoe UI", 3, 18)); // NOI18N
        btnLui.setText("<");
        btnLui.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLuiActionPerformed(evt);
            }
        });

        jPanel2.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        btnThem2.setFont(new java.awt.Font("Segoe UI", 2, 14)); // NOI18N
        btnThem2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/raven/icon/png/add.png"))); // NOI18N
        btnThem2.setText("ADD");
        btnThem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThem2ActionPerformed(evt);
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

        btnClean2.setFont(new java.awt.Font("Segoe UI", 2, 12)); // NOI18N
        btnClean2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/raven/icon/png/reset.png"))); // NOI18N
        btnClean2.setText("RESET");
        btnClean2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnClean2ActionPerformed(evt);
            }
        });

        btnClean3.setFont(new java.awt.Font("Segoe UI", 2, 12)); // NOI18N
        btnClean3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/raven/icon/png/return.png"))); // NOI18N
        btnClean3.setText("RETURN");
        btnClean3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnClean3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnSua, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnClean2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnClean3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnThem2))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel2Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {btnClean2, btnClean3, btnSua, btnThem2});

        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnThem2)
                .addGap(18, 18, 18)
                .addComponent(btnSua)
                .addGap(18, 18, 18)
                .addComponent(btnClean2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 14, Short.MAX_VALUE)
                .addComponent(btnClean3)
                .addContainerGap())
        );

        btnNext.setFont(new java.awt.Font("Segoe UI", 3, 18)); // NOI18N
        btnNext.setText(">");
        btnNext.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNextActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Segoe UI Black", 3, 24)); // NOI18N
        jLabel1.setText("THÔNG TIN SẢN PHẨM");

        btnDauCuoi.setFont(new java.awt.Font("Segoe UI", 3, 18)); // NOI18N
        btnDauCuoi.setText(">>");
        btnDauCuoi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDauCuoiActionPerformed(evt);
            }
        });

        soTrang.setText("     Số Trang");

        tblSanPham.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "STT", "Mã Giày ", "Tên Giày", "Trạng Thái"
            }
        ));
        tblSanPham.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblSanPhamMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblSanPham);

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel2.setFont(new java.awt.Font("Sitka Display", 3, 18)); // NOI18N
        jLabel2.setText("Mã Sản Phẩm");

        jLabel3.setFont(new java.awt.Font("Sitka Display", 3, 18)); // NOI18N
        jLabel3.setText("Trạng Thái");

        txtMaSP.setEditable(false);

        jLabel4.setFont(new java.awt.Font("Sitka Display", 3, 18)); // NOI18N
        jLabel4.setText("Tên Sản Phẩm");

        buttonGroup1.add(rboDangKinhDoanh);
        rboDangKinhDoanh.setFont(new java.awt.Font("Segoe UI", 2, 14)); // NOI18N
        rboDangKinhDoanh.setText("Đang Kinh Doanh");

        buttonGroup1.add(rboNgungKinhDoanh);
        rboNgungKinhDoanh.setFont(new java.awt.Font("Segoe UI", 2, 14)); // NOI18N
        rboNgungKinhDoanh.setText("Ngừng Kinh Doanh");

        buttonGroup1.add(rboHetHang);
        rboHetHang.setFont(new java.awt.Font("Segoe UI", 2, 14)); // NOI18N
        rboHetHang.setText("Đã Hết Hàng");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(18, 18, 18)
                        .addComponent(txtMaSP, javax.swing.GroupLayout.PREFERRED_SIZE, 644, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addComponent(jLabel3))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtTenSP, javax.swing.GroupLayout.PREFERRED_SIZE, 644, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(rboDangKinhDoanh)
                                .addGap(43, 43, 43)
                                .addComponent(rboNgungKinhDoanh)
                                .addGap(18, 18, 18)
                                .addComponent(rboHetHang)))))
                .addContainerGap(27, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtMaSP, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtTenSP, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(rboDangKinhDoanh)
                    .addComponent(rboNgungKinhDoanh)
                    .addComponent(rboHetHang))
                .addContainerGap(53, Short.MAX_VALUE))
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "TÌM KIẾM SẢN PHẨM ", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 3, 18))); // NOI18N

        jLabel5.setFont(new java.awt.Font("Segoe UI", 3, 18)); // NOI18N
        jLabel5.setText("Tìm Kiếm");

        jLabel6.setFont(new java.awt.Font("Segoe UI", 3, 18)); // NOI18N
        jLabel6.setText("Trạng Thái");

        txtSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSearchActionPerformed(evt);
            }
        });

        buttonGroup3.add(rbo1);
        rbo1.setFont(new java.awt.Font("Segoe UI", 2, 14)); // NOI18N
        rbo1.setText("Đang Kinh Doanh");
        rbo1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbo1ActionPerformed(evt);
            }
        });

        buttonGroup3.add(rbo2);
        rbo2.setFont(new java.awt.Font("Segoe UI", 2, 14)); // NOI18N
        rbo2.setText("Ngừng Kinh Doanh");
        rbo2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbo2ActionPerformed(evt);
            }
        });

        buttonGroup3.add(rbo3);
        rbo3.setFont(new java.awt.Font("Segoe UI", 2, 14)); // NOI18N
        rbo3.setText("Đã Hết Hàng");
        rbo3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbo3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5)
                    .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 310, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(125, 125, 125)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(rbo1)
                        .addGap(43, 43, 43)
                        .addComponent(rbo2)
                        .addGap(18, 18, 18)
                        .addComponent(rbo3)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(rbo1)
                    .addComponent(rbo2)
                    .addComponent(rbo3))
                .addContainerGap())
        );

        btnSauCuoi.setFont(new java.awt.Font("Segoe UI", 3, 18)); // NOI18N
        btnSauCuoi.setText("<<");
        btnSauCuoi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSauCuoiActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(364, 364, 364)
                        .addComponent(jLabel1))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(297, 297, 297)
                        .addComponent(btnSauCuoi)
                        .addGap(18, 18, 18)
                        .addComponent(btnLui, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(soTrang, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnNext)
                        .addGap(18, 18, 18)
                        .addComponent(btnDauCuoi, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(22, 22, 22))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 65, Short.MAX_VALUE))
                    .addComponent(jScrollPane1)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(8, 8, 8)
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 241, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(35, 35, 35)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(soTrang, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnSauCuoi, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnLui, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnNext, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnDauCuoi, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(16, 16, 16))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnLuiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLuiActionPerformed
        page--;
        if (page >= 1) {
            soTrang.setText(page + " / " + gioiHanPage);
            fillToTable(sanPham_Repository.get(page, 5));
        } else {
            page = 1;
        }
    }//GEN-LAST:event_btnLuiActionPerformed

    private void btnThem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThem2ActionPerformed
        SanPham sp = getSanPham();
        if (sp == null) {
            Notifications.getInstance().show(Notifications.Type.INFO, Notifications.Location.TOP_CENTER, "Xảy ra lỗi vui lòng kiểm tra lại.");
            return;
        } else {
            int check = JOptionPane.showConfirmDialog(this, "Bạn có muốn thêm màu sắc không ?", "Xác nhận", JOptionPane.YES_NO_OPTION);
            if (check == JOptionPane.YES_OPTION) {
                // Người dùng chọn "Có"
                sanPham_Repository.addSanPham(sp);
                lts = sanPham_Repository.get(page, 5);
                fillToTable(lts);
                Notifications.getInstance().show(Notifications.Type.INFO, Notifications.Location.TOP_CENTER, "Bạn đã thêm thành công màu sắc mới.");
            } else if (check == JOptionPane.NO_OPTION) {
                // Người dùng chọn "Không"
                Notifications.getInstance().show(Notifications.Type.INFO, Notifications.Location.TOP_CENTER, "Bạn đã thoát thêm màu sắc.");
                return;
            } else {
                // Người dùng đóng hoặc đặt lại hộp thoại
                System.out.println("Hộp thoại đã đóng hoặc đặt lại.");
            }
        }
    }//GEN-LAST:event_btnThem2ActionPerformed

    private void btnSuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaActionPerformed
        SanPham sp = getSanPham();
        if (sp == null) {
            Notifications.getInstance().show(Notifications.Type.INFO, Notifications.Location.TOP_CENTER, "Xảy ra lỗi vui lòng kiểm tra lại.");
            return;
        } else {
            int check = JOptionPane.showConfirmDialog(this, "Bạn có muốn thêm màu sắc không ?", "Xác nhận", JOptionPane.YES_NO_OPTION);
            if (check == JOptionPane.YES_OPTION) {
                // Người dùng chọn "Có"
                String maSp = txtMaSP.getText();
                sanPham_Repository.updateSanPham(sp, maSp);
                lts = sanPham_Repository.get(page, 5);
                fillToTable(lts);
                Notifications.getInstance().show(Notifications.Type.INFO, Notifications.Location.TOP_CENTER, "Bạn đã thêm thành công màu sắc mới.");
            } else if (check == JOptionPane.NO_OPTION) {
                // Người dùng chọn "Không"
                Notifications.getInstance().show(Notifications.Type.INFO, Notifications.Location.TOP_CENTER, "Bạn đã thoát thêm màu sắc.");
                return;
            } else {
                // Người dùng đóng hoặc đặt lại hộp thoại
                System.out.println("Hộp thoại đã đóng hoặc đặt lại.");
            }
        }
    }//GEN-LAST:event_btnSuaActionPerformed

    private void btnClean2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClean2ActionPerformed
        txtMaSP.setText("");
        txtTenSP.setText("");
        rboDangKinhDoanh.setSelected(true);
        rbo1.setSelected(true);
    }//GEN-LAST:event_btnClean2ActionPerformed

    private void btnNextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNextActionPerformed
        page++;
        if (page <= gioiHanPage) {
            fillToTable(sanPham_Repository.get(page, 5));
            soTrang.setText(page + " / " + gioiHanPage);
            return;
        }
        page = gioiHanPage;
    }//GEN-LAST:event_btnNextActionPerformed

    private void btnDauCuoiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDauCuoiActionPerformed
        fillToTable(sanPham_Repository.get(gioiHanPage, 5));
    }//GEN-LAST:event_btnDauCuoiActionPerformed

    private void tblSanPhamMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblSanPhamMouseClicked
        int index = tblSanPham.getSelectedRow();
        if (index < 0) {
            Notifications.getInstance().show(Notifications.Type.INFO, Notifications.Location.TOP_CENTER, "Vui lòng chọn dòng cần hiện thông tin.");
            return;
        } else {
            txtMaSP.setText((String) tblSanPham.getValueAt(index, 1));
            txtTenSP.setText((String) tblSanPham.getValueAt(index, 2));
            
            if (tblSanPham.getValueAt(index, 3).equals("Đang Kinh Doanh")) {
                rboDangKinhDoanh.setSelected(true);
            } else if (tblSanPham.getValueAt(index, 3).equals("Ngừng Kinh Doanh")) {
                rboNgungKinhDoanh.setSelected(true);
            } else if (tblSanPham.getValueAt(index, 3).equals("Đã Hết Hàng")) {
                rboHetHang.setSelected(true);
            }   
        }
    }//GEN-LAST:event_tblSanPhamMouseClicked

    private void txtSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSearchActionPerformed
        List<SanPham> listMauSearch = sanPham_Repository.search_SanPham(txtSearch.getText().trim());
        fillToTable(listMauSearch);
    }//GEN-LAST:event_txtSearchActionPerformed

    private void btnSauCuoiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSauCuoiActionPerformed
        fillToTable(sanPham_Repository.get(1, 5));
    }//GEN-LAST:event_btnSauCuoiActionPerformed

    private void btnClean3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClean3ActionPerformed
        Application.showForm(new Form_SanPhamChiTiet());
    }//GEN-LAST:event_btnClean3ActionPerformed

    private void rbo1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbo1ActionPerformed
        List<SanPham> list1 = sanPham_Repository.search_SanPhamByTrangThai(0);
        fillToTable(list1);
    }//GEN-LAST:event_rbo1ActionPerformed

    private void rbo2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbo2ActionPerformed
        List<SanPham> list2 = sanPham_Repository.search_SanPhamByTrangThai(1);
        fillToTable(list2);
    }//GEN-LAST:event_rbo2ActionPerformed

    private void rbo3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbo3ActionPerformed
        List<SanPham> list3 = sanPham_Repository.search_SanPhamByTrangThai(2);
        fillToTable(list3);
    }//GEN-LAST:event_rbo3ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnClean2;
    private javax.swing.JButton btnClean3;
    private javax.swing.JButton btnDauCuoi;
    private javax.swing.JButton btnLui;
    private javax.swing.JButton btnNext;
    private javax.swing.JButton btnSauCuoi;
    private javax.swing.JButton btnSua;
    private javax.swing.JButton btnThem2;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.ButtonGroup buttonGroup3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JRadioButton rbo1;
    private javax.swing.JRadioButton rbo2;
    private javax.swing.JRadioButton rbo3;
    private javax.swing.JRadioButton rboDangKinhDoanh;
    private javax.swing.JRadioButton rboHetHang;
    private javax.swing.JRadioButton rboNgungKinhDoanh;
    private javax.swing.JLabel soTrang;
    private javax.swing.JTable tblSanPham;
    private javax.swing.JTextField txtMaSP;
    private javax.swing.JTextField txtSearch;
    private javax.swing.JTextField txtTenSP;
    // End of variables declaration//GEN-END:variables
}
