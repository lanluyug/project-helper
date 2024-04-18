CREATE TABLE test_table (
    id INT PRIMARY KEY,
    name VARCHAR(20),
    age INT,
    address VARCHAR(200)
);

-- 创建一个存储过程，用于插入10万测试数据
DELIMITER //
CREATE PROCEDURE insert_test_data()
BEGIN
  DECLARE i INT DEFAULT 1;
  WHILE i <= 100000 DO
    -- 随机生成姓名和年龄
    SET @name = CONCAT('name', i);
    SET @address = CONCAT('address......................', i);
    SET @age = FLOOR(RAND() * 100);
    -- 插入数据
INSERT INTO test_table (id, name, age, address) VALUES (i, @name, @age, @address);
-- 更新计数器
SET i = i + 1;
END WHILE;
END //
DELIMITER ;

-- 调用存储过程
CALL insert_test_data();

