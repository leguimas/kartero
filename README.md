# kartero

O **kartero** é uma simplíssima aplicação em JAVA feita para expôr na WEB, através de uma API REST, a consulta à base de CEPs dos Correios. Cansado de precisar implementar mais de uma vez, em trabalhos diferentes, a mesma solução, resolvi criar essa aplicação WEB e deixá-la pública para quem quiser utilizá-la e, principalmente, para quem quiser evoluí-la.

### **ATENÇÃO**

* O **kartero** é apenas a aplicação WEB, ou seja, não vem junto com a base de dados dos Correios. Para ter a solução completa, você deverá adquirir os dados junto à instituição, seguindo todas as normas legais necessárias.
* O **kartero** foi implementado em poucas horas. Sendo assim, foi feito o extritamente necessário para a aplicação funcionar. Isso significa que infinitas melhorias podem ser feitas e você está mais do que convidado a participar dessa evolução.

### Serviços REST

Atualmente, existe um único serviço REST disponível através da aplicação. Trata-se de um GET para a obtenção do endereço completo de um CEP. Pro que a aplicação foi criada, é mais do que o suficiente! :) Na aplicação WEB tem uma documentação simples mas com mais informações sobre o serviço.

```
http://host.doseu.servidor/aplicacao/api/cep/<CEP-DESEJADO>
```

### **QUERO USAR!**

A utilização do **kartero** é bastante simples. Tudo o que você vai precisar de infraestrutura é um servidor JAVA qualquer e qualquer base de dados populada e dentro da estrutura dos Correios. Providenciado esses dois pontos, siga os seguintes passos:

1. Instale o servidor JAVA desejado.
2. Faça um clone do projeto e execute a build via maven.
3. No servidor instalado, disponibilize o driver JDBC para conexão com a base de dados.
4. Crie, em um local acessível pela aplicação, um arquivo chamado *kartero.database* com o seguinte contéudo:

```
kartero.classeJDBC=[CLASSE_JDBC_A_SER_UTILIZADA]
kartero.urlJDBC=[URL_PARA_CONEXAO_JDBC]
kartero.usuario=[USUARIO_PARA_CONEXAO_JDBC]
kartero.senha=[SENHA_PARA_CONEXAO_JDBC]
kartero.minimoDeConexoes=[NUMERO_MINIMO_DE_CONEXOES_COM_BANCO_DE_DADOS]
kartero.maximoDeConexoes=[NUMERO_MAXIMO_DE_CONEXOES_COM_BANCO_DE_DADOS]
```

### Dúvidas?

Se você tiver qualquer dúvida, o código tá aberto pra ser fuçado! :) Ou então, se preferir, é só me chamar no [twitter](twitter.com/leguimas) que trocamos uma ideia. Beleza? Enjoy it!
