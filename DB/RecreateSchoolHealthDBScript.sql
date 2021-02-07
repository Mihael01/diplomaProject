#База данни Училищно здраве
CREATE SCHEMA IF NOT EXISTS school_health;
ALTER SCHEMA school_health CHARACTER SET utf8;

USE school_health;

#Номенклатурна таблица за видове училища
CREATE TABLE IF NOT EXISTS school_type(
school_type_code VARCHAR(20) NOT NULL UNIQUE,
school_type_name VARCHAR(100) NOT NULL,
CONSTRAINT school_type_pk PRIMARY KEY (school_type_code));

#Номенклатурна таблица за области
CREATE TABLE IF NOT EXISTS region(
region_code VARCHAR(3) NOT NULL UNIQUE,
region_name VARCHAR(30) NOT NULL,
CONSTRAINT region_pk PRIMARY KEY (region_code));

#Номенклатурна таблица за общини
CREATE TABLE IF NOT EXISTS municipality(
municipality_code VARCHAR(5) NOT NULL UNIQUE,
municipality_name VARCHAR(30) NOT NULL,
CONSTRAINT municipality_pk PRIMARY KEY (municipality_code));

#Номенклатурна таблица за селища (градове/села)
CREATE TABLE IF NOT EXISTS settlement_place(
ekatte INT(5) NOT NULL UNIQUE,
settlement_place_type VARCHAR(5) NOT NULL,
settlement_place_name VARCHAR(40) NOT NULL,
municipality_code VARCHAR(5),
region_code VARCHAR(3),
CONSTRAINT settlement_place_pk PRIMARY KEY (ekatte),
CONSTRAINT municipality_code_fk FOREIGN KEY (municipality_code) REFERENCES municipality(municipality_code),
CONSTRAINT region_code_fk FOREIGN KEY (region_code) REFERENCES region(region_code));

#Таблица за адреси
CREATE TABLE IF NOT EXISTS address(
id INT NOT NULL AUTO_INCREMENT,
street VARCHAR(30),
number VARCHAR(10),
comment VARCHAR(50), # Допълнителна информация за адреса
ekatte INT(5),
CONSTRAINT address_pk PRIMARY KEY (id),
CONSTRAINT ekatte_fk FOREIGN KEY (ekatte) REFERENCES settlement_place(ekatte));

#Таблица с училищните медицински лица
CREATE TABLE IF NOT EXISTS school_medics(
id INT NOT NULL AUTO_INCREMENT,
school_medics_name VARCHAR(100),
school_medics_telephone_number VARCHAR(50),
CONSTRAINT school_medics_pk PRIMARY KEY (id));

#Tаблица за училища
CREATE TABLE IF NOT EXISTS school(
id INT(6) NOT NULL AUTO_INCREMENT,
school_number VARCHAR(6),
school_type VARCHAR(20),
school_type_other VARCHAR(150) ,
school_name VARCHAR(150),
school_telephone_number VARCHAR(50),
address_id INT,
school_medics_id INT,
CONSTRAINT school_pk PRIMARY KEY (id),
CONSTRAINT address_fk FOREIGN KEY (address_id) REFERENCES address(id),
CONSTRAINT school_type_fk FOREIGN KEY (school_type) REFERENCES school_type(school_type_code),
CONSTRAINT school_medics_fk FOREIGN KEY (school_medics_id) REFERENCES school_medics(id));
#Тригер, който ограничава инсъртите в таблицата със селища, ако въвежданата община или област не съществувава в таблиците за общини и области.
#Инициализацията на данни за селищата, общините и областите в България се извършва от администратора на приложението на базата на InitSettlementPlacesScript.sql; InitMunicipalityScript.sql и InitRegionScript.sql.

DELIMITER $$

CREATE TRIGGER before_insert_settlement_place
BEFORE INSERT
ON settlement_place FOR EACH ROW
BEGIN
    IF NOT EXISTS (SELECT 1 FROM region WHERE NEW.region_code = region.region_code)
    THEN CALL `Insert not allowed`;
    END IF;
    
    IF NOT EXISTS (SELECT 1 FROM municipality WHERE NEW.municipality_code = municipality.municipality_code)
    THEN CALL `Insert not allowed`;
    END IF;
END $$

DELIMITER ;


#Номенклатурна таблица за пол
CREATE TABLE IF NOT EXISTS sex_type(
sex_type_code VARCHAR(1) NOT NULL UNIQUE,
sex_type_name VARCHAR(10) NOT NULL,
CONSTRAINT sex_type_pk PRIMARY KEY (sex_type_code));

