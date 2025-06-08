package dados;

// PrincipalFormas.java
public class PrincipalFormas {
    public static void main(String[] args) {
        TrianguloEquilatero t1 = new TrianguloEquilatero();
        t1.setLado(5);
        TrianguloEquilatero t2 = new TrianguloEquilatero();
        t2.setLado(7);

        Losango l1 = new Losango();
        l1.setDiagonalMaior(8);
        l1.setDiagonalMenor(6);
        Losango l2 = new Losango();
        l2.setDiagonalMaior(10);
        l2.setDiagonalMenor(5);

        Circulo c1 = new Circulo();
        c1.setRaio(4);
        Circulo c2 = new Circulo();
        c2.setRaio(6);

        System.out.println(t1);
        System.out.println(t2);
        System.out.println(l1);
        System.out.println(l2);
        System.out.println(c1);
        System.out.println(c2);
    }
}
