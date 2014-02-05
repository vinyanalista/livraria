-- Quaisquer registros que devem ser inseridos no banco de dados durante a instalação da aplicação devem ser colocados aqui

-- Livros

INSERT INTO livro (id, ano, autor, edicao, editora, estoque, genero, isbn, preco, sinopse, titulo) VALUES (10, 2005, 'Bruna Surfistinha', 1, 'Panda Books', 60, 'Biografia', '9788576950172', 27.10, 'Neste livro você vai conhecer detalhes reveladores da menina de classe média alta que trocou os finais de semana com a família no Guarujá para se prostituir aos 17 anos.', 'O Doce Veneno do Escorpião');
INSERT INTO livro (id, ano, autor, edicao, editora, estoque, genero, isbn, preco, sinopse, titulo) VALUES (9, 2007, 'Mario Puzo', 1, 'Bestbolso', 2, 'Romance', '8577990192', 25, 'A mais perfeita reconstituição da Cosa Nostra, a Máfia americana, e do mundo alucinante criado por cinco famílias do mafiosi em guerra em Nova York.', 'O Poderoso Chefão');
INSERT INTO livro (id, ano, autor, edicao, editora, estoque, genero, isbn, preco, sinopse, titulo) VALUES (8, 2004, 'Dan Brown', 1, 'Arqueiro', 1, 'Romance', '8575421468', 23.90, 'Antes de decifrar O Código Da Vinci, Robert Langdon, o famoso professor de simbologia de Harvard, vive sua primeira aventura em Anjos e Demônios, quando tenta impedir que uma antiga sociedade secreta destrua a Cidade do Vaticano.', 'Anjos e Demônios');
INSERT INTO livro (id, ano, autor, edicao, editora, estoque, genero, isbn, preco, sinopse, titulo) VALUES (7, 2004, 'Dan Brown', 1, 'Arqueiro', 20, 'Romance', '8575421131', 23.90, 'Um assassinato dentro do Museu do Louvre, em Paris, traz à tona uma sinistra conspiração para revelar um segredo que foi protegido por uma sociedade secreta desde os tempos de Jesus Cristo.' , 'O Código da Vinci');
INSERT INTO livro (id, ano, autor, edicao, editora, estoque, genero, isbn, preco, sinopse, titulo) VALUES (6, 2013, 'Dan Brown', 1, 'Arqueiro', 20, 'Romance', '9788580411522', 39.90, 'Neste novo e fascinante thriller Dan Brown retoma a mistura magistral de história, arte, códigos e símbolos que o consagrou em O código Da Vinci, Anjos e demônios e O símbolo perdido e faz de Inferno sua aposta mais alta até o momento.', 'Inferno');
INSERT INTO livro (id, ano, autor, edicao, editora, estoque, genero, isbn, preco, sinopse, titulo) VALUES (5, 2006, 'Deitel', 5, 'Prentice Hall', 10, 'Informática', '8576050560', 410, 'A melhor e mais respeitada introdução a C++, programação orientada a objetos (POO) e projeto orientado a objetos (OOD) com UML 2 utilizando o Deitel Live-Code.', 'C ++ Como Programar');
INSERT INTO livro (id, ano, autor, edicao, editora, estoque, genero, isbn, preco, sinopse, titulo) VALUES (4, 2011, 'Andrew S. Tanenbaum', 5, 'Pearson Education', 50, 'Informática', '9788576059240', 170, 'Este livro aborda as redes desenvolvidas a partir de 1990, explicando com elas funcionam internamente. Andrew Tanenbaum apresenta explicações detalhadas e exemplos baseados na Internet para facilitar a compreensão do leitor.', 'Redes de Computadores');
INSERT INTO livro (id, ano, autor, edicao, editora, estoque, genero, isbn, preco, sinopse, titulo) VALUES (3, 2005, 'Sierra Kathy', 1, 'Alta Books', 5, 'Informática', '9788576081739', 139, 'Use a Cabeça Java é uma experiência completa de aprendizado em programação orientada a objetos (OO) e Java.', 'Use a Cabeça Java');
INSERT INTO livro (id, ano, autor, edicao, editora, estoque, genero, isbn, preco, sinopse, titulo) VALUES (2, 2013, 'Ricardo Lecheta', 3, 'Novatec', 30, 'Informática', '9788575223444', 123, 'O Android é a plataforma open-source criada pelo Google para o desenvolvimento de aplicações para dispositivos móveis. É a nova sensação do momento e está revolucionando o desenvolvimento de aplicações.', 'Google Android');
INSERT INTO livro (id, ano, autor, edicao, editora, estoque, genero, isbn, preco, sinopse, titulo) VALUES (1, 2010, 'Deitel', 8, 'Prentice Hall', 10, 'Informática', '9788576055631', 312, 'A oitava edição de Java - como programar, lançada pela Pearson Education, chega ao mercado com novo design e um traço inovador.', 'Java - Como Programar');

