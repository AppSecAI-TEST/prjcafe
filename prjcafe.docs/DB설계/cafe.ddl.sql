
-- cafeboard �����ͺ��̽� ���� ��������
DROP DATABASE IF EXISTS cafeboard;

CREATE DATABASE cafeboard DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;

-- ����� �߰�
GRANT ALL ON *.* TO tester1@localhost IDENTIFIED BY '1234';

-- �����ͺ��̽� ����
USE cafeboard;

-- �������̺�

DROP TABLE IF EXISTS TB_cafe_user;
CREATE TABLE TB_cafe_user (
      userno        INT             AUTO_INCREMENT
    , userlevel     INT             DEFAULT 1
    , email         NVARCHAR(40)    NOT NULL
    , passwd        NVARCHAR(30)    NOT NULL 
    , userphone     NVARCHAR(30)    NOT NULL 
    , useraddr      VARCHAR(100)        
    , sex           NVARCHAR(30)   
                                     
    , emailselect   NVARCHAR(30)    DEFAULT 0                           
    , usernickname  NVARCHAR(30)    NOT NULL                           
    , PRIMARY KEY(userno)

)
ENGINE=InnoDB 
AUTO_INCREMENT=1 
DEFAULT CHARACTER SET utf8 
COLLATE utf8_general_ci;

Insert into TB_cafe_user (email, passwd, userphone, useraddr, sex, emailselect, usernickname) values ('dshhi89@nate.com','123123','01038192704','��㾾Ƽ','����','0','�ϰ赿�޽�');
Insert into TB_cafe_user (email, passwd, userphone, useraddr, sex, emailselect, usernickname) values ('aa@nate.com','123123','01012342704','���Ե�','����','0','�ϰ赿ȣ����');
Insert into TB_cafe_user (email, passwd, userphone, useraddr, sex, emailselect, usernickname) values ('bb@nate.com','123123','01056782704','�߸�','����','1','�ϰ赿��Ʈ��');
Insert into TB_cafe_user (email, passwd, userphone, useraddr, sex, emailselect, usernickname) values ('cc@nate.com','123123','01032142704','�츮��','����','1','�ϰ赿�����̴���');

select * from TB_cafe_user;

-- ī������ ���̺�
DROP TABLE IF EXISTS TB_cafe_cafeinfo;
CREATE TABLE TB_cafe_cafeinfo (
      cafeno        INT             AUTO_INCREMENT
    , brand         NVARCHAR(50)
    , cafename      NVARCHAR(30)    NOT NULL
    , cafeaddr      NVARCHAR(100)   NOT NULL 
    , cafephone     NVARCHAR(30)            
    , avg_grade     FLOAT           DEFAULT 0                                     
    , review_count  INT             DEFAULT 0
    , like_count    INT             DEFAULT 0           
    , PRIMARY KEY(cafeno, cafename)

)


ENGINE=InnoDB 
AUTO_INCREMENT=1 
DEFAULT CHARACTER SET utf8 
COLLATE utf8_general_ci;

Insert into TB_cafe_cafeinfo (brand, cafename, cafeaddr, cafephone) values ('�̵��','�̵��_�������','����� 9���ⱸ ��','1577-1212');
Insert into TB_cafe_cafeinfo (brand, cafename, cafeaddr, cafephone) values ('�Ҹ���','�Ҹ���_��迪��','��迪 2���ⱸ ���ͼ� ������ �ﺸ','1577-1213');
Insert into TB_cafe_cafeinfo (brand, cafename, cafeaddr, cafephone) values ('��Ÿ����','��Ÿ����_�ϰ迪��','�ϰ赿 4���ⱸ ���ͼ� �ڷ� �ﺸ','1577-1210');


select * from TB_cafe_cafeinfo;



