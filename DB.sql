# DB 생성
DROP DATABASE IF EXISTS `JSPAM`;
CREATE DATABASE `JSPAM`;
USE `JSPAM`;

# article 테이블 생성
CREATE TABLE article(
    id INT(10) UNSIGNED NOT NULL PRIMARY KEY AUTO_INCREMENT,
    regDate DATETIME NOT NULL,
    title CHAR(100) NOT NULL,
    `body` TEXT NOT NULL
);

# article 테스트데이터 생성
INSERT INTO article
SET regDate = NOW(),
title = '제목 1',
`body` = '내용 1';

INSERT INTO article
SET regDate = NOW(),
title = '제목 2',
`body` = '내용 2';

INSERT INTO article
SET regDate = NOW(),
title = '제목 3',
`body` = '내용 3';

INSERT INTO article
SET regDate = NOW(),
title = '제목 4',
`body` = '내용 4';