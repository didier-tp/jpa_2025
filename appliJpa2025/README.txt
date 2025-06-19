appliJpa2025
============
Application JPA (intégrée dans Spring6/springBoot3) 
avec base de données de référence BaseTpJpa2025 (Mysql/MariaDB) à préparer via src/script
java >=17
JPA 3.1 (jakarta.persistence... à la place de javax.persitence... )

-------------------
NB: structures des tables visibles au niveau du script  src/main/script/mysql_mariadb/init-db.sql
    structures objets(avec diag UML) dans src/main/resources/...png
---------------------------------------
tp.appliJpa.entity.Employe 
tp.appliJpa.repository.RepositoryEmployeSansSpring à tester via src/main/java/tp.appliJpa.TestSansSpringApp
==> petit exemple élémentaire n'utilisant pas du tout spring (que JPA)
avec utilisation implicite de META-INF/persitence.xml
     pas de @PersistenceContext
     entityManager.getTransaction().begin(); et entityManager.getTransaction().commit(); 
     ...
-----------
tp.appliJpa.entity.Employe 
et tp.appliJpa.repository.RepositoryEmploye
et src/test/java/TestRepositoryEmploye
==> exemple élémentaire intégré dans spring avec @PersitenceContext et commit/rollback automatiques (standard JEE)
META-INF/persitence.xml n'est plus utilisé , à la place config SpringBoot ---> application.properties
-------------
NB: pour des raisons de compacité du code , tous les DAO/Repository hériteront de RepositoryGenericDao 
(code source dans package tp.appliJpa.repository.generic)
---> ce projet ne s'appuie vonlontairement pas sur springDataJpa pour ne pas cacher/masquer l'API JPA
Autrement dit , 95% du code de ce projet devrait fonctionner dans un projet JakartaEE (ex: avec Jboss Widfly ou autre).
-----------------

partie "Compte, Client , Operation" :

   relation n-1 @ManyToOne entre Operation et Compte 
   relation inverse 1-n @OneToMany entre Compte et Operation
   Tests ou sein de TestRepositoryOperation et TestRepositoryCompte
   
   relation n-n ordinaire entre Compte et Client ( via table client_compte )
   coté principal: Client avec @ManyToMany( fetch = FetchType.LAZY) et 	 @JoinTable(name = "client_compte", ...)
   cote secondaire : Compte avec @ManyToMany(mappedBy="comptes" , ...)
   --> test de la liaison n-n au sein de TestRepositoryCompte
   
-------------------
  CompteEpargne héritant de Compte (exemple en mode "singleTable avec colonne discriminante "type_compte" valant "CompteCourant" pour Compte et CompteEpargne pour CompteEpargne)
------------------
  CompteService.effectuerVirement + CompteServiceImpl = exemple de @Transactional (spring) --> test au niveau de TestCompteService
-------------------
  
partie ClientAvecAdresse et AdresseClient = exemple de relation 1-1 @OneToOne
test dans TestRepositoryClientAvecAdresse
------------------ 
partie Commande / LigneCommande = exemple de relation 1-n avec java.util.Map plutôt que java.util.List 
test au sein de TestRepositoryCommande
--------------
partie BigData et TestRepositoryBigData = exemple avec 	@Lob
--------
partie ResaAvecVersion , RepositoryResaAvecVersion	, ResaService(Impl)
 = exemple avec @Version et verrouillage optimiste 
 NB: il est normal que TestResaAvecVersion.testResaAvecVersionV2 génère quelques messages d'erreurs , c'est le principe du verouillage optimiste .


  
  