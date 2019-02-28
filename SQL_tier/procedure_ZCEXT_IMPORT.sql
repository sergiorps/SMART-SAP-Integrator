CREATE PROCEDURE [dbo].[ZCEXIMPORT] @V_DATADE VARCHAR(8) = NULL, @V_DATAATE VARCHAR(8) = NULL
AS
    
DECLARE @V_OBJ INT;
DECLARE @V_URL VARCHAR(200);
DECLARE @V_RESPONSE VARCHAR(8000);
  
SET @V_URL = 'http://10.150.0.12:8080/conector_smart/services/conn_ceximport?method=runJob&arg1=--context_param datade=' + @V_DATADE + '&arg2=--context_param dataate=' + @V_DATAATE
EXEC SP_OACREATE 'MSXML2.ServerXMLHttp', @V_OBJ out
EXEC SP_OAMETHOD @V_OBJ, 'OPEN', NULL, 'GET', @V_URL, FALSE
EXEC SP_OAMETHOD @V_OBJ, 'SEND'
exec SP_OAGETPROPERTY @V_OBJ, 'responseText', @V_RESPONSE out
  
select 
EXCODIGOEMPRESA,
EXMODELODOCUMENTO,
EXSERIEDOCUMENTO,
EXNUMERODOCUMENTO,
CONVERT(VARCHAR(10), EXDATAEMISSAO, 112) EXDATAEMISSAO,
EXCFOP,
EXCODIGOFORNECEDOR,
EXTIPODECL,
EXNUMERODECLARACAO,
CONVERT(VARCHAR(10), EXDATADECLARACAO, 112) EXDATADECLARACAO,
CONVERT(VARCHAR(10), EXDATAENTRADA, 112) EXDATAENTRADA,
EXVALORTOTAL,
CONVERT(VARCHAR(10), EXDATAEMBARQUE, 112) EXDATAEMBARQUE,
EXNUMEROCONHECIMENTO,
EXDATACONHECIMENTO,
EXTIPOCONHECIMENTO,
EXNOMEPAIS,
EXCODIGOPAIS,
EXDATASISCOMEX,
CONVERT(VARCHAR(10), EXDATADESEMBARACO, 112) EXDATADESEMBARACO,
EXVALORMERCADORIAS,
EXVALORPIS,
EXVALORCOFINS,
EXVALORADUANA,
EXVALORADUANAICMS,
EXVALORIOF,
EXVALORIMPOSTOIMPORT,
EXVALORBASEIPI,
EXVALORIPI,
EXVALORBASEICM,
EXVALORICM,
EXLOCDESEMB,
EXUFDESEMB,
'PADRAO' ORIGEMDADOS
from dbo.ztemp_ceximport
where EXNUMERODECLARACAO <> ''

  
EXEC SP_OADESTROY @V_OBJ
  
RETURN
