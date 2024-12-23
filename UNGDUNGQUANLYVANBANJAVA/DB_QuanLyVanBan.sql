CREATE DATABASE QUANLYCONGVAN
drop database QuanLyCongVan;
use QUANLYCONGVAN;


CREATE TABLE SOVANBAN (
    maSo INT PRIMARY KEY IDENTITY(1,1),
    soVanBan NVARCHAR(100) NOT NULL,
    soDen BIT,
    nam INT NOT NULL,
	daXoa BIT DEFAULT 0,
);
drop table sovanban;
select * from sovanban
INSERT INTO SOVANBAN (soVanBan, soDen, nam) VALUES 
(N'Số văn bản đến', 1, 2020),
(N'Số văn bản đến', 0, 2021), 
(N'Số văn bản đến', 1, 2022),
(N'Số văn bản đến', 1, 2023),
(N'Số văn bản đến', 0, 2024),
(N'Số văn bản đi', 1, 2020),
(N'Số văn bản đi', 0, 2021),
(N'Số văn bản đi', 1, 2022),  
(N'Số văn bản đi', 1, 2023), 
(N'Số văn bản đi', 0, 2024);
DROP TABLE LOAIVANBAN;



CREATE TABLE tblPhongBan
(   
	MaPhongBan INT PRIMARY KEY IDENTITY (1,1),
    TenPhongBan NVARCHAR(50) UNIQUE
);
drop table tblPhongBan


insert into tblPhongBan (TenPhongBan)
values
(N'Phòng Kế hoạch'),
(N'Phòng Thiết kế'),
(N'Phòng Kỹ thuật'),
(N'Phòng Dự toán'),
(N'Phòng Tài chính'),
(N'Phòng Nhân sự'),
(N'Phòng Pháp chế'),
(N'Ban Giám đốc'),
(N'Phòng An toàn Lao động'),
(N'Phòng Vật tư'),
(N'Ban Quản lý Dự án'),
(N'Phòng Kiểm tra Chất lượng');


CREATE TABLE TAILIEU (
    MaTL INT PRIMARY KEY IDENTITY(1,1),           
    TenTL NVARCHAR(max),              
    NgayTao DATE,                               
    KichCo INT,                                   
    Loai NVARCHAR(50),                           
    DuongDan NVARCHAR(200)                  
);
drop table TAILIEU

drop table vanbannoibo;
CREATE TABLE VanBanNoiBo
(
    SoKyHieu VARCHAR(50) PRIMARY KEY,
    TenVanBan NVARCHAR(max),
    NgayBanHanh DATE,

    maloai CHAR(10), -- Khóa ngoại đến LOAIVANBAN
    MaBanHanh INT, -- Khóa ngoại đến tblPhongBan (Phòng ban hành)
    MaBanNhan INT, -- Khóa ngoại đến tblPhongBan (Phòng ban nhận)

    NguoiNhan NVARCHAR(50),
    NguoiKy NVARCHAR(50),
    NguoiDuyet NVARCHAR(50),
    TrichYeu NVARCHAR(max),
    NoiDung NVARCHAR(max),
    CONSTRAINT FK_MaLoai FOREIGN KEY (maloai) REFERENCES LOAIVANBAN(maloai),
    CONSTRAINT FK_MaPhongHanh FOREIGN KEY (MaBanHanh) REFERENCES tblPhongBan(MaPhongBan),
    CONSTRAINT FK_MaPhongNhan FOREIGN KEY (MabanNhan) REFERENCES tblPhongBan(MaPhongBan),

	-- Mã tài liệu;
	MaTL INT,
	CONSTRAINT FK_MaTaiLieu FOREIGN KEY (MaTL) REFERENCES TAILIEU(MaTL)
);




