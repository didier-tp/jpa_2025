CREATE DATABASE IF NOT EXISTS BaseTpJpa2025 ;
use BaseTpJpa2025;

DROP TABLE IF EXISTS ligne_commande;
DROP TABLE IF EXISTS commande;

DROP TABLE IF EXISTS employe;
DROP TABLE IF EXISTS operation;
DROP TABLE IF EXISTS client_compte;
DROP TABLE IF EXISTS compte;
DROP TABLE IF EXISTS adresse_de_client;
DROP TABLE IF EXISTS client;
DROP TABLE IF EXISTS resa_avec_version;
DROP TABLE IF EXISTS big_data;

DROP TABLE IF EXISTS pays;
DROP TABLE IF EXISTS devise;

DROP TABLE IF EXISTS acteur_film;
DROP TABLE IF EXISTS acteur;
DROP TABLE IF EXISTS film;

DROP TABLE IF EXISTS ligne_facture;
DROP TABLE IF EXISTS client_infos_facture;
DROP TABLE IF EXISTS facture;
DROP TABLE IF EXISTS article_materiel;
DROP TABLE IF EXISTS article_numerique;
DROP TABLE IF EXISTS article;


create table commande (
numero integer not null auto_increment,
date date, 
primary key (numero));

create table ligne_commande (
num_commande integer not null,
num_ligne integer not null, 
prix_unitaire double precision, 
quantite integer, 
ref_produit varchar(255), 
primary key (num_commande, num_ligne));

ALTER TABLE ligne_commande ADD CONSTRAINT ligne_commande_avec_commande_valide
FOREIGN KEY (num_commande) REFERENCES commande(numero);

INSERT INTO commande (numero,date) VALUES (1,'2023-12-19');
INSERT INTO ligne_commande (num_commande,num_ligne,prix_unitaire,quantite,ref_produit) 
 VALUES (1,1,2.4,1,"stylo");
INSERT INTO ligne_commande (num_commande,num_ligne,prix_unitaire,quantite,ref_produit) 
 VALUES (1,2,6.1,3,"cahier");

CREATE TABLE employe(
emp_id INTEGER auto_increment,
firstname VARCHAR(64),
lastname VARCHAR(64),
phone_number VARCHAR(64),
email VARCHAR(64),
login VARCHAR(32),
password VARCHAR(64),
PRIMARY KEY(emp_id));

CREATE TABLE compte(
numero INTEGER auto_increment,
label  VARCHAR(64),
solde DOUBLE,
type_compte VARCHAR(64),
taux DOUBLE,
PRIMARY KEY(numero));

CREATE TABLE operation(
num_op INTEGER auto_increment,
label  VARCHAR(64),
montant DOUBLE,
date_op  VARCHAR(16),
num_compte INTEGER,
PRIMARY KEY(num_op));

ALTER TABLE operation ADD CONSTRAINT operation_avec_compte_valide
FOREIGN KEY (num_compte) REFERENCES compte(numero);

CREATE TABLE client(
id INTEGER auto_increment,
prenom  VARCHAR(64),
nom VARCHAR(64),
PRIMARY KEY(id));

CREATE TABLE adresse_de_client(
id_client INTEGER,
numero  VARCHAR(16),
rue  VARCHAR(64),
code_postal VARCHAR(64),
ville VARCHAR(64));

ALTER TABLE adresse_de_client ADD CONSTRAINT adresse_de_client_avec_client_valide
FOREIGN KEY (id_client) REFERENCES client(id);


CREATE TABLE client_compte(
id_client  INTEGER,
num_compte INTEGER,
PRIMARY KEY(id_client,num_compte));

ALTER TABLE client_compte ADD CONSTRAINT client_compte_avec_compte_valide
FOREIGN KEY (num_compte) REFERENCES compte(numero);
ALTER TABLE client_compte ADD CONSTRAINT client_compte_avec_client_valide
FOREIGN KEY (id_client) REFERENCES client(id);


INSERT INTO employe (emp_id,firstname,lastname,phone_number,email,login,password)
VALUES (1,'alain', 'Therieur' , '0102030405' , 'alain.therieur@xyz.com','login1','pwd1');

INSERT INTO employe (emp_id,firstname,lastname,phone_number,email,login,password)
VALUES (2,'axelle', 'Aire' , '0102030405' , 'axelle.aire@m2i.com','login2','pwd2');

INSERT INTO compte (numero,label,solde,type_compte,taux) 
   VALUES (1,'compteA', 50.0,'CompteCourant',null);
