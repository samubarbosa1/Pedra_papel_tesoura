from random import randint


class JokenpoGame:
    def __init__(self):
        self.jogadas = ["pedra","spock","papel","lagarto","tesoura"]
        self.euristica = {0:0,1:0,2:0,3:0,4:0}
        self.jogadaAtual = 0
        self.contJogadas = 0
        self.qtdVitorias = 0
        self.qtdDerrotas = 0

    def jogar(self):
        self.contJogadas += 1
        if(self.contJogadas < 6):
            self.jogadaAtual = randint(0,4)
        else:
            self.jogadaEuristica()
        print(f"\nSua jogada foi: {self.jogadas[self.jogadaAtual]}\n")
        return self.jogadaAtual
        

    def jogadaEuristica(self):
        probValues = {}
        cont = 0
        for key, value in self.euristica.items():
            cont+=value+1
            probValues.update({key:cont})
        probNum = randint(1,cont)
        for key, value in probValues.items():
            if probNum <= value:
                self.jogadaAtual = key
                break    

    def atualizarEuristica(self, jogada):
        for i in range(2):
            jogadaFavorecida = (jogada+ 1 + i)%5
            numJogada = self.euristica.get(jogadaFavorecida)
            self.euristica.update({jogadaFavorecida:numJogada+1})

    def jogadaOponente(self, jogadaOponente):
        suaJogadaNome = self.jogadas[self.jogadaAtual]
        jogadaOponenteNome = self.jogadas[jogadaOponente]
        print(f"Você\t|\tOponente\n{suaJogadaNome}\t|\t{jogadaOponenteNome}\n")
        dif = (self.jogadaAtual - jogadaOponente)%5
        if(dif > 2):
            print("Você Perdeu :(\n")
            self.qtdDerrotas += 1
        elif(dif == 0):
            print("Empate!\n")
        else:
            print("Você Ganhou!!\n")
            self.qtdVitorias += 1

    def resultado(self):
        print("\nApós 15 jogadas, aqui estão os seus índices:\n")
        print("Vit\t|\tDer\t|\tEmp")
        print(f"{self.qtdVitorias}\t|\t{self.qtdDerrotas}\t|\t{15-self.qtdDerrotas-self.qtdVitorias}\n")
            
            
