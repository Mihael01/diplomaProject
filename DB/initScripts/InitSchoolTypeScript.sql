#Номенклатурни данни за видове училища
INSERT INTO school_health.school_type (school_type_code, school_type_name) VALUES ('НУ','Начално училище');
INSERT INTO school_health.school_type (school_type_code, school_type_name) VALUES ('ОУ','Основно училище');
INSERT INTO school_health.school_type (school_type_code, school_type_name) VALUES ('ОбУ','Обединено училище');
INSERT INTO school_health.school_type (school_type_code, school_type_name) VALUES ('СУ','Средно училище');
INSERT INTO school_health.school_type (school_type_code, school_type_name) VALUES ('Г','Гимназия');
#Когато типа на училището е друг, трябва да се попълни допълнителна информация в полето school_type_other от таблицата school
INSERT INTO school_health.school_type (school_type_code, school_type_name) VALUES ('Др','Друг вид училище');