-- Endere�os
INSERT INTO endereco (id, bairro, cep, cidade, estado, logradouro, numero) VALUES (1, 'Algum', '49000000', 'Fict�cia', 'Sergipe', 'rua Frei lim�o', '45');

-- Usu�rios
INSERT INTO usuario (id, tipo, cpf, data_nascimento, email, nome, rg, senha, telefone, endereco_id) VALUES (1, 2, '12345678911', '1990/08/04', 'usuario@email.com', 'Usuario', '73284902', 'muito dificil', '7937282920', 1);

-- Clientes
INSERT INTO cliente (id, data_cadastro) VALUES (1, '2013/11/03');

-- Movimenta��es
INSERT INTO movimentacao (id, tipo, data) VALUES (1, 2, '2013/11/03');
INSERT INTO movimentacao (id, tipo, data) VALUES (2, 2, '2013/11/04');
INSERT INTO movimentacao (id, tipo, data) VALUES (3, 2, '2013/11/05');
INSERT INTO movimentacao (id, tipo, data) VALUES (4, 2, '2013/11/06');
INSERT INTO movimentacao (id, tipo, data) VALUES (5, 2, '2013/11/07');


-- Itens de Livros
INSERT INTO itemlivro (id, preco, quantidade, movimentacao_id, livro_id) VALUES (1, 312, 1, 1, 1);
INSERT INTO itemlivro (id, preco, quantidade, movimentacao_id, livro_id) VALUES (2, 246, 2, 1, 2);
INSERT INTO itemlivro (id, preco, quantidade, movimentacao_id, livro_id) VALUES (3, 417, 3, 2, 3);
INSERT INTO itemlivro (id, preco, quantidade, movimentacao_id, livro_id) VALUES (4, 170, 1, 2, 4);
INSERT INTO itemlivro (id, preco, quantidade, movimentacao_id, livro_id) VALUES (5, 410, 1, 3, 5);
INSERT INTO itemlivro (id, preco, quantidade, movimentacao_id, livro_id) VALUES (6, 79.80, 2, 3, 6);
INSERT INTO itemlivro (id, preco, quantidade, movimentacao_id, livro_id) VALUES (7, 71.70, 3, 4, 7);
INSERT INTO itemlivro (id, preco, quantidade, movimentacao_id, livro_id) VALUES (8, 47.80, 2, 4, 8);
INSERT INTO itemlivro (id, preco, quantidade, movimentacao_id, livro_id) VALUES (9, 25, 1, 5, 9);
INSERT INTO itemlivro (id, preco, quantidade, movimentacao_id, livro_id) VALUES (10, 406.50, 15, 5, 10);

-- Pagamentos
INSERT INTO pagamento(id, aprovado, data, tipo) VALUES (1, 'TRUE', '2014/01/02', 3);
INSERT INTO pagamento(id, aprovado, data, tipo) VALUES (2, 'TRUE', '2014/01/03', 1);
INSERT INTO pagamento(id, aprovado, data, tipo) VALUES (3, 'FALSE', '2014/01/04', 5);
INSERT INTO pagamento(id, aprovado, data, tipo) VALUES (4, 'TRUE', '2014/01/05', 2);
INSERT INTO pagamento(id, data, tipo) VALUES (5, '2014/01/06', 1);

-- Vendas
INSERT INTO venda(id, cliente_id, pagamento_id) VALUES (1, 1, 1);
INSERT INTO venda(id, cliente_id, pagamento_id) VALUES (2, 1, 2);
INSERT INTO venda(id, cliente_id, pagamento_id) VALUES (3, 1, 3);
INSERT INTO venda(id, cliente_id, pagamento_id) VALUES (4, 1, 4);
INSERT INTO venda(id, cliente_id, pagamento_id) VALUES (5, 1, 5);
