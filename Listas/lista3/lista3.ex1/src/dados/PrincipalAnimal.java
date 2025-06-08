package dados;
// PrincipalAnimal.java
public class PrincipalAnimal {
    public static void main(String[] args) {
        Animal rex = new Cao("Rex");
        Animal bob = new Cao("Bob");
        Animal mimi = new Gato("Mimi");
        Animal lulu = new Gato("Lulu");
        Animal mimosa = new Vaca("Mimosa");
        Animal malhada = new Vaca("Malhada");

        System.out.println(rex.emitirSom());
        System.out.println(bob.emitirSom());
        System.out.println(mimi.emitirSom());
        System.out.println(lulu.emitirSom());
        System.out.println(mimosa.emitirSom());
        System.out.println(malhada.emitirSom());
    }
}
