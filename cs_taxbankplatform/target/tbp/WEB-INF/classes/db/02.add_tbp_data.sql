-- 更新说明
-- 1,添加了 银行贷款协议管理模块--贷款协议维护
INSERT INTO `tb_resource` VALUES ('121', null, '贷款协议管理', 'agreement', '0', '', null, 'fa  fa-book', '0', '', '2016-09-18 09:35:36', '2016-09-18 09:51:56');
INSERT INTO `tb_resource` VALUES ('122', '125', '添加', 'loanagreement:add', '1', '/loanagreement/addUI.html', null, null, '0', '', '2016-09-18 09:39:32', '2016-09-18 09:52:29');
INSERT INTO `tb_resource` VALUES ('123', '125', '编辑', 'loanagreement:edit', '1', '/loanagreement/editUI.html', null, null, '0', '', '2016-09-18 09:40:21', '2016-09-18 09:52:16');
INSERT INTO `tb_resource` VALUES ('124', '125', '删除', 'loanagreement:deleteBatch', '1', '/loanagreement/deleteBatch.html', null, null, '0', '', '2016-09-18 09:41:32', '2016-09-18 09:52:06');
INSERT INTO `tb_resource` VALUES ('125', '121', '贷款协议维护', 'agreement:loanagreement', '0', '/loanagreement/listUI.html', null, null, '0', '', '2016-09-18 09:49:58', '2016-09-18 09:51:41');

-- 2,tb_loan_forms_attach表中的enable字段修正为enabled

ALTER TABLE `tb_loan_forms_attach`
CHANGE COLUMN `enable` `enabled`  varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT 'Y' COMMENT '是否可见' AFTER `updatetime`;

-- 3,tb_financial_product表中的覆盖区域字段fp_overlay_pc_ids 长度不够,从100修改为1000

ALTER TABLE `tb_financial_product`
MODIFY COLUMN `fp_overlay_pc_ids`  varchar(1000) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '覆盖区域' AFTER `ps_id`;