CREATE TABLE LOAIVANBAN(
	maLoai char(10) primary key,
	loaiVanBan nvarchar(100) not null,
	moTa nvarchar(max),
	daXoa bit DEFAULT 0,
);
INSERT INTO LOAIVANBAN (maLoai, loaiVanBan, moTa)
VALUES
('BV', N'Bản vẽ', N'Tài liệu chứa các bản vẽ thiết kế và kỹ thuật chi tiết của công trình.'),
('HD', N'Hợp đồng', N'Văn bản thỏa thuận giữa các bên liên quan về điều khoản và trách nhiệm.'),
('BCPD', N'Báo cáo tiến độ công trình', N'Tài liệu trình bày tiến độ thực hiện các hạng mục trong dự án.'),
('TLP', N'Tài liệu pháp lý', N'Tài liệu liên quan đến các yêu cầu pháp lý và quy định cần tuân thủ.'),
('HSD', N'Hồ sơ dự án', N'Tập hợp thông tin và tài liệu chi tiết về dự án, bao gồm mục tiêu, phạm vi, và tiến độ.'),
('TTK', N'Tài liệu thiết kế', N'Tài liệu mô tả các chi tiết thiết kế của công trình, bao gồm bản vẽ và thông số kỹ thuật.'),
('TQTC', N'Tài liệu quản lý thi công', N'Tài liệu hướng dẫn và theo dõi quy trình thực hiện thi công công trình.'),
('TLVT', N'Tài liệu về vật liệu và thiết bị', N'Tài liệu chi tiết về danh mục, thông số kỹ thuật của vật liệu và thiết bị sử dụng trong dự án.'),
('TLATL', N'Tài liệu an toàn lao động', N'Tài liệu hướng dẫn và quy định đảm bảo an toàn cho nhân viên tại công trường.'),
('TLTC', N'Tài liệu tài chính', N'Tài liệu liên quan đến chi phí, ngân sách và các báo cáo tài chính của dự án.'),
('TLBTBG', N'Tài liệu bảo trì và bàn giao', N'Tài liệu cung cấp thông tin về bảo trì và quy trình bàn giao công trình sau hoàn thiện.'),
('TLKTCL', N'Tài liệu kiểm tra chất lượng', N'Tài liệu ghi nhận các kết quả kiểm tra và đánh giá chất lượng của các hạng mục.'),
('HSNL', N'Hồ sơ quản lý nhân lực', N'Tài liệu liên quan đến thông tin, hồ sơ và công việc của nhân lực tham gia dự án.');

drop table loaivanban
CREATE TABLE NOIBANHANH
(
    maNoiBanHanh INT identity(1,1) PRIMARY KEY,  -- Giả sử `maNoiBanHanh` là một số nguyên
    noiBanHanh NVARCHAR(300) not null,      -- Kiểu chuỗi với độ dài tối đa 100 ký tự
    moTa NVARCHAR(max),                     -- Kiểu dữ liệu văn bản dài
    daXoa BIT DEFAULT 0            -- Kiểu boolean, mặc định là 0 (chưa xóa)
);

drop table noibanhanh;
INSERT INTO NOIBANHANH (noiBanHanh, moTa)
VALUES
(N'Phòng Kế hoạch', N'Chịu trách nhiệm lập kế hoạch và theo dõi tiến độ dự án.'),
(N'Phòng Thiết kế', N'Chịu trách nhiệm thiết kế kỹ thuật và các bản vẽ xây dựng.'),
(N'Phòng Kỹ thuật', N'Đảm bảo chất lượng kỹ thuật và giám sát thi công tại công trường.'),
(N'Phòng Dự toán', N'Phụ trách lập dự toán chi phí và quản lý ngân sách dự án.'),
(N'Phòng Tài chính', N'Quản lý tài chính và xử lý các vấn đề liên quan đến ngân sách.'),
(N'Phòng Nhân sự', N'Tuyển dụng, quản lý và đào tạo nhân lực cho công ty.'),
(N'Phòng Pháp chế', N'Xử lý các vấn đề pháp lý và đảm bảo tuân thủ quy định pháp luật.'),
(N'Ban Giám đốc', N'Đưa ra các quyết định chiến lược và quản lý toàn bộ hoạt động công ty.'),
(N'Phòng An toàn Lao động', N'Giám sát và đảm bảo an toàn lao động tại công trường.'),
(N'Phòng Vật tư', N'Quản lý cung ứng và kiểm soát vật tư, thiết bị cho dự án.'),
(N'Ban Quản lý Dự án', N'Tổ chức thực hiện và giám sát toàn bộ dự án xây dựng.'),
(N'Phòng Kiểm tra Chất lượng', N'Đánh giá và kiểm tra chất lượng các công trình xây dựng.');


