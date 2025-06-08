package dados;
import java.util.List;
import java.util.Random;
import java.util.Collections;
import java.util.LinkedList;

public abstract class Gerador implements ISequencia{

    protected List<Integer> sequencia = new LinkedList<Integer>();

    public abstract void gerar(int n);

    public int sortear(){
        Random r = new Random();
        return sequencia.get(r.nextInt(sequencia.size()));
    }
    public long somatorio(){
        long soma=0;
        for(long num : sequencia){
            soma+=num;
        }
        return soma;
    }
    public long produtorio(){
        long produto=1;
        for(long num : sequencia){
            if(num>0){
                produto*=num;
            }
        }
        return produto;
    }
    public double mediaGeometrica(){
        double resultado;
        resultado=Math.pow(produtorio(), 1.0/(double)sequencia.size());
        return resultado;
    }
    public double mediaAritmetica(){
        return somatorio()/(double)sequencia.size();
    }
    public double variancia(){
        double media=mediaAritmetica();
        double soma=0;
        for(int num: sequencia){
            soma+=Math.pow(num-media,2);
        }
        return soma/(double)sequencia.size();
    }
    public double desvioPadrao(){
        return Math.sqrt(variancia());
    }
    public int amplitude(){
        return Collections.max(sequencia)- Collections.min(sequencia);
    }
    public List<Integer> getSequencia() {
        return this.sequencia;
    }
}
