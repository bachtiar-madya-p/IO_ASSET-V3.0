USE [master]
GO

CREATE DATABASE [umx]
GO

USE [umx]
GO
/****** Object:  Table [dbo].[application]    Script Date: 14/10/2019 11:57:16 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[application](
	[id] [bigint] IDENTITY(1,1) NOT NULL,
	[name] [nvarchar](128) NOT NULL,
	[configurationName] [nvarchar](128) NOT NULL,
	[recipientList] [nvarchar](256) NULL,
	[attributeList] [nvarchar](500) NOT NULL,
	[mailSubject] [varchar](1000) NULL,
	[mailBody] [varchar](5000) NULL,
 CONSTRAINT [application_PK] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[dispensation]    Script Date: 14/10/2019 11:57:16 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[dispensation](
	[id] [bigint] IDENTITY(1,1) NOT NULL,
	[applicationId] [bigint] NOT NULL,
	[name] [nvarchar](128) NOT NULL,
 CONSTRAINT [dispensation_PK] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[execution]    Script Date: 14/10/2019 11:57:16 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[execution](
	[id] [varchar](50) NOT NULL,
	[applicationId] [bigint] NOT NULL,
	[status] [varchar](50) NOT NULL,
	[startDt] [datetime] NOT NULL,
	[completedDt] [datetime] NULL,
	[name] [varchar](100) NULL,
 CONSTRAINT [execution_PK] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[execution_data]    Script Date: 14/10/2019 11:57:16 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[execution_data](
	[executionId] [varchar](50) NOT NULL,
	[whiteListCompliant] [int] NOT NULL,
	[whitelistNonCompliant] [int] NOT NULL,
	[whitelistProcessed] [int] NOT NULL,
	[normalCompliant] [int] NOT NULL,
	[normalNonCompliant] [int] NOT NULL,
	[normalProcessed] [int] NOT NULL,
	[rulesProcessed] [int] NOT NULL,
	[accountsProcessed] [int] NOT NULL,
 CONSTRAINT [execution_data_PK] PRIMARY KEY CLUSTERED 
(
	[executionId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[execution_violation]    Script Date: 14/10/2019 11:57:16 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[execution_violation](
	[executionId] [varchar](50) NOT NULL,
	[accountId] [varchar](100) NOT NULL,
	[roleList] [varchar](5000) NOT NULL,
	[accountType] [varchar](50) NOT NULL,
	[userId] [varchar](100) NOT NULL,
 CONSTRAINT [execution_violation_PK] PRIMARY KEY CLUSTERED 
(
	[executionId] ASC,
	[accountId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[rule]    Script Date: 14/10/2019 11:57:16 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[rule](
	[id] [bigint] IDENTITY(1,1) NOT NULL,
	[applicationId] [bigint] NOT NULL,
	[attributeMap] [nvarchar](4000) NOT NULL,
	[rolename] [nvarchar](256) NOT NULL,
	[hash] [varchar](256) NULL,
 CONSTRAINT [rule_PK] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[setting]    Script Date: 14/10/2019 11:57:16 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[setting](
	[id] [bigint] IDENTITY(1,1) NOT NULL,
	[name] [nvarchar](128) NOT NULL,
	[value] [nvarchar](512) NOT NULL,
	[sortorder] [int] NULL,
	[description] [nvarchar](512) NULL,
 CONSTRAINT [setting_PK] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO

ALTER TABLE [dbo].[execution] ADD  CONSTRAINT [DF_execution_startDt]  DEFAULT (getdate()) FOR [startDt]
GO

ALTER TABLE [dbo].[dispensation]  WITH CHECK ADD  CONSTRAINT [dispensation_FK] FOREIGN KEY([applicationId])
REFERENCES [dbo].[application] ([id])
ON DELETE CASCADE
GO

ALTER TABLE [dbo].[dispensation] CHECK CONSTRAINT [dispensation_FK]
GO

ALTER TABLE [dbo].[execution]  WITH CHECK ADD  CONSTRAINT [execution_FK] FOREIGN KEY([applicationId])
REFERENCES [dbo].[application] ([id])
ON DELETE CASCADE
GO

ALTER TABLE [dbo].[execution] CHECK CONSTRAINT [execution_FK]
GO

ALTER TABLE [dbo].[execution_data]  WITH CHECK ADD  CONSTRAINT [execution_data_FK] FOREIGN KEY([executionId])
REFERENCES [dbo].[execution] ([id])
ON DELETE CASCADE
GO

ALTER TABLE [dbo].[execution_data] CHECK CONSTRAINT [execution_data_FK]
GO

ALTER TABLE [dbo].[execution_violation]  WITH CHECK ADD  CONSTRAINT [execution_violation_FK] FOREIGN KEY([executionId])
REFERENCES [dbo].[execution] ([id])
ON DELETE CASCADE
GO

ALTER TABLE [dbo].[execution_violation] CHECK CONSTRAINT [execution_violation_FK]
GO

ALTER TABLE [dbo].[rule]  WITH CHECK ADD  CONSTRAINT [rule_FK] FOREIGN KEY([applicationId])
REFERENCES [dbo].[application] ([id])
ON DELETE CASCADE
GO

ALTER TABLE [dbo].[rule] CHECK CONSTRAINT [rule_FK]
GO


TRUNCATE TABLE [setting];

INSERT INTO umx.dbo.setting (name,value,sortorder,description) VALUES 
('umx.url','http://192.168.1.176:8080/umx',1,'UMX URL')
,('idg.url','http://192.168.0.216:8082',2,'IDG URL')
,('idg.authorization','Basic QUQxXEVBZG1pbjpwYXNzd29yZA==',3,'IDG Authorization Header')
,('mail.server.host','192.168.0.32',4,'Mail Server Host')
,('mail.server.port','25',5,'Mail Server Port')
,('mail.sender','no-reply@ic.sg',6,'Mail Sender')
,('mail.subject','Please remove excessive roles now',7,'Mail Subject')
,('mail.content','Hi, Updated content to include this.',8,'Mail Content');