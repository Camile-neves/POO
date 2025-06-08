from abc import ABC, abstractmethod

class Pessoa(ABC):
    def __init__(self, nome):
        self.nome = nome

class Aluno(Pessoa):
    def __init__(self, nome, notas):
        super().__init__(nome)
        self.notas = notas

    def calcular_media(self):
        return sum(self.notas) / len(self.notas)

    def __repr__(self):
        media = self.calcular_media()
        status = "Aprovado" if media >= 7 else "Exame"
        return f"Aluno: {self.nome}, Notas: {self.notas}, Média: {media:.2f}, Status: {status}"

class Professor(Pessoa):
    def __init__(self, nome, salario):
        super().__init__(nome)
        self.salario = salario

    def __repr__(self):
        return f"Professor: {self.nome}, Salário: R${self.salario:.2f}"

if __name__ == "__main__":
    professores = [
        Professor("Carlos", 5000),
        Professor("Ana", 5200)
    ]

    alunos = [
        Aluno("João", [8, 7, 9]),
        Aluno("Maria", [6, 5, 7]),
        Aluno("Pedro", [4, 6, 5]),
        Aluno("Luana", [9, 8, 10]),
        Aluno("Bruno", [5, 6, 5])
    ]

    for prof in professores:
        print(prof)

    for aluno in alunos:
        print(aluno)
