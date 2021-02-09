Test d'une des implementations (ici avec swap1.jar).

Suppose qu'on a bien cree tous les .jar a l'avance ...

1. Compilation et execution en ligne de commande (sans eclipse)

On suppose que tous les .jar (y compris ce qui est necessaire pour JUnit) se
trouve dans le repertoire courant.

Pour compiler:
java -classpath .:./swap1.jar:./hamcrest-core-1.3.jar:./junit-4.11.jar TestSysteme

Pour lancer l'execution:
java -classpath .:./swap1.jar:./hamcrest-core-1.3.jar:./junit-4.11.jar org.junit.runner.JUnitCore TestSysteme


2. Pour lancer les tests sous eclipse:
Creer un projet sous eclipse, lui ajouter JUNIT4 dans ses librairies
Ajouter le fichier swap1.jar dans les "JAR externes"
Inclure dans le projet le fichier TestSysteme.java, et le completer.
Executer le projet comme Test JUnit.



