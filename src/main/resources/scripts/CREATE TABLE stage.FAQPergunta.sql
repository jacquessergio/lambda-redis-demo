CREATE TABLE `stage`.`FAQPergunta` (
        `idPergunta` INT NOT NULL AUTO_INCREMENT,
        `idCategoria` INT NOT NULL,
        `descricao` VARCHAR(150) NOT NULL,
        `dataAtualizacao` DATE NOT NULL,
    PRIMARY KEY (`idPergunta`),
    UNIQUE INDEX `idFAQPergunta_UNIQUE` (`idPergunta` ASC),
    INDEX `idCategoria_idx` (`idCategoria` ASC),
        CONSTRAINT `idCategoria`
    FOREIGN KEY (`idCategoria`)
        REFERENCES `stage`.`FAQCategoria` (`idCategoria`)
        ON DELETE NO ACTION
        ON UPDATE NO ACTION);
        
INSERT INTO stage.FAQPergunta (idCategoria, descricao, dataAtualizacao) VALUES (1, 'Como remover o Adesivo?', now()); 
SELECT * FROM stage.FAQPergunta;