CREATE TABLE VANBANDEN (
	id int identity(1,1) primary key,
    tenso nvarchar(300),
	nam int,
    soKyHieu VARCHAR(30) NOT NULL,
    ngayBanHanh DATE NOT NULL,
    noiBanHanh nvarchar(300),
	loaiVanBan nvarchar(300),
	soDen INT NOT NULL,
    ngayDen DATE NOT NULL,
    soTrang INT,
    nguoiNhan NVARCHAR(200),
    nguoiKy NVARCHAR(200),
    nguoiDuyet NVARCHAR(200),
	trichYeu Nvarchar(max),
	noiDung Nvarchar(max),
	dinhKemfile nvarchar(max),
	daXoa bit default 0
);

drop table vanbandi;

select * from  VANBANDEN;

CREATE TABLE VANBANDI (
	id int identity(1,1) primary key,
    tenSo nvarchar(300),
	nam int,
    soKyHieu VARCHAR(30) NOT NULL,
    ngayBanHanh DATE,
    noiNhan VARCHAR(300),
    loaiVanBan nvarchar(300),
    soDi INT,
    slBan INT,
    soTrang INT,
    nguoiGui NVARCHAR(200),
    nguoiKy NVARCHAR(200),
    nguoiDuyet NVARCHAR(200),
	trichYeu Nvarchar(max),
	noiDung Nvarchar(max),
	dinhKemfile nvarchar(max),
	daXoa bit default 0
);
drop table VanBanDen;
drop table vanbannoibo
drop table tailieu





CREATE TABLE tblNguoiDung (

HoVaTen NVARCHAR(500),

Email NVARCHAR(50),

SoDienThoai CHAR(10),

TenTaiKhoan VARCHAR(50) PRIMARY KEY,

MatKhau VARCHAR(255),

VaiTro NVARCHAR(50)

);

SELECT * FROM tblNguoiDung

FALTER TABLE tblNguoiDung

ALTER COLUMN MatKhau VARCHAR(255);

DROP table tblNguoiDung;

 CREATE TABLE tblNguoiDung (
	CoQuan NVARCHAR(500),
    HoVaTen NVARCHAR(500),
    Email NVARCHAR(50),
    SoDienThoai CHAR(10),
    TenTaiKhoan VARCHAR(50) PRIMARY KEY,
    MatKhau VARCHAR(255),
    VaiTro NVARCHAR(50),
    Salt VARBINARY(255)  -- Sử dụng VARBINARY thay cho BLOB để lưu trữ giá trị Salt
);

INSERT INTO tblNguoiDung (CoQuan, HoVaTen, Email, SoDienThoai, TenTaiKhoan, MatKhau, VaiTro, Salt)
VALUES 
(N'Phòng Kế Toán', N'Nguyễn Văn Dũng', 'nguyendung@gmail.com', '0901234567', 'nguyendung', 'abcd12345', N'user', 0xA1B2C3D4E5F6),
(N'Ban Giám Đốc', N'Phạm Thị Hạnh', 'phamh@gmail.com', '0987123456', 'phamhanh', 'abcd12345', N'admin', 0xF1E2D3C4B5A6);

select * from tblNguoiDung

CREATE TABLE tblThongTinKyDuyet (
	id INT IDENTITY(1,1) PRIMARY KEY,
    NguoiKy VARCHAR(255) NOT NULL,
    NguoiDuyet VARCHAR(255) NOT NULL,
    NguoiGui VARCHAR(255) NOT NULL,
    NguoiNhan VARCHAR(255) NOT NULL,
    NoiBanHanh VARCHAR(255) NOT NULL
);
select * from tblThongTinKyDuyet