CREATE TABLE student (
  id bigint NOT NULL AUTO_INCREMENT PRIMARY KEY,
  firstname varchar(45),
  lastname varchar(45),
  birth_date date
);

CREATE TABLE java_grade (
  id bigint NOT NULL AUTO_INCREMENT PRIMARY KEY,
  student_id bigint,
  grade double,
  FOREIGN KEY (student_id) REFERENCES student(id)
);