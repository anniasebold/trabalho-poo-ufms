 ## 📋 Sobre
  Esse projeto foi desenvolvido na disciplina de Linguagem de Programação Orientada a Objetos.
  Ele consiste em criar um sistema para uma academia fictícia chamada _GymFit_ com CRUDS de 4 entidades diferentes, sendo elas:
  - Modalidade
  - Aluno
  - Instrutor
  - Equipamento

  Ao utilizar o sistema é possível fazer um CRUD de cada uma delas e relacioná-las com outras entidades através de relações 1:1, 1:N e N:N.

  No caso de relação N:N (Modalidade -> Equipamento) existe uma tela só para o cadastro e visualização desse relacionamento já que se trata de uma nova tabela no Banco de Dados.

## 💾 Como utilizar 

Primeiramente clone o projeto
      
    git clone git@github.com:anniasebold/trabalho-poo-ufms.git

Depois mude a URL em src/connection/conexao para a URL do banco na sua máquina, que está na pasta database, por exemplo:

    jdbc:sqlite:/home/anniasebold/Desktop/GymFit/database/GymFit.db

Depois rode o projeto em alguma IDE (preferencialmente Eclipse).

Após isso você verá o projeto já conectado com o Banco de Dados.