INSERT INTO compte (numero,label,solde,type_compte,taux) 
  VALUES (2,'compteB', 70.0,'CompteEpargne',1.2);


INSERT INTO operation (num_op,label,montant,date_op,num_compte) 
   VALUES (1,'achat bonbons', -5.2 , '2022-12-08' , 1);
INSERT INTO operation (num_op,label,montant,date_op,num_compte) 
    VALUES (2,'paye novembre', 2000 , '2022-11-30' , 1);
    
INSERT INTO client (id,prenom,nom) VALUES (1,'alex', 'Therieur');
INSERT INTO client (id,prenom,nom) VALUES (2,'jean', 'Aimare');

INSERT INTO client_compte (id_client,num_compte) VALUES (1,1); 
INSERT INTO client_compte (id_client,num_compte) VALUES (2,1);
INSERT INTO client_compte (id_client,num_compte) VALUES (2,2); 

INSERT INTO adresse_de_client (id_client,numero,rue,code_postal,ville) 
      VALUES (1,"1","rue Xy" , "75001" , "Paris"); 
      
INSERT INTO adresse_de_client (id_client,numero,rue,code_postal,ville) 
      VALUES (2,"3bis","rue zz" , "76000" , "Rouen");      

CREATE TABLE resa_avec_version(
id_resa INTEGER auto_increment,
num_version INTEGER,
objet  VARCHAR(64),
num_client INTEGER,
PRIMARY KEY(id_resa));

INSERT INTO resa_avec_version (id_resa,num_version,objet,num_client) 
  VALUES (1,1,"uniqueVoitureDeCourtoisie" , null); 
  
CREATE TABLE big_data (
   id bigint not null auto_increment, 
   image longblob, 
   json_data longtext, 
   primary key (id));  
   
   
CREATE TABLE devise(
	code_devise VARCHAR(8),
	monnaie VARCHAR(64),
	d_change double,
	PRIMARY KEY(code_devise));	 

CREATE TABLE pays(
	code_pays VARCHAR(8),
	capitale VARCHAR(64),
	nom_pays VARCHAR(64),
	ref_devise VARCHAR(64),
	PRIMARY KEY(code_pays));	  
	
ALTER TABLE pays ADD CONSTRAINT Pays_avec_devise_valide 
FOREIGN KEY (ref_devise) REFERENCES devise(code_devise);

INSERT INTO devise (code_devise,d_change,monnaie) VALUES ('EUR',1.11570,'euro');
INSERT INTO devise (code_devise,d_change,monnaie) VALUES ('JPY',0.00961816 ,'yen');
INSERT INTO devise (code_devise,d_change,monnaie)VALUES ('USD',1.0,'dollar');
INSERT INTO devise (code_devise,d_change,monnaie) VALUES ('GBP',1.32940,'livre');

INSERT INTO pays (capitale,code_pays,nom_pays,ref_devise)
               VALUES ('Paris','fr','France','EUR');
 INSERT INTO  pays (capitale,code_pays,nom_pays,ref_devise)
               VALUES ('Berlin','de','Allemagne','EUR');
 INSERT INTO  pays (capitale,code_pays,nom_pays,ref_devise)
               VALUES ('Rome','it','Italie','EUR');      
 INSERT INTO  pays (capitale,code_pays,nom_pays,ref_devise)
               VALUES ('Londres','uk','Royaumes unis','GBP');           
 INSERT INTO  pays (capitale,code_pays,nom_pays,ref_devise)
               VALUES ('Washingtown','us','USA','USD');     
 INSERT INTO  pays (capitale,code_pays,nom_pays,ref_devise)
               VALUES ('Pekin','china','Chine','USD');         
 INSERT INTO  pays (capitale,code_pays,nom_pays,ref_devise)
               VALUES ('Tokyo','JP','Japon','JPY');  	
   
CREATE TABLE film
     ( id_film integer  NOT NULL auto_increment,
       titre VARCHAR(64) ,
       date_sortie date ,
       producteur VARCHAR(48),
       PRIMARY KEY (id_film) );

INSERT INTO film Values (1,'Film1' , '1950-02-23' , 'producteur xx');
INSERT INTO film Values (2,'Film2' , '1960-02-23' , 'producteur yy');
INSERT INTO film Values (3,'Film3' , '1970-02-23' , 'producteur zz');


CREATE TABLE acteur
     ( id_acteur integer  NOT NULL auto_increment,
       nom VARCHAR(48) ,
       prenom VARCHAR(64),
       PRIMARY KEY (id_acteur) );

INSERT INTO acteur Values (1,'Richard' , 'Pierre' );
INSERT INTO acteur Values (2,'Depardieu' , 'Gérard' );
 

