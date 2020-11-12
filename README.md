# AnaliseDeDadosDeVenda
Criar um sistema de análise de dados de venda que irá importar lotes de arquivos e produzir um relatório baseado em informações presentes no mesmo. Existem 3 tipos de dados dentro dos arquivos e eles podem ser distinguidos pelo seu identificador que estará presente na primeira coluna de cada linha, onde o separador de colunas é o caractere “ç”.


### Requisitos
Para compilar e rodar está aplicação você precisa:
* [Lombok](https://projectlombok.org/download)

* [Java SE 11](https://www.oracle.com/java/technologies/javase/jdk11-archive-downloads.html)

* [Maven](https://maven.apache.org/download.cgi)

### Clonar o projeto
Você pode clonar este repositório, pelo git usando:
```
https://github.com/Marcelocs19/AnaliseDeDadosDeVenda
```

### Rodar a aplicação localmente
Criar estas pastas "HOMEPATH\data", e colocar duas pastas chamadas "in" e "out" no diretório Desktop. Dentro da pasta "in" colocar arquivos .txt como por exemplo:
```
001ç65275832060çPedroç50000
001ç34240297001çPauloç40000.99
002ç15980209000136çJose da SilvaçRural
002ç52620600000156çEduardo PereiraçRural
003ç10ç[1-10-100,2-30-2.50,3-40-3.10]çPedro
003ç08ç[1-34-10,2-33-1.50,3-40-0.10]çPaulo
```
Dados do vendedor
Os dados do vendedor possuem o identificador 001 e seguem o seguinte formato:
001çCPFçNameçSalary

Dados do cliente
Os dados do cliente possuem o identificador 002 e seguem o seguinte formato:
002çCNPJçNameçBusiness Area

Dados de venda
Os dados de venda possuem o identificador 003 e seguem o seguinte formato:
003çSale IDç[Item ID-Item Quantity-Item Price]çSalesman name

Existem varias jeitos para rodar uma aplicação Java no seu computador. Um jeito é executar a classe main App pela sua IDE.

Ou você pode rodar a aplicação pela linha de comando usando:

```
java -jar relatorio.jar
```


## License

This project is licensed under the MIT License - see the [LICENSE.md](LICENSE.md) file for details
