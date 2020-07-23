ALTER TABLE `user_order`
MODIFY COLUMN `play_hall`  int(11) NULL DEFAULT NULL COMMENT '播放厅' AFTER `movie_name`;

