ALTER TABLE `user_order`
ADD COLUMN `status`  int(11) NULL DEFAULT NULL COMMENT '1可以退票2退票成功' AFTER `is_del`;