#Таблица с основни данни за всеки ученик
CREATE TABLE IF NOT EXISTS student(
id INT NOT NULL AUTO_INCREMENT,
first_name VARCHAR(50),
middle_name VARCHAR(50),
last_name VARCHAR(50),
class_ VARCHAR(10),
class_letter VARCHAR(1), # паралелка
school_id INT,
egn INT(20), # ЕГН или номер на чужд гражданин
birth_date DATE,
birth_place VARCHAR(100), # месторождение
address_id INT,
telephone_number VARCHAR(30),
sex  VARCHAR(1),
family_burden VARCHAR(500), # фамилна обремененост
past_illnesses VARCHAR(1000), # минали заболявания
blood_type VARCHAR(10), # кръвна група
rh VARCHAR(10), # Rh +/-
allergies VARCHAR(1000),
CONSTRAINT student_pk PRIMARY KEY (id),
CONSTRAINT s_addr_fk FOREIGN KEY (address_id) REFERENCES address(id),
CONSTRAINT school_fk FOREIGN KEY (school_id) REFERENCES school(id),
CONSTRAINT sex_fk FOREIGN KEY (sex) REFERENCES sex_type(sex_type_code));

#Номенклатурна таблица за родител/настойник
CREATE TABLE IF NOT EXISTS parent_type(
parent_type_code VARCHAR(10) NOT NULL UNIQUE,
parent_type_name VARCHAR(100) NOT NULL,
CONSTRAINT parent_type_pk PRIMARY KEY (parent_type_code));

#Таблица с родители на учениците
CREATE TABLE IF NOT EXISTS parent(
id INT NOT NULL AUTO_INCREMENT,
parent_name VARCHAR(100),
parent_type_code VARCHAR(10),
address_id INT,
telephone_number VARCHAR(30),
place_of_work VARCHAR(100),
business_telephone_number VARCHAR(30),
CONSTRAINT parent_pk PRIMARY KEY (id),
CONSTRAINT p_addr_fk FOREIGN KEY (address_id) REFERENCES address(id),
CONSTRAINT parent_type_fk FOREIGN KEY (parent_type_code) REFERENCES parent_type(parent_type_code));


#Свързваща таблица между родител и ученик, тъй като може един ученик да има повече от един регистриран родител, а един родител може да има повече от едно дете в дадено училище
CREATE TABLE IF NOT EXISTS student_parent(
student_id INT NOT NULL,
parent_id INT NOT NULL,
CONSTRAINT student_fk FOREIGN KEY (student_id) REFERENCES student(id),
CONSTRAINT parent_fk FOREIGN KEY (parent_id) REFERENCES parent(id));

#Добавяме първичен ключ към таблица родител - ученик
ALTER TABLE student_parent
ADD COLUMN id INT,
ADD CONSTRAINT student_parent_id PRIMARY KEY (id);

#Таблица с лични лекари на учениците
CREATE TABLE IF NOT EXISTS gp(
id INT NOT NULL AUTO_INCREMENT,
gp_name VARCHAR(150),
gp_telephone_number VARCHAR(50),
medical_center VARCHAR(200),
CONSTRAINT gp_pk PRIMARY KEY (id));

#Добавяме външен ключ към личния лекар в таблицата с учениците
ALTER TABLE student
ADD COLUMN gp_id INT,
ADD CONSTRAINT gp_fk FOREIGN KEY (gp_id) REFERENCES gp(id);

#Лична здравно профилактична карта
CREATE TABLE IF NOT EXISTS lzpk( 
id INT NOT NULL AUTO_INCREMENT,
lzpk_number INT,  # номер
issue_date DATE, # издадена на
issued_from VARCHAR(100), #  от кого е издадена
CONSTRAINT lzpk_pk PRIMARY KEY (id));

#Добавяме външен ключ към ЛЗПК в таблицата с учениците
ALTER TABLE student
ADD COLUMN lzpk_id INT,
ADD CONSTRAINT lzpk_fk FOREIGN KEY (lzpk_id) REFERENCES lzpk(id);

#Номенклатурна таблица за потвърждение - да/не
CREATE TABLE IF NOT EXISTS confirmation_flag(
confirmation_flag_code VARCHAR(1) NOT NULL UNIQUE,
confirmation_flag_value VARCHAR(2) NOT NULL,
CONSTRAINT confirmation_flag_pk PRIMARY KEY (confirmation_flag_code));

#Номенклатурна таблица за имунизации - не подлежи/подлежи за възрастта
CREATE TABLE IF NOT EXISTS immunization_comment(
immunization_comment_code VARCHAR(1) NOT NULL UNIQUE,
immunization_comment_value VARCHAR(50) NOT NULL,
CONSTRAINT immunization_comment_pk PRIMARY KEY (immunization_comment_code));

