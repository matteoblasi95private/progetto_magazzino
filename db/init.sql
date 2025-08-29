IF DB_ID('DBMAGAZZINI') IS NULL
  CREATE DATABASE DBMAGAZZINI;
GO

IF NOT EXISTS (SELECT 1 FROM sys.sql_logins WHERE name = 'admin')
  CREATE LOGIN admin WITH PASSWORD = 'Magazzinetto34*';
GO

IF DB_ID('DBMAGAZZINI') IS NOT NULL
    USE DBMAGAZZINI;
GO

IF NOT EXISTS (SELECT 1 FROM sys.database_principals WHERE name = 'admin')
BEGIN
  CREATE USER admin FOR LOGIN admin;
  EXEC sp_addrolemember 'db_owner', 'admin';
END;
GO


IF  NOT EXISTS (SELECT * FROM sys.objects 
WHERE object_id = OBJECT_ID(N'[dbo].[TIS_ROLE]') AND type in (N'U'))

BEGIN


CREATE TABLE [dbo].[TIS_ROLE](
	[Id] [int] PRIMARY KEY NOT NULL,
	[Name] [varchar](800) NOT NULL,
	[Descr] [varchar](150) NOT NULL
);

INSERT INTO TIS_ROLE VALUES(1, 'USER', 'SIMPLE USER');
INSERT INTO TIS_ROLE VALUES(2, 'ADMIN', 'Amministratore');

END


IF  NOT EXISTS (SELECT * FROM sys.objects 
WHERE object_id = OBJECT_ID(N'[dbo].[TIS_USER]') AND type in (N'U'))

BEGIN


CREATE TABLE [dbo].[TIS_USER](
	[Username] [varchar](60) PRIMARY KEY NOT NULL,
	[Password] [varchar](800) NOT NULL,
	[Email] [varchar](150) NOT NULL,
	[PhoneNumber] [varchar](20) NULL,
	[CreatedAt] [datetime] NULL,
	[UpdatedAt] [datetime] NULL,
	[IdRuolo] INT,
	CONSTRAINT [USER_ROLE_FK] FOREIGN KEY([IdRuolo])
	REFERENCES [dbo].[TIS_ROLE] ([Id])
);

INSERT INTO TIS_USER VALUES('matteo', 'SuperStrongPw1234*', 'aaa@bb', '1234', GETDATE(), GETDATE(), 1);

END


IF  NOT EXISTS (SELECT * FROM sys.objects 
WHERE object_id = OBJECT_ID(N'[dbo].[TIS_CLIENTI]') AND type in (N'U'))

BEGIN


CREATE TABLE [dbo].[TIS_CLIENTI](
	[Id] [int] IDENTITY(1,1) PRIMARY KEY NOT NULL,
	[CodiceFiscale] [varchar](16) NOT NULL,
	[Nome] [varchar](100) NOT NULL,
	[Cognome] [varchar](100) NOT NULL,
	[Email] [varchar](150) NOT NULL,
	[Telefono] [varchar](20) NULL,
	[Indirizzo] [varchar](255) NULL,
	[Citta] [varchar](100) NULL,
	[Cap] [varchar](10) NULL,
	[Paese] [varchar](100) NULL,
	[Data_registrazione] [datetime] NULL,
	[Attivo] [bit] NULL
)

END



IF  NOT EXISTS (SELECT * FROM sys.objects 
WHERE object_id = OBJECT_ID(N'[dbo].[TIS_PRODOTTI]') AND type in (N'U'))

BEGIN


CREATE TABLE [dbo].[TIS_PRODOTTI](
	[Id] [int] IDENTITY(1,1) NOT NULL primary key,
	[CodiceProdotto] [varchar](50) NOT NULL,
	[Nome] [varchar](150) NOT NULL,
	[Descrizione] [text] NULL,
	[Prezzo] [decimal](10, 2) NOT NULL,
	[QuantitaDisponibile] [int] NULL,
	[Categoria] [varchar](100) NULL,
	[Attivo] [bit] NULL,
	[DataCreazione] [datetime] NULL,
	[DataAggiornamento] [datetime] NULL
)

END



IF  NOT EXISTS (SELECT * FROM sys.objects 
WHERE object_id = OBJECT_ID(N'[dbo].[TIS_ORDINI]') AND type in (N'U'))

BEGIN

