
INSERT INTO `veiculo` (`ano`, `combustivel`, `cor`, `marca`, `modelo`, `placa`, `renavan`, `versao`) VALUES ('2012', 'FLEX', 'Branco', 'FIAT', 'Palio ELX Fire', 'KOY-2241', 'RJ12345665454', 'Sedan');

INSERT INTO `veiculo` (`ano`, `combustivel`, `cor`, `marca`, `modelo`, `placa`, `renavan`, `versao`) VALUES ('2017', 'FLEX', 'Azul', 'FIAT', 'Palio ELX Fire', 'KOY-1441', 'RJ12345665454', 'Sedan');

INSERT INTO `veiculo` (`ano`, `combustivel`, `cor`, `marca`, `modelo`, `placa`, `renavan`, `versao`) VALUES ('2016', 'GASOLINA', 'Preta', 'HONDA', 'CG 125', 'SSL-2241', 'RJ12345665454', 'Sedan');

insert into instrutor (nome, credencial, validade_credencial) values('Marquinho de Jesus', 'AB-334455', str_to_date('2018-12-20', '%Y-%m-%d'));

insert into instrutor (nome, credencial, validade_credencial) values('Renata Estrela', 'AB-55588877', str_to_date('2018-12-20', '%Y-%m-%d'));

insert into instrutor (nome, credencial, validade_credencial) values('Ze das coves', 'AB-334455', str_to_date('2018-12-20', '%Y-%m-%d'));

insert into aluno( nome, pai, mae, endereco, numero, complemento, bairro, cidade, cep, uf, data_nacimento, identidade, orgao_emissor, data_expedicao, cpf, email, contato1, contato2, contato3, cadastrado_em, path_foto ) 
values ('Leonardo Lopes Pontes', 'Jose Agostinho Pontes Filho', 'Valdileia Lopes Pontes', 'Rua Cesar Machado', 105, 'casa', 'Pacheco', 'Sao Goncalo', '24736-180', 'RJ', str_to_date('2018-12-20', '%Y-%m-%d'), '12327093-6', 'DIC/RJ', str_to_date('2018-12-20', '%Y-%m-%d'), '088.470.917-56', 'lopespontesleonardo@gmail.com', '(21) 2622-3311', '', '', now(), null );
insert into aluno( nome, pai, mae, endereco, numero, complemento, bairro, cidade, cep, uf, data_nacimento, identidade, orgao_emissor, data_expedicao, cpf, email, contato1, contato2, contato3, cadastrado_em, path_foto ) 
values ('Renata Estrela da Motta Pontes', 'Jose Agostinho Pontes Filho', 'Valdileia Lopes Pontes', 'Rua Cesar Machado', 105, 'casa', 'Pacheco', 'Sao Goncalo', '24736-180', 'RJ', str_to_date('2018-12-20', '%Y-%m-%d'), '12327093-6', 'DIC/RJ', str_to_date('2018-12-20', '%Y-%m-%d'), '088.470.917-56', 'lopespontesleonardo@gmail.com', '(21) 2622-3311', '', '', now(), null );
insert into aluno( nome, pai, mae, endereco, numero, complemento, bairro, cidade, cep, uf, data_nacimento, identidade, orgao_emissor, data_expedicao, cpf, email, contato1, contato2, contato3, cadastrado_em, path_foto ) 
values ('Laura Estrela Pontes', 'Jose Agostinho Pontes Filho', 'Valdileia Lopes Pontes', 'Rua Cesar Machado', 105, 'casa', 'Pacheco', 'Sao Goncalo', '24736-180', 'RJ', str_to_date('2018-12-20', '%Y-%m-%d'), '12327093-6', 'DIC/RJ', str_to_date('2018-12-20', '%Y-%m-%d'), '088.470.917-56', 'lopespontesleonardo@gmail.com', '(21) 2622-3311', '', '', now(), null );

insert into processo (	renach,	servico,	categoria,	data_inicio,	data_termino,	observacao,	aluno_id) values( 'RJ-223423424', 'PRIMEIRA_HABILITACAO', 'A', now(), null, '', 1);
	
insert into processo (	renach,	servico,	categoria,	data_inicio,	data_termino,	observacao,	aluno_id) values( 'RJ-77445511', 'PRIMEIRA_HABILITACAO', 'A', now(), null, '', 2);
	
