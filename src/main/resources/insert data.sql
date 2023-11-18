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

