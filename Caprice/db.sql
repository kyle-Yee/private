create database Caprice;
use Caprice;

#用户表
create table c_user(
	userId int primary key auto_increment,   #用户编号，自动生成
	userName varchar(50) not null,	#用户名
	sex char(2) default '男',	#性别
	telephone varchar(20),      	#电话
	level int default 1,            #等级，默认1
	mailbox varchar(50) not null unique,			#邮箱		 
	password varchar(50) not null,			#密码		 
	registerTime datetime,     #注册时间
	birthday datetime default '1990-1-1', #生日
	headPortrait varchar(2000),        #头像
	个性签名 varchar(100)
);

#文章表
create table c_article(
	articleId int primary key auto_increment,   #文章id，自动生成
	userId int not null references c_user(userId),  #用户ID
	accessPermission int default 1,#1:全部可见，2：仅粉丝可见，3：仅主页可见，4：仅自己可见
	releaseDate datetime, #发布日期
	lastModifiedDate datetime,#最后修改日期
	content text, #文章内容
	articleTag varchar(10)  #文章标签
);

#评论表
create table c_comment(
	commentId int primary key auto_increment,  #评论id
	reviewers int not null references c_user(userId), #评论者id
	articleId int not null references c_article(articleId), #被评论文章id
	commentContent varchar(1024) not null, #评论内容
	comment_date datetime ,  #评论时间
	byReviewers int references c_user(userId), #被评论者id
	bycommentId int #被评论id
);

#用户关注表
create table c_userAttention(
	userId int not null references c_user(userId), #关注者
	byUserId int not null references c_user(userId) #被关注者
)

#标签关注表
create table c_TagAttention(
	userId int not null references c_user(userId), #关注者
	tag varchar(10) #关注标签
)