CREATE TABLE [dbo].[TIS_ORDINI](
	[Id] [int] IDENTITY(1,1) NOT NULL PRIMARY KEY,
	[IdCliente] [int] NULL,
	[IdProdotto] [int] NULL,
	[IdStatoOrdine] [int] NULL,
	[DataCreazione] [datetime] NULL,
	[DataAggiornamento] [datetime] NULL,
	CONSTRAINT [ORDINE_CLIENTE_FK] FOREIGN KEY([IdCliente])
	REFERENCES [dbo].[TIS_CLIENTI] ([Id]),
	CONSTRAINT [ORDINE_PRODOTTO_FK] FOREIGN KEY([IdProdotto])
	REFERENCES [dbo].[TIS_PRODOTTI] ([Id])
	
);

CREATE INDEX IX_ORDINE_CLIENTE  ON dbo.TIS_ORDINI(IdCliente);
CREATE INDEX IX_ORDINE_PRODOTTO ON dbo.TIS_PRODOTTI(IdProdotto);

END


IF NOT EXISTS (SELECT 1 FROM sys.objects WHERE name='TIS_SPEDIZIONE_STATO' AND type='U')
BEGIN
  CREATE TABLE dbo.TIS_SPEDIZIONE_STATO (
    Id       INT IDENTITY(1,1) PRIMARY KEY,
    Codice   VARCHAR(30)  NOT NULL UNIQUE,
    Descr    VARCHAR(150) NOT NULL
  );

  INSERT INTO dbo.TIS_SPEDIZIONE_STATO (Codice,Descr) VALUES
    ('CREATA','Spedizione creata'),
    ('RITIRATA','Ritirata dal corriere'),
    ('IN_TRANSITO','In transito'),
    ('IN_GIACENZA','In giacenza'),
    ('CONSEGNATA','Consegnata'),
    ('FALLITA','Tentata consegna fallita'),
    ('RESO','Reso al mittente'),
	('ANNULLATA', 'Annullata');
END
GO

IF NOT EXISTS (SELECT 1 FROM sys.objects WHERE name='TIS_CORRIERI' AND type='U')
BEGIN
  CREATE TABLE dbo.TIS_CORRIERI (
    Id       INT IDENTITY(1,1) PRIMARY KEY,
    Nome     VARCHAR(100) NOT NULL UNIQUE,
    Sito     VARCHAR(200) NULL
  );

  INSERT INTO dbo.TIS_CORRIERI (Nome) VALUES ('SDA'),('GLS'),('BRT'),('DHL'),('UPS');
END
GO



IF NOT EXISTS (SELECT 1 FROM sys.objects WHERE name='TIS_SPEDIZIONI' AND type='U')
BEGIN

  CREATE TABLE dbo.TIS_SPEDIZIONI (
    Id INT IDENTITY(1,1) PRIMARY KEY,
    IdOrdine INT NOT NULL,
	DataSpedizione DATETIME,
	IdCorriere INT NOT NULL,
	IdStatoSpedizione INT not null,
	CostoSpedizione DECIMAL(18,2),
	TrackingNumber VARCHAR(80) NOT NULL UNIQUE,

    DestNome            VARCHAR(150)  NOT NULL,
    DestIndirizzo       VARCHAR(255)  NOT NULL,
    DestCap             VARCHAR(10)   NOT NULL,
    DestCitta           VARCHAR(100)  NOT NULL,
    DestProvincia       VARCHAR(50)   NULL,
    DestPaese           VARCHAR(100)  NOT NULL,

    DataCreazione        DATETIME2(0)  NOT NULL CONSTRAINT DF_SPED_CREAZ DEFault SYSUTCDATETIME(),
    DataAggiornamento    DATETIME2(0)  NOT NULL CONSTRAINT DF_SPED_AGG   DEFault SYSUTCDATETIME(),
    DataRitiro           DATETIME2(0)  NULL,
    DataConsegnaPrevista DATETIME2(0)  NULL,
    DataConsegnaEffettiva DATETIME2(0) NULL,

    Note                 VARCHAR(500)  NULL,

    CONSTRAINT FK_SPED_ORDINE   FOREIGN KEY (IdOrdine)  REFERENCES dbo.TIS_ORDINI(Id),
    CONSTRAINT FK_SPED_CORRIERE FOREIGN KEY (IdCorriere)REFERENCES dbo.TIS_CORRIERI(Id),
    CONSTRAINT FK_SPED_STATO    FOREIGN KEY (IdStatoSpedizione)   REFERENCES dbo.TIS_SPEDIZIONE_STATO(Id),

    CONSTRAINT CK_SPED_COSTO    CHECK (CostoSpedizione >= 0)
  );

  CREATE INDEX IX_SPED_IDORDINE  ON dbo.TIS_SPEDIZIONI(IdOrdine);
  CREATE INDEX IX_SPED_STATO     ON dbo.TIS_SPEDIZIONI(IdStato);
  
