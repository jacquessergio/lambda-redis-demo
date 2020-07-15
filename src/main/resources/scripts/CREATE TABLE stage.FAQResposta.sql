CREATE TABLE `stage`.`FAQResposta` (
        `idResposta` INT NOT NULL AUTO_INCREMENT,
        `idPergunta` INT NOT NULL,
        `idCategoria` INT NOT NULL,
        `descricao` TEXT NOT NULL,
        `dataAtualizacao` DATE NOT NULL,
    PRIMARY KEY (`idResposta`),
    UNIQUE INDEX `idFAQResposta_UNIQUE` (`idResposta` ASC),
    INDEX `idPergunta_idx` (`idPergunta` ASC),
        CONSTRAINT `idPergunta`
    FOREIGN KEY (`idPergunta`)
        REFERENCES `stage`.`FAQPergunta` (`idPergunta`)
        ON DELETE NO ACTION
        ON UPDATE NO ACTION);

INSERT INTO stage.FAQResposta (idPergunta, idCategoria, descricao, dataAtualizacao) VALUES (1, 1, 'Exemplo Resposta pra remover o adesivo', now());
SELECT * FROM stage.FAQResposta;