CREATE TABLE acteur_film
     ( id_acteur integer,
       id_film integer ,
       role VARCHAR(64),
       primary key(id_acteur,id_film));

ALTER TABLE acteur_film ADD CONSTRAINT avec_acteur_valide 
                          FOREIGN KEY  (id_acteur) REFERENCES acteur(id_acteur);
ALTER TABLE acteur_film ADD CONSTRAINT avec_film_valide
                          FOREIGN KEY  (id_film) REFERENCES film(id_film);
                          
INSERT INTO acteur_film(id_acteur,id_film,role) Values (1,1 , 'pecheur' );
INSERT INTO acteur_film(id_acteur,id_film,role) Values (1,2 , 'jardinier' );
INSERT INTO acteur_film(id_acteur,id_film,role) Values (2,1 , 'policier' );
INSERT INTO acteur_film(id_acteur,id_film,role) Values (2,2 , 'voleur' );
INSERT INTO acteur_film(id_acteur,id_film,role) Values (2,3 , 'amoureux' );
                          
CREATE TABLE article
     ( id integer  NOT NULL auto_increment,
       label VARCHAR(64) ,
       prix double,
       PRIMARY KEY (id) );                          

INSERT INTO article Values (1,'article1',5.5);
INSERT INTO article Values (2,'article2',6.6);
INSERT INTO article Values (3,'article3',7.7);

CREATE TABLE article_materiel
     ( id integer,
       poids double ,
       volume double,
       PRIMARY KEY (id) ); 
	   

ALTER TABLE article_materiel ADD CONSTRAINT complement_materiel_pour_article_valide 
                          FOREIGN KEY  (id) REFERENCES article(id); 

INSERT INTO article_materiel(id,poids,volume) Values (2,1.1,0.2);						  
	   
CREATE TABLE article_numerique
     ( id integer,
       download_url VARCHAR(128) ,
       description_url VARCHAR(128),
       PRIMARY KEY (id) ); 	 

ALTER TABLE article_numerique ADD CONSTRAINT complement_numerique_pour_article_valide 
                          FOREIGN KEY  (id) REFERENCES article(id); 

INSERT INTO article_numerique(id,download_url,description_url) Values (3,"http://xyz/download/a3","http://xyz/description/a3");							  

CREATE TABLE facture
     ( numero integer  NOT NULL auto_increment,
       date VARCHAR(12) ,
       PRIMARY KEY (numero) );   
       
CREATE TABLE client_infos_facture(
       num_facture integer  NOT NULL ,
       nom VARCHAR(64) ,
       adresse VARCHAR(128) ,
       email VARCHAR(128) ,
       PRIMARY KEY (num_facture)  );     

INSERT INTO facture Values (1,'2025-05-12');

CREATE TABLE ligne_facture
     ( id integer  NOT NULL auto_increment,
       id_facture integer,
       id_article integer,
       quantite integer default 1,
       PRIMARY KEY (id) );  

ALTER TABLE client_infos_facture ADD CONSTRAINT infos_client_ref_facture_valide 
                          FOREIGN KEY  (num_facture) REFERENCES facture(numero);  
       
ALTER TABLE ligne_facture ADD CONSTRAINT ligne_ref_facture_valide 
                          FOREIGN KEY  (id_facture) REFERENCES facture(numero);       
ALTER TABLE ligne_facture ADD CONSTRAINT ligne_ref_article_valide 
                          FOREIGN KEY  (id_article) REFERENCES article(id);  
                          
INSERT INTO client_infos_facture(num_facture,nom,adresse,email) Values (1,'client_Abc','1 rue elle 75001 Paris','client_abc@gmail.com');                          
                          
INSERT INTO ligne_facture(id_facture,id_article,quantite) Values (1,1,2);
INSERT INTO ligne_facture(id_facture,id_article,quantite) Values (1,2,3);
INSERT INTO ligne_facture(id_facture,id_article,quantite) Values (1,3,1);

select * from ligne_facture;
select * from facture;
select * from client_infos_facture;
select * from article;
select * from article_materiel;
select * from article_numerique;

SELECT * from film;
SELECT * from acteur;    
SELECT * from acteur_film;
               
SELECT * FROM devise;
SELECT * FROM pays;               
               
SELECT * FROM commande;
SELECT * FROM ligne_commande;

SELECT * FROM employe;
SELECT * FROM compte;
SELECT * FROM operation;
SELECT * FROM client;
SELECT * FROM adresse_de_client;
SELECT * FROM client_compte;
SELECT * FROM resa_avec_version;