-- --------------------------------------------------------
-- 主机:                           10.10.10.14
-- 服务器版本:                        5.5.50 - MySQL Community Server (GPL)
-- 服务器操作系统:                      Win64
-- HeidiSQL 版本:                  8.2.0.4675
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;



-- 导出  表 tbp1.tb_attachment 结构
DROP TABLE IF EXISTS `tb_attachment`;
CREATE TABLE IF NOT EXISTS `tb_attachment` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '附件表id',
  `name` varchar(100) DEFAULT NULL COMMENT '附件名',
  `type` varchar(100) DEFAULT NULL COMMENT '附件类型',
  `size` bigint(20) DEFAULT NULL COMMENT '附件大小',
  `path` varchar(500) DEFAULT NULL COMMENT '附件路径',
  `url` varchar(500) DEFAULT NULL COMMENT '附件地址',
  `fk_table` varchar(50) DEFAULT NULL COMMENT '关联表',
  `fk_id_name` varchar(50) DEFAULT NULL COMMENT '关联表ID名',
  `fk_id` bigint(20) DEFAULT NULL COMMENT '关联ID',
  `status` varchar(4) DEFAULT NULL COMMENT '有效状态',
  `creator_id` int(11) DEFAULT NULL,
  `creator_name` varchar(100) DEFAULT NULL COMMENT '上传者',
  `createtime` timestamp NULL DEFAULT NULL COMMENT '上传人',
  PRIMARY KEY (`id`),
  KEY `fk_table` (`fk_table`,`fk_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 数据导出被取消选择。


-- 导出  表 tbp1.tb_feedback 结构
DROP TABLE IF EXISTS `tb_feedback`;
CREATE TABLE IF NOT EXISTS `tb_feedback` (
  `f_id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `region_id` int(11) unsigned NOT NULL COMMENT '区域ID',
  `enabled` varchar(1) NOT NULL COMMENT '状态',
  `f_title` varchar(50) NOT NULL COMMENT '反馈意见标题',
  `f_content` text NOT NULL COMMENT '反馈意见内容',
  `creatorid` int(11) unsigned NOT NULL COMMENT '创建人',
  `createtime` datetime NOT NULL COMMENT '创建日期',
  `updatorid` int(11) unsigned NOT NULL COMMENT '修改人',
  `updatetime` datetime NOT NULL COMMENT '修改日期',
  PRIMARY KEY (`f_id`),
  UNIQUE KEY `f_id` (`f_id`),
  KEY `FK_tb_feedback_tb_regions` (`region_id`),
  CONSTRAINT `FK_tb_feedback_tb_regions` FOREIGN KEY (`region_id`) REFERENCES `tb_regions` (`region_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='反馈建议';

-- 数据导出被取消选择。


-- 导出  表 tbp1.tb_financial_product 结构
DROP TABLE IF EXISTS `tb_financial_product`;
CREATE TABLE IF NOT EXISTS `tb_financial_product` (
  `fp_id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '金融产品Id',
  `fp_name` varchar(100) NOT NULL COMMENT '金融产品名称',
  `org_id` int(11) unsigned NOT NULL COMMENT '所属银行id',
  `region_id` int(11) NOT NULL COMMENT '所属区域',
  `fp_issue_time` timestamp NULL DEFAULT NULL COMMENT '发布时间',
  `fp_remove_time` timestamp NULL DEFAULT NULL COMMENT '下架时间',
  `la_id` int(11) unsigned NOT NULL COMMENT '最高贷款额度',
  `ld_id` int(11) unsigned NOT NULL COMMENT '最长贷款期限',
  `lr_id` int(11) unsigned NOT NULL COMMENT '贷款利率',
  `rw_ids` varchar(100) NOT NULL COMMENT '还款方式',
  `gs_ids` varchar(100) NOT NULL COMMENT '担保方式',
  `ps_id` int(11) NOT NULL COMMENT '产品状态',
  `fp_overlay_pc_ids` varchar(100) NOT NULL COMMENT '覆盖区域',
  `fp_ishot` varchar(1) NOT NULL DEFAULT 'Y' COMMENT '是否热门(Y/N)',
  `fp_need_info` varchar(100) DEFAULT NULL COMMENT '申请所需资料',
  `fp_details` text COMMENT '产品详情',
  `fp_prd_img_url` varchar(100) DEFAULT NULL COMMENT '产品图片地址',
  `fp_remark` varchar(100) DEFAULT NULL COMMENT '备注',
  `creatorid` int(11) unsigned NOT NULL COMMENT '创建人',
  `createtime` datetime NOT NULL COMMENT '创建时间',
  `updatorid` int(11) unsigned NOT NULL COMMENT '最后修改人id',
  `updatetime` datetime NOT NULL COMMENT '最后修改时间',
  `enabled` varchar(1) NOT NULL DEFAULT 'Y' COMMENT '逻辑删除状态',
  PRIMARY KEY (`fp_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 数据导出被取消选择。


-- 导出  表 tbp1.tb_guarantee_style 结构
DROP TABLE IF EXISTS `tb_guarantee_style`;
CREATE TABLE IF NOT EXISTS `tb_guarantee_style` (
  `gs_id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '担保方式id',
  `gs_name` varchar(100) NOT NULL COMMENT '担保方式',
  `gs_code` varchar(50) NOT NULL COMMENT '担保方式编码',
  `enabled` varchar(1) NOT NULL COMMENT '有效标志（Y:有效N:无效）',
  PRIMARY KEY (`gs_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='担保方式表';

-- 数据导出被取消选择。


-- 导出  表 tbp1.tb_html_tag 结构
DROP TABLE IF EXISTS `tb_html_tag`;
CREATE TABLE IF NOT EXISTS `tb_html_tag` (
  `ht_id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '填写方式id',
  `ht_name` varchar(100) NOT NULL COMMENT '填写方式',
  `ht_type` varchar(50) NOT NULL COMMENT '填写方式编码',
  `enabled` varchar(1) NOT NULL COMMENT '有效标志（Y:有效N:无效）',
  PRIMARY KEY (`ht_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='填写方式表';

-- 数据导出被取消选择。


-- 导出  表 tbp1.tb_links 结构
DROP TABLE IF EXISTS `tb_links`;
CREATE TABLE IF NOT EXISTS `tb_links` (
  `l_id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `region_id` int(11) unsigned NOT NULL COMMENT '区域ID',
  `l_name` varchar(100) NOT NULL COMMENT '合作单位名称',
  `l_url` varchar(100) DEFAULT NULL COMMENT '合作单位门户URL',
  `l_img_url` varchar(100) NOT NULL COMMENT '图片URL',
  `l_display_order` double NOT NULL COMMENT '显示先后顺序',
  `enabled` varchar(1) NOT NULL DEFAULT 'Y' COMMENT '有效标识(Y/N)',
  `creatorid` int(11) NOT NULL COMMENT '创建人',
  `createtime` datetime NOT NULL COMMENT '创建日期',
  `updatorid` int(11) NOT NULL COMMENT '更改人',
  `updatetime` datetime NOT NULL COMMENT '修改日期',
  PRIMARY KEY (`l_id`),
  UNIQUE KEY `l_id` (`l_id`),
  KEY `FK_tb_links_tb_regions` (`region_id`),
  CONSTRAINT `FK_tb_links_tb_regions` FOREIGN KEY (`region_id`) REFERENCES `tb_regions` (`region_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='友情链接';

-- 数据导出被取消选择。


-- 导出  表 tbp1.tb_loan_agreement 结构
DROP TABLE IF EXISTS `tb_loan_agreement`;
CREATE TABLE IF NOT EXISTS `tb_loan_agreement` (
  `la_id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '协议ID',
  `region_id` varchar(100) NOT NULL COMMENT '城市ID',
  `org_id` int(11) unsigned NOT NULL COMMENT '银行ID',
  `la_name` varchar(100) NOT NULL COMMENT '协议名称',
  `la_content` text COMMENT '协议内容',
  `enabled` varchar(1) NOT NULL DEFAULT 'Y' COMMENT '有效标志(Y/N)',
  `creatorid` int(11) unsigned NOT NULL COMMENT '创建人',
  `createtime` datetime NOT NULL COMMENT '创建日期',
  `updatorid` int(11) unsigned NOT NULL COMMENT '修改人',
  `updatetime` datetime NOT NULL COMMENT '修改日期',
  PRIMARY KEY (`la_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 数据导出被取消选择。


-- 导出  表 tbp1.tb_loan_amount 结构
DROP TABLE IF EXISTS `tb_loan_amount`;
CREATE TABLE IF NOT EXISTS `tb_loan_amount` (
  `la_id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `la_name` varchar(50) NOT NULL COMMENT '贷款金额项目名称',
  `la_code` varchar(50) NOT NULL COMMENT '贷款金额编码',
  `enabled` varchar(1) NOT NULL DEFAULT 'Y' COMMENT '有效标识(Y/N)',
  PRIMARY KEY (`la_id`),
  UNIQUE KEY `la_id` (`la_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='贷款金额';

-- 数据导出被取消选择。


-- 导出  表 tbp1.tb_loan_apply 结构
DROP TABLE IF EXISTS `tb_loan_apply`;
CREATE TABLE IF NOT EXISTS `tb_loan_apply` (
  `la_id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '申请id',
  `region_id` int(11) unsigned NOT NULL COMMENT '区域id',
  `fp_id` int(11) unsigned NOT NULL COMMENT '产品id',
  `rw_id` int(11) unsigned NOT NULL COMMENT '还款方式',
  `nsrsbh` varchar(50) NOT NULL COMMENT '纳税人识别号',
  `la_serial_number` varchar(100) NOT NULL,
  `la_amount` int(11) unsigned NOT NULL COMMENT '申请额度(万)',
  `la_repay_loan_deadline` int(11) unsigned NOT NULL COMMENT '申请还款期限(月)',
  `la_apply_time` datetime NOT NULL COMMENT '申请时间',
  `la_first_time` datetime DEFAULT NULL COMMENT '初审时间',
  `la_end_time` datetime DEFAULT NULL COMMENT '终审时间',
  `la_status` int(11) unsigned NOT NULL COMMENT '审批状态',
  `la_remark` varchar(100) NOT NULL COMMENT '备注',
  `creatorid` int(11) unsigned NOT NULL COMMENT '创建人id',
  `createtime` date NOT NULL COMMENT '创建时间',
  `updatorid` int(11) unsigned NOT NULL COMMENT '最后修改人id',
  `updatetime` date NOT NULL COMMENT '最后修改时间',
  PRIMARY KEY (`la_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 数据导出被取消选择。


-- 导出  表 tbp1.tb_loan_apply_attach 结构
DROP TABLE IF EXISTS `tb_loan_apply_attach`;
CREATE TABLE IF NOT EXISTS `tb_loan_apply_attach` (
  `lf_id` int(11) unsigned NOT NULL COMMENT '申请主表id',
  `pdi_id` int(11) unsigned NOT NULL COMMENT '产品数据项Id',
  `laa_id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '贷款申请附表id',
  `region_id` int(11) unsigned NOT NULL COMMENT '区域id',
  `org_id` int(11) unsigned NOT NULL COMMENT '组织ID',
  `pdi_values` varchar(1000) NOT NULL COMMENT '产品数据项值',
  `laa_remark` varchar(100) DEFAULT NULL COMMENT '备注',
  `creatorid` int(11) unsigned NOT NULL COMMENT '创建人id',
  `createtime` date NOT NULL COMMENT '创建时间',
  `updatorid` int(11) unsigned NOT NULL COMMENT '最后修改人id',
  `updatetime` date NOT NULL COMMENT '最后修改时间',
  `enabled` varchar(1) NOT NULL DEFAULT 'Y' COMMENT '是否可见',
  PRIMARY KEY (`laa_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 数据导出被取消选择。


-- 导出  表 tbp1.tb_loan_apply_final 结构
DROP TABLE IF EXISTS `tb_loan_apply_final`;
CREATE TABLE IF NOT EXISTS `tb_loan_apply_final` (
  `laf_id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '结果id',
  `region_id` int(11) unsigned NOT NULL COMMENT '区域id',
  `fp_id` int(11) unsigned NOT NULL COMMENT '产品id',
  `la_id` int(11) unsigned NOT NULL COMMENT '申请ID',
  `lac_id` int(11) unsigned NOT NULL COMMENT '结果(最终流程结果)代码(1:同意 2:不同意 3:退单)',
  `laf_opinion` text COMMENT '审批意见',
  `laf_remark` varchar(100) DEFAULT NULL COMMENT '备注',
  `creatorid` int(11) unsigned NOT NULL COMMENT '创建人id',
  `createtime` date NOT NULL COMMENT '创建时间',
  `updateid` int(11) unsigned NOT NULL COMMENT '最后修改人id',
  `updatetime` date NOT NULL COMMENT '最后修改时间',
  PRIMARY KEY (`laf_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 数据导出被取消选择。


-- 导出  表 tbp1.tb_loan_approve_code 结构
DROP TABLE IF EXISTS `tb_loan_approve_code`;
CREATE TABLE IF NOT EXISTS `tb_loan_approve_code` (
  `lac_id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '代码id',
  `lac_name` varchar(100) NOT NULL COMMENT '代码名称',
  `lac_code` varchar(50) NOT NULL COMMENT '代码code',
  `enabled` varchar(1) NOT NULL COMMENT '有效标志(Y/N)',
  `lac_remark` varchar(100) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`lac_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 数据导出被取消选择。


-- 导出  表 tbp1.tb_loan_approve_rec 结构
DROP TABLE IF EXISTS `tb_loan_approve_rec`;
CREATE TABLE IF NOT EXISTS `tb_loan_approve_rec` (
  `lar_id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '记录id',
  `region_id` int(11) unsigned NOT NULL COMMENT '区域id',
  `fp_id` int(11) unsigned NOT NULL COMMENT '产品id',
  `la_id` int(11) unsigned NOT NULL COMMENT '申请id',
  `las_id` int(11) unsigned NOT NULL COMMENT '审批状态代码',
  `lac_id` int(11) unsigned NOT NULL COMMENT '审批意见代码',
  `lar_credit_quota` int(11) unsigned NOT NULL COMMENT '授信额度',
  `lar_loan_deadline` int(11) unsigned NOT NULL COMMENT '贷款期限',
  `lar_begin` date NOT NULL COMMENT '贷款期限起',
  `lar_end` date NOT NULL COMMENT '贷款期限止',
  `lar_rate` int(11) unsigned NOT NULL COMMENT '贷款利率',
  `lar_isoverlay_area` int(11) unsigned NOT NULL COMMENT '是否在产品覆盖区域',
  `rw_id` int(11) unsigned NOT NULL COMMENT '还款方式',
  `lar_bank_name` varchar(100) NOT NULL COMMENT '借款方开户银行',
  `lar_bank_account` varchar(100) NOT NULL COMMENT '借款方银行账号',
  `lar_contract` varchar(100) NOT NULL COMMENT '贷款合同号',
  `lar_opinion` text NOT NULL COMMENT '审批意见',
  `lar_remark` varchar(100) DEFAULT NULL COMMENT '备注',
  `creatorid` int(11) unsigned NOT NULL COMMENT '创建人id',
  `createtime` date NOT NULL COMMENT '创建时间',
  `updatorid` int(11) unsigned NOT NULL COMMENT '最后修改人id',
  `updatetime` date NOT NULL COMMENT '最后修改时间',
  PRIMARY KEY (`lar_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 数据导出被取消选择。


-- 导出  表 tbp1.tb_loan_approve_status 结构
DROP TABLE IF EXISTS `tb_loan_approve_status`;
CREATE TABLE IF NOT EXISTS `tb_loan_approve_status` (
  `las_id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '代码id',
  `las_name` varchar(100) NOT NULL COMMENT '代码名称',
  `las_code` varchar(50) NOT NULL COMMENT '代码',
  `enabled` varchar(1) NOT NULL COMMENT '有效标志(Y/N)',
  `las_remark` varchar(100) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`las_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 数据导出被取消选择。


-- 导出  表 tbp1.tb_loan_deadline 结构
DROP TABLE IF EXISTS `tb_loan_deadline`;
CREATE TABLE IF NOT EXISTS `tb_loan_deadline` (
  `ld_id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `ld_name` varchar(50) NOT NULL COMMENT '贷款期限项目名称',
  `ld_code` varchar(50) NOT NULL COMMENT '贷款期限类型编码',
  `enabled` varchar(1) NOT NULL DEFAULT 'Y' COMMENT '有效标志(Y/N)',
  PRIMARY KEY (`ld_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='贷款期限';

-- 数据导出被取消选择。


-- 导出  表 tbp1.tb_loan_forms 结构
DROP TABLE IF EXISTS `tb_loan_forms`;
CREATE TABLE IF NOT EXISTS `tb_loan_forms` (
  `lf_id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '申请单id',
  `org_id` int(11) unsigned NOT NULL COMMENT '银行ID',
  `region_id` int(11) unsigned NOT NULL COMMENT '区域ID',
  `la_id` int(11) unsigned DEFAULT NULL COMMENT '协议ID',
  `fp_id` int(11) unsigned NOT NULL COMMENT '产品ID',
  `lf_remark` varchar(100) DEFAULT NULL COMMENT '备注',
  `creatorid` int(11) unsigned NOT NULL COMMENT '创建人',
  `createtime` datetime NOT NULL COMMENT '创建日期',
  `updatorid` int(11) unsigned NOT NULL COMMENT '修改人',
  `updatetime` datetime NOT NULL COMMENT '修改日期',
  PRIMARY KEY (`lf_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 数据导出被取消选择。


-- 导出  表 tbp1.tb_loan_rates 结构
DROP TABLE IF EXISTS `tb_loan_rates`;
CREATE TABLE IF NOT EXISTS `tb_loan_rates` (
  `lr_id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `lr_name` varchar(50) NOT NULL COMMENT '贷款利率项目名称',
  `lr_code` varchar(50) NOT NULL COMMENT '贷款利率类型编码',
  `enabled` varchar(1) NOT NULL DEFAULT 'Y' COMMENT '有效标识(Y/N)',
  PRIMARY KEY (`lr_id`),
  UNIQUE KEY `lr_id` (`lr_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='贷款利率';

-- 数据导出被取消选择。


-- 导出  表 tbp1.tb_loan_status 结构
DROP TABLE IF EXISTS `tb_loan_status`;
CREATE TABLE IF NOT EXISTS `tb_loan_status` (
  `ls_id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '贷款状态表id',
  `ls_name` varchar(100) NOT NULL COMMENT '贷款状态',
  `ls_code` varchar(50) NOT NULL COMMENT '贷款状态编码',
  `enabled` varchar(1) NOT NULL COMMENT '有效标志（Y:有效N:无效）',
  PRIMARY KEY (`ls_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='贷款状态表';

-- 数据导出被取消选择。


-- 导出  表 tbp1.tb_login_info 结构
DROP TABLE IF EXISTS `tb_login_info`;
CREATE TABLE IF NOT EXISTS `tb_login_info` (
  `l_id` int(11) NOT NULL AUTO_INCREMENT,
  `u_id` int(11) DEFAULT NULL,
  `u_account_name` varchar(255) DEFAULT NULL,
  `l_ip` varchar(255) DEFAULT NULL,
  `l_login_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`l_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 数据导出被取消选择。


-- 导出  表 tbp1.tb_log_info 结构
DROP TABLE IF EXISTS `tb_log_info`;
CREATE TABLE IF NOT EXISTS `tb_log_info` (
  `l_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `u_id` bigint(20) NOT NULL,
  `l_account_name` varchar(100) DEFAULT NULL,
  `l_operation` varchar(255) DEFAULT NULL COMMENT '用户所做的操作',
  `l_content` varchar(1000) DEFAULT NULL COMMENT '日志内容',
  `l_create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建日期',
  PRIMARY KEY (`l_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 数据导出被取消选择。


-- 导出  表 tbp1.tb_message 结构
DROP TABLE IF EXISTS `tb_message`;
CREATE TABLE IF NOT EXISTS `tb_message` (
  `m_id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '消息ID',
  `m_title` varchar(100) NOT NULL COMMENT '标题',
  `m_subtitle` varchar(100) DEFAULT NULL COMMENT '副标题',
  `m_content` text COMMENT '消息内容',
  `userId` int(11) unsigned NOT NULL COMMENT '用户ID(接收人)',
  `type` int(11) unsigned NOT NULL COMMENT '类型ID',
  `enabled` varchar(1) NOT NULL DEFAULT 'Y' COMMENT '有效标识(Y/N)',
  `creatorid` int(11) unsigned NOT NULL COMMENT '创建人',
  `createtime` datetime NOT NULL COMMENT '创建时间',
  `updatorid` int(11) unsigned NOT NULL COMMENT '最后修改人id',
  `updatetime` datetime NOT NULL COMMENT '最后修改时间',
  `unread` varchar(1) NOT NULL DEFAULT 'Y' COMMENT '未读状态(Y/N)',
  PRIMARY KEY (`m_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 数据导出被取消选择。


-- 导出  表 tbp1.tb_message_type 结构
DROP TABLE IF EXISTS `tb_message_type`;
CREATE TABLE IF NOT EXISTS `tb_message_type` (
  `mt_id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '消息类型ID',
  `mt_name` varchar(100) DEFAULT NULL COMMENT '类型名称',
  `enabled` varchar(1) NOT NULL DEFAULT 'Y' COMMENT '有效标识(Y/N)',
  `creatorid` int(11) unsigned NOT NULL COMMENT '创建人ID',
  `createtime` datetime NOT NULL COMMENT '创建时间',
  `updatorid` int(11) unsigned NOT NULL COMMENT '最后修改人id',
  `updatetime` datetime NOT NULL COMMENT '最后修改时间',
  PRIMARY KEY (`mt_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 数据导出被取消选择。


-- 导出  表 tbp1.tb_news 结构
DROP TABLE IF EXISTS `tb_news`;
CREATE TABLE IF NOT EXISTS `tb_news` (
  `n_id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `region_id` int(11) unsigned NOT NULL COMMENT '区域ID',
  `org_id` int(11) unsigned NOT NULL COMMENT '组织ID',
  `nt_id` int(11) unsigned NOT NULL COMMENT '门户新闻信息类型表ID',
  `n_title` varchar(50) NOT NULL COMMENT '门户信息名称',
  `n_published_date` datetime NOT NULL COMMENT '门户信息日期',
  `n_published_dept` varchar(50) NOT NULL COMMENT '门户信息源类型',
  `n_content` text NOT NULL COMMENT '门户信息内容',
  `enabled` varchar(1) NOT NULL DEFAULT 'Y' COMMENT '有效标志(Y/N)',
  `creatorid` int(11) unsigned NOT NULL COMMENT '创建人',
  `createtime` datetime NOT NULL COMMENT '创建日期',
  `updatorid` int(11) unsigned NOT NULL COMMENT '修改人',
  `updatetime` datetime NOT NULL COMMENT '修改日期',
  PRIMARY KEY (`n_id`),
  UNIQUE KEY `n_id` (`n_id`),
  UNIQUE KEY `n_title` (`n_title`),
  KEY `nt_id` (`nt_id`),
  KEY `FK_tb_news_tb_regions` (`region_id`),
  KEY `FK_tb_news_tb_organizations` (`org_id`),
  CONSTRAINT `FK_nt_id` FOREIGN KEY (`nt_id`) REFERENCES `tb_news_type` (`nt_id`),
  CONSTRAINT `FK_tb_news_tb_organizations` FOREIGN KEY (`org_id`) REFERENCES `tb_organizations` (`org_id`),
  CONSTRAINT `FK_tb_news_tb_regions` FOREIGN KEY (`region_id`) REFERENCES `tb_regions` (`region_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='门户新闻信息';

-- 数据导出被取消选择。


-- 导出  表 tbp1.tb_news_type 结构
DROP TABLE IF EXISTS `tb_news_type`;
CREATE TABLE IF NOT EXISTS `tb_news_type` (
  `nt_id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `nt_name` varchar(50) NOT NULL COMMENT '新闻类型名称',
  `nt_code` varchar(50) NOT NULL COMMENT '新闻类型编码',
  `enabled` varchar(1) NOT NULL DEFAULT 'Y' COMMENT '有效标志(Y/N)',
  PRIMARY KEY (`nt_id`),
  UNIQUE KEY `nt_id` (`nt_id`),
  UNIQUE KEY `nt_name` (`nt_name`),
  UNIQUE KEY `列 3` (`nt_code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='门户新闻信息类型';

-- 数据导出被取消选择。


-- 导出  表 tbp1.tb_organizations 结构
DROP TABLE IF EXISTS `tb_organizations`;
CREATE TABLE IF NOT EXISTS `tb_organizations` (
  `org_id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '组织id',
  `region_id` int(11) unsigned NOT NULL COMMENT '区域id',
  `org_name` varchar(100) NOT NULL COMMENT '组织名称',
  `ot_id` int(11) unsigned NOT NULL COMMENT '组织类型',
  `org_code` varchar(50) NOT NULL COMMENT '组织编码',
  `remark` varchar(200) DEFAULT NULL COMMENT '备注',
  `enabled` varchar(1) NOT NULL COMMENT '有效标志（Y:有效，N:无效）',
  `creatorid` int(11) unsigned NOT NULL COMMENT '创建人id',
  `createtime` datetime NOT NULL COMMENT '创建时间 ',
  `updatorid` int(11) unsigned NOT NULL COMMENT '更新人id',
  `updatetime` datetime NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`org_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 数据导出被取消选择。


-- 导出  表 tbp1.tb_organizations_type 结构
DROP TABLE IF EXISTS `tb_organizations_type`;
CREATE TABLE IF NOT EXISTS `tb_organizations_type` (
  `ot_id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '组织类型id',
  `ot_type_code` varchar(50) NOT NULL COMMENT '组织类型',
  `ot_name` varchar(100) NOT NULL COMMENT '组织名称',
  `enabled` varchar(1) NOT NULL COMMENT '是否有效标识',
  `ot_remark` varchar(100) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`ot_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 数据导出被取消选择。


-- 导出  表 tbp1.tb_parameters 结构
DROP TABLE IF EXISTS `tb_parameters`;
CREATE TABLE IF NOT EXISTS `tb_parameters` (
  `p_id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `p_code` varchar(50) NOT NULL COMMENT '参数代码',
  `p_name` varchar(50) NOT NULL COMMENT '参数名称',
  `p_value` varchar(100) NOT NULL COMMENT '参数值',
  `enabled` varchar(1) NOT NULL DEFAULT 'Y' COMMENT '有效标志(Y/N)',
  `creatorid` int(11) unsigned NOT NULL COMMENT '创建人',
  `createtime` datetime NOT NULL COMMENT '创建日期',
  `updatorid` int(11) unsigned NOT NULL COMMENT '修改人',
  `updatetime` datetime NOT NULL COMMENT '修改日期',
  PRIMARY KEY (`p_id`),
  UNIQUE KEY `p_id` (`p_id`),
  UNIQUE KEY `p_code` (`p_code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='门户常用参数';

-- 数据导出被取消选择。


-- 导出  表 tbp1.tb_portal_faq 结构
DROP TABLE IF EXISTS `tb_portal_faq`;
CREATE TABLE IF NOT EXISTS `tb_portal_faq` (
  `pf_id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `region_id` int(11) unsigned NOT NULL COMMENT '城市ID',
  `pf_name` varchar(100) NOT NULL COMMENT '常见问题名称',
  `pf_content` text NOT NULL COMMENT '常见问题内容',
  `enabled` varchar(1) NOT NULL DEFAULT 'Y' COMMENT '有效标志(Y/N)',
  `creatorid` int(11) unsigned NOT NULL COMMENT '创建人',
  `createtime` datetime NOT NULL COMMENT '创建日期',
  `updatorid` int(11) unsigned NOT NULL COMMENT '修改人',
  `updatetime` datetime NOT NULL COMMENT '修改日期',
  PRIMARY KEY (`pf_id`),
  UNIQUE KEY `pf_id` (`pf_id`),
  UNIQUE KEY `pf_name` (`pf_name`),
  KEY `FK_tb_portal_faq_tb_regions` (`region_id`),
  CONSTRAINT `FK_tb_portal_faq_tb_regions` FOREIGN KEY (`region_id`) REFERENCES `tb_regions` (`region_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='系统常见问题';

-- 数据导出被取消选择。


-- 导出  表 tbp1.tb_portal_imgs 结构
DROP TABLE IF EXISTS `tb_portal_imgs`;
CREATE TABLE IF NOT EXISTS `tb_portal_imgs` (
  `pi_id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `region_id` int(11) unsigned NOT NULL COMMENT '区域ID',
  `pit_id` int(11) unsigned NOT NULL COMMENT '门户图片类型ID',
  `pi_img_url` varchar(100) NOT NULL COMMENT '图片的URL地址',
  `pi_display_order` double DEFAULT NULL COMMENT '图片的显示顺序',
  `pi_target_url` varchar(100) DEFAULT NULL COMMENT '图片的链接地址',
  `enabled` varchar(1) NOT NULL DEFAULT 'Y' COMMENT '有效标志(Y/N)',
  `creatorid` int(11) unsigned NOT NULL COMMENT '创建人',
  `createtime` datetime NOT NULL COMMENT '创建日期',
  `updatorid` int(11) unsigned NOT NULL COMMENT '修改人',
  `updatetime` datetime NOT NULL COMMENT '修改日期',
  PRIMARY KEY (`pi_id`),
  UNIQUE KEY `pi_id` (`pi_id`),
  KEY `pit_id` (`pit_id`),
  KEY `FK_tb_portal_imgs_tb_regions` (`region_id`),
  CONSTRAINT `FK_tb_portal_imgs_tb_portal_img_type` FOREIGN KEY (`pit_id`) REFERENCES `tb_portal_img_type` (`pit_id`),
  CONSTRAINT `FK_tb_portal_imgs_tb_regions` FOREIGN KEY (`region_id`) REFERENCES `tb_regions` (`region_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='门户图片展示';

-- 数据导出被取消选择。


-- 导出  表 tbp1.tb_portal_img_type 结构
DROP TABLE IF EXISTS `tb_portal_img_type`;
CREATE TABLE IF NOT EXISTS `tb_portal_img_type` (
  `pit_id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `pit_name` varchar(100) NOT NULL COMMENT '图片类型名称',
  `pit_code` varchar(50) NOT NULL COMMENT '图片类型编码',
  `enabled` varchar(1) NOT NULL DEFAULT 'Y' COMMENT '有效标志(Y/N)',
  PRIMARY KEY (`pit_id`),
  UNIQUE KEY `pit_id` (`pit_id`),
  UNIQUE KEY `pit_code` (`pit_code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='门户图片类型';

-- 数据导出被取消选择。


-- 导出  表 tbp1.tb_product_data_items 结构
DROP TABLE IF EXISTS `tb_product_data_items`;
CREATE TABLE IF NOT EXISTS `tb_product_data_items` (
  `pdi_id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT ' 产品数据项Id',
  `pdi_name` varchar(100) NOT NULL COMMENT '数据项名称名称',
  `pdi_code` varchar(100) NOT NULL COMMENT '数据项值名称代码',
  `ht_id` int(11) unsigned NOT NULL COMMENT '填写方式外键关联代码表tb_html_tag',
  `enabled` varchar(1) DEFAULT NULL COMMENT '有效标志，有效为Y',
  `region_id` int(11) unsigned DEFAULT NULL COMMENT '区域ID ',
  `org_id` int(11) unsigned DEFAULT NULL COMMENT '组织ID',
  `creatorid` int(11) unsigned NOT NULL COMMENT '创建人',
  `createtime` datetime DEFAULT NULL COMMENT '创建时间',
  `updatorid` int(11) unsigned NOT NULL COMMENT '最后修改人',
  `updatetime` datetime DEFAULT NULL COMMENT '最后修改时间',
  PRIMARY KEY (`pdi_id`),
  KEY `FK_tb_product_data_items_tb_html_tag` (`ht_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 数据导出被取消选择。


-- 导出  表 tbp1.tb_product_data_item_values 结构
DROP TABLE IF EXISTS `tb_product_data_item_values`;
CREATE TABLE IF NOT EXISTS `tb_product_data_item_values` (
  `pdiv_id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `pdi_id` int(11) unsigned NOT NULL COMMENT '产品数据项Id',
  `pdiv_name` varchar(20) DEFAULT NULL COMMENT '产品选项值名',
  `pdiv_seq` int(3) DEFAULT NULL COMMENT '排序',
  `creatorid` int(11) unsigned NOT NULL COMMENT '创建人',
  `createtime` date DEFAULT NULL COMMENT '创建时间',
  `updatorid` int(11) unsigned NOT NULL COMMENT '最后修改人',
  `updatetime` date DEFAULT NULL COMMENT '最后修改时间',
  PRIMARY KEY (`pdiv_id`),
  KEY `FK_tb_product_data_item_values_tb_product_data_items` (`pdi_id`),
  CONSTRAINT `FK_tb_product_data_item_values_tb_product_data_items` FOREIGN KEY (`pdi_id`) REFERENCES `tb_product_data_items` (`pdi_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 数据导出被取消选择。


-- 导出  表 tbp1.tb_product_faq 结构
DROP TABLE IF EXISTS `tb_product_faq`;
CREATE TABLE IF NOT EXISTS `tb_product_faq` (
  `pf_id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '常见问题Id',
  `pf_content` text NOT NULL COMMENT '产品常见问题	',
  `pf_remark` varchar(100) DEFAULT NULL COMMENT '备注	',
  `creatorid` int(11) NOT NULL COMMENT '创建人',
  `createtime` datetime NOT NULL COMMENT '创建时间',
  `updatorid` int(11) NOT NULL COMMENT '最后修改人',
  `fp_id` int(11) unsigned NOT NULL COMMENT '银行所属产品',
  `updatetime` datetime NOT NULL COMMENT '最后修改时间',
  PRIMARY KEY (`pf_id`),
  UNIQUE KEY `fp_id` (`fp_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 数据导出被取消选择。


-- 导出  表 tbp1.tb_product_status 结构
DROP TABLE IF EXISTS `tb_product_status`;
CREATE TABLE IF NOT EXISTS `tb_product_status` (
  `ps_id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `ps_name` varchar(100) NOT NULL COMMENT '产品状态名',
  `ps_code` varchar(50) NOT NULL COMMENT '产品code',
  `enabled` varchar(1) NOT NULL DEFAULT 'Y' COMMENT '有效标志(Y/N)',
  `creatorid` int(11) unsigned NOT NULL COMMENT '创建人',
  `createtime` datetime NOT NULL COMMENT '创建日期',
  `updatorid` int(11) unsigned NOT NULL COMMENT '修改人',
  `updatetime` datetime NOT NULL COMMENT '修改日期',
  PRIMARY KEY (`ps_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 数据导出被取消选择。


-- 导出  表 tbp1.tb_province_cities 结构
DROP TABLE IF EXISTS `tb_province_cities`;
CREATE TABLE IF NOT EXISTS `tb_province_cities` (
  `pc_id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '省市区代码表id',
  `pc_pid` int(11) unsigned NOT NULL COMMENT '父节点id（如果是省的话全部用0表示）',
  `pc_code` varchar(50) NOT NULL COMMENT 'code',
  `pc_name` varchar(100) NOT NULL COMMENT '省市区名称',
  PRIMARY KEY (`pc_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 数据导出被取消选择。


-- 导出  表 tbp1.tb_regions 结构
DROP TABLE IF EXISTS `tb_regions`;
CREATE TABLE IF NOT EXISTS `tb_regions` (
  `region_id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '开通区域id',
  `region_name` varchar(100) NOT NULL COMMENT '开通区域名称',
  `enabled` varchar(1) NOT NULL COMMENT '删除状态(Y:正常，N:删除)',
  `remark` varchar(200) DEFAULT NULL COMMENT '备注',
  `creatorid` int(11) unsigned NOT NULL COMMENT '创建人id',
  `createtime` datetime NOT NULL COMMENT '创建时间',
  `updatorid` int(11) unsigned NOT NULL COMMENT '更新人id',
  `updatetime` datetime NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`region_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 数据导出被取消选择。


-- 导出  表 tbp1.tb_repayment_way 结构
DROP TABLE IF EXISTS `tb_repayment_way`;
CREATE TABLE IF NOT EXISTS `tb_repayment_way` (
  `rw_id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '还款方式代码表id',
  `rw_name` varchar(100) NOT NULL COMMENT '还款方式',
  `rw_code` varchar(50) NOT NULL COMMENT '还款方式编码',
  `enabled` varchar(1) NOT NULL COMMENT '有效标志（Y:有效N:无效）',
  PRIMARY KEY (`rw_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 数据导出被取消选择。


-- 导出  表 tbp1.tb_resource 结构
DROP TABLE IF EXISTS `tb_resource`;
CREATE TABLE IF NOT EXISTS `tb_resource` (
  `s_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '资源id',
  `s_parent_id` int(11) DEFAULT NULL COMMENT '资源父id',
  `s_name` varchar(100) NOT NULL COMMENT '资源名称',
  `s_source_key` varchar(100) NOT NULL COMMENT '资源唯一标识',
  `s_type` int(11) NOT NULL COMMENT '资源类型,0:目录;1:菜单;2:按钮',
  `s_source_url` varchar(500) DEFAULT NULL COMMENT '资源url',
  `s_level` int(11) DEFAULT NULL COMMENT '层级',
  `s_icon` varchar(100) DEFAULT '' COMMENT '图标',
  `s_is_hide` int(11) DEFAULT '0' COMMENT '是否隐藏',
  `s_description` varchar(100) DEFAULT NULL COMMENT '描述',
  `s_create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `s_update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`s_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='资源表';

-- 数据导出被取消选择。


-- 导出  表 tbp1.tb_resources_role 结构
DROP TABLE IF EXISTS `tb_resources_role`;
CREATE TABLE IF NOT EXISTS `tb_resources_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `s_id` int(11) DEFAULT NULL COMMENT '资源id',
  `r_id` int(11) DEFAULT NULL COMMENT '角色id',
  `t_create_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`),
  KEY `FK_r_resource_role` (`s_id`),
  KEY `FK_r_role_resource` (`r_id`),
  CONSTRAINT `FK_r_resource_role` FOREIGN KEY (`s_id`) REFERENCES `tb_resource` (`s_id`),
  CONSTRAINT `FK_r_role_resource` FOREIGN KEY (`r_id`) REFERENCES `tb_role` (`r_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色权限映射表';

-- 数据导出被取消选择。


-- 导出  表 tbp1.tb_role 结构
DROP TABLE IF EXISTS `tb_role`;
CREATE TABLE IF NOT EXISTS `tb_role` (
  `r_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '角色id',
  `org_id` int(11) unsigned NOT NULL COMMENT '组织id',
  `region_id` int(11) unsigned NOT NULL COMMENT '区域id',
  `r_name` varchar(50) NOT NULL COMMENT '角色名称',
  `r_key` varchar(50) NOT NULL COMMENT '角色key',
  `r_status` VARCHAR(1) NOT NULL COMMENT '角色状态,Y：正常；N：删除',
  `r_description` varchar(100) DEFAULT NULL COMMENT '角色描述',
  `u_id` int(11) DEFAULT '1' COMMENT '创建人id',
  `r_create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `r_update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`r_id`),
  KEY `FK_b_role_user` (`u_id`),
  CONSTRAINT `FK_b_role_user` FOREIGN KEY (`u_id`) REFERENCES `tb_user` (`u_id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色表';

-- 数据导出被取消选择。


-- 导出  表 tbp1.tb_role_user 结构
DROP TABLE IF EXISTS `tb_role_user`;
CREATE TABLE IF NOT EXISTS `tb_role_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `r_id` int(11) DEFAULT NULL COMMENT '角色id',
  `u_id` int(11) DEFAULT NULL COMMENT '用户id',
  `t_create_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`),
  KEY `FK_r_role_user` (`r_id`),
  KEY `FK_r_user_role` (`u_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户角色映射表';

-- 数据导出被取消选择。


-- 导出  表 tbp1.tb_static_code 结构
DROP TABLE IF EXISTS `tb_static_code`;
CREATE TABLE IF NOT EXISTS `tb_static_code` (
  `sc_id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `sc_name` varchar(50) NOT NULL COMMENT '信息名称',
  `sc_code` varchar(50) NOT NULL COMMENT '门户静态信息编码',
  `enabled` varchar(1) NOT NULL DEFAULT 'Y' COMMENT '有效标志(Y/N)',
  PRIMARY KEY (`sc_id`),
  UNIQUE KEY `sc_id` (`sc_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='门户静态类别代码';

-- 数据导出被取消选择。


-- 导出  表 tbp1.tb_static_info 结构
DROP TABLE IF EXISTS `tb_static_info`;
CREATE TABLE IF NOT EXISTS `tb_static_info` (
  `si_id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `region_id` int(11) unsigned NOT NULL COMMENT '区域ID',
  `sc_id` int(11) unsigned NOT NULL COMMENT '门户静态类别代码表ID',
  `si_content` text NOT NULL COMMENT '信息内容',
  `enabled` varchar(1) NOT NULL DEFAULT 'Y' COMMENT '有效标志(Y/N)',
  `creatorid` int(11) unsigned NOT NULL COMMENT '创建人',
  `createtime` datetime NOT NULL COMMENT '创建日期',
  `updatorid` int(11) unsigned NOT NULL COMMENT '更改人',
  `updatetime` datetime NOT NULL COMMENT '更改日期',
  PRIMARY KEY (`si_id`),
  UNIQUE KEY `si_id` (`si_id`),
  KEY `sc_id` (`sc_id`),
  KEY `FK_tb_static_info_tb_regions` (`region_id`),
  CONSTRAINT `FK_sc_id` FOREIGN KEY (`sc_id`) REFERENCES `tb_static_code` (`sc_id`),
  CONSTRAINT `FK_tb_static_info_tb_regions` FOREIGN KEY (`region_id`) REFERENCES `tb_regions` (`region_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='门户静态信息';

-- 数据导出被取消选择。


-- 导出  表 tbp1.tb_user 结构
DROP TABLE IF EXISTS `tb_user`;
CREATE TABLE IF NOT EXISTS `tb_user` (
  `u_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '用户id',
  `region_id` int(11) unsigned NOT NULL COMMENT '区域id',
  `org_id` int(11) unsigned NOT NULL COMMENT '组织id，用于组织机构数据隔离',
  `u_name` varchar(100) NOT NULL COMMENT '真实姓名',
  `u_account_name` varchar(100) NOT NULL COMMENT '账户名称',
  `u_password` varchar(100) NOT NULL COMMENT '用户密码',
  `u_delete_status` int(11) DEFAULT '0' COMMENT '逻辑删除状态',
  `u_locked` int(11) DEFAULT '0' COMMENT '是否锁定',
  `u_description` varchar(200) DEFAULT NULL COMMENT '用户描述',
  `u_credentials_salt` varchar(500) NOT NULL COMMENT '加密盐',
  `u_creator_id` int(11) NOT NULL COMMENT '创建者ID',
  `u_creator_name` varchar(100) NOT NULL COMMENT '创建者',
  `u_create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `u_update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`u_id`),
  UNIQUE KEY `u_account_name_unique` (`u_account_name`) USING BTREE,
  KEY `FK_u_creator_user` (`u_creator_id`),
  CONSTRAINT `FK_u_creator_user` FOREIGN KEY (`u_creator_id`) REFERENCES `tb_user` (`u_id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户账户表';

-- 数据导出被取消选择。


-- 导出  表 tbp1.tb_user_info 结构
DROP TABLE IF EXISTS `tb_user_info`;
CREATE TABLE IF NOT EXISTS `tb_user_info` (
  `u_id` int(11) NOT NULL COMMENT '用户id',
  `u_sex` int(11) DEFAULT NULL COMMENT '性别',
  `u_birthday` date DEFAULT NULL COMMENT '出生日期',
  `u_telephone` varchar(20) DEFAULT NULL COMMENT '电话',
  `u_email` varchar(50) DEFAULT NULL COMMENT '邮箱',
  `u_address` varchar(200) DEFAULT NULL COMMENT '住址',
  `u_create_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`u_id`),
  CONSTRAINT `FK_r_user_info` FOREIGN KEY (`u_id`) REFERENCES `tb_user` (`u_id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户扩展信息表';

-- 数据导出被取消选择。
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
