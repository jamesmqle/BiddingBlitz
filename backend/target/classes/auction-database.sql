-- Insert items into the `item` table
INSERT INTO item (name, description, auctionType, auctionStatus, itemPrice, shippingPrice, isExpeditedShipping, winnerId)
VALUES
('Gaming Laptop', 'High-performance laptop for gaming', 'Forward', 'Active', 1500.00, 50.00, false, NULL),
('Gaming Mouse', 'Ergonomic mouse with RGB lighting', 'Dutch', 'Active', 75.00, 10.00, true, NULL),
('Bluetooth Headphones', 'Noise-canceling over-ear headphones', 'Forward', 'Active', 200.00, 20.00, false, NULL),
('Smartphone', 'Latest model with advanced features', 'Dutch', 'Active', 999.00, 15.00, true, NULL),
('Gaming Keyboard', 'Mechanical keyboard with RGB backlight', 'Forward', 'Active', 120.00, 10.00, false, NULL),
('4K Monitor', '27-inch UHD display for gaming and work', 'Dutch', 'Active', 350.00, 25.00, true, NULL),
('External SSD', 'Fast external storage drive', 'Forward', 'Active', 250.00, 5.00, false, NULL),
('Bluetooth Speaker', 'Portable speaker with excellent sound quality', 'Dutch', 'Active', 60.00, 8.00, false, NULL),
('Wireless Earbuds', 'Compact design with great sound quality', 'Forward', 'Active', 120.00, 5.00, true, NULL),
('Digital Camera', 'High-resolution camera for photography', 'Dutch', 'Active', 750.00, 30.00, false, NULL),
('Smartwatch', 'Fitness and health tracking watch', 'Forward', 'Active', 250.00, 10.00, true, NULL),
('Gaming Chair', 'Ergonomic chair for long gaming sessions', 'Dutch', 'Active', 300.00, 20.00, false, NULL),
('Tablet', 'Lightweight and portable tablet', 'Forward', 'Active', 400.00, 15.00, false, NULL),
('Home Assistant', 'Voice-controlled smart device', 'Dutch', 'Active', 150.00, 5.00, false, NULL),
('Gaming Console', 'Next-gen console for gaming', 'Forward', 'Active', 500.00, 20.00, true, NULL),
('Office Desk', 'Spacious desk for home or office use', 'Dutch', 'Active', 200.00, 40.00, false, NULL),
('Wireless Router', 'High-speed router with extended range', 'Forward', 'Active', 150.00, 10.00, true, NULL),
('Smart TV', '55-inch 4K UHD smart television', 'Dutch', 'Active', 700.00, 50.00, true, NULL),
('VR Headset', 'Immersive virtual reality headset', 'Forward', 'Active', 400.00, 15.00, false, NULL),
('Electric Scooter', 'Portable and eco-friendly scooter', 'Dutch', 'Active', 600.00, 25.00, false, NULL);

-- Insert auctions into the `forward_auction` table
INSERT INTO forward_auction (itemId, remainingTime)
VALUES
(1, 86400000),  -- 24 hours
(3, 43200000),  -- 12 hours
(5, 21600000),  -- 6 hours
(7, 10800000),  -- 3 hours
(9, 5400000),   -- 1.5 hours
(11, 3600000),  -- 1 hour
(13, 1800000),  -- 30 minutes
(15, 900000),   -- 15 minutes
(17, 300000),   -- 5 minutes
(19, 60000);    -- 1 minute

-- Insert auctions into the `dutch_auction` table
INSERT INTO dutch_auction (itemId, minPrice, decrementPrice, timeInterval)
VALUES
(2, 50.00, 5.00, 600),  -- 10 minutes interval
(4, 800.00, 20.00, 600),  -- 10 minutes interval
(6, 300.00, 10.00, 600),  -- 10 minutes interval
(8, 40.00, 2.00, 600),   -- 10 minutes interval
(10, 500.00, 25.00, 600), -- 10 minutes interval
(12, 200.00, 10.00, 600), -- 10 minutes interval
(14, 100.00, 5.00, 600),  -- 10 minutes interval
(16, 100.00, 10.00, 600), -- 10 minutes interval
(18, 600.00, 30.00, 600), -- 10 minutes interval
(20, 400.00, 20.00, 600); -- 10 minutes interval

-- Insert bidders into the `item_bidders` table
INSERT INTO item_bidders (itemId, bidder_id)
VALUES
(1, 101),
(1, 102),
(3, 103),
(3, 104),
(5, 105),
(5, 106),
(7, 107),
(7, 108),
(9, 109),
(9, 110),
(11, 111),
(11, 112),
(13, 113),
(13, 114),
(15, 115),
(15, 116),
(17, 117),
(17, 118),
(19, 119),
(19, 120);