#Таблица за здравословното състояние на ученика
CREATE TABLE IF NOT EXISTS health_condition(
id INT NOT NULL AUTO_INCREMENT,
examination_date DATE, # дата на преглед
diagnosis VARCHAR(100), # диагноза
treatment_place VARCHAR(300), #  къде е лекуван ученика
treatment_result VARCHAR(300), # резултат от лечението 
additional_activities VARCHAR(1), # необходимост от провеждане на допълнителни мероприятия - да/не
additional_activities_description VARCHAR(1), # описание на допълнителни мероприятия
exempt_from_physical_education VARCHAR(1), # освободен от часовете по физическо възпитание - да/не 
therapeutic_physical_education VARCHAR(1), # включени в групи по лечебна физкултура в училище - да/не
dispensarysation VARCHAR(1), # диспансеризация
observer VARCHAR(100), # от кого се наблюдава по време на диспансеризацията
missing_immunization_flag VARCHAR(1), # липсващи имунизации  да/не
mandatory_immunization_flag VARCHAR(1), # не подлежи/подлежи за възрастта  
parasites VARCHAR(1), # ученикът има паразити  - да/не
student_id INT,
CONSTRAINT health_condition_pk PRIMARY KEY (id),
CONSTRAINT hc_student_fk FOREIGN KEY (student_id) REFERENCES student(id),
CONSTRAINT aa_fk FOREIGN KEY (additional_activities) REFERENCES confirmation_flag(confirmation_flag_code),
CONSTRAINT efpe_fk FOREIGN KEY (exempt_from_physical_education) REFERENCES confirmation_flag(confirmation_flag_code),
CONSTRAINT tpe_fk FOREIGN KEY (therapeutic_physical_education) REFERENCES confirmation_flag(confirmation_flag_code),
CONSTRAINT mif_fk FOREIGN KEY (missing_immunization_flag) REFERENCES confirmation_flag(confirmation_flag_code),
CONSTRAINT mandif_fk FOREIGN KEY (mandatory_immunization_flag) REFERENCES immunization_comment(immunization_comment_code),
CONSTRAINT parasites_fk FOREIGN KEY (parasites) REFERENCES confirmation_flag(confirmation_flag_code));

#Таблица за проведени имунизации по дати на ученика (добавят се данни през годините)
CREATE TABLE IF NOT EXISTS immunization(
id INT NOT NULL AUTO_INCREMENT,
immunization_date DATE, # дата на имунизация
immunization_name VARCHAR(100), # име на имунизацията
health_condition_id INT,
CONSTRAINT immunization_pk PRIMARY KEY (id),
CONSTRAINT health_condition_fk FOREIGN KEY (health_condition_id) REFERENCES health_condition(id));

#Таблица с прегледи 
CREATE TABLE IF NOT EXISTS examination(
id INT NOT NULL AUTO_INCREMENT,
examination_date DATE, # дата на прегледа
examination_place VARCHAR(150), # къде е проведен профиактични преглед на ученика
examination_specialist VARCHAR(200), # специалист извършил прегледа
examination_data VARCHAR(500), # Данни от лекарския преглед
mental_development  VARCHAR(500), # Психическо развитие
new_illness VARCHAR(500), # Новооткрити остри и хронични заболявания
paraclinical_examinations VARCHAR(500), #Проведени параклинични изследвания
health_condition_id INT,
CONSTRAINT examination_pk PRIMARY KEY (id),
CONSTRAINT ex_health_condition_fk FOREIGN KEY (health_condition_id) REFERENCES health_condition(id));

#Таблица за антропологични показатели на ученика
CREATE TABLE IF NOT EXISTS anthropological_indicators(
id INT NOT NULL AUTO_INCREMENT,
body_height DECIMAL(5,3), # Височина 
body_weight DECIMAL(5,3), # Телесна маса
body_circumference DECIMAL(5,2), # Обиколка на тялото
student_id INT,
CONSTRAINT anthropological_indicators_pk PRIMARY KEY (id),
CONSTRAINT anthr_student_fk FOREIGN KEY (student_id) REFERENCES student(id)); 


#Таблица за вредни навици и пристрастявания
CREATE TABLE IF NOT EXISTS addictions(
id INT NOT NULL AUTO_INCREMENT,
addictions_description VARCHAR(1000), # Вредни навици и пристрастявания 
methodologies VARCHAR(1000), # Индивидуална работа за преодоляването им / методологии
results VARCHAR(1000), # Резултати
student_id INT,
CONSTRAINT addictions_pk PRIMARY KEY (id),
CONSTRAINT addict_student_fk FOREIGN KEY (student_id) REFERENCES student(id)); 

