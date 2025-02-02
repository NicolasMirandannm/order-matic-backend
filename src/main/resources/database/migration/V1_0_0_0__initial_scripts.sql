CREATE TABLE endereco (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    rua VARCHAR(255) NOT NULL,
    numero INTEGER NOT NULL,
    cidade VARCHAR(255) NOT NULL,
    estado VARCHAR(100) NOT NULL,
    cep VARCHAR(20) NOT NULL,
    complemento VARCHAR(255),
    principal BOOLEAN NOT NULL,

    apartamento_numero VARCHAR(50),
    apartamento_bloco VARCHAR(50),
    apartamento_andar VARCHAR(50),
    apartamento_complemento VARCHAR(255),

    condominio_nome VARCHAR(255),
    condominio_numero_casa INTEGER,
    condominio_complemento VARCHAR(255)
);
