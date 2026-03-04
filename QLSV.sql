create database QLSV;
use QLSV;

create table sinhvien(
masv varchar(10) primary key,
hoten varchar(100) not null,
ngaysinh date not null,
nganh varchar(10) not null,
diemTB double not null,
lop varchar(20) not null
);
INSERT INTO sinhvien VALUES
('4551050001', 'Nguyen Van A', '2004-01-15', 'CNTT', 8.5, 'CNTT1'),
('4551050002', 'Tran Thi B', '2004-03-22', 'CNTT', 7.8, 'CNTT1'),
('4551050003', 'Le Van C', '2003-12-05', 'CNTT', 9.1, 'CNTT1'),

('4551050004', 'Pham Thi D', '2004-06-10', 'CNTT', 6.9, 'CNTT2'),
('4551050005', 'Hoang Van E', '2003-09-18', 'CNTT', 7.4, 'CNTT2'),
('4551050006', 'Vo Thi F', '2004-02-28', 'CNTT', 8.0, 'CNTT2'),
('4551050007', 'Dang Van G', '2003-11-11', 'CNTT', 5.9, 'CNTT2'),

('4551090001', 'Bui Thi H', '2004-07-03', 'KTPM', 6.5, 'KTPM1'),
('4551090002', 'Nguyen Van I', '2003-10-25', 'KTPM', 7.2, 'KTPM1'),
('4551090003', 'Tran Thi K', '2004-04-14', 'KTPM', 8.7, 'KTPM1');