#Таблица за физическа дееспособност
CREATE TABLE IF NOT EXISTS physical_capacity(
id INT NOT NULL AUTO_INCREMENT,
strength_left_hand VARCHAR(10), # Мускулна сила на лява ръка
strength_right_hand VARCHAR(10), # Мускулна сила на дясна ръка
running VARCHAR(10), # Гладко бягане
long_jump VARCHAR(10), # Скок на дължина
throwing_ball VARCHAR(10), # Хвърляне на малка плътна топка
maximum_squats INT(3), # Mаксимален брой клякания
physical_capacity_mark INT(1), # Обща оценка на ученика за физическа дееспособност
student_id INT,
CONSTRAINT addictions_pk PRIMARY KEY (id),
CONSTRAINT pc_student_fk FOREIGN KEY (student_id) REFERENCES student(id)); 

#Номенклатурна таблица за паразити
CREATE TABLE IF NOT EXISTS parasit_type(
parasit_type_code VARCHAR(3) NOT NULL UNIQUE, # Код
parasit_type_name VARCHAR(100) NOT NULL, # Вид паразити
CONSTRAINT parasit_type_pk PRIMARY KEY (parasit_type_code)); 

#Свързваща таблица между ученик и паразити
CREATE TABLE IF NOT EXISTS student_parasit(
parasit_type_code VARCHAR(3) NOT NULL,
student_id INT NOT NULL,
CONSTRAINT parasit_type_fk FOREIGN KEY (parasit_type_code) REFERENCES parasit_type(parasit_type_code),
CONSTRAINT sp_student_fk FOREIGN KEY (student_id) REFERENCES student(id));

#Добавяме първичен ключ към таблица ученик - паразити
ALTER TABLE student_parasit
ADD COLUMN id INT,
ADD CONSTRAINT student_parasit_id PRIMARY KEY (id);

#Номенклатурна таблица за заболявания и аномалии
CREATE TABLE IF NOT EXISTS diseases_and_abnorm_type(
diseases_and_abnorm_type_code VARCHAR(4) NOT NULL UNIQUE, # Шифър
diseases_and_abnorm_type_name VARCHAR(150) NOT NULL, # Заболявания и аномалии
CONSTRAINT diseases_and_abnorm_type_pk PRIMARY KEY (diseases_and_abnorm_type_code)); 

#Свързваща таблица между ученици и заболявания и аномалии
CREATE TABLE IF NOT EXISTS student_diseases_and_abnormalities(
diseases_and_abnorm_type_code VARCHAR(4) NOT NULL,
student_id INT NOT NULL,
CONSTRAINT diseases_and_abnorm_type_fk FOREIGN KEY (diseases_and_abnorm_type_code) REFERENCES diseases_and_abnorm_type(diseases_and_abnorm_type_code),
CONSTRAINT da_student_fk FOREIGN KEY (student_id) REFERENCES student(id));

#Заболявания, които изискват диспансерно наблюдение
CREATE TABLE IF NOT EXISTS dispensary_observation_illness_type(
dispensary_observ_illness_type_code VARCHAR(10) NOT NULL UNIQUE, # МКБ 10 код
dispensary_observ_illness_type_name VARCHAR(150) NOT NULL, # Заболявания, които изискват диспансерно наблюдение
CONSTRAINT dispensary_observation_illness_type_pk PRIMARY KEY (dispensary_observ_illness_type_code));

#Свързваща таблица между ученици и диспансерно наблюдение
CREATE TABLE IF NOT EXISTS student_dispensary_observation(
dispensary_observ_illness_type_code VARCHAR(10) NOT NULL,
student_id INT,
CONSTRAINT dispensary_observation_illness_type_fk FOREIGN KEY (dispensary_observ_illness_type_code) REFERENCES dispensary_observation_illness_type(dispensary_observ_illness_type_code),
CONSTRAINT do_student_fk FOREIGN KEY (student_id) REFERENCES student(id));

#Добавяме период на активност за медицинското лице
ALTER TABLE school_medics
ADD COLUMN active_from DATE,
ADD COLUMN active_to DATE;

#Добавяме първичен ключ към таблица ученик - паразити
ALTER TABLE student_dispensary_observation
ADD COLUMN id INT,
ADD CONSTRAINT st_dispensary_observ_id PRIMARY KEY (id);

#Добавяме първичен ключ към таблица ученик - паразити
ALTER TABLE student_diseases_and_abnormalities
ADD COLUMN id INT,
ADD CONSTRAINT st_diseases_and_abnorm_id PRIMARY KEY (id);

#таблица само с тестова цел
create table IF NOT EXISTS test_table
(
    test_code varchar(3)  not null,
    test_name varchar(30) not null,
    constraint test_code
        unique (test_code)
);

alter table test_table
    add primary key (test_code);




