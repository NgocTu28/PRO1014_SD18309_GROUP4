/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Impl;

import Model.KichThuoc;
import Model.MauSac;
import Model.SanPham;
import Model.SanPhamChiTiet;
import Model.SanPhamChiTiet_T;
import Model.ThuongHieu;
import Repository.DBConnection;
import Repository.KichThuoc_Repository;
import Repository.MauSac_Reponsitory;
import Repository.SanPhamCT_Repository;
import Repository.SanPhamChiTiet_T_Repository;
import Repository.SanPham_Repository;
import Repository.ThuongHieu_Repository;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import static org.apache.poi.ss.usermodel.CellType.BLANK;
import static org.apache.poi.ss.usermodel.CellType.BOOLEAN;
import static org.apache.poi.ss.usermodel.CellType.ERROR;
import static org.apache.poi.ss.usermodel.CellType.FORMULA;
import static org.apache.poi.ss.usermodel.CellType.NUMERIC;
import static org.apache.poi.ss.usermodel.CellType.STRING;
import static org.apache.poi.ss.usermodel.CellType._NONE;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.usermodel.RichTextString;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *
 * @author vutu8
 */
public class giayChiTiet_Impl {

    SanPhamCT_Repository sanPhamChiTiet_Repo = new SanPhamCT_Repository();
    ThuongHieu_Repository thuongHieu_Repository = new ThuongHieu_Repository();
    KichThuoc_Repository kichThuoc_Repository = new KichThuoc_Repository();
    MauSac_Reponsitory mauSac_Reponsitory = new MauSac_Reponsitory();
    SanPham_Repository sanPham_Repository = new SanPham_Repository();
    SanPhamChiTiet_T_Repository sanPhamChiTiet_T_Repository = new SanPhamChiTiet_T_Repository();
    public static final int COLUMN_INDEX_IDSPCT = 0;
    public static final int COLUMN_INDEX_MA_GIAY_CT = 1;
    public static final int COLUMN_INDEX_TEN_GIAY = 2;
    public static final int COLUMN_INDEX_THUONG_HIEU = 3;
    public static final int COLUMN_INDEX_KICH_CO = 4;
    public static final int COLUMN_INDEX_MAU_SAC = 5;
    public static final int COLUMN_INDEX_SO_LUONG = 6;
    public static final int COLUMN_INDEX_GIA_BAN = 7;
    public static final int COLUMN_INDEX_GIA_NIEM_YET = 8;
    public static final int COLUMN_INDEX_MO_TA = 9;
    public static final int COLUMN_INDEX_TRANG_THAI = 10;

    public String exportQr(String pathFolder, String ma) {
        SanPhamChiTiet spct = (SanPhamChiTiet) sanPhamChiTiet_Repo.getProductByMa(ma);
        if (spct == null) {
            return "Không tìm thấy điện thoại";
        }
        DecimalFormat moneyFormat = new DecimalFormat("#,###");
        String data = "Mã Giày Chi Tiết:" + spct.getMaSPCT()
                + "\n Tên Giày:" + spct.getIdSanPham().getTenSanpham()
                + "\n Tên Thương Hiệu:" + spct.getIdThuongHieu().getTenThuongHieu()
                + "\n Kích Thước:" + spct.getIdKichThuoc().getTenSize()
                + "\n Màu Sắc" + spct.getIdMau().getTenMau()
                + "\n Số Lượng Tồn" + spct.getSoLuong()
                + "\n Giá Bán" + spct.getGiaBan()
                + "\n Giá Niêm Yết" + spct.getGiaNiemYet()
                + (spct.getMoTa() == null ? "" : "\n Mô Tả" + spct.getMoTa())
                + "\n Trạng Thái" + spct.getTrangThai();
        try {
            QRCodeWriter qrCodeWriter = new QRCodeWriter();
            Hashtable hints = new Hashtable();
            hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");
            BitMatrix matrix = qrCodeWriter.encode(data, BarcodeFormat.QR_CODE, 200, 200, hints);

            // Write to file image
            Path path = FileSystems.getDefault().getPath(pathFolder + "\\" + spct.getMaSPCT() + "-" + spct.getIdSanPham().getTenSanpham() + ".png");
            BufferedImage qrImage = MatrixToImageWriter.toBufferedImage(matrix);
            MatrixToImageWriter.writeToPath(matrix, "PNG", path);
        } catch (Exception ex) {
            Logger.getLogger(giayChiTiet_Impl.class.getName()).log(Level.SEVERE, null, ex);
            return "Lỗi hệ thống. Không thể export";
        }
        return "Tải thành công";
    }

