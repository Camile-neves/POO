class ObjetoSobrepostoException(Exception):
    def __init__(self, mensagem):
        super().__init__(mensagem)
class Ponto2D:
    def __init__(self, x: int, y: int):
        self.x = x
        self.y = y
class Reta:
    def __init__(self, a: float, b: float):
        self.a = a
        self.b = b

    def intercepta(self, r):
        # Verifica se coeficientes angulares são iguais
        return self.a != r.a

    def estaNaReta(self, p: 'Ponto2D'):
        return p.y == self.a * p.x + self.b

class EspacoGeometrico:
    def __init__(self):
        self.pontos = []
        self.retas = []

    def adicionarReta(self, reta: Reta):
        for r in self.retas:
            if not r.intercepta(reta):
                raise ObjetoSobrepostoException("Reta sobreposta a uma já existente.")
        self.retas.append(reta)

    def adicionarPonto(self, ponto: Ponto2D):
        for r in self.retas:
            if r.estaNaReta(ponto):
                raise ObjetoSobrepostoException(f"Ponto ({ponto.x},{ponto.y}) está sobre uma reta.")
        self.pontos.append(ponto)

def main():
    espaco = EspacoGeometrico()

    try:
        # Retas: y = x + 2, y = x + 1, y = 2x - 2
        r1 = Reta(1, 2)
        r2 = Reta(1, 1)
        r3 = Reta(2, -2)

        espaco.adicionarReta(r1)
        espaco.adicionarReta(r2)
        espaco.adicionarReta(r3)

        # Pontos: (2,3), (3,4), (4,6)
        p1 = Ponto2D(2, 3)
        p2 = Ponto2D(3, 4)
        p3 = Ponto2D(4, 6)
        p4 = Ponto2D(3, 2)  # ponto que não está em nenhuma reta

        espaco.adicionarPonto(p4)  # este pode ser adicionado

        # Esses lançarão exceção
        espaco.adicionarPonto(p1)
        espaco.adicionarPonto(p2)
        espaco.adicionarPonto(p3)

    except ObjetoSobrepostoException as e:
        print("Erro:", e)

if __name__ == "__main__":
    main()