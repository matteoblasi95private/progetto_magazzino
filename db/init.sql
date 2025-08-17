IF DB_ID('DBMAGAZZINI') IS NULL
  CREATE DATABASE DBMAGAZZINI;
GO

IF NOT EXISTS (SELECT 1 FROM sys.sql_logins WHERE name = 'admin')
  CREATE LOGIN admin WITH PASSWORD = 'Magazzinetto34*';
GO

USE DBMAGAZZINI;
GO

IF NOT EXISTS (SELECT 1 FROM sys.database_principals WHERE name = 'admin')
BEGIN
  CREATE USER admin FOR LOGIN admin;
  EXEC sp_addrolemember 'db_owner', 'admin';
END;
GO



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
	
)

END