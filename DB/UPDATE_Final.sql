---CREATE  DATABASE SuperSportSneakers
GO
USE SuperSportSneakers
GO
--- SAN PHAM
IF OBJECT_ID('SANPHAM') IS NOT NULL
	DROP TABLE SANPHAM
CREATE TABLE SANPHAM 
(
	ID BIGINT IDENTITY (1,1) NOT NULL,
	MaSP VARCHAR(15)  NOT NULL ,
	TenSP NVARCHAR(50) NOT NULL,
	NgayTao DATE DEFAULT GETDATE(),
	TrangThai INT DEFAULT 0 NOT NULL --- 0 - Đang Kinh Kinh Doanh 1 - Ngừng Kinh Doanh 2 - Tạm Hết Hàng
	CONSTRAINT PK_SANPHAM PRIMARY KEY (ID)
)
--- THEM SANPHAM
INSERT INTO SANPHAM (MaSP, TenSP)
VALUES 
('SP00001', 'Nike Air Force 1'),
('SP00002', 'Adidas Superstar'),
('SP00003', 'Converse Chuck Taylor'),
('SP00004', 'Vans Old Skool'),
('SP00005', 'Puma Suede');
SELECT * FROM SANPHAM;

IF OBJECT_ID('THUONGHIEU') IS NOT NULL
	DROP TABLE THUONGHIEU
CREATE TABLE THUONGHIEU 
(
	 ID BIGINT IDENTITY (1,1) NOT NULL,
	 MaThuongHieu VARCHAR(15)  NOT NULL ,
	 TenThuongHieu NVARCHAR(50) NOT NULL,
     TrangThai INT DEFAULT 0 NOT NULL , -- 0 ĐANG HỢP TÁC , 1 NGỪNG HỢP TÁC
	 CONSTRAINT PK_THUONGHIEU PRIMARY KEY (ID)
)
-- THEM THUONG HIEU
INSERT INTO THUONGHIEU (MaThuongHieu, TenThuongHieu)
VALUES 
('TH001', 'Nike'),
('TH002', 'Adidas'),
('TH003', 'Converse'),
('TH004', 'Vans'),
('TH005', 'Puma');
SELECT * FROM THUONGHIEU

--- MAU
IF OBJECT_ID('MAU') IS NOT NULL
	DROP TABLE MAU
CREATE TABLE MAU 
(
	 ID BIGINT IDENTITY (1,1) NOT NULL,
	 MaMau VARCHAR(15)  NOT NULL ,
	 TenMau NVARCHAR(50) NOT NULL,
     TrangThai INT DEFAULT 0 NOT NULL , -- 0 CÒN DÙNG , 1 KHÔNG DÙNG
	 CONSTRAINT PK_MAU PRIMARY KEY (ID)
)
INSERT INTO MAU (MaMau, TenMau )
VALUES 
('MAU0001', N'Đỏ'),
('MAU0002', N'Xanh'),
('MAU0003', N'Trắng'),
('MAU0004', N'Vàng'),
('MAU0005', N'Đen');

--- SIZE 
IF OBJECT_ID('SIZE') IS NOT NULL
	DROP TABLE SIZE
CREATE TABLE SIZE 
(
	ID BIGINT IDENTITY (1,1) NOT NULL,
	MaSize VARCHAR(15)  NOT NULL ,
	TenSize DECIMAL(3, 1) NOT NULL,
    TrangThai INT DEFAULT 0 NOT NULL -- 0 CÒN DÙNG , 1 KHÔNG DÙNG
	CONSTRAINT PK_SIZE PRIMARY KEY (ID)
)
-- them SIZE
INSERT INTO SIZE (MaSize, TenSize)
VALUES 
('KT001', 34.5),
('KT002', 35),
('KT003', 35.5),
('KT004', 36),
('KT005', 36.5),
('KT006', 37),
('KT007', 37.5),
('KT008', 38),
('KT009', 38.5),
('KT010', 39);


--- NHAN VIEN
IF OBJECT_ID('NHANVIEN') IS NOT NULL
	DROP TABLE NHANVIEN
CREATE TABLE NHANVIEN 
(
	ID BIGINT IDENTITY (1,1) NOT NULL,
	MaNhanVien VARCHAR(10)  NULL,
	HoVaTen NVARCHAR(50) NULL,
	MatKhau VARCHAR(10) NULL,
	NgaySinh DATE NULL,
	CCCD VARCHAR(12) NULL ,
	Email VARCHAR(50) NULL,
	DiaChi NVARCHAR(100) NULL,
	SDT VARCHAR(15) NULL,
	VaiTro BIT  DEFAULT 0 ,--- 0 NHÂN VIÊN , 1 LÀ QUẢN LÍ
	NgayTao DATE DEFAULT GETDATE(),
	TrangThai BIT DEFAULT 1 , -- 0 ĐÃ NGHỈ 1 ĐANG LÀM
	CONSTRAINT PK_NHANVIEN PRIMARY KEY (ID),
)
--- THEM NHAN VIEN
-- Thêm 10 nhân viên vào NHANVIEN
INSERT INTO NHANVIEN (MaNhanVien, HoVaTen, MatKhau, NgaySinh, CCCD, Email, DiaChi, SDT, VaiTro, NgayTao, TrangThai)
VALUES 
('NV001', N'Nguyễn Văn A', 'pass123', '1990-01-15', '123456789012', 'nva@example.com', N'123 Đường ABC, Quận XYZ', '0901234567', 0, GETDATE(), 1),
('NV002', N'Trần Thị B', 'pass456', '1995-05-20', '234567890123', 'ttb@example.com', N'456 Đường DEF, Quận LMN', '0912345678', 0, GETDATE(), 1),
('NV003', N'Phạm Văn C', 'pass789', '1988-08-10', '345678901234', 'pvc@example.com', N'789 Đường GHI, Quận OPQ', '0923456789', 0, GETDATE(), 1),
('NV004', N'Lê Thị D', 'passABC', '1992-12-25', '456789012345', 'ltd@example.com', N'101 Đường JKL, Quận RST', '0934567890', 0, GETDATE(), 1),
('NV005', N'Ngô Văn E', 'passDEF', '1985-04-30', '567890123456', 'nve@example.com', N'202 Đường UVW, Quận XYZ', '0945678901', 0, GETDATE(), 1),
('NV006', N'Lý Thị F', 'passGHI', '1998-07-05', '678901234567', 'ltf@example.com', N'303 Đường LMN, Quận OPQ', '0956789012', 0, GETDATE(), 1),
('NV007', N'Nguyễn Văn Mạnh', 'passLMN', '1980-11-12', '789012345678', 'hvm@example.com', N'404 Đường OPQ, Quận RST', '0967890123', 1, GETDATE(), 1),
('NV008', N'Tạ Bá Hòa', 'passOPQ', '1983-09-18', '890123456789', 'tbh@example.com', N'505 Đường RST, Quận UVW', '0978901234', 1, GETDATE(), 1),
('NV009', N'Trương Văn I', 'passRST', '1993-03-08', '901234567890', 'tvi@example.com', N'606 Đường XYZ, Quận ABC', '0989012345', 0, GETDATE(), 1),
('NV010', N'Vũ Thị K', 'passUVW', '1996-06-22', '012345678901', 'vtk@example.com', N'707 Đường ABC, Quận DEF', '0990123456', 0, GETDATE(), 1);


SELECT * FROM NHANVIEN
-- ĐỢT GIẢM GIÁ
IF OBJECT_ID('DOT_GIAM_GIA') IS NOT NULL
	DROP TABLE DOT_GIAM_GIA
CREATE TABLE DOT_GIAM_GIA 
(
	ID BIGINT IDENTITY (1,1) NOT NULL,
	IdNV BIGINT NOT NULL,
	MaDGG VARCHAR(10)  NULL ,
	TenDGG NVARCHAR(100) NULL,
	Loai INT  NULL ,--- 0 LÀ % 1 LÀ VND
	GiaTri MONEY NULL, 
	NgayBatDau DATETIME NULL,
	NgayKetThuc DATETIME NULL,
	MoTa NVARCHAR(250) NULL,
	NgayTao DATE DEFAULT GETDATE(),
	TrangThai INT NULL , ---0 SẮP ĐẾN , 1 ĐANG ĐƯỢC ÁP DỤNG , 2 KẾT THÚC ĐỢT GIẢM GIÁ 
	CONSTRAINT PK_DOT_GIAM_GIA PRIMARY KEY (ID),
	CONSTRAINT FK_DOT_GIAM_GIA_NHANVIEN FOREIGN KEY (IdNV) REFERENCES NHANVIEN (ID)
)


SELECT PGG.ID , nv.ID AS IDNV , NV.HoVaTen , MaPhieu , TenPhieu , LoaiPhieu , GiaTri , 
SoLuongPhieu , DonToiThieu , NgayBatDau , NgayKetThuc , PGG.NgayTao , MoTa , PGG.TrangThai FROM PHIEU_GIAM_GIA  AS PGG
JOIN NHANVIEN AS NV ON NV.ID = PGG.IdNV
WHERE LoaiPhieu = 1 AND PGG.TrangThai = 1  order by ID OFFSET 0 ROWS FETCH NEXT 4 ROWS ONLY

SELECT * FROM DOT_GIAM_GIA
--- THEM ĐỢT GIẢM GIÁ 
-- Thêm 10 bản ghi giả mạo vào bảng DOT_GIAM_GIA
INSERT INTO DOT_GIAM_GIA (IdNV, MaDGG, TenDGG, Loai, GiaTri, NgayBatDau, NgayKetThuc, MoTa, NgayTao, TrangThai)
VALUES 
(1, 'DGG001', N'Giảm giá mùa hè', 0, 20, '2023-07-15', '2023-08-15', N'Giảm giá cho mùa hè 2023 20% sản phẩm', GETDATE(), 0),
(2, 'DGG002', N'Khuyến mãi tháng 8', 1, 50000, '2023-08-15', '2023-08-31', N'Ưu đãi đặc biệt trong tháng 8 giảm 50000k', GETDATE(), 2),
(3, 'DGG003', N'Chào mừng năm mới', 0, 15, '2024-01-15', '2024-02-15', N'Giảm giá đón chào năm mới giảm 15%', GETDATE(), 3),
(4, 'DGG004', N'Big Sale', 1, 100000, '2023-09-15', '2023-10-15', N'Ưu đãi lớn trong chương trình Big Sale giảm 100000k', GETDATE(), 2),
(5, 'DGG005', N'Halloween Special', 0, 25, '2023-10-15', '2023-10-31', N'Giảm giá đặc biệt cho Halloween 25%', GETDATE(), 2),
(6, 'DGG006', N'Black Friday', 1, 150000, '2023-11-15', '2023-11-30', N'Chương trình khuyến mãi Black Friday 150000k', GETDATE(), 0),
(7, 'DGG007', N'Cyber Monday', 0, 30, '2023-11-30', '2023-12-15', N'Giảm giá trong sự kiện Cyber Monday  30% sản phẩm', GETDATE(), 0),
(8, 'DGG008', N'Year-End Sale', 1, 200000, '2023-12-15', '2023-12-31', N'Ưu đãi cuối năm 200000k/1 sp', GETDATE(), 3),
(9, 'DGG009', N'Tết Nguyên Đán', 0, 10, '2024-01-15', '2024-02-15', N'Giảm giá dịp Tết Nguyên Đán 10%/ 1sp', GETDATE(), 3),
(10, 'DGG010', N'Valentine Special', 1, 75000, '2024-02-01', '2024-02-14', N'Ưu đãi đặc biệt cho Valentine 75.000 / 1sp', GETDATE(), 0),
(1, 'DGG011', N'Khuyến mãi tháng 11', 1, 50000, '2023-11-15', '2023-11-30', N'Ưu đãi đặc biệt trong tháng 11 giảm 50000k /1sp', GETDATE(), 1);


