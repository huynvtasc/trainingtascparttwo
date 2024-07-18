CREATE TABLE `user` (
                        `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
                        `fullname` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
                        `username` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
                        `password` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
                        `created_at` datetime DEFAULT NULL ON UPDATE current_timestamp(),
                        `modified_at` datetime DEFAULT NULL ON UPDATE current_timestamp(),
                        PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
