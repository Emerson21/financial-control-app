Financial Control App

- Abertura da conta corrente:
    - Deverá ter no mínimo R$ 50,00 reais para realizar a abertura da conta corrente;
    - Esse valor de depósito inicial deverá ser contabilizado como saldo após a abertura da conta corrente;
        * Lançamento do tipo crédito sendo identificado como Deposito Inicial.



- Funcionalidade: Transferência de valores
    - O sistema deverá aceitar receber o pagamento do salário via transferencia bancaria do tipo DOC / TED ou Pix.
    - O valor depositado deverá constar como um lançamento do tipo crédito na conta do correntista que recebeu o valor.
    - O valor transferido deverá constar como um lançamento do tipo débito na conta do correntista que enviou o valor.
    - Não deverá deixar realizar a transferencia caso o valor depositado seja menor que o saldo disponível na conta, lançando uma excecao de saldo indisponivel.
    - Transferencias do tipo DOC deverao ser processadas 1x ao dia.
    - Transferencias do tipo TED deverão ser processadas a cada 5 min.
    - Transferencias via PIX deverão ser processadas on-line.
        
    Informações a constar no extrato:
        Tipo: Débito ou Crédito
        Tipo Transferencia: DOC, TED ou PIX
        Descricao: Descritivo da transferencia
        Valor: Valor da tranferência
        Data: data e hora da transfer6encia
    
  
- Resource de consulta de saldo do correntista / conta corrente;

**************************************************************************************************************************************

 
 - [X] Inicialmente, a aplicação permitirá criar apenas uma instituição financeira, com vários cartões, 
sendo de débito ou de crédito.

 - [X] O cliente para possuir um cartão, seja de débito ou crédito, precisará antes possuir uma conta corrente,
utilizando de uma conta corrente para um cartão de débito, onde o valor será debitado do valor disponível em conta,
não terá cheque especial não podendo ter valores negativos.

 - [ ] Fazendo-se utilização do cartão de crédito, deverá ser disponibilizado de um limite para esse cartão, 
se o cliente pagar apenas uma parte do valor, qual será o valor da próxima fatura? qual a taxa de juros a ser cobrada 
para pagamento parcial da fatura (dívida) e somando-se aos próximos valores das parcelas seguintes?

 - [ ] Todo cartão deverá possuir uma senha ou um mecanismo de validação para efetuar as transações sendo na função débito ou crédito,
e deverá validar antes de aceitar as transações se possui valor disponível na conta de débito, se possui limite disponível na conta de 
crédito? Se o cartão for somente de crédito e chegar uma transação na função de débito ou vice-versa?
Deverá receber os lançamentos por meio de notificações, identificará pelo número do cartão o usuario e atrelará esse lançamento ao usuário, e assim poderá
criar relatórios intelegintes exibindo o total de ganhos, total de gastos, os gastos terão que ser classificados por tipos para que seja visível onde o usuário
está gastando mais.

 - [ ] Depois evoluirá para poder cadastrar múltiplas instituições financeiras, contas de investimentos, de acordo com o comportamento de gastos sugerir leituras
sobre educação financeira, canais no youtube sobre investimentos, sugerindo que o usuário consuma esses conteúdos e tenha uma vida financeira mais
tranquila e sempre olhando os gastos e ganhos.

 - [ ] Poderá criar de orçamentos para cada tipo de despesa, investimentos, para educação.

 - [ ] Aplicacao deverá conter um login e senha para controlar o acesso aos relatorios e lançamentos de débito ou crédito de acordo com
 cada instituição financeira criada para cadastro como também cada cartão de crédito cadastrado.


*****************************************************************************************************************************************************

<h3>Disponibilizar uma API para poder ser chamada e realizar transações bancárias</h3>

* Modelar uma API para ter como base para o desenvolvimento utilizando-se o Swagger e especificação OpenAPI;
* Nessa API as informações a serem passadas deverão ser uma conta de origem, uma conta de destino o valor e o tipo da transação
sendo DOC, TED ou PIX; 























