CREATE TABLE `stage`.`FAQCategoria`
    (
        `idCategoria` INT NOT NULL AUTO_INCREMENT,
        `descricao` VARCHAR (100) NOT NULL,
        `dataAtualizacao` DATE NOT NULL,
    PRIMARY KEY
        (`idCategoria`),
    UNIQUE INDEX `idCategoria_UNIQUE`
        (`idCategoria` ASC));

INSERT INTO stage.FAQCategoria (descricao, dataAtualizacao) VALUES ('Adesivo', now());
INSERT INTO stage.FAQCategoria (descricao, dataAtualizacao) VALUES ('Relógio', now());
INSERT INTO stage.FAQCategoria (descricao, dataAtualizacao) VALUES ('Pulseira', now());
INSERT INTO stage.FAQCategoria (descricao, dataAtualizacao) VALUES ('Produtos', now());
INSERT INTO stage.FAQCategoria (descricao, dataAtualizacao) VALUES ('Sua Conta', now());
INSERT INTO stage.FAQCategoria (descricao, dataAtualizacao) VALUES ('Pagamento', now());
INSERT INTO stage.FAQCategoria (descricao, dataAtualizacao) VALUES ('Atendimento', now());
INSERT INTO stage.FAQCategoria (descricao, dataAtualizacao) VALUES ('Uso em pedágios', now());
INSERT INTO stage.FAQCategoria (descricao, dataAtualizacao) VALUES ('Uso em estacionamentos', now());
INSERT INTO stage.FAQCategoria (descricao, dataAtualizacao) VALUES ('Postos de combustível', now());
INSERT INTO stage.FAQCategoria (descricao, dataAtualizacao) VALUES ('Contratação', now());
INSERT INTO stage.FAQCategoria (descricao, dataAtualizacao) VALUES ('APP', now());
SELECT * FROM stage.FAQCategoria;