    public boolean export(File file) {
        List<SanPhamChiTiet> lst = sanPhamChiTiet_Repo.getToAll();
        try {
            FileOutputStream fos = new FileOutputStream(file);
            XSSFWorkbook workbook = new XSSFWorkbook();
            XSSFSheet sheet = workbook.createSheet("Danh sách chi tiết giày");
            int rowNum = 0;
            Row firstRow = sheet.createRow(rowNum++);

            Cell idDtct = firstRow.createCell(0);
            idDtct.setCellValue("ID SPCT");

            Cell maSPCTTitle = firstRow.createCell(1);
            maSPCTTitle.setCellValue("Mã Giày Chi Tiết");

            Cell tengiayTitle = firstRow.createCell(2);
            tengiayTitle.setCellValue("Tên Giày");

            Cell TenThuongHieuTitle = firstRow.createCell(3);
            TenThuongHieuTitle.setCellValue("Thương Hiệu");

            Cell TenSizeTitle = firstRow.createCell(4);
            TenSizeTitle.setCellValue("Kích Cỡ");

            Cell TenMauTitle = firstRow.createCell(5);
            TenMauTitle.setCellValue("Màu Sắc");

            Cell SoLuongTonTitle = firstRow.createCell(6);
            SoLuongTonTitle.setCellValue("Số Lượng");

            Cell GiaBanTitle = firstRow.createCell(7);
            GiaBanTitle.setCellValue("Giá Bán");

            Cell GiaNiemYetTitle = firstRow.createCell(8);
            GiaNiemYetTitle.setCellValue("Giá Niêm Yết");

            Cell MoTaTitle = firstRow.createCell(9);
            MoTaTitle.setCellValue("Mô Tả");

            Cell TrangThaiTitle = firstRow.createCell(10);
            TrangThaiTitle.setCellValue("Trạng Thái");

            for (SanPhamChiTiet x : lst) {
                Row row = sheet.createRow(rowNum++);
//    CTSP.ID,CTSP.MaCTSP,SP.TenSP,TH.TenThuongHieu,S.TenSize,M.TenMau,CTSP.SoLuongTon, CTSP.GiaBan, CTSP.GiaNiemYet, CTSP.MoTa, CTSP.TrangThai
                Cell cell2 = row.createCell(0);
                cell2.setCellValue(x.getIdSPCT());

                Cell cell3 = row.createCell(1);
                cell3.setCellValue(x.getMaSPCT());

                Cell cell4 = row.createCell(2);
                cell4.setCellValue(x.getIdSanPham().getTenSanpham());

                Cell cell5 = row.createCell(3);
                cell5.setCellValue(x.getIdThuongHieu().getTenThuongHieu());

                Cell cell6 = row.createCell(4);
                cell6.setCellValue(x.getIdKichThuoc().getTenSize());

                Cell cell7 = row.createCell(5);
                cell7.setCellValue(x.getIdMau().getTenMau());

                Cell cell8 = row.createCell(6);
                cell8.setCellValue(x.getSoLuong());

                Cell cell9 = row.createCell(7);
                cell9.setCellValue(x.getGiaBan().toString()); // Đặt giá trị kiểu dữ liệu phù hợp

                Cell cell10 = row.createCell(8);
                cell10.setCellValue(x.getGiaNiemYet().toString()); // Đặt giá trị kiểu dữ liệu phù hợp

                Cell cell11 = row.createCell(9);
                cell11.setCellValue(x.getMoTa());

                Cell cell12 = row.createCell(10);
                cell12.setCellValue(x.getTrangThai());
            }
            workbook.write(fos);
            workbook.close();
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
            return false;
        } catch (IOException ex) {
            ex.printStackTrace();
            return false;
        }
        return true;
    }

