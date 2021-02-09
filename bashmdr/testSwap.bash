#/bin/bash
#Redefinit a chaque fois le CLASSPATH de maniere a trouver en tete
#le .jar qui contient l'implementation courante


# Peu importe le premer jar qu'on donne pour compiler TestSysteme
javac -classpath  .:./swap1.jar:./hamcrest-core-1.3.jar:./junit-4.11.jar TestSysteme.java

for f in ./swap*.jar ; do
   echo "Traitement de: " $f
   java -classpath .:$f:./hamcrest-core-1.3.jar:./junit-4.11.jar\
     org.junit.runner.JUnitCore TestSysteme
done