SELECT CTSP.ID AS IdCTSP, CTSP.MaCTSP , SP.TenSP , CTSP.GiaNiemYet , TH.TenThuongHieu , S.TenSize , M.TenMau
FROM CHI_TIET_SAN_PHAM AS CTSP
JOIN SANPHAM AS SP ON SP.ID  = CTSP.IdSP
JOIN SIZE AS S ON S.ID = CTSP.IdSize
JOIN THUONGHIEU AS TH ON TH.ID = CTSP.IdThuongHieu
JOIN MAU AS M ON M.ID = CTSP.IdMau
WHERE CTSP.TrangThai = 0 -- Chi tiết sản phẩm còn hàng
    AND NOT EXISTS (
        SELECT 1
        FROM CHI_TIET_DGG AS CTDGG
        JOIN DOT_GIAM_GIA AS DGG ON CTDGG.IdDGG = DGG.ID
        WHERE CTDGG.IdCTSP = CTSP.ID
            AND (DGG.NgayBatDau BETWEEN '2023-07-16' AND '2023-12-01'
                 OR DGG.NgayKetThuc BETWEEN '2023-07-16' AND '2023-12-01'
                 OR (DGG.NgayBatDau <= '2023-07-16' AND DGG.NgayKetThuc >= '2023-12-01'))
    );
	SELECT * FROM DOT_GIAM_GIA
	SELECT NV.HoVaTen , MaDGG , TenDGG, Loai , GiaTri , NgayBatDau , NgayKetThuc , DGG.NgayTao, MoTa , DGG.TrangThai FROM DOT_GIAM_GIA AS DGG
	JOIN NHANVIEN AS NV ON NV.ID = DGG.IdNV


	SELECT CTSP.ID AS IdCTSP, CTSP.MaCTSP, SP.TenSP, CTSP.GiaBan, TH.TenThuongHieu, S.TenSize, M.TenMau
FROM CHI_TIET_SAN_PHAM AS CTSP
JOIN SANPHAM AS SP ON SP.ID = CTSP.IdSP
JOIN SIZE AS S ON S.ID = CTSP.IdSize
JOIN THUONGHIEU AS TH ON TH.ID = CTSP.IdThuongHieu
JOIN MAU AS M ON M.ID = CTSP.IdMau
WHERE CTSP.TrangThai = 0 -- Chi tiết sản phẩm còn hàng
    AND NOT EXISTS (
        SELECT 1
        FROM CHI_TIET_DGG AS CTDGG
        JOIN DOT_GIAM_GIA AS DGG ON CTDGG.IdDGG = DGG.ID
        WHERE CTDGG.IdCTSP = CTSP.ID
            AND (DGG.NgayBatDau BETWEEN ? AND ?
                OR DGG.NgayKetThuc BETWEEN ? AND ?
                OR (DGG.NgayBatDau <= ? AND DGG.NgayKetThuc >= ?))
    );





	SELECT PGG.ID , NV.HoVaTen , nv.ID as IDNV , MaPhieu , TenPhieu , LoaiPhieu , GiaTri , SoLuongPhieu ,  DonToiThieu
NgayBatDau , NgayKetThuc ,  PGG.NgayTao , MoTa , PGG.TrangThai FROM PHIEU_GIAM_GIA AS PGG
JOIN NHANVIEN AS NV ON NV.ID = PGG.IdNV
SELECT * FROM PHIEU_GIAM_GIA
INSERT INTO PHIEU_GIAM_GIA (IdNV , MaPhieu , TenPhieu , LoaiPhieu , GiaTri , SoLuongPhieu , DonToiThieu , NgayBatDau , NgayKetThuc , MoTa)
VALUES (? , ? , ? , ? , ? , ? , ? , ? , ? , ?)
UPDATE PHIEU_GIAM_GIA
SET TenPhieu = ? , LoaiPhieu = ? , GiaTri =? , SoLuongPhieu = ? , DonToiThieu = ? , NgayBatDau = ? , NgayKetThuc = ? , MoTa =? 
WHERE MaPhieu LIKE ?

	select * from CHI_TIET_DGG
	INSERT INTO CHI_TIET_DGG(IdDGG , IdCTSP , MsCTSP , DonGia , DonGiaConLai , GiaTriGiam )
	VALUES (1 ,1 , 'CTSP0001' , 1200000 ,1200000 , 20 ),
	(6 ,2 , 'CTSP0002' , 1100000 ,1100000 , 150000 )
	SELECT * FROM CHI_TIET_SAN_PHAM
-------------------------------------------------------------------------------------------------
--- CHI TIẾT SẢN PHẨM

IF OBJECT_ID('CHI_TIET_SAN_PHAM') IS NOT NULL
	DROP TABLE CHI_TIET_SAN_PHAM
CREATE TABLE CHI_TIET_SAN_PHAM
(
	ID BIGINT IDENTITY (1,1) NOT NULL,
	IdSP BIGINT NOT NULL ,
	IdThuongHieu BIGINT NOT NULL,
	IdMau BIGINT NOT NULL,
	IdSize BIGINT NOT NULL,
	--QR VARCHAR(100) NULL,
	IdDGG BIGINT NULL,
	MaCTSP VARCHAR(15) NOT NULL,
	SoLuongTon INT NOT NULL ,
	GiaNiemYet MONEY NOT NULL , -- ÁNH XẠ JAVA => BigDecimal
	GiaBan MONEY NOT NULL,
	MoTa NVARCHAR (250) NOT NULL ,
	NgayTao DATE DEFAULT GETDATE(),
	TrangThai INT NOT NULL DEFAULT 0 , -- 0 CÒN HÀNG , 1 TẠM HẾT , 2 DỪNG BÁN
	CONSTRAINT PK_CHI_TIET_SAN_PHAM PRIMARY KEY (ID),
	CONSTRAINT FK_CHI_TIET_SAN_PHAM_SANPHAM FOREIGN KEY (IdSP) REFERENCES SANPHAM (ID),
	CONSTRAINT FK_CHI_TIET_SAN_PHAM_THUONGHIEU FOREIGN KEY (IdThuongHieu) REFERENCES THUONGHIEU (ID),
	CONSTRAINT FK_CHI_TIET_SAN_PHAM_MAU FOREIGN KEY (IdMau) REFERENCES MAU (ID),
	CONSTRAINT FK_CHI_TIET_SAN_PHAM_SIZE FOREIGN KEY (IdSize) REFERENCES SIZE (ID),
	CONSTRAINT FK_CHI_TIET_SAN_PHAM_DOT_GIAM_GIA FOREIGN KEY (IdDGG) REFERENCES DOT_GIAM_GIA (ID),
)
ALTER TABLE CHI_TIET_SAN_PHAM
ADD  QR VARCHAR(100) NULL;

--- THEM CHI TIET SAN PHAM
INSERT INTO CHI_TIET_SAN_PHAM (IdSP, IdThuongHieu, IdMau, IdSize, MaCTSP, SoLuongTon, GiaNiemYet, GiaBan, MoTa, NgayTao, TrangThai)
VALUES 
(1, 1, 1, 1, 'CTSP0001', 50, 1200000.00, 1200000.00, N'Mô tả CTSP', GETDATE(), 0),
(2, 2, 2, 2, 'CTSP0002', 30, 1100000.00, 1100000.00, N'Mô tả CTSP', GETDATE(), 0),
(3, 3, 3, 3, 'CTSP0003', 20, 1000000.00, 1000000.00, N'Mô tả CTSP', GETDATE(), 0),
(4, 4, 4, 4, 'CTSP0004', 25, 1050000.00, 1050000.00, N'Mô tả CTSP', GETDATE(), 0),
(5, 5, 5, 5, 'CTSP0005', 40, 1150000.00, 1150000.00, N'Mô tả CTSP', GETDATE(), 0),
(5, 1, 2, 3, 'CTSP0006', 55, 1300000.00, 1300000.00, N'Mô tả CTSP', GETDATE(), 0),
(4, 2, 3, 4, 'CTSP0007', 35, 1250000.00, 1250000.00, N'Mô tả CTSP', GETDATE(), 0),
(3, 3, 4, 6, 'CTSP0008', 40, 1180000.00, 1180000.00, N'Mô tả CTSP', GETDATE(), 0),
(2, 4, 5, 1, 'CTSP0009', 30, 1100000.00, 1100000.00, N'Mô tả CTSP', GETDATE(), 0),
(1, 5, 1, 2, 'CTSP0010', 25, 1050000.00, 1050000.00, N'Mô tả CTSP', GETDATE(), 0),
(2, 1, 2, 3, 'CTSP0011', 60, 1400000.00, 1400000.00, N'Mô tả CTSP', GETDATE(), 0),
(3, 2, 3, 10, 'CTSP0012', 45, 1350000.00, 1350000.00, N'Mô tả CTSP', GETDATE(), 0),
(4, 3, 4, 8, 'CTSP0013', 50, 1200000.00, 1200000.00, N'Mô tả CTSP', GETDATE(), 0),
(5, 4, 5, 7, 'CTSP0014', 38, 1120000.00, 1120000.00, N'Mô tả CTSP', GETDATE(), 0),
(1, 5, 1, 2, 'CTSP0015', 42, 1150000.00, 1150000.00, N'Mô tả CTSP', GETDATE(), 0),
(3, 1, 2, 3, 'CTSP0016', 48, 1220000.00, 1220000.00, N'Mô tả CTSP', GETDATE(), 0),
(4, 2, 3, 9, 'CTSP0017', 32, 1280000.00, 1280000.00, N'Mô tả CTSP', GETDATE(), 0),
(5, 3, 4, 5, 'CTSP0018', 28, 1150000.00, 1150000.00, N'Mô tả CTSP', GETDATE(), 0),
(1, 4, 5, 8, 'CTSP0019', 22, 1100000.00, 1100000.00, N'Mô tả CTSP', GETDATE(), 0),
(2, 5, 1, 6, 'CTSP0020', 65, 1380000.00, 1380000.00, N'Mô tả CTSP', GETDATE(), 0),
(2, 1, 2, 3, 'CTSP0021', 48, 1220000.00, 1220000.00, N'Mô tả CTSP', GETDATE(), 0),
(1, 2, 3, 9, 'CTSP0022', 32, 1280000.00, 1280000.00, N'Mô tả CTSP', GETDATE(), 0),
(5, 3, 4, 5, 'CTSP0023', 28, 1150000.00, 1150000.00, N'Mô tả CTSP', GETDATE(), 0),
(4, 4, 5, 8, 'CTSP0024', 22, 1100000.00, 1100000.00, N'Mô tả CTSP', GETDATE(), 0),
(3, 5, 1, 6, 'CTSP0025', 65, 1380000.00, 1380000.00, N'Mô tả CTSP', GETDATE(), 0),
(3, 1, 2, 4, 'CTSP0026', 48, 1220000.00, 1220000.00, N'Mô tả CTSP', GETDATE(), 0),
(4, 2, 3, 6, 'CTSP0027', 32, 1280000.00, 1280000.00, N'Mô tả CTSP', GETDATE(), 0),
(5, 3, 4, 10, 'CTSP0028', 28, 1150000.00, 1150000.00, N'Mô tả CTSP', GETDATE(), 0),
(1, 4, 5, 10, 'CTSP0029', 22, 1100000.00, 1100000.00, N'Mô tả CTSP', GETDATE(), 0),
(2, 5, 1, 7, 'CTSP0023', 65, 1380000.00, 1380000.00, N'Mô tả CTSP', GETDATE(), 0);

