SELECT *
FROM student
WHERE age > 10
  AND age < 20;
SELECT student.name
from student;
SELECT *
FROM student
WHERE student.name LIKE '%o%';
SELECT *
FROM student
WHERE student.age < student.id;
SELECT *
FROM student
ORDER BY student.age;