package dados;

// Cao.java
public class Cao extends Animal {
    public Cao(String nome) {
        super(nome);
    }

    @Override
    public String emitirSom() {
        return nome + ": Au-au";
    }
}

