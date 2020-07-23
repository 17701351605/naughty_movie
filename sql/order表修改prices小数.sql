ALTER TABLE `user_order`
MODIFY COLUMN `prices`  decimal(10,2) NULL DEFAULT NULL COMMENT '实付总价' AFTER `movie_name`;