    public boolean exportMau(File file) {
        List<SanPhamChiTiet> lst = sanPhamChiTiet_Repo.getToAll();
        try {
            FileOutputStream fos = new FileOutputStream(file);
            XSSFWorkbook workbook = new XSSFWorkbook();
            XSSFSheet sheet = workbook.createSheet("Danh sách chi tiết giày");
            int rowNum = 0;
            Row firstRow = sheet.createRow(rowNum++);

            Cell idDtct = firstRow.createCell(0);
            idDtct.setCellValue("Tên Sản Phẩm");

            Cell maSPCTTitle = firstRow.createCell(1);
            maSPCTTitle.setCellValue("Tên Thương Hiệu");

            Cell tengiayTitle = firstRow.createCell(2);
            tengiayTitle.setCellValue("Màu Sắc");

            Cell TenThuongHieuTitle = firstRow.createCell(3);
            TenThuongHieuTitle.setCellValue("Kích Cỡ");

            Cell TenSizeTitle = firstRow.createCell(4);
            TenSizeTitle.setCellValue("Mã Sản Phẩm Chi Tiết");

            Cell SoLuongTonTitle = firstRow.createCell(5);
            SoLuongTonTitle.setCellValue("Số Lượng");

            Cell GiaBanTitle = firstRow.createCell(6);
            GiaBanTitle.setCellValue("Giá Bán");

            Cell GiaNiemYetTitle = firstRow.createCell(7);
            GiaNiemYetTitle.setCellValue("Giá Niêm Yết");

            Cell MoTaTitle = firstRow.createCell(8);
            MoTaTitle.setCellValue("Mô Tả");

            Cell TrangThaiTitle = firstRow.createCell(9);
            TrangThaiTitle.setCellValue("Trạng Thái");

            workbook.write(fos);
            workbook.close();
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
            return false;
        } catch (IOException ex) {
            ex.printStackTrace();
            return false;
        }
        return true;
    }

    private static Object getCellValue(Cell cell) {
//        CellType cellType = cell.getCellTypeEnum();
        CellType cellType = cell.getCellType();
        Object cellValue = null;
        switch (cellType) {
            case BOOLEAN:
                cellValue = cell.getBooleanCellValue();
                break;
            case FORMULA:
                Workbook workbook = cell.getSheet().getWorkbook();
                FormulaEvaluator evaluator = workbook.getCreationHelper().createFormulaEvaluator();
                cellValue = evaluator.evaluate(cell).getNumberValue();
                break;
            case NUMERIC:
                cellValue = cell.getNumericCellValue();
                break;
            case STRING:
                cellValue = cell.getStringCellValue();
                break;
            case _NONE:
            case BLANK:
            case ERROR:
                break;
            default:
                break;
        }

        return cellValue;
    }

