-- Insert into author table
INSERT INTO `bo_mo_c`.`author`
(`Id`,
`Name`,
`Nationallity`,
`Birthday`)
VALUES
(1, 'John Doe', 'American', '1990-05-15'),
-- Add more rows below with similar structure
(2, 'Jane Smith', 'British', '1985-08-22'),
(3, 'Carlos Rodriguez', 'Spanish', '1978-11-10'),
(4, 'Yuki Tanaka', 'Japanese', '1982-03-25'),
(5, 'Sophie Dubois', 'French', '1995-07-08');

-- Insert into publisher table
INSERT INTO `bo_mo_c`.`publisher`
(`Id`,
`Name`,
`Headquarter`)
VALUES
(1, 'XYZ Publications', 'New York'),
-- Add more rows below with similar structure
(2, 'Global Books Ltd.', 'London'),
(3, 'Libros Españoles', 'Madrid'),
(4, 'Tokyo Publishing House', 'Tokyo'),
(5, 'Éditions Françaises', 'Paris');


INSERT INTO `bo_mo_c`.`book`
(`Id`,
`AuthorId`,
`PublisherId`,
`Title`,
`Description`,
`PublishYear`,
`NumerOfPages`,
`Image`,
`Price`,
`Language`)
VALUES
(1, 1, 1, 'The Great Book', 'An amazing book about something', 2020, 300, 'book_image.jpg', 19.99, 'English'),
-- Add more rows below with similar structure
(2, 2, 2, 'Another Book', 'A fantastic read for everyone', 2021, 250, 'another_book_image.jpg', 24.99, 'Spanish'),
(3, 3, 2, 'Mystery Novel', 'A thrilling mystery story', 2019, 400, 'mystery_novel_image.jpg', 29.99, 'French'),
(4, 4, 3, 'Science Fiction Adventure', 'Explore the universe in this sci-fi adventure', 2022, 350, 'sci_fi_adventure_image.jpg', 34.99, 'German'),
(5, 5, 4, 'Historical Drama', 'Step back in time with this gripping historical drama', 2018, 500, 'historical_drama_image.jpg', 39.99, 'Italian');

INSERT INTO `bo_mo_c`.`bookcategory` (`Id`, `Name`)
VALUES
(1, 'Fiction'),
(2, 'Non-Fiction'),
(3, 'Mystery'),
(4, 'Science Fiction'),
(5, 'Fantasy'),
(6, 'Romance'),
(7, 'Biography'),
(8, 'History'),
(9, 'Self-Help'),
(10, 'Science'),
(11, 'Technology'),
(12, 'Business'),
(13, 'Cooking'),
(14, 'Travel'),
(15, 'Poetry');

INSERT INTO `bo_mo_c`.`book_bookcategory` (`BookId`, `BookCategoryId`)
VALUES
(1, 1),  -- Book 1 belongs to Category 1
(1, 2),  -- Book 1 also belongs to Category 2
(2, 3),  -- Book 2 belongs to Category 3
(2, 4),  -- Book 2 also belongs to Category 4
(3, 5); 

INSERT INTO `bo_mo_c`.`customer` VALUES (1,'nmq@gmail.com','Quan1','0977513028'),(2,'tqm@gmail.com','Minh','0384333222');
INSERT INTO `bo_mo_c`. `account` VALUES (1,1,'nmq21012002','pass1');
INSERT INTO `bo_mo_c`. `address` VALUES (1,1,'2320','PhamHung','HaNoi','NamTuLiem'),(2,2,'64','LoDuc','HaNoi','HoanKiem');

INSERT INTO `bo_mo_c`. `brand` VALUES (1,'Nike','Beaverton, Oregon'),(2,'Adidas','Herzogenaurach, Germany'),(3,'Gucci','Florence, Italy'),(4,'H&M','Stockholm, Sweden'),(5,'Ananas','HoChiMinh, VietNam');
INSERT INTO `bo_mo_c`. `clothes` VALUES (1,2,'T-shirt','M','tshirt.jpg',20),(2,1,'Jeans','32x34','jeans.jpg',50),(3,3,'Sweater Test','L','sweater.jpg',40),(4,2,'Dress','S','dress.jpg',60),(5,2,'Sweater Test','L','sweater.jpg',40);
INSERT INTO `bo_mo_c`. `clothescategory` VALUES (1,'Shirts'),(2,'Jeans'),(3,'Dresses'),(4,'Sweaters'),(5,'Hoodie'),(6,'Shorts');
INSERT INTO `bo_mo_c`. `clothes_clothescategory` VALUES (1,1),(2,2),(4,3),(3,4);
INSERT INTO `bo_mo_c`. `fullname` VALUES (1,1,'Nguyen','Quan'),(2,2,'Tran','Minh');
INSERT INTO `bo_mo_c`. `producer` VALUES (1,'Apple','USA'),(2,'Samsung','Korea'),(3,'XiaoMi','China'),(4,'Vsmart','VietNam'),(5,'Bphone','VietNam');
INSERT INTO `bo_mo_c`. `mobile` VALUES (1,1,'IphoneXS','iphonexs.jpg',23,'For poor',100),(2,1,'Iphone16','iphone16.jpg',23,'For rich',250),(3,1,'Iphone10','iphone10.jpg',23,'For poor',150),(4,2,'Samsung S10','samsungs10.jpg',10,'For Korean',50),(5,3,'XiaoMi RedMi 8','redmi8.jpg',11,'For Chinese',20),(6,4,'Vsmart 8','vsmart8.jpg',30,'For Vietnamese',200);
INSERT INTO `bo_mo_c`. `voucher` VALUES (1,'10OFF','2023-12-29',10,'Minimum purchase of $50'),(2,'FREESHIP','2023-11-30',100,'Valid for free shipping'),(3,'20PERCENT','2024-01-15',20,'Applicable on all items'),(4,'SALE50','2023-12-15',50,'Valid on sale items'),(5,'WELCOME10','2023-11-25',10,'For new customers only'),(6,'10OAF','2023-12-29',20,'Minimum purchase of $100');
INSERT INTO `bo_mo_c`. `address` VALUES (1,1,'2320','PhamHung','HaNoi','NamTuLiem'),(2,2,'64','LoDuc','HaNoi','HoanKiem');
INSERT INTO `bo_mo_c`. `rating` VALUES (1,1,NULL,NULL,'2023-11-19',7),(2,NULL,1,NULL,'2023-11-20',5),(3,NULL,NULL,1,'2023-11-19',9),(4,NULL,2,NULL,'2023-11-22',7),(5,2,NULL,NULL,'2023-11-23',6),(6,1,NULL,NULL,'2023-12-19',5);


