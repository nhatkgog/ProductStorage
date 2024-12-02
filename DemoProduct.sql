Create database DemoProduct
go

Use DemoProduct
go

create table Product(
ProductID int primary key identity,
ProductName nvarchar(255),
Quantity int not null
)
go

insert into Product values
('Apple', 10),
('Banana', 15),
('Orange', 20);
go