    public String importFile(File file) {
        List<SanPhamChiTiet_T> listCTDT = new ArrayList<>();
        String ketQua;
        try {
            FileInputStream inputStream = new FileInputStream(file);
            Workbook workbook = new XSSFWorkbook(inputStream);

            Sheet sheet = workbook.getSheetAt(0);

            Iterator<Row> iterator = sheet.iterator();
            while (iterator.hasNext()) {
                Row nextRow = iterator.next();
                if (nextRow.getRowNum() == 0 || nextRow.getRowNum() == 1) {
                    continue;
                }

                Iterator<Cell> cellIterator = nextRow.cellIterator();

                SanPhamChiTiet_T spct = new SanPhamChiTiet_T();
                while (cellIterator.hasNext()) {
                    Cell nextCell = cellIterator.next();
                    Object cellValue = getCellValue(nextCell);
//                    if (cellValue == null || cellValue.toString().isEmpty()) {
//                        continue;
//                    }

                    int columnIndex = nextCell.getColumnIndex();
                    switch (columnIndex) {
                        case 0:
                            if (nextCell.getCellType() == CellType.BLANK || nextCell.getCellType() == CellType._NONE) {
                                return "Tên Sản Phẩm bị bỏ trống tại hàng " + (nextCell.getRowIndex() + 1);
                            }
                            // Case type excel
                            String tenSP = "";
                            if (nextCell.getCellType() == CellType.NUMERIC) {
                                tenSP += nextCell.getNumericCellValue();
                            } else if (nextCell.getCellType() == CellType.STRING) {
                                tenSP += nextCell.getStringCellValue();
                            }
                            tenSP = tenSP.trim();
                            // Case type excel

                            if (tenSP.isEmpty()) {
                                return "Mã SP bị bỏ trống tại hàng " + (nextCell.getRowIndex() + 1);
                            }
                            SanPham sp = sanPham_Repository.findSanPhamByName(tenSP);
                            System.out.println(sp.getIdSanPham());
                            spct.setIdSanPham(sp.getIdSanPham());
                            break;
                        case 1:
                            if (nextCell.getCellType() == CellType.BLANK || nextCell.getCellType() == CellType._NONE) {
                                return "Thương hiệu bị bỏ trống tại hàng " + (nextCell.getRowIndex() + 1);
                            }
                            // Case type excel
                            String tenTH = "";
                            if (nextCell.getCellType() == CellType.NUMERIC) {
                                tenTH += nextCell.getNumericCellValue();
                            } else if (nextCell.getCellType() == CellType.STRING) {
                                tenTH += nextCell.getStringCellValue();
                            }
                            tenTH = tenTH.trim();
                            // Case type excel

                            if (tenTH.isEmpty()) {
                                return "Thương hiệu bị bỏ trống tại hàng " + (nextCell.getRowIndex() + 1);
                            }

                            ThuongHieu th = thuongHieu_Repository.findThuongHieuByName(tenTH);
                            System.out.println(th.getIdThuongHieu());
                            spct.setIdThuongHieu(th.getIdThuongHieu());
                            break;
                        case 2:
                            if (nextCell.getCellType() == CellType.BLANK || nextCell.getCellType() == CellType._NONE) {
                                return "Màu bị bỏ trống tại hàng " + (nextCell.getRowIndex() + 1);
                            }
                            // Case type excel
                            String tenMau = "";
                            if (nextCell.getCellType() == CellType.NUMERIC) {
                                tenMau += nextCell.getNumericCellValue();
                            } else if (nextCell.getCellType() == CellType.STRING) {
                                tenMau += nextCell.getStringCellValue();
                            }
                            tenMau = tenMau.trim();
                            // Case type excel

                            if (tenMau.isEmpty()) {
                                return "Màu bị bỏ trống tại hàng " + (nextCell.getRowIndex() + 1);
                            }

                            MauSac ms = mauSac_Reponsitory.findMauSacByName(tenMau);
                            spct.setIdMau(ms.getIdMau());
                            System.out.println(ms.getIdMau());
                            break;
                        case 3:
                            if (nextCell.getCellType() == CellType.BLANK || nextCell.getCellType() == CellType._NONE) {
                                return "Size bị bỏ trống tại hàng " + (nextCell.getRowIndex() + 1);
                            }
                            // Case type excel
                            String tenSize = "";
                            if (nextCell.getCellType() == CellType.NUMERIC) {
                                tenSize += nextCell.getNumericCellValue();
                            } else if (nextCell.getCellType() == CellType.STRING) {
                                tenSize += nextCell.getStringCellValue();
                            }
                            tenSize = tenSize.trim();
                            // Case type excel

                            if (tenSize.isEmpty()) {
                                return "Kích thước bị bỏ trống tại hàng " + (nextCell.getRowIndex() + 1);
                            }
                            Float size = Float.parseFloat(tenSize);
                            KichThuoc kt = kichThuoc_Repository.findKichCoByName(size);
                            spct.setIdKichThuoc(kt.getIdSize());
                            System.out.println(kt.getIdSize());
                            break;
                        case 4:
                            if (nextCell.getCellType() == CellType.BLANK || nextCell.getCellType() == CellType._NONE) {
                                return "Mã sản phẩm chi tiết bỏ trống tại hàng " + (nextCell.getRowIndex() + 1);
                            }

                            String maSPCT = "";
                            if (nextCell.getCellType() == CellType.NUMERIC) {
                                maSPCT += ((int) nextCell.getNumericCellValue());
                            } else if (nextCell.getCellType() == CellType.STRING) {
                                maSPCT += nextCell.getStringCellValue();
                            }
                            String id = sanPhamChiTiet_Repo.getMaSanPhamChiTietByMa(maSPCT);
                            if (maSPCT.equalsIgnoreCase(id)) {
                                return "Mã sản phẩm đã tồn tại" + (nextCell.getRowIndex() + 1);
                            } else {
                                if (maSPCT.isEmpty()) {
                                    return "Mã sản phẩm chi tiết bỏ trống tại hàng " + (nextCell.getRowIndex() + 1);
                                }
                                spct.setMaSPCT(maSPCT);
                            }
                            // Case type excel
                            break;
                        case 5:
                            String soLuongTon = "";
                            if (nextCell.getCellType() == CellType.NUMERIC) {
                                double numericValue = nextCell.getNumericCellValue();
                                if (numericValue % 1 == 0) {
                                    // Nếu là số nguyên, chuyển đổi thành số nguyên
                                    soLuongTon += (int) numericValue;
                                } else {
                                    // Nếu là số thực, giữ nguyên giá trị thực
                                    soLuongTon += numericValue;
                                }
                            } else if (nextCell.getCellType() == CellType.STRING) {
                                soLuongTon += nextCell.getStringCellValue();
                            }
                            soLuongTon = soLuongTon.trim();

                            if (soLuongTon.isEmpty()) {
                                return "Số lượng tồn bỏ trống tại hàng " + (nextCell.getRowIndex() + 1);
                            }

                            int soLuongTons;
                            try {
                                soLuongTons = Integer.parseInt(soLuongTon);
                            } catch (NumberFormatException e) {
                                return "Giá trị không hợp lệ tại hàng " + (nextCell.getRowIndex() + 1);
                            }

                            spct.setSoLuong(soLuongTons);
                            break;
                        case 6:
                            if (nextCell.getCellType() == CellType.BLANK || nextCell.getCellType() == CellType._NONE) {
                                return "Giá niêm yết bỏ trống tại hàng " + (nextCell.getRowIndex() + 1);
                            }
                            // Case type excel
                            String giaNY = "";
                            if (nextCell.getCellType() == CellType.NUMERIC) {
                                giaNY += nextCell.getNumericCellValue();
                            } else if (nextCell.getCellType() == CellType.STRING) {
                                giaNY += nextCell.getStringCellValue();
                            }
                            giaNY = giaNY.trim();
                            // Case type excel
                            if (giaNY.isEmpty()) {
                                return "Giá bán bỏ trống tại hàng " + (nextCell.getRowIndex() + 1);
                            }
                            Float giaNYs = Float.parseFloat(giaNY);
                            spct.setGiaNiemYet(giaNYs);
                            break;

                        case 7:
                            if (nextCell.getCellType() == CellType.BLANK || nextCell.getCellType() == CellType._NONE) {
                                return "Giá bán bỏ trống tại hàng " + (nextCell.getRowIndex() + 1);
                            }
                            // Case type excel
                            String giaBan = "";
                            if (nextCell.getCellType() == CellType.NUMERIC) {
                                giaBan += nextCell.getNumericCellValue();
                            } else if (nextCell.getCellType() == CellType.STRING) {
                                giaBan += nextCell.getStringCellValue();
                            }
                            giaBan = giaBan.trim();
                            // Case type excel
                            if (giaBan.isEmpty()) {
                                return "Giá bán bỏ trống tại hàng " + (nextCell.getRowIndex() + 1);
                            }
                            Float giaBans = Float.parseFloat(giaBan);
                            spct.setGiaBan(giaBans);
                            break;
                        case 8:
                            if (nextCell.getCellType() == CellType.BLANK || nextCell.getCellType() == CellType._NONE) {
                                return "Mô tả bỏ trống tại hàng " + (nextCell.getRowIndex() + 1);
                            }

                            String moTa = "";
                            if (nextCell.getCellType() == CellType.NUMERIC) {
                                moTa += ((int) nextCell.getNumericCellValue());
                            } else if (nextCell.getCellType() == CellType.STRING) {
                                moTa += nextCell.getStringCellValue();
                            }
                            moTa = moTa.trim();
                            spct.setMoTa(moTa);
                            break;
                        case 9:
                            if (nextCell.getCellType() == CellType.BLANK || nextCell.getCellType() == CellType._NONE) {
                                return "Trạng thái bỏ trống tại hàng " + (nextCell.getRowIndex() + 1);
                            }
                            // Case type excel
                            String trangThaiStr = "";
                            if (nextCell.getCellType() == CellType.NUMERIC) {
                                trangThaiStr += nextCell.getNumericCellValue();
                            } else if (nextCell.getCellType() == CellType.STRING) {
                                trangThaiStr += nextCell.getStringCellValue();
                            }
                            trangThaiStr = trangThaiStr.trim();
                            // Case type excel
                            if (trangThaiStr.isEmpty()) {
                                return "Trạng thái bỏ trống tại hàng " + (nextCell.getRowIndex() + 1);
                            }
                            Integer trangThai = null;
                            if (trangThaiStr.equalsIgnoreCase("Còn Hàng")) {
                                trangThai = 0;
                            }
                            if (trangThaiStr.equalsIgnoreCase("Tạm Hết")) {
                                trangThai = 1;
                            }
                            if (trangThaiStr.equalsIgnoreCase("Dừng Bán")) {
                                trangThai = 2;
                            }
                            if (trangThai == null) {
                                return "Vui lòng sửa lại trạng thái tại dòng " + (nextCell.getRowIndex() + 1) + "\nTrạng thái hợp lệ: Hoạt động, Không hoạt động";
                            }
                            spct.setTrangThai(trangThai);
                            break;
                        default:
                            break;
                    }

                }
                listCTDT.add(spct);
                System.out.println(listCTDT);
            }
            boolean save = sanPhamChiTiet_Repo.insertListBienThe(listCTDT);
            if (save == false) {
                ketQua = "Import thất bại";
                return ketQua;
            }

            workbook.close();
            inputStream.close();
            return "Import thành công";
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
            return null;
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }

    }

