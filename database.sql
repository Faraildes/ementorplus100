CREATE TABLE teacher (
  Id int(11) NOT NULL AUTO_INCREMENT,
  Name varchar(60) NOT NULL,    
  Cpf varchar(11) NOT NULL,
  Phone varchar(10) NOT NULL,
  AdmissionDate date NOT NULL,
  Salary double NOT NULL,  
  Chief varchar(3) NOT NULL,
  Coordinator varchar(3) NOT NULL,
  PRIMARY KEY (Id)
  );
  
  CREATE TABLE Turma (
  Id int(11) NOT NULL AUTO_INCREMENT,
  Name varchar(60) NOT NULL,    
  Notas double NOT NULL,    
  PRIMARY KEY (Id)
  );

CREATE TABLE student (
  Id int(11) NOT NULL AUTO_INCREMENT,
  Name varchar(60) NOT NULL,  
  BirthDate datetime NOT NULL, 
  Cpf varchar(11) NOT NULL,
  Phone varchar(10) NOT NULL,
  Period int(2) NOT NULL,  
  TurmaId int(11) NOT NULL,
  PRIMARY KEY (Id),
  FOREIGN KEY (TurmaId) REFERENCES turma (id)  
  );

INSERT INTO teacher(Name, Cpf, Phone, AdmissionDate, Salary, Chief, Coordinator) VALUES
	('Francisco', '25468974521', '985674528', '2002-12-12', 12000.00, 'nao', 'nao'),
	('Oberlan',  '25467821321', '97856421', '2003-02-21', 10000.00, 'nao', 'nao'),
	('Maria', '32565487231', '978564212', '1995-11-21', 15000.00, 'nao', 'nao');
    
INSERT INTO turma(Name) VALUES
	('POO' ),
    ('POI' ),
    ('Eng.Software');

INSERT INTO student (Name, BirthDate, Cpf, Phone, Period, TurmaId) VALUES 
  ('Bob Brown', '1998-04-21 00:00:00', '15687945329', '986534726', 2, 1),
  ('Maria Green', '1979-12-31 00:00:00','23654893312','965878421', 3, 3),                
  ('Alex Grey', '1988-01-15 00:00:00', '32564879231', '987654231', 3, 2),
  ('Martha Red','1993-11-30 00:00:00', '14578965421', '987564212', 2, 1),
  ('Donald Blue', '2000-01-09 00:00:00', '5648975412', '96543257', 3, 2),
  ('Alex Pink', '1997-03-04 00:00:00', '23456789254', '98654231', 2, 3);
