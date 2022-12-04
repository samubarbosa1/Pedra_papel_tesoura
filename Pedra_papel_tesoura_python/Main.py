from Client import Client
from JokenpoGame import JokenpoGame


if __name__ == "__main__":
    
    HOST, PORT = "172.15.0.79", 3322
    socket = Client(HOST, PORT)
    game = JokenpoGame()

    qtdJogadas = 0
    while qtdJogadas < 15:
        jogada = game.jogar()
        socket.enviar(bytes(str(jogada), 'utf-8'))
        oponente =  int.from_bytes(socket.receber(), "big")
        game.jogadaOponente(oponente)
        game.atualizarEuristica(oponente)
        qtdJogadas += 1
    game.resultado()