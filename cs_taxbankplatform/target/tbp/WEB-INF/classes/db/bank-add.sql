INSERT INTO `tb_parameters` (`p_id`, `p_code`, `p_name`, `p_value`, `enabled`, `creatorid`, `createtime`, `updatorid`, `updatetime`) VALUES ('SCJT300', 'getdhgzsj', '获取贷后数据构造配置IP', '200.100.73.188', 'Y', '1', '2017-10-17 17:33:57', '1', '2017-10-17 17:34:06');

UPDATE `tb_organizations` SET `org_code`='GLBANK' WHERE  `org_id`='ZZ0012017101218583016607';

UPDATE `tb_repayment_way` SET `rw_name`='每月付息，到期还本', `rw_code`='每月付息，到期还本' WHERE  `rw_id`='HKFS01';

UPDATE `tb_loan_rates` SET `lr_name`='12%以上', `lr_code`='12up' WHERE  `lr_id`='LL0012017101219225341639';

CREATE TABLE `tb_interfacelog` (
	`il_id` VARCHAR(50) NOT NULL COMMENT '主键',
	`bh` VARCHAR(50) NULL DEFAULT NULL COMMENT '编号',
	`nsrsbh` VARCHAR(50) NULL DEFAULT NULL COMMENT '纳税人识别号/社会信用统一代码',
	`interfaceName` VARCHAR(50) NULL DEFAULT NULL COMMENT '接口名称',
	`errorLog` VARCHAR(200) NULL DEFAULT NULL COMMENT '报错信息',
	`content` VARCHAR(500) NULL DEFAULT NULL COMMENT '参数',
	`createtime` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
	PRIMARY KEY (`il_id`)
)
COMMENT='接口日志记录'
COLLATE='utf8_general_ci'
ENGINE=InnoDB;

