package dados;

// Animal.java
public abstract class Animal {
    protected String nome;

    public Animal(String nome) {
        this.nome = nome;
    }

    public abstract String emitirSom();
}

