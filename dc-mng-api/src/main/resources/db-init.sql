INSERT INTO `dc_menu` (`menu_id`, `create_by`, `create_time`, `update_time`, `update_by`, `cache`, `component`, `name`, `hidden`, `i_frame`, `icon`, `menu_sort`, `path`, `permission`, `pid`, `sub_count`, `title`, `type`) VALUES (1, NULL, NULL, NULL, NULL, b'0', NULL, NULL, b'0', b'0', 'system', 1, 'system', NULL, NULL, 4, '系统管理', 0);
INSERT INTO `dc_menu` (`menu_id`, `create_by`, `create_time`, `update_time`, `update_by`, `cache`, `component`, `name`, `hidden`, `i_frame`, `icon`, `menu_sort`, `path`, `permission`, `pid`, `sub_count`, `title`, `type`) VALUES (2, NULL, NULL, NULL, NULL, b'0', 'system/user/index', 'User', b'0', b'0', 'peoples', 2, 'user', 'user:list', 1, 3, '用户管理', 1);
INSERT INTO `dc_menu` (`menu_id`, `create_by`, `create_time`, `update_time`, `update_by`, `cache`, `component`, `name`, `hidden`, `i_frame`, `icon`, `menu_sort`, `path`, `permission`, `pid`, `sub_count`, `title`, `type`) VALUES (3, NULL, NULL, NULL, NULL, b'0', 'system/role/index', 'Role', b'0', b'0', 'role', 3, 'role', 'roles:list', 1, 3, '角色管理', 1);
INSERT INTO `dc_menu` (`menu_id`, `create_by`, `create_time`, `update_time`, `update_by`, `cache`, `component`, `name`, `hidden`, `i_frame`, `icon`, `menu_sort`, `path`, `permission`, `pid`, `sub_count`, `title`, `type`) VALUES (4, NULL, NULL, NULL, NULL, b'0', 'system/menu/index', 'Menu', b'0', b'0', 'menu', 5, 'menu', 'menu:list', 1, 3, '菜单管理', 1);
INSERT INTO `dc_menu` (`menu_id`, `create_by`, `create_time`, `update_time`, `update_by`, `cache`, `component`, `name`, `hidden`, `i_frame`, `icon`, `menu_sort`, `path`, `permission`, `pid`, `sub_count`, `title`, `type`) VALUES (5, NULL, NULL, NULL, NULL, b'0', 'system/dict/index', 'Dict', b'0', b'0', 'education', 4, 'dict', '1', 1, 0, '字典管理', 1);
INSERT INTO `dc_menu` (`menu_id`, `create_by`, `create_time`, `update_time`, `update_by`, `cache`, `component`, `name`, `hidden`, `i_frame`, `icon`, `menu_sort`, `path`, `permission`, `pid`, `sub_count`, `title`, `type`) VALUES (6, NULL, NULL, NULL, NULL, b'0', NULL, NULL, b'0', b'0', NULL, 2, NULL, 'user:add', 2, 0, '用户新增', 2);
INSERT INTO `dc_menu` (`menu_id`, `create_by`, `create_time`, `update_time`, `update_by`, `cache`, `component`, `name`, `hidden`, `i_frame`, `icon`, `menu_sort`, `path`, `permission`, `pid`, `sub_count`, `title`, `type`) VALUES (7, NULL, NULL, NULL, NULL, b'0', NULL, NULL, b'0', b'0', NULL, 3, NULL, 'user:edit', 2, 0, '用户编辑', 2);
INSERT INTO `dc_menu` (`menu_id`, `create_by`, `create_time`, `update_time`, `update_by`, `cache`, `component`, `name`, `hidden`, `i_frame`, `icon`, `menu_sort`, `path`, `permission`, `pid`, `sub_count`, `title`, `type`) VALUES (8, NULL, NULL, NULL, NULL, b'0', NULL, NULL, b'0', b'0', NULL, 4, NULL, 'user:del', 2, 0, '用户删除', 2);
INSERT INTO `dc_menu` (`menu_id`, `create_by`, `create_time`, `update_time`, `update_by`, `cache`, `component`, `name`, `hidden`, `i_frame`, `icon`, `menu_sort`, `path`, `permission`, `pid`, `sub_count`, `title`, `type`) VALUES (9, NULL, NULL, NULL, NULL, b'0', NULL, NULL, b'0', b'0', NULL, 2, NULL, 'roles:add', 3, 0, '角色创建', 2);
INSERT INTO `dc_menu` (`menu_id`, `create_by`, `create_time`, `update_time`, `update_by`, `cache`, `component`, `name`, `hidden`, `i_frame`, `icon`, `menu_sort`, `path`, `permission`, `pid`, `sub_count`, `title`, `type`) VALUES (10, NULL, NULL, NULL, NULL, b'0', NULL, NULL, b'0', b'0', NULL, 3, NULL, 'roles:edit', 3, 0, '角色修改', 2);
INSERT INTO `dc_menu` (`menu_id`, `create_by`, `create_time`, `update_time`, `update_by`, `cache`, `component`, `name`, `hidden`, `i_frame`, `icon`, `menu_sort`, `path`, `permission`, `pid`, `sub_count`, `title`, `type`) VALUES (11, NULL, NULL, NULL, NULL, b'0', NULL, NULL, b'0', b'0', NULL, 4, NULL, 'roles:del', 3, 0, '角色删除', 2);
INSERT INTO `dc_menu` (`menu_id`, `create_by`, `create_time`, `update_time`, `update_by`, `cache`, `component`, `name`, `hidden`, `i_frame`, `icon`, `menu_sort`, `path`, `permission`, `pid`, `sub_count`, `title`, `type`) VALUES (12, NULL, NULL, NULL, NULL, b'0', NULL, NULL, b'0', b'0', NULL, 2, NULL, 'menu:add', 4, 0, '菜单新增', 2);
INSERT INTO `dc_menu` (`menu_id`, `create_by`, `create_time`, `update_time`, `update_by`, `cache`, `component`, `name`, `hidden`, `i_frame`, `icon`, `menu_sort`, `path`, `permission`, `pid`, `sub_count`, `title`, `type`) VALUES (13, NULL, NULL, NULL, NULL, b'0', NULL, NULL, b'0', b'0', NULL, 3, NULL, 'menu:edit', 4, 0, '菜单编辑', 2);
INSERT INTO `dc_menu` (`menu_id`, `create_by`, `create_time`, `update_time`, `update_by`, `cache`, `component`, `name`, `hidden`, `i_frame`, `icon`, `menu_sort`, `path`, `permission`, `pid`, `sub_count`, `title`, `type`) VALUES (14, NULL, NULL, NULL, NULL, b'0', NULL, NULL, b'0', b'0', NULL, 4, NULL, 'menu:del', 4, 0, '菜单删除', 2);
INSERT INTO `dc_menu` (`menu_id`, `create_by`, `create_time`, `update_time`, `update_by`, `cache`, `component`, `name`, `hidden`, `i_frame`, `icon`, `menu_sort`, `path`, `permission`, `pid`, `sub_count`, `title`, `type`) VALUES (15, NULL, NULL, NULL, NULL, b'0', NULL, NULL, b'0', b'0', NULL, 2, NULL, 'menu:add', 5, 0, '字典新增', 2);
INSERT INTO `dc_menu` (`menu_id`, `create_by`, `create_time`, `update_time`, `update_by`, `cache`, `component`, `name`, `hidden`, `i_frame`, `icon`, `menu_sort`, `path`, `permission`, `pid`, `sub_count`, `title`, `type`) VALUES (16, NULL, NULL, NULL, NULL, b'0', NULL, NULL, b'0', b'0', NULL, 3, NULL, 'menu:edit', 5, 0, '字典编辑', 2);
INSERT INTO `dc_menu` (`menu_id`, `create_by`, `create_time`, `update_time`, `update_by`, `cache`, `component`, `name`, `hidden`, `i_frame`, `icon`, `menu_sort`, `path`, `permission`, `pid`, `sub_count`, `title`, `type`) VALUES (17, NULL, NULL, NULL, NULL, b'0', NULL, NULL, b'0', b'0', NULL, 4, NULL, 'menu:del', 5, 0, '字典删除', 2);