SELECT * FROM CHI_TIET_SAN_PHAM

-- ++++++ BẢNG THÊN MỚI


--- CT ĐỢT GIẢM GIÁ 

-- CSTP SẼ KO LÀM KHÓA NGOẠI XỬ LÝ JAVA TỰ THÊM ID VÀ MÃ CTSP VÀ TÍNH GIÁ CÒN LẠI , GIÁ TRỊ GIẢM VD % THÌ PHẢI QUY RA ĐƯỢC BN TIỀN
IF OBJECT_ID('CHI_TIET_DGG') IS NOT NULL
	DROP TABLE CHI_TIET_DGG
CREATE TABLE CHI_TIET_DGG 
(
	ID BIGINT IDENTITY (1,1) NOT NULL,
	IdDGG BIGINT NOT NULL,
	IdCTSP BIGINT NULL,
	MaCTSP VARCHAR(20) NULL,
	DonGia MONEY NULL,
	DonGiaConLai MONEY NULL,
	GiaTriGiam FLOAT NULL, 
	TrangThai INT DEFAULT 0 , --- 0 CHUA XOA , 1 XOA
	CONSTRAINT PK_CHI_TIET_DGG PRIMARY KEY (ID),
	CONSTRAINT FK_CHI_TIET_DGG_DOT_GIAM_GIA FOREIGN KEY (IdDGG) REFERENCES DOT_GIAM_GIA (ID),
)
SELECT IdCTSP , IdDGG , MaDGG, CTDGG.DonGia , GiaTriGiam , GiaTri FROM CHI_TIET_DGG AS CTDGG
                    JOIN DOT_GIAM_GIA AS DGG ON DGG.ID = CTDGG.IdDGG
                    WHERE MaDGG LIKE 'DGG032'

				SELECT HD.ID AS IDHD, MaHoaDon, KH.ID AS IDKH, HD.NgayTao, TenKhachHang , hd.TrangThai
                    FROM HOADON AS HD
                    JOIN KHACHHANG AS KH ON KH.ID = HD.IdKH
                    WHERE HD.TrangThai =3 or  HD.TrangThai = 0 AND NOT EXISTS (
                       SELECT 1 FROM PHIEUGIAOHANG AS PGH WHERE PGH.IDHoaDon = HD.ID
                    );
---- kêt thúc phần chỉ sửa
IF OBJECT_ID('KHACHHANG') IS NOT NULL
	DROP TABLE KHACHHANG
CREATE TABLE KHACHHANG
(
	ID BIGINT IDENTITY (1,1) NOT NULL,
	IdNV BIGINT NOT NULL,
	MaKhachHang VARCHAR(20) NULL,
	TenKhachHang NVARCHAR(50) NULL,
	SDT VARCHAR(15) NOT NULL,
	NgaySinh DATE NULL,
	GioiTinh BIT NULL , --- 0  NU , 1 NAM
	Email VARCHAR(50) NULL,
	DiaChi NVARCHAR(100) NULL,
	Diem INT DEFAULT 0 ,
	CapBac INT DEFAULT 0 , -- O DONG 1 BAC 2 VANG 
	NgayTao DATE DEFAULT GETDATE(),
	TrangThai BIT DEFAULT 1 , --- 1 CÒN HOẠT ĐỘNG 0 DÙNG HOẶT ĐỌNG
	CONSTRAINT PK_KHACHHANG PRIMARY KEY (ID) ,
	CONSTRAINT FK_KHACHHANG_NHANVIEN FOREIGN KEY (IdNV) REFERENCES NHANVIEN (ID)
)


--- THEM KHACH HANG
-- Thêm 10 khách hàng giả mạo vào bảng KHACHHANG
INSERT INTO KHACHHANG (IdNV, MaKhachHang, TenKhachHang, SDT, NgaySinh, Email, GioiTinh, DiaChi, Diem, CapBac, NgayTao, TrangThai)
VALUES 
(1, 'KH000', N'Khách bán lẻ', '....', '1990-05-20', '...', 0, N'....', 0, 3, GETDATE(), 1),
(1, 'KH001', N'Nguyễn Thị Ánh', '0901234567', '1990-05-20', 'manh@gmail.com', 0, N'123 Đường ABC, Quận XYZ', 100, 2, GETDATE(), 1),
(2, 'KH002', N'Trần Văn Bình', '0912345678', '1985-08-10', 'manh@gmail.com', 1, N'456 Đường DEF, Quận LMN', 50, 1, GETDATE(), 1),
(3, 'KH003', N'Phạm Minh Châu', '0923456789', '1992-12-25', 'manh@gmail.com', 1, N'789 Đường GHI, Quận OPQ', 200, 2, GETDATE(), 1),
(4, 'KH004', N'Lê Thị Dương', '0934567890', '1988-04-30', 'manh@gmail.com', 0, N'101 Đường JKL, Quận RST', 150, 1, GETDATE(), 1),
(5, 'KH005', N'Ngô Hồng Êm', '0945678901', '1995-07-05', 'manh@gmail.com', 0, N'202 Đường UVW, Quận XYZ', 80, 1, GETDATE(), 1),
(6, 'KH006', N'Lý Minh Giao', '0956789012', '1980-11-12', 'manh@gmail.com', 1, N'303 Đường LMN, Quận OPQ', 120, 2, GETDATE(), 1),
(7, 'KH007', N'Nguyễn Thị Hương', '0967890123', '1983-09-18', 'manh@gmail.com', 0, N'404 Đường OPQ, Quận RST', 90, 1, GETDATE(), 1),
(8, 'KH008', N'Tạ Đình Hòa', '0978901234', '1998-03-08', 'manh@gmail.com', 1, N'505 Đường RST, Quận UVW', 180, 2, GETDATE(), 1),
(9, 'KH009', N'Trương Hoàng I', '0989012345', '1993-06-22', 'manh@gmail.com', 1, N'606 Đường XYZ, Quận ABC', 60, 1, GETDATE(), 1),
(10, 'KH010', N'Vũ Thị Kiều', '0990123456', '1996-09-15', 'manh@gmail.com', 0, N'707 Đường ABC, Quận DEF', 130, 2, GETDATE(), 1);

--- PHIEU GIAM GIA
IF OBJECT_ID('PHIEU_GIAM_GIA') IS NOT NULL
	DROP TABLE PHIEU_GIAM_GIA
CREATE TABLE PHIEU_GIAM_GIA 
(
	ID BIGINT IDENTITY (1,1) NOT NULL,
	IdNV BIGINT NOT NULL,
	MaPhieu NVARCHAR(20) NULL ,
	TenPhieu NVARCHAR(100) NULL,
	LoaiPhieu BIT NULL , -- 0 LÀ % , 1 VND
	GiaTri FLOAT NULL ,-- ++=> sửa lại thành FLoat
	SoLuongPhieu INT NULL,
	DonToiThieu FLOAT NULL , --- ÁNH XẠ JAVA => BigDecimal
	NgayBatDau DATETIME NULL,
	NgayKetThuc DATETIME NULL,
	NgayTao DATE DEFAULT GETDATE(),
	MoTa NVARCHAR(250) NULL,
	TrangThai INT DEFAULT 0 NULL , -- 0 SẮP ĐẾN , 1 ĐANG DIỄN RA , 2 ĐÃ KẾT THÚC , 3 ĐANG ĐỢI (TẦM MẤY THÁNG MỚI ĐẾN)
	CONSTRAINT PK_PHIEU_GIAM_GIA PRIMARY KEY (ID),
	CONSTRAINT FK_PHIEU_GIAM_GIA_NHANVIEN FOREIGN KEY (IdNV) REFERENCES NHANVIEN (ID)
)


