-- phpMyAdmin SQL Dump
-- version 4.9.0.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jul 11, 2021 at 05:41 AM
-- Server version: 10.4.6-MariaDB
-- PHP Version: 7.2.22

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `cashier`
--

-- --------------------------------------------------------

--
-- Table structure for table `products`
--

CREATE TABLE `products` (
  `id_product` varchar(5) NOT NULL,
  `product_name` varchar(120) NOT NULL,
  `price` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `products`
--

INSERT INTO `products` (`id_product`, `product_name`, `price`) VALUES
('A1234', 'Book', 10000),
('A3421', 'Pensil', 6000),
('AB123', 'Teh Tubruk', 4000),
('AS783', 'Hot Cream', 3000),
('B1221', 'Beras 1Kg', 12000),
('B1222', 'Gula Jawa', 22000),
('C3212', 'Le Mineral', 3600),
('C3213', 'Indomie Goreng', 2500),
('E3421', 'Susu Beruang', 9000),
('YA123', 'Tepung Terigu', 11000);

-- --------------------------------------------------------

--
-- Table structure for table `receipt`
--

CREATE TABLE `receipt` (
  `id_receipt` int(11) NOT NULL,
  `id_product` varchar(5) NOT NULL,
  `quantity` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `receipt`
--

INSERT INTO `receipt` (`id_receipt`, `id_product`, `quantity`) VALUES
(1, 'AB123', 6),
(1, 'AB123', 2),
(1, 'AB123', 2),
(2, 'AB123', 4),
(2, 'AB123', 2),
(3, 'AB123', 4),
(4, 'AB123', 6),
(4, 'A1234', 3),
(5, 'AB123', 7),
(5, 'A1234', 4),
(5, 'B1222', 4),
(6, 'A1234', 2),
(6, 'B1222', 1),
(7, 'A1234', 4),
(8, 'A1234', 2),
(8, 'B1222', 3),
(9, 'A1234', 4),
(9, 'YA123', 5),
(10, 'C3213', 10),
(10, 'YA123', 2),
(11, 'A1234', 6),
(11, 'C3212', 4),
(12, 'A1234', 5),
(12, 'C3212', 6);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `products`
--
ALTER TABLE `products`
  ADD PRIMARY KEY (`id_product`);

--
-- Indexes for table `receipt`
--
ALTER TABLE `receipt`
  ADD KEY `id_product` (`id_product`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