-- ī�� ���� ���̺�
DROP TABLE IF EXISTS TB_cafe_review;
CREATE TABLE IF NOT EXISTS  TB_cafe_review (
      commentno     INT        AUTO_INCREMENT
    , userno        INT
    , cafeno        INT        
    , content       NVARCHAR(40)   NOT NULL           
    , grade         FLOAT          NOT NULL
    , regdate       DateTime                                     
    , PRIMARY KEY(commentno)
)
ENGINE=InnoDB 
AUTO_INCREMENT=1 
DEFAULT CHARACTER SET utf8 
COLLATE utf8_general_ci;


-- ī�� ���� ������ ���̺�
DROP TABLE IF EXISTS TB_cafe_management_data;

CREATE TABLE IF NOT EXISTS  TB_cafe_management_data (
      cafefileno     INT          AUTO_INCREMENT
    , filename       NVARCHAR(50)     
    , filetype       NVARCHAR(30)    
    , filesize       INT             
    , menu_id        INT                      
    , tempfilename   VARCHAR(40)                      
    , imageData      LONGBLOB                                      
    
    , PRIMARY KEY(cafefileno)
)
ENGINE=InnoDB 
AUTO_INCREMENT=1 
DEFAULT CHARACTER SET utf8 
COLLATE utf8_general_ci;


-- ī�� ���� �̹��������� ���̺�
DROP TABLE IF EXISTS TB_cafe_reviewimg_data;
CREATE TABLE IF NOT EXISTS  TB_cafe_reviewimg_data (
      reviewfileno   INT             AUTO_INCREMENT
    , filename       NVARCHAR(50)    
    , filetype       NVARCHAR(30)    
    , filesize       INT             
    , commentno      INT                 
    , tempfilename   VARCHAR(40)                                    
    , imageData      LONGBLOB        
    
    , PRIMARY KEY(reviewfileno)
)
ENGINE=InnoDB 
AUTO_INCREMENT=1 
DEFAULT CHARACTER SET utf8 
COLLATE utf8_general_ci;

-- ī�� �޴� ���̺�
DROP TABLE IF EXISTS TB_cafe_menu;
CREATE TABLE IF NOT EXISTS  TB_cafe_menu (
      cafeno         INT             
    , menu_id        INT             AUTO_INCREMENT
    , menu_name      NVARCHAR(50)    
    , price          INT             
    , descrption     NVARCHAR(50)
    
    ,PRIMARY KEY(menu_id)
)
ENGINE=InnoDB 
AUTO_INCREMENT=1 
DEFAULT CHARACTER SET utf8 
COLLATE utf8_general_ci;


-- ī�� ���� ���̺�
DROP TABLE IF EXISTS TB_cafe_star_point;
CREATE TABLE IF NOT EXISTS  TB_cafe_star_point (
      starno         INT             AUTO_INCREMENT
    , cafeno         INT             
    , grade          FLOAT
    
    ,PRIMARY KEY(starno)
)
ENGINE=InnoDB 
AUTO_INCREMENT=1 
DEFAULT CHARACTER SET utf8 
COLLATE utf8_general_ci;


-- foreign key ����

alter table TB_cafe_review add constraint cafereview_cafeinfo_fk foreign key(cafeno) references TB_cafe_cafeinfo(cafeno);
alter table TB_cafe_management_data add constraint cafemanagement_cafemenu_fk foreign key(menu_id) references TB_cafe_menu(menu_id);
alter table TB_cafe_menu add constraint cafemenu_cafeinfo_fk foreign key(cafeno) references TB_cafe_cafeinfo(cafeno);
alter table TB_cafe_reviewimg_data add constraint reviewdata_cafeeview_fk foreign key(commentno) references TB_cafe_review(commentno);
alter table TB_cafe_star_point add constraint starpoint_cafeinfo_fk foreign key(cafeno) references TB_cafe_cafeinfo(cafeno);

alter table TB_cafe_review add constraint cafereview_cafeuser_fk foreign key(userno) references TB_cafe_user(userno);
-- alter table TB_cafe_star_point add constraint starpoint_cafereview_fk foreign key(commentno) references TB_cafe_review(commentno);