-- Thêm 10 phiếu giảm giá giả mạo vào bảng PHIEU_GIAM_GIA
INSERT INTO PHIEU_GIAM_GIA (IdNV, MaPhieu, TenPhieu, LoaiPhieu, GiaTri, SoLuongPhieu, DonToiThieu, NgayBatDau, NgayKetThuc, MoTa, TrangThai)
VALUES 
(1, 'PGG001', N'Phiếu giảm giá mùa hè', 0, 20, 100, 500000, '2023-07-15T00:00:00', '2023-08-15T23:59:59', N'Áp dụng cho mùa hè 2023 20% hóa đơn', 2),
(2, 'PGG002', N'Phiếu giảm giá tháng 8', 1, 50000, 50, 1000000, '2023-08-01T00:00:00', '2023-08-31T23:59:59', N'Ưu đãi đặc biệt trong tháng 8 50000k / hóa đơn', 2),
(3, 'PGG003', N'Phiếu khuyến mãi năm mới', 0, 15, 200, 2000000, '2024-01-01T00:00:00', '2024-01-15T23:59:59', N'Chào đón năm mới 2024 15% /hóa đơn', 3),
(4, 'PGG004', N'Phiếu giảm giá Big Sale', 1, 100000, 30, 1500000, '2023-09-01T00:00:00', '2023-09-30T23:59:59', N'Ưu đãi lớn trong chương trình Big Sale 100000k / hóa đơn', 2),
(5, 'PGG005', N'Phiếu giảm giá Halloween', 0, 25, 20, 800000, '2023-10-15T00:00:00', '2023-10-31T23:59:59', N'Giảm giá đặc biệt cho Halloween 25% / hóa đơn', 2),
(6, 'PGG006', N'Phiếu khuyến mãi Black Friday', 1, 150000, 10, 2000000, '2023-11-25T00:00:00', '2023-11-30T23:59:59', N'Chương trình khuyến mãi Black Friday 150000k / hóa đơn', 0),
(7, 'PGG007', N'Phiếu giảm giá Cyber Monday', 0, 30, 15, 3000000, '2023-11-30T00:00:00', '2023-12-15T23:59:59', N'Giảm giá trong sự kiện Cyber Monday 30% / hóa đơn', 0),
(8, 'PGG008', N'Phiếu giảm giá Year-End Sale', 1, 200000, 5, 500000, '2023-11-12T00:00:00', '2023-12-31T23:59:59', N'Ưu đãi cuối năm 200000k / hóa đơn', 1),
(9, 'PGG009', N'Phiếu giảm giá Tết Nguyên Đán', 0, 10, 25, 1000000, '2024-01-15T00:00:00', '2024-02-15T23:59:59', N'Giảm giá dịp Tết Nguyên Đán 10 % / hóa đơn', 3),
(10, 'PGG010', N'Phiếu giảm giá Valentine', 1, 75000, 40, 1200000, '2024-02-01T00:00:00', '2024-02-14T23:59:59', N'Ưu đãi đặc biệt cho Valentine 75000k / hóa đơn', 3);


SELECT * FROM PHIEU_GIAM_GIA

IF OBJECT_ID('HOADON') IS NOT NULL
	DROP TABLE HOADON
CREATE TABLE HOADON
(
	ID BIGINT IDENTITY (1,1) NOT NULL,
	IdPGG BIGINT  NULL,
	IdNV BIGINT NOT NULL,
	IdKH BIGINT NOT NULL,
	MaHoaDon VARCHAR(20) NOT NULL,------------ THÊM CẤP BẬC Ở ĐÂY
	CapBac INT DEFAULT 0,-- LƯU CẤP BẬC TẠI THỜI ĐIỂM TT
	PhanTramGia FLOAT DEFAULT 0 , --- CẤP BẬC + PHIẾU THEO % +=> //	PhanTramGia INT NULL , --- CẤP BẬC + PHIẾU THEO % ++ Doi thanh FLoat
	TienPhieuGiam FLOAT DEFAULT 0 ,-- TIỀN GIẢM THEO PHIẾU VND
	DiemDoi FLOAT DEFAULT 0, -- tiền khách hàng từ đó lấy ra tiền 1 diem = 10k 
	-- +=> //	DiemDoi INT NULL, -- tiền khách hàng từ đó lấy ra tiền 1 diem = 10k => MONEY
	PhuongThucTT INT DEFAULT 0 , -- 0 là tại quầy , 1 chuyển khoản ,2  có thể làm kết hợp 2 loại(Có thể bỏ)
	TienKhDua FLOAT DEFAULT 0,
	TienKhChuyenKhoan FLOAT DEFAULT 0 ,
	TienThua FLOAT DEFAULT 0,
	ThanhTien FLOAT DEFAULT 0,
	HinhThucMua BIT DEFAULT 0,-- 0 tại quầy 1 ship
	NgayTao DATETIME DEFAULT GETDATE(),
	NgayThanhToan DATETIME NULL,
	TrangThai INT  DEFAULT 0, -- 0 CHƯA THANH TOÁN , 1 ĐÃ THANH TOÁN , 2 ĐANG GIAO , 3 THANH TOÁN TRƯỚC , 4 hủy
	CONSTRAINT PK_HOADON PRIMARY KEY (ID),
	CONSTRAINT FK_HOADON_PHIEU_GIAM_GIA FOREIGN KEY (IdPGG) REFERENCES PHIEU_GIAM_GIA(ID) ,
	CONSTRAINT FK_HOADON_NHANVIEN FOREIGN KEY (IdNV) REFERENCES NHANVIEN (ID),
	CONSTRAINT FK_HOADON_KHACHHANG FOREIGN KEY (IdKH) REFERENCES KHACHHANG (ID),
)

---- Thêm cột HinhThucMua kiểu dữ liệu BIT với giá trị NULL vào bảng HOADON
--ALTER TABLE HOADON
--ADD HinhThucMua BIT DEFAULT 0 ; -- 0 LÀ TẠI QUẦY 1 ONLINE

--UPDATE HOADON
--SET HinhThucMua = 0
-- Thêm 10 hóa đơn giả mạo không có đợt giảm giá và phiếu giảm giá
INSERT INTO HOADON (IdPGG, IdNV, IdKH, MaHoaDon, CapBac, PhanTramGia, TienPhieuGiam, DiemDoi, PhuongThucTT, TienKhDua, TienKhChuyenKhoan, TienThua, ThanhTien, NgayTao, NgayThanhToan, TrangThai)
VALUES 
(NULL, 1, 1, 'HD001' , 0, NULL, NULL, NULL, 0, 1500000, NULL, NULL, 1500000, GETDATE(), '2023-11-01T15:30:00', 1),
(NULL, 2, 2, 'HD002', 0, NULL, NULL, NULL, 0, 2000000, NULL, NULL, 2000000, GETDATE(), '2023-11-02T14:45:00', 1),
(NULL, 3, 3, 'HD003', 0, NULL, NULL, NULL, 0, 1800000, NULL, NULL, 1800000, GETDATE(), '2023-11-03T16:20:00', 1),
(NULL, 4, 4, 'HD004', 0, NULL, NULL, NULL, 0, 1200000, NULL, NULL, 1200000, GETDATE(), '2023-11-04T17:10:00', 1),
(NULL, 5, 5, 'HD005', 0, NULL, NULL, NULL, 0, 2500000, NULL, NULL, 2500000, GETDATE(), '2023-11-05T18:00:00', 1),
(NULL, 6, 6, 'HD006', 0, NULL, NULL, NULL, 0, 3000000, NULL, NULL, 3000000, GETDATE(), '2023-11-06T19:45:00', 1),
(NULL, 7, 7, 'HD007', 0, NULL, NULL, NULL, 0, 2200000, NULL, NULL, 2200000, GETDATE(), '2023-11-07T20:30:00', 1),
(NULL, 8, 8, 'HD008', 0, NULL, NULL, NULL, 0, 1900000, NULL, NULL, 1900000, GETDATE(), '2023-11-08T21:15:00', 1),
(NULL, 9, 9, 'HD009', 0, NULL, NULL, NULL, 0, 1700000, NULL, NULL, 1700000, GETDATE(), '2023-11-09T22:00:00', 1),
(NULL, 10, 10, 'HD010', 0, NULL, NULL, NULL, 0, 2100000, NULL, NULL, 2100000, GETDATE(), '2023-11-10T23:30:00', 1);
	UPDATE HOADON
	SET PhanTramGia = 0 , TienPhieuGiam = 0 , DiemDoi = 0, TienKhChuyenKhoan =0 , TienThua =0

SELECT * FROM HOADON
SELECT * FROM CHI_TIET_DGG
SELECT * FROM CHI_TIET_SAN_PHAM
SELECT * FROM DOT_GIAM_GIA
SELECT * FROM CHI_TIET_SAN_PHAM
INSERT INTO CHI_TIET_DGG (IdDGG , IdCTSP , MaCTSP , DonGia , DonGiaConLai , GiaTriGiam ) values 
(1,1,'CTSP0001',1200000.00, 1200000.00 , 20 ),
(6,2,'CTSP0002',1100000.00, 1100000.00 , 150000 )



--- HÓA ĐƠN CHI TIẾT

IF OBJECT_ID('HOADONCHITIET') IS NOT NULL
	DROP TABLE HOADONCHITIET

CREATE TABLE HOADONCHITIET
(
	ID BIGINT IDENTITY (1,1) NOT NULL,
	IdHoaDon BIGINT NOT NULL ,
	IdCTSP BIGINT NOT NULL,
	SoLuong INT NULL,
	MaDGG VARCHAR(10)  NULL ,
	LoaiDGG INT NULL,
	GiaTriDGG INT  NULL,
	QuyDoiDGGTT FLOAT NULL , -- => ĐỀU QUY RA THÀNH vnd THEO SẢN PHẨM
	GiaBan FLOAT NULL,
	DonGia FLOAT NULL,
	ThanhTien FLOAT NULL,
	NgayTao DATETIME DEFAULT GETDATE(),
	TrangThai INT NULL ,
	CONSTRAINT PK_HOADONCHITIET PRIMARY KEY (ID),
	CONSTRAINT FK_HOADONCHITIET_HOADON FOREIGN KEY (IdHoaDon) REFERENCES HOADON  (ID),
	CONSTRAINT FK_HOADONCHITIET_CHI_TIET_SAN_PHAM FOREIGN KEY (IdCTSP) REFERENCES CHI_TIET_SAN_PHAM (ID)
)
UPDATE HOADONCHITIET
SET ThanhTien = SoLuong * GiaBan;
select * from HOADONCHITIET



--ALTER TABLE HOADONCHITIET
--ADD LoaiDGG_INT INT NULL;

---- Cập nhật dữ liệu từ cột BIT sang cột INT
--UPDATE HOADONCHITIET
--SET LoaiDGG = CONVERT(INT, LoaiDGG);

