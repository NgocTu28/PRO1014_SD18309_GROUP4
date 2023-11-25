/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Repository;

import Model.SanPhamChiTiet;
import Model.SanPhamChiTiet_T;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SanPhamChiTiet_T_Repository {

    Connection connect = DBConnection.getConnect();

    public boolean insertListBienThe(List<SanPhamChiTiet_T> list) {
        String query = " Insert  into CHI_TIET_SAN_PHAM (IdSP,IdThuongHieu,IdMau,IdSize,MaCTSP,SoLuongTon,GiaNiemYet,GiaBan,MoTa,TrangThai) Values (?,?,?,?,?,?,?,?,?,?)";
        boolean check = false;
        if (connect != null) {
            try {
                PreparedStatement pstm = connect.prepareStatement(query);
                for (SanPhamChiTiet_T spct : list) {
                    pstm.setLong(1, spct.getIdSanPham());
                    pstm.setLong(2, spct.getIdThuongHieu());
                    pstm.setLong(3, spct.getIdMau());
                    pstm.setLong(4, spct.getIdKichThuoc());
                    pstm.setString(5, spct.getMaSPCT());
                    pstm.setInt(6, spct.getSoLuong());
                    pstm.setFloat(7, spct.getGiaNiemYet());
                    pstm.setFloat(8, spct.getGiaBan());
                    pstm.setString(9, spct.getMoTa());
                    pstm.setInt(10, spct.getTrangThai());

                    check = pstm.execute();

                }
                pstm.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return check;

    }
}
