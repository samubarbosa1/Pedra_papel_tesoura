public class Main {
    public static void main(String[] args) {
        int PORT = 3322;
        Server servidor = new Server(PORT);
        Jokenpo game = new Jokenpo();
        int cont = 0;
        while (cont < 15){
            int jogada = game.jogar();
            int Oponente = servidor.receber() - 48;
            game.jogadaOponente(Oponente);
            game.atualizarEuristica(Oponente);
            servidor.enviar(jogada);
            cont++;
        }
        game.resultado();
    }
}