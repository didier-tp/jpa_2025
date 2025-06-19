Rappel:  structures des tables visibles au niveau du script  src/main/script/mysql_mariadb/init-db.sql
         structures objets(avec diag UML) dans src/main/resources/...png
---------------------

Devise, Pays : exemple sans auto_increment
-------------------------------------
Acteur, Film : exemple de ralation n-n sophistiquée se décomposant en deux relation n-1 complémentaire.
Etant donné que la table de jointure acteur_film comporte une colonne de detail "role" (role joué par un acteur précis dans un film précis),
cette table est vue comme une entité java/objet RoleActeurFilm (avec une clef primaire composite RoleActeurFilmCompositePk )  
--------------------------------------
Facture exemple de classe d'entité dont les valeurs sont stockées dans plusieurs tables :
   facture (table principale)  et client_infos_facture (table(s) secondaire(s))
--------------------------
Facture , LigneFacture et LigneFacture référençant Article:
exemple de cascades 
