//screva um programa em Java, que leia o nome e a idade de 5 pessoas e
//exiba-os em ordem decrescente no console. É obrigatório o uso de array
import java.util.Scanner;


public class Pratica1 {
    public static void main(String[] args) {
        Scanner leitor = new Scanner(System.in);
        String[] nome = new String[5];
        int[] idade = new int[5];
        for (int i = 0; i < 5; i++) {
            System.out.println("Nome " + (i + 1) + ":");
            nome[i] = leitor.next();
            System.out.println("Digite a idade " + (i + 1) + ":");
            idade[i] = leitor.nextInt();
        }
        for (int i = 0; i < idade.length - 1; i++) {
            for (int j = i + 1; j < idade.length; j++) {
                if (idade[i] < idade[j]) {
                    int tempIdade = idade[i];
                    idade[i] = idade[j];
                    idade[j] = tempIdade;
                    String tempNome = nome[i];
                    nome[i] = nome[j];
                    nome[j] = tempNome;
                }
            }
        }
        for (int i = 0; i < 5; i++) {
            System.out.println("Nome: " + nome[i]);
            System.out.println("Idade: " + idade[i]);
        }
    }
}