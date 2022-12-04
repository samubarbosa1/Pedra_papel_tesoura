import socket

class Client:
    def __init__(self, HOST, PORT):
        self.HOST = HOST
        self.PORT = PORT
        self.soc = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
        self.soc.connect((self.HOST, self.PORT))

    def enviar(self, mensagem):
        self.soc.sendall(mensagem)
        print(f'Enviando para o Servidor\n')
    
    def receber(self):
        print("Recebendo do servidor...\n")
        data = self.soc.recv(1024)
        return data