    Connection connect = DBConnection.getConnect();

    public List<SanPhamChiTiet> getAllResponse() {

        List<SanPhamChiTiet> list = new ArrayList<>();
        String sql = "select  CTSP.ID,CTSP.MaCTSP,SP.TenSP,TH.TenThuongHieu,S.TenSize,M.TenMau,CTSP.SoLuongTon, CTSP.GiaBan, CTSP.GiaNiemYet, CTSP.MoTa, CTSP.TrangThai , CTSP.ID as ID from CHI_TIET_SAN_PHAM as CTSP\n"
                + "join MAU as M on M.ID = CTSP.IdMau\n"
                + "join SIZE as S on S.ID = CTSP.IdSize\n"
                + "join THUONGHIEU as TH on TH.ID = CTSP.IdThuongHieu\n"
                + "join SANPHAM as SP on SP.ID = CTSP.IdSP\n";

        try {
            PreparedStatement pstm = connect.prepareCall(sql);
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                SanPham sanPham = new SanPham(rs.getString("TenSP"));
                MauSac mauSac = new MauSac(rs.getString("TenMau"));
                ThuongHieu thuongHieu = new ThuongHieu(rs.getString("TenThuongHieu"));
                KichThuoc kichThuoc = new KichThuoc(rs.getFloat("TenSize"));

                SanPhamChiTiet sanPhamChiTiet = new SanPhamChiTiet(rs.getLong("ID"), rs.getString("MaCTSP"), rs.getInt("SoLuongTon"), rs.getFloat("GiaBan"), rs.getFloat("GiaNiemYet"), rs.getInt("TrangThai"), rs.getString("MoTa"), mauSac, kichThuoc, thuongHieu, sanPham);
                list.add(sanPhamChiTiet);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return list;
    }

}
