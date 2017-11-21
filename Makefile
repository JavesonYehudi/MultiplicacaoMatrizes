JFLAGS = -g
JC = /usr/bin/javac
JAVA = /usr/bin/java
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
		src/br/com/uerj/ServidorSocket.java 

SERVIDOR = ServidorSocket
CLIENTE = ClienteSocket

default: classes

classes: $(CLASSES:.java=.class)

servidor: classes
		$(JAVA) -classpath ./out br.com.uerj.$(SERVIDOR)

cliente: classes
		$(JAVA) -classpath ./out br.com.uerj.$(CLIENTE)

clean:
		$(RM) *.class