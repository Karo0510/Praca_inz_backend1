drop table if exists employees;
drop table if exists job_position;
drop table if exists risk;
drop table if exists register_of_accidents;
drop table if exists periodic_training_register;
drop table if exists employees_accident;
drop table if exists employees_periodic_trainings;


CREATE TABLE employees (
  employee_id bigint NOT NULL AUTO_INCREMENT,
  first_work_day date NOT NULL,
  email varchar(255) DEFAULT NULL,
  first_name varchar(255) NOT NULL,
  first_day_of_introductory_training date DEFAULT NULL,
  job_position varchar(255) DEFAULT NULL,
  last_name varchar(255) NOT NULL,
  last_day_of_introductory_training date DEFAULT NULL,
  PRIMARY KEY (employee_id)
) ENGINE=InnoDB;

CREATE TABLE job_position (
  job_position_id bigint NOT NULL AUTO_INCREMENT,
  is_biological_risk bit(1) NOT NULL,
  is_chemical_risk bit(1) NOT NULL,
  is_mental_stress bit(1) NOT NULL,
  is_physical_risk bit(1) NOT NULL,
  job_position_name varchar(255) NOT NULL,
  PRIMARY KEY (job_position_id),
  UNIQUE KEY (job_position_name)
) ENGINE=InnoDB;

CREATE TABLE risk (
  id bigint NOT NULL AUTO_INCREMENT,
  job_position_job_position_id bigint DEFAULT NULL,
  dangerous varchar(255) NOT NULL,
  cause_of_dangerous varchar(255) NOT NULL,
  probability int NOT NULL,
  rank_of_effects int NOT NULL,
  risk int NOT NULL,
  preventive_actions varchar(255),
  probability_after_preventive_actions int,
  rank_of_effects_after_preventive_actions int,
  risk_after_preventive_actions int,
  last_update_date date NOT NULL,
  PRIMARY KEY (id),
  KEY (job_position_job_position_id),
  CONSTRAINT FOREIGN KEY (job_position_job_position_id) REFERENCES job_position (job_position_id)
) ENGINE=InnoDB;

CREATE TABLE register_of_accidents (
  id bigint NOT NULL AUTO_INCREMENT,
  accident_id bigint NOT NULL,
  date_of_accidents date NOT NULL,
  is_recognized_as_accident bit(1) NOT NULL,
  number_of_victims int NOT NULL,
  place_of_accident varchar(255) NOT NULL,
  number_of_accidental_protocole varchar(255) NOT NULL,
  type_of_accidents int NOT NULL,
  PRIMARY KEY (id),
  UNIQUE KEY (accident_id),
  UNIQUE KEY (number_of_accidental_protocole)
) ENGINE=InnoDB;

CREATE TABLE periodic_training_register (
  training_id bigint NOT NULL AUTO_INCREMENT,
  employee_id bigint NOT NULL,
  training_start_date date NOT NULL,
  periodic_training_date date DEFAULT NULL,
  periodic_training_exam_date date DEFAULT NULL,
  PRIMARY KEY (training_id)
) ENGINE=InnoDB;

CREATE TABLE employees_accident (
  employee_id bigint NOT NULL,
  accident_id bigint NOT NULL,
  CONSTRAINT  FOREIGN KEY (employee_id) REFERENCES employees (employee_id),
  CONSTRAINT FOREIGN KEY (accident_id) REFERENCES register_of_accidents (id)
) ENGINE=InnoDB;

CREATE TABLE employees_periodic_trainings (
  employee_id bigint NOT NULL,
  training_id bigint NOT NULL,
  CONSTRAINT FOREIGN KEY (training_id) REFERENCES periodic_training_register (training_id),
  CONSTRAINT FOREIGN KEY (employee_id) REFERENCES employees (employee_id)
) ENGINE=InnoDB;

