BEGIN TRANSACTION;
CREATE TABLE test1 (id NUMERIC, AirConditioner TEXT, Date NUMERIC, Characters TEXT);
INSERT INTO test1 VALUES(1,'制冷王',2015,'美的制冷王空调，开启智慧生活，hold住酷暑高温!');
INSERT INTO test1 VALUES(2,'儿童星',2015,'美的儿童空调，智能感应，呵护备至!');
INSERT INTO test1 VALUES(3,'舒适星',2015,'有凉感，无风感，智能呵护舒适整晚');
COMMIT;