END
GO



IF NOT EXISTS (SELECT 1 FROM sys.objects WHERE name='TIS_SPEDIZIONI_STORICO' AND type='U')
BEGIN
  CREATE TABLE dbo.TIS_SPEDIZIONI_STORICO(
    Id             INT IDENTITY(1,1) PRIMARY KEY,
    IdSpedizione   INT NOT NULL,
    IdStato   VARCHAR(60) NOT NULL,
    Descrizione    VARCHAR(255) NULL,
    DataEvento     DATETIME NOT NULL,
    CONSTRAINT FK_SPEV_SPED FOREIGN KEY (IdSpedizione) REFERENCES dbo.TIS_SPEDIZIONI(Id),
	CONSTRAINT FK_SPED_STATO FOREIGN KEY (IdStato) REFERENCES dbo.TIS_SPEDIZIONE_STATO(Id)
  );

  CREATE INDEX IX_SPEV_SPED_DATA ON dbo.TIS_SPEDIZIONI_STORICO(IdSpedizione, DataEvento DESC);
END
GO



IF NOT EXISTS (SELECT 1 FROM sys.objects WHERE name='TIS_MAGAZZINI' AND type='U')
BEGIN
  CREATE TABLE dbo.TIS_MAGAZZINI (
    Id           INT IDENTITY(1,1) PRIMARY KEY,
    Codice       VARCHAR(30)  NOT NULL UNIQUE,
    Nome         VARCHAR(100) NOT NULL,
    Indirizzo    VARCHAR(255) NULL,
    Citta        VARCHAR(100) NULL,
    Paese        VARCHAR(100) NULL,
    Attivo       BIT NOT NULL DEFAULT 1,
    DataCreazione DATETIME
  );
END
GO



IF NOT EXISTS (SELECT 1 FROM sys.objects WHERE name='TIS_GIACENZE' AND type='U')
BEGIN
  CREATE TABLE TIS_GIACENZE(
	IdMagazzino INT NOT NULL,
	IdProdotto INT NOT NULL,
	QuantitaDisponibile INT NOT NULL,
	DataAggiornamento DATETIME,
	PRIMARY KEY(IdMagazzino, IdProdotto),
	CONSTRAINT FK_GIACENZE_MAGAZZINO FOREIGN KEY(IdMagazzino) REFERENCES dbo.TIS_MAGAZZINI(Id),
	CONSTRAINT FK_GIACENZE_PRODOTTI FOREIGN KEY(IdProdotto) REFERENCES dbo.TIS_PRODOTTI(Id)
  );
END
GO


IF NOT EXISTS (SELECT 1 FROM sys.objects WHERE name='TIS_MAGAZZINO_STORICO_MOVIMENTI' AND type='U')
BEGIN
  CREATE TABLE TIS_MAGAZZINO_STORICO_MOVIMENTI(
  
    Id INT NOT NULL IDENTITY PRIMARY KEY,
	IdMagazzino INT NOT NULL,
	IdProdotto INT NOT NULL,
	QuantitaMovimento INT NOT NULL,
	TipoMovimento VARCHAR(30) NOT NULL,
	DataMovimento DATETIME,
	Riferimento VARCHAR(100),
	Utente varchar(100) NOT NULL,
	Note VARCHAR(500),
	CONSTRAINT FK_MOV_MAG FOREIGN KEY (IdMagazzino) REFERENCES dbo.TIS_MAGAZZINI(Id),
    CONSTRAINT FK_MOV_PROD FOREIGN KEY (IdProdotto) REFERENCES dbo.TIS_PRODOTTI(Id),
	CONSTRAINT QT_MOV_MAGGIORE_ZERO CHECK (QuantitaMovimento > 0)
  );
  
    CREATE INDEX IX_MOV_MAGPROD ON dbo.TIS_MAGAZZINO_STORICO_MOVIMENTI(IdMagazzino, IdProdotto, DataMovimento DESC);
  
END
GO