INSERT INTO HOADONCHITIET (IdHoaDon, IdCTSP, SoLuong, MaDGG, LoaiDGG, GiaTriDGG, QuyDoiDGGTT, GiaBan, DonGia, NgayTao, TrangThai)
VALUES 
(1, 1, 2, NULL, NULL, NULL, 0, 750000, 375000, GETDATE(), 1),
(1, 2, 1, NULL, NULL, NULL, 0, 800000, 800000, GETDATE(), 1),
(2, 3, 3, NULL, NULL, NULL, 0, 900000, 300000, GETDATE(), 1),
(2, 4, 2, NULL, NULL, NULL, 0, 1200000, 600000, GETDATE(), 1),
(3, 5, 1, NULL, NULL, NULL, 0, 450000, 450000, GETDATE(), 1),
(3, 3, 2, NULL, NULL, NULL, 0, 500000, 250000, GETDATE(), 1),
(4, 2, 2, NULL, NULL, NULL, 0, 700000, 350000, GETDATE(), 1),
(4, 1, 1, NULL, NULL, NULL, 0, 1500000, 1500000, GETDATE(), 1),
(5, 1, 3, NULL, NULL, NULL, 0, 750000, 250000, GETDATE(), 1),
(5, 5, 1, NULL, NULL, NULL, 0, 1000000, 1000000, GETDATE(), 1),
(6, 1, 1, NULL, NULL, NULL, 0, 800000, 800000, GETDATE(), 1),
(6, 2, 2, NULL, NULL, NULL, 0, 1600000, 800000, GETDATE(), 1),
(7, 3, 1, NULL, NULL, NULL, 0, 2000000, 2000000, GETDATE(), 1),
(7, 4, 2, NULL, NULL, NULL, 0, 2400000, 1200000, GETDATE(), 1),
(8, 5, 1, NULL, NULL, NULL, 0, 2500000, 2500000, GETDATE(), 1),
(8, 3, 3, NULL, NULL, NULL, 0, 2700000, 900000, GETDATE(), 1),
(9, 4, 2, NULL, NULL, NULL, 0, 3000000, 1500000, GETDATE(), 1),
(9, 2, 1, NULL, NULL, NULL, 0, 1800000, 1800000, GETDATE(), 1),
(10, 3, 3, NULL, NULL, NULL, 0, 2400000, 800000 ,  GETDATE(), 1);

SELECT * FROM HOADONCHITIET
--- PHIEU GIAO HÀNG
IF OBJECT_ID('PHIEUGIAOHANG') IS NOT NULL
	DROP TABLE PHIEUGIAOHANG
CREATE TABLE PHIEUGIAOHANG
(
	ID BIGINT IDENTITY (1,1) NOT NULL,
	IdHoaDon BIGINT NOT NULL,
	IdKH BIGINT NOT NULL,
	TenNguoiNhan NVARCHAR(50)  NULL,
	SDTNguoiNhan VARCHAR(15)  NULL,
	DiaChi NVARCHAR(100)  NULL,
	TenShip NVARCHAR(50)  NULL,
	SDTShip VARCHAR(15)  NULL,
	GiaShip FLOAT NULL , ---->ÁNH XẠ JAVA => BigDecimal
	NgayTaoPhieu DATETIME DEFAULT GETDATE(),
	MaVanDon VARCHAR(20) NULL,
	ĐonViVanChuyen NVARCHAR(100) NULL,
	NgayHoanThanhDon DATETIME NULL,
	TrangThai INT DEFAULT 0 , --0   CHỜ GIAO, 1 : DANG GIAO , 2 : LÀ HẸN LẠI (UPDATE LẠI THÁNG THÁI NGÀY) , 3 hủy
	CONSTRAINT PK_PHIEUGIAOHANG PRIMARY KEY (ID),
	CONSTRAINT FK_PHIEUGIAOHANG_HOADON FOREIGN KEY (IdHoaDon) REFERENCES HOADON  (ID),
	CONSTRAINT FK_PHIEUGIAOHANG_KHACHHANG FOREIGN KEY (IdKH) REFERENCES KHACHHANG   (ID)
)
SELECT * FROM CHI_TIET_SAN_PHAM
UPDATE HOADON
SET TrangThai = ?
WHERE MaHoaDon LIKE  ?
UPDATE PHIEUGIAOHANG
SET TrangThai
WHERE MaVanDon LIKE ?

-- Thêm dữ liệu vào bảng PHIEUGIAOHANG
INSERT INTO PHIEUGIAOHANG (IdHoaDon, IdKH, TenNguoiNhan, SDTNguoiNhan, DiaChi, TenShip, SDTShip, GiaShip, NgayTaoPhieu, MaVanDon, ĐonViVanChuyen, NgayHoanThanhDon, TrangThai)
VALUES 
(1, 1, N'Nguyễn Văn A', '0901234567', N'123 Đường ABC, Quận 1, TP.HCM', N'Nhân viên A', '0987654321', 50000.00, GETDATE(), 'VD001', N'Giao hàng nhanh', NULL, 1),
(2, 2, N'Nguyễn Thị B', '0909876543', N'456 Đường XYZ, Quận 2, TP.HCM', N'Nhân viên B', '0123456789', 60000.00, GETDATE(), 'VD002', N'Giao hàng tiết kiệm', NULL, 1),
(3, 3, N'Trần Văn C', '0987654321', N'789 Đường XYZ, Quận 3, TP.HCM', N'Nhân viên C', '0987654321', 70000.00, GETDATE(), 'VD003', N'Giao hàng nhanh', NULL, 0),
(4, 4, N'Lê Thị D', '0123456789', N'101 Đường DEF, Quận 4, TP.HCM', N'Nhân viên D', '0901234567', 80000.00, GETDATE(), 'VD004', N'Giao hàng tiết kiệm', NULL, 2),
(5, 5, N'Phạm Văn E', '0901234567', N'202 Đường GHI, Quận 5, TP.HCM', N'Nhân viên E', '0123456789', 90000.00, GETDATE(), 'VD005', N'Giao hàng nhanh', NULL, 3),
(6, 6, N'Mai Thị F', '0987654321', N'303 Đường KLM, Quận 6, TP.HCM', N'Nhân viên F', '0987654321', 100000.00, GETDATE(), 'VD006', N'Giao hàng tiết kiệm', NULL, 0),
(7, 7, N'Đỗ Văn G', '0123456789', N'404 Đường NOP, Quận 7, TP.HCM', N'Nhân viên G', '0901234567', 110000.00, GETDATE(), 'VD007', N'Giao hàng nhanh', NULL, 1),
(8, 8, N'Vũ Thị H', '0909876543', N'505 Đường QRS, Quận 8, TP.HCM', N'Nhân viên H', '0123456789', 120000.00, GETDATE(), 'VD008', N'Giao hàng tiết kiệm', NULL, 2),
(9, 9, N'Hoàng Văn I', '0987654321', N'606 Đường TUV, Quận 9, TP.HCM', N'Nhân viên I', '0987654321', 130000.00, GETDATE(), 'VD009', N'Giao hàng nhanh', NULL, 0),
(10, 10, N'Ngô Thị K', '0123456789', N'707 Đường WXY, Quận 10, TP.HCM', N'Nhân viên K', '0901234567', 140000.00, GETDATE(), 'VD010', N'Giao hàng tiết kiệm', NULL, 3);

--select * from  HOADON
--join KHACHHANG on HOADON.IdKH = KHACHHANG.ID
--join HOADONCHITIET on HOADON.ID = HOADONCHITIET.IdHoaDon
--where MaKhachHang like 'KH001' and NgayThanhToan = '2023-11-20 00:32:24.407'
--order by NgayThanhToan
--SELECT PGH.ID AS ID , HD.ID AS IDHD  , MaVanDon , DiaChi, MaHoaDon , TenNguoiNhan , SDTNguoiNhan , GiaShip , TenShip , SDTShip , PGH.NgayTaoPhieu  , PGH.NgayHoanThanhDon FROM HOADON AS HD 
--JOIN PHIEUGIAOHANG AS PGH ON HD.ID = PGH.IdHoaDon
--JOIN KHACHHANG AS KH ON KH.ID = PGH.IdKH
SELECT MaHoaDon , HD.NgayTao , TenKhachHang FROM HOADON AS HD
JOIN KHACHHANG AS KH ON KH.ID = HD.IdKH
WHERE HD.HinhThucMua =1 AND HD.TrangThai = 0
INSERT INTO PHIEUGIAOHANG (IdHoaDon , IdKH , TenNguoiNhan , SDTNguoiNhan , DiaChi , TenShip , SDTShip , GiaShip , MaVanDon , ĐonViVanChuyen , NgayHoanThanhDon)
VALUES  (? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ?)
		--SELECT * FROM PHIEUGIAOHANG
		--INSERT INTO HOADON (IdNV , IdKH , MaHoaDon , HinhThucMua) VALUES 
		--(1,1,'MAHDTEST6',1),
		--(1,2,'MAHDTEST2',1),
		--(1,3,'MAHDTEST3',1),
		--(1,4,'MAHDTEST4',1),
		--(1,5,'MAHDTEST5',1),;
		SELECT HD.ID AS IDHD, MaHoaDon, KH.ID AS IDKH, HD.NgayTao, TenKhachHang
FROM HOADON AS HD
JOIN KHACHHANG AS KH ON KH.ID = HD.IdKH
WHERE HD.HinhThucMua = 1 AND HD.TrangThai = 1 AND NOT EXISTS (
    SELECT 1 FROM PHIEUGIAOHANG AS PGH WHERE PGH.IdHoaDon = HD.ID
);




---- CHI TIẾT PHIẾU GIAO
IF OBJECT_ID('CHI_TIET_PHIEU_GIAO') IS NOT NULL
	DROP TABLE CHI_TIET_PHIEU_GIAO
CREATE TABLE CHI_TIET_PHIEU_GIAO
(
	ID BIGINT IDENTITY (1,1) NOT NULL,
	IdCTSP BIGINT NULL,
	IdPGH BIGINT NULL ,
	SoLuong INT NULL ,
	CONSTRAINT PK_CHI_TIET_PHIEU_GIAO PRIMARY KEY (ID),
	CONSTRAINT FK_CHI_TIET_PHIEU_GIAO_PHIEUGIAOHANG FOREIGN KEY (IdPGH) REFERENCES PHIEUGIAOHANG(ID),
	CONSTRAINT FK_CHI_TIET_PHIEU_GIAO_CHI_TIET_SAN_PHAM FOREIGN KEY (IdCTSP) REFERENCES CHI_TIET_SAN_PHAM(ID),
)

INSERT INTO CHI_TIET_PHIEU_GIAO (IdCTSP, IdPGH, SoLuong)
VALUES 
(1, 1, 2),
(2, 1, 3),
(3, 2, 1),
(4, 2, 4),
(5, 2, 2),
(1, 3, 1),
(2, 3, 2),
(3, 4, 3),
(4, 4, 1),
(5, 5, 4);

