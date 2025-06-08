# Classe Corretor
class Corretor:
    def __init__(self, nome, creci, experiencia):
        self.nome = nome
        self.creci = creci
        self.experiencia = experiencia  # Anos de experiência

    def __str__(self):
        return f"Corretor {self.nome}, CRECI: {self.creci}, Experiência: {self.experiencia} anos"

# Classe Cliente
class Cliente:
    def __init__(self, nome, telefone, orcamento):
        self.nome = nome
        self.telefone = telefone
        self.orcamento = orcamento  # Orçamento disponível para compra

    def __str__(self):
        return f"Cliente {self.nome}, Telefone: {self.telefone}, Orçamento: R$ {self.orcamento:.2f}"

# Criando e exibindo instâncias das classes
corretor1 = Corretor("Marcos Silva", "12345-SP", 10)
corretor2 = Corretor("Ana Souza", "67890-RJ", 5)

cliente1 = Cliente("João Pereira", "(11) 99999-8888", 300000)
cliente2 = Cliente("Maria Fernanda", "(21) 98888-7777", 500000)

# Exibindo os objetos
print(corretor1)
print(corretor2)
print(cliente1)
print(cliente2)
