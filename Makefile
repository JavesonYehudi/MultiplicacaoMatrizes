# Binários
JAVAC=/usr/bin/javac
JAVA=/usr/bin/java
JAR=/usr/bin/jar

JFLAGS = -g
JC = /usr/bin/javac
.SUFFIXES: .java .class
.java.class:
		$(JC) $(JFLAGS) -d out -sourcepath src $*.java

CLASSES = \
		src/br/com/uerj/modelo/Celula.java \
		src/br/com/uerj/modelo/Matriz.java \
		src/br/com/uerj/modelo/Tarefa.java \
		src/br/com/uerj/controle/Cliente.java \
		src/br/com/uerj/controle/ControleTeclado.java \
		src/br/com/uerj/controle/Sacola.java \
		src/br/com/uerj/RecebeMensagem.java \
		src/br/com/uerj/ClienteSocket.java \
		src/br/com/uerj/Main.java \
		src/br/com/uerj/ServidorSocket.java 


default: classes

classes: $(CLASSES:.java=.class)

clean:
		$(RM) *.class