---- END CODE

-- phieeus giao 
SELECT PGH.ID MaVanDon , MaHoaDon , TenKhachHang , SDTNguoiNhan , GiaShip , TenShip , SDTShip , PGH.NgayTaoPhieu , ĐonViVanChuyen ,NgayHoanThanhDon , PGH.TrangThai FROM PHIEUGIAOHANG AS PGH
JOIN HOADON AS HD ON HD.ID = PGH.IdHoaDon
JOIN KHACHHANG AS KH ON KH.ID = PGH.IdKH
UPDATE PHIEUGIAOHANG 
SET TenNguoiNhan = ? , SDTNguoiNhan = ? , DiaChi = ? , TenShip = ? , SDTShip = ? , ĐonViVanChuyen = ? , NgayHoanThanhDon = ? , GiaShip = ?
WHERE MaVanDon LIKE ''
SELECT * FROM PHIEUGIAOHANG

SELECT MaHoaDon , TenNguoiNhan , PGH.NgayTaoPhieu , TenSP , SoLuong , HDCT.GiaBan , (SoLuong * HDCT.GiaBan) AS THANHTIEN  FROM HOADON
JOIN PHIEUGIAOHANG AS PGH ON PGH.IdHoaDon = HOADON.ID
JOIN HOADONCHITIET AS HDCT ON HDCT.IdHoaDon = HOADON.ID
JOIN CHI_TIET_SAN_PHAM AS CTSP ON CTSP.ID = HDCT.IdCTSP
JOIN SANPHAM AS SP ON SP.ID = CTSP.IdSP
WHERE MaVanDon LIKE 'PGH016'
----------------------
SELECT * FROM 






select * from CHI_TIET_SAN_PHAM
SELECT * FROM CHI_TIET_PHIEU_GIAO

SELECT * FROM KHACHHANG

SELECT * FROM NHANVIEN

SELECT KH.ID, IdNV , MaKhachHang , TenKhachHang , KH.SDT AS SDT_KH  ,KH.NgaySinh AS NGAYSINH_KH , GioiTinh , KH.DiaChi AS DIACHI_KH , Diem , CapBac, KH.TrangThai AS TRANGTHAI_KH from KHACHHANG AS  KH
JOIN NHANVIEN AS NV ON KH.IdNV = NV.ID

--- THIEU EMAIL

SELECT ID, MaKhachHang , TenKhachHang  , SDT , GioiTinh , NgaySinh , DiaChi , Diem , CapBac FROM KHACHHANG 

SELECT ID, MaKhachHang , TenKhachHang  , SDT , GioiTinh , NgaySinh , DiaChi , Diem , CapBac FROM KHACHHANG 
WHERE MaKhachHang LIKE '' OR TenKhachHang LIKE '' OR SDT LIKE  ''

SELECT hd.MaHoaDon , HD.NgayThanhToan , HD.ThanhTien , HD.TrangThai  FROM HOADON AS HD 
JOIN KHACHHANG AS KH  ON HD.IdKH = KH.ID 
WHERE KH.MaKhachHang LIKE 'KH000'


SELECT HD.ID AS ID , HD.MaHoaDon AS MaHoaDon , hd.NgayThanhToan AS NgayThanhToan ,
                   	HD.ThanhTien AS ThanhTien , HD.TrangThai AS TrangThai , 
                 	HD.DiemDoi AS DiemDoi , HD.CapBac AS CapBac   FROM HOADON AS HD
                  	JOIN KHACHHANG ON KHACHHANG.ID = HD.IdKH
                   WHERE MaKhachHang = 'KH000' 

SELECT * FROM KHACHHANG

---  CÓ THỂ CHỈNH GIỚI TÍNH THÀNH INT  , THIẾU TRƯỜNG EMAIL trong kh
--- THÊM CẤP BẬC CHO HÓA ĐƠN
--- SỬA LẠI BẢNG ĐỢT GIẢM GIÁ THÊM BẢNG ĐỂ LƯU ĐƯỢC SP ÁP DỤNG CHO SP NÀO TẠI THỜI GIAN NÀO 
--- VÌ SAU KHI HẾT ĐỢT GIẢM GIÁ THÌ SẼ XÓA KHÓA NGOẠI ĐI NÊN SẼ KO LƯU ĐƯỢC LỊCH SỬ ÁP DỤNG CHO TỪNG SẢN PHẨM 
SELECT * FROM
    (SELECT ROW_NUMBER() OVER (ORDER BY ID) AS rownum,  * FROM KHACHHANG)
    AS temp
    WHERE rownum BETWEEN 1 AND 5

	----INSERT INTO KHACHHANG (MaKhachHang , TenKhachHang, SDT , DiaChi , GioiTinh , NgaySinh ) VALUES 
	---(? , ? , ? ,? ,? , ?)


	--SELECT ID , TenKhachHang , SDT , DiaChi FROM
 --   (SELECT ROW_NUMBER() OVER (ORDER BY ID) AS rownum,  * FROM KHACHHANG)
	--AS temp WHERE rownum BETWEEN ? AND ?

	select KH.ID as ID , NV.ID as IdNV , MaKhachHang , TenKhachHang , GioiTinh , KH.SDT as SDT  , KH.DiaChi as DiaChi, KH.Email as Email, KH.NgaySinh as NgaySinh , Diem , CapBac from KHACHHANG as KH
	join NHANVIEN as NV on NV.ID = KH.IdNV
	order by ID OFFSET 4 ROWS FETCH NEXT 4 ROWS ONLY

	SELECT COUNT(*) AS totalRows FROM KHACHHANG

	SELECT * FROM
    (SELECT ROW_NUMBER() OVER (ORDER BY id) AS rownum,  * FROM KHACHHANG)
    AS temp
    WHERE rownum BETWEEN 6 AND 10

	select * from CHI_TIET_SAN_PHAM
	select * from NHANVIEN


--update KHACHHANG
--set TenKhachHang =N'Khách Bán Lẻ', CapBac = 3, SDT= N'Không có', DiaChi = '......', Email = '......', Diem = 0
--where ID =1 
SELECT CapBac FROM KHACHHANG
select * from HOADON

--update HOADON
--set DiemDoi = 0
	SELECT COUNT(*) AS totalRows FROM KHACHHANG

	--INSERT INTO KHACHHANG ( IdNV ,  MaKhachHang , TenKhachHang, SDT, NgaySinh , GioiTinh, Email , DiaChi ) 
	--VALUES (?,  ? , ?, ?, ? , ?, ? , ?);


	--UPDATE KHACHHANG
	--SET TenKhachHang = ?, SDT = ?, NgaySinh = ? , GioiTinh = ?, Email = ? , DiaChi = ?
	--WHERE MaKhachHang LIKE ?
	--select * from KHACHHANG

	update KHACHHANG
	set MaKhachHang = 'KH012' , Email = 'kh2@gmail.com'
	where ID =2

	SELECT * FROM HOADON

	SELECT HD.ID AS ID , HD.MaHoaDon AS MaHoaDon , hd.NgayThanhToan AS NgayThanhToan , 
	HD.ThanhTien AS ThanhTien , HD.TrangThai AS TrangThai , 
	HD.DiemDoi AS DiemDoi , HD.CapBac AS CapBac   FROM HOADON AS HD
	JOIN KHACHHANG ON KHACHHANG.ID = HD.IdKH
	WHERE MaKhachHang = 'KH001'
	order by ID OFFSET 0 ROWS FETCH NEXT 4 ROWS ONLY
	

	SELECT ID, IdNV, MaKhachHang , TenKhachHang  , SDT , GioiTinh , NgaySinh , Email , DiaChi , Diem , CapBac FROM KHACHHANG 
    WHERE MaKhachHang LIKE '%K%' OR TenKhachHang LIKE '' OR SDT LIKE  ''
	order by ID OFFSET 4 ROWS FETCH NEXT 4 ROWS ONLY

	SELECT COUNT(*) AS totalRows FROM HOADON 
	JOIN KHACHHANG ON KHACHHANG.ID = HOADON.IdKH
	WHERE MaKhachHang = 'KH001'

	select * from HOADON
	select * from PHIEU_GIAM_GIA


	SELECT CTSP.ID, CTSP.MaCTSP as  MaCTSP , SP.ID AS IdSP , SP.TenSP AS TenSP , S.ID AS IdSize , S.TenSize AS TenSize,
	TH.ID AS IdTH , TH.TenThuongHieu AS TenThuongHieu , M.ID AS IdM , M.TenMau AS TenMau ,
	SoLuongTon, GiaNiemYet , GiaBan
	FROM CHI_TIET_SAN_PHAM AS CTSP
	JOIN SANPHAM AS SP ON CTSP.IdSP = SP.ID
	JOIN SIZE AS S ON CTSP.IdSize = S.ID
	JOIN THUONGHIEU AS TH ON CTSP.IdThuongHieu =TH.ID
	JOIN MAU AS M ON CTSP.IdMau = M.ID
	WHERE CTSP.SoLuongTon > 0

	UPDATE CHI_TIET_SAN_PHAM 
	SET SoLuongTon = 1
	WHERE ID =1

	SELECT * FROM HOADON


	SELECT COUNT(*) AS totalRows FROM HOADON

	INSERT INTO HOADON(IdPGG , IdNV , IdKH, MaHoaDon) VALUES 
	(NULL ,2 , 2 , 'HD23-11-15172210')


	select * from HOADON
	JOIN PHIEU_GIAM_GIA ON HOADON.IdPGG = PHIEU_GIAM_GIA.ID
	where  PHIEU_GIAM_GIA.ID = 1

	update HOADON
	set IdPGG = 1
	where ID = 1
	select * from HOADONCHITIET

	SELECT ID, IdSP , IdThuongHieu, IdMau, IdSize , IdDGG , MaCTSP , SoLuongTon , GiaNiemYet , GiaBan, MoTa , TrangThai FROM CHI_TIET_SAN_PHAM

	update CHI_TIET_SAN_PHAM
	set IdDGG =1
	where ID =1

	select * from HOADONCHITIET
	SELECT * FROM CHI_TIET_SAN_PHAM
	--INSERT INTO CHI_TIET_SAN_PHAM (IdSP, IdThuongHieu, IdMau, IdSize , IdDGG , MaCTSP , SoLuongTon , GiaNiemYet , GiaBan , MoTa) VALUES
	--(?, ?, ?, ? , ? , ? , ? , ? , ? , ?)

	--insert into  DOT_GIAM_GIA (IdNV , MaDGG , Loai, GiaTri , NgayBatDau , NgayKetThuc , MoTa)
	--VALUES (? , ? , ?, ? , ? , ? , ?)

	--INSERT INTO HOADONCHITIET (IdHoaDon, IdCTSP, SoLuong , MaDGG , LoaiDGG , GiaTriDGG ,  QuyDoiDGGTT , DonGia , GiaBan)
	--VALUES (?, ?, ? , ? , ? , ? ,  ? , ? , ?)


	SELECT CTSP.ID AS ID, IdSP , IdThuongHieu, IdMau, IdSize  , MaCTSP , SoLuongTon , GiaNiemYet , GiaBan, CTSP.MoTa AS ID , CTSP.TrangThai AS TrangThai ,
	IdDGG , DGG.MaDGG AS MaDGG , DGG.Loai AS Loai , DGG.GiaTri AS GiaTri , DGG.TrangThai AS TrangThaiDGG
	
	FROM CHI_TIET_SAN_PHAM AS CTSP
   LEFT JOIN DOT_GIAM_GIA AS DGG ON CTSP.IdDGG = DGG.ID
	INSERT INTO HOADON  (IdNV , IdKH , MaHoaDon) VALUES 
	(1 , 3, 'HD23-11-15235010')

	
	select * from HOADON
	select * from DOT_GIAM_GIA

	update DOT_GIAM_GIA
	set TrangThai = 1 
	where id =1

	select * from CHI_TIET_SAN_PHAM

	select * from HOADONCHITIET
	where IdHoaDon = 12
	select * from HOADON
	where MaHoaDon like 'HD23-11-16005511'


	SELECT CTSP.ID as ID , CTSP.MaCTSP as  MaCTSP , SP.ID AS IdSP , SP.TenSP AS TenSP , S.ID AS IdSize , S.TenSize AS TenSize,
		TH.ID AS IdTH , TH.TenThuongHieu AS TenThuongHieu , M.ID AS IdM , M.TenMau AS TenMau ,
		IdDGG , DGG.MaDGG AS MaDGG , DGG.Loai AS LoaiDGG , DGG.GiaTri AS GiaTriDGG , DGG.TrangThai AS TrangThaiDGG ,
		SoLuongTon, GiaNiemYet , GiaBan
	FROM CHI_TIET_SAN_PHAM AS CTSP
		JOIN SANPHAM AS SP ON CTSP.IdSP = SP.ID
		JOIN SIZE AS S ON CTSP.IdSize = S.ID
		JOIN THUONGHIEU AS TH ON CTSP.IdThuongHieu =TH.ID
		JOIN MAU AS M ON CTSP.IdMau = M.ID
		LEFT JOIN DOT_GIAM_GIA  AS DGG ON CTSP.IdDGG = DGG.ID
    WHERE CTSP.SoLuongTon > 0

	update HOADON
	set TrangThai = 3 , HinhThucMua =1
	where id = 13

	select * from CHI_TIET_SAN_PHAM
	SELECT HinhThucMua FROM HOADON
	INSERT INTO KHACHHANG (IdNV, MaKhachHang, TenKhachHang, SDT, NgaySinh, Email, GioiTinh, DiaChi, Diem, CapBac, NgayTao, TrangThai)