insert into processo (	renach,	servico,	categoria,	data_inicio,	data_termino,	observacao,	aluno_id) values( 'RJ-78945151', 'PRIMEIRA_HABILITACAO', 'A', now(), null, '', 3);	

insert into permissao (role_name) values('ROLE_USER');
insert into permissao (role_name) values('ROLE_ADMIN');

insert into usuario (username, name, password, enabled, provisional_password) values('user1', 'Atendente 1', '$2a$10$Jb0NMns70RwRvOY8LJ9/XO8NuT5sswALRCOGEKHQdwjqPLtkgx8w6', 1, 1);
insert into usuario (username, name, password, enabled, provisional_password) values('user2', 'Atendente 2', '$2a$10$Jb0NMns70RwRvOY8LJ9/XO8NuT5sswALRCOGEKHQdwjqPLtkgx8w6', 1, 1);

insert into usuario_permissao(usuario_id, permissao_id) values(1, 1);
insert into usuario_permissao(usuario_id, permissao_id) values(2, 2);

INSERT INTO `aluno` (`aluno_id`, `bairro`, `cadastrado_em`, `cep`, `cidade`, `complemento`, `contato1`, `contato2`, `contato3`, `cpf`, `data_expedicao`, `data_nacimento`, `email`, `endereco`, `identidade`, `mae`, `nome`, `numero`, `orgao_emissor`, `pai`, `path_foto`, `uf`) VALUES ('1', 'Pacheco', now(), '24736-180', 'Sao goncalo', 'casa', '(21) 2233-2222', '(21) 9752828632', '', '088.470.917-56', now(), now(), 'lopespontesleonardo@gmail.com', 'Rua cesar machado', '123270936', 'valdileia', 'Leonardo Lopes Pontes', '120', 'DIC', 'Jose', NULL, 'RJ')

INSERT INTO `aluno` (`aluno_id`, `bairro`, `cadastrado_em`, `cep`, `cidade`, `complemento`, `contato1`, `contato2`, `contato3`, `cpf`, `data_expedicao`, `data_nacimento`, `email`, `endereco`, `identidade`, `mae`, `nome`, `numero`, `orgao_emissor`, `pai`, `path_foto`, `uf`) VALUES ('2', 'Pacheco', now(), '24736-180', 'Sao goncalo', 'casa', '(21) 2233-2222', '(21) 9752828632', '', '744.666.123-44', now(), now(), 'lopespontesleonardo@gmail.com', 'Rua cesar machado', '123270936', 'valdileia', 'Ronaldo Gaucho', '120', 'DIC', 'Jose', NULL, 'RJ');

INSERT INTO `aluno` (`aluno_id`, `bairro`, `cadastrado_em`, `cep`, `cidade`, `complemento`, `contato1`, `contato2`, `contato3`, `cpf`, `data_expedicao`, `data_nacimento`, `email`, `endereco`, `identidade`, `mae`, `nome`, `numero`, `orgao_emissor`, `pai`, `path_foto`, `uf`) VALUES ('3', 'Pacheco', now(), '24736-180', 'Sao goncalo', 'casa', '(21) 2233-2222', '(21) 9752828632', '', '666.222.222-77', now(), now(), 'lopespontesleonardo@gmail.com', 'Rua cesar machado', '123270936', 'valdileia', 'Mauro Galvao', '120', 'DIC', 'Jose', NULL, 'RJ');

INSERT INTO `aluno` (`aluno_id`, `bairro`, `cadastrado_em`, `cep`, `cidade`, `complemento`, `contato1`, `contato2`, `contato3`, `cpf`, `data_expedicao`, `data_nacimento`, `email`, `endereco`, `identidade`, `mae`, `nome`, `numero`, `orgao_emissor`, `pai`, `path_foto`, `uf`) VALUES ('4', 'Pacheco', now(), '24736-180', 'Sao goncalo', 'casa', '(21) 2233-2222', '(21) 9752828632', '', '678.098.543-77', now(), now(), 'lopespontesleonardo@gmail.com', 'Rua cesar machado', '123270936', 'valdileia', 'Mario Estrela', '120', 'DIC', 'Jose', NULL, 'RJ');

commit;