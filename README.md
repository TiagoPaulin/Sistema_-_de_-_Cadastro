# Sistema_-_de_-_Cadastro
Utilizar os conceitos de Orientação a Objetos, Java.io, Streams, lambdas e exceções para criar um sistema de cadastro de usuários via terminal.

## O sistema funciona da seguinte forma:
* No inicio da execução é criado um arquivo .txt "formulario.txt" com 4  perguntas iniciais e um diretório "CADASTROS" vazio.
* Em seguida é exibido um menu com as seguintes opções:
1. Cadastrar Usuário
2. Listar usuários
3. Cadastrar pergunta
4. Deletar Pergunta
5. Buscar usuário por nome, email ou idade

* O cadastro de usuário irá ler as perguntas do arquivo "formulario.txt" e exibir no terminal, cada pergunta exibida aguardará a resposta do usuário,
quando o usuário terminar de responder o questionário saré criado dentro do diretório "CADASTROS" um arquivo "1-NOMEDOUSUARIO.txt" que irá armazenar todas as informações fornecidas pelo usuário

* O listar usuários irá acessar o diretório "CADASTROS" e seus aquivos e listaráo nome de todos os usuários que foram cadastrados no sistema

* O cadastrar pergunta irá solicitar ao usuário que digite uma nova pergunta e a pergunta deverá ser adicionada ao arquivo "formulario.txt", o proximo ususario a se cadastrar tera que responder essa pergunta

* O deletar pergunta lista todas as perguntas e solicita que o usuário entre com o índice de uma das perguntas cadastradas e a pergunta selecionada sera apagada do "formulario.txt"

* O buscar pergunta ao usuario de que forma ele deseja realizar a pesquisa e quando ele entra com a informação o sistema retorna todas as informações dos cadastros que atendem a busca

## Regras
* o sistema deve possuir as seguintes validações
- O nome do usuário deve ter no minimo 10 caracteres
- o email deve conter "@"
- o usuário nao pode ser cadastrado se tiver menos de 18 anos
- a entrada de altura deve utilizar , para separar a casa dos metros da dos centímetros (ex: 1,80m)
- Não é possível cadastrar um usuário com um email que já está no sistema
- Não será possivel deletar as 4 primeiras perguntas 
*  A busca deve retornar os resultados de todos os usuários, seja por parte do nome ou o termo inteiro, exemplo:
Cadastrados : Lucas Almeida, Luca De Sá, Luc Montavão.
Caso o usuário digite “Luca”, deverá retornar somente o primeiro e o segundo com suas devidas informações.