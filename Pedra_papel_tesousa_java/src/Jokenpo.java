import java.util.HashMap;
import java.util.Random;

public class Jokenpo {
    private String jogadas[] = {"pedra","spock","papel","lagarto","tesoura"};
    private HashMap<Integer, Integer> euristica;
    private int jogadaAtual;
    private int contJogadas;
    private int qtdVitorias;
    private int qtdDerrotas;

    Jokenpo(){
        this.inicializarEuristica();
        this.jogadaAtual = 0;
        this.contJogadas = 0;
        this.qtdVitorias = 0;
        this.qtdDerrotas = 0;
    }

    private void inicializarEuristica(){
        euristica = new HashMap<>();
        for(int i = 0; i < 5; i++)
            euristica.put(i,0);
    }

    public int jogar(){
        this.jogadaEuristica();
        System.out.println("\nSua jogada foi: " + this.jogadas[this.jogadaAtual].toString());
        return this.jogadaAtual;
    }

    private void jogadaEuristica(){
        HashMap<Integer,Integer> probValues = new HashMap<>();
        int cont = 0;
        for(int i = 0; i < 5; i++){
            cont += this.euristica.get(i) + 1;
            probValues.put(i,cont);
        }
        int probNum = new Random().nextInt(cont) + 1;
        for(int i = 0; i < 5; i++){
            if(probNum <= probValues.get(i)){
                this.jogadaAtual = i;
                break;
            }
        }
    }

    public void atualizarEuristica(int jogada){
        for(int i = 0; i < 2; i++){
            int jogadaFavorecida = (jogada + 1 + i)%5;
            int numJogada = this.euristica.get(jogadaFavorecida);
            this.euristica.put(jogadaFavorecida,numJogada+1);
        }
    }

    public void jogadaOponente(int jogadaOponente){
        String suaJogadaNome = this.jogadas[this.jogadaAtual];
        String jogadaOponenteNome = this.jogadas[jogadaOponente];
        System.out.println("\nVoce\t|\tOponente\n" + suaJogadaNome + "\t|\t" + jogadaOponenteNome);
        int dif = (this.jogadaAtual - jogadaOponente)%5;
        if(dif < 0)
            dif+=5;
        if(dif > 2){
            System.out.println("Voce Perdeu :(");
            this.qtdDerrotas++;
        }
        else if(dif == 0)
            System.out.println("Empate!");
        else{
            System.out.println("Voce Ganhou!!");
            this.qtdVitorias++;
        }
    }

    public void resultado(){
        System.out.println("\nApos 15 jogadas, aqui estao os seus indices:\n");
        System.out.println("Vit\t|\tDer\t|\tEmp");
        System.out.print(String.valueOf(this.qtdVitorias)+"\t|\t"+String.valueOf(this.qtdDerrotas)+"\t|\t");
        System.out.println(String.valueOf(15-this.qtdDerrotas-this.qtdVitorias)+"\n");
    }
}