VALUES 
(1, 'KH011', N'Ngọc Tú', '0868819925', '2002-10-27', 'ntu@gmail.com',10, N'Hoa Bình', 0, 0, GETDATE(), 1)

select * from HOADON



					--SELECT 
     --                HD.ID  AS IdHD , HD.MaHoaDon AS MaHoaDon  , HD.NgayTao AS NgayTao , HD.TrangThai AS TrangThaiHD ,
     --                NV.ID AS IdNV , NV.HoVaTen AS TenNV , NV.MaNhanVien AS MaNhanVien , 
     --                KH.ID AS IdKH , KH.TenKhachHang AS TenKH , KH.MaKhachHang AS  MaKhachHang ,

					-- PGG.ID AS IdPGG , PGG.MaPhieu AS  MaPhieu , PGG.TenPhieu AS TenPhieu , PGG.GiaTri AS GiaTri  , PGG.LoaiPhieu AS  LoaiPhieu, PGG.SoLuongPhieu AS SoLuongPhieu  , PGG.DonToiThieu AS DonToiThieu  , PGG.TrangThai AS TrangThaiPGG
     --               FROM HOADON AS HD
					--LEFT JOIN KHACHHANG AS KH ON KH.ID = HD.IdKH 
     --               LEFT JOIN NHANVIEN AS NV ON NV.ID = HD.IdNV
					--LEFT JOIN PHIEU_GIAM_GIA AS PGG ON PGG.ID  = HD.IdPGG
     --               WHERE HD.PhuongThucTT = 0 
     --               ORDER BY HD.NgayTao DESC


				 SELECT 
                  HDCT.ID AS IdHDCT , HD.ID  AS IdHD , HD.MaHoaDon AS MaHoaDon  , HD.NgayTao AS NgayTao , HD.TrangThai AS TrangThaiHD , HD.CapBac AS CAPBAC , HD.PhanTramGia AS PhanTramGia, HD.TienPhieuGiam AS TienPhieuGiam , HD.DiemDoi AS DiemDoi , HD.PhuongThucTT AS PhuongThucTT , 
				HD.NgayThanhToan AS NgayThanhToanHD,    HD.TienKhDua AS TienKhDua , HD.TienKhChuyenKhoan AS TienKhChuyenKhoan ,HD.TienThua AS TienThua , HD.ThanhTien AS ThanhTien , HD.HinhThucMua AS  HinhThucMua , HD.TrangThai AS TrangThaiHD ,
				   CTSP.ID AS IdCTSP,CTSP.MaCTSP AS MaCTSP , CTSP.GiaBan AS GiaBan ,  CTSP.GiaNiemYet  AS GiaNiemYet,
				   SP.ID AS IDSP ,SP.MaSP AS MASP , SP.TenSP AS TENSP ,
				   DGG.ID AS IDDGG , DGG.MaDGG AS MADGG ,  DGG.TenDGG AS TENDGG , DGG.Loai AS LOAIDGG , DGG.GiaTri AS GIATRI , DGG.NgayBatDau AS NGAYBD , DGG.NgayKetThuc AS NGAYKT , DGG.MoTa AS MOTA , DGG.TrangThai TRANGTHAIDGG,
				   NV.ID AS IdNV , NV.HoVaTen AS TenNV , NV.MaNhanVien AS MaNhanVien , 
				  KH.ID AS IdKH , KH.TenKhachHang AS TenKH , KH.MaKhachHang AS  MaKhachHang ,
				PGG.ID AS IdPGG , PGG.MaPhieu AS  MaPhieu , PGG.TenPhieu AS TenPhieu , PGG.GiaTri AS GiaTri  , PGG.LoaiPhieu AS  LoaiPhieu, PGG.SoLuongPhieu AS SoLuongPhieu  , PGG.DonToiThieu AS DonToiThieu  , PGG.TrangThai AS TrangThaiPGG ,
				 HDCT.IdCTSP AS IdCTSP , HDCT.SoLuong AS SoLuongHDCT , HDCT.MaDGG AS MaDGGHDCT , HDCT.LoaiDGG AS LoaiGGHDCT , HDCT.GiaTriDGG AS GiaTriHDCT , HDCT.QuyDoiDGGTT AS QuyDoiDGGTT , HDCT.GiaBan AS GiaBanHDCT , HDCT.DonGia AS DonGiaHDCT  , HDCT.ThanhTien AS ThanhtIENHDCT
              , S.TenSize , M.TenMau , TH.TenThuongHieu ,
			  p.TenShip , p.SDTShip , p.GiaShip
			  FROM HOADONCHITIET AS HDCT
			LEFT JOIN HOADON AS HD ON HD.ID = HDCT.IdHoaDon
			LEFT JOIN CHI_TIET_SAN_PHAM AS CTSP ON CTSP.ID = HDCT.IdCTSP
			LEFT JOIN SANPHAM AS SP  ON SP.ID = CTSP.ID
			LEFT JOIN DOT_GIAM_GIA AS DGG ON DGG.ID = CTSP.IdDGG
			LEFT JOIN KHACHHANG AS KH ON KH.ID = HD.IdKH 
            LEFT JOIN NHANVIEN AS NV ON NV.ID = HD.IdNV
			LEFT JOIN PHIEU_GIAM_GIA AS PGG ON PGG.ID  = HD.IdPGG
			LEFT JOIN SIZE AS S ON S.ID = CTSP.IdSize 
			LEFT JOIN THUONGHIEU AS TH ON TH.ID = CTSP.IdThuongHieu  
			LEFT JOIN MAU AS M ON M.ID = CTSP.IdMau 
			LEFT JOIN PHIEUGIAOHANG AS P ON P.IdHoaDon = HD.ID
			 
			WHERE HD.MaHoaDon LIKE 'HD001' 'HD23-11-17010215'
              ORDER BY HDCT.NgayTao DESC
			  select * from HOADON

			  SELECT * FROM PHIEUGIAOHANG


			  SELECT * FROM HOADONCHITIET
			  -----------
			  SELECT CTSP.ID as ID , CTSP.MaCTSP as  MaCTSP , SP.ID AS IdSP , SP.TenSP AS TenSP , S.ID AS IdSize , S.TenSize AS TenSize,
                    TH.ID AS IdTH , TH.TenThuongHieu AS TenThuongHieu , M.ID AS IdM , M.TenMau AS TenMau ,
                     IdDGG , DGG.MaDGG AS MaDGG , DGG.Loai AS LoaiDGG , DGG.GiaTri AS GiaTriDGG , DGG.TrangThai AS TrangThaiDGG ,
                     SoLuongTon, GiaNiemYet , GiaBan
                    FROM CHI_TIET_SAN_PHAM AS CTSP
                     JOIN SANPHAM AS SP ON CTSP.IdSP = SP.ID
                     JOIN SIZE AS S ON CTSP.IdSize = S.ID
                     JOIN THUONGHIEU AS TH ON CTSP.IdThuongHieu =TH.ID
                     JOIN MAU AS M ON CTSP.IdMau = M.ID
                     JOIN DOT_GIAM_GIA  AS DGG ON CTSP.IdDGG = DGG.ID
					 WHERE CTSP.MaCTSP LIKE 'CTSP0002'
			  -------
			   SELECT CTSP.ID as ID , CTSP.MaCTSP as  MaCTSP ,
                     IdDGG , DGG.MaDGG AS MaDGG , DGG.Loai AS LoaiDGG , DGG.GiaTri AS GiaTriDGG , DGG.TrangThai AS TrangThaiDGG ,
                     SoLuongTon, GiaNiemYet , GiaBan
                    FROM  DOT_GIAM_GIA  AS DGG
					 JOIN CHI_TIET_SAN_PHAM AS CTSP ON CTSP.IdDGG = DGG.ID
                     
					 WHERE CTSP.MaCTSP LIKE 'CTSP0002'
			  SELECT * FROM CHI_TIET_SAN_PHAM
			  SELECT ID FROM HOADON WHERE MaHoaDon LIKE ''

			  select HOADON.ID ,HOADON.MaHoaDon , CHI_TIET_SAN_PHAM.MaCTSP   from HOADONCHITIET
			  join HOADON on HOADON.ID  = HOADONCHITIET.IdHoaDon
			  join CHI_TIET_SAN_PHAM on CHI_TIET_SAN_PHAM.ID = HOADONCHITIET.IdCTSP
			  
			  where HOADONCHITIET.id =24

			  SELECT * FROM HOADONCHITIET
			  order by NgayTao desc

			  select * from DOT_GIAM_GIA
			   where HOADONCHITIET.id =12

			  update DOT_GIAM_GIA
			  set TrangThai =1
			  where id =6;

			  select * from HOADON
			  select * from DOT_GIAM_GIA
			  select * from CHI_TIET_SAN_PHAM
			  UPDATE CHI_TIET_SAN_PHAM
			  set IdDGG =  6
			  where id =2

			  SELECT * FROM HOADONCHITIET
			  JOIN HOADON ON HOADON.ID = HOADONCHITIET.IdHoaDon
			  WHERE HOADON.MaHoaDon LIKE 

			  INSERT INTO HOADONCHITIET (IdHoaDon , IdCTSP , SoLuong , MaDGG , LoaiDGG , GiaTriDGG , QuyDoiDGGTT , GiaBan , DonGia) VALUES 
			  (16 , 2 , 2 , 'DGG006' , 1 , 150000 , 150000 , ? , 1100000)