INSERT INTO `dc_dept` (`dept_id`, `create_by`, `create_time`, `update_by`, `update_time`, `dept_sort`, `enabled`, `name`, `pid`, `sub_count`) VALUES (1, 'admin', NULL, NULL, NULL, 0, b'1', '总店', NULL, 1);

INSERT INTO `dc_user` (`user_id`, `create_by`, `create_time`, `update_by`, `update_time`, `avatar_name`, `avatar_path`, `email`, `enabled`, `gender`, `is_admin`, `nick_name`, `password`, `phone`, `pwd_reset_time`, `username`, `dept_id`) VALUES (1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, b'1', '男', b'1', '管理员', '$2a$10$9dLp/sYQbUVBOGFVv8WoPeTuGAf610xpcIpm8TEPMxFo5S2vFmpGy', NULL, NULL, 'admin', 1);

INSERT INTO `dc_role` (`role_id`, `create_by`, `create_time`, `update_time`, `update_by`, `description`, `level`, `name`) VALUES (1, NULL, NULL, NULL, NULL, '超级管理员', 1, '超级管理员');

INSERT INTO `dc_roles_menus` (`role_id`, `menu_id`) VALUES (1, 1);
INSERT INTO `dc_roles_menus` (`role_id`, `menu_id`) VALUES (1, 2);
INSERT INTO `dc_roles_menus` (`role_id`, `menu_id`) VALUES (1, 3);
INSERT INTO `dc_roles_menus` (`role_id`, `menu_id`) VALUES (1, 4);
INSERT INTO `dc_roles_menus` (`role_id`, `menu_id`) VALUES (1, 5);
INSERT INTO `dc_roles_menus` (`role_id`, `menu_id`) VALUES (1, 6);
INSERT INTO `dc_roles_menus` (`role_id`, `menu_id`) VALUES (1, 7);
INSERT INTO `dc_roles_menus` (`role_id`, `menu_id`) VALUES (1, 9);
INSERT INTO `dc_roles_menus` (`role_id`, `menu_id`) VALUES (1, 10);
INSERT INTO `dc_roles_menus` (`role_id`, `menu_id`) VALUES (1, 11);
INSERT INTO `dc_roles_menus` (`role_id`, `menu_id`) VALUES (1, 12);
INSERT INTO `dc_roles_menus` (`role_id`, `menu_id`) VALUES (1, 13);
INSERT INTO `dc_roles_menus` (`role_id`, `menu_id`) VALUES (1, 14);
INSERT INTO `dc_roles_menus` (`role_id`, `menu_id`) VALUES (1, 15);
INSERT INTO `dc_roles_menus` (`role_id`, `menu_id`) VALUES (1, 16);
INSERT INTO `dc_roles_menus` (`role_id`, `menu_id`) VALUES (1, 17);
INSERT INTO `dc_users_roles` (`user_id`, `role_id`) VALUES (1, 1);

INSERT INTO `dc_dict` (`dict_id`, `create_by`, `create_time`, `update_time`, `update_by`, `description`, `name`) VALUES (1, NULL, NULL, NULL, NULL, '用户状态', 'user_status');

INSERT INTO `dc_dict_detail` (`detail_id`, `create_by`, `create_time`, `update_time`, `update_by`, `dict_sort`, `label`, `value`, `dict_id`) VALUES (1, NULL, NULL, NULL, NULL, 1, '激活', 'true', 1);
INSERT INTO `dc_dict_detail` (`detail_id`, `create_by`, `create_time`, `update_time`, `update_by`, `dict_sort`, `label`, `value`, `dict_id`) VALUES (2, NULL, NULL, NULL, NULL, 2, '禁用', 'false', 1);
