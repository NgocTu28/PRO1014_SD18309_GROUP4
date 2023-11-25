/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author vutu8
 */
public class SanPhamChiTiet_T {
    private Long idSPCT;
    private DotGiamGia_M idDGG;
    private String maSPCT;
    private int soLuong;
    private Float giaBan;
    private Float giaNiemYet;
    private int TrangThai;
    private String moTa;
    private Long idMau;
    private Long idKichThuoc;
    private Long idThuongHieu;
    private Long idSanPham;

    public SanPhamChiTiet_T() {
    }

    public SanPhamChiTiet_T(Long idSPCT, DotGiamGia_M idDGG, String maSPCT, int soLuong, Float giaBan, Float giaNiemYet, int TrangThai, String moTa, Long idMau, Long idKichThuoc, Long idThuongHieu, Long idSanPham) {
        this.idSPCT = idSPCT;
        this.idDGG = idDGG;
        this.maSPCT = maSPCT;
        this.soLuong = soLuong;
        this.giaBan = giaBan;
        this.giaNiemYet = giaNiemYet;
        this.TrangThai = TrangThai;
        this.moTa = moTa;
        this.idMau = idMau;
        this.idKichThuoc = idKichThuoc;
        this.idThuongHieu = idThuongHieu;
        this.idSanPham = idSanPham;
    }
    
    public SanPhamChiTiet_T(Long idSPCT, String maSPCT, int soLuong, Float giaBan, Float giaNiemYet, int TrangThai, String moTa, Long idMau, Long idKichThuoc, Long idThuongHieu, Long idSanPham) {
        this.idSPCT = idSPCT;
        this.maSPCT = maSPCT;
        this.soLuong = soLuong;
        this.giaBan = giaBan;
        this.giaNiemYet = giaNiemYet;
        this.TrangThai = TrangThai;
        this.moTa = moTa;
        this.idMau = idMau;
        this.idKichThuoc = idKichThuoc;
        this.idThuongHieu = idThuongHieu;
        this.idSanPham = idSanPham;
    }

    public Long getIdSPCT() {
        return idSPCT;
    }

    public void setIdSPCT(Long idSPCT) {
        this.idSPCT = idSPCT;
    }

    public DotGiamGia_M getIdDGG() {
        return idDGG;
    }

    public void setIdDGG(DotGiamGia_M idDGG) {
        this.idDGG = idDGG;
    }

    public String getMaSPCT() {
        return maSPCT;
    }

    public void setMaSPCT(String maSPCT) {
        this.maSPCT = maSPCT;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public Float getGiaBan() {
        return giaBan;
    }

    public void setGiaBan(Float giaBan) {
        this.giaBan = giaBan;
    }

    public Float getGiaNiemYet() {
        return giaNiemYet;
    }

    public void setGiaNiemYet(Float giaNiemYet) {
        this.giaNiemYet = giaNiemYet;
    }

    public int getTrangThai() {
        return TrangThai;
    }

    public void setTrangThai(int TrangThai) {
        this.TrangThai = TrangThai;
    }

    public String getMoTa() {
        return moTa;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }

    public Long getIdMau() {
        return idMau;
    }

    public void setIdMau(Long idMau) {
        this.idMau = idMau;
    }

    public Long getIdKichThuoc() {
        return idKichThuoc;
    }

    public void setIdKichThuoc(Long idKichThuoc) {
        this.idKichThuoc = idKichThuoc;
    }

    public Long getIdThuongHieu() {
        return idThuongHieu;
    }

    public void setIdThuongHieu(Long idThuongHieu) {
        this.idThuongHieu = idThuongHieu;
    }

    public Long getIdSanPham() {
        return idSanPham;
    }

    public void setIdSanPham(Long idSanPham) {
        this.idSanPham = idSanPham;
    }
    
    
}