--
--CREATE TRIGGER trgAfterInsert_HOADONCHITIET
--ON HOADONCHITIET
--AFTER INSERT
--AS
--BEGIN
--    SET NOCOUNT ON;

--    UPDATE HOADONCHITIET
--    SET GiaBan = IdCTSP.GiaNiemYet - INSERTED.QuyDoiDGGTT,
--        ThanhTien = INSERTED.SoLuong * (IdCTSP.GiaNiemYet - INSERTED.QuyDoiDGGTT)
--    FROM HOADONCHITIET
--    INNER JOIN INSERTED ON HOADONCHITIET.ID = INSERTED.ID
--    INNER JOIN CHI_TIET_SAN_PHAM IdCTSP ON HOADONCHITIET.IdCTSP = IdCTSP.ID;
--END;

--DROP TRIGGER trgAfterInsert_HOADONCHITIET;

---
SELECT HD.ID AS ID , HD.MaHoaDon AS MaHoaDon , hd.NgayThanhToan AS NgayThanhToan , 
                   	HD.ThanhTien AS ThanhTien , HD.TrangThai AS TrangThai , 
                   	HD.DiemDoi AS DiemDoi , HD.CapBac AS CapBac   FROM HOADON AS HD
                    	JOIN KHACHHANG ON KHACHHANG.ID = HD.IdKH 
                    	WHERE MaKhachHang = 'KH001'






SELECT ID, IdNV , MaKhachHang , TenKhachHang  , SDT , GioiTinh , NgaySinh , Email , DiaChi , Diem , CapBac FROM KHACHHANG 
                       WHERE GioiTinh = 1





SELECT SoLuongTon FROM CHI_TIET_SAN_PHAM WHERE MaCTSP LIKE ''
id,idHD , idCTSP , soLuong , maDGG , loại giảm giá, giá trị dgg , giá bán , giatriQD , don giá , thành tiền , 

 INSERT INTO HOADONCHITIET ( id ,IdHoaDon , IdCTSP , SoLuong , MaDGG , LoaiDGG , GiaTriDGG , QuyDoiDGGTT , GiaBan , DonGia, ThanhTien) VALUES 
			  (? , ? , ? , ? , ? , ? , ? , ? , ? , ? ,?)

			  SELECT DGG.ID AS IdDGG , MaDGG , Loai , GiaTri, DGG.TrangThai AS TrangThaiDGG ,
			  CTSP.ID AS IdDGG , MaCTSP ,GiaNiemYet , GiaBan 
			  FROM CHI_TIET_SAN_PHAM AS CTSP
			  JOIN DOT_GIAM_GIA AS DGG ON DGG.ID = CTSP.IdDGG ;
			  WHERE CHI_TIET_SAN_PHAM.MaCTSP LIKE ''

			  select * from HOADONCHITIET
			  join HOADON on HOADON.ID = HOADONCHITIET.ID
			  
			  SELECT * FROM HOADON
INSERT INTO HOADON ( IdNV, IdKH, MaHoaDon, CapBac, PhanTramGia, TienPhieuGiam, DiemDoi, PhuongThucTT, TienKhDua, TienKhChuyenKhoan, TienThua, ThanhTien, NgayThanhToan, TrangThai)
VALUES 
( 1, 1, 'HD001' , 0, NULL, NULL, NULL, 0, 1500000, NULL, NULL, 1500000, '2023-11-01T15:30:00', 1),

	update CHI_TIET_SAN_PHAM
	set GiaBan = GiaNiemYet
	where id =15
	DELETE HOADONCHITIET WHERE IdCTSP =0  and IdHoaDon = 0
	select * from CHI_TIET_SAN_PHAM
DROP TRIGGER trgAfterInsert_HOADONCHITIET;
select * from KHACHHANG

-- Trigger cho bảng HOADONCHITIET  + ĐÃ CHẠY
-- CREATE TRIGGER trgAfterInsert_HOADONCHITIET
ON HOADONCHITIET
AFTER INSERT
AS
BEGIN
    SET NOCOUNT ON;

    DECLARE @IDPGG INT;
    DECLARE @LoaiPhieu BIT;
    DECLARE @GiaTri FLOAT;

    -- Kiểm tra xem ChiTietSanPham có thuộc Phiếu Giảm Giá không
    SELECT @IDPGG = p.ID, @LoaiPhieu = p.LoaiPhieu, @GiaTri = p.GiaTri
    FROM INSERTED i
    LEFT JOIN HOADON h ON i.IdHoaDon = h.ID
    LEFT JOIN PHIEU_GIAM_GIA p ON h.IdPGG = p.ID;

    IF @IDPGG IS NULL
    BEGIN
        -- Nếu không thuộc Phiếu Giảm Giá, set các cột như yêu cầu
        UPDATE HOADONCHITIET
        SET 
            LoaiDGG = 0,
            GiaTriDGG = 0,
            QuyDoiDGGTT = 0.0,
            GiaBan = 0.0,
            DonGia = 0.0,
            ThanhTien = 0.0,
            TrangThai = 1
        FROM HOADONCHITIET
        INNER JOIN INSERTED ON HOADONCHITIET.ID = INSERTED.ID;
    END
    ELSE
    BEGIN
        -- Nếu thuộc Phiếu Giảm Giá, kiểm tra Loại Phiếu
        IF @LoaiPhieu = 0
        BEGIN
            -- Loại Phiếu là %
            UPDATE HOADONCHITIET
            SET 
                QuyDoiDGGTT = IdCTSP.GiaNiemYet - (IdCTSP.GiaNiemYet * (@GiaTri / 100)),
                GiaBan = IdCTSP.GiaNiemYet - (IdCTSP.GiaNiemYet * (@GiaTri / 100)),
                DonGia = IdCTSP.GiaNiemYet - (IdCTSP.GiaNiemYet * (@GiaTri / 100)),
                ThanhTien = INSERTED.SoLuong * (IdCTSP.GiaNiemYet - (IdCTSP.GiaNiemYet * (@GiaTri / 100)))
            FROM HOADONCHITIET
            INNER JOIN INSERTED ON HOADONCHITIET.ID = INSERTED.ID
            INNER JOIN CHI_TIET_SAN_PHAM IdCTSP ON HOADONCHITIET.IdCTSP = IdCTSP.ID;

            -- Cập nhật GiaBan và SốLượngTon trong CHI_TIET_SAN_PHAM
            UPDATE CHI_TIET_SAN_PHAM
            SET 
                GiaBan = IdCTSP.GiaNiemYet - (IdCTSP.GiaNiemYet * (@GiaTri / 100)),
                SoLuongTon = IdCTSP.SoLuongTon - INSERTED.SoLuong
            FROM CHI_TIET_SAN_PHAM
            INNER JOIN INSERTED ON CHI_TIET_SAN_PHAM.ID = INSERTED.IdCTSP
            INNER JOIN CHI_TIET_SAN_PHAM IdCTSP ON CHI_TIET_SAN_PHAM.ID = IdCTSP.ID;
        END
        ELSE
        BEGIN
            -- Loại Phiếu là VND
            UPDATE HOADONCHITIET
            SET 
                QuyDoiDGGTT = @GiaTri,
                GiaBan = IdCTSP.GiaNiemYet - @GiaTri,
                DonGia = IdCTSP.GiaNiemYet - @GiaTri,
                ThanhTien = INSERTED.SoLuong * (IdCTSP.GiaNiemYet - @GiaTri)
            FROM HOADONCHITIET
            INNER JOIN INSERTED ON HOADONCHITIET.ID = INSERTED.ID
            INNER JOIN CHI_TIET_SAN_PHAM IdCTSP ON HOADONCHITIET.IdCTSP = IdCTSP.ID;

            -- Cập nhật GiaBan và SốLượngTon trong CHI_TIET_SAN_PHAM
            UPDATE CHI_TIET_SAN_PHAM
            SET 
                GiaBan = IdCTSP.GiaNiemYet - @GiaTri,
                SoLuongTon = IdCTSP.SoLuongTon - INSERTED.SoLuong
            FROM CHI_TIET_SAN_PHAM
            INNER JOIN INSERTED ON CHI_TIET_SAN_PHAM.ID = INSERTED.IdCTSP
            INNER JOIN CHI_TIET_SAN_PHAM IdCTSP ON CHI_TIET_SAN_PHAM.ID = IdCTSP.ID;
        END
    END;
END;
-------------------------------------------------------------------------------------------
SELECT *  FROM HOADON AS HD
JOIN PHIEUGIAOHANG  AS PGH ON PGH.IdHoaDon = HD.ID


---------------------------------------------
-- Bỏ
-- Trigger cho bảng HOADONCHITIET
--CREATE TRIGGER trgAfterInsert_HOADONCHITIET
